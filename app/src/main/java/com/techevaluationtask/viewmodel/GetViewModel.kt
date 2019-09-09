package com.techevaluationtask.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import android.os.AsyncTask
import android.support.annotation.NonNull
import com.techevaluationtask.model.GetResponse
import com.techevaluationtask.model.repository.GetPayload
import com.techevaluationtask.model.repository.TaskRepository
import com.techevaluationtask.view.PostActivity
import com.techevaluationtask.view.Utils.CommonMethods

class GetViewModel(application: Application, activity: Activity) : AndroidViewModel(application) {

    var mActivity:Activity?=null
    var getSaveObserve: LiveData<GetResponse>?=null
    var getResponse: ObservableField<ArrayList<GetPayload>> = ObservableField()
    init {
        this.mActivity=activity
        getSaveObserve = TaskRepository.getInstance().getApiData(mActivity!!)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(@param:NonNull @field:NonNull private val application: Application, private val activity: Activity) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GetViewModel(application,activity) as T
        }
    }

    fun getObservableSaveResult(): LiveData<GetResponse> {
        return getSaveObserve!!
    }

    fun setSaveData(getResponse: ArrayList<GetPayload>?){
        this.getResponse.set(getResponse)

    }

    @SuppressLint("StaticFieldLeak")
    private inner class GetTask() : AsyncTask<Boolean, Boolean, Boolean>() {

        override fun doInBackground(vararg params: Boolean?): Boolean {
            getSaveObserve = TaskRepository.getInstance().getApiData(mActivity!!)
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