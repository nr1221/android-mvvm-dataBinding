package com.naren.databinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naren.databinding.data.ApiService
import com.naren.databinding.data.DataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class MainViewModel : ViewModel() {

    private var apiService = ApiService()
    private val disposable = CompositeDisposable()
    var dogList = MutableLiveData<List<DataModel>>()
    var isLoading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()


    fun fetchData() {
        isLoading.value = true
        disposable.add(
            apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DataModel>>() {
                    override fun onSuccess(value: List<DataModel>?) {
                        dogList.value = value
                        isLoading.value = false
                        error.value = false
                    }

                    override fun onError(e: Throwable?) {
                        error.value = true
                        isLoading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}