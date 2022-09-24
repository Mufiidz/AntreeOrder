package id.my.mufidz.antreeorder.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
object Utils {
    val json: Json by lazy {
        Json {
            encodeDefaults = true
            prettyPrint = true
            isLenient = true
            explicitNulls = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
}