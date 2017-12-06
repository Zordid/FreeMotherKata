class NumberCracker {
    companion object {

        /**
         * As per code definition, condition 1 means:
         * - must be 9 different digits
         */
        fun testCondition1(str: String): Boolean {
            return str.toSet().size == 9
        }

        fun testCondition2(str: String): Boolean {
            val reversed = str.reversed().toInt()
            val sqrt = Math.sqrt(reversed.toDouble());
            return sqrt.toInt() * sqrt.toInt() == reversed
        }

    }
}