package ec.edu.ups.model;

public class Player {

	private String name;

	/* 3 posiciones establecidas: 0 = piedra; 1 = papel; 2 = tijera */
	private Element[] elements;
	private int win;

	public Player(String name, Element[] elements) {
		super();
		this.name = name;
		this.elements = elements;
		this.win = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Element[] getElements() {
		return elements;
	}

	public void setElements(Element[] elements) {
		this.elements = elements;
	}

	public int getWin() {
		return win;
	}

	public void addWin() {
		this.win++;
	}

	public void setWin(int win) {
		this.win = win;
	}
}
