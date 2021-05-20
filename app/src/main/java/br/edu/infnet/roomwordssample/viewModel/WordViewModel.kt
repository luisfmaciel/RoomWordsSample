package br.edu.infnet.roomwordssample.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.infnet.roomwordssample.R
import br.edu.infnet.roomwordssample.model.Word
import br.edu.infnet.roomwordssample.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: WordRepository = WordRepository(application)

    private var mAllWords: LiveData<List<Word>>

    init {
        mAllWords = mRepository.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word){
        mRepository.insert(word)
    }

    fun deleteAll(){
        mRepository.deleteAll()
    }

    fun deleteWord(word: Word){
        mRepository.deleteWord(word)
    }

    fun updateWord(word: Word){
        mRepository.updateWord(word)
    }

}