package com.bugcompany.nothesaplamauygulamasi.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.bugcompany.nothesaplamauygulamasi.databinding.ActivityNoteSaveBinding
import com.bugcompany.nothesaplamauygulamasi.network.ApiUtils
import com.bugcompany.nothesaplamauygulamasi.network.NotesDaoInterface
import com.bugcompany.nothesaplamauygulamasi.response.CrudResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoteSaveActvity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteSaveBinding
    private lateinit var notesDaoInterface: NotesDaoInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteSaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toolbar2.title = "Not Kayıt"
            setSupportActionBar(toolbar2)

            notesDaoInterface = ApiUtils.getNoteDaoInterface()

            buttonSave.setOnClickListener {

                val lessonName = editTextLessonName.text.toString().trim()
                val note1 = editTextNote1.text.toString().trim()
                val note2 = editTextNote2.text.toString().trim()

                if (TextUtils.isEmpty(lessonName)) {
                    Snackbar.make(toolbar2, "Ders Adını Giriniz", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (TextUtils.isEmpty(note1)) {
                    Snackbar.make(toolbar2, "1.Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (TextUtils.isEmpty(note2)) {
                    Snackbar.make(toolbar2, "2.Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                notesDaoInterface.insertNote(lessonName, note1.toInt(), note2.toInt()).enqueue(object : Callback<CrudResponse> {
                        override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {
                        }

                        override fun onFailure(call: Call<CrudResponse>, t: Throwable) {
                        }

                    })
                val intent = Intent(this@NoteSaveActvity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}