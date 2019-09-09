package com.techevaluationtask.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import android.util.Patterns
import com.android.databinding.library.baseAdapters.BR
import com.techevaluationtask.model.PostData
import com.techevaluationtask.model.PostErrorFields
import com.techevaluationtask.view.Utils.Singleton

class PostValidation : BaseObservable(){

    var postFields:PostData= Singleton.getPostDataInstance()
    var postErrorFields:PostErrorFields= PostErrorFields()


    fun isValidData():Boolean{
        return isValidEid()&&isValidname()&&isValididbharahno()&&isValidEmail()&&isValidUnifiedno()&&isValidMobileno()
    }

    fun clearErrors(){
        postErrorFields.eidErrorEnable=false
        postErrorFields.eidError=""
        postErrorFields.nameErrorEnable=false
        postErrorFields.nameError=""
        updateChanges()
    }
    fun Int.length() = when(this) {
        0 -> 1
        else -> Math.log10(Math.abs(toDouble())).toInt() + 1
    }
    fun isValidEid():Boolean{
        return if(postFields.eid==0|| postFields.eid!!.length()<6){
            postErrorFields.eidErrorEnable=true
            postErrorFields.eidError="Invalid Eid"
            updateChanges()
            false
        }else{
            postErrorFields.eidErrorEnable=false
            postErrorFields.eidError=""
            updateChanges()
            true
        }
    }

    fun isValidname():Boolean{
        return if(postFields.name!!.isEmpty()){
            postErrorFields.nameErrorEnable=true
            postErrorFields.nameError="Invalid name"
            updateChanges()
            false
        }else{
            postErrorFields.nameErrorEnable=false
            postErrorFields.nameError=""
            updateChanges()
            true
        }
    }

    fun isValididbharahno():Boolean{
        return if(postFields.idbharahno==0){
            postErrorFields.idbarahnoErrorEnable=true
            postErrorFields.idbarahnoError="Invalid Id bharah Number"
            updateChanges()
            false
        }else{
            postErrorFields.idbarahnoErrorEnable=false
            postErrorFields.idbarahnoError=""
            updateChanges()
            true
        }
    }

    fun isValidEmail(): Boolean {
        return if(!TextUtils.isEmpty(postFields.emailaddress) && Patterns.EMAIL_ADDRESS.matcher(postFields.emailaddress).matches()){
            postErrorFields.emailErrorEnable=false
            postErrorFields.emailError=""
            updateChanges()
            true
        }else {
            postErrorFields.emailErrorEnable=true
            postErrorFields.emailError="Invalid email"
            notifyPropertyChanged(BR.userNameError)
            notifyPropertyChanged(BR.usernameErrorEnable)
            updateChanges()
            false
        }

    }

    fun isValidUnifiedno():Boolean{
        return if(postFields.unifiedNumber==0){
            postErrorFields.unifiednoErrorEnable=true
            postErrorFields.unifiednoError="Invalid Unified Number"
            updateChanges()
            false
        }else{
            postErrorFields.unifiednoErrorEnable=false
            postErrorFields.unifiednoError=""
            updateChanges()
            true
        }
    }

    fun isValidMobileno():Boolean{
        return if(postFields.mobilenumber==0){
            postErrorFields.mobilenoErrorEnable=true
            postErrorFields.mobilenoError="Invalid Mobile Number"
            updateChanges()
            false
        }else{
            postErrorFields.mobilenoErrorEnable=false
            postErrorFields.mobilenoError=""
            updateChanges()
            true
        }
    }

    private fun updateChanges() {
        notifyPropertyChanged(BR.eidError)
        notifyPropertyChanged(BR.eidErrorEnable)
        notifyPropertyChanged(BR.nameError)
        notifyPropertyChanged(BR.nameErrorEnable)
        notifyPropertyChanged(BR.idbharahNoError)
        notifyPropertyChanged(BR.idbharahNoErrorEnable)
        notifyPropertyChanged(BR.emailError)
        notifyPropertyChanged(BR.emailErrorEnable)
        notifyPropertyChanged(BR.unifiedNoError)
        notifyPropertyChanged(BR.unifiedNoErrorEnable)
        notifyPropertyChanged(BR.mobileNoError)
        notifyPropertyChanged(BR.mobileNoErrorEnable)
    }


    @Bindable
    fun getEidError():String{
        return postErrorFields.eidError.toString()
    }

    @Bindable
    fun getEidErrorEnable(): Boolean? {
        return postErrorFields.eidErrorEnable
    }

    @Bindable
    fun getNameError():String{
        return postErrorFields.nameError.toString()
    }

    @Bindable
    fun getNameErrorEnable(): Boolean? {
        return postErrorFields.nameErrorEnable
    }

    @Bindable
    fun getIdbharahNoError():String{
        return postErrorFields.idbarahnoError.toString()
    }

    @Bindable
    fun getIdbharahNoErrorEnable(): Boolean? {
        return postErrorFields.idbarahnoErrorEnable
    }

    @Bindable
    fun getUnifiedNoError():String{
        return postErrorFields.unifiednoError.toString()
    }

    @Bindable
    fun getUnifiedNoErrorEnable(): Boolean? {
        return postErrorFields.unifiednoErrorEnable
    }

    @Bindable
    fun getMobileNoError():String{
        return postErrorFields.mobilenoError.toString()
    }

    @Bindable
    fun getMobileNoErrorEnable(): Boolean? {
        return postErrorFields.mobilenoErrorEnable
    }

    @Bindable
    fun getEmailError():String{
        return postErrorFields.emailError.toString()
    }

    @Bindable
    fun getEmailErrorEnable(): Boolean? {
        return postErrorFields.emailErrorEnable
    }


}