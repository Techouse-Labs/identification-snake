package com.steven.pengenalanjenisularajamas.user.dashboard

import androidx.lifecycle.MutableLiveData
import com.steven.pengenalanjenisularajamas.BaseViewModel

class DashboardViewModel: BaseViewModel() {
    val types = MutableLiveData<List<Map<String, Any>>>()

    fun getSnakes() {
        onLoading.postValue(true)

        firestore.collection("snakes").addSnapshotListener { value, error ->
            onLoading.postValue(false)

            if(error != null) {
                onError.postValue(error.message)
            } else {
                val datas = arrayListOf<Map<String, Any>>()
                value?.documents?.forEach {
                    val data = it.data
                    if (data != null) {
                        datas.add(data)
                    }
                }

                types.postValue(datas)
            }
        }
    }

    fun getFacts() {
        onLoading.postValue(true)

        firestore.collection("snakes").whereNotEqualTo("unique", "").addSnapshotListener { value, error ->
            onLoading.postValue(false)

            if(error != null) {
                onError.postValue(error.message)
            } else {
                val datas = arrayListOf<Map<String, Any>>()
                value?.documents?.forEach {
                    val data = it.data
                    if (data != null) {
                        datas.add(data)
                    }
                }

                types.postValue(datas)
            }
        }
    }
}