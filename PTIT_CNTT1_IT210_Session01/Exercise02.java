package PTIT_CNTT1_IT210_Session01;

public class Exercise02 {

    // Nếu trong Spring:
    // @Component
    // @Scope("prototype")
    // thì mỗi lần lấy bean sẽ tạo ra object mới

    static class PlaySession {
        private double playTime = 0;

        public void addTime(double time) {
            this.playTime += time;
        }

        public double getPlayTime() {
            return playTime;
        }
    }

    /*
     * CODE CŨ BỊ LỖI:
     * 
     * @Component
     * public class PlaySession {
     * private double playTime = 0;
     * 
     * public void addTime(double time) {
     * this.playTime += time;
     * }
     * }
     * 
     * Giải thích:
     * - Trong Spring, bean mặc định có scope là singleton
     * - Singleton nghĩa là chỉ có một object duy nhất trong IoC Container
     * - Nếu PlaySession là singleton thì nhiều máy sẽ dùng chung một instance
     * - Biến playTime sẽ bị cộng dồn giữa các máy
     * - Dẫn đến tính tiền sai
     * 
     * Cách sửa trong Spring:
     * - Dùng @Scope("prototype") để mỗi lần lấy bean sẽ có object mới
     */
}