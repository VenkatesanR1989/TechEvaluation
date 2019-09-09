package com.techevaluationtask.model.repository


import com.techevaluationtask.model.GetResponse
import com.techevaluationtask.model.PostApiResponse
import com.techevaluationtask.model.PostData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded



interface APIService {
    @FormUrlEncoded
    @Headers( "consumer-key: mobile_dev","consumer-secret: 20891a1b4504ddc33d42501f9c8d2215fbe85008" )
    @POST("/iskan/v1/certificates/towhomitmayconcern")
    fun postDataApi(@Body postData: PostData): Call<PostApiResponse>

    @FormUrlEncoded
    @Headers( "consumer-key: mobile_dev","consumer-secret: 20891a1b4504ddc33d42501f9c8d2215fbe85008","Content-Type:application/x-www-form-urlencoded")
    @POST("iskan/v1/certificates/towhomitmayconcern") // specify the sub url for our base url
    fun postDataApi1(@Field("eid") eid: Int,
              @Field("name") name: String,
              @Field("idbarahno") idbarahno: Int,
              @Field("emailaddress") emailAddress: String,
              @Field("unifiednumber") unifiedNumber: Int,
              @Field("mobileno") mobileNumber: Int): Call<PostApiResponse>


    @Headers( "consumer-key: mobile_dev","consumer-secret: 20891a1b4504ddc33d42501f9c8d2215fbe85008")
    @GET("public/v1/news?local=en") // specify the sub url for our base url
    fun getDataApi(): Call<GetResponse>


 //   Response{protocol=http/1.1, code=404, message=Not Found, url=https://api.qa.mrhe.gov.ae/iskan/v1/certificates/towhomitmayconcern}
}

