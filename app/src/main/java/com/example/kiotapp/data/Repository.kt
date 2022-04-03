package com.example.kiotapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kiotapp.utils.FireBaseConst.COLLECTION_PRODUCT
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repository private constructor() {
    private val fireStore = Firebase.firestore

    private val _allProduct = MutableLiveData(listOf<Product>())
    val allProduct: LiveData<List<Product>> = _allProduct

    fun getAllProduct() {
        val listProduct = mutableListOf<Product>()
        fireStore.collection(COLLECTION_PRODUCT).get().addOnSuccessListener {
            for (document in it.documents) {
                document.toObject(Product::class.java)?.let { product ->
                    listProduct.add(product)
                }
            }
            Log.d(TAG, "getAllProduct: $listProduct")
            _allProduct.postValue(listProduct)
        }.addOnFailureListener {
            Log.d(TAG, "getAllProduct -- ERROR: ")
        }
    }

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val TAG: String = Repository::class.java.simpleName

        @JvmStatic
        fun getInstance(): Repository {
            return Holder.INSTANCE
        }
    }
}