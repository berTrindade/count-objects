package dip;

import java.awt.image.BufferedImage;

public class Calculations 
{
	private BufferedImage image;
	private double average;
	private double variance;
	private double median;
	
	public Calculations(BufferedImage image) 
	{
		this.image = image;
		this.average = 0.0d;
		this.variance = 0.0d;
		
		System.out.println("Average: " + getAverage() + "\n" + 
						   "Variance: " + getVariance() + "\n" + 
						   "Median: " + getMedian());
	}

	/*
	 * Static Methods Cannot Directly Access Instance Variables and Instance Methods.
	 * URL: https://www.oreilly.com/library/view/javatm-how-to/9780133813036/ch08lev2sec24.html
	 */
	private double getAverage()
	{
		int adder = 0;
		
		int y = image.getHeight();
		int x = image.getWidth();
		
		for(int i = 0; i < x; i++)
			for(int j = 0; j < (y/2); j++)
				adder += image.getRGB(i, j);
		
		int quantity = x * y;
			
		average = adder / quantity;
				
		return average;
	}
	
	private double getVariance()
	{
		int adder = 0;
		
		int y = image.getHeight();
		int x = image.getWidth();
		
		double aux = 0.0f;
		
		for(int i = 0; i < x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				aux = (image.getRGB(i, j) - average);
				adder += (aux * aux);
			}
		}
		
		int quantity = x * y;
		
		variance = adder / quantity;
		
		return variance;
	}
	
	private int[] getArrayOfAnImage()
	{
		int y = image.getHeight();
		int x = image.getWidth();
		
		int[] totalOfPixels = new int[x * y];
		int position = 0;
		
		for(int i = 0; i < x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				totalOfPixels[position] = image.getRGB(i, j);
				position++;
			}
		}
		return totalOfPixels;
	}
	
	private double getMedian()
	{
		int[] array = getArrayOfAnImage();
		
		if(array.length % 2 == 0)
			median = array[array.length / 2] >> 16 & 0xFF;
		else
			median = ((array[array.length / 2] + (array[array.length / 2] + 1)) / 2) >> 16 & 0xFF; 
		
		return median;
	}
	
//	private int getMean()
//	{
//		int[] array = getArrayOfAnImage();
//		
//		int y = image.getHeight();
//		int x = image.getWidth();
//	
//		int mean = 0;
//		int total = 0;
//		
//		for(int i = 0; i < x; i++)
//			for(int j = 0; j < y; j++)
//				array[image.getRGB(i, j)]++;
//		
//		for(int i = 0; i < array.length; i++)
//			if(array[i] > total)
//			{
//				total = array[i];
//				mean = t
//			}			
//	}
	
//	public void displayStatistics(double average)
//	{
//		
//	}
}
