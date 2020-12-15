package bootimg

import cfig.bootimg.cpio.AndroidCpio
import cfig.bootimg.cpio.AndroidCpioEntry
import cfig.helper.Helper
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidCpioEntryTest {
    @Test
    fun dirEntry() {
        run {//dir, fileMode 040755
            val entry1 = AndroidCpioEntry(name = "acct", statMode = 0x41ed, data = byteArrayOf(), ino = 300000)
            val exp = Helper.fromHexString("3037303730313030303439336530303030303431656430303030303030303030303030303030303030303030303130303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030353030303030303030616363740000")
            Assert.assertTrue(entry1.encode().contentEquals(exp))
            Assert.assertTrue(entry1.encode2().contentEquals(exp))
        }

        run {//dir, fileMode 040755
            val entry2 = AndroidCpioEntry(name = "apex", statMode = 0x41ed, data = byteArrayOf(), ino = 300001)
            val exp2 = Helper.fromHexString("3037303730313030303439336531303030303431656430303030303030303030303030303030303030303030303130303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030353030303030303030617065780000")
            assertTrue(entry2.encode().contentEquals(exp2))
            assertTrue(entry2.encode2().contentEquals(exp2))
        }
    }

    @Test
    fun linkEntry() {
        run {//link, fileMode 0120777
            val entry3 = AndroidCpioEntry(name = "bin", statMode = 0xa1a4, data = "/system/bin".toByteArray(), ino = 300002)
            val exp3 = Helper.fromHexString("303730373031303030343933653230303030613161343030303030303030303030303030303030303030303030313030303030303030303030303030306230303030303030303030303030303030303030303030303030303030303030303030303030303034303030303030303062696e0000002f73797374656d2f62696e00")
            entry3.encode()
            entry3.encode2()
            entry3.encode()
            entry3.encode2()
            assertTrue(exp3.contentEquals(entry3.encode()))
            assertTrue(exp3.contentEquals(entry3.encode2()))
        }
    }

    @Test
    fun fileEntry() {
        //init.environ.rc
        val initrc = "23207365742075702074686520676c6f62616c20656e7669726f6e6d656e740a6f6e206561726c792d696e69740a202020206578706f727420414e44524f49445f424f4f544c4f474f20310a202020206578706f727420414e44524f49445f524f4f54202f73797374656d0a202020206578706f727420414e44524f49445f415353455453202f73797374656d2f6170700a202020206578706f727420414e44524f49445f44415441202f646174610a202020206578706f727420414e44524f49445f53544f52414745202f73746f726167650a202020206578706f727420414e44524f49445f4152545f524f4f54202f617065782f636f6d2e616e64726f69642e6172740a202020206578706f727420414e44524f49445f4931384e5f524f4f54202f617065782f636f6d2e616e64726f69642e6931386e0a202020206578706f727420414e44524f49445f545a444154415f524f4f54202f617065782f636f6d2e616e64726f69642e747a646174610a202020206578706f72742045585445524e414c5f53544f52414745202f7364636172640a202020206578706f727420415345435f4d4f554e54504f494e54202f6d6e742f617365630a202020206578706f727420424f4f54434c41535350415448202f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f636f72652d6f6a2e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f636f72652d6c69626172742e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f636f72652d696375346a2e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f6f6b687474702e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f626f756e6379636173746c652e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f6170616368652d786d6c2e6a61723a2f73797374656d2f6672616d65776f726b2f6672616d65776f726b2e6a61723a2f73797374656d2f6672616d65776f726b2f6578742e6a61723a2f73797374656d2f6672616d65776f726b2f74656c6570686f6e792d636f6d6d6f6e2e6a61723a2f73797374656d2f6672616d65776f726b2f766f69702d636f6d6d6f6e2e6a61723a2f73797374656d2f6672616d65776f726b2f696d732d636f6d6d6f6e2e6a61723a2f73797374656d2f6672616d65776f726b2f6672616d65776f726b2d6174622d6261636b776172642d636f6d7061746962696c6974792e6a61723a2f617065782f636f6d2e616e64726f69642e636f6e7363727970742f6a6176616c69622f636f6e7363727970742e6a61723a2f617065782f636f6d2e616e64726f69642e6d656469612f6a6176616c69622f757064617461626c652d6d656469612e6a61723a2f617065782f636f6d2e616e64726f69642e6d6564696170726f76696465722f6a6176616c69622f6672616d65776f726b2d6d6564696170726f76696465722e6a61723a2f617065782f636f6d2e616e64726f69642e6f732e7374617473642f6a6176616c69622f6672616d65776f726b2d7374617473642e6a61723a2f617065782f636f6d2e616e64726f69642e7065726d697373696f6e2f6a6176616c69622f6672616d65776f726b2d7065726d697373696f6e2e6a61723a2f617065782f636f6d2e616e64726f69642e73646b6578742f6a6176616c69622f6672616d65776f726b2d73646b657874656e73696f6e732e6a61723a2f617065782f636f6d2e616e64726f69642e776966692f6a6176616c69622f6672616d65776f726b2d776966692e6a61723a2f617065782f636f6d2e616e64726f69642e746574686572696e672f6a6176616c69622f6672616d65776f726b2d746574686572696e672e6a61720a202020206578706f727420444558324f4154424f4f54434c41535350415448202f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f636f72652d6f6a2e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f636f72652d6c69626172742e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f636f72652d696375346a2e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f6f6b687474702e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f626f756e6379636173746c652e6a61723a2f617065782f636f6d2e616e64726f69642e6172742f6a6176616c69622f6170616368652d786d6c2e6a61723a2f73797374656d2f6672616d65776f726b2f6672616d65776f726b2e6a61723a2f73797374656d2f6672616d65776f726b2f6578742e6a61723a2f73797374656d2f6672616d65776f726b2f74656c6570686f6e792d636f6d6d6f6e2e6a61723a2f73797374656d2f6672616d65776f726b2f766f69702d636f6d6d6f6e2e6a61723a2f73797374656d2f6672616d65776f726b2f696d732d636f6d6d6f6e2e6a61723a2f73797374656d2f6672616d65776f726b2f6672616d65776f726b2d6174622d6261636b776172642d636f6d7061746962696c6974792e6a61720a202020206578706f72742053595354454d534552564552434c41535350415448202f73797374656d2f6672616d65776f726b2f636f6d2e616e64726f69642e6c6f636174696f6e2e70726f76696465722e6a61723a2f73797374656d2f6672616d65776f726b2f73657276696365732e6a61723a2f73797374656d2f6672616d65776f726b2f65746865726e65742d736572766963652e6a61723a2f617065782f636f6d2e616e64726f69642e7065726d697373696f6e2f6a6176616c69622f736572766963652d7065726d697373696f6e2e6a61723a2f617065782f636f6d2e616e64726f69642e776966692f6a6176616c69622f736572766963652d776966692e6a61723a2f617065782f636f6d2e616e64726f69642e69707365632f6a6176616c69622f616e64726f69642e6e65742e69707365632e696b652e6a61720a202020200a202020200a202020200a202020200a"
        println("<" + String(Helper.fromHexString(initrc)) + ">")
        val entry = AndroidCpioEntry(name = "/init.environ.rc",
                statMode = java.lang.Long.valueOf("100644", 8),
                data = Helper.fromHexString(initrc),
                ino = 300003)
        assertTrue(entry.encode().contentEquals(entry.encode2()))
    }

    fun packTest() {
        val dir = "/home/yu/work/boot/build/unzip_boot/root"
        val oF = "/home/yu/work/boot/root.cpio"
        val acpio = AndroidCpio()
        acpio.pack(dir, oF, "/home/yu/work/boot/build/unzip_boot/ramdisk_filelist.txt")
    }
}
