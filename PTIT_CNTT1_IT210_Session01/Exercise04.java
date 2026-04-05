package PTIT_CNTT1_IT210_Session01;

public class Exercise04 {

    // Interface gửi Email
    interface EmailSender {
        void send(String message);
    }

    // Interface gửi SMS
    interface SmsSender {
        void send(String message);
    }

    // Cài đặt gửi Email
    static class EmailSenderImpl implements EmailSender {
        @Override
        public void send(String message) {
            System.out.println("Đã gửi Email: " + message);
        }
    }

    // Cài đặt gửi SMS
    static class SmsSenderImpl implements SmsSender {
        @Override
        public void send(String message) {
            // Mô phỏng lỗi mạng khi gửi SMS
            throw new RuntimeException("Mất kết nối tới dịch vụ SMS");
        }
    }

    // Giải pháp 1: Constructor Injection
    // Đây là cách phù hợp nhất vì dependency được truyền vào ngay khi khởi tạo
    // object
    // Giúp object luôn ở trạng thái đầy đủ dependency
    // Dễ kiểm thử và dễ bảo trì
    static class NotificationServiceByConstructor {
        private EmailSender emailSender;
        private SmsSender smsSender;

        public NotificationServiceByConstructor(EmailSender emailSender, SmsSender smsSender) {
            this.emailSender = emailSender;
            this.smsSender = smsSender;
        }

        public void notifyRechargeSuccess(String message) {
            emailSender.send(message);

            try {
                smsSender.send(message);
            } catch (Exception e) {
                System.out.println("Gửi SMS thất bại: " + e.getMessage());
            }
        }
    }

    // Giải pháp 2: Setter Injection
    // Cách này linh hoạt vì có thể gán dependency sau khi tạo object
    // Nhưng nếu quên gọi setter thì object có thể chưa đủ dependency để hoạt động
    static class NotificationServiceBySetter {
        private EmailSender emailSender;
        private SmsSender smsSender;

        public void setEmailSender(EmailSender emailSender) {
            this.emailSender = emailSender;
        }

        public void setSmsSender(SmsSender smsSender) {
            this.smsSender = smsSender;
        }

        public void notifyRechargeSuccess(String message) {
            if (emailSender != null) {
                emailSender.send(message);
            }

            try {
                if (smsSender != null) {
                    smsSender.send(message);
                }
            } catch (Exception e) {
                System.out.println("Gửi SMS thất bại: " + e.getMessage());
            }
        }
    }

    /*
     * So sánh 2 giải pháp DI
     * 
     * 1. Constructor Injection
     * Ưu điểm:
     * - Rõ ràng dependency của class
     * - Object được tạo ra với đầy đủ dependency
     * - Dễ kiểm thử
     * - Phù hợp với dependency bắt buộc
     * 
     * Nhược điểm:
     * - Constructor có thể dài nếu có quá nhiều dependency
     * 
     * 2. Setter Injection
     * Ưu điểm:
     * - Linh hoạt
     * - Có thể thay đổi dependency sau khi object được tạo
     * 
     * Nhược điểm:
     * - Có thể quên gọi setter
     * - Object có thể ở trạng thái thiếu dependency
     * - Kém an toàn hơn Constructor Injection
     * 
     * Kết luận:
     * - Nên chọn Constructor Injection
     * - Vì EmailSender và SmsSender là dependency quan trọng
     * - Đồng thời dễ xử lý và kiểm thử khi dịch vụ SMS bị lỗi kết nối
     */

    public static void main(String[] args) {

        EmailSender emailSender = new EmailSenderImpl();
        SmsSender smsSender = new SmsSenderImpl();

        System.out.println("Constructor Injection:");
        NotificationServiceByConstructor service1 = new NotificationServiceByConstructor(emailSender, smsSender);
        service1.notifyRechargeSuccess("Nạp tiền thành công 50.000 VNĐ");

        System.out.println("-----");

        System.out.println("Setter Injection:");
        NotificationServiceBySetter service2 = new NotificationServiceBySetter();
        service2.setEmailSender(emailSender);
        service2.setSmsSender(smsSender);
        service2.notifyRechargeSuccess("Nạp tiền thành công 100.000 VNĐ");
    }
}