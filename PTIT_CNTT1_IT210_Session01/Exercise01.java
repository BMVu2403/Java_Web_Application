package PTIT_CNTT1_IT210_Session01;

public class Exercise01 {

    // Interface abstraction cho Payment Gateway
    // RechargeService chỉ nên phụ thuộc vào interface này
    interface PaymentGateway {
        void pay(double amount);
    }

    // Implementation cổng thanh toán nội bộ
    static class InternalPaymentGateway implements PaymentGateway {
        @Override
        public void pay(double amount) {
            System.out.println("Thanh toán nội bộ: " + amount);
        }
    }

    // Implementation cổng Momo
    static class MomoPaymentGateway implements PaymentGateway {
        @Override
        public void pay(double amount) {
            System.out.println("Thanh toán qua Momo: " + amount);
        }
    }

    // Service xử lý nạp tiền
    static class RechargeService {
        private PaymentGateway gateway;

        // CODE CŨ SAI:
        // public RechargeService() {
        // this.gateway = new InternalPaymentGateway();
        // }
        // Sai vì tự khởi tạo dependency bên trong class
        // Làm cho RechargeService bị phụ thuộc chặt vào InternalPaymentGateway
        // Khó mở rộng thêm Momo, ZaloPay
        // Vi phạm nguyên lý IoC

        // CODE ĐÚNG:
        // Nhận dependency từ bên ngoài bằng constructor injection
        public RechargeService(PaymentGateway gateway) {
            this.gateway = gateway;
        }

        public void processRecharge(String username, double amount) {
            gateway.pay(amount);
            System.out.println("Nạp " + amount + " cho " + username);
        }
    }

    public static void main(String[] args) {
        PaymentGateway internalGateway = new InternalPaymentGateway();
        RechargeService service1 = new RechargeService(internalGateway);
        service1.processRecharge("userA", 50000);

        System.out.println("-----");

        PaymentGateway momoGateway = new MomoPaymentGateway();
        RechargeService service2 = new RechargeService(momoGateway);
        service2.processRecharge("userB", 100000);
    }
}