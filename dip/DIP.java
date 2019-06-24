package dip;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DIP extends JFrame 
{
	private BufferedImage image;
	private JLabel label;
	
	public static void main(String[] args)
	{
		new DIP();
	}
	
	public DIP()
	{
		createAndShowGUI();
		
		label = new JLabel();
	}
	
	private void createAndShowGUI()
	{
		// Sets title to the frame
		this.setTitle("Digital Image Processing (DIP)");
		
		// Setting close operation
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Sets 1024 width and 768 height
		this.setSize(1024, 768);
		
		this.setJMenuBar(createMenuBar());
		
		// Maximizes the Frame to fill the entire screen 
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		// Makes the frame visible
		this.setVisible(true);
	}
	
	private JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createMenu());
		menuBar.add(createMenuTransformations());
		menuBar.add(createMenuFilters());
		menuBar.add(createMenuBinary());
		
		return menuBar;
	}
	
	private JMenu createMenu()
	{
		JMenu menu = new JMenu("File");
		
		menu.setMnemonic(KeyEvent.VK_A);
		
		menu.add(createMenuItem());
		
		return menu;
	}
	
	private JMenu createMenuTransformations()
	{
		JMenu menu = new JMenu("Transformations");
		
		menu.setMnemonic(KeyEvent.VK_A);
		
		menu.add(createMenuItemTransformations());
		
		return menu;
	}
	
	private JMenuItem createMenuItemTransformations()
	{
		JMenuItem menuItem = new JMenuItem("Rotate");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
//			image = rotate(180, image);
//			image = enlarge(image, 2);
//			image = reduce(image);
			//image = translation(image, 30, 20);
			
			new Islands2(image);
			
			label.setIcon(new ImageIcon(image));

			revalidate();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}	
	
	private JMenuItem createMenuItem()
	{
		JMenuItem menuItem = new JMenuItem("Open file");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
			image = openFile();
			
			JPanel panel = new JPanel();
			
			label.setIcon(new ImageIcon(image));
		    panel.add(label);
		    
		    add(panel);
		    
		    revalidate();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}
	
	private JMenu createMenuBinary()
	{
		JMenu menu = new JMenu("Binarization");
		
		menu.setMnemonic(KeyEvent.VK_A);
		
		menu.add(createMenuItemBinary());
		
		return menu;
	}
	
	private JMenuItem createMenuItemBinary()
	{
		JMenuItem menuItem = new JMenuItem("Binarization");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
			image = Binarization.binarize(image);
			
			label.setIcon(new ImageIcon(image));

			revalidate();
			repaint();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}
	
	private JMenuItem createMenuItemStatistics()
	{
		JMenuItem menuItem = new JMenuItem("Statistics");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{
			new Calculations(image);			
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}
	
	private JMenu createMenuFilters()
	{
		JMenu menu = new JMenu("Filters");
		
		menu.setMnemonic(KeyEvent.VK_A);
		
		menu.add(createMenuItemFilters0());
		menu.add(createMenuItemFilters1());
		menu.add(createMenuItemFilters2());
		menu.add(createMenuItemFilters3());
		menu.add(createMenuItemFilters4());
		
		return menu;
	}
	
	private JMenuItem createMenuItemFilters0()
	{
		JMenuItem menuItem = new JMenuItem("0");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
			image = ApplyBrightnessAndConstrast(image, 40, 1.4);
			
			label.setIcon(new ImageIcon(image));

			revalidate();
			repaint();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}
	
	private JMenuItem createMenuItemFilters1()
	{
		JMenuItem menuItem = new JMenuItem("1");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
			image = translation(image, 30, 20);
			
			label.setIcon(new ImageIcon(image));

			revalidate();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}	
	
	private JMenuItem createMenuItemFilters2()
	{
		JMenuItem menuItem = new JMenuItem("2");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
			image = translation(image, 30, 20);
			
			label.setIcon(new ImageIcon(image));

			revalidate();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}
	
	private JMenuItem createMenuItemFilters3()
	{
		JMenuItem menuItem = new JMenuItem("3");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
			image = translation(image, 30, 20);
			
			label.setIcon(new ImageIcon(image));

			revalidate();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}
	
	private JMenuItem createMenuItemFilters4()
	{
		JMenuItem menuItem = new JMenuItem("4");
		
		menuItem.addActionListener((ActionEvent e) -> 
		{ 
			image = translation(image, 30, 20);
			
			label.setIcon(new ImageIcon(image));

			revalidate();
		});
		
		menuItem.setMnemonic(KeyEvent.VK_A);
			
		return menuItem;
	}
	
	// https://www.geeksforgeeks.org/translation-objects-computer-graphics-reference-added-please-review/
	private BufferedImage translation(BufferedImage input, int dx, int dy) 
	{
        int weight = input.getWidth();
        int height = input.getHeight();
        
        BufferedImage reducedImage = new BufferedImage(weight, height, input.getType());
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < weight; x++)
            {
            	int x1 = (int) (x + dx);
            	int y1 = (int) (y + dy);
            	
            	if (x1 >= 0 && x1 < weight && y1 >= 0 && y1 < height)
            		reducedImage.setRGB(x, y, input.getRGB(x1, y1));
            }
        }
        
        return reducedImage;
    }
	
	// https://algs4.cs.princeton.edu/11model/Scale.java.html
	private BufferedImage reduce(BufferedImage input) 
	{
        int height = input.getHeight();
        int width = input.getWidth();
        
        int nHeight = (int) (input.getHeight() * 0.5);
        int nWidth = (int) (input.getWidth() * 0.5);
        
        BufferedImage reducedImage = new BufferedImage(nWidth, nHeight, input.getType());
        	
        for (int y = 0; y < nHeight; y++)
        {
            for (int x = 0; x < nWidth; x++)
            {
            	int x1 = (int) (x * width / nWidth);
            	int y1 = (int) (y * height / nHeight);
            	
                reducedImage.setRGB(x, y, input.getRGB(x1, y1));	
            }
        }
        
        return reducedImage;
    }

	// Pixel (aglutinação de Picture Element, sendo Pix a abreviação de Picture) é o nome dado ao menor elemento de uma imagem digital, o qual é composto por um conjunto de 3 pontos: vermelho, verde e azul (RGB).
    private BufferedImage enlarge(BufferedImage input, int times) 
    {
        int weight = times * input.getWidth();
        int height = times * input.getHeight();
        
        BufferedImage enlargedImage = new BufferedImage(weight, height, input.getType());
        
        for (int y = 0; y < height; y++)
            for (int x = 0; x < weight; x++)
                enlargedImage.setRGB(x, y, input.getRGB(x/times, y/times));
        
        return enlargedImage;
    }
	
	// Rotate image a given number of degrees counterclockwise
	private BufferedImage rotate(double angle, BufferedImage input)
	{
		int height = input.getHeight();
		int width = input.getWidth();	
		
		/*
		 * If we had a square that has four corners at (0,0), (1,0), (0,1) and (1,1) we would want to move it so that the CENTER of our square is now at (0,0). First we have to figure out where the center is, in this case it's pretty simple, the center resides at (0.5, 0.5).
		 * These lines below represent the Coordinates of center of rotation
		 * URL: https://www.reddit.com/r/learnprogramming/comments/4cc1pz/java_how_do_i_rotate_a_shape/
		 * ..	
		 * The indices of an image can range from 0 to 255. The total number of colors is limited to 256 colors. That's why I subtracted 1 from both dimensions
		 */
		double x0 = 0.5 * (width  - 1);   
	    double y0 = 0.5 * (height - 1); 
		
		/*
		 * Angle of rotation (radians, counterclockwise)
		 * Convert x degrees to radians
		 */
		angle = Math.toRadians(angle); 
		
		double sin = Math.sin(angle);	
		double cos = Math.cos(angle);

		BufferedImage rotated = new BufferedImage(width, height, input.getType());
		
		// Reading all the image
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				// Now we can simply subtract that from every other point we had for it to "move" them so that the center of the square is now at (0, 0). That means that essentially we subtract (0.5, 0.5) from every other point and we end up having (-0.5, -0.5), (0.5, -0.5), (-0.5, 0.5), (0.5, 0.5).
				double xt = x - x0;
				double yt = y - y0;
				
				/*
				 * Rotating Points around an Arbitrary Center:
				 * Those lines down below represent the Coordinates of point after rotation
				 * URL: http://danceswithcode.net/engineeringnotes/rotations_in_2d/rotations_in_2d.html
				 */
				int x1 = (int) ((xt * cos - yt * sin) + x0);
				int y1 = (int) ((xt * sin + yt * cos) + y0);
				
				if (x1 >= 0 && x1 < width && y1 >= 0 && y1 < height) 
					rotated.setRGB(x, y, input.getRGB(x1, y1));
			}	
		}
		
		return rotated; 
	}
	
	public BufferedImage ApplyBrightnessAndConstrast(BufferedImage input, int b, double c) 
	{
		int height = input.getHeight();
		int width = input.getWidth();
		
		BufferedImage destination = new BufferedImage(width, height, input.getType());
        
        for (int x = 0; x < width; x++) 
        {
            for (int y = 0; y < height; y++) 
            {
                int newPixelValue = (int)(c * input.getRGB(x, y) + b);
                
                // //R,G,B values which are out of the range 0 to 255 should set to 0 or 255
                if (newPixelValue > 255) 
                    newPixelValue = 255;
                
                if (newPixelValue < 0)
                    newPixelValue = 0;
                
                destination.setRGB(x, y, newPixelValue);
            }
        }
          
        return destination;
    }
	
	public BufferedImage GaussianoFilter(BufferedImage input)
    {
        int[][] pattern = new int[][]{{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
        int i, j;
        double v, z = 0;
        byte novoValor;
        
        int height = input.getHeight();
		int width = input.getWidth();
		
        int widthA = width - 1;
        int heightA = height - 1;
		
		BufferedImage destination = new BufferedImage(width, height, input.getType());
        
        for (int y = 1; y < heightA; y++)
        {
            for (int x = 1; x < widthA; x++)
            {
                z = 0;
                for (i = 0; i < 3; i++)
                {
                    for (j = 0; j < 3; j++)
                    {
                        
                    }
                }
            }
        }
        
        return image;
    }
	
	private BufferedImage openFile()
	{
		JFileChooser chooser = new JFileChooser();
		BufferedImage selectedFile = null;
		
		int result = chooser.showOpenDialog(getParent());
		
		if(result == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				selectedFile = convertoToGrayScale(new FileInputStream(chooser.getSelectedFile()));
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		} else
			System.out.println("No selection!");
		
		return selectedFile;
	}
	
	// Luminosity method to convert colored image to Grayscale: https://bostjan-cigan.com/java-color-image-to-grayscale-conversion-algorithms/
	private BufferedImage convertoToGrayScale(FileInputStream file)
	{
		BufferedImage image = null;
		
		// Read image
		try
		{
			image = ImageIO.read(file);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		// Getting height and weight of the image
		int height = image.getHeight();
		int weight = image.getWidth();
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < weight; x++)
			{
				Color color = new Color(image.getRGB(x, y));
				
				int red = (int) (color.getRed() * 0.21);
				int green = (int) (color.getGreen() * 0.72);
				int blue = (int) (color.getBlue() * 0.07);
				
				// Get pixels by R, G, B
		        int luminosity = (red + green + blue);
				red = green = blue = luminosity;
				
				Color gray = new Color(red, green, blue);
				image.setRGB(x, y, gray.getRGB());
			}
		}
		
		return image; 
	}
	
	
}
