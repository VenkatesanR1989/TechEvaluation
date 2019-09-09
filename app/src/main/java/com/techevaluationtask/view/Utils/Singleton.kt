package com.techevaluationtask.view.Utils

import android.annotation.SuppressLint
import com.techevaluationtask.model.PostData

object Singleton {

    @SuppressLint("StaticFieldLeak")
    var postData: PostData?=null
    fun getPostDataInstance(): PostData {
        if(postData==null){
            postData= PostData()
        }
        return postData!!
    }




}