package ec.edu.ups.controller;

import ec.edu.ups.model.Player;
import ec.edu.ups.tools.ControlManager;

public class RuleController {

	private Player player1;
	private Player player2;
	private Player winner;
	private int count;

	/**
	 * Identificacion para los elementos del jugador. 100 no selecciona ninguna
	 * opcion 0 selecciona piedra 1 selecciona papel 2 selecciona tijeras
	 */

	private int p1 = 100;
	private int p2 = 100;

	private boolean status;
	private boolean statusCount;

	private long startTime;
	private long estimatedTime;

	public RuleController(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.count = 5;
	}

	public boolean isStatus() {
		return status;
	}

	public Player getWinner() {
		return winner;
	}

	/**
	 * timeCounter controla el tiempo de lectura de teclado
	 */
	public void startGame() {
		status = true;
		statusCount = true;
		startTime = System.nanoTime();
	}

	public Player winner() {

		/**
		 * aparte, cuando se decida quien gano, en proximas actualizaciones, aumentar en
		 * uno el total de victorias del un jugador y restar el total de derrotas del
		 * otro jugador?
		 */

		int optionPlayer1 = option(player1);
		int optionPlayer2 = option(player2);

		System.out.println("Comprobando...");

		if (optionPlayer1 == optionPlayer2) {
			// EMPATE
			return null;
		} else if (optionPlayer1 == 100 && optionPlayer2 != 100) {
			// PLAYER1 no escogio una opcion a tiempo
			winner = player2;
		} else if (optionPlayer1 != 100 && optionPlayer2 == 100) {
			// PLAYER2 no escogio una opcion a tiempo
			winner = player1;
		} else if (optionPlayer1 == 0 && optionPlayer2 == 1) {
			// piedra vs papel
			winner = player2;
		} else if (optionPlayer1 == 0 && optionPlayer2 == 2) {
			// piedra vs tijera
			winner = player1;
		} else if (optionPlayer1 == 1 && optionPlayer2 == 0) {
			// papel vs piedra
			winner = player1;
		} else if (optionPlayer1 == 1 && optionPlayer2 == 2) {
			// papel vs tijera
			winner = player2;
		} else if (optionPlayer1 == 2 && optionPlayer2 == 0) {
			// tijera vs piedra
			winner = player2;
		} else if (optionPlayer1 == 2 && optionPlayer2 == 1) {
			// tijera vs papel
			winner = player1;
		} else {
			winner = null;
		}
		return winner;
	}

	/**
	 * metodo para saber que opcion se escogio, sirve especialmente si se da el caso
	 * de que no se escoge ninguna opcion, entonces devuelve un valor nulo.
	 */
	private int option(Player player) {
		int option = 100;
		int n = player.getElements().length;
		for (int i = 0; i < n; i++) {
			if (player.getElements()[i].isSelected() == true) {
				option = i;
				break;
			}
		}
		return option;
	}

	/*
	 *  
	 *  */
	private void setOption(Player player, int op) {
		if (op != 100) {
			player.getElements()[op].setSelected(true);
		}
	}

	public void update() {
		if (ControlManager.keyboard.isRock1() && status) {
			p1 = 0;
		}

		if (ControlManager.keyboard.isRock2() && status) {
			p2 = 0;
		}

		if (ControlManager.keyboard.isPaper1() && status) {
			p1 = 1;
		}

		if (ControlManager.keyboard.isPaper2() && status) {
			p2 = 1;
		}

		if (ControlManager.keyboard.isScissors1() && status) {
			p1 = 2;
		}

		if (ControlManager.keyboard.isScissors2() && status) {
			p2 = 2;
		}

		/**
		 * control de tiempo de 5 segundos.
		 */
		estimatedTime = (System.nanoTime() - startTime) / 1000000000;
		if (estimatedTime == 5 && status) {
			status = false;
			setOption(player1, p1);
			setOption(player2, p2);
			winner();
		}

		if (passASecond()) {
			System.out.println(count);
			count--;
		}

//		if (estimatedTime == 1 && !statusCount) {
//			statusCount = true;
//		}
	}

	private boolean passASecond() {
		if (estimatedTime == 2) {
			return true;
		}
		return false;
	}

}
