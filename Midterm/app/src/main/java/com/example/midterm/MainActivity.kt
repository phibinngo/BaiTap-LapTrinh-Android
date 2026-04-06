package com.example.midterm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.midterm.ui.theme.MidtermTheme // Lưu ý: Tên theme này có thể đổi tùy tên project của ní
import com.example.midterm.view.AppNavigation
import com.example.midterm.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo bộ não AuthViewModel duy nhất ở đây để truyền đi khắp app
        val authViewModel = AuthViewModel()

        setContent {
            MidtermTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Gọi trạm điều hướng
                    AppNavigation(authViewModel = authViewModel)
                }
            }
        }
    }
}