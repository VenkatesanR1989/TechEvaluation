package com.techevaluationtask.viewmodel

import android.arch.lifecycle.LiveData
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableField
import com.techevaluationtask.model.Login
import android.util.Patterns
import android.text.TextUtils
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.techevaluationtask.model.db.entity.Credentials

class Loginvalidation: BaseObservable() {
    var loginFields:Login= Login()

    fun isValidEmail(target: String): Boolean {
        return if(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()){
            true
        }else {
            loginFields.usernameErrorEnable=true
            loginFields.usernameError="Invalid Username"
            notifyPropertyChanged(BR.userNameError)
            notifyPropertyChanged(BR.usernameErrorEnable)
            false
        }

    }

    fun isValidPassword(target: String): Boolean {
        return if( target.isEmpty() && target.length<9){
            loginFields.passwordErrorEnable=true
            loginFields.passwordError="Invalid Password"
            notifyPropertyChanged(BR.passwordError)
            notifyPropertyChanged(BR.passwordErrorEnable)
            false
        }else {
            true
        }
    }

    fun isValidLogin(credentials: ObservableField<List<Credentials>>):Boolean{
        return isValidEmail(loginFields.username!!)&&isValidPassword(loginFields.password!!)&&isValidCredentials(credentials)
    }

    fun isValidCredentials(credentials: ObservableField<List<Credentials>>):Boolean{

        return if(loginFields.username==(credentials.get()!![0].username)||
                loginFields.password==(credentials.get()!![0].Password)){
            Log.e("Login",""+credentials.get()!![0].username)
            clearErrors()
            true
        }else{
            loginFields.usernameErrorEnable=true
            loginFields.usernameError="Invalid Username"
            loginFields.passwordErrorEnable=true
            loginFields.passwordError="Invalid Password"
            updateChanges()
            false

        }
    }

    fun clearErrors(){
        loginFields.usernameErrorEnable=false
        loginFields.usernameError=""
        loginFields.passwordErrorEnable=false
        loginFields.passwordError=""
        updateChanges()
    }
    private fun updateChanges() {
        notifyPropertyChanged(BR.userNameError)
        notifyPropertyChanged(BR.usernameErrorEnable)
        notifyPropertyChanged(BR.passwordError)
        notifyPropertyChanged(BR.passwordErrorEnable)
    }

    @Bindable
    fun getUpdateLoginFields():Login{
        return loginFields
    }




    @Bindable
    fun getUserNameError():String{
        return loginFields.usernameError.toString()
    }

    @Bindable
    fun getUsernameErrorEnable(): Boolean? {
        return loginFields.usernameErrorEnable
    }


    @Bindable
    fun getPasswordError():String{
        return loginFields.passwordError.toString()
    }
    @Bindable
    fun getPasswordErrorEnable(): Boolean? {
        return loginFields.passwordErrorEnable
    }
}