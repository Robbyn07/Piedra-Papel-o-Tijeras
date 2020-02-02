package ec.edu.ups.controller;

import ec.edu.ups.model.Player;
import ec.edu.ups.tools.ControlManager;
import ec.edu.ups.tools.Sound;

public class RuleController {

	private Player player1;
	private Player player2;
	private Player winner;
	
	private Sound sound;

	/*
	 * Identificacion para los elementos del jugador: 100 = no selecciona ninguna
	 * opcion. 0 = selecciona piedra. 1 = selecciona papel. 2 = selecciona tijeras.
	 */

	private int p1 = 100;
	private int p2 = 100;
	private int option;

	public RuleController(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}

	public int getP1() {
		return p1;
	}

	public int getP2() {
		return p2;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public Player getWinner() {
		winner = winner();
		this.p1 = 100;
		this.p2 = 100;
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	private Player winner() {

		/*
		 * Aparte, cuando se decida quien gano, en proximas actualizaciones, aumentar en
		 * uno el total de victorias de un jugador... y restar el total de derrotas del
		 * otro jugador?
		 */

		int optionPlayer1 = selectOption(player1);
		int optionPlayer2 = selectOption(player2);
		winner = null;
		System.out.println("Comprobando..." + optionPlayer1 + " | " + optionPlayer2);

		if (optionPlayer1 == optionPlayer2) {
			// EMPATE
			drawSound();
			return null;
		} else if (optionPlayer1 == 100 && optionPlayer2 != 100) {
			// PLAYER1 no escogio una opcion a tiempo
			yaySound();
			player2.addWin();
			winner = player2;
		} else if (optionPlayer1 != 100 && optionPlayer2 == 100) {
			// PLAYER2 no escogio una opcion a tiempo
			yaySound();
			player1.addWin();
			winner = player1;
		} else if (optionPlayer1 == 0 && optionPlayer2 == 1) {
			// piedra vs papel
			paperSound();
			player2.addWin();
			winner = player2;
		} else if (optionPlayer1 == 0 && optionPlayer2 == 2) {
			// piedra vs tijera
			rockSound();
			player1.addWin();
			winner = player1;
		} else if (optionPlayer1 == 1 && optionPlayer2 == 0) {
			// papel vs piedra
			paperSound();
			player1.addWin();
			winner = player1;
		} else if (optionPlayer1 == 1 && optionPlayer2 == 2) {
			// papel vs tijera
			scissorSound();
			player2.addWin();
			winner = player2;
		} else if (optionPlayer1 == 2 && optionPlayer2 == 0) {
			// tijera vs piedra
			rockSound();
			player2.addWin();
			winner = player2;
		} else if (optionPlayer1 == 2 && optionPlayer2 == 1) {
			// tijera vs papel
			scissorSound();
			player1.addWin();
			winner = player1;
		} else {
			winner = null;
		}
		return winner;
	}

	/**
	 * Metodo para saber que opcion se escogio, sirve especialmente si se da el caso
	 * de que no se escoge ninguna opcion, entonces devuelve un valor nulo.
	 */
	private int selectOption(Player player) {
		int n = player.getElements().length;

		for (int i = 0; i < n; i++) {

			if (player.getElements()[i].isSelected() == true) {
				return i;
			}

		}
		return 100;
	}

	public void setFalseElements() {
		for (int i = 0; i < player1.getElements().length; i++) {
			player1.getElements()[i].setSelected(false);
			player2.getElements()[i].setSelected(false);
		}

	}
//
//	public Element getTrueElement(Player player) {
//		int n = player.getElements().length;
//
//		for (int i = 0; i < n; i++) {
//			if (player.getElements()[i].isSelected() == true) {
//				return player.getElements()[i];
//			}
//		}
//		return null;
//	}

	/**
	 * 
	 * @param player
	 * @param op
	 */
	public void setOption(Player player, int op) {
		if (op != 100) {
			player.getElements()[op].setSelected(true);
		}
	}

	/**
	 * 
	 */
	public void updateKeyboard() {
		if (ControlManager.keyboard.isRock1()) {
			p1 = 0;
		}

		if (ControlManager.keyboard.isRock2()) {
			p2 = 0;
		}

		if (ControlManager.keyboard.isPaper1()) {
			p1 = 1;
		}

		if (ControlManager.keyboard.isPaper2()) {
			p2 = 1;
		}

		if (ControlManager.keyboard.isScissors1()) {
			p1 = 2;
		}

		if (ControlManager.keyboard.isScissors2()) {
			p2 = 2;
		}
	}

	public void setVelocity(int v) {

		for (int i = 0; i < player1.getElements().length; i++) {
			player1.getElements()[i].setVelocityX(v);
			player1.getElements()[i].setVelocityY(v);
			player2.getElements()[i].setVelocityX(v);
			player2.getElements()[i].setVelocityY(v);

		}

	}
	
	private void paperSound() {
		this.sound = new Sound("/ec/edu/ups/resources/sounds/paper.wav");
		this.sound.play();
	}
	
	private void scissorSound() {
		this.sound = new Sound("/ec/edu/ups/resources/sounds/scissors.wav");
		this.sound.play();
	}
	
	private void rockSound() {
		this.sound = new Sound("/ec/edu/ups/resources/sounds/rock.wav");
		this.sound.play();
	}
	
	private void drawSound() {
		this.sound = new Sound("/ec/edu/ups/resources/sounds/draw.wav");
		this.sound.play();
	}
	
	private void yaySound() {
		this.sound = new Sound("/ec/edu/ups/resources/sounds/yay.wav");
		this.sound.play();
	}

}
