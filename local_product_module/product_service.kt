//package com.example.Android.local_product_module
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Query
//
////const val ORIGINAL_URL = "http://10.0.2.2:8080/android/read.php?api_key=roth"
//const val PRODUCT_BASE_URL = "http://10.0.2.2"
//
//interface ProductService {
//    @GET("android/read.php")
//    suspend fun getProducts(
//        @Query("api_key") apiKey: String = "roth"
//    ): ProductModel
//
//    companion object {
//        var service: ProductService? = null
//        fun getInstance(): ProductService {
//            if (service == null) {
//                service = Retrofit.Builder()
//                    .baseUrl(PRODUCT_BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                    .create(ProductService::class.java)
//            }
//            return service!!
//        }
//    }
//}

//=================================31-08-2024=================================

package com.example.Android.local_product_module

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//const val ORIGINAL_URL = "http://10.0.2.2:8080/android/read.php?api_key=roth"
const val PRODUCT_BASE_URL = "http://10.0.2.2"

data class APIResponse(
    val success: Boolean,
    val message: String
)

interface ProductService{
    @GET("android/read.php")
    suspend fun getProducts(
        @Query("api_key") apiKey: String = "roth",
    ): ProductModel

    @FormUrlEncoded
    @POST("android/insert_post.php")
    suspend fun insertRecord(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("qty") qty: String,
        @Field("price") price: String,
        @Field("image") image: String,
        @Query("api_key") key: String = "roth"
    ): APIResponse


    @FormUrlEncoded
    @POST("android/update_post.php")
    suspend fun updateRecord(
        @Field("pid") pid: String,
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("qty") qty: String,
        @Field("price") price: String,
        @Field("image") image: String,
        @Query("api_key") key: String = "roth"
    ): APIResponse

    @FormUrlEncoded
    @POST("android/delete_post.php")
    suspend fun deleteRecord(
        @Field("pid") pid: String,
        @Query("api_key") key: String = "roth"
    ): APIResponse

    companion object {
        var service : ProductService? = null
        fun getInstance(): ProductService {
            if(service == null){
                val gson = GsonBuilder().setLenient().create()
                service = Retrofit.Builder()
                    .baseUrl(PRODUCT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ProductService::class.java)
            }
            return service!!
        }
    }
}