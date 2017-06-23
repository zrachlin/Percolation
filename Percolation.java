/**
 * Auto Generated Java Class.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   private int[][] grid;
   private int n;
   private int openSites;
   private WeightedQuickUnionUF w;
    
    /* ADD YOUR CODE HERE */
   public Percolation(int n)    {            // create n-by-n grid, with all sites blocked
       if (n<=0) throw new IllegalArgumentException();
       this.n = n;
       grid = new int[n][n];
       w = new WeightedQuickUnionUF((n*n)+2);
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
           }
       }
       openSites = 0;
   }
   public void open(int row, int col)  {// open site (row, col) if it is not open already
       if (row < 1 || row > n || col <1 || col >n) throw new IndexOutOfBoundsException("row index is out of bounds");
       int iD = xyTo1D(row,col);
    
       if (!isOpen(row,col)){
           grid[row-1][col-1]=1;
          
           if (row > 1) {
               if (isOpen(row-1,col)) {
                   w.union(iD,iD-n);
               }
           }
           else {
               w.union(0,iD);
           }
           
           if (row < n) {
               if (isOpen(row+1,col)) {
                   w.union(iD,iD+n);
               }
           }
           else {
               w.union(iD,(n*n)+1);
           }
           
           if (col > 1) {
               if (isOpen(row,col-1)) {
                   w.union(iD,iD-1);
               }
           }
           if (col < n) {
               if (isOpen(row,col+1)) {
                   w.union(iD,iD+1);
               }
           }
          
           openSites = openSites + 1;
       }
   }
   public boolean isOpen(int row, int col) {  // is site (row, col) open?
       if (row < 1 || row > n || col <1 || col >n) throw new IndexOutOfBoundsException("row index is out of bounds");
       if (grid[row-1][col-1] > 0){
           return true;
       }
       else {
           return false;
       }
   }     
   public boolean isFull(int row, int col) {  // is site (row, col) full?
       if (row < 1 || row > n || col <1 || col >n) throw new IndexOutOfBoundsException("index is out of bounds");
       if (w.connected(xyTo1D(row,col),0)){
           return true;
       }
       else {
           return false;
       }
   } 
   public int numberOfOpenSites() {
       return openSites;
   }
   public boolean percolates() {             // does the system percolate?
       if (w.connected(0,(n*n)+1)) {
           return true;
       }
       else {
           return false;
       }
   }
   private int xyTo1D(int row, int col) {
       if (row < 1 || row > n || col <1 || col >n) throw new IndexOutOfBoundsException("index is out of bounds");
       int iD = (row-1)*n+col;
       return iD;
   }    
   public static void main(String[] args) {   // test client (optional)   
   }
}

