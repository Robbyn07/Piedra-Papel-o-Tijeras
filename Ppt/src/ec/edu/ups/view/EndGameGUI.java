package ec.edu.ups.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ec.edu.ups.controller.statemachine.game.GameWinnerManager;
import ec.edu.ups.main.Constants;

public class EndGameGUI extends JPanel implements ActionListener {

    private JLabel winnerMessage;
    private JLabel winner;
    private JButton boton;
    private String player;
    private String space = "";
    // ****
    private ImageIcon imagen;
    String path;
    // ****
    private GameWinnerManager gameWinnerManager;

    private String[] final_images;

    public EndGameGUI(GameWinnerManager gameWinnerManager) {
	// setSize(500,500);
	path = "/ec/edu/ups/resources/textures/pptarmas.png";
	final_images = new String[4];
	images();
	// ****
	setImagePath();
	// this.path = Constants.RING_PATH;
	// ****
	this.gameWinnerManager = gameWinnerManager;
	// player = p1.getName();
//
//	initComponets();

    }

    private void spaces() {
	space = "";
	int l = 13 - player.length();
	if (l > 0) {
	    for (int i = 0; i < l; i++) {
		space = space + " ";
	    }
	}

    }

    private void initComponets() {

	setLayout(null);

//	winnerMessage = new JLabel("AND THE WINNER IS:");
//	winner = new JLabel(space + player + "!!!");
//
//	winnerMessage.setFont(new Font("Serif", Font.BOLD, 90));
//	winner.setFont(new Font("Serif", Font.BOLD, 120));
//
////	winnerMessage.setBackground(new Color(92, 145, 184));
//	winnerMessage.setForeground(Color.white);
//	winner.setForeground(Color.white);
////	winnerMessage.setOpaque(true);

	boton = new JButton("INICIO");
	boton.setForeground(Color.white);
	boton.setActionCommand("volver");
	boton.addActionListener(this);
	boton.setBackground(new Color(92, 145, 184));
	// boton.setBounds(getSize().width / 2 - 30, getSize().height - 20, 60,
	// 10);
	boton.setBounds(2, 2, 100, 70);
//	add(winnerMessage, BorderLayout.NORTH);
//	add(winner, BorderLayout.CENTER);
	add(boton);

	setName("Panel Final");
	System.out.println("IN");
	setOpaque(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String accion = e.getActionCommand();

	switch (accion) {
	case "volver":
	    try {
		// VOLVER A LA VENTANA INICIAL DEL JUEGO
		this.gameWinnerManager.getMainMager().setCurrentPanel(0);
	    } catch (Exception e2) {
		System.out.println("error");
	    }

	    break;
	default:
	    System.out.println("Ya valio, no deberia haber llegado hasta aqui");
	    break;
	}
    }

    // ****
//158,39,13
    public void paint(Graphics g) {

	g.fillRect(0, 0, getSize().width, getSize().height);

	Dimension tamano = getSize();
	imagen = new ImageIcon(getClass().getResource(path));

	g.setColor(Color.BLACK);
	g.fillRect((getSize().width / 2) - (imagen.getIconWidth() / 2) - 4, 2,
		imagen.getIconWidth() + 8, imagen.getIconHeight() + 8);

	g.drawImage(imagen.getImage(),
		(getSize().width / 2) - (imagen.getIconWidth() / 2), 6,
		imagen.getIconWidth(), imagen.getIconHeight(), null);

	System.out.println(
		"TOTAL: " + (getSize().height - imagen.getIconHeight()));
	System.out.println("VENTANA: " + getSize().height);
	System.out.println("IMAGEN: " + imagen.getIconHeight());

	g.setColor(new Color(92, 145, 184));
	g.fillRect(20, imagen.getIconHeight() + 100, getSize().width - 40,
		(Constants.FULL_HEIGHT_WINDOW - imagen.getIconHeight()) - 120);

	Font font = new Font("Serif", 0, 70);
	g.setFont(font);
	g.setColor(Color.WHITE);

	g.setFont(font);
	g.drawString(player, 40, imagen.getIconHeight() + 180);

	super.paint(g);
    }

    // ****

    public void setGameWinnet(String gameWinner) {
	setImagePath();
//	player = gameWinner;
	player = "Y EL GANADOR ES: " + gameWinner;
	spaces();
	this.removeAll();
//	this.revalidate();
//	this.repaint();
	initComponets();

    }

    private void images() {

	final_images[0] = "/ec/edu/ups/resources/textures/pptarmas.jpg";
	final_images[1] = "/ec/edu/ups/resources/textures/pptavatar.jpg";
	final_images[2] = "/ec/edu/ups/resources/textures/pptpegadostodos.jpg";
	final_images[3] = "/ec/edu/ups/resources/textures/pptpensando.png";

    }

    private void setImagePath() {
	Random r = new Random();
	int valorDado = r.nextInt(3);
	this.path = final_images[valorDado];
    }

}
