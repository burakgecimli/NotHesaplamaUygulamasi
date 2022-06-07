package com.bugcompany.nothesaplamauygulamasi.response

import com.bugcompany.nothesaplamauygulamasi.model.NoteModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NoteResponse(
    @SerializedName("notlar")
    @Expose
    var notes:List<NoteModel>,

    @SerializedName("success")
    @Expose
    var success:Int
) {
}