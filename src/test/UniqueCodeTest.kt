import org.testng.Assert
import org.testng.annotations.Test

class UniqueCodeTest {

    @Test
    fun `test finding next possible exact match`() {
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("9"), "9")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("98"), "98")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("987"), "987")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("9876"), "9876")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("98765"), "98765")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("987654"), "987654")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("9876543"), "9876543")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("98765432"), "98765432")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("987654321"), "987654321")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("9876543210"), "9876543210")
    }

    @Test
    fun `test finding next possible from above`() {
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("11"), "10")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("111"), "109")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("1111"), "1098")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("1000"), "0987")
        Assert.assertEquals(findNextPossibleUniqueCodeDesc("1012"), "0987")
    }

    @Test
    fun `test finding next possible fails`() {
        Assert.assertNull(findNextPossibleUniqueCodeDesc("00"))
        Assert.assertNull(findNextPossibleUniqueCodeDesc("011"))
        Assert.assertNull(findNextPossibleUniqueCodeDesc("0122"))
        Assert.assertNull(findNextPossibleUniqueCodeDesc("01233"))
        Assert.assertNull(findNextPossibleUniqueCodeDesc("012344"))
    }

    @Test
    fun `test iterator simple`() {
        Assert.assertEquals(
                ("9" downToUnique "0").asSequence().toList(),
                "9 8 7 6 5 4 3 2 1 0".split(" ")
        )
        Assert.assertEquals(
                ("9" downToUnique "4").asSequence().toList(),
                "9 8 7 6 5 4".split(" ")
        )
    }

    @Test
    fun `test iterator two digits`() {
        Assert.assertEquals(
                ("11" downToUnique "0").asSequence().toList(),
                "10 09 08 07 06 05 04 03 02 01".split(" ")
        )
        Assert.assertEquals(
                ("100" downToUnique "95").asSequence().toList(),
                "098 097 096 095".split(" ")
        )
    }

    @Test
    fun `test iterator empty range`() {
        Assert.assertEquals(
                ("010" downToUnique "0").asSequence().toList(),
                emptyList<String>()
        )
        Assert.assertEquals(
                ("1009" downToUnique "1000").asSequence().toList(),
                emptyList<String>()
        )

    }

}