package model;

public class SquareModel implements Square {
	int X, Y;
	Player owner;
	boolean own;
	String title;
	int level; 
	double[] price;
	double[] tax;
	String color;

	public SquareModel(double[] price, double[] tax, String color, String title, int X, int Y) {
		level = 0;
		own = false;
		this.price = price;
		this.tax = tax;
		this.color = color;
		this.title = title;
		this.X = X;
		this.Y = Y;
	}

	public SquareModel(int X, int Y) {
		level = -1;
		own = true;
		this.X = X;
		this.Y = Y;
	}

	public SquareModel(double[] price, double[] tax, boolean own, String title, int X, int Y) {
		level = 0;
		this.title = title;
		this.price = price;
		this.tax = tax;
		this.own = own;
		this.X = X;
		this.Y = Y;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return tax[level];
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price[level];
	}

	@Override
	public boolean isOwn() {
		// TODO Auto-generated method stub
		return own;
	}

	@Override
	public void setOwn(boolean b) {
		// TODO Auto-generated method stub
		own = b;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public int setLevel(int level) {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		level++;
	}

	@Override
	public void levelDown() {
		// TODO Auto-generated method stub
		level--;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return X;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return Y;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}

	@Override
	public void setOwner(Player player) {
		// TODO Auto-generated method stub
		owner = player;
	}

}
