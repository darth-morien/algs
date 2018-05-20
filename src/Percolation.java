import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class Percolation {

	private int[][] grid;
	private int n;
	private int uniqueCount = 1;
	WeightedQuickUnionUF alg = new WeightedQuickUnionUF(n * n);
	private int  row;
	private int  col; 
	
	private final static Logger LOGGER = Logger.getLogger(Percolation.class.getName());

	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		this.n = n;
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException();
		} else {
			this.grid = new int[n][n];// initialized with 0, that means "blocked"
			this.row = StdRandom.uniform(1,n);
			this.col = StdRandom.uniform(1,n);
			LOGGER.info("Row is " + row + ", " + " col is " + col);
			
		}
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		if ((row > n + 1 || row < 1) || (col > n + 1 || col < 1)) {
			throw new java.lang.IllegalArgumentException();
		} else {
			if (!isOpen(row, col)) {
				grid[row - 1][col - 1] = uniqueCount;
				uniqueCount++;
				LOGGER.info("The site on " + row + ", " + col + " is now open with value " + uniqueCount);
			} else {
				LOGGER.info("The site on " + row + ", " + col + " was already opened with value " + uniqueCount);
			}
		}

	};

	// is site (row, col) open?
	// 0 means closed; else means open
	public boolean isOpen(int row, int col) {
		if ((row > n + 1 || row < 1) || (col > n + 1 || col < 1)) {
			throw new java.lang.IllegalArgumentException("row is "+ row + " col is "+ col);
		} else {
			if (grid[row - 1][col - 1] > 0) {
				return true;
			} else
				return false;
		}
	}

	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		if ((row > n + 1 || row < 1) || (col > n + 1 || col < 1)) {
			throw new java.lang.IllegalArgumentException();
		}

		
			for (int i = 0; i <= n; i++) {
				//if (isOpen(row, col)) {
					if (isOpen(row, col) && alg.connected(grid[row-1][col-1], grid[0][i])) {
						return true;
				//}
			}
		}
		
		return false;
	}

	// number of open sites
	public int numberOfOpenSites() {
		return alg.count();
	}

	// does the system percolate?
	public boolean percolates() {
			
		for (int i = 1; i <= n; i++) {
			if (isFull(n,i)) {
				return true;
			}
		}
		return false;
	}

	private void processAll() {

		for (int row = 1; row <= n; row++) {
			for (int col = 1; col <= n; col++) {
				if (isOpen(row, col)) {

					if ((isOpen(row, col + 1)) && (col + 1 <= n)) {
						alg.union(grid[row][col], grid[row][col + 1]);
					}

					if ((isOpen(row + 1, col)) && (col + 1 <= n)) {
						alg.union(grid[row][col], grid[row + 1][col]);
					}
				}
			}
		}

	}
	
	private void monteCarlo(){
			do {
				if (!isOpen(row, col)) {
					open(row, col);
					processAll();
				}
				
			} while (!percolates());
		
	}

	public static void main(String[] args) {
		System.out.println("Enter the grid size n");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Percolation perc= new Percolation(n);
		perc.monteCarlo();
		System.out.println("System percolated with " + perc.numberOfOpenSites() +" open sites");

	}

}
