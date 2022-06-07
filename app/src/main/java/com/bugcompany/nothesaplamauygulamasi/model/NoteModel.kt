package com.bugcompany.nothesaplamauygulamasi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NoteModel(

    @SerializedName("not_id")
    @Expose
    var noteId: Int,

    @SerializedName("ders_adi")
    @Expose
    var lessonName: String,

    @SerializedName("not1")
    @Expose
    var note1: Int,

    @SerializedName("not2")
    @Expose
    var note2: Int,


    ) : Serializable {


}