package com.example.Android.local_product_module

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import coil.compose.AsyncImage



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(vm: ProductViewModel, nc: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Cart") },
                navigationIcon = {
                    IconButton(onClick = { nc.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
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
                        IconButton(onClick = { nc.navigate("productList") }) {
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
        if (vm.cartItems.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(vm.cartItems) { product ->
                    // Use a Column instead of Card
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp) // Add some padding for spacing
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = product.title,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Price Text
                            Text(
                                text = product.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Gray
                            )

                            // Cart Button
                            var isInCart by remember { mutableStateOf(false) } // Manage cart state

                            // Remove from Cart button
                            Button(
                                onClick = {
                                    vm.removeFromCart(product) // Remove the item from the cart
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = customColor)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RemoveShoppingCart,
                                    contentDescription = "Remove from Cart",
                                    tint = Color.White // Set icon color to white for better contrast
                                )
                            }
                        }

                        Text(
                            text = "Price: ${product.price} $",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Gray
                        )
                        Text(
                            text = "Quantity: ${product.qty}",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(8.dp))


                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Your cart is empty")
            }
        }
    }
}




