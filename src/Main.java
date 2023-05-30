import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.text.*;

public class Main {
    public static void main(String arg[]) {
        Menu menu = new Menu(); // 시작하면 메인 메뉴로 이동
        menu.home(menu.cart);
    }
}

class Menu {

    String name; // 이름
    String explain; // 설명

    public String toString() {
        return name + " | " + explain;
    }

    Menu() {}
    Menu(String a, String b) {
        this.name = a;
        this.explain = b;
    } // 기본 생성자

    static int choose(int a) {

        Scanner sc = new Scanner(System.in);
        int result = 0;
        boolean validinput = false;

        while (!validinput) {
            try {
                System.out.println("숫자를 입력해주세요 :");
                if (sc.hasNextInt()) {
                    result = sc.nextInt();
                    if (result < 1 || result > a) {
                        throw new InputMismatchException();
                    }
                    validinput = true;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("올바르지 않은 입력 입니다.");
                if (sc.hasNext()) {
                    sc.nextLine();
                }
            }
        }
        return result;
    } //

    static Menu burgers = new Menu("Burgers","앵거스 비프 통살을 다져만든 버거" );
    static Menu custard = new Menu("Forzen Custard","매장에서 신선하게 만드는 아이스크림" );
    static Menu drink = new Menu("Drinks","매장에서 직접 만드는 음료" );
    static Menu beer = new Menu("Beer","뉴욕 브루클린 브루어리에서 양조한 맥주" );
    static Menu order = new Menu("Order","장바구니를 확인 후 주문합니다." );
    static Menu cancel = new Menu("Cancel","진행중인 주문을 취소합니다." );

    Cart cart = new Cart();
    public void home(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println("1. " + burgers.toString());
        System.out.println("2. " + custard.toString());
        System.out.println("3. " + drink.toString());
        System.out.println("4. " + beer.toString());
        System.out.println("");
        System.out.println("[ ORDER MENU ]");
        System.out.println("5. " + order.toString());
        System.out.println("6. " + cancel.toString());

        Item item = new Item();

        int max = 6;
        int choose = choose(max);
        switch (choose) {
            case 1:
                item.Burger_Menu(cart);
                break;
            case 2:
                item.Icecream_Menu(cart);
                break;
            case 3:
                item.Drink_Menu(cart);
                break;
            case 4:
                item.Beer_Menu(cart);
                break;
            case 5:
                Show_Cart(cart);
                break;
            case 6:
                Clear_Cart(cart);
                break;
        }
    }

    private static int orderCount = 0; // 주문 횟수 (대기번호)
    private void Show_Cart(Cart cart) {
        System.out.println("\n[ 장바구니 ]");
        for (Order order : cart.getOrders()) {
            System.out.println("상품명 : " + order.getName() + " | 가격 : W " + order.getPrice());
        }
        DecimalFormat df = new DecimalFormat("#,###.#");
        System.out.println("총합 : W " + df.format(cart.getTotal()));
        System.out.println("\n아래와 같이 주문 하시겠습니까? (Y/N)");

        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();

        if (result.equalsIgnoreCase("Y" ) | result.equalsIgnoreCase("y" )) {
            orderCount++;
            System.out.println("대기번호는 [ " + orderCount + " ] 번 입니다.");
            System.out.println("");
            cart.clearorders();
            home(cart);
        } else {
            System.out.println("주문을 취소하셨습니다. 메인 메뉴로 돌아갑니다!");
            home(cart);
        }
    }

    private void Clear_Cart(Cart cart) {
        cart.clearorders();
        System.out.println("장바구니가 비워졌습니다.");
        home(cart);
    }
}

class Item extends Menu {
    double price; // 가격

    public String toString() {
        return name + " | W : " + price + " | " + explain;
    }

    Item() {
    }
    Item(String a, String b, double c) {
        super(a, b);
        this.price = c;
    }

    public double getPrice() {
        return this.price;
    }
    public String getName() {
        return this.name;
    }

    static Item shack = new Item("Shack Burger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", 6.9);
    static Item smoke = new Item("Smoke Shack", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", 8.9);
    static Item shroom = new Item("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", 9.4);
    static Item cheese = new Item("Cheese burger", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", 6.9);
    static Item clasic = new Item("Hamburger", "비프패티를 기반으로 야채가 들어간 기본버거", 5.4);

    public void Burger_Menu(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ Burgers MENU ]");
        System.out.println("1. " + shack.toString());
        System.out.println("2. " + smoke.toString());
        System.out.println("3. " + shroom.toString());
        System.out.println("4. " + cheese.toString());
        System.out.println("5. " + clasic.toString());

        int max = 5;
        int choose = Menu.choose(max);
        Order order = new Order();

        switch (choose) {
            case 1:
                order.setName(shack.getName());
                order.setPrice(shack.getPrice());
                cart.addOrder(order);
                break;
            case 2:
                order.setName(smoke.getName());
                order.setPrice(smoke.getPrice());
                cart.addOrder(order);
                break;
            case 3:
                order.setName(shroom.getName());
                order.setPrice(shroom.getPrice());
                cart.addOrder(order);
                break;
            case 4:
                order.setName(cheese.getName());
                order.setPrice(cheese.getPrice());
                cart.addOrder(order);
                break;
            case 5:
                order.setName(clasic.getName());
                order.setPrice(clasic.getPrice());
                cart.addOrder(order);
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }

    static Item vanilla = new Item("VaniLla Forzen Custard", "콕콕 씹히는 바닐라빈의 향기를 머금은 쫀득한 아이스크림", 3.5);
    static Item strawberry = new Item("Strawberry Forzen Custard", "쫀득한 젤라또 기반의 달콤한 아이스크림에 상큼한 딸기가 퐁당", 3.2);
    static Item choco = new Item("Choco Forzen Custard", "쫀득한 아이스크림과 초콜릿의 달콤한 콜라보", 3.0);
    public void Icecream_Menu(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ Forzen Custard ]");
        System.out.println("1. " + vanilla.toString());
        System.out.println("2. " + strawberry.toString());
        System.out.println("3. " + choco.toString());

        int max = 3;
        int choose = Menu.choose(max);
        Order order = new Order();

        switch (choose) {
            case 1:
                order.setName(vanilla.getName());
                order.setPrice(vanilla.getPrice());
                cart.addOrder(order);
                break;
            case 2:
                order.setName(strawberry.getName());
                order.setPrice(strawberry.getPrice());
                cart.addOrder(order);
                break;
            case 3:
                order.setName(choco.getName());
                order.setPrice(choco.getPrice());
                cart.addOrder(order);
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }

    static Item coke = new Item("Coke", "짜릿한 탄산과 다가오는 달콤함. 햄버거의 영원한 단짝", 1.5);
    static Item soda = new Item("Lime soda", "상큼한 레몬과 라임 그리고 탄산의 청량한 만남", 1.3);
    static Item ambasa = new Item("Ambasa", "부드러운 우유와 탄산의 달콤한 콜라보레이션", 1.1);

    public void Drink_Menu(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ Drink ]");
        System.out.println("1. " + coke.toString());
        System.out.println("2. " + soda.toString());
        System.out.println("3. " + ambasa.toString());

        int max = 3;
        int choose = Menu.choose(max);
        Order order = new Order();

        switch (choose) {
            case 1:
                order.setName(coke.getName());
                order.setPrice(coke.getPrice());
                cart.addOrder(order);
                break;
            case 2:
                order.setName(soda.getName());
                order.setPrice(soda.getPrice());
                cart.addOrder(order);
                break;
            case 3:
                order.setName(ambasa.getName());
                order.setPrice(ambasa.getPrice());
                cart.addOrder(order);
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }

    static Item kas = new Item("Kas", "카스를 안 마셔본 사람은 있지만, 한 번만 마셔본 사람은 없다는 한국의 대표 맥주", 2.3);
    static Item hite = new Item("Hite", "부드럽고 꺼지지 않는 거품이 특징인 청량한 맥주", 2.2);
    static Item filite = new Item("Fi-lite", "세상에서 가장 싼 맥주, 세상에서 가장 무난한 맥주", 1.4);
    public void Beer_Menu(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ Beer ]");
        System.out.println("1. " + kas.toString());
        System.out.println("2. " + hite.toString());
        System.out.println("3. " + filite.toString());

        int max = 3;
        int choose = Menu.choose(max);
        Order order = new Order();

        switch (choose) {
            case 1:
                order.setName(kas.getName());
                order.setPrice(kas.getPrice());
                cart.addOrder(order);
                break;
            case 2:
                order.setName(hite.getName());
                order.setPrice(hite.getPrice());
                cart.addOrder(order);
                break;
            case 3:
                order.setName(filite.getName());
                order.setPrice(filite.getPrice());
                cart.addOrder(order);
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }
}

class Cart {
    private ArrayList<Order> orders;
    public Cart() {
        this.orders = new ArrayList<>();
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }
    public void clearorders() {
        this.orders.clear();
    }
    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public double getTotal() {
        double total = 0;
        for (Order order : this.orders) {
            total += order.getPrice();
        }
        return total;
    }

}
class Order {
    String name; // 이름
    double price; // 가격

    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return this.price;
    }
    public String getName() {
        return this.name;
    }

}