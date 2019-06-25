package dip;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Queue;

class Pair {
	int x, y;

	public Pair(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
}

class CountIslands
{
	// Below arrays details all 8 possible movements from a cell
	// (top, right, bottom, left and 4 diagonal moves)
	private static final int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
	private static final int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };

	public CountIslands(BufferedImage binaryImage) 
	{
		int[][] mat = getMatrix(binaryImage); 

			int M = mat.length;
			int N = mat[0].length;

			// stores if cell is visited or not
			boolean[][] visited = new boolean[M][N];

			int island = 0;
			for (int i = 0; i < M; i++)
			{
				for (int j = 0; j < N; j++)
				{
					// start BFS from each unvisited node and
					// increment island count
					if (mat[i][j] == 1 && !visited[i][j])
					{
						BFS(mat, visited, i, j);
						island++;
					}
				}
			}

			System.out.print("Number of islands are " + island);
	}
	
	// Function to check if it is safe to go to position (x, y)
	// from current position. The function returns false if (x, y)
	// is not valid matrix coordinates or (x, y) represents water or
	// position (x, y) is already visited
	public static boolean isSafe(int[][] mat, int x, int y,
							 boolean[][] visited)
	{
		return (x >= 0) && (x < visited.length) &&
				(y >= 0) && (y < visited[0].length) &&
				(mat[x][y] == 1 && !visited[x][y]);
	}

	public static void BFS(int[][] mat, boolean[][] visited, int i, int j)
	{
		// create an empty queue and enqueue source node
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(i, j));

		// Mark this cell as visited 
		visited[i][j] = true;

		// run till queue is not empty
		while (!q.isEmpty())
		{
			// pop front node from queue and visit it
			int x = q.peek().x;
			int y = q.peek().y;
			q.poll();

			// check for all 8 possible movements from current cell
			// and enqueue each valid movement
			for (int k = 0; k < 8; k++)
			{
				// Skip if location is invalid or already visited 
				// or has water
				if (isSafe(mat, x + row[k], y + col[k], visited))
				{
					printArray(mat);
					
					// skip if location is invalid or it is already
					// visited or consists of water
					visited[x + row[k]][y + col[k]] = true;
					q.add(new Pair(x + row[k], y + col[k]));
				}
			}
		}
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
	    			int rgb = bi.getRGB ( x, y ) ; // pixel rgb codificação 
	    			int valor = ( rgb >> 8 ) & 0xFF ; // pegamos o componente VERDE 
	    			arr [ x ] [ y ] = valor;
	    		}
	    	}
	    	return arr;
	    }
	 
	 public static void printArray(int[][] array) 
	 {
		 int row = array.length;
		 int column = array[0].length;
		 
	    	int[][] matrix = new int[row][column];

	        for (int y = 0; y < row; y++)
	            for (int x = 0; x < column; x++) 
	            	System.out.print(matrix[y][x] + " ");
	        
	        System.out.println();
	    }
}
