package com.techevaluationtask.model.db


import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import com.techevaluationtask.model.db.dao.LoginDao
import com.techevaluationtask.model.db.entity.Credentials
@Database(entities = arrayOf(Credentials::class), version = 1)
abstract class CredentialDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao


    companion object {
        private var instance: CredentialDatabase? = null

        fun getInstance(context: Context): CredentialDatabase? {
            if (instance == null) {
                synchronized(CredentialDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CredentialDatabase::class.java, "notes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }

    }
    class PopulateDbAsyncTask(db: CredentialDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val loginDao = db?.loginDao()

        override fun doInBackground(vararg p0: Unit?) {
            loginDao?.insert(Credentials("test@gmail.com", "Test$123"))
        }
    }

}