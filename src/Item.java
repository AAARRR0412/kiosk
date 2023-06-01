public class Item extends Menu {
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