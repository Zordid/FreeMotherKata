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
    }

}