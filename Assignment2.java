import java.util.*;

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
                System.out.println("Choose Restaurant");
                zotato.displayAll(new Restaurant());
                int restaurantNumber = input.nextInt();
                input.nextLine();
                Restaurant restaurantSelected = zotato.selectARestaurant(restaurantNumber - 1);

                while (true) {
                    restaurantSelected.showGreeting();
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
                        input.nextLine();
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
                        System.out.println("Reward Points " + restaurantSelected.getRewardPoints());
                    } else if (menu2choice == 4) {
                        if (restaurantSelected instanceof FastFoodRestaurant
                                || restaurantSelected instanceof AuthenticRestaurant) {
                            System.out.print("Enter the overall bill discount: ");
                            int discount = input.nextInt();
                            input.nextLine();
                            restaurantSelected.setOverallDiscount(discount);
                        } else {
                            System.out.println("Regular restaurants cannot have overall bill discounts");
                        }
                    } else {
                        break;
                    }
                }
            } else if (menu1Choice == 2) {
                zotato.displayAll(new Customer());
                int customerNumber = input.nextInt();
                input.nextLine();
                Customer customerSelected = zotato.selectACustomer(customerNumber - 1);

                Restaurant restaurantSelected = new Restaurant();
                while (true) {
                    customerSelected.showGreeting();
                    System.out.println("\t1) Select Restaurant");
                    System.out.println("\t2) Checkout Cart");
                    System.out.println("\t3) Rewards Won");
                    System.out.println("\t4)Print recent orders");
                    System.out.println("\t5)Exit");
                    int menu3choice = input.nextInt();
                    input.nextLine();
                    if (menu3choice == 1) {
                        System.out.println("Choose Restaurant");
                        zotato.displayAll(new Restaurant());
                        int restaurantNumber = input.nextInt();
                        input.nextLine();
                        restaurantSelected = zotato.selectARestaurant(restaurantNumber - 1);
                        System.out.println("Choose item by code");
                        restaurantSelected.displayAllDishes();
                        int dishNumber = input.nextInt();
                        input.nextLine();
                        FoodItem dishSelected = restaurantSelected.getADish(dishNumber);
                        System.out.println("Enter item quantity -");
                        int quantity = input.nextInt();
                        input.nextLine();
                        FoodItemInCart newItemInCart = new FoodItemInCart(dishSelected.getUID(), dishSelected.getName(),
                                dishSelected.getPrice(), dishSelected.getDiscount(), quantity,
                                dishSelected.getCategory(), restaurantSelected.getName());
                        customerSelected.updateCart(newItemInCart, restaurantSelected);

                    } else if (menu3choice == 2) {
                        customerSelected.showCart();
                        float totalAmount = customerSelected.calculateTotalBill(restaurantSelected);
                        int deliveryCharges;
                        if (customerSelected instanceof EliteCustomer) {
                            deliveryCharges = 0;
                        } else {
                            deliveryCharges = customerSelected.getDeliveryFess();
                        }
                        System.out.println("\t1)Proceed to checkout");
                        int choice = input.nextInt();
                        input.nextLine();
                        if (choice == 1) {
                            int success = customerSelected.showConfirmation(totalAmount, deliveryCharges,
                                    restaurantSelected);
                            if (success == 1) {
                                zotato.addRestaurantRoyalty((float) (totalAmount - deliveryCharges) / 100);
                                zotato.addDeliveryFees(deliveryCharges);
                            }

                        }
                    } else if (menu3choice == 3) {
                        System.out.println("Reward Points " + customerSelected.getRewards());
                    } else if (menu3choice == 4) {
                        customerSelected.showRecentOrders();
                    } else {
                        break;
                    }
                }
            } else if (menu1Choice == 3) {
                System.out.println("You want to check the details of ?");
                System.out.println("1) Customer");
                System.out.println("2) Restaurant");
                int menu4Choice = input.nextInt();
                input.nextLine();
                if (menu4Choice == 1) {
                    System.out.println("Choose a customer");
                    System.out.println("");
                    zotato.displayAll(new Customer());
                    int customerNumber = input.nextInt();
                    input.nextLine();
                    Customer customerSelected = zotato.selectACustomer(customerNumber - 1);
                    customerSelected.showDetails();
                } else if (menu4Choice == 2) {
                    System.out.println("Choose a restaurant");
                    zotato.displayAll(new Restaurant());
                    int restaurantNumber = input.nextInt();
                    input.nextLine();
                    Restaurant restaurantSelected = zotato.selectARestaurant(restaurantNumber - 1);
                    restaurantSelected.showDetails();
                }
            } else if (menu1Choice == 4) {
                System.out.println("Total Company Balance - INR " + zotato.getTotal());
                System.out.println("Total delivery charges collected - INR" + zotato.getTotalDeliveryFess());
            } else {
                break;
            }
        }
    }
}

class App {
    protected ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
    protected ArrayList<Customer> customerList;
    private canBeLoggedInto dummyRestaurantandCustomer; // defined for calling methods in the canBeLoggedInto interface
    private float restaurantRoyalty;
    private int deliveryFeesTotal;

    public App() {
        this.customerList = createCustomers();
        this.restaurantList = createRestaurants();
        restaurantRoyalty = 0;
        deliveryFeesTotal = 0;
    }

    public ArrayList<Restaurant> createRestaurants() {
        ArrayList<Restaurant> array = new ArrayList<>();
        array.add(new AuthenticRestaurant("Shah", "New Delhi"));
        array.add(new Restaurant("Ravi's", "Mumbai"));
        array.add(new AuthenticRestaurant("The Chinese", "Chennai"));
        array.add(new FastFoodRestaurant("Wang's", "New Delhi"));
        array.add(new Restaurant("Paradise", "Hyderabad"));
        return array;
    }

    public ArrayList<Customer> createCustomers() {
        ArrayList<Customer> array = new ArrayList<>();
        array.add(new EliteCustomer("Ram", "Mumbai"));
        array.add(new EliteCustomer("Sam", "New Delhi"));
        array.add(new SpecialCustomer("Tim", "Hyderabad"));
        array.add(new Customer("Kim", "Hyderabad"));
        array.add(new Customer("Jim", "Chennai"));
        return array;
    }

    public void displayAll(canBeLoggedInto object) {
        this.dummyRestaurantandCustomer = object;
        this.dummyRestaurantandCustomer.showAll(this);
    }

    public Restaurant selectARestaurant(int index) {
        return this.restaurantList.get(index);
    }

    public Customer selectACustomer(int index) {
        return this.customerList.get(index);
    }

    public void addRestaurantRoyalty(float royalty) {
        this.restaurantRoyalty += royalty;
    }

    public void addDeliveryFees(int fees) {
        this.deliveryFeesTotal += fees;
    }

    public int getTotalDeliveryFess() {
        return this.deliveryFeesTotal;
    }

    public float getTotal() {
        return this.restaurantRoyalty;
    }

}

class Restaurant implements canBeLoggedInto, hasOverallDiscount, hasRestaurantDiscount {
    protected String name;
    protected ArrayList<FoodItem> menu;
    protected int rewards;
    protected int overallDiscount;
    protected String address;
    protected int totalOrders;

    public Restaurant(String name, String address) {
        this.totalOrders = 0;
        this.name = name;
        this.rewards = 0;
        menu = new ArrayList<FoodItem>();
        this.overallDiscount = 0;
        this.address = address;
    }

    public Restaurant() {

    }

    public void showDetails() {
        System.out.println(this.name + " " + this.address + " " + this.totalOrders);

    }

    public int getRewardPoints() {
        return this.rewards;
    }

    public void addDishToMenu(FoodItem dish) {
        this.menu.add(dish);
    }

    @Override
    public void showGreeting() {
        System.out.println("Welcome " + this.name);
    }

    public void showAll(App zotato) {
        for (int i = 0; i < zotato.restaurantList.size(); i++) {
            System.out.print(i + 1 + ") " + zotato.restaurantList.get(i).name);
            if (zotato.restaurantList.get(i) instanceof AuthenticRestaurant) {
                System.out.print("(Authentic)\n");
            } else if (zotato.restaurantList.get(i) instanceof FastFoodRestaurant) {
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

    public void setRewards(int rewards) {
        this.rewards += rewards;
    }

    public void updateMenuDish(int UID, int attributeNumber) {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).getUID() == UID) {
                FoodItem dish = this.menu.get(i);
                if (attributeNumber == 1) {
                    System.out.println("Enter the new name");
                    String newName = input.nextLine();
                    dish.setName(newName);
                } else if (attributeNumber == 2) {
                    System.out.println("Enter the new price");
                    int newPrice = input.nextInt();
                    input.nextLine();
                    dish.setPrice(newPrice);
                } else if (attributeNumber == 3) {
                    System.out.println("Enter the new quantity");
                    int newQuantity = input.nextInt();
                    input.nextLine();
                    dish.setQuantity(newQuantity);
                } else if (attributeNumber == 4) {
                    System.out.println("Enter the new category");
                    String newCategory = input.nextLine();
                    dish.setCategory(newCategory);
                } else {
                    System.out.println("Enter the new offer");
                    int newDiscount = input.nextInt();
                    input.nextLine();
                    dish.setDiscount(newDiscount);
                }
                System.out.println(dish.getUID() + " " + this.name + " - " + dish.getName() + " " + dish.getPrice()
                        + " " + dish.getQuantity() + " " + dish.getDiscount() + "% off " + dish.getCategory());
            }
        }
    }

    public void updateDishQuantity(int UID, int ordered) {
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).getUID() == UID) {
                this.menu.get(i).setQuantity(this.menu.get(i).getQuantity() - ordered);
            }
        }
        this.totalOrders += 1;
    }

    public int getQuantityOfADish(int UID) {
        for (int i = 0; i < this.menu.size(); i++) {
            if (this.menu.get(i).UID == UID) {
                return this.menu.get(i).quantity;
            }
        }
        return 0;
    }

    @Override
    public float addRestaurantDiscount(float total) {
        return total;
    }

    public void setOverallDiscount(int val) {
        this.overallDiscount = val;
    }

    public int getOverallDiscount() {
        return this.overallDiscount;
    }

    public String getName() {
        return this.name;
    }

}

class AuthenticRestaurant extends Restaurant implements hasRestaurantDiscount {

    public AuthenticRestaurant(String name, String address) {
        super(name, address);
    }

    @Override
    public float addRestaurantDiscount(float total) {
        if (total > 200) {
            return total - 50;
        }
        return total;
    }

    @Override
    public void showDetails() {
        System.out.println(this.name + "(Authentic) " + this.address + " " + this.totalOrders);

    }
}

class FastFoodRestaurant extends Restaurant {
    public FastFoodRestaurant(String name, String address) {
        super(name, address);
    }

    @Override
    public void showDetails() {
        System.out.println(this.name + "(Fast Food) " + this.address + " " + this.totalOrders);
    }
}

class Customer implements canBeLoggedInto, hasToPayDeliveryFees, hasCustomerDiscount {
    protected String name;
    protected String address;
    protected float rewards;
    protected float walletMoney;
    protected ArrayList<FoodItemInCart> cart;
    protected ArrayList<Order> orderList;

    public Customer(String name, String address) {
        this.address = address;
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
        System.out.println("Customer Menu");
    }

    public float getRewards() {
        return this.rewards;
    }

    public void showRecentOrders() {
        for (int i = this.orderList.size() - 1; i >= 0; i--) {
            Order singleOrder = this.orderList.get(i);
            singleOrder.showDetails();
        }
    }

    @Override
    public void showAll(App zotato) {
        for (int i = 0; i < zotato.customerList.size(); i++) {
            System.out.print(i + 1 + ") " + zotato.customerList.get(i).name);
            if (zotato.customerList.get(i) instanceof EliteCustomer) {
                System.out.print("(Elite)\n");
            } else if (zotato.customerList.get(i) instanceof SpecialCustomer) {
                System.out.print("(Special)\n");
            } else {
                System.out.print("\n");
            }
        }
    }

    public void showCart() {
        System.out.println("Items in Cart -");
        for (int i = 0; i < this.cart.size(); i++) {
            System.out.println(this.cart.get(i).getUID() + " " + this.cart.get(i).getRestaurantName() + " - "
                    + this.cart.get(i).getName() + " - " + this.cart.get(i).getPrice() + " - "
                    + this.cart.get(i).getQuantity() + " - " + this.cart.get(i).getDiscount() + "% off");
        }
    }

    public void updateCart(FoodItemInCart newItem, Restaurant restaurantSelected) {
        if (newItem.getQuantity() > restaurantSelected.getQuantityOfADish(newItem.getUID())) {
            System.out.println("Sorry, the restaurant is short on this dish :(");
            return;
        }
        for (int i = 0; i < this.cart.size(); i++) {
            if (!this.cart.get(i).getRestaurantName().equals(newItem.getRestaurantName())) {
                System.out.println("You cannot add dishes from two different restaurants");
                System.out.println(
                        "The dishes from current restaurant will be added to the cart and the your previous order will be discarded");
                this.cart.clear();
                this.cart.add(newItem);
                return;
            }

        }
        this.cart.add(newItem);
        System.out.println("Items added to cart");
    }

    public float calculateTotalBill(Restaurant restaurantSelected) {
        float totalAmount = 0;
        for (int i = 0; i < this.cart.size(); i++) {
            FoodItemInCart dish = this.cart.get(i);
            totalAmount += ((dish.getPrice() * dish.getQuantity())
                    - ((float) dish.getDiscount() / 100) * (dish.getPrice() * dish.getQuantity()));
        }
        if (restaurantSelected instanceof FastFoodRestaurant || restaurantSelected instanceof AuthenticRestaurant) {
            totalAmount -= totalAmount * ((float) restaurantSelected.getOverallDiscount() / 100);
        }
        if (restaurantSelected instanceof AuthenticRestaurant) {
            totalAmount = restaurantSelected.addRestaurantDiscount(totalAmount);
        }
        if (this instanceof EliteCustomer || this instanceof SpecialCustomer) {
            totalAmount = this.addCustomerDiscount(totalAmount);
        }
        if (this instanceof EliteCustomer) {
            System.out.println("Delivery Charges - 0/-");
            System.out.println("Total order value - INR " + totalAmount + "/-");
        } else {
            System.out.println("Delivery Charges - " + this.getDeliveryFess() + "/-");
            totalAmount = this.addDeliveryFees(totalAmount);
            System.out.println("Total order value - INR " + totalAmount + "/-");
        }
        return totalAmount;
    }

    public int deductMoney(float totalAmount) {
        if (this.rewards > 0) {
            if (totalAmount >= this.rewards) {
                totalAmount -= this.rewards;
                rewards = 0;
            } else {
                this.rewards -= totalAmount;
                totalAmount = 0;
                return 0;
            }
        }
        if (totalAmount > 0 && this.walletMoney >= totalAmount) {
            this.walletMoney -= totalAmount;
            return 1;
        } else {
            System.out.println("Insufficient balance in wallet");
            return 0;
        }
    }

    public void calculateRewards(float totalAmount, Restaurant restaurantSelected) {
        if (restaurantSelected instanceof FastFoodRestaurant) {
            this.rewards = this.rewards + 10 * (int) (totalAmount / 150);
            restaurantSelected.setRewards(10 * (int) (totalAmount / 150));
        } else if (restaurantSelected instanceof AuthenticRestaurant) {
            this.rewards = this.rewards + 25 * (int) (totalAmount / 200);
            restaurantSelected.setRewards(25 * (int) (totalAmount / 200));
        } else {
            this.rewards = this.rewards + 5 * (int) (totalAmount / 100);
            restaurantSelected.setRewards(5 * (int) (totalAmount / 100));
        }
    }

    public int showConfirmation(float totalAmount, int deliveryCharges, Restaurant restaurantSelected) {
        int totalQuantity = 0;
        int success = this.deductMoney(totalAmount);
        if (success == 1) {
            for (int i = 0; i < this.cart.size(); i++) {
                totalQuantity += this.cart.get(i).getQuantity();
                restaurantSelected.updateDishQuantity(this.cart.get(i).getUID(), this.cart.get(i).getQuantity());

            }
            System.out.println(totalQuantity + " items successfully bought for INR " + totalAmount);
            Order newOrder = new Order(this.cart, totalAmount, deliveryCharges);
            this.orderList.add(newOrder);

            this.calculateRewards(totalAmount, restaurantSelected);
            return 1;
        }
        return 0;

    }

    public void showDetails() {
        System.out.println(this.name + " " + this.address + " " + this.walletMoney);
    }

    @Override
    public float addDeliveryFees(float total) {
        return total + 40;
    }

    @Override
    public int getDeliveryFess() {
        // TODO Auto-generated method stub
        return 40;
    }

    @Override
    public float addCustomerDiscount(float total) {
        return total;
    }
}

class SpecialCustomer extends Customer implements hasToPayDeliveryFees, hasCustomerDiscount {
    public SpecialCustomer(String name, String address) {
        super(name, address);
    }

    @Override
    public float addDeliveryFees(float total) {
        return total + 20;
    }

    @Override
    public int getDeliveryFess() {
        return 20;
    }

    @Override
    public float addCustomerDiscount(float total) {
        if (total > 200) {
            return total - 25;
        }
        return total;
    }

    @Override
    public void showDetails() {
        System.out.println(this.name + " (Special) " + this.address + " " + this.walletMoney);
    }
}

class EliteCustomer extends Customer implements hasCustomerDiscount {

    public EliteCustomer(String name, String address) {
        super(name, address);
    }

    @Override
    public float addCustomerDiscount(float total) {
        if (total > 200) {
            return total - 25;
        }
        return total;
    }

    @Override
    public void showDetails() {
        System.out.println(this.name + " (Elite) " + this.address + " " + this.walletMoney);
    }

}

class Order {
    private float amountToPay;
    private ArrayList<FoodItemInCart> dishes;
    private int deliveryCharges;

    public Order(ArrayList<FoodItemInCart> dishes, float amountToPay, int deliveryCharges) {
        this.dishes = dishes;
        this.amountToPay = amountToPay;
        this.deliveryCharges = deliveryCharges;
    }

    public void showDetails() {
        System.out.println("Order: ");
        for (int i = 0; i < this.dishes.size(); i++) {
            this.dishes.get(i).showDetails();
        }
        System.out.println("Delivery Charges: " + this.deliveryCharges);
        System.out.println("Total amount: " + this.amountToPay);
        System.out.println("\n");
    }
}

class FoodItem {
    static int count = 1;
    protected int UID;
    protected String name;
    protected int price;
    protected int discount;
    protected int quantity;
    protected String category;

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

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public void setDiscount(int newDiscount) {
        this.discount = newDiscount;
    }

    public void setCategory(String newCategory) {
        this.category = newCategory;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }
}

class FoodItemInCart extends FoodItem {
    private String restaurantName;

    public FoodItemInCart(int UID, String name, int price, int discount, int quantity, String category,
            String restaurant) {
        this.UID = UID;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.category = category;
        this.restaurantName = restaurant;
    }

    public String getRestaurantName() {
        return this.restaurantName;
    }

}

interface hasOverallDiscount {
    void setOverallDiscount(int value);

    int getOverallDiscount();
}

interface hasCustomerDiscount {
    float addCustomerDiscount(float total);
}

interface canBeLoggedInto {
    void showGreeting();

    void showAll(App zotato);
}

interface hasToPayDeliveryFees {
    float addDeliveryFees(float total);

    int getDeliveryFess();
}

interface hasRestaurantDiscount {
    float addRestaurantDiscount(float total);
}