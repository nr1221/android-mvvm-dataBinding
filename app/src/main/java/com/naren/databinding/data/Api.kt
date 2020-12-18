package com.naren.databinding.data

import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("DevTides/DogsApi/master/dogs.json")
    fun getDogList() : Single<List<DataModel>>

}