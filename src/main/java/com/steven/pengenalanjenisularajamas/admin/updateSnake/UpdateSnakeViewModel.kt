package com.steven.pengenalanjenisularajamas.admin.updateSnake

import android.net.Uri
import com.esafirm.imagepicker.model.Image
import com.steven.pengenalanjenisularajamas.BaseViewModel

class UpdateSnakeViewModel: BaseViewModel() {

    fun saveData(id: String, name: String, detail: String, unique: String, image: Image?) {
        if(image != null) {
            storage.getReference(id).putFile(image.uri).addOnSuccessListener {task ->
                task.storage.downloadUrl.addOnSuccessListener {uri ->
                    putToFirestore(id, name, detail, unique, uri)
                }
            }
        } else {
            putToFirestore(id, name, detail, unique, null)
        }
    }

    private fun putToFirestore(id: String, name: String, detail: String, unique: String, url: Uri?) {
        val request = mutableMapOf<String, Any>(
            "name" to name,
            "detail" to detail,
            "unique" to unique,
        )

        if(url != null) {
            request["imageUrl"] = url
        }

        firestore.collection("snakes").document(id).update(request)
        .addOnSuccessListener {
            isDone.postValue(true)
        }.addOnFailureListener {
            setLoading(false)
            onError.postValue(it.message)
        }
    }

    fun delete(id: String) {
        firestore.collection("snakes").document(id).delete().addOnSuccessListener {
            isDone.postValue(true)
        }.addOnFailureListener {
            setLoading(false)
            onError.postValue(it.message)
        }
    }
}