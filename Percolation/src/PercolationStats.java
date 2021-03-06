public class PercolationStats {
    private double[] results;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = new Percolation(N);
            int steps = 0;
            while (!per.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int column = StdRandom.uniform(N) + 1;
                if (!per.isOpen(row, column)) {
                    per.open(row, column);
                    steps++;
                }
            }
            results[i] = (double) steps / (N * N);
        }
    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(results.length));
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(results.length));
    }

    // test client, described below
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(200, 100);
        StdOut.print("mean = " + ps.mean() + "\n");
        StdOut.print("std dev = " + ps.stddev() + "\n");
        StdOut.print("95% confidence interval = " + ps.confidenceLo());
        StdOut.print(", " + ps.confidenceHi());
    }
}
