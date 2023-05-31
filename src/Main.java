import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.text.*;

public class Main {
    public static void main(String arg[]) {
        Menu menu = new Menu(); // 시작하면 메인 메뉴로 이동합니다.
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

    /// 메인 화면에 사용되는 객체들 입니다. (선택지)
    static Menu burgers = new Menu("Burgers","앵거스 비프 통살을 다져만든 버거" );
    static Menu custard = new Menu("Forzen Custard","매장에서 신선하게 만드는 아이스크림" );
    static Menu drink = new Menu("Drinks","매장에서 직접 만드는 음료" );
    static Menu beer = new Menu("Beer","뉴욕 브루클린 브루어리에서 양조한 맥주" );
    static Menu order = new Menu("Order","장바구니를 확인 후 주문합니다." );
    static Menu cancel = new Menu("Cancel","진행중인 주문을 취소합니다." );

    /// 입력을 받는 메서드 입니다.
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
    }

    ///

    Cart cart = new Cart(); // 메뉴 클래스에서 사용 할 장바구니 객체를 만듭니다. (장바구니에 물건을 전달 받아야 함)

    // 메인 화면 메서드 입니다.
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

        int choose = choose(6);
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

    /// 메인화면 메서드 끝입니다.

    private static int orderCount = 0; // 주문 횟수 (대기번호)

    /// 주문화면 메서드 시작입니다.

    private void Show_Cart(Cart cart) {
        System.out.println("\n[ 장바구니 ]");
        for (Order order : cart.getOrders()) {
            System.out.println("상품명 : " + order.getName() + " | 가격 : W " + order.getPrice());
        }
        DecimalFormat df = new DecimalFormat("#,###.#"); /// 단위를 소숫점 1자리로 설정합니다.
        System.out.println("총합 : W " + df.format(cart.getTotal()));
        System.out.println("\n아래와 같이 주문 하시겠습니까? (Y/N)");

        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();

        if (result.equalsIgnoreCase("Y" )) {
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

    /// 주문화면 메서드 끝입니다.

    /// 장바구니 비우고 메인으로 돌아가는 메서드 입니다.

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

    /// [Burgers]
    static Item shack = new Item("Shack Burger", "토마토, 양상추, 쉑소스가 토핑된 치즈버거", 6.9);
    static Item smoke = new Item("Smoke Shack", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거", 8.9);
    static Item shroom = new Item("Shroom Burger", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거", 9.4);
    static Item cheese = new Item("Cheese burger", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", 6.9);
    static Item clasic = new Item("Hamburger", "비프패티를 기반으로 야채가 들어간 기본버거", 5.4);
    /// [Forzen Custards]
    static Item vanilla = new Item("VaniLla Frozen Custard", "콕콕 씹히는 바닐라빈의 향기를 머금은 쫀득한 아이스크림", 3.5);
    static Item strawberry = new Item("Strawberry Frozen Custard", "쫀득한 젤라또 기반의 달콤한 아이스크림에 상큼한 딸기가 퐁당", 3.2);
    static Item choco = new Item("Choco Frozen Custard", "쫀득한 아이스크림과 초콜릿의 달콤한 콜라보", 3.0);
    /// [Drinks]
    static Item coke = new Item("Coke", "짜릿한 탄산과 다가오는 달콤함. 햄버거의 영원한 단짝", 1.5);
    static Item soda = new Item("Lime soda", "상큼한 레몬과 라임 그리고 탄산의 청량한 만남", 1.3);
    static Item ambasa = new Item("Ambasa", "부드러운 우유와 탄산의 달콤한 콜라보레이션", 1.1);
    /// [Beers]
    static Item kas = new Item("Kas", "카스를 안 마셔본 사람은 있지만, 한 번만 마셔본 사람은 없다는 한국의 대표 맥주", 2.3);
    static Item hite = new Item("Hite", "부드럽고 꺼지지 않는 거품이 특징인 청량한 맥주", 2.2);
    static Item filite = new Item("Fi-lite", "세상에서 가장 싼 맥주, 세상에서 가장 무난한 맥주", 1.4);


    /// [ order 클래스의 price, name 변수와 Item 클래스의 price와 name 변수를 일치 시켜주는 메서드 ]

    public double getPrice() {
        return this.price;
    }
    public String getName() {
        return this.name;
    }

    ///

    /// 버거 선택 화면 메서드 입니다.
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

        int choose = Menu.choose(5);

        switch (choose) {
            case 1:
                cart.addOrder(new Order(shack.getName(), shack.getPrice()));
                break;
            case 2:
                cart.addOrder(new Order(smoke.getName(), smoke.getPrice()));
                break;
            case 3:
                cart.addOrder(new Order(shroom.getName(), shroom.getPrice()));
                break;
            case 4:
                cart.addOrder(new Order(cheese.getName(), cheese.getPrice()));
                break;
            case 5:
                cart.addOrder(new Order(clasic.getName(), clasic.getPrice()));
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }

    /// 아이스크림 선택 화면 메서드 입니다.
    public void Icecream_Menu(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ Forzen Custard ]");
        System.out.println("1. " + vanilla.toString());
        System.out.println("2. " + strawberry.toString());
        System.out.println("3. " + choco.toString());

        int choose = Menu.choose(3);

        switch (choose) {
            case 1:
                cart.addOrder(new Order(vanilla.getName(), vanilla.getPrice()));
                break;
            case 2:
                cart.addOrder(new Order(strawberry.getName(), strawberry.getPrice()));
                break;
            case 3:
                cart.addOrder(new Order(choco.getName(), choco.getPrice()));
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }

    /// 음료수 선택 화면 메서드 입니다.
    public void Drink_Menu(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ Drink ]");
        System.out.println("1. " + coke.toString());
        System.out.println("2. " + soda.toString());
        System.out.println("3. " + ambasa.toString());

        int choose = Menu.choose(3);

        switch (choose) {
            case 1:
                cart.addOrder(new Order(coke.getName(), coke.getPrice()));
                break;
            case 2:
                cart.addOrder(new Order(soda.getName(), soda.getPrice()));
                break;
            case 3:
                cart.addOrder(new Order(ambasa.getName(), ambasa.getPrice()));
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }

    /// 맥주 선택 화면 메서드 입니다.
    public void Beer_Menu(Cart cart) {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"");
        System.out.println("아래 상품 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        System.out.println("");
        System.out.println("[ Beer ]");
        System.out.println("1. " + kas.toString());
        System.out.println("2. " + hite.toString());
        System.out.println("3. " + filite.toString());

        int choose = Menu.choose(3);

        switch (choose) {
            case 1:
                cart.addOrder(new Order(kas.getName(), kas.getPrice()));
                break;
            case 2:
                cart.addOrder(new Order(hite.getName(), hite.getPrice()));
                break;
            case 3:
                cart.addOrder(new Order(filite.getName(), filite.getPrice()));
                break;
        }

        Menu menu = new Menu();
        menu.home(cart);
    }
}

class Cart {
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
class Order {
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