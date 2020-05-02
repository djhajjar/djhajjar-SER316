package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

    protected int userAge;
    public List<Product> cart;

    // SER316 Task 2 SPOTBUGS FIX - removed cartStorage

    /**
     * Calculates the final cost after all savings and tax has been applied.
     * Also checks that the user is of age to purchase alcohol if it is in their
     * cart at checkout. Sales tax is always AZ tax.
     *
     * Calculation is based off of the following prices and deals: <br>
     * Dairy -> $3 <br>
     * Meat -> $10 <br>
     * Produce -> $2 or 3 for $5 <br>
     * Alcohol -> $8 Frozen Food -> $5 <br>
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

        for (Product product : cart) {
            cost += product.getCost();

            if (product instanceof Alcohol && this.userAge < 21) {
                throw new UnderAgeException("Cart Owner must be 21 or over to purchase alcohol");
            }
        }

        cost -= this.Amount_saved();
        return cost + this.getTax(cost, "AZ");
    }

    // calculates how much was saved in the current shopping cart based on the
    // deals, returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: create white box
    // tests and fix the method, reach at least 98% coverage
    public int Amount_saved() throws UnderAgeException {
        int discount = 0;
        double produce_counter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) instanceof Produce) {
                produce_counter++;
            } else if (cart.get(i) instanceof Alcohol) {
                alcoholCounter++;
            } else if (cart.get(i) instanceof FrozenFood) {
                frozenFoodCounter++;
            }
        }

        discount += (int) Math.floor(produce_counter / 3);
        discount += Math.min(alcoholCounter, frozenFoodCounter) * 3;

        return discount;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBT, String twoLetterUSStateAbbreviation) {
        Map<String, Double> taxeCodes = new HashMap<String, Double>();
        taxeCodes.put("AZ", 0.08);
        taxeCodes.put("CA", 0.09);
        taxeCodes.put("NY", 0.1);
        taxeCodes.put("CO", 0.07);

        return taxeCodes.getOrDefault(twoLetterUSStateAbbreviation, 0.0);
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
