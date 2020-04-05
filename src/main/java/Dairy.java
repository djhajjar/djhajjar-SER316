package main.java;

public class Dairy extends Product {
    public Dairy() {
        super(3);
    }

    public int getCost() {
        return super.getCost();
    }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Dairy))
			return false;

		return this.getCost() == ((Dairy) obj).getCost();
	}
}
