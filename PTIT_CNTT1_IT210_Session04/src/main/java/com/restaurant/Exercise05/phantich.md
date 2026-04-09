1. Sơ đồ Luồng dữ liệu (Kiến trúc 3 tầng)
   Client -> Controller -> Service -> Repository -> Database

2. Bẫy dữ liệu ép kiểu 
   Hiện tượng: Khi người dùng nhập chữ (VD: limit=abc) vào ô yêu cầu số (kiểu Integer/Long) trên URL, hệ thống sẽ trả về lỗi HTTP 400 Bad Request.
   Nguyên nhân: Spring MVC thực hiện cơ chế Ép kiểu tự động. Khi không thể biến một chuỗi chữ cái thành con số toán học, nó sẽ ném ra ngoại lệ

3. Phân tích Tính an toàn (Idempotency)
   3.1 DELETE:
Khi lag bấm 3 lần: Lần 1 xóa thành công. Lần 2, 3 hệ thống thấy đơn đã xóa nên bỏ qua. Trạng thái dữ liệu không thay đổi.
   3.2 POST:  
Cứ gọi là tạo mới.
Khi lag bấm 3 lần: Hệ thống sinh ra 3 đơn hàng mới tinh (3 ID khác nhau) với cùng một nội dung.
