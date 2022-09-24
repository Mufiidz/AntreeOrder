package id.my.mufidz.antreeorder.data

import id.my.mufidz.antreeorder.models.ApiResponse
import id.my.mufidz.antreeorder.models.User
import id.my.mufidz.antreeorder.utils.Const
import id.my.mufidz.antreeorder.utils.Endpoint
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServices {

    @Headers("Content-Type: ${Const.applicationJson}")
    @POST(Endpoint.register)
    fun setRegister(@Body user: User): Call<ApiResponse<String>>

    @Headers("Content-Type: ${Const.applicationJson}")
    @POST(Endpoint.login)
    fun setLogin(@Body user: User): Call<ApiResponse<User>>
}