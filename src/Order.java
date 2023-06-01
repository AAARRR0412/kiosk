public class Order {
    String name; // 주문한 상품의 이름을 담습니다.
    double price; // 주문한 상품의 가격을 담습니다.

    public Order(String name, double price) {
        this.name = name;
        this.price = price;
    } /// Item 클래스의 name, price를 Order 클래스의 name, price로 설정할 때 사용한 메서드입니다.

    public void setPrice(double price) {
        this.price = price;
    } /// Item 클래스의 name를 Order 클래스의 name로 설정할 떄 사용한 메서드입니다.
    public void setName(String name) {
        this.name = name;
    } /// Item 클래스의 name를 Order 클래스의 name로 설정할 떄 사용한 메서드입니다.
    public double getPrice() {
        return this.price;
    } /// price를 읽는 메서드 입니다.
    public String getName() {
        return this.name;
    } /// name를 읽는 메서드 입니다.

}
