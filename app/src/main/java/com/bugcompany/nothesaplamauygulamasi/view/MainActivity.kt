package com.bugcompany.nothesaplamauygulamasi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bugcompany.nothesaplamauygulamasi.adapter.NoteAdapter
import com.bugcompany.nothesaplamauygulamasi.databinding.ActivityMainBinding
import com.bugcompany.nothesaplamauygulamasi.model.NoteModel
import com.bugcompany.nothesaplamauygulamasi.network.ApiUtils
import com.bugcompany.nothesaplamauygulamasi.network.NotesDaoInterface
import com.bugcompany.nothesaplamauygulamasi.response.NoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var notesDaoInterface: NotesDaoInterface
    private lateinit var noteList: ArrayList<NoteModel>
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            toolbar.title = "Not Hesaplama"
            setSupportActionBar(toolbar)

            notesDaoInterface = ApiUtils.getNoteDaoInterface()

            getAllNotes()

            floatingActionButton.setOnClickListener {
                val intent = Intent(this@MainActivity, NoteSaveActvity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun getAllNotes() {
        notesDaoInterface.getAllNotes().enqueue(object : Callback<NoteResponse> {
            override fun onResponse(call: Call<NoteResponse>, response: Response<NoteResponse>) {

                val tempList = response.body()?.notes
                tempList?.let {
                    adapter = NoteAdapter(this@MainActivity, tempList)
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()

                    binding.recyclerView.setHasFixedSize(true)
                    var toplam: Double = 0.0
                    for (n in tempList) {
                        toplam = toplam + (n.note1.toDouble() + n.note2.toDouble()) / 2
                    }

                    val result: Double = String.format("%.2f", toplam / tempList.size).toDouble()
                    binding.toolbar.subtitle = ("Ortalama:$result")
                }
            }

            override fun onFailure(call: Call<NoteResponse>, t: Throwable) {
            }
        })
    }

}