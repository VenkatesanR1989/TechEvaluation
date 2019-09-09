package com.techevaluationtask.viewmodel

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.ObservableField
import android.support.annotation.NonNull
import android.util.Log
import com.techevaluationtask.model.db.CredentialDatabase
import com.techevaluationtask.model.db.dao.LoginDao
import com.techevaluationtask.model.db.entity.Credentials
import com.techevaluationtask.view.PostActivity


class Loginviewmodel(application: Application, activity: Activity) : AndroidViewModel(application) {

    val loginValidation:Loginvalidation?= Loginvalidation()
    private var loginDao: LoginDao
    private var credentials: LiveData<List<Credentials>>
    var credentialsObserve: ObservableField<List<Credentials>> = ObservableField()
    var mActivity:Activity?=null
    init {
        val database: CredentialDatabase = CredentialDatabase.getInstance(application.applicationContext)!!
        loginDao = database.loginDao()
        credentials = loginDao.getAllCredential()
        this.mActivity=activity
    }

    fun onLoginClicked(){
        if(loginValidation!!.isValidLogin(credentialsObserve)){
            mActivity!!.startActivity(Intent(mActivity, PostActivity::class.java))
        }
    }
    fun textChanged() {
        loginValidation!!.clearErrors()
    }

    fun getCredentials(): LiveData<List<Credentials>> {
        return credentials
    }

    fun setObserverCredential(credential: List<Credentials>) {
        this.credentialsObserve.set(credential)

    }

    @Suppress("UNCHECKED_CAST")
    class Factory(@param:NonNull @field:NonNull private val application: Application, private val activity: Activity) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return Loginviewmodel(application,activity) as T
        }
    }



}