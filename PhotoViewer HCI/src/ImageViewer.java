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
import javax.swing.SpringLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

public class ImageViewer {

	public static void main(String[] args) {
		final JFrame frame = new JFrame("Photo Viewer");
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][grow][][][][][][][]", "[][][][][grow][][][][]"));
		
		JScrollPane scrollPane = new JScrollPane();
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
		
		JMenuItem mntmNewParentFile = new JMenuItem("New Parent File");
		mnFile.add(mntmNewParentFile);
		
		JMenuItem mntmNewImageFile = new JMenuItem("New Image File");
		mnFile.add(mntmNewImageFile);
		
		JMenuItem mntmPreviousImage = new JMenuItem("Previous Image");
		menuBar.add(mntmPreviousImage);
		
		JMenuItem mntmNextImage = new JMenuItem("Next Image");
		menuBar.add(mntmNextImage);
		
		frame.setVisible(true);

	}

}
