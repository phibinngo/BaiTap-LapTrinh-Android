# 🔐 Thực hành Đăng nhập và Đăng ký với Firebase
=================================

Ứng dụng này minh họa quy trình xác thực người dùng (Authentication) trong Android. Ứng dụng cho phép người dùng tạo tài khoản mới, đăng nhập bằng Email/Mật khẩu truyền thống, đồng thời tích hợp tính năng tiện lợi là Đăng nhập bằng tài khoản Google (Google Sign-In). Toàn bộ hệ thống xác thực được quản lý và bảo mật thông qua dịch vụ Firebase Authentication.

### 📚 **Kiến thức yêu cầu**
--------------
* Nắm vững cú pháp của ngôn ngữ **Kotlin** và cách xây dựng giao diện form đăng nhập/đăng ký bằng **Jetpack Compose**.
* Biết cách tích hợp và cấu hình dịch vụ **Firebase Authentication** vào dự án Android.
* Nắm được luồng xử lý tạo tài khoản, đăng nhập, và đăng xuất bằng Email/Mật khẩu qua Firebase SDK.
* Biết cách thiết lập và triển khai **Google Sign-In** (yêu cầu cấu hình mã SHA-1 trên Firebase Console và xử lý token xác thực).
* Quản lý trạng thái người dùng (ví dụ: tự động chuyển hướng vào màn hình chính nếu phát hiện người dùng đã đăng nhập từ lần mở app trước).

### 🚀 **Hướng dẫn cài đặt và chạy ứng dụng**
---------------
1. Cài đặt Android Studio trên máy (nếu chưa có).
2. Tải mã nguồn (source code) của bài tập về.
3. **Lưu ý quan trọng:** Cần phải có file `google-services.json` đặt trong thư mục `app`. Ngoài ra, để tính năng Đăng nhập bằng Google hoạt động, bạn phải đảm bảo mã SHA-1 của môi trường chạy app đã được khai báo chính xác trên Firebase Console.
4. Mở Android Studio và Import project này vào.
5. Chọn **Build** và **Run** để chạy thử ứng dụng.
