package data.network

import kotlinx.serialization.Serializable

@Serializable
data class LoginReq(
    val account: String,
    val password: String,
)