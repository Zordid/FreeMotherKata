class NumberCracker {
    companion object {

        /**
         * As per code definition, condition 1 means:
         * - must be 9 different digits
         */
        fun testCondition1(str: String): Boolean {
            return str.toSet().size == 9
        }

        /**
         * As per code definition, condition 2 means:
         * - read the digits from right to left and it must be a square number
         */
        fun testCondition2(str: String): Boolean {
            val reversed = str.reversed().toInt()
            val sqrt = Math.sqrt(reversed.toDouble());
            return sqrt.toInt() * sqrt.toInt() == reversed
        }

    }

}

fun main(args: Array<String>) {
    for (i in 987654321 downTo 0 ) {
        val s = i.toString().padStart(9, '0')

        if (NumberCracker.testCondition1(s) && NumberCracker.testCondition2(s)) {
            println("The number cracker password is: $s")
            break
        }
    }
}