package com.example.midterm.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.midterm.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class ProductViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    var productList = mutableStateListOf<Product>()
    var notice = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun clearNotice() {
        notice.value = ""
    }

    fun fetchProducts() {
        isLoading.value = true
        db.collection("products").get()
            .addOnSuccessListener { documents ->
                productList.clear()
                for (document in documents) {
                    val product = document.toObject(Product::class.java) // gan ep du lieu tren db vua dem xuong vao model tạo obj moi
                    productList.add(product)
                }
                isLoading.value = false
            }
            .addOnFailureListener {
                notice.value = "Lỗi tải dữ liệu: ${it.message}"
                isLoading.value = false
            }
    }

    // THÊM SẢN PHẨM
    fun addProduct(name: String, category: String, priceStr: String, imageUrl: String, onSuccess: () -> Unit) {
        if (name.isBlank() || category.isBlank() || priceStr.isBlank() || imageUrl.isBlank()) {
            notice.value = "Vui lòng nhập đủ thông tin!"
            return
        }

        val priceDouble = priceStr.toDoubleOrNull()
        if (priceDouble == null) {
            notice.value = "Giá phải là số !"
            return
        }
        isLoading.value = true
        val productId = UUID.randomUUID().toString()

        val product = Product(
            id = productId,
            name = name,
            category = category,
            price = priceDouble,
            imageUrl = imageUrl
        )

        db.collection("products").document(productId).set(product)
            .addOnSuccessListener {
                isLoading.value = false
                notice.value = "Thêm sản phẩm thành công"
                onSuccess()
            }
            .addOnFailureListener {
                notice.value = "Lỗi: ${it.message}"
                isLoading.value = false
            }
    }

    // CẬP NHẬT SẢN PHẨM
    fun updateProduct(productId: String, name: String, category: String, priceStr: String, imageUrl: String, onSuccess: () -> Unit) {
        val priceDouble = priceStr.toDoubleOrNull()
        if (priceDouble == null) {
            notice.value = "Giá phải là số !"
            return
        }

        isLoading.value = true

        val updatedProduct = Product(
            id = productId,
            name = name,
            category = category,
            price = priceDouble,
            imageUrl = imageUrl
        )

        db.collection("products").document(productId).set(updatedProduct)
            .addOnSuccessListener {
                isLoading.value = false
                notice.value = "Cập nhật hoàn tất"
                onSuccess()
            }
            .addOnFailureListener {
                notice.value = "Lỗi: ${it.message}"
                isLoading.value = false
            }
    }

    fun deleteProduct(productId: String) {
        db.collection("products").document(productId).delete()
            .addOnSuccessListener {
                notice.value = "Xóa sản phẩm thành công"
                fetchProducts()
            }
    }
}