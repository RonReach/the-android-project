package com.example.Android.local_product_module

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage


val customColor = Color(0xFF336B8B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScaffold(vm: ProductViewModel, nc: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "WeFound",
                        fontWeight = FontWeight.Bold, // Make text bold
                        fontSize = 30.sp, // Increase font size (adjust as needed)
                        color = customColor // Set text color
                    )
                },
                actions = {
                    IconButton(onClick = {
                        vm.getResultList()
                    }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            tint = customColor,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White, // Set the bottom bar color
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
                        IconButton(onClick = { nc.navigate("cart") }) { // Navigate to CartScreen
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Use paddingValues here
        ) {
            ProductBodyColumn(vm, nc)
        }
    }

}

@Composable
fun ProductBodyColumn(vm: ProductViewModel, nc: NavController) {
    LaunchedEffect(Unit) {
        vm.getResultList()
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        if (vm.isLoading) {
            CircularProgressIndicator()
        } else if (vm.errorMessage.isNotEmpty()) {
            Text("Error: ${vm.errorMessage}")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(vm.records.size) { index ->
                    val element = vm.records[index]

                    // Check if the product is already in the cart
                    val isInCart = vm.cartItems.contains(element)

                    // Full-screen product layout with price and Add/Remove from Cart button below the image
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                vm.selectedRecord = element
                                nc.navigate("productDetail/${element.pid}")
                            }
                    ) {
                        // Display the product image
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            color = Color.Gray
                        ) {
                            AsyncImage(
                                model = element.image,
                                error = painterResource(android.R.drawable.ic_menu_info_details),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // Row below the image containing price and Add/Remove from Cart button
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Product price on the left
                            Text(
                                text = element.title,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                            // Add/Remove from Cart button on the right
                            Button(
                                onClick = {
                                    if (isInCart) {
                                        vm.removeFromCart(element) // Remove from cart
                                    } else {
                                        vm.addToCart(element) // Add to cart
                                    }
                                },
                                modifier = Modifier.align(Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(containerColor = customColor)
                            ) {
                                // Switch between Add and Remove icons
                                if (isInCart) {
                                    Icon(
                                        imageVector = Icons.Default.RemoveShoppingCart, // Updated to RemoveShoppingCart
                                        contentDescription = "Remove from Cart"
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Filled.AddShoppingCart,
                                        contentDescription = "Add to Cart"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



