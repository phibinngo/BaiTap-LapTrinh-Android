package com.example.midterm.view.admin.products

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PowerSettingsNew // Icon nút nguồn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.midterm.model.Product
import com.example.midterm.viewmodel.AuthViewModel
import com.example.midterm.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel,
    authViewModel: AuthViewModel
) {
    val productList = viewModel.productList

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,

        // --- TÙY BIẾN 2 NÚT NỔI 2 BÊN ---
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp), // Thụt lề 2 bên cho cân đối
                horizontalArrangement = Arrangement.SpaceBetween, // Đẩy 2 thằng về 2 đầu
                verticalAlignment = Alignment.Bottom
            ) {
                // 1. NÚT THÊM (+) - GÓC TRÁI
                FloatingActionButton(
                    onClick = { navController.navigate("add_product") },
                    containerColor = Color(0xFF1976D2),
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Thêm")
                }

                // 2. NÚT ĐĂNG XUẤT (NGUỒN) - GÓC PHẢI
                FloatingActionButton(
                    onClick = {
                        authViewModel.logout()
                        navController.navigate("login") { popUpTo(0) }
                    },
                    containerColor = Color(0xFFE53935),
                    contentColor = Color.White,
                    shape = CircleShape
                ) {
                    Icon(imageVector = Icons.Default.PowerSettingsNew, contentDescription = "Đăng xuất")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center // Đặt hàng ngang này vào giữa để nó giãn ra 2 bên
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DANH SÁCH SẢN PHẨM",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (viewModel.isLoading.value) {
                CircularProgressIndicator()
            } else if (productList.isEmpty()) {
                Text(text = "Danh sách trống!", color = Color.Gray)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(productList) { product ->
                        ProductItemCard(
                            product = product,
                            onEditClick = { navController.navigate("update_product/${product.id}") },
                            onDeleteClick = { viewModel.deleteProduct(product.id) }
                        )
                    }
                }
            }
        }
    }
}

//@Composable
//fun ProductItemCard(product: Product, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
//    OutlinedCard(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(8.dp),
//        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
//        border = BorderStroke(1.dp, Color(0xFF64B5F6))
//    ) {
//        Row(
//            modifier = Modifier.padding(12.dp).fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(product.imageUrl)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = product.name,
//                modifier = Modifier.size(width = 100.dp, height = 70.dp),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Column(modifier = Modifier.weight(1f)) {
//                ProductTextInfo(label = "Tên sp", value = product.name)
//                // Ép kiểu hiển thị giá về chuỗi
//                ProductTextInfo(label = "Giá sp", value = product.price.toString())
//                ProductTextInfo(label = "Loại sp", value = product.category)
//            }
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                OutlinedIconButton(
//                    onClick = onEditClick,
//                    modifier = Modifier.size(36.dp),
//                    shape = RoundedCornerShape(8.dp),
//                    colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = Color(0xFFFFB300)),
//                    border = BorderStroke(1.dp, Color(0xFFFFB300))
//                ) {
//                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Sửa", modifier = Modifier.size(20.dp))
//                }
//
//                OutlinedIconButton(
//                    onClick = onDeleteClick,
//                    modifier = Modifier.size(36.dp),
//                    shape = RoundedCornerShape(8.dp),
//                    colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = Color(0xFFE53935)),
//                    border = BorderStroke(1.dp, Color(0xFFE53935))
//                ) {
//                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Xóa", modifier = Modifier.size(20.dp))
//                }
//            }
//        }
//    }
//}
@Composable
fun ProductItemCard(product: Product, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp), // Thêm chút khoảng cách 2 bên
        shape = RoundedCornerShape(16.dp), // Bo góc tròn hơn nhìn sẽ hiện đại hơn
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // Thêm đổ bóng nhẹ cho sang
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF5F5F5)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = product.name,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // --- THÔNG TIN SẢN PHẨM ---
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF1A1A1A)
                )
                Text(
                    text = "${product.price.toLong()} VNĐ",
                    color = Color(0xFF1976D2),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Surface(
                    color = Color(0xFFE3F2FD),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = product.category,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        fontSize = 11.sp,
                        color = Color(0xFF1976D2)
                    )
                }
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {


                IconButton(
                    onClick = onEditClick,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xFFE8EAF6), // Màu xanh Indigo rất nhạt
                        contentColor = Color(0xFF3F51B5)    // Màu xanh Indigo đậm
                    ),
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Sửa",
                        modifier = Modifier.size(20.dp)
                    )
                }


                IconButton(
                    onClick = onDeleteClick,
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0xFFFFEBEE), // Màu hồng nhạt
                        contentColor = Color(0xFFC62828)    // Màu đỏ đô đậm
                    ),
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Xóa",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductTextInfo(label: String, value: String) {
    Row {
        Text(text = "$label:", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 14.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = value, color = Color.Black, fontSize = 14.sp)
    }
}