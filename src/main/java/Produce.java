package main.java;

public class Produce extends Product {
    public Produce() {
        super(2);
    }

    public int getCost() {
        return super.getCost();
    }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Produce))
			return false;

		return this.getCost() == ((Produce) obj).getCost();
	}
}
