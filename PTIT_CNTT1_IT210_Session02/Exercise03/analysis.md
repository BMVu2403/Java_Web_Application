# Phân tích Scope & Implicit Objects trong hệ thống quản lý đơn hàng

## 1. Tại sao thông báo lỗi đăng nhập phải lưu vào Request Scope?

Trong module đăng nhập, khi người dùng nhập sai username hoặc password, hệ thống cần hiển thị thông báo lỗi.

Thông báo này chỉ cần tồn tại trong một lần request duy nhất (khi submit form). Sau khi người dùng refresh trang hoặc gửi request mới, thông báo lỗi phải biến mất.

Vì vậy, thông báo lỗi được lưu trong **Request Scope** là phù hợp.

### Nếu lưu vào Session Scope sẽ xảy ra vấn đề:

- Thông báo lỗi sẽ bị giữ lại trong nhiều request tiếp theo
- Người dùng có thể thấy lỗi cũ ngay cả khi đã nhập đúng thông tin
- Gây trải nghiệm người dùng không tốt và sai logic

=> Kết luận: Thông báo lỗi nên lưu trong Request Scope vì nó chỉ có ý nghĩa tạm thời.

---

## 2. Tại sao totalViewCount phải lưu vào Application Scope?

Biến `totalViewCount` dùng để đếm tổng số lần xem trang đơn hàng của toàn hệ thống.

Biến này cần được:

- Chia sẻ giữa tất cả người dùng
- Không phụ thuộc vào từng phiên đăng nhập riêng biệt

Vì vậy, biến này phải được lưu trong **Application Scope**.

### Nếu lưu vào Session Scope sẽ xảy ra:

- Mỗi người dùng sẽ có một bộ đếm riêng
- Ví dụ:
  - User A xem 3 lần → thấy 3
  - User B mới vào → thấy 1
- Không phản ánh đúng tổng số lượt xem toàn hệ thống

=> Kết luận: Application Scope là lựa chọn đúng vì nó dùng chung cho toàn bộ ứng dụng.

---

## 3. Race Condition là gì?

Race Condition là tình trạng xảy ra khi nhiều thread (nhiều người dùng) cùng truy cập và thay đổi một biến dùng chung tại cùng một thời điểm.

Điều này có thể gây ra:

- Sai lệch dữ liệu
- Mất dữ liệu
- Kết quả không chính xác

---

## 4. Phân tích đoạn code gây Race Condition

```java
Integer count = (Integer) application.getAttribute("totalViewCount");
if (count == null) count = 0;
count++;
application.setAttribute("totalViewCount", count);