package com.techevaluationtask.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.techevaluationtask.R
import com.techevaluationtask.model.GetResponse
import com.techevaluationtask.viewmodel.GetViewModel
import kotlinx.android.synthetic.main.activity_get.*


class GetActivity : AppCompatActivity() {

    var activityGetBinding: com.techevaluationtask.databinding.ActivityGetBinding?=null
    private var recyclerView: RecyclerView? = null
    private var mAdapter:GetAdapter? = null
    private var getviewmodel: GetViewModel? = null
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityGetBinding = DataBindingUtil.setContentView(this, R.layout.activity_get)
        setSupportActionBar(toolbar)
        updateRecyclerView()
        val factory = GetViewModel.Factory(application, this@GetActivity)
        getviewmodel = ViewModelProviders.of(this, factory).get(GetViewModel::class.java)
        observeSaveViewModel(getviewmodel!!)

    }

    private fun updateRecyclerView() {
        recyclerView = activityGetBinding!!.content.recyclerView
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.isNestedScrollingEnabled = false

    }



    fun observeSaveViewModel(viewModel: GetViewModel) {
        viewModel.getObservableSaveResult().observe(this,
                Observer<GetResponse> { getResponse ->
                    if (getResponse != null) {
                        viewModel.setSaveData(getResponse.payLoad)
                                mAdapter = GetAdapter(this, getResponse.payLoad!!)
                                 recyclerView!!.adapter = mAdapter
                    } })
    }

}
