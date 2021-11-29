import java.util.*;

public class Minesweeper {

    private Cell[][] board;
    
    private int n;
    
    public Minesweeper() {
        int cols = 9;
        int rows = 9;

        n = 9;
        Cell[][] board = new Cell[n][n];

        int mines = 0;
        
        List<Integer> locations = new ArrayList<Integer>();
        while (locations.size() < 10) {
            Random r = new Random();
            int low = 0;
            int high = 81;
            int result = r.nextInt(high - low) + low;
            if (!locations.contains( result )) {
                locations.add( result );
            }
        }
        
        
        for ( int i = 0; i < n; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                boolean mine = false;
                int location = i * n + j;
                if (locations.contains( location )) {
                    mine = true;
                }
                board[i][j] = new Cell( mine, i, j );
            }
        }

        for ( int i = 0; i < n; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                int count = 0;
                if ( !board[i][j].getMine() ) {
                    if ( i - 1 >= 0 && board[i - 1][j].getMine() ) {
                        count++;
                    }
                    if ( i - 1 >= 0 && j + 1 < cols && board[i - 1][j + 1].getMine() ) {
                        count++;
                    }
                    if ( j + 1 < cols && board[i][j + 1].getMine() ) {
                        count++;
                    }
                    if ( i + 1 < rows && j + 1 < cols && board[i + 1][j + 1].getMine() ) {
                        count++;
                    }
                    if ( i + 1 < rows && board[i + 1][j].getMine() ) {
                        count++;
                    }
                    if ( i + 1 < rows && j - 1 >= 0 && board[i + 1][j - 1].getMine() ) {
                        count++;
                    }
                    if ( j - 1 >= 0 && board[i][j - 1].getMine() ) {
                        count++;
                    }
                    if ( i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1].getMine() ) {
                        count++;
                    }
                }
                board[i][j].setCount( count );
            }
        }
        this.board = board;
    }
    
    public void print() {
        for ( int i = 0; i < n; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                Cell cur = board[i][j];
                if ( cur.getMine() ) {
                    if (!cur.isUncovered()) {
                        if (cur.isFlagged()) {
                            System.out.print("P");
                        }
                        else {
                            System.out.print( "-" );
                        }
                    }
                    else {
                        System.out.print( "X " );
                    }
                }
                else {
                    if (!cur.isUncovered()) {
                        if (cur.isFlagged()) {
                            System.out.print("P");
                        }
                        else {
                            System.out.print( "-" );
                        }
                    }
                    else {
                        System.out.print( cur.getCount() + " " );
                    }
                }
            }
            System.out.println();
        }
    }
    
    public void solve() {
        
        uncover(0, 0);
        
    }
    
    public void chain(int row, int col) {
        
        if (board[row][col].getCount() == 0) {
            
            if ( row - 1 >= 0 && board[row - 1][col].getCount() == 0 ) {
                uncover(row - 1, col);
            }
            if ( row - 1 >= 0 && col + 1 < n && board[row - 1][col + 1].getCount() == 0 ) {
                count++;
            }
            if ( col + 1 < n && board[row][col + 1].getCount() == 0 ) {
                count++;
            }
            if ( row + 1 < n && col + 1 < n && board[row + 1][col + 1].getCount() == 0 ) {
                count++;
            }
            if ( row + 1 < n && board[row + 1][col].getCount() == 0 ) {
                count++;
            }
            if ( row + 1 < n && col - 1 >= 0 && board[row + 1][col - 1].getCount() == 0 ) {
                count++;
            }
            if ( col - 1 >= 0 && board[row][col - 1].getCount() == 0 ) {
                count++;
            }
            if ( row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1].getCount() == 0 ) {
                count++;
            }
        }
    }
    public void uncover(int row, int col) {
        
        if (board[row][col].getMine()) {
            // lose
        }
        else {
            board[row][col].setUncovered();
            chain(row, col);
        }
    }
    
    public static void Main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
        minesweeper.solve();
    }
}
