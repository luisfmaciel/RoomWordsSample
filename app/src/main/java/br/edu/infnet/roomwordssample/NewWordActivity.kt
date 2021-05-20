package br.edu.infnet.roomwordssample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        val word = intent.getStringExtra(EXTRA_WORD)
        if(word != null) {
            edit_word.setText(word)
            button_save.text = getString(R.string.update)
            button_delete_word.visibility = View.VISIBLE
        } else {
            button_delete_word.visibility = View.GONE
        }

        actionSaveWord()
        actionDeleteWord()
    }

    private fun actionSaveWord(){
        val id = intent.getIntExtra(EXTRA_ID, 0)
        button_save.setOnClickListener {
            val intent = Intent()

            val wordStr = edit_word.text.toString()
            if(wordStr.isEmpty()){
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                intent.putExtra(EXTRA_ID, id)
                intent.putExtra(EXTRA_WORD, wordStr)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }

    private fun actionDeleteWord() {
        val word = intent.getStringExtra(EXTRA_WORD)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        button_delete_word.setOnClickListener {
            intent.putExtra(EXTRA_ID, id)
            intent.putExtra(EXTRA_WORD, word)
            setResult(DELETE, intent)
            finish()
        }
    }
}