package com.example.Android.local_product_module

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.AsyncImage

@Composable
fun AppNavHost(vm: ProductViewModel, nc: NavController) {
    NavHost(navController = nc as NavHostController, startDestination = "productList") {
        composable("productList") {
            ProductScaffold(vm, nc)
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            com.example.Android.local_product_module.ProductDetailScreen(productId, vm, nc)
        }
        composable("searchPage") {
            ProductSearchScreen(vm, nc)
        }
        composable("cart") {
            CartScreen(vm, nc)
        }
        composable("aboutUs") {
            AboutUsScreen(vm, nc)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearchScreen(vm: ProductViewModel, nc: NavController) {
    var query = remember { mutableStateOf("") }  // Holds the search query

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Products") },
                navigationIcon = {
                    IconButton(onClick = { nc.navigateUp() }) { // Navigate back to the previous screen
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = customColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White, // Background color
                    titleContentColor = customColor, // Text color
                    navigationIconContentColor = customColor // Back icon color
                ),
                actions = {
                    IconButton(onClick = { vm.getResultList() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh", tint = customColor)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(onClick = { nc.navigate("productList") }) {
                            Icon(Icons.Default.Home, contentDescription = "Home", modifier = Modifier.size(35.dp), tint = customColor)
                        }
                        IconButton(onClick = { nc.navigate("cart") }) { // Navigate to CartScreen
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Cart",
                                modifier = Modifier.size(35.dp),
                                tint = customColor
                            )
                        }
                        IconButton(onClick = { nc.navigate("searchPage") }) { // Navigate to the search page
                            Icon(Icons.Default.Search, contentDescription = "Search", modifier = Modifier.size(35.dp), tint = customColor)
                        }
                        IconButton(onClick = { nc.navigate("aboutUs") }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "About Us",
                                modifier = Modifier.size(35.dp),
                                tint = customColor
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column {
                // Search bar
                TextField(
                    value = query.value,
                    onValueChange = { query.value = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search for products...") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Filter products based on the query
                val filteredProducts = vm.records.filter {
                    it.title.contains(query.value, ignoreCase = true)
                }

                // Display the filtered products
                if (filteredProducts.isNotEmpty()) {
                    LazyColumn {
                        items(filteredProducts.size) { index ->
                            val product = filteredProducts[index]
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable {
                                        nc.navigate("productDetail/${product.pid}")
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = product.image,
                                    contentDescription = product.title,
                                    modifier = Modifier.size(80.dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(product.title, style = MaterialTheme.typography.titleMedium)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("Price: ${product.price}", style = MaterialTheme.typography.bodyMedium)
                                }
                            }
                        }
                    }
                } else {
                    // If no products match the search query
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No products found", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}






