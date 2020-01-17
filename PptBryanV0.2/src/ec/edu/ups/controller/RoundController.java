package ec.edu.ups.controller;

import ec.edu.ups.model.Player;

public class RoundController {

	private Player player1;
	private Player player2;
	private int winsP1;
	private int winsP2;
	private int round;
	private int[] data; // datos actuales de la partida

	public RoundController(Player player1, Player player2) {
		super();
		data = new int[3];
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * Metodo que obtiene datos de las victorias de los jugadores y la ronda en la
	 * que va.
	 * 
	 * @return data[], donde se almacena la informacion obtenida
	 */
	public int[] gameData() {
		winsP1 = player1.getWin();
		winsP2 = player2.getWin();
		round = winsP1 + winsP2 + 1;
		data[0] = winsP1;
		data[1] = winsP2;
		data[2] = round;

		return data;
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

}
