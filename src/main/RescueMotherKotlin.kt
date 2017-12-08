import NumberCracker.Companion.crackCodeFast
import kotlin.system.measureTimeMillis

class NumberCracker {
    companion object {

        // we know for sure the possible range of combinations
        private val MAX_POSSIBLE_RANGE = 12345678..999999999

        /**
         * As per code definition, condition 1 means:
         * - must be 9 different digits [0..9]
         */
        internal fun testCondition1(code: String): Boolean {
            return code.toSet().filter { it in '0'..'9' }.size == 9
        }

        /**
         * As per code definition, condition 2 means:
         * - read the digits from right to left and it must be a square number
         */
        internal fun testCondition2(code: String): Boolean {
            val reversed = code.reversed().toInt()
            return isSquareNumber(reversed)
        }

        /**
         * Fast square number detection algorithm taken from
         * https://www.johndcook.com/blog/2008/11/17/fast-way-to-test-whether-a-number-is-a-square/
         *
         * Returns true if [number] is a square number, false otherwise
         */
        private fun isSquareNumber(number: Int): Boolean {
            val h = number and 0xF // last hexadecimal "digit"
            if (h > 9)
                return false // return immediately in 6 cases out of 16.
            // Take advantage of Boolean short-circuit evaluation
            if ( h != 2 && h != 3 && h != 5 && h != 6 && h != 7 && h != 8 ) {
                val sqrt = Math.sqrt(number.toDouble()).toInt()
                return sqrt * sqrt == number
            }
            return false
        }

        /**
         * As per code definition, condition 3 means:
         * - there must not be a greater number than the code that fulfills the first two conditions
         */
        internal fun testCondition3(code: String): Boolean {
            val codeAsInt = try {
                code.toInt()
            } catch (e: NumberFormatException) {
                return false
            }
            return ((codeAsInt + 1)..MAX_POSSIBLE_RANGE.last).none { testConditions1And2(it.toString()) }
        }

        private fun testConditions1And2(code: String) = testCondition1(code) && testCondition2(code)

        fun crackCode(rangeToTest: IntRange = MAX_POSSIBLE_RANGE): String? {
            for (t in rangeToTest.reversed()) {
                val code = t.toString().padStart(9, '0')
                if (testConditions1And2(code))
                    return code
            }
            return null
        }

        fun crackCodeFast(): String? {
            for (code in "999999999" downToUnique "0") {
                if (testCondition2(code)) return code
            }
            return null
        }

    }

}

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        println(crackCodeFast()?.let { "Found the matching code: $it" } ?: "No matching code found.")
    }
    print("Took $time ms.")

    println("\nNow counting all possible values in the range 999999999 to 0")
    var count = 0
    for (i in "999999999" downToUnique "0")
        count++
    println("There are $count combinations")

}