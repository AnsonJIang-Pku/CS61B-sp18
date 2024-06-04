package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //成员变量不能直接在构造函数里面初始化，一定要先在外面声明
    private boolean[][] grid;
    private WeightedQuickUnionUF ds1; // Disjoint set with virtual bottom site
    private WeightedQuickUnionUF ds2; // Disjoint set without virtual bottom site
    private int gridLength;
    private int nOpenSites = 0;

    // Constructor
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        gridLength = N;
        grid = new boolean[N][N]; //约定输入的下标从0开始,最大为N-1
        ds1 = new WeightedQuickUnionUF(N * N + 2); //并查集
        ds2 = new WeightedQuickUnionUF(N * N + 1);
        //初始化为not open(False)
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                grid[i][j] = false;
                //初始化并查集1最后一行
                if (i == N - 1) {
                    int nowN = xyTo1D(i, j);
                    ds1.union(N * N + 1, nowN);
                }
            }
        }
    }

    //To convert a 2D (row, col) coordinate into 1D number
    private int xyTo1D(int row, int col) {
        return (row * gridLength) + col;
    }

    //To open a site: open the site (row, col) if it is not open already
    public void open(int row, int col) {
        //Check parameters
        if (row < 0 || row >= gridLength || col < 0 || col >= gridLength) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            nOpenSites += 1;
            // To union the new site with its neighbor
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            int nowN = xyTo1D(row, col);
            //如果本身在第一行，和虚拟节点N*N相连
            if (row == 0) {
                //给顶部的节点都共同连接一个虚节点N*N
                ds1.union(gridLength * gridLength, nowN);
                ds2.union(gridLength * gridLength, nowN);
            }
            for (int[] d: dirs) {
                int nextX = row + d[0], nextY = col + d[1];
                if (0 <= nextX && nextX < gridLength && 0 <= nextY && nextY < gridLength) {
                    if (isOpen(nextX, nextY)) {
                        int nextN = xyTo1D(nextX, nextY);
                        ds1.union(nowN, nextN);
                        ds2.union(nowN, nextN);
//                        //如果邻居节点有在最后一行的，且连接后变成full(当前节点是full)，那么将其与N * N + 1连接
//                        if (nextX == gridLength - 1 && isFull(row, col)) {
//                            ds.union(gridLength * gridLength + 1, nextN);
//                        }
                    }
                }
            }
//            //如果本身在最底下，且是full的，那么与虚拟节点N * N + 1连接
//            if (row == gridLength - 1 && isFull(row, col)) {
//                ds1.union(gridLength * gridLength + 1, nowN);
//            }
        }
    }

    // Check whether a site is open
    public boolean isOpen(int row, int col) {
        //Check parameters
        if (row < 0 || row >= gridLength || col < 0 || col >= gridLength) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    //To check if (row, col) is full
    public boolean isFull(int row, int col) {
        //Check parameters
        if (row < 0 || row >= gridLength || col < 0 || col >= gridLength) {
            throw new IndexOutOfBoundsException();
        }
        int nowN = xyTo1D(row, col);
        if (ds2.connected(gridLength * gridLength, nowN)) {
            return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return nOpenSites;
    }

    //Does the system percolate?
    public boolean percolates() {
        return ds1.connected(gridLength * gridLength, gridLength * gridLength + 1);
    }
    public static void main (String[] args) {
        // use for unit testing (not required)
    }
}
