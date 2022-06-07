package com.bugcompany.nothesaplamauygulamasi.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CrudResponse(

    @SerializedName("success")
    @Expose
    var succes:Int,

    @SerializedName("message")
    @Expose
    var message:String
) {

}