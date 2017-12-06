import org.testng.Assert
import org.testng.Assert.*
import org.testng.annotations.Test

class NumberCrackerTest {

    @Test
    fun `Teste Bedingung 1 nur auf Länge`() {
        Assert.assertFalse(NumberCracker.testCondition1("123"))
        Assert.assertTrue(NumberCracker.testCondition1("123456789"))
        Assert.assertFalse(NumberCracker.testCondition1("1234567890"))
    }

    @Test
    fun `Teste Bedingung 1 Länge und unterschiedliche Ziffern`() {
        Assert.assertTrue(NumberCracker.testCondition1("123456789"))
        Assert.assertTrue(NumberCracker.testCondition1("123456798"))
        Assert.assertFalse(NumberCracker.testCondition1("123456788"))
    }

    @Test
    fun `Teste Bedingung 2`() {
        Assert.assertTrue(NumberCracker.testCondition2("46"))
        Assert.assertFalse(NumberCracker.testCondition2("64"))
        Assert.assertTrue(NumberCracker.testCondition2((123 * 123).toString().reversed()))
    }

    @Test
    fun `test the magic code`() {
        val code = "982356417"
        Assert.assertTrue(NumberCracker.testCondition1(code) && NumberCracker.testCondition2(code))
    }

    @Test
    fun `test there is no bigger code`() {
        val code = 982356417
        for (i in (code+1) until 1000000000) {
            Assert.assertFalse(NumberCracker.testCondition1(i.toString()) && NumberCracker.testCondition2(i.toString()))
        }
    }

}