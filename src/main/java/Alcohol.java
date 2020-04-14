package main.java;

public class Alcohol extends Product {
    public Alcohol() {
        super(8);
    }

    public int getCost() {
        return super.getCost();
    }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Alcohol))
			return false;

		return this.getCost() == ((Alcohol) obj).getCost();
	}
}
