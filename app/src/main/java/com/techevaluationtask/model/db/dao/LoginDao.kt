package com.techevaluationtask.model.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.techevaluationtask.model.db.entity.Credentials

@Dao
interface LoginDao {

    @Insert
    fun insert(note: Credentials)

    @Query("DELETE FROM credential_table")
    fun deleteAllCredential()

    @Query("SELECT * FROM credential_table ")
    fun getAllCredential(): LiveData<List<Credentials>>

}