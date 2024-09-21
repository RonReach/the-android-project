package com.example.Android.local_product_module

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.Android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(vm: ProductViewModel, nc: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "About Us") },
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
                    navigationIconContentColor = customColor
                )
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
                        IconButton(onClick = { nc.navigate("cart") }) {
                            Icon(
                                imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Cart",
                                modifier = Modifier.size(35.dp),
                                tint = customColor
                            )
                        }
                        IconButton(onClick = { nc.navigate("searchPage") }) {
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
        // Content of About Us Screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("WeFound App", style = MaterialTheme.typography.titleLarge, fontSize = 50.sp, color = customColor)
            Spacer(modifier = Modifier.height(30.dp))
            Text("We are provide you guys all the best technology!", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(15.dp))
            Text("Sen Sok, Phnom Penh, Cambodia", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(15.dp))
            Text("Tel: +855-12-345-678", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(15.dp))
            Text("Email: wefound.info@gmail.com", style = MaterialTheme.typography.bodyLarge)



            // Add more details as needed
        }
    }
}

