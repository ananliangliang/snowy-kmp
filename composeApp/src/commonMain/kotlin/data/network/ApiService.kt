package data.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import util.SmUtils

class ApiService(private val client: HttpClient) {
    
    suspend fun doLogin(account: String, password: String): String {
        return client
            .post("https://snowyapi.xiaonuo.vip/auth/b/doLogin") {
                contentType(ContentType.Application.Json)
                setBody(LoginReq(account, SmUtils.doSm2Encrypt(password)))
            }.body<SnowyRes<String>>().data ?: throw RuntimeException("123")
    }
}