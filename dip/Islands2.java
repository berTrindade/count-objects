package dip;

import java.awt.image.BufferedImage;

class Islands2 
{ 
	static BufferedImage image; 
    
    public Islands2(BufferedImage image) 
    {
    	this.image = image;
    	
    	int matrix[][] = getMatrix(image); 
    	
    	for(int row = 0; row < matrix.length; row++)
    		for(int column = 0; column < matrix[0].length; column++)
    			System.out.println(matrix[row][column]);
    	
        System.out.println("Number of islands is: " + countIslands(matrix));
	}
	
    static final int ROW = 5, COL = 5; 
  
    // A function to check if a given cell (row, col) can 
    // be included in DFS 
    boolean isSafe(int image[][], int row, int col, 
                   boolean visited[][]) 
    { 
        // row number is in range, column number is in range 
        // and value is 1 and not yet visited 
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (image[row][col] == 1 && !visited[row][col]); 
    } 
  
    // A utility function to do DFS for a 2D boolean matrix. 
    // It only considers the 8 neighbors as adjacent vertices 
    void DFS(int image[][], int row, int col, boolean visited[][]) 
    { 
        // These arrays are used to get row and column numbers 
        // of 8 neighbors of a given cell 
        int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 }; 
        int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 }; 
  
        // Mark this cell as visited 
        visited[row][col] = true; 
  
        // Recur for all connected neighbours 
        for (int k = 0; k < 8; ++k) 
        {
            //if (isSafe(image, row + rowNbr[k], col + colNbr[k], visited)) 
                DFS(image, row + rowNbr[k], col + colNbr[k], visited);
        }
    } 
  
    // The main function that returns count of islands in a given 
    // boolean 2D matrix 
    int countIslands(int[][] matrix) 
    { 
        // Make a bool array to mark visited cells. 
        // Initially all cells are unvisited 
        boolean visited[][] = new boolean[matrix.length][matrix[0].length]; 
  
        // Initialize count as 0 and travese through the all cells 
        // of given matrix 
        int count = 0; 
        for (int i = 0; i < matrix.length; ++i) 
            for (int j = 0; j < matrix[0].length; ++j) 
                if (matrix[i][j] == 1 && !visited[i][j]) // If a cell with 
                { // value 1 is not 
                    // visited yet, then new island found, Visit all 
                    // cells in this island and increment island count 
                    DFS(matrix, i, j, visited); 
                    ++count; 
                } 
  
        return count; 
    } 
    
    public int[][] getMatrix(BufferedImage image) 
    {
    	int[][] mat = new int[image.getWidth()][image.getHeight()];

        for (int y = 0; y < image.getHeight(); y++)
            for (int x = 0; x < image.getWidth(); x++) 
                    mat[x][y] = image.getRGB(x, y);
        
        return mat;
    }
	
    public int [][] BufferedImageToArray(BufferedImage bi)  
    {
    	int W = bi.getWidth ( ), H = bi.getHeight ( );
    	int [][]arr = new int [ W ] [ H ] ;
    	for(int y = 0 ; y < H; y++)
    	{
    		for ( int x = 0 ; x <W; x ++ )  
    		{ 
    			int rgb = bi.getRGB ( x, y ) ; 
    			int valor = ( rgb >> 8 ) & 0xFF ; 
    			arr [ x ] [ y ] = valor;
    		}
    	}
    	return arr;
    }
} 
