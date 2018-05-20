import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int n;
	private int trials;
	Percolation perc = new Percolation(n);
	
	
	   public PercolationStats(int n, int trials){
		   // perform trials independent experiments on an n-by-n grid
		  
			if (n <= 0 || trials <=0) {
				throw new java.lang.IllegalArgumentException();
			} else {
				 this.n = n;
				 this.trials = trials;
				
			}
	   }
		   public double mean()  {
			return 0;
			   // sample mean of percolation threshold
		   }
		   public double stddev() {
			return 0;
			   // sample standard deviation of percolation threshold
		   }
		   public double confidenceLo()  {
			return 0;
			   // low  endpoint of 95% confidence interval
		   }
		   public double confidenceHi()  {
			return 0;
			   // high endpoint of 95% confidence interval
		   }

		   
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
