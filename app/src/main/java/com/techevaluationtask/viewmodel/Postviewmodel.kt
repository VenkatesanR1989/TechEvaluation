package com.techevaluationtask.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.ObservableField
import android.os.AsyncTask
import android.support.annotation.NonNull
import android.util.Log
import com.techevaluationtask.model.PostApiResponse
import com.techevaluationtask.model.repository.TaskRepository
import com.techevaluationtask.view.GetActivity
import com.techevaluationtask.view.PostActivity
import com.techevaluationtask.view.Utils.CommonMethods
import com.techevaluationtask.view.Utils.Singleton

class Postviewmodel(application: Application,activity: Activity) : AndroidViewModel(application) {

    val postValidation:PostValidation?= PostValidation()
    var mActivity:Activity?=null
    var postSaveObserve: LiveData<PostApiResponse>?=null
    var postResponse: ObservableField<PostApiResponse> = ObservableField()

    init {
        mActivity=activity
    }
    @Suppress("UNCHECKED_CAST")
    class Factory(@param:NonNull @field:NonNull private val application: Application, private val activity: Activity) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return Postviewmodel(application,activity) as T
        }
    }

    fun textChanged() {
        postValidation!!.clearErrors()
    }

    fun onLoginClicked(){
        if(postValidation!!.isValidData()){
            PostTask().execute()
        }
    }

    fun onGetDataClick(){
        mActivity!!.startActivity(Intent(mActivity, GetActivity::class.java))
    }

    fun getObservableSaveResult(): LiveData<PostApiResponse> {
        return postSaveObserve!!
    }

    fun setSaveData(postApiResponse: PostApiResponse){
        this.postResponse.set(postApiResponse)
        val mStatus = this.postResponse.get()!!.message
        CommonMethods.commonAlertDialog(mActivity!!,"",mStatus!!)
    }

    @SuppressLint("StaticFieldLeak")
    private inner class PostTask() : AsyncTask<Boolean, Boolean, Boolean>() {

        override fun doInBackground(vararg params: Boolean?): Boolean {
            postSaveObserve = TaskRepository.getInstance().postApiData(Singleton.getPostDataInstance(),mActivity!!)
            return true
        }

        override fun onPostExecute(result: Boolean) {
            (mActivity!! as PostActivity).postSaveObserve()
        }

        override fun onPreExecute() {
            CommonMethods.showProgressDialog(mActivity!!)
        }
    }
}