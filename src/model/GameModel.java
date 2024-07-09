package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
	Board boardmodel;
	List<Player> playerList;
	List<GameModelObserver> observerList;
	Dice dice;

	public GameModel() {
		playerList = new ArrayList<Player>();
		boardmodel = new BoardFactory().create9x9Board();
		observerList = new ArrayList<GameModelObserver>();
	}

	public void buy() {
		boardmodel.getCurrPlayer().buy(boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex()));
		boardmodel.updatePlayerProperties();
		boardmodel.notifyPlayerOb();
		boardmodel.updatePlayerLocation();
	}

	public int rollDice() {
		return boardmodel.rollingDice();
	}

	public void addPlayer(Player player) {
		playerList.add(player);
	}

	public void removePlayer(Player player) {
		playerList.remove(player);
	}

	public void gameStart() {
		boardmodel.setCurrPlayer(playerList.get(0));
		boardmodel.notifyPlayerOb();
	}

	public void nextPlayer() {
		int index = playerList.indexOf(boardmodel.getCurrPlayer());
		if (index == playerList.size() - 1) {
			index = 0;
			boardmodel.setCurrPlayer(playerList.get(index));
		} else {
			index++;
			boardmodel.setCurrPlayer(playerList.get(index));
		}
		boardmodel.notifyPlayerOb();
		boardmodel.updatePlayerProperties();
		boardmodel.updatePlayerLocation();
	}

	public void chanceSquareFunction() {
		// TODO Auto-generated method stub
		String[] chanceCards = { "Go to Jail", "Go back 3 spaces", "Advanced to Go",
				"You won a competition collect 100$", "You have been elected chairman of the board pay each other 50$",
				"Take a trip to Reading RailRoad", "Get out of jail free card", "Pay poor tax 15$",
				"You building your loan mature collect 150$", "Advanced to Illinois Avenue" };
		Random ranNum = new Random();
		String getCard = chanceCards[ranNum.nextInt(chanceCards.length)];

		switch (getCard) {
		case "Advanced to Go":
			boardmodel.getCurrPlayer().setIndex(0);
			boardmodel.updatePlayerLocation();
			updateNoticeCard("chance0");
			boardmodel.notifyPlayerOb();
			break;
		case "Go back 3 spaces":
			boardmodel.getCurrPlayer().setIndex(boardmodel.getCurrPlayer().getIndex() - 3);
			updateNoticeCard("chance7");
			boardmodel.updatePlayerLocation();
			doSquareFunction();
			boardmodel.updatePlayerLocation();
			boardmodel.notifyPlayerOb();
			break;
		case "Go to Jail":
			boardmodel.goToJailSquareFunction();
			updateNoticeCard("chance8");
			boardmodel.notifyPlayerOb();
			break;
		case "Pay poor tax 15$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - 15);
			updateNoticeCard("chance10");
			boardmodel.notifyPlayerOb();
			break;
		case "You building your loan mature collect 150$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 150);
			updateNoticeCard("chance14");
			boardmodel.notifyPlayerOb();
			break;
		case "You won a competition collect 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 100);
			updateNoticeCard("chance15");
			boardmodel.notifyPlayerOb();
			break;
		case "You have been elected chairman of the board pay each other 50$":
			for (Player player : playerList) {
				player.setMoney(player.getMoney() + 50);
			}
			;
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - (50 * playerList.size()));
			updateNoticeCard("chance13");
			boardmodel.notifyPlayerOb();
			break;
		case "Take a trip to Reading RailRoad":
			int n = boardmodel.getCurrPlayer().getIndex();
			if (n > 5)
				boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 200);
			boardmodel.getCurrPlayer().setIndex(5);
			updateNoticeCard("chance11");
			boardmodel.updatePlayerLocation();
			boardmodel.notifyPlayerOb();
			break;
		case "Get out of jail free card":
			boardmodel.getCurrPlayer().setNumOfFreeGetOutOfJailCard(boardmodel.getCurrPlayer().getNumOfFreeGetOutOfJailCard() + 1);
			updateNoticeCard("chance6");
			boardmodel.notifyPlayerOb();
			break;
		case "Advanced to Illinois Avenue":
			n = boardmodel.getCurrPlayer().getIndex();
			if (n > 19)
				boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 200);
			boardmodel.getCurrPlayer().setIndex(19);
			updateNoticeCard("chance1");
			boardmodel.updatePlayerLocation();
			boardmodel.notifyPlayerOb();
			break;
		}
	}

	public void comunitySquareFunction() {
		// TODO Auto-generated method stub
		String[] communityChestCards = { "Get out of Jail free card", "Advanced to Go", "Doctor fees pay 50$",
				"Go to jail", "Grand Opera Night", "Hopistal fees pay 100$", "School fees pay 50$", "You inherit 100$",
				"Sale of stock collect 50$", "Holiday fund mature collect 100$" };
		Random ranNum = new Random();
		String getCard = communityChestCards[ranNum.nextInt(communityChestCards.length)];

		switch (getCard) {
		case "Get out of Jail free card":
			boardmodel.getCurrPlayer().setNumOfFreeGetOutOfJailCard(boardmodel.getCurrPlayer().getNumOfFreeGetOutOfJailCard() + 1);
			updateNoticeCard("community3");
			boardmodel.notifyPlayerOb();
			break;
		case "Advanced to Go":
			boardmodel.goSquareFuntion();
			updateNoticeCard("community0");
			boardmodel.notifyPlayerOb();
			break;
		case "Doctor fees pay 50$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - 50);
			updateNoticeCard("community2");
			boardmodel.notifyPlayerOb();
			break;
		case "Go to jail":
			boardmodel.goToJailSquareFunction();
			updateNoticeCard("community4");
			boardmodel.notifyPlayerOb();
			break;
		case "Grand Opera Night":
			for (Player player : playerList) {
				player.setMoney(player.getMoney() - 50);
			}
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + (50 * playerList.size()));
			updateNoticeCard("community6");
			boardmodel.notifyPlayerOb();
			break;
		case "Hopistal fees pay 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - 100);
			updateNoticeCard("community9");
			boardmodel.notifyPlayerOb();
			break;
		case "School fees pay 50$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - 50);
			updateNoticeCard("community10");
			boardmodel.notifyPlayerOb();
			break;
		case "You inherit 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 100);
			updateNoticeCard("community14");
			boardmodel.notifyPlayerOb();
			break;
		case "Sale of stock collect 50$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 50);
			updateNoticeCard("community15");
			boardmodel.notifyPlayerOb();
			break;
		case "Holiday fund mature collect 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 100);
			updateNoticeCard("community16");
			boardmodel.notifyPlayerOb();
			break;
		}
	}

	public void notifyGameObs() {
		for (GameModelObserver ob : observerList) {
			ob.update(playerList.size());
		}
	}

	public void updateNoticeCard(String src) {
		for (GameModelObserver ob : observerList) {
			ob.cardNotice(src);
		}
	}

	public void registerObserver(GameModelObserver ob) {
		observerList.add(ob);
	}

	public void removerObserver(GameModelObserver ob) {
		observerList.remove(ob);
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public Board getBoardModel() {
		return boardmodel;
	}

	public void move(int step) {
		// TODO Auto-generated method stub
		boardmodel.move(step);
		doSquareFunction();
		boardmodel.notifyPlayerOb();
		boardmodel.updatePlayerLocation();
	}

	public void doSquareFunction() {
		// TODO Auto-generated method stub
		int index = boardmodel.getCurrPlayer().getIndex();
		boolean isOwnYet = boardmodel.getSquareList().get(index).isOwn();
		if (index == 3 || index == 11 || index == 27) {
			comunitySquareFunction();
			System.out.println("chest");
		} else if (index == 0) {

		} else if (index == 8) {

		} else if (index == 16) {

		} else if (index == 6 || index == 18 || index == 30) {
			chanceSquareFunction();
			System.out.println("Chance");
		} else if (index == 24) {
			boardmodel.goToJailSquareFunction();
			System.out.println("GotoJail");
		} else if (index == 5 || index == 13 || index == 21 || index == 29) {
			if (isOwnYet == true) {
				railRoadFee();
			}
		} else if (index == 14) {
			if (isOwnYet == true) {
				electricCompanyFee();
			}
		} else {
			isOwnYet = boardmodel.getSquareList().get(index).isOwn();
			if (isOwnYet == true) {
				landFee();
			}
			System.out.println("Land");
		}
	}

	public void railRoadFee() {
		String nameSquareCurrPlayer = boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex()).getTitle();
		String nameSquare = boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex()).getTitle();
		Player owner = boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex()).getOwner();
		double taxSquare = boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex()).getTax();
		if (nameSquareCurrPlayer.equals(nameSquare)) {
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - taxSquare);
			owner.setMoney(owner.getMoney() + taxSquare);
		}
		notifyGameObs();
	}

	public void landFee() {
		for (int i = 0; i < boardmodel.getSquareList().size(); i++) {
			String nameSquareCurrPlayer = boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex())
					.getTitle();
			String nameSquare = boardmodel.getSquareList().get(i).getTitle();
			Player owner = boardmodel.getSquareList().get(i).getOwner();
			double taxSquare = boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex()).getTax();
			if (nameSquareCurrPlayer.equals(nameSquare)) {
				boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - taxSquare);
				owner.setMoney(owner.getMoney() + taxSquare);
			}
		}
		notifyGameObs();
	}

	public void electricCompanyFee() {
		int[] diceValues = dice.getDiceValue();
		int res = 0;
		for (int i = 0; i < diceValues.length; i++) {
			res += diceValues[i];
		}
		double taxElectricCompany = boardmodel.getSquareList().get(14).getTax();
		Player owner = boardmodel.getSquareList().get(boardmodel.getCurrPlayer().getIndex()).getOwner();
		boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - (res * taxElectricCompany));
		owner.setMoney(owner.getMoney() + (res * taxElectricCompany));
		notifyGameObs();
	}
}
