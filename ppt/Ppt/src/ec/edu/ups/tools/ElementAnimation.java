package ec.edu.ups.tools;

import java.awt.Rectangle;

import ec.edu.ups.controller.statemachine.game.GameRuleManager;
import ec.edu.ups.main.Constants;
import ec.edu.ups.model.Element;
import ec.edu.ups.model.Player;

public class ElementAnimation {

    private double G = 0.2;

    private int[] xD;
    private int[] yD;

    private int[] wD;
    private int[] hD;

    private boolean running;
    private int animaOption;

    private Player[] players;
    GameRuleManager gameRuleManager;

    /*
     * Atributos necesarios para realizar las animaciones de ataque.
     */
    private boolean[] jumping;
    private boolean[] stateMovingElement;
    private boolean[] stateAtacking;
    private double[] speedsX;
    private double[] speedsY;
    private boolean atacking;
    /* Fin */

    /*
     * Control de frecuencia.
     */
    int NS_PER_SECOND = 1000000000;
    int UPS_OBJECT;
    double NS_PER_UPDATES;
    long updateReference;
    long countReference;
    double timeElapsed;
    double delta = 0;
    long loopStart;
    /* Fin */

    public ElementAnimation(Player[] players, GameRuleManager gameRuleManager) {
	super();

	this.players = players;
	this.gameRuleManager = gameRuleManager;

	setDefaults(players);
	this.running = false;
	this.atacking = false;
	this.animaOption = 0;

	this.stateAtacking = new boolean[4];

	this.stateAtacking[0] = true;
	this.stateAtacking[1] = false;
	this.stateAtacking[2] = false;
	this.stateAtacking[3] = false;

    }

    /*
     * Getters y Setters.
     */
    public boolean isRunning() {
	return running;
    }

    public void setRunning(boolean running) {
	this.running = running;
    }

    public boolean isAtacking() {
	return atacking;
    }

    public void setAtacking(boolean atacking) {
	this.atacking = atacking;
    }

    /**
     * Metodo update, llamado desde el controlador de estados GameRuleManager,
     * este ejecuta la animacion que este selecionada en animaOption.
     * animaOption = 0 para no ejecutar ninguna animacion.
     * 
     * @param second
     */
    public void update(long second) {
	switch (animaOption) {

	case 0:

	    break;
	case 1:
	    inAnimation();

	    break;
	case 2:
	    presentation();
	    break;
	case 3:
	    atackAnimation();
	    break;
	default:
	    System.out.println("Animacion no encontrada.");
	    break;
	}

    }

    /**
     * Establece a los arrays xD, yD, wD, hD extraidos de los elementos de cada
     * jugador.
     * 
     * Tambien establece las velocidades de ataque por defecto para cada
     * Element.
     * 
     * Tambien inicializa el array Jumping para la animacion de presentacion.
     * 
     * @param Array de Player, para obtener los datos de los elementos de cada
     *              jugador.
     */
    public void setDefaults(Player[] players) {

	int n = players[0].getElements().length * 2;
	this.xD = new int[n];
	this.yD = new int[n];
	this.wD = new int[n];
	this.hD = new int[n];
	this.jumping = new boolean[n];
	this.stateMovingElement = new boolean[n];
	this.speedsX = new double[n];
	this.speedsY = new double[n];
	int c = 0;

	int mW = Constants.MID_WIDTH_WIN;
	int mH = Constants.MID_HEIGHT_WIN;

	for (int i = 0; i < players.length; i++) {
	    for (int j = 0; j < players[i].getElements().length; j++) {

		Element element = players[i].getElements()[j];

		this.xD[c] = element.getPointX();
		this.yD[c] = element.getPointY();
		this.wD[c] = element.getWidth();
		this.hD[c] = element.getHeight();
		this.jumping[c] = false;
		this.stateMovingElement[c] = false;

		double vx = Math.abs(mW - element.getEdge().getX()) / 30;
		double vy = Math.abs(mH - element.getEdge().getY()) / 30;

		speedsX[c] = vx;
		speedsY[c] = vy;

		c++;
	    }
	}
    }

    /**
     * Inicializa los atributos y metodos necesarios para realizar la animacion
     * de Entrada.
     * 
     * @param velocity
     */
    public void startInAnimation(int velocity) {

	NS_PER_UPDATES = NS_PER_SECOND / velocity;
	UPS_OBJECT = velocity;
	running = true;
	defaultElements();

	setStateMoving(true);

	animaOption = 1;

	this.players[0].getElements()[0].setPointY(-128);
	this.players[0].getElements()[1].setPointX(-128);
	this.players[0].getElements()[2].setPointY(Constants.HEIGHT_WINDOW);

	this.players[1].getElements()[0].setPointY(-128);
	this.players[1].getElements()[1].setPointX(Constants.WIDTH_WINDOW);
	this.players[1].getElements()[2].setPointY(Constants.HEIGHT_WINDOW);

	updateReference = System.nanoTime();
	countReference = System.nanoTime();

    }

    /**
     * Termina la aniamcion de Entrada.
     */
    public void stopInAnimation() {

	this.animaOption = 0;
	this.running = false;

	startPresentation(20);

    }

    /**
     * Este metodo realiza la animacion de entrada para cada elemento.
     */
    private void inAnimation() {

	if (running) {

	    loopStart = System.nanoTime();

	    timeElapsed = loopStart - updateReference;
	    updateReference = loopStart;

	    delta += timeElapsed / NS_PER_UPDATES;

	    while (delta >= 1) {
		delta = 0;

		int c = 0;

		for (int i = 0; i < this.players.length; i++) {

		    for (int j = 0; j < players[i].getElements().length; j++) {

			Element element = players[i].getElements()[j];

			element.setVelocityY(
				(element.getVelocityY()) + 2 * 0.9);
			element.setVelocityX(
				(element.getVelocityX()) + 2 * 0.9);

			int x = (int) element.getPointX();
			int y = (int) element.getPointY();

			if (stateMovingElement[c] && element.getOption() == 'R'
				&& y < yD[c]) {
			    moveDown(element);

			}

			if (stateMovingElement[c] && element.getOption() == 'S'
				&& y > yD[c]) {
			    moveUp(element);
			}

			if (stateMovingElement[c]
				&& element.getOption() == 'P') {

			    if (x < xD[c]) {
				moveRight(element);
			    }

			    if (x > xD[c]) {
				moveLeft(element);
			    }

			}

			if (stateMovingElement[c] && element.getOption() == 'R'
				&& element.getPointY() >= yD[c]) {

			    stateMovingElement[c] = false;
			}

			if (stateMovingElement[c] && element.getOption() == 'S'
				&& element.getPointY() <= yD[c]) {
			    stateMovingElement[c] = false;
			}

			if (stateMovingElement[c]
				&& element.getOption() == 'P') {

			    if (element.getPointX() >= xD[c] && c == 1) {

				stateMovingElement[c] = false;
			    }

			    if (element.getPointX() <= xD[c] && c == 4) {
				stateMovingElement[c] = false;
			    }

			}

			c++;
		    }

		}

		if (!getStateRunAtack()) {
		    stopInAnimation();
		}

		updateReference = System.nanoTime();
	    }

	}

    }

    /**
     * Este metodo inicializa los atributos o metodos necesarios para realizar
     * la animacion de Presentacion.
     * 
     * @param velocity int; la velocidad con la que correra la animacion.
     */
    public void startPresentation(int velocity) {

	NS_PER_UPDATES = NS_PER_SECOND / velocity;
	UPS_OBJECT = velocity;
	running = true;
	animaOption = 2;
	G = Math.abs(G);
	defaultElements();

	setStateMoving(true);

	updateReference = System.nanoTime();
	countReference = System.nanoTime();

    }

    /**
     * Termina la animacion de ataque.
     */
    private void stopAtackAnimation() {
	running = false;
	setAtacking(false);
	gameRuleManager.setWinning(true);

    }

    /**
     * Termina la animacion de presentacion.
     */
    public void stopPresentation() {
	running = false;
	animaOption = 0;

    }

    /**
     * Realiza un movimiento leve a todos los elementos, es la animacion de
     * presentacion durante la ejecucion del juego.
     */
    public void presentation() {

	if (running) {

	    loopStart = System.nanoTime();

	    timeElapsed = loopStart - updateReference;
	    updateReference = loopStart;

	    delta += timeElapsed / NS_PER_UPDATES;

	    if (delta >= 1) {
		delta = 0;
		int nP = players.length;
		int nE;
		int c = 0;
		Element element;

		for (int i = 0; i < nP; i++) {
		    nE = players[i].getElements().length;

		    for (int j = 0; j < nE; j++) {
			element = players[i].getElements()[j];

			if (jumping[c]) {
			    if (G > 0) {
				G = -G;
			    }
			}

			int jump = (int) (element.getPointY()
				+ element.getVelocityY());

			element.setVelocityY(element.getVelocityY() + G * 0.9);

			if (jump <= (yD[c] - 2)) {
			    jumping[c] = false;
			    G = Math.abs(G);
			} else {
			    element.setPointY(jump);
			}

			if (jump >= (yD[c] + 2)) {
			    element.setPointY(yD[c] + 2);
			    jumping[c] = true;
			} else {
			    element.setPointY(jump);
			}
			c++;
		    }

		}
		updateReference = System.nanoTime();
	    }

	}

    }

    /**
     * Este metodo inicializa los atributos o metodos necesarios para realizar
     * la animacion de Ataque.
     * 
     * @param velocity int; la velocidad con la que correra la animacion.
     */
    public void startAtackAnimation(int velocity) {
	running = true;
	animaOption = 3;
	setStateMoving(true);
	NS_PER_UPDATES = NS_PER_SECOND / velocity;
	UPS_OBJECT = velocity;
	G = Math.abs(G);
	updateReference = System.nanoTime();
	countReference = System.nanoTime();
    }

    /**
     * Metodo para distribuir el tipo de animacion en el ataque. Si es TRUE, el
     * elemento atacará, caso contrario, el elemento saldrá del campo.
     */
    private void atackAnimation() {

	if (running) {

	    loopStart = System.nanoTime();

	    timeElapsed = loopStart - updateReference;
	    updateReference = loopStart;

	    delta += timeElapsed / NS_PER_UPDATES;
	    int c = 0;
	    if (delta >= 1) {
		delta = 0;
		for (int i = 0; i < players.length; i++) {
		    for (int j = 0; j < players[i].getElements().length; j++) {

			Element element = players[i].getElements()[j];

			if (element.isSelected()) {
			    atackAnimation(element, c);

			} else {
			    atackOutAnimation(element, c);
			}
			c++;
		    }
		}
		loopStart = System.nanoTime();

	    }

	}

    }

    /**
     * Este metodo realiza la animacion de ataque al elemento seleccionado por
     * cada jugador.
     * 
     * @param element Element isSelected = true.
     * @param c       Id para controlar el estado de movimiento.
     */
    public void atackAnimation(Element element, int c) {
	int mX = Constants.MID_WIDTH_WIN;
	int mY = Constants.MID_HEIGHT_WIN;

	int x = (int) element.getEdge().getX();
	int y = (int) element.getEdge().getY();

	double vx = Math
		.abs(element.getVelocityX() + Math.abs(speedsX[c] * 0.9));
	double vy = Math
		.abs(element.getVelocityY() + Math.abs(speedsY[c] * 0.9));

	element.setVelocityX(vx);
	element.setVelocityY(vy);

	if (stateMovingElement[c] && x < (mX)) {
	    moveRight(element);
	}

	if (stateMovingElement[c] && x > mX) {
	    moveLeft(element);
	}

	if (stateMovingElement[c] && y < mY) {
	    moveDown(element);
	}

	if (stateMovingElement[c] && y > mY) {
	    moveUp(element);

	}

	if (stateMovingElement[c]
		&& element.collision(new Rectangle(mX - 32, mY - 32, 64, 64))) {
	    stateMovingElement[c] = false;

	    element.setWidth(40);
	    element.setHeight(40);
	    element.setPointX(-130);
	    element.setPointY(-130);
	}

	if (!getStateRunAtack()) {
	    stopAtackAnimation();
	}

    }

    /**
     * Este metodo realiza la animacion para retirar el elemento del campo.
     * 
     * @param element Element a editar su posicion x, y.
     * @param c       Id para controlar el estado de movimiento.
     */
    private void atackOutAnimation(Element element, int c) {

	int x = element.getPointX();
	int y = element.getPointY();

	int w = Constants.WIDTH_WINDOW;
	int h = Constants.HEIGHT_WINDOW;

	int mW = Constants.MID_WIDTH_WIN;

	element.setVelocityY((element.getVelocityY()) + 7 * 0.9);
	element.setVelocityX((element.getVelocityX()) + 7 * 0.9);

	if (stateMovingElement[c] && element.getOption() == 'R' && y > 0) {
	    moveUp(element);
	}

	if (stateMovingElement[c] && element.getOption() == 'S'
		&& y < h - element.getHeight()) {
	    moveDown(element);
	}

	if (stateMovingElement[c] && element.getOption() == 'P') {
	    if (x < mW && x > 0) {
		moveLeft(element);
	    }

	    if (x > mW && x < w - element.getWidth()) {
		moveRight(element);
	    }
	}

	if (stateMovingElement[c] && element.getOption() == 'R'
		&& element.getPointY() <= 0) {
	    stateMovingElement[c] = false;
	}

	if (stateMovingElement[c] && element.getOption() == 'S'
		&& element.getPointY() >= h - element.getHeight()) {
	    stateMovingElement[c] = false;
	}

	if (stateMovingElement[c] && element.getOption() == 'P') {

	    if (x < mW && x <= 0) {
		stateMovingElement[c] = false;
	    }

	    if (x > mW && x >= w - element.getWidth()) {
		stateMovingElement[c] = false;
	    }

	}

	if (!getStateRunAtack()) {
	    stopAtackAnimation();
	}
    }

    /**
     * Este metodo resta la posicion Y del elemento - la velocidad Y del
     * elemento.
     * 
     * @param element Element que se modificara la posicion X o Y.
     */
    private void moveUp(Element element) {
	element.setPointY((int) (element.getPointY() - element.getVelocityY()));
    }

    /**
     * Este metodo suma la posicion Y del elemento + la velocidad Y del
     * elemento.
     * 
     * @param element
     */
    private void moveDown(Element element) {
	element.setPointY((int) (element.getPointY() + element.getVelocityY()));
    }

    /**
     * Este metodo resta la posicion X del elemento - la velocidad X del
     * elemento.
     * 
     * @param element Element que se modificara la posicion X o Y.
     */
    private void moveLeft(Element element) {
	element.setPointX((int) (element.getPointX() - element.getVelocityX()));
    }

    /**
     * Este metodo suma la posicion X del elemento + la velocidad X del
     * elemento.
     * 
     * @param element Element que se modificara la posicion X o Y.
     */
    private void moveRight(Element element) {
	element.setPointX((int) (element.getPointX() + element.getVelocityX()));
    }

    /**
     * Este metodo establece todos los elemtos de cada jugador por defecto.
     */
    public void defaultElements() {

	int c = 0;
	for (int i = 0; i < players.length; i++) {
	    for (int j = 0; j < players[i].getElements().length; j++) {
		players[i].getElements()[j].setPointX(xD[c]);
		players[i].getElements()[j].setPointY(yD[c]);
		players[i].getElements()[j].setWidth(wD[c]);
		players[i].getElements()[j].setHeight(hD[c]);

		players[i].getElements()[j].setVelocityX(0);
		players[i].getElements()[j].setVelocityY(0);

		c++;
	    }
	}

    }

    /**
     * Establece un boolean al control de movimiento para todos los elementos de
     * cada jugador.
     * 
     * @param b Booleano para establecer en el control de movimiento.
     */
    private void setStateMoving(boolean b) {
	for (int i = 0; i < stateMovingElement.length; i++) {
	    stateMovingElement[i] = b;
	}
    }

    /**
     * Busca si hay un elemento, en el control de movimiento, si un elemento aun
     * sigue en movimiento.
     * 
     * @return Boolean; TRUE si un elemento sigue en movimiento.
     */
    private boolean getStateRunAtack() {

	for (int i = 0; i < stateMovingElement.length; i++) {

	    if (stateMovingElement[i] == true) {
		return true;
	    }
	}
	return false;
    }

}
