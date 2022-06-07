package com.bugcompany.nothesaplamauygulamasi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugcompany.nothesaplamauygulamasi.databinding.CardDesignBinding
import com.bugcompany.nothesaplamauygulamasi.model.NoteModel
import com.bugcompany.nothesaplamauygulamasi.view.DetailActivity

class NoteAdapter(var mContext: Context, var noteList: List<NoteModel>) :
    RecyclerView.Adapter<NoteAdapter.CardDesign>() {

    inner class CardDesign(val binding: CardDesignBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesign {
        val binding = CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesign(binding)
    }

    override fun onBindViewHolder(holder: CardDesign, position: Int) {
        val note = noteList[position]
        holder.binding.textViewLesson.text = note.lessonName
        holder.binding.textViewNote1.text = note.note1.toString()
        holder.binding.textViewNote2.text = note.note2.toString()

        holder.binding.cardNote.setOnClickListener {
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra("object", note)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}