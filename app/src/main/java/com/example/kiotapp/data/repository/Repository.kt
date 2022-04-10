package com.example.kiotapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kiotapp.data.model.Bill
import com.example.kiotapp.data.model.Order
import com.example.kiotapp.data.model.Product
import com.example.kiotapp.utils.FireBaseConst
import com.example.kiotapp.utils.FireBaseConst.COLLECTION_BILL
import com.example.kiotapp.utils.FireBaseConst.COLLECTION_ORDER
import com.example.kiotapp.utils.FireBaseConst.COLLECTION_PRODUCT
import com.example.kiotapp.utils.getTimeZoneToFloat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class Repository private constructor() : IRepository {
    private val fireStore = Firebase.firestore

    private val fireStorage = FirebaseStorage.getInstance(FireBaseConst.FIRE_STORAGE_BUCKETS)
    private val storageRef = fireStorage.reference

    override var allProduct = listOf<Product>()
            private val _allProductLive = MutableLiveData(allProduct)
    override val allProductLive: LiveData<List<Product>> = _allProductLive

    private val coroutine = CoroutineScope(Dispatchers.IO)

    init {
        getAllProduct()
        getAllUrlImage()
    }

    override fun getAllProduct() {
        val listProduct = mutableListOf<Product>()
        fireStore.collection(COLLECTION_PRODUCT).get().addOnSuccessListener {
            for (document in it.documents) {
                document.toObject(Product::class.java)?.let { product ->
                    listProduct.add(product)
                }
            }
            Log.d(TAG, "getAllProduct: $listProduct")
            allProduct = listProduct
            _allProductLive.postValue(allProduct)
        }.addOnFailureListener {
            Log.d(TAG, "getAllProduct -- ERROR: ")
        }
    }

    override fun saveOrderToRemote(userInformation: List<Order>) {
        userInformation.forEach {
            val map = hashMapOf(
                "time" to getTimeZoneToFloat()
            )
            fireStore.collection(COLLECTION_ORDER).add(it).addOnSuccessListener {ref ->
                ref.update(map as Map<String, Any>)
            }
        }
    }

    override fun saveBillToRemote(bill: Bill){
        fireStore.collection(COLLECTION_BILL).document(bill.idBill.toString()).set(bill)
    }

    private val mapImage = mutableMapOf<Int, String>()
    private val _mapLiveImage  = MutableLiveData(mapImage.toMap())
    override val mapLiveImage: LiveData<Map<Int, String>>  = _mapLiveImage

    override fun getAllUrlImage() {
        storageRef.child("ProductImage").listAll().addOnSuccessListener {

            it.items.map { item ->
            coroutine.launch {
                Log.d(TAG, "getAllUrlImage: Nam - chay")
                val id = item.name.dropLast(4).toIntOrNull() ?: -1 // 0
                item.downloadUrl.addOnSuccessListener { uri ->
                    mapImage[id] = uri.toString()
                    Log.d(TAG, "getAllUrlImage: Nam - ra")
                    _mapLiveImage.postValue(mapImage)
                }
            }

        }
        }.addOnFailureListener {
            Log.d(TAG, "getAllUrlImage: failure ${it.message}")
        }
    }

    override suspend fun getUrlImage(imageName: String): Flow<String> {
        return flow {
            Log.d(TAG, "getUrlImage: raa")
            storageRef.child(imageName).downloadUrl.addOnSuccessListener {
                Log.d(TAG, "getUrlImage: sad")
                coroutine.launch {
                    emit(it.toString())
                    Log.d(TAG, "getUrlImage: phat $it")
                }
            }.addOnFailureListener {
                Log.d(TAG, "getUrlImage: error = $it")
            }
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