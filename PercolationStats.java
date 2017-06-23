import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] stats;
    
    public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
        if (n<=0 || trials<=0) throw new IllegalArgumentException();
        stats = new double[trials];
        
        for (int i = 0; i<trials;i++) {
            Percolation p = new Percolation(n);
            boolean perc = false;
            while (!perc) {
                int row = StdRandom.uniform(n)+1;
                int col = StdRandom.uniform(n)+1;
                p.open(row,col);
                if (p.percolates()) {
                    perc = true;
                }
            }
            stats[i] = (double) p.numberOfOpenSites()/(n*n);
        }
    }
    public double mean() {                            // sample mean of percolation threshold
       return StdStats.mean(stats);
    }                         
    public double stddev()   {                        // sample standard deviation of percolation threshold
        return StdStats.stddev(stats);
    }
    public double confidenceLo()    {              // low  endpoint of 95% confidence interval
        double confidenceLevel = 1.96;
        double temp = confidenceLevel * stddev()/Math.sqrt(stats.length);
        return mean()-temp;
    }
    public double confidenceHi()     {            // high endpoint of 95% confidence interval
        double confidenceLevel = 1.96;
        double temp = confidenceLevel * stddev()/Math.sqrt(stats.length);
        return mean()+temp;
    }
    public static void main(String[] args)    {    // test client (described below)
        PercolationStats ps = new PercolationStats(200,100);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLo());
        System.out.println(ps.confidenceHi());
    }
}
