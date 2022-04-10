package com.example.kiotapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.kiotapp.R
import com.example.kiotapp.data.ItemMenuAction
import com.example.kiotapp.data.model.Order
import com.example.kiotapp.data.model.OrderGeneral
import com.example.kiotapp.data.model.OrderInformation
import com.example.kiotapp.data.model.Product
import com.example.kiotapp.data.repository.IRepository
import com.example.kiotapp.data.repository.Repository
import com.example.kiotapp.utils.Const
import com.example.kiotapp.utils.IDialogKiotViewModel
import com.example.kiotapp.utils.combine
import com.example.kiotapp.utils.notifyObserver
import com.sangtb.androidlibrary.base.data.DataDialog

class HomeViewModel(application: Application) : AndroidViewModel(application),
    ItemMenuAction<Product>, IDialogKiotViewModel {
    private val repository: IRepository = Repository.getInstance()

    private var _typeTab = MutableLiveData(Const.TAB1)

    override val currentOrderSelected = MutableLiveData(Order())

    var idBill = MutableLiveData(-1)

    private val _listOrder = mutableMapOf<Int, Order>()
    private val listOrderLive = MutableLiveData(mutableMapOf<Int, Order>())

    private val _currentBill = OrderGeneral()
    val currentBillLive = MutableLiveData(_currentBill)

    val orderInformation = listOrderLive.map {
        it.values.map { order ->
            val currentProduct = findProductById(order.idProduct)
            OrderInformation(
                calo = currentProduct?.calo?: 0,
                name = currentProduct?.name?:"",
                idProduct = order.idProduct,
                count = order.count,
                price = currentProduct?.price ?: DEFAULT_FLOAT
            )
        }
    }

    val abc = MutableLiveData(DataDialog())

    val allProduct = repository.allProductLive.combine(_typeTab) { allProduct, typeTab ->
        if (typeTab == Const.TAB1) {
            allProduct
        } else {
            allProduct.filter {
                it.type == typeTab
            }
        }
    }

    private fun findProductById(productID: Int): Product? {
        return repository.allProduct.find { it.productId == productID }
    }

    val myOrderGeneral = listOrderLive.map { it ->
        val orderGeneral = OrderGeneral()
        orderGeneral.apply {
            it.values.forEach { order ->
                count += order.count
                total += findProductById(order.idProduct)?.price ?: DEFAULT_FLOAT
                tax += total * 0.15f
            }
        }
        application.getString(
            R.string.my_order_eat_in,
            orderGeneral.tax,
            orderGeneral.total,
            orderGeneral.count
        )
    }

    fun onCancelProduct() {
        _listOrder.remove(currentOrderSelected.value?.idProduct)
        listOrderLive.value = _listOrder
    }

    override val isEnableMinusButton = currentOrderSelected.map {
        it.count > 1
    }

    override val isEnableRemoveFromCart = MutableLiveData(false)

    fun onClickOrderInformation(item: OrderInformation){
        onClickProduct(findProductById(item.idProduct)?: Product())
    }

    fun onClickProduct(item: Product) {
        isEnableRemoveFromCart.value = _listOrder.containsKey(item.productId)
        currentOrderSelected.value = _listOrder[item.productId] ?: Order(
            idBill = checkBillCreated(),
            idProduct = item.productId,
            count = 1
        )
        currentOrderSelected.notifyObserver()
    }

    override fun onPlus() {
        currentOrderSelected.value?.apply {
            count = ++count
        }
        currentOrderSelected.notifyObserver()
    }

    override fun onMinus() {
        currentOrderSelected.value?.apply {
            count = --count
        }
        currentOrderSelected.notifyObserver()
    }

    override fun onAddToBuild() {
        currentOrderSelected.value?.let {
            if(it.count > 0){
                _listOrder[it.idProduct] = it
            }
        }
        listOrderLive.value = _listOrder
    }

    private fun checkBillCreated(): Int {
        var mIdBill = 0
        idBill.value?.let {
            if (it == -1) {
                mIdBill = (0..1000).random()
            }
        }
        idBill.value = mIdBill
        return mIdBill
    }

    override fun onClickItem(data: Product) {
        super.onClickItem(data)
        Log.d(TAG, "onClickItem: $data")
    }

    override fun onClickTab(idTab: Int) {
        Log.d(TAG, "onClickTab: $idTab")
        _typeTab.postValue(idTab)
    }

    companion object {
        private const val TAG = "HomeViewModel"
        private const val DEFAULT_FLOAT = 0f
    }
}