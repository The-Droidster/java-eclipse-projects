package sk.swing.firstapp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FirstSwingApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {// usage of inline anonymous
													// class implementing
													// Runnable
			// All GUI updates must be done in the event dispatch thread. If
			// you're operating in a different thread, doing a GUI update in
			// invokeLater yanks it out of your thread and into the event
			// dispatch thread.
			@Override
			public void run() {
				new FirstSwingApp(); // Create main class object

			}
		});

	}

	public FirstSwingApp() {
		createFrame(); // constructor to call Frame creation
	}

	private void createFrame() { // create Frame

		// set native OS UI
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
				| InstantiationException e) {

		}
		JFrame frame = new JFrame("First Swing App!");
		// frame.setSize(400, 400); //use pack() instead.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(); // Create Panel
		frame.add(panel);
		placePanelComponents(panel);

		frame.pack(); // Meaningful with proper use of a LayoutManager, else use
						// setSize()
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // centers the frame on the screen
		// To properly center the window, you should invoke this method after
		// the window size has been set.
		frame.setVisible(true);
	}

	private static void placePanelComponents(JPanel panel) { // Add components
																// to Panel

		JLabel label1 = new JLabel("Hello World!");
		label1.setToolTipText("Just a ToolTip!");
		panel.add(label1);

	}
}
