import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    String name; // 이름
    String explain; // 설명
    ArrayList<Menu> menuList = new ArrayList<>();

    public String toString() {
        return name + " | " + explain;
    }

    Menu() {
        menuList.add(new Menu("Burgers","앵거스 비프 통살을 다져만든 버거"));
        menuList.add(new Menu("Forzen Custard","매장에서 신선하게 만드는 아이스크림"));
        menuList.add(new Menu("Drinks","매장에서 직접 만드는 음료"));
        menuList.add(new Menu("Beer","뉴욕 브루클린 브루어리에서 양조한 맥주"));
    }
    Menu(String a, String b) {
        this.name = a;
        this.explain = b;
    } // 기본 생성자

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
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println((i + 1) + ". " + menuList.get(i).toString());
        }
        System.out.println("");
        System.out.println("[ ORDER MENU ]");
        System.out.println("5. Order | 장바구니를 확인 후 주문합니다. ");
        System.out.println("6. Cancel | 진행중인 주문을 취소합니다.");

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