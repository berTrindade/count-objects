package dip;

import java.awt.image.BufferedImage;

public class Transformations 
{
	private BufferedImage image;
	
	public Transformations(BufferedImage image)
	{
		this.image = image;
	}
	
//	private BufferedImage rotate(int angle)
//	{
//		int y = image.getHeight();
//		int x = image.getWidth();
//		
//		double sin = Math.sin(angle);
//		double cos = Math.cos(angle);
//		
//		int x0 = 0;
//		int y0 = 0;
//		
//		for(int i = 0; i < y; i++)
//		{
//			for(int j = 0; j < x; j++)
//			{
//				x0 = (int) ((i * cos) - (j * sin));
//				y0 = (int) ((j * sin) - (i * cos));
//			}
//		}
//				
//		BufferedImage rotated = new BufferedImage(x0, y0, BufferedImage.TYPE_INT_RGB);
//		
//		return rotated; 
//	}
}
