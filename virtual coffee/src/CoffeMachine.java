import java.util.Scanner;

public class CoffeMachine {
    final int esWater = 250;
    final int esCoffee = 16;
    final int esCost = 4;
    final int laWater = 350;
    final int laMilk = 75;
    final int laCoffe = 20;
    final int laCost = 7;
    final int capWater = 200;
    final int capMilk = 100;
    final int capCoffe = 12;
    final int capCost = 6;
    int m_water = 400;
    int m_milk = 540;
    int m_coffe = 120;
    int m_cup = 9;
    int money = 550;
    String msg = "";
    boolean userIn = true;


    private void UserAction(String action) {

        while (userIn) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            switch (action) {
                case "fill":
                    FillMachine();
                    break;
                case "buy":
                    BuyACoffe();
                    break;
                case "take":
                    GiveMonye();
                    break;
                case "remaining":
                    PrintState();
                    break;
                case "exit":
                    userIn = false;


            }
        }


    }

    private void GiveMonye() {
        System.out.println("I gave you $" + money);
        money = 0;
    }


//    private  void CheckAmount(int cup, int cups) {
//
//
//        if (cup == cups) {
//            System.out.println("Yes, I can make that amount of coffee");
//        } else if (cup < cups) {
//            System.out.println("No, I can make only " + cup + " cup(s) of coffee");
//        } else if (cup > cups) {
//            System.out.println("Yes, I can make that amount of coffee (and even " + (cup - cups) + " more than that)");
//        }
//    }

    private void PrintState() {
        System.out.println("The coffee machine has:");
        System.out.println(m_water + " of water");
        System.out.println(m_milk + " of milk");
        System.out.println(m_coffe + " of coffee beans");
        System.out.println(m_cup + " of disposable cups");
        System.out.println(money + " of money");

    }

    private void FillMachine(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add:  ");
        m_water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:  ");
        m_milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        m_coffe += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add");
        m_cup += scanner.nextInt();
    }


    //region BuyACoffe
    private void BuyACoffe(Scanner scanner) {

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String st = scanner.nextLine();
        if (st.equals("back")) {
            UserAction();
            return;
        }
        int c = Integer.parseInt(st);
        switch (c) {
            case 1:
                if (Coffee(esWater, esCoffee, esCost)) {
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, not enough " + msg + "!");
                }

                break;
            case 2:
                if (Coffee(laWater, laCoffe, laCost, laMilk)) {
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, not enough " + msg + "!");
                }
                break;
            case 3:
                if (Coffee(capWater, capCoffe, capCost, capMilk)) {
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, not enough " + msg + "!");
                }
                break;
        }
    }

    private Boolean Coffee(int water, int coffee, int cost, int milk) {
        if (m_water >= water) {

            if (m_milk >= milk) {

                if (m_coffe >= coffee) {

                    if (m_cup >= 1) {
                        m_water -= water;
                        m_coffe -= coffee;
                        m_milk -= milk;
                        money += cost;
                        m_cup -= 1;
                        return true;
                    } else {
                        msg += "Cups";
                        return false;
                    }
                } else {
                    msg += "Coffee";
                    return false;
                }

            } else {
                msg += "Milk";
                return false;
            }

        } else {
            msg += "Water";
            return false;
        }
    }


    private Boolean Coffee(int _water, int _coffee, int _cost) {
        return Coffee(_water, _coffee, _cost, 0);
    }
    //endregion
}
