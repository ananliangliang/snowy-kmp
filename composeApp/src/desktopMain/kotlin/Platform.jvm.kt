class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val baseUrl: String = "https://snowyapi.xiaonuo.vip"
}

actual fun getPlatform(): Platform = JVMPlatform()