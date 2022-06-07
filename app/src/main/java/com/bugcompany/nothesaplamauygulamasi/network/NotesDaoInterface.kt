package com.bugcompany.nothesaplamauygulamasi.network


import com.bugcompany.nothesaplamauygulamasi.response.CrudResponse
import com.bugcompany.nothesaplamauygulamasi.response.NoteResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesDaoInterface {


    @POST("notlar/delete_not.php")
    @FormUrlEncoded
    fun deleteNote(@Field("not_id") not_id: Int): Call<CrudResponse>

    @POST("notlar/insert_not.php")
    @FormUrlEncoded
    fun insertNote(
        @Field("ders_adi") lessonName: String,
        @Field("not1") note1: Int,
        @Field("not2") note2: Int
    ): Call<CrudResponse>

    @POST("notlar/update_not.php")
    @FormUrlEncoded
    fun updateNote(
        @Field("not_id") note_id: Int,
        @Field("ders_adi") lessonName: String,
        @Field("not1") note1: Int,
        @Field("not2") note2: Int
    ): Call<CrudResponse>

    @GET("notlar/tum_notlar.php")
    fun getAllNotes(): Call<NoteResponse>
}