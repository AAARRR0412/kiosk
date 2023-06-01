import java.util.ArrayList;

public class Cart {
    /// Order 클래스의 객체만을 담을 수 있는 Arraylist 를 생성합니다. 지네렉스 <>를 통해서 Order 클래스만 담을 수 있게 했습니다.
    private ArrayList<Order> orders;
    public Cart() {
        this.orders = new ArrayList<>();
    }
    public void addOrder(Order order) { // Order 클래스의 order 객체를 Arraylist orders에 추가합니다.
        this.orders.add(order);
    }
    public void clearorders() {
        this.orders.clear();
    } /// Arraylist orders를 초기화 합니다.
    public ArrayList<Order> getOrders() {
        return this.orders;
    } /// 주문화면 메서드에서 Arraylist orders에 담긴 것을 전달합니다.

    public double getTotal() {
        double total = 0;
        for (Order order : this.orders) {
            total += order.getPrice();
        }
        return total;
    } /// Order 클래스의 order 객체의 price 값을 모두 더합니다.

}