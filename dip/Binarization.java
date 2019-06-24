package dip;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Binarization
{	
	public static BufferedImage binarize(BufferedImage original) 
	{
        int red;
        int newPixel;
 
        int threshold = otsuTreshold(original);
 
        /*
         * Go through all the pixels of the image, 
         * checking only the first pixel value (because it is grayscaled), 
         * if the pixel value exceeds the threshold we set it to 255 else we set it to 0. And this is it, the binarized image is created.
         */
        BufferedImage binarized = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
 
        for(int i = 0; i< original.getWidth(); i++) 
        {
            for(int j = 0; j < original.getHeight(); j++) 
            {
                // Get pixels
                red = new Color(original.getRGB(i, j)).getRed();
                
                newPixel = (red > threshold) ? 255 : 0;
                
                // https://www.dreamincode.net/forums/topic/216615-converting-rgb-to-integer/
                Color pixel = new Color(newPixel, newPixel, newPixel);
                
                binarized.setRGB(i, j, pixel.getRGB()); 
            }
        }
 
        return binarized;
    }
	
	/*
	 *  Get binary threshold using Otsu's method
	 *  His a segmenting algorithm: Meaning we will use this algorithm for separating the object to the background (https://ainewbie.wordpress.com/2016/11/10/otsu-thresholding-java/)
	 *  Objective: maximize inter-class variance between object and background
	 *  https://medium.com/@hbyacademic/otsu-thresholding-4337710dc519
	 *  
	 *  Check this: https://github.com/k-sae/HCI-Lab/blob/master/ImageProccessing/Segmentations%20algos/Otsu%20Thresholding/src/Otsu_Thresholder/Control/Thresholder.java
	 */
    private static int otsuTreshold(BufferedImage original) 
    {
    	// Create a histogram of the pixels value
        int[] histogram = imageHistogram(original);
        
        // Total number of pixels
        int total = original.getHeight() * original.getWidth();
 
        // Get sum of all pixel intensities
        double sum = sumIntensities(histogram);
     
        // Background
        double weightBackground = 0.0; // q0
        double meanBackground = 0.0; // u1
        double sumBackground = 0.0;
        
        // Foreground
        double weightForeground = 0.0; // q1
        double meanForeground = 0.0; // u2
	   
        double maximumVariance = 0;
        int thresholdValue = 0;
 
        for(int i = 0; i < 256; i++) 
        {
        	// Weight Background 
            weightBackground += histogram[i];
            
            if(weightBackground == 0) 
            	continue;
            
            // Weight Foreground
            weightForeground = total - weightBackground;
 
            if(weightForeground == 0) 
            	break;
 
            // Get sum of until current pixel intensities
            sumBackground += (double) (i * histogram[i]);
            
            // Mean Background
            meanBackground = sumBackground / weightBackground;
            
            // Mean Foreground
            meanForeground = (sum - sumBackground) / weightForeground; 
            
            // Calculate Between Class Variance
            double varianceBetween = (double) weightBackground * (double) weightForeground * (meanBackground - meanForeground) * (meanBackground - meanForeground);
 
            // Check if new maximum found
            if(varianceBetween > maximumVariance) 
            {
            	maximumVariance = varianceBetween;
            	thresholdValue = i;
            }
        }
 
        return thresholdValue;
 
    }

	// Returns sum of all the pixel intensities in image
    private static double sumIntensities(int[] histogram) 
    {
        double sum = 0;
        
        for (int i = 0; i < histogram.length; i++)
           sum += i * histogram[i];
        
        return sum;
     }
	
    // Create image histogram 
	private static int[] imageHistogram(BufferedImage input)
	{
		int[] histogram = new int[256];
		
		for(int x = 0; x < input.getWidth(); x++)
		{
			for(int y = 0; y < input.getHeight(); y++)
			{
				// Assuming an image is grayscale (i.e. RGB all the same). So we will only read the Red channel
				int red = new Color(input.getRGB (x, y)).getRed();
                histogram[red]++;
			}
		}
			
		return histogram;	
	}
}
