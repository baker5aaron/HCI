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
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

private class ViewerActionListener implements ActionListener {
	private int i;
	
	public ViewerActionListener(int i) {
		this.i = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}

public class ImageViewer {

	public static void main(String[] args) {
		final JFrame frame = new JFrame("Photo Viewer");
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][grow][][][][][][][]", "[][][][][grow][][][][]"));
		frame.setSize(640,480);
		JLabel imgLabel = new JLabel();
		
		JScrollPane scrollPane = new JScrollPane(imgLabel);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(scrollPane, "cell 0 0 14 8,grow");
				
		JButton btnPrevious = new JButton("Previous");
		frame.getContentPane().add(btnPrevious, "cell 0 8");
		
		JButton btnChooseImage = new JButton("Choose Image");
		frame.getContentPane().add(btnChooseImage, "cell 6 8,alignx center");
		
		JButton btnNext = new JButton("Next");
		frame.getContentPane().add(btnNext, "cell 13 8");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("   File   ");
		menuBar.add(mnFile);
		
		
		JFileChooser picker = new JFileChooser();
		picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = picker.showOpenDialog(frame);
		
		BufferedImage img;
		File[] files;
		int i = 0;
		File f = picker.getSelectedFile();
		
		
		
		if (result == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(frame, f.getAbsolutePath(), "Selection",
					JOptionPane.INFORMATION_MESSAGE);
			files = f.listFiles();
			System.out.println(files[i].getAbsolutePath());
			try {
				while( !(files[i].isFile()) )
					i++;
				System.out.println(files[i].getAbsolutePath());
				img = ImageIO.read(new File(files[i].getAbsolutePath()));
				imgLabel.setIcon(new ImageIcon(img));
			} catch (IOException e2) {
			
				e2.printStackTrace();
			}
		}
		
		JMenuItem mntmNewParentFile = new JMenuItem("New Parent Folder");
		mntmNewParentFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser picker = new JFileChooser();
				picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = picker.showOpenDialog(frame);
				
				BufferedImage img;
				File[] files;
				int i = 0;
				File f = picker.getSelectedFile();
				
				
				
				if (result == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(frame, f.getAbsolutePath(), "Selection",
							JOptionPane.INFORMATION_MESSAGE);
					files = f.listFiles();
					System.out.println(files[i].getAbsolutePath());
					try {
						while( !(files[i].isFile()) )
							i++;
						System.out.println(files[i].getAbsolutePath());
						img = ImageIO.read(new File(files[i].getAbsolutePath()));
						imgLabel.setIcon(new ImageIcon(img));
					} catch (IOException e2) {
					
						e2.printStackTrace();
					}
				}
			}
		});
		
		mnFile.add(mntmNewParentFile);
		
		JMenuItem mntmNewImageFile = new JMenuItem("New Image File");
		
		mnFile.add(mntmNewImageFile);
		
		JMenuItem mntmPreviousImage = new JMenuItem("Previous Image");
		mntmPreviousImage.setMaximumSize(new Dimension(100,50));
		menuBar.add(mntmPreviousImage);
		
		JMenuItem mntmNextImage = new JMenuItem("Next Image");
		mntmNextImage.setMaximumSize(new Dimension(100,50));
		//mntmNextImage.add(files);
		menuBar.add(mntmNextImage);
		
		
		mntmNewParentFile.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
				final JFileChooser chooser = new JFileChooser();
				final int result = chooser.showOpenDialog(frame);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					final File f = chooser.getSelectedFile();
					JOptionPane.showMessageDialog(frame, f.getAbsolutePath(), "Selection",
							JOptionPane.INFORMATION_MESSAGE);
					//files = f.getParentFile().listFiles();
					
					try {
						BufferedImage img = ImageIO.read(new File(f.getAbsolutePath()));
						imgLabel.setIcon(new ImageIcon(img));
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnChooseImage.addActionListener(new ActionListener() {
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
		
		mntmNewImageFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		
		frame.setVisible(true);

	}

}
