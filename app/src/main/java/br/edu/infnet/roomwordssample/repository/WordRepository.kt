package br.edu.infnet.roomwordssample.repository

import android.app.Application

import androidx.lifecycle.LiveData
import br.edu.infnet.roomwordssample.*
import br.edu.infnet.roomwordssample.asyncTask.DeleteAllAsyncTask
import br.edu.infnet.roomwordssample.asyncTask.InsertAsyncTask
import br.edu.infnet.roomwordssample.dao.WordDao
import br.edu.infnet.roomwordssample.database.WordRoomDatabase
import br.edu.infnet.roomwordssample.model.Word

class WordRepository(application: Application) {

    var wordDao: WordDao

    var mAllWords: LiveData<List<Word>>

    init {
        val db: WordRoomDatabase = WordRoomDatabase.invoke(application)
        wordDao = db.wordDao()
        mAllWords = wordDao.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {
        InsertAsyncTask(wordDao, INSERT).execute(word)
    }

    fun deleteAll() {
        DeleteAllAsyncTask(wordDao).execute()
    }

    fun deleteWord(word: Word) {
        InsertAsyncTask(wordDao, DELETE).execute(word)
    }

    fun updateWord(word: Word) {
        InsertAsyncTask(wordDao, UPDATE).execute(word)
    }
}
