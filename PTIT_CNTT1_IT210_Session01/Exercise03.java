package PTIT_CNTT1_IT210_Session01;

import java.util.HashMap;
import java.util.Map;

public class Exercise03 {

    // Interface quản lý kho món ăn
    // Service chỉ phụ thuộc vào abstraction này
    interface InventoryRepository {
        int getStock(String foodName);

        void reduceStock(String foodName, int quantity);
    }

    // Interface quản lý tài khoản người dùng
    // Service chỉ phụ thuộc vào abstraction này
    interface UserAccountRepository {
        double getBalance(String username);

        void deductBalance(String username, double amount);
    }

    // Cài đặt kho món ăn
    static class InventoryRepositoryImpl implements InventoryRepository {
        private Map<String, Integer> inventory = new HashMap<>();

        public InventoryRepositoryImpl() {
            inventory.put("Mì xào bò", 0);
            inventory.put("Mì tôm", 10);
            inventory.put("Coca", 20);
        }

        @Override
        public int getStock(String foodName) {
            return inventory.getOrDefault(foodName, -1);
        }

        @Override
        public void reduceStock(String foodName, int quantity) {
            inventory.put(foodName, inventory.get(foodName) - quantity);
        }
    }

    // Cài đặt tài khoản người dùng
    static class UserAccountRepositoryImpl implements UserAccountRepository {
        private Map<String, Double> accounts = new HashMap<>();

        public UserAccountRepositoryImpl() {
            accounts.put("user01", 50000.0);
            accounts.put("user02", -1000.0);
            accounts.put("user03", 10000.0);
        }

        @Override
        public double getBalance(String username) {
            return accounts.getOrDefault(username, -999999.0);
        }

        @Override
        public void deductBalance(String username, double amount) {
            accounts.put(username, accounts.get(username) - amount);
        }
    }

    // Service xử lý nghiệp vụ đặt đồ ăn
    static class OrderFoodService {
        private InventoryRepository inventoryRepository;
        private UserAccountRepository userAccountRepository;

        // Constructor Injection
        // Repository được truyền từ bên ngoài vào
        // Đây là Dependency Injection giúp giảm phụ thuộc chặt
        public OrderFoodService(InventoryRepository inventoryRepository,
                UserAccountRepository userAccountRepository) {
            this.inventoryRepository = inventoryRepository;
            this.userAccountRepository = userAccountRepository;
        }

        public String orderFood(String username, String foodName, double price, int quantity) {

            // Kiểm tra số lượng gọi món
            if (quantity <= 0) {
                return "Thất bại: Số lượng gọi món không hợp lệ";
            }

            // Kiểm tra món ăn trong kho
            int stock = inventoryRepository.getStock(foodName);
            if (stock == -1) {
                return "Thất bại: Món ăn không tồn tại trong kho";
            }

            // Kiểm tra hết hàng hoặc không đủ số lượng
            if (stock == 0 || stock < quantity) {
                return "Thất bại: " + foodName + " đã hết hàng";
            }

            // Kiểm tra số dư tài khoản
            double balance = userAccountRepository.getBalance(username);
            if (balance == -999999.0) {
                return "Thất bại: Tài khoản không tồn tại";
            }

            // Bẫy dữ liệu: số dư âm
            if (balance < 0) {
                return "Thất bại: Số dư tài khoản không hợp lệ";
            }

            // Tính tổng tiền cần thanh toán
            double total = price * quantity;

            // Kiểm tra đủ tiền hay không
            if (balance < total) {
                return "Thất bại: Tài khoản không đủ số dư";
            }

            // Nếu hợp lệ thì cập nhật kho và trừ tiền
            inventoryRepository.reduceStock(foodName, quantity);
            userAccountRepository.deductBalance(username, total);

            return "Đặt món thành công cho " + username + ": " + foodName;
        }
    }

    public static void main(String[] args) {

        // ApplicationContext trong Spring sẽ tiêm dependency tự động
        // Ở đây mô phỏng DI bằng cách tạo object rồi truyền vào constructor

        InventoryRepository inventoryRepository = new InventoryRepositoryImpl();
        UserAccountRepository userAccountRepository = new UserAccountRepositoryImpl();

        OrderFoodService orderFoodService = new OrderFoodService(inventoryRepository, userAccountRepository);

        // Test trường hợp thành công
        System.out.println(orderFoodService.orderFood("user01", "Mì tôm", 10000, 2));

        // Test trường hợp hết hàng
        System.out.println(orderFoodService.orderFood("user01", "Mì xào bò", 30000, 1));

        // Test trường hợp số dư âm
        System.out.println(orderFoodService.orderFood("user02", "Coca", 10000, 1));

        // Test trường hợp không đủ tiền
        System.out.println(orderFoodService.orderFood("user03", "Mì tôm", 15000, 2));
    }
}