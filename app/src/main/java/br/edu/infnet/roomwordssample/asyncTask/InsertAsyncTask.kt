package br.edu.infnet.roomwordssample.asyncTask

import android.os.AsyncTask
import br.edu.infnet.roomwordssample.DELETE
import br.edu.infnet.roomwordssample.INSERT
import br.edu.infnet.roomwordssample.UPDATE
import br.edu.infnet.roomwordssample.dao.WordDao
import br.edu.infnet.roomwordssample.model.Word

class InsertAsyncTask (
    private val mAsyncTaskDao: WordDao,
    private val DBOperation: Int
) : AsyncTask<Word, Void, Void>()
{
    override fun doInBackground(vararg params: Word): Void? {
        when (DBOperation) {
            INSERT -> mAsyncTaskDao.insert(params[0])
            UPDATE -> mAsyncTaskDao.updateWord(params[0])
            DELETE -> mAsyncTaskDao.deleteWord(params[0])
        }
        return null
    }
}