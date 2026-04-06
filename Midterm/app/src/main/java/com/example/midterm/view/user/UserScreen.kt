package com.example.midterm.view.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.midterm.viewmodel.AuthViewModel

@Composable
fun UserScreen(navController: NavController, authViewModel: AuthViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        //  Nút Đăng xuất
        Button(
            onClick = {
                authViewModel.logout()
                navController.navigate("login") {
                    popUpTo(0)
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd) //góc phải trên cùng
                .padding(16.dp),    // padding
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)) // màu đỏ
        ) {
            Text("Đăng xuất", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Text(
            text = "Đây là trang user",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center) // set giữa màn hình
        )
    }
}