package br.edu.infnet.roomwordssample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.roomwordssample.model.Word
import br.edu.infnet.roomwordssample.viewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: WordListAdapter? = null
    private var mWordViewModel: WordViewModel? = null
    private val ADD_REQUEST_CODE = 71

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        adapter = WordListAdapter() {
                partItem: Word -> partItemClicked(partItem)
        }

        setSupportActionBar(toolbar)
        setRecyclerView()
        setUpListeners()
        subscribe()
    }

    private fun setRecyclerView(){
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    private fun setUpListeners(){
        fab_add.setOnClickListener {
            startActivityForResult(Intent(this, NewWordActivity::class.java), ADD_REQUEST_CODE)
        }

        fab_deleteAll.setOnClickListener {
            mWordViewModel!!.deleteAll()
        }
    }

    private fun subscribe() {
        mWordViewModel!!.getAllWords().observe(this, Observer {
            if (it.isNullOrEmpty()) {
                tv_lista_vazia.visibility = View.VISIBLE
            } else {
                tv_lista_vazia.visibility = View.GONE
            }
            adapter!!.setWords(it)
        })
    }

    private fun partItemClicked(word : Word) {
        val intent = Intent(this, NewWordActivity::class.java)
        intent.putExtra(EXTRA_ID, word.id)
        intent.putExtra(EXTRA_WORD, word.mWord)

        startActivityForResult(intent, ADD_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val id: Int = data!!.getIntExtra(EXTRA_ID, 0)
        val wordStr: String = data.getStringExtra(EXTRA_WORD).toString()
        val word = Word(id, wordStr)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == ADD_REQUEST_CODE) {
                if(id == 0) {
                    mWordViewModel!!.insert(word)
                } else {
                    mWordViewModel!!.updateWord(word)
                }
            }
        } else if (resultCode == DELETE) {
            mWordViewModel!!.deleteWord(word)
        }
    }
}