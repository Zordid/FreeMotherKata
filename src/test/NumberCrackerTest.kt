import org.testng.Assert
import org.testng.Assert.*
import org.testng.annotations.Test

class NumberCrackerTest {

    @Test
    fun `Teste Bedingung 1 nur auf Länge`() {
        Assert.assertFalse(NumberCracker.testCondition1("123"));
    }

}