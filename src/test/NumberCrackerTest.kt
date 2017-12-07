import org.testng.Assert
import org.testng.annotations.Test

class NumberCrackerTest {

    @Test
    fun `test condition 1 only for length`() {
        // too short
        Assert.assertFalse(NumberCracker.testCondition1("123"))
        // just right
        Assert.assertTrue(NumberCracker.testCondition1("123456789"))
        // too long
        Assert.assertFalse(NumberCracker.testCondition1("1234567890"))
    }

    @Test
    fun `test condition 1 for length and uniqueness of digits`() {
        // fine
        Assert.assertTrue(NumberCracker.testCondition1("123456789"))
        // fine as well, just flipped numbers
        Assert.assertTrue(NumberCracker.testCondition1("123456798"))
        // not okay, contains duplicate 8
        Assert.assertFalse(NumberCracker.testCondition1("123456788"))
    }

    @Test
    fun `test condition 1 for containing only digits`() {
        // fine, only digits
        Assert.assertTrue(NumberCracker.testCondition1("123456789"))
        // not okay, contains an "A"
        Assert.assertFalse(NumberCracker.testCondition1("1234A6789"))
    }

    @Test
    fun `test condition 2`() {
        // fine as 64 is a square number
        Assert.assertTrue(NumberCracker.testCondition2("46"))
        // not okay, as 46 is not a square number
        Assert.assertFalse(NumberCracker.testCondition2("64"))
        // large square number in reverse - fine
        Assert.assertTrue(NumberCracker.testCondition2((123 * 123).toString().reversed()))
    }

    @Test
    fun `test condition 3`() {
        // the solution which is known for this test
        val codeAsInt = 982356417
        Assert.assertTrue(NumberCracker.testCondition3(codeAsInt.toString()))
        Assert.assertFalse(NumberCracker.testCondition3((codeAsInt-1).toString()))
    }

    @Test
    fun `test the magic code cracker and all conditions`() {
        val crackedCode = NumberCracker.crackCode()
        Assert.assertTrue(NumberCracker.testCondition1(crackedCode ?: ""))
        Assert.assertTrue(NumberCracker.testCondition2(crackedCode ?: ""))
        Assert.assertTrue(NumberCracker.testCondition3(crackedCode ?: ""))
    }

}