package ec.edu.ups.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ec.edu.ups.controller.statemachine.game.StartGameState;

public class StartGameGUI extends JPanel implements ActionListener {

	private StartGameState startGameState;
	private JTextField jTPlayer1;

	private JButton jBAcept;;

	public StartGameGUI(StartGameState startGameState) {
		super();
		this.startGameState = startGameState;
		initComponents();

	}

	private void initComponents() {
		setLayout(new BorderLayout());

		jTPlayer1 = new JTextField(20);

		jBAcept = new JButton("Aceptar");
		jBAcept.addActionListener(this);
		jBAcept.setActionCommand("acept");

		add(jTPlayer1, BorderLayout.NORTH);
		add(jBAcept, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "acept":

			this.startGameState.getMainMager().setCurrentPanel(1);
			this.startGameState.getMainMager().getStateManager().getGameRuleManager().getPlayers()[0]
					.setName(jTPlayer1.getText());
			break;

		default:
			break;
		}

	}

}
