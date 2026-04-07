package com.example.midterm.view.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.midterm.R
import com.example.midterm.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val notice = authViewModel.notice.value
    val role = authViewModel.role.value
    val context = LocalContext.current

    // --- SETUP GOOGLE LOGIN ---
    // Cái R.string.default_web_client_id này tự động có khi ní chép file google-services.json mới vào
    // --- SETUP GOOGLE LOGIN (Đã fix cứng mã Web Client ID) ---
    val googleSignInClient = remember {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            // Bơm thẳng cái mã client_type: 3 vào đây (Nhớ giữ nguyên 2 dấu ngoặc kép nha)
            .requestIdToken("1054564804423-t0p2r8fdnjbh9t6hiu6ehb3ooh4kodbr.apps.googleusercontent.com")
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    // Launcher để bật cửa sổ chọn tài khoản Google
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { idToken ->
                // Gọi hàm login bằng Google trong ViewModel
                authViewModel.loginWithGoogle(idToken)
            }
        } catch (e: ApiException) {
            // Ép nó in ra cái mã lỗi (statusCode)
            authViewModel.notice.value = "Lỗi Google Code: ${e.statusCode}"
            e.printStackTrace()
        }
    }

    LaunchedEffect(role) {
        if (role == "admin") {
            navController.navigate("admin_home") { popUpTo("login") { inclusive = true } }
        } else if (role == "user") {
            navController.navigate("user_home") { popUpTo("login") { inclusive = true } }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("ĐĂNG NHẬP", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(32.dp))

        AuthTextField(value = email, onValueChange = { email = it }, label = "Email")
        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(value = password, onValueChange = { password = it }, label = "Mật khẩu", isPassword = true)
        Spacer(modifier = Modifier.height(8.dp))

        if (notice.isNotEmpty()) {
            Text(text = notice, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Nút đăng nhập thường
        AuthButton(text = "Đăng nhập", onClick = {
            authViewModel.login(email, password)
        })

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "HOẶC", fontWeight = FontWeight.Bold, color = Color.Gray)
        Spacer(modifier = Modifier.height(12.dp))

        // Nút đăng nhập Google Vip Pro
        AuthButton(
            text = "Đăng nhập nhanh bằng Google",
            onClick = {
                // Xóa đăng nhập cũ (nếu có) trước khi bật cửa sổ mới
                googleSignInClient.signOut().addOnCompleteListener {
                    launcher.launch(googleSignInClient.signInIntent)
                }
            },
            containerColor = Color(0xFFDB4437) // Màu đỏ đặc trưng của Google
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.navigate("register") }) {
            Text("Chưa có tài khoản? Đăng ký ngay")
        }
    }
}