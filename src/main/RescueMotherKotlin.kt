class NumberCracker {
    companion object {

        /**
         * As per code definition, condition 1 means:
         * - must be 9 different digits
         */
        fun testCondition1(str:String): Boolean {
            return str.toSet().size == 9
        }
    }
}