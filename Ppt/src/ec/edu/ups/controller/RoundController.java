package ec.edu.ups.controller;

import ec.edu.ups.model.Player;

public class RoundController {

	private RuleController ruleController;

	private Player player1;
	private Player player2;
	private int winsP1;
	private int winsP2;
	private int round;
	private int[] data; // datos actuales de la partida

	public RoundController(Player player1, Player player2) {
		super();
		round = 1;
		data = new int[3];
		this.player1 = player1;
		this.player2 = player2;

		this.ruleController = new RuleController(player1, player2);
	}

	public RuleController getRuleController() {
		return ruleController;
	}

	public void setRuleController(RuleController ruleController) {
		this.ruleController = ruleController;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getWinsP1() {
		return winsP1;
	}

	public void setWinsP1(int winsP1) {
		this.winsP1 = winsP1;
	}

	public int getWinsP2() {
		return winsP2;
	}

	public void setWinsP2(int winsP2) {
		this.winsP2 = winsP2;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	/**
	 * Metodo que obtiene datos de las victorias de los jugadores y la ronda en la
	 * que va.
	 * 
	 * @return data[], donde se almacena la informacion obtenida
	 */
	public void gameData() {
		winsP1 = player1.getWin();
		winsP2 = player2.getWin();
		round = winsP1 + winsP2 + 1;
		data[0] = winsP1;
		data[1] = winsP2;
		data[2] = round;
//		return data;
	}

	public void finishedRound() {
		gameData();
	}

	/**
	 * Metodo para verificar el ganador de la partida
	 * 
	 * @return Player, devuelve nulo si hay un empate
	 */
	public Player finalWinner() {
		winsP1 = player1.getWin();
		winsP2 = player2.getWin();

		if (winsP1 > winsP2) {
			return player1;
		} else if (winsP1 < winsP2) {
			return player2;
		} else {
			return null;
		}
	}

	public void update() {

		ruleController.updateKeyboard();

	}

	public void selectOption() {
		System.out.println("p1: " + ruleController.getP1());
		ruleController.setOption(this.player1, ruleController.getP1());
		ruleController.setOption(this.player2, ruleController.getP2());
	}

	public Player getRoundWinner() {
		return ruleController.getWinner();
	}

	public void startRound() {
		gameData();
		ruleController.setWinner(null);
	}

}
