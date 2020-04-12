package test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.*;

public class CartTest {
	Cart cart1, cart2;
	double cart1Expected, cart2Expected, test3Expected, test4Expected;

	private void addItemsToCart(Cart cart, int dairy, int meat, int produce, int alcohol, int forzenFood) {
		for (int i = 0; i < dairy; i++)
			cart.addItem(new Dairy());

		for (int i = 0; i < meat; i++)
			cart.addItem(new Meat());

		for (int i = 0; i < produce; i++)
			cart.addItem(new Produce());

		for (int i = 0; i < alcohol; i++)
			cart.addItem(new Alcohol());

		for (int i = 0; i < forzenFood; i++)
			cart.addItem(new FrozenFood());
	}

	@Before
    public void setUp() throws Exception {
        cart1 = new Cart(45);
        cart2 = new Cart(20);

        addItemsToCart(cart1, 1, 1, 3, 1, 1);
        addItemsToCart(cart2, 1, 1, 3, 1, 1);

        cart1Expected = 4;
        cart2Expected = 0; // Error expected
        test3Expected = 30.24;
        test4Expected = 0; // Error expected
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void amountSavedNoErrors() throws UnderAgeException {
		double saved = cart1.Amount_saved();
		assertEquals(cart1Expected, saved, 0.1);
	}

	@Test
	public void amountSavedException() throws UnderAgeException {
		double saved = cart2.Amount_saved();
		assertEquals(cart2Expected, saved, 0.1);
	}

	@Test
	public void calcCostTest() throws UnderAgeException {
		double saved = cart1.calcCost();
		assertEquals(test3Expected, saved, 0.1);
	}

	@Test
	public void calcCostError() throws UnderAgeException {
		double saved = cart2.calcCost();
		assertEquals(test4Expected, saved, 0.1);
	}

	@Test
	public void taxTest() {
		assertEquals(.08, cart1.getTax(1.0, "AZ"), 0.1);
		assertEquals(.09, cart1.getTax(1.0, "CA"), 0.1);
		assertEquals(.1, cart1.getTax(1.0, "NY"), 0.1);
		assertEquals(.07, cart1.getTax(1.0, "CO"), 0.1);
		assertEquals(1, cart1.getTax(1.0, "LA"), 0.1);
	}

	@Test
	public void RemoveItemTest() {
		assertTrue(cart1.RemoveItem(new FrozenFood()));
		assertFalse(cart1.RemoveItem(new FrozenFood()));
	}
}
