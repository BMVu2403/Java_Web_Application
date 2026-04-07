# security_report.md

## 1. XSS là gì?

XSS (Cross-Site Scripting) là lỗ hổng bảo mật xảy ra khi ứng dụng web hiển thị dữ liệu do người dùng nhập vào mà không escape an toàn, khiến trình duyệt hiểu dữ liệu đó như HTML hoặc JavaScript và có thể thực thi nó.

Ví dụ, nếu người dùng nhập:

```html
<script>alert(1)</script>
```

và ứng dụng in trực tiếp ra màn hình, trình duyệt có thể chạy đoạn script này.

---

## 2. Tại sao `<c:out value="${keyword}"/>` an toàn hơn `${keyword}`?

`${keyword}` chỉ in giá trị ra giao diện.

`<c:out value="${keyword}"/>` sẽ escape các ký tự đặc biệt như `<`, `>`, `&`, `"`, `'`, nên dữ liệu được hiển thị dưới dạng văn bản thường thay vì bị trình duyệt hiểu là HTML/JavaScript.

### Ví dụ với input

```html
<script>alert(1)</script>
```

### Nếu dùng `${keyword}`

HTML sinh ra có thể là:

```html
Kết quả tìm kiếm cho: <script>alert(1)</script>
```

Khi đó script có thể bị thực thi.

### Nếu dùng `<c:out value="${keyword}"/>`

HTML sinh ra sẽ gần giống:

```html
Kết quả tìm kiếm cho: &lt;script&gt;alert(1)&lt;/script&gt;
```

Khi đó trình duyệt chỉ hiển thị text, không chạy script.

---

## 3. Khác nhau giữa `<c:if>` và `<c:choose>/<c:when>/<c:otherwise>`

### `<c:if>`
Dùng cho một điều kiện đơn. Nếu điều kiện đúng thì hiển thị nội dung.

Ví dụ:
- Nếu `events` rỗng thì hiển thị thông báo không có kết quả.

### `<c:choose>/<c:when>/<c:otherwise>`
Dùng khi có nhiều nhánh điều kiện loại trừ nhau, tương tự `if / else if / else` trong Java.

### Trong bài này
- Phần “Không tìm thấy sự kiện nào phù hợp.” nên dùng `<c:if>` vì chỉ có một điều kiện là `empty events`.
- Phần “Giá vé” nên dùng `<c:choose>` vì có 2 nhánh:
  - `price == 0`
  - ngược lại
- Phần “Vé còn lại” nên dùng `<c:choose>` vì có 3 nhánh:
  - `remainingTickets == 0`
  - `remainingTickets < 10`
  - còn lại

Dùng `<c:choose>` ở hai phần này sẽ rõ ràng hơn và dễ đọc hơn.

---

## 4. `<c:url>` giải quyết vấn đề gì?

Nếu hardcode:

```html
<a href="/events/1/book">Đặt vé</a>
```

thì đường dẫn luôn tính từ root `/`.

Điều này sẽ sai khi ứng dụng được deploy với context path khác, ví dụ `/ticketing`.

Khi đó URL đúng phải là:

```text
/ticketing/events/1/book
```

`<c:url>` giúp tạo URL đúng theo context path hiện tại của ứng dụng.

Ví dụ:

```jsp
<c:url value="/events/${event.id}/book"/>
```

Nếu deploy ở `/ticketing` thì link sẽ tự thành `/ticketing/events/{id}/book`.

---

## 5. Những gì đã triển khai

- File `search.jsp` không có scriptlet
- Chỉ dùng EL + JSTL
- `keyword` được hiển thị bằng `<c:out>`
- `event.name` được hiển thị bằng `<c:out>`
- Có kiểm tra `not empty events` trước khi dùng `events[0]`
- Có dùng `c:url` để tránh sai context path
- Có dùng `fmt:formatNumber` để định dạng giá vé
- Có xử lý hết vé, sắp hết vé, còn vé
- Dùng JSTL Jakarta EE URI phù hợp Tomcat 10+