package main.java;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    protected int userAge;
    public List<Product> cart;
    // SER316 Task 2 SPOTBUGS FIX - removed cartStorage

    /**
     * Calculates the final cost after all savings and tax has been applied.
     * Also checks that the user is of age to purchase alcohol if it is in their
     * cart at checkout. Sales tax is always AZ tax.
     *
     * Calculation is based off of the following prices and deals: Dairy -> $3
     * Meat -> $10 Produce -> $2 or 3 for $5 Alcohol -> $8 Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     *
     * If there is an alcohol product in the cart and the user is under 21, then
     * an UnderAgeException should be thrown.
     *
     * @return double totalCost
     * @throws UnderAgeException
     */
    public double calcCost() throws UnderAgeException {
        double cost = 0;

        int produceCounter = 0;
        int alcoholCounter = 0;
        int frozenCounter = 0;

        for (Product product : cart) {
            cost += product.getCost();

            if (product instanceof Produce) {
                produceCounter++;

                if (produceCounter >= 3) {
                    cost -= 1;
                    produceCounter = 0;
                }
            } else if (product instanceof Alcohol) {
                alcoholCounter++;

                if (this.userAge < 21) {
                    throw new UnderAgeException("Cart Owner must be 21 or"
                            + " over to purchase alcohol");
                }

                if (alcoholCounter >= 1 && produceCounter >= 1) {
                    cost -= 3;
                    alcoholCounter--;
                    produceCounter--;
                }
            } else if (product instanceof FrozenFood) {
                frozenCounter++;

                if (alcoholCounter >= 1 && frozenCounter >= 1) {
                    cost -= 3;
                    alcoholCounter--;
                    frozenCounter--;
                }
            }
        }

        return cost + this.getTax(cost, "AZ");
    }

    // calculates how much was saved in the current shopping cart based on the
    // deals, returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: create white box
    // tests and fix the method, reach at least 98% coverage
    public int Amount_saved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;

        double produce_counter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;

        for (int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings += cart.get(i).getCost();

            if (cart.get(i) instanceof Produce) {
                produce_counter++;

                if (produce_counter >= 3) {
                    costAfterSavings -= 1;
                    produce_counter = 0;
                }
            } else if (cart.get(i) instanceof Alcohol) {
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            } else if (cart.get(i) instanceof FrozenFood) {
                frozenFoodCounter++;
            }

            // removed excess code that was tracking dairy counter

            if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                costAfterSavings -= 3; // added instead of subtracted
                alcoholCounter--;
                frozenFoodCounter--;
            }
        }

        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBT, String twoLetterUSStateAbbreviation) {
        double newTotal = 0;

        switch (twoLetterUSStateAbbreviation) {
            case "AZ":
                newTotal = totalBT * .08;
                break;
            case "CA":
                newTotal = totalBT * .09;
                break;
            case "NY":
                newTotal = totalBT * .1;
                break; // why isn't this here?
            case "CO":
                newTotal = totalBT * .07;
                break;
            default:
                return 0;
        }

        return newTotal;
    }

    public void addItem(Product np) {
        cart.add(np);
    }

    public boolean removeItem(Product productToRemove) {
        boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).equals(productToRemove)) {
                cart.remove(i);
                test = true;
                return test;
            }
        }
        return false;
    }

    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public List<Product> getCart() {
        return cart;
    }
}
