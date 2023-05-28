package com.steven.pengenalanjenisularajamas.admin.addSnake

import android.net.Uri
import com.esafirm.imagepicker.model.Image
import com.steven.pengenalanjenisularajamas.BaseViewModel

class AddSnakeViewModel: BaseViewModel() {

    fun saveData(name: String, detail: String, unique: String, image: Image) {
        firestore.collection("snakes").document(name).get()
            .addOnSuccessListener {
                setLoading(true)

                if(it.exists()) {
                    throw Exception("Nama ular sudah digunakan!")
                }

                storage.getReference(name).putFile(image.uri).addOnSuccessListener {task ->
                    task.storage.downloadUrl.addOnSuccessListener {uri ->
                        putToFirestore(name, detail, unique, uri)
                    }
                }

                setLoading(false)
            }.addOnFailureListener {
                setLoading(false)
                onError.postValue(it.message)
            }
    }

    private fun putToFirestore(name: String, detail: String, unique: String, url: Uri) {
        firestore.collection("snakes").document(name).set(mapOf(
            "name" to name,
            "detail" to detail,
            "unique" to unique,
            "imageUrl" to url
        )).addOnSuccessListener {
            isDone.postValue(true)
        }.addOnFailureListener {
            setLoading(false)
            onError.postValue(it.message)
        }
    }
}