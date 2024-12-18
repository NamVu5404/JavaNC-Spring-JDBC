# Hướng dẫn cài đặt dự án "Quản lý bất động sản" từ GitHub

## Các bước thực hiện:

### 1. Tải source code về:
```bash
git clone https://github.com/NamVu5404/JavaNC-Spring-JDBC.git
```

### 2. Thiết lập cơ sở dữ liệu:
- Mở MySQL Workbench.
- Tạo một database mới.
- Import cơ sở dữ liệu từ file `estateadvance.sql` trong source code.

### 3. Chạy source code:
- Mở dự án bằng IntelliJ IDEA.
- Chạy ứng dụng.

## Tài khoản đăng nhập mẫu:

### Admin:
- **Username:** nguyenvana  
- **Password:** 123456

### Nhân viên:
- **Username:** nguyenvanb  
- **Password:** 123456

### Khách hàng:
- **Username:** vuvannam  
- **Password:** 123456

## Yêu cầu hệ thống:
- Máy tính cần cài đặt **JDK 1.8** trở lên.

## Phạm vi của dự án:

### Đăng nhập:
- Phân quyền: Admin, Staff và User.

### Admin:
- Quản lý tòa nhà.
- Quản lý tài khoản.
- Quản lý khách hàng.
- Tìm kiếm khách hàng.
- Tìm kiếm tài khoản

### Khách hàng:
- Đăng nhập
- Đăng ký
- Tìm kiếm tòa nhà.
- Xem tin tức.
