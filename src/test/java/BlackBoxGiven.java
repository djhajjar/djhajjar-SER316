package test.java;

import main.java.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import main.java.Cart;
import main.java.Cart1;
import main.java.Cart2;
import main.java.Cart3;
import main.java.Cart4;
import main.java.Cart5;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

	private Class<Cart> classUnderTest;

	@SuppressWarnings("unchecked")
	public BlackBoxGiven(Object classUnderTest) {
		this.classUnderTest = (Class<Cart>) classUnderTest;
	}

	// Define all classes to be tested
	@Parameterized.Parameters
	public static Collection<Object[]> cartClassUnderTest() {
		Object[][] classes = { { Cart0.class }, { Cart1.class }, { Cart2.class }, { Cart3.class }, { Cart4.class },
				{ Cart5.class } };
		return Arrays.asList(classes);
	}

	private Cart createCart(int age) throws Exception {
		Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
		return constructor.newInstance(age);
	}

	Cart cart1, cart2, cart3, cart4, cart5, cart6, cart7, cart8, cart9, cart10, cart11, cart12, cart13, cart14, cart15,
			cart16, cart17, cart18;
	double cart1Expected, cart2Expected, cart3Expected, cart4Expected, cart5Expected, cart6Expected, cart7Expected,
			cart8Expected, cart9Expected, cart10Expected, cart11Expected, cart12Expected, cart13Expected,
			cart14Expected, cart15Expected, cart16Expected, cart17Expected, cart18Expected;
	double cart2b, cart2c, cart2d, cart2e;

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

	@org.junit.Before
	public void setUp() throws Exception {
		cart1 = createCart(40); // Empty
		cart2 = createCart(20); // Dairy, also underage
		cart3 = createCart(40); // Meat
		cart4 = createCart(40); // Produce
		cart5 = createCart(40); // Alcohol
		cart6 = createCart(20); // underage
		cart7 = createCart(40); // frozen food only
		cart8 = createCart(40); // one of each
		cart9 = createCart(40); // 2 produce
		cart10 = createCart(40); // 3 produce
		cart11 = createCart(40); // 4 produce
		cart12 = createCart(40); // all deals -> 3 produce, 1 of everything else
		cart13 = createCart(21); // just of age
		cart14 = createCart(22); // 2 alcohol, 2 frozen food
		cart15 = createCart(22); // 2 alcohol, 1 frozen food
		cart16 = createCart(23); // 1 alcohol, 2 frozen food
		cart17 = createCart(40); // 6 produce
		cart18 = createCart(20); // underage, 1 alcohol, 1 frozen

		addItemsToCart(cart1, 0, 0, 0, 0, 0);
		addItemsToCart(cart2, 1, 0, 0, 0, 0);
		addItemsToCart(cart3, 0, 1, 0, 0, 0);
		addItemsToCart(cart4, 0, 0, 1, 0, 0);
		addItemsToCart(cart5, 0, 0, 0, 1, 0);
		addItemsToCart(cart6, 0, 0, 0, 1, 0);
		addItemsToCart(cart7, 0, 0, 0, 0, 1);
		addItemsToCart(cart8, 1, 1, 1, 1, 1);
		addItemsToCart(cart9, 0, 0, 2, 0, 0);
		addItemsToCart(cart10, 0, 0, 3, 0, 0);
		addItemsToCart(cart11, 0, 0, 4, 0, 0);
		addItemsToCart(cart12, 1, 1, 3, 1, 1);
		addItemsToCart(cart13, 0, 0, 0, 1, 0);
		addItemsToCart(cart14, 0, 0, 0, 2, 2);
		addItemsToCart(cart15, 0, 0, 0, 2, 1);
		addItemsToCart(cart16, 0, 0, 0, 1, 2);
		addItemsToCart(cart17, 0, 0, 6, 0, 0);
		addItemsToCart(cart18, 0, 0, 0, 1, 1);

		cart1Expected = 0.0;
		cart2Expected = 3.24;
		cart3Expected = 10.8;
		cart4Expected = 2.16;
		cart5Expected = 8.64;
		cart6Expected = 0.0;
		cart7Expected = 5.4;
		cart8Expected = 27;
		cart9Expected = 4.32;
		cart10Expected = 5.4;
		cart11Expected = 7.56;
		cart12Expected = 30.24;
		cart13Expected = 8.64;
		cart14Expected = 21.6;
		cart15Expected = 19.44;
		cart16Expected = 16.2;
		cart17Expected = 10.8;
		cart18Expected = 0;

		cart2b = 3.27;
		cart2c = 3.3;
		cart2d = 3.21;
		cart2e = 3; // without tax
	}

	@Test
	public void calcCostCart1() throws UnderAgeException {
		double amount = cart1.calcCost();
		assertEquals(cart1Expected, amount, .01);
	}

	@Test
	public void calcCostCart2() throws UnderAgeException {
		double amount = cart2.calcCost();
		assertEquals(cart2Expected, amount, .01);
	}

	@Test
	public void calcCostCart2b() throws UnderAgeException {
		double amount = cart2.calcCost();
		assertEquals(cart2b, amount, .01);
	}

	@Test
	public void calcCostCart2c() throws UnderAgeException {
		double amount = cart2.calcCost();
		assertEquals(cart2c, amount, .01);
	}

	@Test
	public void calcCostCart2d() throws UnderAgeException {
		double amount = cart2.calcCost();
		assertEquals(cart2d, amount, .01);
	}

	@Test
	public void calcCostCart2e() throws UnderAgeException {
		double amount = cart2.calcCost();
		assertEquals(cart2e, amount, .01);
	}

	@Test
	public void calcCostCart3() throws UnderAgeException {
		double amount = cart3.calcCost();
		assertEquals(cart3Expected, amount, .01);
	}

	@Test
	public void calcCostCart4() throws UnderAgeException {
		double amount = cart4.calcCost();
		assertEquals(cart4Expected, amount, .01);
	}

	@Test
	public void calcCostCart5() throws UnderAgeException {
		double amount = cart5.calcCost();
		assertEquals(cart5Expected, amount, .01);
	}

	@Test
	public void calcCostCart6() throws UnderAgeException {
		double amount = cart6.calcCost();
		assertEquals(cart6Expected, amount, .01);
	}

	@Test
	public void calcCostCart7() throws UnderAgeException {
		double amount = cart7.calcCost();
		assertEquals(cart7Expected, amount, .01);
	}

	@Test
	public void calcCostCart8() throws UnderAgeException {
		double amount = cart8.calcCost();
		assertEquals(cart8Expected, amount, .01);
	}

	@Test
	public void calcCostCart9() throws UnderAgeException {
		double amount = cart9.calcCost();
		assertEquals(cart9Expected, amount, .01);
	}

	@Test
	public void calcCostCart10() throws UnderAgeException {
		double amount = cart10.calcCost();
		assertEquals(cart10Expected, amount, .01);
	}

	@Test
	public void calcCostCart11() throws UnderAgeException {
		double amount = cart11.calcCost();
		assertEquals(cart11Expected, amount, .01);
	}

	@Test
	public void calcCostCart12() throws UnderAgeException {
		double amount = cart12.calcCost();
		assertEquals(cart12Expected, amount, .01);
	}

	@Test
	public void calcCostCart13() throws UnderAgeException {
		double amount = cart13.calcCost();
		assertEquals(cart13Expected, amount, .01);
	}

	@Test
	public void calcCostCart14() throws UnderAgeException {
		double amount = cart14.calcCost();
		assertEquals(cart14Expected, amount, .01);
	}

	@Test
	public void calcCostCart15() throws UnderAgeException {
		double amount = cart15.calcCost();
		assertEquals(cart15Expected, amount, .01);
	}

	@Test
	public void calcCostCart16() throws UnderAgeException {
		double amount = cart16.calcCost();
		assertEquals(cart16Expected, amount, .01);
	}

	@Test
	public void calcCostCart17() throws UnderAgeException {
		double amount = cart17.calcCost();
		assertEquals(cart17Expected, amount, .01);
	}

	@Test
	public void calcCostCart18() throws UnderAgeException {
		double amount = cart18.calcCost();
		assertEquals(cart18Expected, amount, .01);
	}
}