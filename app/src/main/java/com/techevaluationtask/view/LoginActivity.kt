package com.techevaluationtask.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.techevaluationtask.R
import com.techevaluationtask.databinding.ActivityLoginBinding
import android.arch.lifecycle.ViewModelProviders
import com.techevaluationtask.model.db.entity.Credentials
import com.techevaluationtask.viewmodel.Loginviewmodel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var activityLoginBinding:ActivityLoginBinding?=null
    private var loginViewModel: Loginviewmodel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setSupportActionBar(toolbar)
        val factory = Loginviewmodel.Factory(application, this@LoginActivity)
        loginViewModel = ViewModelProviders.of(this, factory).get(Loginviewmodel::class.java)
        activityLoginBinding!!.loginViewModel = loginViewModel
        observeLoginViewModel(loginViewModel!!)
    }

    private fun observeLoginViewModel(viewModel: Loginviewmodel) {
        loginViewModel!!.getCredentials().observe(this,
                Observer<List<Credentials>> { t -> loginViewModel!!.setObserverCredential(t!!) })
    }


}
