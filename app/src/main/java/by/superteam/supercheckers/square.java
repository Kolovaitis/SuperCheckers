package by.superteam.supercheckers;

import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Created by pasha on 01.10.2016.
 */
public class Square {
    private boolean isWhite;
    private int column;
    private int row;
    private Checker checker;

    public Square(boolean isWhite, int row, int column) {
        this.isWhite = isWhite;
        this.checker = null;
        this.column = column;
        this.row = row;
    }

    public Checker getChecker() {
        return checker;
    }

    public void setChecker(Checker checker) {
        if(checker!=null) {
            checker.setRow(this.getRow());
            checker.setColumn(this.getColumn());
        }
        this.checker = checker;
    }

    public boolean getWhite() {
        return isWhite;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void drawSquare() {
        ImageView img = (ImageView) ((TableRow) InGameMode2And3Activity.board.getChildAt(this.row)).getChildAt(this.column);
        if (this.isWhite) {
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setBackgroundResource(R.mipmap.white_king_on_white);
                    } else {
                        img.setBackgroundResource(R.mipmap.white_on_white);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setBackgroundResource(R.mipmap.black_king_on_white);
                    } else {
                        img.setBackgroundResource(R.mipmap.black_on_white);
                    }
                }
            } else {
                img.setBackgroundResource(R.mipmap.white);
            }
        } else {
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setBackgroundResource(R.mipmap.white_king_on_black);
                    } else {
                        img.setBackgroundResource(R.mipmap.white_on_black);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setBackgroundResource(R.mipmap.black_king_on_black);
                    } else {
                        img.setBackgroundResource(R.mipmap.black_on_black);
                    }
                }
            } else {
                img.setBackgroundResource(R.mipmap.black);
            }
        }
    }

    public void highlight() {
        ImageView img = (ImageView) ((TableRow) InGameMode2And3Activity.board.getChildAt(this.row)).getChildAt(this.column);
        if (this.checker == null) {
            img.setBackgroundResource(R.mipmap.green);
        } else {
            if (this.checker.isWhite()) {
                if (this.checker.isKing()) {
                    img.setBackgroundResource(R.mipmap.white_king_on_red);
                } else {
                    img.setBackgroundResource(R.mipmap.white_on_red);
                }
            } else {
                if (this.checker.isKing()) {
                    img.setBackgroundResource(R.mipmap.black_king_on_red);
                } else {
                    img.setBackgroundResource(R.mipmap.black_on_red);
                }
            }
        }
    }

    public void highlight2() {
        ImageView img = (ImageView) ((TableRow) InGameMode2And3Activity.board.getChildAt(this.row)).getChildAt(this.column);
        if (this.checker.isWhite()) {
            if (this.checker.isKing()) {
                img.setBackgroundResource(R.mipmap.white_king_on_purple);
            } else {
                img.setBackgroundResource(R.mipmap.white_on_purple);
            }
        } else {
            if (this.checker.isKing()) {
                img.setBackgroundResource(R.mipmap.black_king_on_purple);
            } else {
                img.setBackgroundResource(R.mipmap.black_on_purple);
            }
        }
    }

    public void highlight3(){
        ImageView img = (ImageView) ((TableRow) InGameMode2And3Activity.board.getChildAt(this.row)).getChildAt(this.column);
        if (this.checker.isWhite()) {
            if (this.checker.isKing()) {
                img.setBackgroundResource(R.mipmap.white_king_on_green);
            } else {
                img.setBackgroundResource(R.mipmap.white_on_green);
            }
        } else {
            if (this.checker.isKing()) {
                img.setBackgroundResource(R.mipmap.black_king_on_green);
            } else {
                img.setBackgroundResource(R.mipmap.black_on_green);
            }
        }
    }

}
