package util

/** sm2公钥 */
val SmUtils.PUBLIC_KEY: String get() = "04298364ec840088475eae92a591e01284d1abefcda348b47eb324bb521bb03b0b2a5bc393f6b71dabb8f15c99a0050818b56b23f31743b93df9cf8948f15ddb54"

expect object SmUtils {
    /** sm2公钥加密，结果为小写Hex字符串 cipher mode: C1C3C2 */
    fun doSm2Encrypt(str: String): String
    /** sm3摘要，结果为小写Hex字符串 */
    fun doSm3Digest(str: String): String
}