package com.techevaluationtask.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.techevaluationtask.R
import com.techevaluationtask.databinding.ActivityPostBinding
import com.techevaluationtask.model.PostApiResponse
import com.techevaluationtask.viewmodel.Postviewmodel

import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {
    var activityPostBinding: ActivityPostBinding?=null
    private var postviewmodel: Postviewmodel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPostBinding = DataBindingUtil.setContentView(this, R.layout.activity_post)
        setSupportActionBar(toolbar)
        val factory = Postviewmodel.Factory(application, this@PostActivity)
        postviewmodel = ViewModelProviders.of(this, factory).get(Postviewmodel::class.java)
        activityPostBinding!!.postViewModel = postviewmodel

    }
    fun postSaveObserve(){
        observeSaveViewModel(postviewmodel!!)
    }

    fun observeSaveViewModel(viewModel: Postviewmodel) {
        viewModel.getObservableSaveResult().observe(this,
                Observer<PostApiResponse> { postResponse ->
                    if (postResponse != null) {
                        viewModel.setSaveData(postResponse)
                    }
                })
    }


}
