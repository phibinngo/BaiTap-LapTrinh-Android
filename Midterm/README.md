# 🏆 Đồ án Giữa kỳ: Ứng dụng Quản lý & Phân quyền
=================================

Đây là bài tập giữa kỳ tổng hợp các kiến thức đã học. Ứng dụng cung cấp một luồng quản lý hoàn chỉnh bao gồm xác thực người dùng, thao tác dữ liệu trực tuyến và hệ thống phân quyền (Role-based access control) để giới hạn quyền hạn tùy thuộc vào loại tài khoản.

### ✨ **Các chức năng chính**
--------------
* **Xác thực (Authentication):** Cho phép người dùng Đăng ký và Đăng nhập an toàn sử dụng hệ thống của Firebase.
* **Quản lý dữ liệu (CRUD):** Thực hiện đầy đủ các thao tác Thêm (Create), Đọc (Read), Cập nhật (Update) và Xóa (Delete) dữ liệu, được đồng bộ hóa theo thời gian thực trên đám mây.
* **Phân quyền người dùng:** Giao diện và các chức năng thao tác dữ liệu được tự động điều chỉnh dựa trên vai trò của tài khoản (Ví dụ: Chỉ tài khoản "Admin" mới thấy nút Xóa/Sửa, còn tài khoản "User" bình thường chỉ có quyền Xem).

### 📚 **Kiến thức yêu cầu**
--------------
* Nắm vững ngôn ngữ **Kotlin** và cách xây dựng luồng điều hướng màn hình phức tạp bằng **Jetpack Compose**.
* Tích hợp thành thạo hệ sinh thái **Firebase** (đặc biệt là Firebase Authentication và Cloud Firestore).
* Biết cách thiết kế cấu trúc dữ liệu NoSQL để lưu trữ thông tin và vai trò (Role) của từng người dùng.
* Quản lý trạng thái giao diện (State) linh hoạt để ẩn/hiện các thành phần UI (như nút bấm, biểu mẫu) tùy theo quyền hạn của người dùng đang đăng nhập.

### 🚀 **Hướng dẫn cài đặt và chạy ứng dụng**
---------------
1. Cài đặt Android Studio trên máy (nếu chưa có).
2. Tải mã nguồn (source code) của đồ án về.
3. **Lưu ý quan trọng để chạy app thành công:** 
   * Đảm bảo đã đặt file `google-services.json` vào đúng thư mục `app`.
   * Trên Firebase Console, cần bật dịch vụ Authentication (phương thức đăng nhập tương ứng) và tạo cơ sở dữ liệu Cloud Firestore.
   * (Nếu có) Đảm bảo các Firestore Security Rules đã được thiết lập đúng để cho phép các thao tác đọc/ghi.
4. Mở Android Studio và Import project này vào.
5. Chọn **Build** và **Run** để chạy thử ứng dụng.
