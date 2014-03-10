public class Percolation {
    private int size;
    private int[] states; // from 1 to N,0:closed 1:open
    private WeightedQuickUnionUF cellStorage;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        size = N;
        states = new int[N * N + 2];
        cellStorage = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N * N; i++) {
            states[i] = 0;
        }
        states[N * N] = 1;
        states[N * N + 1] = 1;
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkRange(i, j);
        if (isOpen(i, j)) {
            return;
        }
        int index = getCellIndex(i, j);
        states[index] = 1; // mark as open

        // if not the top row
        if (i != 1 && isOpen(i - 1, j)) {
            union(index, getCellIndex(i - 1, j));
        } else if (i == 1) {
            union(index, size * size);
            // connect to virtual top cell
        }
        // if not the bottom row
        if (i != size && isOpen(i + 1, j)) {
            union(index, getCellIndex(i + 1, j));
        } else if (i == size) {
            union(index, size * size + 1);
            // connect to virtual bottom cell
        }
        // if not the left border
        if (j != 1 && isOpen(i, j - 1)) {
            union(index, getCellIndex(i, j - 1));
        }
        // if not the right border
        if (j != size && isOpen(i, j + 1)) {
            union(index, getCellIndex(i, j + 1));
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkRange(i, j);
        return states[getCellIndex(i, j)] == 1;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkRange(i, j);
        return cellStorage.connected(size * size, getCellIndex(i, j));
    }

    // does the system percolate?
    public boolean percolates() {
        return cellStorage.connected(size * size, size * size + 1);
    }

    // check range
    private void checkRange(int i, int j) {
        if (i <= 0 || j <= 0 || i > size || j > size) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
    }

    // get the id of the slide
    private int getCellIndex(int i, int j) {
        return (size * (i - 1)) + j - 1;
    }

    // link one slide with another
    private void union(int a, int b) {
        if (!cellStorage.connected(a, b)) {
            cellStorage.union(a, b);
        }
    }

    public static void main(String[] args) {
        Percolation per = new Percolation(3);
        per.open(1, 1);
        System.out.println(per.cellStorage.connected(0, 9));
        per.open(1, 2);
        per.open(2, 2);
        System.out.println(per.cellStorage.connected(4, 9));
        per.open(3, 2);
        System.out.println(per.cellStorage.connected(4, 10));
        System.out.println(per.cellStorage.connected(10, 9));
        for (int i = 0; i < 11; i++) {
            System.out.print(i);
        }
        System.out.println(per.percolates());
    }
}
