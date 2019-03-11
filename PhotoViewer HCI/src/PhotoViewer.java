import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class PhotoViewer {

	public static void main(final String[] args) {
		final JFrame frame = new JFrame("Photo Viewer");
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(new BorderLayout());
		final JButton b = new JButton("Click Me");
		final JLabel l = new JLabel("You haven't clicked the button yet");
		b.addActionListener(new ActionListener() {
			int counter = 0;

			public void actionPerformed(final ActionEvent e) {
				counter++;
				l.setText("You clicked the button " + counter + " times");
			}
		});
		frame.getContentPane().add(b, BorderLayout.NORTH);
		frame.getContentPane().add(l, BorderLayout.SOUTH);
		
		final JLabel imgLabel = new JLabel();
		JScrollPane scrollPane = new JScrollPane(imgLabel);
		//File imgFile;

		final JButton open = new JButton("Open");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// JOptionPane has lots of basic dialogs
				final JFileChooser chooser = new JFileChooser();
				final int result = chooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					final File f = chooser.getSelectedFile();
					JOptionPane.showMessageDialog(frame, f.getAbsolutePath(), "Selection",
							JOptionPane.INFORMATION_MESSAGE);
					
					try {
						BufferedImage img = ImageIO.read(new File(f.getAbsolutePath()));
						imgLabel.setIcon(new ImageIcon(img));
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		
		frame.getContentPane().add(open, BorderLayout.WEST);
		
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		frame.setVisible(true);
	}
}
