package util

import cocoapods.GMObjC.GMSm2Utils
import cocoapods.GMObjC.GMSm3Utils
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual object SmUtils {

    actual fun doSm2Encrypt(str: String): String {
        val ansiEncrypted = GMSm2Utils.encryptText(str, PUBLIC_KEY) ?: ""
        return GMSm2Utils.asn1DecodeToC1C3C2(ansiEncrypted)?.lowercase() ?: throw RuntimeException("sm2 decode error")
    }

    actual fun doSm3Digest(str: String): String {
        return GMSm3Utils.hashWithString(str)?.lowercase() ?: throw RuntimeException("sm3 encrypt error")
    }
}