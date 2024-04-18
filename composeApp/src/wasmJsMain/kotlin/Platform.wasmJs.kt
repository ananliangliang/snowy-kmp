class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val baseUrl: String = "" // web使用相对路径 + 代理请求后端数据
}

actual fun getPlatform(): Platform = WasmPlatform()