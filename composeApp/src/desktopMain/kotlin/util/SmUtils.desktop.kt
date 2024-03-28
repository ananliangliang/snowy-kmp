package util

import cn.hutool.crypto.SmUtil
import cn.hutool.crypto.asymmetric.KeyType
import cn.hutool.crypto.asymmetric.SM2

actual object SmUtils {
    private val sm2 =  SM2(null, PUBLIC_KEY)
    actual fun doSm2Encrypt(str: String) = sm2.encryptHex(str, KeyType.PublicKey).substring(2) // 去除密文开头的04
    actual fun doSm3Digest(str: String): String = SmUtil.sm3(str)
}