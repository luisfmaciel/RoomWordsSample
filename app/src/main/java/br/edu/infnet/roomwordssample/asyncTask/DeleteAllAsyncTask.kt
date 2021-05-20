package br.edu.infnet.roomwordssample.asyncTask

import android.os.AsyncTask
import br.edu.infnet.roomwordssample.dao.WordDao

class DeleteAllAsyncTask (
        private val mAsyncTaskDao: WordDao
) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {

        mAsyncTaskDao.deleteAll()

        return null
    }
}