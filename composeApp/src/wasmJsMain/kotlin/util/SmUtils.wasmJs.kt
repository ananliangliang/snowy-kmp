package util

@JsModule("sm-crypto")
external val smCrypto: SmCrypto

external class SmCrypto {
    val sm2: Sm2
    fun sm3(message: String): String
}
external class Sm2 {
    fun doEncrypt(str: String, publicKey: String, cipherMode: Int): String
}
    
actual object SmUtils {
    actual fun doSm2Encrypt(str: String): String = smCrypto.sm2.doEncrypt(str, PUBLIC_KEY, 1)

    actual fun doSm3Digest(str: String): String = smCrypto.sm3(str)
}