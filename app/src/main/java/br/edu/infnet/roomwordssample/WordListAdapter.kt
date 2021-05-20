package br.edu.infnet.roomwordssample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.roomwordssample.model.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class WordListAdapter (val onClickListener: (Word) -> Unit) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mWords:List<Word>? = null

    class WordViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.textView
    }

    fun getWordAtPosition(position: Int): Word {
        return mWords!![position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val card = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent,  false)
        return WordViewHolder(card)
    }

    override fun getItemCount(): Int {
        return if (mWords != null){
            mWords!!.size
        } else{
            0
        }
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word: Word = mWords!![position]

        holder.textView.text = word.mWord

        holder.itemView.setOnClickListener{
            onClickListener(this.mWords!![position])
        }
    }

    fun setWords(mWords:List<Word>){
        this.mWords = mWords
        notifyDataSetChanged()
    }
}