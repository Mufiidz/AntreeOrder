package id.my.mufidz.antreeorder.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@Parcelize
data class User(
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    val id: String = "",
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    val name: String = "",
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    val username: String = "",
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    val password: String = "",
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    val token: String = "",
) : Parcelable