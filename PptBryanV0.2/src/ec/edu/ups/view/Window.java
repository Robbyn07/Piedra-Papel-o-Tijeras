
package ec.edu.ups.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Window extends JFrame {

	private String title;

	public Window(final String title, final Screen game, int width, int height) {
		this.title = title;
		configWindow(game, width, height);
	}

	private void configWindow(Screen game, int width, int height) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(width, height);
		setResizable(false);
		// setIconImage(image);
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
//		setUndecorated(true);

		setVisible(true);

		setLocationRelativeTo(null);

		// pack();

	}

}
