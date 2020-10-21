import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int[] sites;
    WeightedQuickUnionUF weightedQuickUnionUF;
    int N;
    int openSites;
    int virtualTop;
    int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("index " + n + "should be more than 0");
        }

        N = n;
        sites = new int[n * n];
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        virtualTop =  n * n;
        virtualBottom = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }

        int index = getIndex(row, col);
        sites[index] = 1;
        openSites++;

        connectAdjacentOpenSites(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[getIndex(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return weightedQuickUnionUF.connected(0 ,getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(virtualTop , virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {}

    private int getIndex(int row, int col) {
        return ((row - 1) * N) + col - 1;
    }

    private void connectAdjacentOpenSites(int row, int col) {
        int index = getIndex(row, col);

        // if in first row connect with virtual component
        if (row == 1) {
            weightedQuickUnionUF.union(virtualTop, index);
        }

        // last row
        if (row == N) {
            weightedQuickUnionUF.union(index, virtualBottom);
        }

        int indexOfTopSite = index - N;
        int indexOfBottomSite = index + N;
        int indexOfRightSite = index + 1;
        int indexOfLeftSite = index - 1;

        if (indexOfTopSite > -1 && sites[indexOfTopSite] == 1) {
            weightedQuickUnionUF.union(index, indexOfTopSite);
        }

        if (indexOfBottomSite < N * N && sites[indexOfBottomSite] == 1) {
            weightedQuickUnionUF.union(index, indexOfBottomSite);
        }

        // check if right site not on bottom row
        if (indexOfRightSite % N != 0 && sites[indexOfRightSite] == 1) {
            weightedQuickUnionUF.union(index, indexOfRightSite);
        }

        // check if not on upper row
        if (indexOfLeftSite > 0 && indexOfLeftSite % N != (N - 1) && sites[indexOfLeftSite] == 1) {
            weightedQuickUnionUF.union(index, indexOfLeftSite);
        }

    }

    private void validate(int row, int col) {
        if (row < 1 || row > N) {
            throw new IllegalArgumentException("row " + row + " is not between 1 and " + N);
        }

        if (col < 1 || col > N) {
            throw new IllegalArgumentException("col " + col + " is not between 1 and " + N);
        }
    }
}
