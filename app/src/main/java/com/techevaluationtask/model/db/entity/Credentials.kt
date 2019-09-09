package com.techevaluationtask.model.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "credential_table")
data class Credentials(

    var username: String,

    var Password: String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}