package di

import data.network.ApiService
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val koinModule = module {
    single { HttpClient { install(ContentNegotiation) { json() } } }
    singleOf(::ApiService)
}