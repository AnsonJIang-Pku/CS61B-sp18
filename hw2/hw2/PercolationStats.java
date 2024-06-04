package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int numExperiment;
    private double[] resEachTime;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        numExperiment = T;
        // 存储每次模拟的结果
        resEachTime = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation pGrid = pf.make(N); // make an N-by-N grid
            // Repeat until the system percolates:
            while (!pGrid.percolates()) {
                // choose a site uniformly at random among all blocked sites
                int randRow = StdRandom.uniform(0, N), randCol = StdRandom.uniform(0, N);
                // Open the site.
                pGrid.open(randRow, randCol);
            }
            resEachTime[i] = (double) pGrid.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (int i = 0; i < numExperiment; i += 1) {
            sum += resEachTime[i];
        }
        return sum / numExperiment;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();
        double sum = 0;
        for (int i = 0; i < numExperiment; i += 1) {
            double delta = resEachTime[i] - mean;
            sum += Math.pow(delta, 2);
        }
        double res = Math.sqrt(sum / (numExperiment - 1));
        return res;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(numExperiment);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(numExperiment);
    }
}
