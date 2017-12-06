import org.testng.Assert
import org.testng.Assert.*
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
        Assert.assertTrue(NumberCracker.testCondition1("123456789"))
        Assert.assertTrue(NumberCracker.testCondition1("123456798"))
        Assert.assertFalse(NumberCracker.testCondition1("123456788"))
    }

    @Test
    fun `test condition 1 for containing only digits`() {
        Assert.assertTrue(NumberCracker.testCondition1("123456789"))
        Assert.assertFalse(NumberCracker.testCondition1("1234A6789"))
    }

    @Test
    fun `test condition 2`() {
        Assert.assertTrue(NumberCracker.testCondition2("46"))
        Assert.assertFalse(NumberCracker.testCondition2("64"))
        Assert.assertTrue(NumberCracker.testCondition2((123 * 123).toString().reversed()))
    }

    @Test
    fun `test the magic code`() {
        val code = "982356417"
        Assert.assertTrue(NumberCracker.testCode(code))
    }

    @Test
    fun `test that there is no larger code`() {
        val code = 982356417
        for (i in (code + 1) until 1000000000) {
            Assert.assertFalse(NumberCracker.testCode(i.toString()))
        }
    }

}