package main.java;

public class Meat extends Product {
    public Meat() {
        super(10);
    }

    public int getCost() {
        return super.getCost();
    }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Meat))
			return false;

		return this.getCost() == ((Meat) obj).getCost();
	}
}
