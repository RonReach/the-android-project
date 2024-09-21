package com.example.Android.local_product_module

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _records = mutableStateListOf<Records>()
    private val _cartItems = mutableStateListOf<Records>()
    var errorMessage: String by mutableStateOf("")
    var successMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)

    // Publicly accessible lists
    val records: List<Records> get() = _records
    val cartItems: List<Records> get() = _cartItems

    var selectedRecord: Records by mutableStateOf(
        Records(pid = "", title = "", body = "", price = "", qty = "", image = "")
    )

    // Reset state messages and selected record
    fun resetStates() {
        errorMessage = ""
        successMessage = ""
        isLoading = false
        selectedRecord = Records(pid = "", title = "", body = "", price = "", qty = "", image = "")
    }

    // Insert a new record
    fun insertRecord(record: Records) {
        viewModelScope.launch {
            isLoading = true
            val service = ProductService.getInstance()
            try {
                val response: APIResponse = service.insertRecord(
                    title = record.title,
                    body = record.body,
                    price = record.price,
                    qty = record.qty,
                    image = record.image,
                )

                if (response.success) {
                    successMessage = response.message
                    getResultList() // Refresh the record list
                } else {
                    errorMessage = "Error: ${response.message}"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    // Update an existing record
    fun updateRecord(record: Records) {
        viewModelScope.launch {
            isLoading = true
            val service = ProductService.getInstance()
            try {
                val response: APIResponse = service.updateRecord(
                    pid = record.pid,
                    title = record.title,
                    body = record.body,
                    price = record.price,
                    qty = record.qty,
                    image = record.image,
                )

                if (response.success) {
                    successMessage = response.message
                    getResultList() // Refresh the record list
                } else {
                    errorMessage = "Error: ${response.message}"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    // Delete a record by its product ID
    fun deleteRecord(pid: String) {
        viewModelScope.launch {
            isLoading = true
            val service = ProductService.getInstance()
            try {
                val response: APIResponse = service.deleteRecord(pid = pid)

                if (response.success) {
                    successMessage = response.message
                    _records.removeIf { it.pid == pid }
                } else {
                    errorMessage = "Error: ${response.message}"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    // Fetch the list of products
    fun getResultList() {
        viewModelScope.launch {
            isLoading = true
            val apiService = ProductService.getInstance()
            try {
                _records.clear()
                _records.addAll(apiService.getProducts().records)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    // Manage cart items
    fun addToCart(product: Records) {
        if (!_cartItems.contains(product)) {
            _cartItems.add(product)
        }
    }

    fun removeFromCart(product: Records) {
        _cartItems.remove(product)
    }

    // Get cart item count
    fun getCartItemCount(): Int {
        return _cartItems.size
    }
}

