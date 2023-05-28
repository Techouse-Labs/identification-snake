package com.steven.pengenalanjenisularajamas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

open class BaseViewModel: ViewModel() {
    val onLoading = MutableLiveData(false)
    val onError = MutableLiveData("")
    val isDone = MutableLiveData(false)

    val firestore = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()

    fun setLoading(value: Boolean) {
        onLoading.postValue(value)
    }
}