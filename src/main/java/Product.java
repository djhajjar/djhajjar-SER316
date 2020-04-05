package main.java;

public class Product {
	private int cost;

	public Product(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product))
			return false;

		return this.getCost() == ((Product) obj).getCost();
	}
}
