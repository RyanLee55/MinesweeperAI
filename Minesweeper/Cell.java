
public class Cell {

    private int     row;

    private int     col;

    private boolean mine;

    private int     count;

    private boolean flagged;

    private boolean uncovered;

    public Cell ( boolean mine, int row, int col ) {
        this.flagged = false;
        this.mine = mine;
        this.row = row;
        this.col = col;
        this.uncovered = false;
    }

    public void setUncovered() {
        this.uncovered = true;
    }
    public boolean getMine () {
        return mine;
    }

    public void setCount ( int count ) {
        this.count = count;
    }

    public int getCount () {
        return count;
    }

    public boolean isFlagged () {
        return flagged;
    }

    public boolean isUncovered () {
        return uncovered;
    }
}
