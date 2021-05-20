package br.edu.infnet.roomwordssample.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.edu.infnet.roomwordssample.model.Word

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(mWord: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * from word_table LIMIT 1")
    fun getAnyWord():Array<Word>

    @Delete
    fun deleteWord(word:Word)

    @Update
    fun updateWord(word: Word)
}