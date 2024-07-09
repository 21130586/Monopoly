package model;

import java.util.Random;

public class SingleDice implements Dice {
	int diceA;
	boolean dicePairValue;

	@Override
	public int roll() {
		// TODO Auto-generated method stub
		Random ran = new Random();
		int dicea = ran.nextInt(6) + 1;
		return dicea;
	}

	@Override
	public int[] getDiceValue() {
		// TODO Auto-generated method stub
		int[] a = { diceA };
		return a;
	}

	@Override
	public void setDoubleDiceValue(int a, int b) {
		// TODO Auto-generated method stub
	}

	public boolean isDicePairValue() {
		return dicePairValue;
	}

}
