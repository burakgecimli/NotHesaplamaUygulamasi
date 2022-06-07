package com.bugcompany.nothesaplamauygulamasi.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bugcompany.nothesaplamauygulamasi.R
import com.bugcompany.nothesaplamauygulamasi.databinding.ActivityDetailBinding
import com.bugcompany.nothesaplamauygulamasi.model.NoteModel
import com.bugcompany.nothesaplamauygulamasi.network.ApiUtils
import com.bugcompany.nothesaplamauygulamasi.network.NotesDaoInterface
import com.bugcompany.nothesaplamauygulamasi.response.CrudResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var daoInterface: NotesDaoInterface
    private lateinit var noteModel: NoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toolbar3.title = "Not Detay"
            setSupportActionBar(toolbar3)

            daoInterface = ApiUtils.getNoteDaoInterface()

            noteModel = intent.getSerializableExtra("object") as NoteModel
            editTextLessonName.setText(noteModel.lessonName)
            editTextNote1.setText((noteModel.note1).toString())
            editTextNote2.setText((noteModel.note2).toString())

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_delete -> {
                Snackbar.make(binding.toolbar3, "Silmek isteğinize emin misiniz?", Snackbar.LENGTH_SHORT).setAction("Evet") {
                        daoInterface.deleteNote(noteModel.noteId).enqueue(object : Callback<CrudResponse> {
                                override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {
                                }

                                override fun onFailure(call: Call<CrudResponse>, t: Throwable) {
                                }
                            })
                        val intent = Intent(this@DetailActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }.show()
                return true
            }

            R.id.action_update -> {

                binding.apply {
                    val lessonName = editTextLessonName.text.toString().trim()
                    val note1 = editTextNote1.text.toString().trim()
                    val note2 = editTextNote2.text.toString().trim()

                    if (TextUtils.isEmpty(lessonName)) {
                        Snackbar.make(toolbar3, "Ders Adını Giriniz", Snackbar.LENGTH_SHORT).show()
                        return false
                    }

                    if (TextUtils.isEmpty(note1)) {
                        Snackbar.make(toolbar3, "1.Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                        return false
                    }

                    if (TextUtils.isEmpty(note2)) {
                        Snackbar.make(toolbar3, "2.Notu Giriniz", Snackbar.LENGTH_SHORT).show()
                        return false
                    }
                    daoInterface.updateNote(noteModel.noteId, lessonName, note1.toInt(), note2.toInt()).enqueue(object : Callback<CrudResponse> {
                        override fun onResponse(call: Call<CrudResponse>, response: Response<CrudResponse>) {
                        }
                        override fun onFailure(call: Call<CrudResponse>, t: Throwable) {
                        }

                    })

                }

                val intent = Intent(this@DetailActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> return false
        }


    }


}