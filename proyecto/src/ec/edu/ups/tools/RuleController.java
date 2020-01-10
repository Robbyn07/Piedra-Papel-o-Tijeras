package ec.edu.ups.tools;

import ec.edu.ups.model.Player;

public class RuleController {

	private Player player1;
	private Player player2;
	private Keyboard keyboard;
	
	public RuleController(Player player1, Player player2, Keyboard keyboard) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.keyboard = keyboard;
		update();
	}
	
	
	
	public Player winner() {
		
		/*aparte, cuando se decida quien gano, en proximas actualizaciones, aumentar en uno
		el total de victorias del un jugador y restar el total de derrotas del otro jugador?
		*/
		
		int optionPlayer1 = option(player1);
		int optionPlayer2 = option(player2);
		
		if (optionPlayer1==optionPlayer2) {
			//EMPATE
			return null;
		}else if (optionPlayer1==100 && optionPlayer2!=100) {
			//PLAYER1 no escogio una opcion a tiempo
			return player2;
		}else if (optionPlayer1!=100 && optionPlayer2==100) {
			//PLAYER2 no escogio una opcion a tiempo
			return player1;
		}else if (optionPlayer1==0 && optionPlayer2==1) {
			//piedra vs papel
			return player2;
		}else if (optionPlayer1==0 && optionPlayer2==2) {
			//piedra vs tijera
			return player1;
		}else if (optionPlayer1==1 && optionPlayer2==0) {
			//papel vs piedra
			return player1;
		}else if (optionPlayer1==1 && optionPlayer2==2) {
			//papel vs tijera
			return player2;
		}else if (optionPlayer1==2 && optionPlayer2==0) {
			//tijera vs piedra
			return player2;
		}else if (optionPlayer1==2 && optionPlayer2==1) {
			//tijera vs papel
			return player1;
		}else {
			return null;
		}
	}
	
	
	/*metodo para saber que opcion se escogio, sirve especialmente si se da el caso de que no se escoge ninguna
	  opcion, entonces devuelve un valor nulo*/
	private int option(Player player) {
		int option = 100;
		for(int i=0; i<3; i++) {
			if (player.getElements()[i].isSelected()==true) {
				option=i;
				break;
			}
		}
		return option;
	}
	
	public void update() {
		
	}
	
}
