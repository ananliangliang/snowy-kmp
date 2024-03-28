package data.network

import kotlinx.serialization.Serializable

@Serializable
data class SnowyRes<T>(
    val code: Int,
    val data: T?,
    val msg: String,
)