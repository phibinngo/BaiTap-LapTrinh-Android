package com.example.midterm.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Import các màn hình từ đúng thư mục
import com.example.midterm.view.auth.LoginScreen
import com.example.midterm.view.auth.RegisterScreen
import com.example.midterm.view.admin.products.ProductListScreen
import com.example.midterm.view.admin.products.AddProductScreen
import com.example.midterm.view.admin.products.UpdateProductScreen
import com.example.midterm.view.user.UserScreen
import com.example.midterm.viewmodel.AuthViewModel
import com.example.midterm.viewmodel.ProductViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    // Khởi tạo cỗ máy xử lý dữ liệu Sản phẩm
    val productViewModel: ProductViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {

        // 1. Màn hình Đăng nhập
        composable("login") {
            LoginScreen(navController, authViewModel)
        }

        // 2. Màn hình Đăng ký
        composable("register") {
            RegisterScreen(navController, authViewModel)
        }

        // 3. Màn hình User
        composable("user_home") {
            UserScreen(navController = navController, authViewModel = authViewModel)
        }

        // 4. Màn hình Admin (Danh sách)
        composable("admin_home") {
            // Nhớ truyền cả 2 ViewModel vào đây nhé ní
            ProductListScreen(
                navController = navController,
                viewModel = productViewModel,
                authViewModel = authViewModel
            )
        }

        // 5. Màn hình Thêm sản phẩm
        composable("add_product") {
            AddProductScreen(navController = navController, viewModel = productViewModel)
        }

        // 6. Màn hình Cập nhật sản phẩm (Nhận ID từ URL để biết đang sửa thằng nào)
        composable(
            route = "update_product/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            UpdateProductScreen(navController = navController, viewModel = productViewModel, productId = productId)
        }
    }
}