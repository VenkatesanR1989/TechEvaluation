package com.techevaluationtask.view.Utils

import android.app.*
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object CommonMethods {
    private var mProgressDialog: ProgressDialog? = null
    var builder: AlertDialog.Builder? = null

    fun commonAlertDialog(context:Activity, title:String, msg: String) {
        builder = AlertDialog.Builder(context)
        builder!!.setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Okay") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder!!.create()
        alert.setTitle(title)
        alert.show()
    }


    fun isConnected(activity: Activity): Boolean {
        var info: NetworkInfo? = null
        try {
            val cm = (activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            info = cm.activeNetworkInfo
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return info != null && info.isConnected
    }


    fun showProgressDialog(mContext: Context) {
        try {
            mProgressDialog = ProgressDialog(mContext)
            mProgressDialog!!.setMessage("Please wait...")
            mProgressDialog!!.setCancelable(false)
            mProgressDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun closeProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog!!.isShowing) {
                    mProgressDialog!!.dismiss()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }













}