class UniqueCodeDescIterator
constructor(
        start: Long,
        private val end: Long = 0,
        private val length: Int = start.toString().length
) : Iterator<String> {
    constructor(start: String) : this(start.toLong(), length = start.length)

    private var nextValue: String?
    private val nextValueArray = CharArray(length)

    private val minValueForLength by lazy { "0123456789".substring(0, length).toLong() }
    private val maxValueForLength by lazy { "9876543210".substring(0, length).toLong() }

    init {
        if (start < 0) throw kotlin.IllegalArgumentException("Start must be positive")
        if (length < 1 || length > 10) throw kotlin.IllegalArgumentException("Length must be between 1 and 10")

        nextValue = when {
            start < end -> null
            start < minValueForLength -> null
            start >= maxValueForLength -> {
                maxValueForLength.toCode().toCharArray(nextValueArray)
                String(nextValueArray)
            }
            else -> {
                start.toCode().toCharArray(nextValueArray)
                findNextPossibleUniqueCodeDesc(nextValueArray)
                String(nextValueArray)
            }
        }
        safeGuardEnd()
    }

    private fun Long.toCode() = this.toString().padStart(length, '0')

    override fun hasNext(): Boolean = nextValue != null

    override fun next(): String {
        val next = nextValue ?: throw kotlin.NoSuchElementException()
        nextValue = if (decrementUniqueCode(nextValueArray)) {
            String(nextValueArray)
        } else {
            null
        }
        safeGuardEnd()
        return next
    }

    private fun safeGuardEnd() {
        if (end > 0 && nextValue?.toLong() ?: -1 < end)
            nextValue = null
    }

}

/**
 * Find the next lower or equal unique code from the given start [code].
 *
 * Returns true if a valid code was found, false otherwise.
 */
private fun findNextPossibleUniqueCodeDesc(code: CharArray): Boolean {
    var pos = 0
    while (pos < code.size && !code.containsRtoL(code[pos], pos - 1)) {
        pos++
    }
    if (pos < code.size) {
        // search for next allowed lower digit
        var newChar = code[pos] - 1
        while (code.containsRtoL(newChar, pos - 1)) newChar--
        if (newChar >= '0') {
            code[pos] = newChar
            pos++
        } else {
            if (!decrementUniqueCode(code, pos - 1))
                return false
        }
    }
    while (pos < code.size) {
        var newChar = '9'
        while (code.containsRtoL(newChar, pos - 1)) newChar--
        if (newChar < '0')
            return false
        code[pos] = newChar
        pos++
    }
    return true
}

fun findNextPossibleUniqueCodeDesc(input: String): String? {
    val array = input.toCharArray()
    return if (findNextPossibleUniqueCodeDesc(array)) String(array) else null
}

/**
 * Tries to decrement the given a unique code in [uniqueCode] at [position].
 *
 * Return true if this was possible, false otherwise
 */
private fun decrementUniqueCode(uniqueCode: CharArray, position: Int = uniqueCode.size - 1): Boolean {
    // at the very first position, simply decrease the digit, if possible
    if (position == 0) {
        return if (uniqueCode[0] > '0') {
            uniqueCode[0]--
            true
        } else false
    }

    // if the digit has room to decrease, try to
    if (uniqueCode[position] > '0') {
        var newChar = uniqueCode[position] - 1
        while (uniqueCode.containsRtoL(newChar, position - 1)) newChar--
        if (newChar >= '0') {
            uniqueCode[position] = newChar
            return true
        }
    }
    if (!decrementUniqueCode(uniqueCode, position - 1)) return false

    var newChar = '9'
    while (uniqueCode.containsRtoL(newChar, position - 1)) newChar--
    uniqueCode[position] = newChar
    return true
}

/**
 * fast search for an [element] in a CharArray starting at given [startPos] down to 0.
 *
 * Returns true if element was found, false otherwise.
 */
private fun CharArray.containsRtoL(element: Char, startPos: Int = this.size - 1): Boolean {
    var pos = startPos
    while (pos >= 0) {
        if (this[pos] == element)
            return true
        pos--
    }
    return false
}

fun decrementUniqueCode(input: String): String? {
    val array = input.toCharArray()
    return if (decrementUniqueCode(array)) String(array) else null
}

infix fun String.downToUnique(end: String) = UniqueCodeDescIterator(this.toLong(), end.toLong(), this.length)
