
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
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PhotoViewer {

	public static void main(final String[] args) {
		final JFrame frame = new JFrame("Photo Viewer");
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		final JLabel imgLabel = new JLabel();
		JScrollPane scrollPane = new JScrollPane(imgLabel);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 23, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 59, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 427, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 624, SpringLayout.WEST, frame.getContentPane());
		@SuppressWarnings("unused")
		File imgFile;

		final JButton open = new JButton("Open");
		springLayout.putConstraint(SpringLayout.SOUTH, open, 238, SpringLayout.NORTH, frame.getContentPane());
		open.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
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
		
		frame.getContentPane().add(open);
		
		frame.getContentPane().add(scrollPane);
		
		JButton nextBtn = new JButton("Next");
		springLayout.putConstraint(SpringLayout.WEST, nextBtn, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, open, 0, SpringLayout.WEST, nextBtn);
		springLayout.putConstraint(SpringLayout.SOUTH, nextBtn, -21, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(nextBtn);
		
		JButton prevBtn = new JButton("Previous");
		springLayout.putConstraint(SpringLayout.NORTH, open, 25, SpringLayout.SOUTH, prevBtn);
		frame.getContentPane().add(prevBtn);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmChooseFile = new JMenuItem("Choose File");
		mnFile.add(mntmChooseFile);
		
		JMenuItem mntmChooseImage = new JMenuItem("Choose Image");
		mnFile.add(mntmChooseImage);
		
		JMenuItem mntmPrevious = new JMenuItem("Previous");
		menuBar.add(mntmPrevious);
		
		JMenuItem mntmNext = new JMenuItem("Next");
		menuBar.add(mntmNext);

		frame.setVisible(true);
	}
}
