package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ec.edu.ups.controller.statemachine.game.StartGameState;
import ec.edu.ups.main.Constants;

public class StartGameGUI extends JPanel implements ActionListener {

	private StartGameState startGameState;

	private JLabel text1;
	private JLabel text2;
	private JLabel text3;
	private JTextField player1;
	private JTextField player2;
	private JTextField turns;
	private JButton start;

	public StartGameGUI(StartGameState startGameState) {
		super();
		this.startGameState = startGameState;
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		JPanel p1 = new JPanel() {

			public void paint(Graphics g) {
				Dimension tamano = getSize();
				ImageIcon imagen = new ImageIcon(getClass().getResource(Constants.BACKGROUND_PATH));

				g.drawImage(imagen.getImage(), 0, 0, tamano.width, tamano.height, null);
				setOpaque(false);
				super.paint(g);

			}
		};

		p1.setLayout(new BorderLayout());

		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());

		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());

		text1 = new JLabel("Player 1: ");
		text1.setFont(new Font(null, Font.BOLD, 20));
		text1.setForeground(Color.WHITE);
		p3.add(text1);

		player1 = new JTextField(10);
		player1.setFont(new Font(null, Font.PLAIN, 20));
		p3.add(player1);

		JLabel space1 = new JLabel(" ");
		p3.add(space1);

		JLabel space2 = new JLabel(" ");
		p3.add(space2);

		text3 = new JLabel("Turnos: ");
		text3.setFont(new Font(null, Font.BOLD, 20));
		p3.add(text3);

		turns = new JTextField(5);
		turns.setFont(new Font(null, Font.PLAIN, 20));
		p3.add(turns);

		JLabel space3 = new JLabel(" ");
		p3.add(space3);

		JLabel space4 = new JLabel(" ");
		p3.add(space4);

		text2 = new JLabel("Player 2: ");
		text2.setFont(new Font(null, Font.BOLD, 20));
		p3.add(text2);

		player2 = new JTextField(10);
		player2.setFont(new Font(null, Font.PLAIN, 20));
		p3.add(player2);

		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());

		start = new JButton("OK!");
		start.setForeground(Color.white);
		start.setBackground(Color.DARK_GRAY);
		start.addActionListener(this);
		start.setActionCommand("comeon");
		start.setFont(new Font(null, Font.BOLD, 25));
		p4.add(start);

		p2.add(p3, BorderLayout.CENTER);
		p2.add(p4, BorderLayout.SOUTH);

		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);

		p1.add(p2, BorderLayout.SOUTH);

		add(p1, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String action = e.getActionCommand();
			System.out.println("Comando: " + action);

			switch (action) {
			case "comeon":

				String play1 = player1.getText();
				String play2 = player2.getText();

				if ((play1.length() > 10) || (play2.length() > 10)) {
					JOptionPane.showMessageDialog(null, "Max. 10 Caracteres.", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					if ((play1.length() == 0) || (play2.length() == 0)) {
						JOptionPane.showMessageDialog(null, "Ingrese el nombre para el jugador.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int number = Integer.parseInt(turns.getText());
						if (number <= 0) {
							JOptionPane.showMessageDialog(null,
									"Nadie: \n" + "Absolutamente nadie: \n" + "La profe: " + number, "FELICIDADES XD!!",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							this.startGameState.getMainMager().setCurrentPanel(1);
							this.startGameState.getMainMager().getStateManager().getGameRuleManager()
									.setRoundNumber(number);
							this.startGameState.getMainMager().getStateManager().getGameRuleManager().getPlayers()[0]
									.setName(player1.getText().toUpperCase());
							this.startGameState.getMainMager().getStateManager().getGameRuleManager().getPlayers()[1]
									.setName(player2.getText().toUpperCase());
						}
					}
				}

				break;

			default:
				break;
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, "Ingrese solo números enteros.", "ERROR de Turnos", 2);;
		}
		

	}

}
