package com.example.Android.local_product_module

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductInsertScaffold(vm: ProductViewModel, nc: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Insert Product") },
                navigationIcon = {
                    IconButton(onClick = {
                        vm.resetStates()
                        nc.popBackStack()
                    }) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                        )
                    }
                }
            )

        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            ProductInsertBody(vm)
        }
    }
}

@Composable
fun ProductInsertBody(vm: ProductViewModel) {

    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var qty by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            placeholder = { Text("Enter Title") },
            leadingIcon = {
                Icon(
                    Icons.Default.TextFields,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = body,
            onValueChange = { body = it },
            label = { Text("body") },
            placeholder = { Text("Enter body") },
            leadingIcon = {
                Icon(
                    Icons.Default.Textsms,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = price,
            onValueChange = { price = it },
            label = { Text("price") },
            placeholder = { Text("Enter price") },
            leadingIcon = {
                Icon(
                    Icons.Default.CurrencyExchange,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = qty,
            onValueChange = { qty = it },
            label = { Text("Quantity") },
            placeholder = { Text("Enter Quantity") },
            leadingIcon = {
                Icon(
                    Icons.Default.Numbers,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = image,
            onValueChange = { image = it },
            label = { Text("image") },
            placeholder = { Text("Enter image") },
            leadingIcon = {
                Icon(
                    Icons.Default.Image,
                    contentDescription = null,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            )
        )
        Button(onClick = {
            val item = Records(
                pid = "0",
                title = title,
                body = body,
                price = price,
                qty = qty,
                image = image,
            )
            vm.insertRecord(item)
        }) {
            Text("INSERT")
        }

        if (vm.isLoading) {
            CircularProgressIndicator()
        }

        if (vm.successMessage.isNotEmpty()) {
            Text(vm.successMessage, color = Color.Blue)
        }

        if (vm.errorMessage.isNotEmpty()) {
            Text(vm.errorMessage, color = Color.Red)
        }
    }
}