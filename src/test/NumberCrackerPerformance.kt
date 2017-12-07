import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import kotlin.system.measureTimeMillis

class NumberCrackerPerformance {

    @BeforeSuite
    fun warmup() {
        NumberCracker.crackCode()
    }

    @Test
    fun `test performance`() {
        var accumulatedTime = 0L
        for (attempt in 1..10) {
            accumulatedTime+= measureTimeMillis { NumberCracker.crackCode() }
        }

        println("Average time for cracking is ${accumulatedTime/10.0} ms")
    }


}