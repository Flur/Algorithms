import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private final double[] fractions;
    private final int T;
    private final int N;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        N = n;
        T = trials;
        fractions = new double[T];

        for (int i = 0; i < T; i++) {
            testPercolation(i);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double s = stddev();
        double mean = mean();

        return mean - ((1.96 * s) / Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double s = stddev();
        double mean = mean();

        return mean + ((1.96 * s) / Math.sqrt(T));
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        StdOut.print("mean                    = " + percolationStats.mean() + "\n");
        StdOut.print("stddev                  = " + percolationStats.stddev() + "\n");
        StdOut.print("95% confidence interval = [" + percolationStats.confidenceLo() + " ," + percolationStats.confidenceHi() + "]" + "\n");
    }

    private void testPercolation(int i) {
        Percolation percolation = new Percolation(N);

        while (!percolation.percolates()) {
            int row = StdRandom.uniform(1, N + 1);
            int col = StdRandom.uniform(1, N + 1);

            percolation.open(row, col);
        }

        fractions[i] = (double) percolation.numberOfOpenSites() / (double) (N * N);
    }
}
