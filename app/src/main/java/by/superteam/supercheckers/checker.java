package by.superteam.supercheckers;

class Checker {
    private int column;
    private int row;
    private boolean isWhite;
    private boolean isKing;

    public Checker(int row, int column, boolean isWhite) {
        this.column = column;
        this.isWhite = isWhite;
        this.row = row;
        this.isKing = false;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public void setKing(boolean isKing) {
        this.isKing = isKing;
    }
}
