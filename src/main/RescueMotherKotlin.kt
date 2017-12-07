import NumberCracker.Companion.crackCode
import kotlin.system.measureTimeMillis

class NumberCracker {
    companion object {

        // we know for sure the possible range of combinations
        private val MAX_POSSIBLE_RANGE = 12345678..987654321

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
            return ((codeAsInt+1) .. MAX_POSSIBLE_RANGE.last).none { testConditions1And2(it.toString()) }
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

    }

}

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        print(crackCode()?.let { "Found the matching code: $it" } ?: "No matching code found.")
    }
    print("Took $time ms.")
}