import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val baseUrl: String = "https://snowyapi.xiaonuo.vip"
}

actual fun getPlatform(): Platform = IOSPlatform()