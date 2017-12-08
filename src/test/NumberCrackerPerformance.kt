import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import kotlin.system.measureTimeMillis

class NumberCrackerPerformance {

    @BeforeSuite
    fun warmUp() {
        NumberCracker.crackCodeFast()
        NumberCracker.crackCode()
    }

    @Test
    fun `test performance`() {
        var accumulatedTime = 0L
        for (attempt in 1..10) {
            accumulatedTime+= measureTimeMillis { println("$attempt: ${NumberCracker.crackCode()}") }
        }

        println("Average time for cracking is ${accumulatedTime/10.0} ms")
    }

    @Test
    fun `test performance fast`() {
        var accumulatedTime = 0L
        for (attempt in 1..10) {
            accumulatedTime+= measureTimeMillis  { println("$attempt: ${NumberCracker.crackCodeFast()}") }
        }

        println("Average time for fast cracking is ${accumulatedTime/10.0} ms")
    }


}