class NumberCracker {
    companion object {

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
            val sqrt = Math.sqrt(reversed.toDouble());
            return sqrt.toInt() * sqrt.toInt() == reversed
        }

        fun testCode(code: String) = testCondition1(code) && testCondition2(code)

    }

}

fun main(args: Array<String>) {
    for (i in 987654321 downTo 12345678) {
        val code = i.toString().padStart(9, '0')

        if (NumberCracker.testCode(code)) {
            println("The number cracker password is: $code")
            return
        }
    }
    println("No matching code found.")
}