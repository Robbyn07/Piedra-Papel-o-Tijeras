
package ec.edu.ups.controller.statemachine.game;

import java.awt.Graphics;
import ec.edu.ups.controller.statemachine.GameState;
import ec.edu.ups.tools.ControlManager;
import ec.edu.ups.tools.Keyboard;

public class GameRuleManager implements GameState {

    // SpriteSheet sheet = new
    // SpriteSheet("/ec/edu/ups/resources/textures/damas64.png", 64, false);
	
	private Keyboard key1 = new Keyboard();
	private Keyboard key2 = new Keyboard();
	
	//player 
	//0 no selecciona ninguna opcion
	//1 selecciona piedra
	//2 selecciona papel
	//3 selecciona tijeras
	int player1 = 0; 
	int player2 = 0;
	
	boolean status;
	
	long startTime;
	long estimatedTime;
	
    public GameRuleManager() {
		super();
		timeCounter();
	}
	
    /**
     * timeCounter controla el tiempo de lectura de teclado
     */
    public void timeCounter () {
    	status=true;
    	startTime = System.nanoTime();
	}
    


    @Override
    public void update() {
		if (ControlManager.keyboard.isStone1() && status) {
			System.out.println("A 1"); 
			player1 = 1;
			key1=ControlManager.keyboard;
		}
		
		if (ControlManager.keyboard.isStone2()&&status) {
			System.out.println("Izq 1");
			player2 = 1;
			key2=ControlManager.keyboard;
		}
		
		if (ControlManager.keyboard.isPaper1()&&status) {
			System.out.println("S 2"); 
			player1 = 2;
			key1=ControlManager.keyboard;
		}
		
		if (ControlManager.keyboard.isPaper2()&&status) {
			System.out.println("Arriba 2");
			player2 = 2;
			key2=ControlManager.keyboard;
		}
		
		if (ControlManager.keyboard.isScissors1()&&status) {
			System.out.println("D 3"); 
			player1 = 3;
			key1=ControlManager.keyboard;
		}
		
		if (ControlManager.keyboard.isScissors2()&&status) {
			System.out.println("Der 3");
			player2 = 3;
			key2=ControlManager.keyboard;
		}
		
		//control de tiempo 5 segundos 
		estimatedTime = (System.nanoTime() - startTime)/1000000000;
		if (estimatedTime == 5 && status) {
			status=false;
			System.out.println("tiempo finalizado");
			System.out.println("P1= "+player1+" P2= "+player2);
		}
    }

    @Override
    public void print(Graphics g) {

    }

}
