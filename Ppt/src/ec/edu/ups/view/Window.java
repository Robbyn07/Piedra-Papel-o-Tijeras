
package ec.edu.ups.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

    private String title;
    private JPanel mainPanel;

    public Window(final String title, int width, int height) {
	this.title = title;
	configWindow(width, height);
    }

    private void configWindow(int width, int height) {
	setTitle(title);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	setSize(width, height);
	setResizable(false);
	// setIconImage(image);
	setLayout(new BorderLayout());

	mainPanel = new JPanel();
	mainPanel.setLayout(new BorderLayout());

	add(mainPanel, BorderLayout.CENTER);
//		setUndecorated(true);

	setVisible(true);

	setLocationRelativeTo(null);
//
//	pack();

    }

    public void setPanel(JPanel jpanel) {
	mainPanel.removeAll();
	mainPanel.add(jpanel, BorderLayout.CENTER);
	jpanel.repaint();
	jpanel.revalidate();
	mainPanel.repaint();

	setLocationRelativeTo(null);

    }

}
