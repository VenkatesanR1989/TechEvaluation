package com.techevaluationtask.model.repository

import android.app.Activity
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.techevaluationtask.model.GetResponse
import com.techevaluationtask.model.PostApiResponse
import com.techevaluationtask.model.PostData
import com.techevaluationtask.view.Utils.CommonMethods
import com.techevaluationtask.view.Utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class TaskRepository {

    private val mBaseUrl = "https://api.qa.mrhe.gov.ae/mrhecloud/v1.4/api/"
    private var mAPIService: APIService? = null
    init{
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS)
                .hostnameVerifier { _, _ -> true }
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mAPIService=retrofit.create(APIService::class.java)
    }
    companion object {
        private var taskRepository: TaskRepository? = null
        @Synchronized
        fun getInstance(): TaskRepository {
            if (taskRepository == null) {
                if (taskRepository == null) {
                    taskRepository = TaskRepository()
                }
            }
            return taskRepository!!
        }
    }




    fun postApiData(postReq: PostData, activity: Activity): LiveData<PostApiResponse> {
        val data = MutableLiveData<PostApiResponse>()
        if (CommonMethods.isConnected(activity))
        {
            val eid: Int= postReq.eid!!
            val name: String=postReq.name!!.toString()
            val idbarahno: Int=postReq.idbharahno!!
            val emailAddress: String=postReq.emailaddress!!.toString()
            val unifiedNumber: Int=postReq.unifiedNumber!!
            val mobileNumber: Int=postReq.mobilenumber!!
            mAPIService!!.postDataApi1(eid,name,idbarahno,emailAddress,unifiedNumber,mobileNumber).enqueue(object : Callback<PostApiResponse> {
                override fun onResponse(call: Call<PostApiResponse>, response: Response<PostApiResponse>) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            data.value = response.body()
                            CommonMethods.closeProgressDialog()
                        } else {
                            CommonMethods.commonAlertDialog(activity, "", Constants.TECHNICAL_ERROR)
                            CommonMethods.closeProgressDialog()
                        }
                    } else {
                        CommonMethods.closeProgressDialog()
                        when (response.code()) {
                            404 -> Toast.makeText(activity, "not found", Toast.LENGTH_SHORT).show()
                            500 -> Toast.makeText(activity, "server broken", Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(activity, "unknown error", Toast.LENGTH_SHORT).show()
                        }
                    }


                }
                override fun onFailure(call: Call<PostApiResponse>, t: Throwable) {
                    data.value = null
                    CommonMethods.closeProgressDialog()
                    CommonMethods.commonAlertDialog(activity, "", Constants.TECHNICAL_ERROR)
                }
            })
        }else{
            CommonMethods.closeProgressDialog()
            CommonMethods.commonAlertDialog(activity,"", Constants.NETWORK_ERROR)
        }


        return data
    }


    fun getApiData(activity: Activity): LiveData<GetResponse> {
        val data = MutableLiveData<GetResponse>()
        if (CommonMethods.isConnected(activity))
        {
            CommonMethods.showProgressDialog(activity)
            mAPIService!!.getDataApi().enqueue(object : Callback<GetResponse> {
                override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            data.value = response.body()
                            CommonMethods.closeProgressDialog()
                        } else {
                            CommonMethods.commonAlertDialog(activity, "", Constants.TECHNICAL_ERROR)
                            CommonMethods.closeProgressDialog()
                        }
                    } else {
                        CommonMethods.closeProgressDialog()
                        when (response.code()) {
                            404 -> Toast.makeText(activity, "not found", Toast.LENGTH_SHORT).show()
                            500 -> Toast.makeText(activity, "server broken", Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(activity, "unknown error"+response.code(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                    data.value = null
                    CommonMethods.closeProgressDialog()
                    CommonMethods.commonAlertDialog(activity, "", Constants.TECHNICAL_ERROR)
                }
            })
        }else{
            CommonMethods.closeProgressDialog()
            CommonMethods.commonAlertDialog(activity,"", Constants.NETWORK_ERROR)
        }


        return data
    }


}