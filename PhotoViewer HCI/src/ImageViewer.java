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

class ViewerActionListener implements ActionListener {
	private int i;
	private File f;
	private File[] fileList;
	
	public ViewerActionListener(int i, File f, File[] files) {
		this.setIter(i);
		this.setFile(f);
		this.setFileList(files);
	}

	
	public void actionPerformed(ActionEvent e) {
		setIter(i);
		setFile(f);
		setFileList(fileList);
	}


	public File[] getFileList() {
		return fileList;
	}


	public void setFileList(File[] fileList) {
		this.fileList = fileList;
	}


	public int getIter() {
		return i;
	}


	public void setIter(int i) {
		this.i = i;
	}


	public File getFile() {
		return f;
	}


	public void setFile(File f) {
		this.f = f;
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
		File[] fileList = null;
		int i = 0;
		File file = picker.getSelectedFile();
		
		
		
		if (result == JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(frame, file.getAbsolutePath(), "Selection",
					JOptionPane.INFORMATION_MESSAGE);
			fileList = file.listFiles();
			try {
				while( !(fileList[i].isFile()) )
					i++;
				System.out.println(fileList[i].getAbsolutePath());
				img = ImageIO.read(new File(fileList[i].getAbsolutePath()));
				imgLabel.setIcon(new ImageIcon(img));
			} catch (IOException e2) {
			
				e2.printStackTrace();
			}
		}
		
		JMenuItem mntmNewParentFile = new JMenuItem("New Parent Folder");
		mntmNewParentFile.addActionListener(new ViewerActionListener(i, file, fileList) {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser picker = new JFileChooser();
				picker.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = picker.showOpenDialog(frame);
				
				BufferedImage img;
				setIter(0);
				setFile(picker.getSelectedFile());
				setFileList(picker.getSelectedFile().listFiles());
				
				
				
				if (result == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(frame, file.getAbsolutePath(), "Selection",
							JOptionPane.INFORMATION_MESSAGE);
					setFileList(file.listFiles());
					System.out.println(getFileList()[getIter()].getAbsolutePath());
					try {
						while( !(getFileList()[getIter()].isFile()) )
							setIter(getIter() + 1);
						img = ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath()));
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
		menuBar.add(mntmNextImage);
		
		btnChooseImage.addActionListener(new ViewerActionListener(i, file, fileList) {
			public void actionPerformed(final ActionEvent e) {
				
				final JFileChooser chooser = new JFileChooser();
				final int result = chooser.showOpenDialog(frame);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					final File f = chooser.getSelectedFile();
					JOptionPane.showMessageDialog(frame, f.getAbsolutePath(), "Selection",
							JOptionPane.INFORMATION_MESSAGE);
					setFile(picker.getSelectedFile());
					setFileList(picker.getSelectedFile().listFiles());
					for(int x = 0; x < getFileList().length; x++)
						if(getFile().equals(getFileList()[x]))
							setIter(x);
					
					try {
						BufferedImage img = ImageIO.read(new File(f.getAbsolutePath()));
						imgLabel.setIcon(new ImageIcon(img));
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		mntmNewImageFile.addActionListener(new ViewerActionListener(i, file, fileList) {
			public void actionPerformed(ActionEvent e) {
				
				final JFileChooser chooser = new JFileChooser();
				final int result = chooser.showOpenDialog(frame);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					final File f = chooser.getSelectedFile();
					setFileList(f.listFiles());
					setFile(f);
					for(int x = 0; x < getFileList().length; x++)
						if(getFile().equals(getFileList()[x]))
							setIter(x);
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
		
		mntmPreviousImage.addActionListener(new ViewerActionListener(i, file, fileList) {
			public void actionPerformed(ActionEvent e) {
				
				BufferedImage img;
				setIter(getIter()-1);
				try {
					while( !(getFileList()[getIter()].isFile()) || ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath())) == null ) {
						setIter(getIter() - 1);
						if(getIter() < 0)
							setIter(getFileList().length);
					}
					
					img = ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath()));
					imgLabel.setIcon(new ImageIcon(img));
				} catch (IOException e2) {
				
					e2.printStackTrace();
				}
			}
		});
		
		mntmNextImage.addActionListener(new ViewerActionListener(i, file, fileList) {
			public void actionPerformed(ActionEvent e) {
				BufferedImage img;
				setIter(getIter()+1);
				try {
					while( !(getFileList()[getIter()].isFile()) || ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath())) == null ) {
						setIter(getIter() + 1);
						if(getIter() == getFileList().length)
							setIter(0);
					}
					
					img = ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath()));
					imgLabel.setIcon(new ImageIcon(img));
				} catch (IOException e2) {
				
					e2.printStackTrace();
				}
			}
		});
		
		btnPrevious.addActionListener(new ViewerActionListener(i, file, fileList) {
			public void actionPerformed(ActionEvent e) {
				
				BufferedImage img;
				setIter(getIter()-1);
				if (getIter() < 0)
					setIter(getFileList().length - 1);
				try {
					while( !(getFileList()[getIter()].isFile()) || ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath())) == null ) {
						setIter(getIter() - 1);
						if(getIter() < 0)
							setIter(getFileList().length - 1);
						}
					
					img = ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath()));
					imgLabel.setIcon(new ImageIcon(img));
				} catch (IOException e2) {
				
					e2.printStackTrace();
				}
			}
		});
		
		btnNext.addActionListener(new ViewerActionListener(i, file, fileList) {
			public void actionPerformed(ActionEvent e) {
				
				BufferedImage img;
				setIter(getIter()+1);
				try {
					while( !(getFileList()[getIter()].isFile()) || ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath())) == null ) {
						setIter(getIter() + 1);
						if(getIter() == getFileList().length)
							setIter(0);
					}
					
					img = ImageIO.read(new File(getFileList()[getIter()].getAbsolutePath()));
					imgLabel.setIcon(new ImageIcon(img));
				} catch (IOException e2) {
				
					e2.printStackTrace();
				}
				
			}
		});
		
		
		frame.setVisible(true);

	}

}
