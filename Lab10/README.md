# 🗄️ Bài tập CRUD Cơ bản với Room Database
=================================

Ứng dụng này minh họa cách lưu trữ và quản lý dữ liệu cục bộ (local database) trực tiếp trên thiết bị Android. Ứng dụng thực hiện đầy đủ các thao tác CRUD (Thêm, Đọc, Sửa, Xóa) thông qua thư viện Room, cung cấp một lớp trừu tượng (abstraction layer) an toàn và tối ưu hơn trên nền tảng SQLite truyền thống.

### 📚 **Kiến thức yêu cầu**
--------------
* Nắm vững cú pháp của ngôn ngữ **Kotlin** và cách xây dựng giao diện bằng **Jetpack Compose**.
* Hiểu cấu trúc và cách định nghĩa 3 thành phần cốt lõi của Room: **Entity** (Đại diện cho bảng), **DAO** (Chứa các câu lệnh truy vấn) và lớp **Database**.
* Biết cách tổ chức mã nguồn và giữ nguyên cấu trúc thư mục dữ liệu cho gọn gàng (ví dụ: gom các thành phần của Room vào package `com.example.Lab10.data`).
* Sử dụng **Coroutines** và **Flow** để thực hiện các truy vấn bất đồng bộ dưới nền và tự động cập nhật giao diện ngay khi dữ liệu trong bảng thay đổi.
* Biết cách kết hợp với **ViewModel** để quản lý trạng thái (State) và tách biệt logic xử lý dữ liệu khỏi phần giao diện (UI).

### 🚀 **Hướng dẫn cài đặt và chạy ứng dụng**
---------------
1. Cài đặt Android Studio trên máy (nếu chưa có).
2. Tải mã nguồn (source code) của bài tập về.
3. Mở Android Studio và Import project này vào.
4. Chọn **Build** và **Run** để chạy thử ứng dụng.
