package com.example.Android.local_product_module

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String?, vm: ProductViewModel, nc: NavController) {
    val product = vm.records.find { it.pid == productId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = product?.title ?: "Product Detail") },
                navigationIcon = {
                    IconButton(onClick = { nc.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Home",
                            tint = customColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = customColor,
                    navigationIconContentColor = customColor,
                    actionIconContentColor = customColor
                ),
                actions = {
                    IconButton(onClick = { vm.getResultList() }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = customColor
                        )
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
                        IconButton(onClick = { nc.navigate("home") }) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home",
                                modifier = Modifier.size(35.dp),
                                tint = customColor
                            )
                        }
                        IconButton(onClick = { nc.navigate("cart") }) {
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Cart",
                                modifier = Modifier.size(35.dp),
                                tint = customColor
                            )
                        }
                        IconButton(onClick = { nc.navigate("searchPage") }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                modifier = Modifier.size(35.dp),
                                tint = customColor
                            )
                        }
                        IconButton(onClick = { nc.navigate("aboutPage") }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "About",
                                modifier = Modifier.size(35.dp),
                                tint = customColor
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (product != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                item {
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.title,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Create a Row for price and Add to Cart button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Price: ${product.price} $",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Gray
                        )
                        var isInCart by remember { mutableStateOf(false) } // Manage the cart state

                        Button(
                            onClick = {
                                if (isInCart) {
                                    vm.removeFromCart(product) // Remove product from cart
                                } else {
                                    vm.addToCart(product) // Add product to cart
                                }
                                isInCart = !isInCart // Toggle the cart state
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = customColor)
                        ) {
                            Icon(
                                imageVector = if (isInCart) Icons.Default.RemoveShoppingCart else Icons.Default.AddShoppingCart,
                                contentDescription = if (isInCart) "Remove from Cart" else "Add to Cart",
                                tint = Color.White // Set icon color to white for better contrast
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Quantity: ${product.qty}",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Description: ${product.body}",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.Gray
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Product not found")
            }
        }
    }
}

