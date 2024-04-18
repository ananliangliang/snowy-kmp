import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val baseUrl: String = "https://snowyapi.xiaonuo.vip"
}

actual fun getPlatform(): Platform = AndroidPlatform()