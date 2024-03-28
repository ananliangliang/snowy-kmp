package util

import kotlin.test.Test
import kotlin.test.assertEquals

class SmUtilsTest {
    
    @Test
    fun `should return correct sm3 digest when input is string 1` () {
        val digest = SmUtils.doSm3Digest("1")
        assertEquals("cbdddb8e8421b23498480570d7d75330538a6882f5dfdc3b64115c647f3328c4", digest)
    }
}