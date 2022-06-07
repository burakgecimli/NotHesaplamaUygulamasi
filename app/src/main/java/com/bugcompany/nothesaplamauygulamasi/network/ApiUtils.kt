package com.bugcompany.nothesaplamauygulamasi.network

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getNoteDaoInterface(): NotesDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(NotesDaoInterface::class.java)
        }
    }
}