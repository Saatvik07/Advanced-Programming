import java.util.*;

import org.w3c.dom.NameList;

public class Assignment2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        App zotato = new App();
        while (true) {
            System.out.println("Welcome to Zotato:");
            System.out.println("\t1) Enter as Restaurant Owner");
            System.out.println("\t2) Enter as Customer");
            System.out.println("\t3)Check User Details");
            System.out.println("\t4)Company Account details");
            System.out.println("\t5)Exit");
            int menu1Choice = input.nextInt();
            input.nextLine();
            if (menu1Choice == 1) {
                zotato.displayAllRestaurants();
                int restaurantNumber = input.nextInt();
                input.nextLine();
                Restaurant restaurantSelected = zotato.selectARestaurant(restaurantNumber - 1);
                restaurantSelected.showGreeting();
                while (true) {
                    System.out.println("\t1) Add item");
                    System.out.println("\t2) Edit item");
                    System.out.println("\t3)Print Rewards");
                    System.out.println("\t4)Discount on bill value");
                    System.out.println("\t5)Exit");
                    int menu2choice = input.nextInt();
                    input.nextLine();
                    if (menu2choice == 1) {
                        System.out.println("Enter food item details");
                        System.out.println("Food Name:");
                        String newDishName = input.nextLine();
                        System.out.println("Item Price:");
                        int newDishPrice = input.nextInt();
                        System.out.println("Item Quantity:");
                        int newDishQuantity = input.nextInt();
                        System.out.println("Item Category:");
                        String newDishCategory = input.nextLine();
                        System.out.println("Offer:");
                        int newDishOffer = input.nextInt();
                        input.nextLine();
                        FoodItem newDish = new FoodItem(newDishName, newDishPrice, newDishOffer, newDishQuantity,
                                newDishCategory);
                        newDish.showDetails();
                        restaurantSelected.addDishToMenu(newDish);

                    } else if (menu2choice == 2) {
                        System.out.println("Choose item by code");
                        restaurantSelected.displayAllDishes();
                        int dishNumber = input.nextInt();
                        input.nextLine();
                        System.out.println("Choose an attribute to edit:");
                        System.out.println("1) Name");
                        System.out.println("2) Price");
                        System.out.println("3) Quantity");
                        System.out.println("4) Category");
                        System.out.println("5) Offer");
                        int attributeNumber = input.nextInt();
                        input.nextLine();
                        restaurantSelected.updateMenuDish(dishNumber, attributeNumber);

                    } else if (menu2choice == 3) {

                    } else if (menu2choice == 4) {

                    } else {
                        break;
                    }
                }
            } else if (menu1Choice == 2) {
                zotato.displayAllCustomers();
            }
        }
    }
}

class App {
    protected ArrayList<Restaurant> restaurantList;
    protected ArrayList<Customer> customerList;
    private canBeLoggedInto dummyRestaurantandCustomer; // defined for calling methods in the canBeLoggedInto interface
    private int restaurantRoyalty;
    private int deliveryFeesTotal;

    public App() {
        restaurantRoyalty = 0;
        deliveryFeesTotal = 0;
    }

    public void displayAllRestaurants() {
        this.dummyRestaurantandCustomer = new Restaurant();
        this.dummyRestaurantandCustomer.showAll();
    }

    public void displayAllCustomers() {
        this.dummyRestaurantandCustomer = new Customer();
        this.dummyRestaurantandCustomer.showAll();
    }

    public Restaurant selectARestaurant(int index) {
        return this.restaurantList.get(index);
    }
}

class Restaurant extends App implements canBeLoggedInto {
    protected String name;
    protected ArrayList<FoodItem> menu;
    protected int rewards;

    public Restaurant(String name) {
        this.name = name;
        this.rewards = 0;
        menu = new ArrayList<FoodItem>();
    }

    public Restaurant() {

    }

    public void addDishToMenu(FoodItem dish) {
        this.menu.add(dish);
    }

    @Override
    public void showGreeting() {
        System.out.println("Welcome " + this.name);
    }

    public void showAll() {
        for (int i = 0; i < this.restaurantList.size(); i++) {
            System.out.print(i + 1 + ") " + this.restaurantList.get(i).name);
            if (this.restaurantList.get(i) instanceof AuthenticRestaurant) {
                System.out.print("(Authentic)\n");
            } else if (this.restaurantList.get(i) instanceof FastFoodRestaurant) {
                System.out.print("(Fast Food)\n");
            } else {
                System.out.print("\n");
            }
        }
    }

    public void displayAllDishes() {
        for (int i = 0; i < this.menu.size(); i++) {
            System.out.println(this.menu.get(i).getUID() + " " + this.name + " - " + this.menu.get(i).getName() + " "
                    + this.menu.get(i).getPrice() + " " + this.menu.get(i).getQuantity() + " "
                    + this.menu.get(i).getDiscount() + "% off " + this.menu.get(i).getCategory());
        }
    }

    public FoodItem getADish(int UID) {
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).getUID() == UID) {
                return this.menu.get(i);
            }
        }
        return this.menu.get(0);

    }

    public void updateMenuDish(int UID, int attributeNumber) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).getUID() == UID) {
                FoodItem dish = this.menu.get(i);
                if (attributeNumber == 1) {
                    System.out.println("Enter the new Name");
                    String newName = input.nextLine();
                } else if (attributeNumber == 2) {
                    restaurantSelected.updateMenuItem(dishNumber, 2);
                } else if (attributeNumber == 3) {
                    restaurantSelected.updateMenuItem(dishNumber, 3);
                } else if (attributeNumber == 4) {
                    restaurantSelected.updateMenuItem(dishNumber, 4);
                } else {

                }
            }
        }
    }

}

class AuthenticRestaurant extends Restaurant implements hasOverallDiscount {
    private int overallDiscount;

    public AuthenticRestaurant(String name) {
        super(name);
    }

    @Override
    public void setOverallDiscount(int value) {
        this.overallDiscount = value;
    }

    public int getOverallDiscount() {
        return this.overallDiscount;
    }
}

class FastFoodRestaurant extends Restaurant implements hasOverallDiscount {
    private int overallDiscount;

    public FastFoodRestaurant(String name) {
        super(name);
    }

    @Override
    public void setOverallDiscount(int value) {
        this.overallDiscount = value;
    }

    public int getOverallDiscount() {
        return this.overallDiscount;
    }
}

interface hasOverallDiscount {
    void setOverallDiscount(int value);

    int getOverallDiscount();
}

interface canBeLoggedInto {
    void showGreeting();

    void showAll();
}

interface hasToPayDeliveryFees {
    void setDeliveryFees();
}

class Customer extends App implements canBeLoggedInto, hasToPayDeliveryFees {
    protected String name;
    protected int rewards;
    protected int walletMoney;
    protected ArrayList<FoodItemInCart> cart;
    protected ArrayList<Order> orderList;
    private int deliveryFees;

    public Customer(String name) {
        this.name = name;
        this.rewards = 0;
        this.walletMoney = 1000;
        this.cart = new ArrayList<FoodItemInCart>();
        this.orderList = new ArrayList<Order>();
    }

    public Customer() {
        // Had to declare explicitly due to inheritance

    }

    public void showGreeting() {
        System.out.println("Welcome " + this.name);
    }

    @Override
    public void showAll() {
        for (int i = 0; i < this.customerList.size(); i++) {
            System.out.print(i + 1 + ") " + this.customerList.get(i).name);
            if (this.customerList.get(i) instanceof EliteCustomer) {
                System.out.print("(Elite)\n");
            } else if (this.customerList.get(i) instanceof SpecialCustomer) {
                System.out.print("(Special)\n");
            } else {
                System.out.print("\n");
            }
        }
    }

    @Override
    public void setDeliveryFees() {
        this.deliveryFees = 40;
    }
}

class SpecialCustomer extends Customer {
    public SpecialCustomer(String name) {
        super(name);
    }
}

class EliteCustomer extends Customer {

    public EliteCustomer(String name) {
        super(name);
    }
}

class Order {
    private int amountToPay;
    private ArrayList<FoodItemInCart> dishes;
    private int deliveryCharges;

    public Order(ArrayList<FoodItemInCart> dishes, int amountToPay, int deliveryCharges) {
        this.dishes = dishes;
        this.amountToPay = amountToPay;
        this.deliveryCharges = deliveryCharges;
    }
}

class FoodItem {
    static int count = 1;
    private int UID;
    private String name;
    private int price;
    private int discount;
    private int quantity;
    private String category;

    public FoodItem(String name, int price, int discount, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.quantity = quantity;
        this.UID = count;
        count++;
    }

    public FoodItem() {

    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getDiscount() {
        return this.discount;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getCategory() {
        return this.category;
    }

    public int getUID() {
        return this.UID;
    }

    public void showDetails() {
        System.out.println(this.getUID() + " " + this.getName() + " " + this.getPrice() + " " + this.getQuantity() + " "
                + this.getDiscount() + "% off " + this.getCategory());
    }
}

class FoodItemInCart extends FoodItem {
    String restaurantName;

    public FoodItemInCart(String name, int price, int discount, int quantity, String category, String restaurant) {
        super(name, price, discount, quantity, category);
        this.restaurantName = restaurant;
    }

}