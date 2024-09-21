package com.example.Android.local_product_module

import com.google.gson.annotations.SerializedName

data class ProductModel (

    @SerializedName("success") var success : Boolean,
    @SerializedName("records") var records : List<Records>

)

data class Records (

    @SerializedName("pid") var pid : String,
    @SerializedName("title") var title : String,
    @SerializedName("body") var body : String,
    @SerializedName("price") var price : String,
    @SerializedName("qty") var qty : String,
    @SerializedName("image") var image : String

)
