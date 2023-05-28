package com.steven.pengenalanjenisularajamas.admin.snakes

import androidx.lifecycle.MutableLiveData
import com.steven.pengenalanjenisularajamas.BaseViewModel

class SnakesViewModel: BaseViewModel() {
    val snakes = MutableLiveData<List<Map<String, Any>>>()

    fun getSnakes() {
        onLoading.postValue(true)

        firestore.collection("snakes")
            .addSnapshotListener { value, error ->
            onLoading.postValue(false)

            if(error != null) {
                onError.postValue(error.message)
            } else {
                val datas = arrayListOf<Map<String, Any>>()
                value?.documents?.forEach {
                    val data = it.data
                    if (data != null) {
                        data["id"] = it.id
                        datas.add(data)
                    }
                }

                snakes.postValue(datas)
            }
        }
    }
}