import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.logging.Logger;

public class Percolation {

	private int[][] grid;
	private int n;
	private int uniqueCount = 1;
	WeightedQuickUnionUF alg = new WeightedQuickUnionUF(n * n);

	private final static Logger LOGGER = Logger.getLogger(Percolation.class.getName());

	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException();
		} else {
			grid = new int[n][n];// initialized with 0, that means "blocked"
		}
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		if ((row > n + 1 || row < 1) || (col > n + 1 || col < 1)) {
			throw new java.lang.IllegalArgumentException();
		} else {
			if (!isOpen(row, col)) {
				grid[row + 1][col + 1] = uniqueCount;
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
			throw new java.lang.IllegalArgumentException();
		} else {
			if (grid[row + 1][col + 1] > 0) {
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

		for (int i = 1; i <= n; i++) {
			if (alg.connected(grid[row][col], grid[1][i])) {
				return true;
			}
		}
		return false;
	}

	// number of open sites
	public int numberOfOpenSites() {
		return 0;
	}

	// does the system percolate?
	public boolean percolates() {
		return false;
	}

	/*private void firstRow() {

		for (int col = 1; col <= n; col++) {
			if (isOpen(1, col)) {

				if ((isOpen(1, col + 1)) && (col + 1 <= n)) {
					alg.union(grid[1][col], grid[1][col + 1]);
				}

				if (isOpen(2, col)) {
					alg.union(grid[1][col], grid[2][col]);
				}

			}
		}

	}*/

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
