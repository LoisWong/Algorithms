import java.util.Scanner;



public class PercolationStats {
	private double[] results;
	public PercolationStats(int N, int T) {// perform T independent computational experiments on an N-by-N grid
		if(N <= 0 || T <= 0)
			throw new IllegalArgumentException("N or T is not bigger than 0");
		results = new double[T];
		for(int i = 0; i < T; i++){
			Percolation per = new Percolation(N);
			int steps = 0;
			while(!per.percolates()){
				int row=StdRandom.uniform(N)+1;
		        int column=StdRandom.uniform(N)+1;
		        if(!per.isOpen(row,column)){
		          per.open(row,column);
		          steps++;
		        }
			}
			results[i]=(double)steps/(N*N);
		}
	}
	public double mean() {    // sample mean of percolation threshold
		return StdStats.mean(results);
	}
	public double stddev() {   // sample standard deviation of percolation threshold
		return StdStats.stddev(results);
	}
	public double confidenceLo() {   // returns lower bound of the 95% confidence interval
		return mean()-((1.96*stddev())/Math.sqrt(results.length));
	}
	public double confidenceHi() {   // returns upper bound of the 95% confidence interval
		return mean()+((1.96*stddev())/Math.sqrt(results.length));
	}
	public static void main(String[] args) {// test client, described below
		/*
		Scanner scan = new Scanner(System.in);
		int N = 0; 
		int T = 0;
		N = scan.nextInt();
		T = scan.nextInt();*/
		PercolationStats ps=new PercolationStats(200,100); 
	    StdOut.print("mean = "+ps.mean()+"\n");
	    StdOut.print("std dev = "+ps.stddev()+"\n");
	    StdOut.print("95% confidence interval = "+ps.confidenceLo()+", "+ps.confidenceHi());
	}
}
