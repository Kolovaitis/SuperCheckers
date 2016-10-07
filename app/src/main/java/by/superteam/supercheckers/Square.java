package by.superteam.supercheckers;

import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TableRow;

/**
 * Created by Влад on 04.10.2016.
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
            img.setBackgroundResource(R.drawable.white);
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.white_king);
                    } else {
                        img.setImageResource(R.mipmap.white_checker);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.black_king);
                    } else {
                        img.setImageResource(R.mipmap.black_checker);
                    }
                }
            }else {
                img.setImageResource(0);
            }
        } else {
            img.setBackgroundResource(R.drawable.black);
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.white_king);
                    } else {
                        img.setImageResource(R.mipmap.white_checker);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.black_king);
                    } else {
                        img.setImageResource(R.mipmap.black_checker);
                    }
                }
            }else {
                img.setImageResource(0);
            }
        }
    }

    public void drawSquare2() {
        ImageView img = (ImageView) ((TableRow) CommonDisplayActivity.board.getChildAt(this.row)).getChildAt(this.column);
        if (this.isWhite) {
            img.setBackgroundResource(R.drawable.white);
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.black_king);
                    } else {
                        img.setBackgroundResource(R.mipmap.black_checker);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.white_king);
                    } else {
                        img.setImageResource(R.mipmap.white_checker);
                    }
                }
            }else {
                img.setImageResource(0);
            }
        } else {
            img.setBackgroundResource(R.drawable.black);
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.black_king);
                    } else {
                        img.setImageResource(R.mipmap.black_checker);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.white_king);
                    } else {
                        img.setImageResource(R.mipmap.white_checker);
                    }
                }
            }else {
                img.setImageResource(0);
            }
        }
    }

    public void drawSquare3() {
        ImageView img = (ImageView) ((TableRow) CommonDisplayActivity.board.getChildAt(this.row)).getChildAt(this.column);
        if (this.isWhite) {
            img.setBackgroundResource(R.drawable.white);
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.white_king);
                    } else {
                        img.setImageResource(R.mipmap.white_checker);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.black_king);
                    } else {
                        img.setImageResource(R.mipmap.black_checker);
                    }
                }
            }else {
                img.setImageResource(0);
            }
        } else {
            img.setBackgroundResource(R.drawable.black);
            if (this.checker != null) {
                if (this.checker.isWhite()) {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.white_king);
                    } else {
                        img.setImageResource(R.mipmap.white_checker);
                    }
                } else {
                    if (this.checker.isKing()) {
                        img.setImageResource(R.mipmap.black_king);
                    } else {
                        img.setImageResource(R.mipmap.black_checker);
                    }
                }
            }else {
                img.setImageResource(0);
            }
        }
    }

    public void highlight() {
        ImageView img = (ImageView) ((TableRow) InGameMode2And3Activity.board.getChildAt(this.row)).getChildAt(this.column);
        if (this.checker == null) {
            img.setBackgroundResource(R.drawable.green);
        } else {
            img.setBackgroundResource(R.drawable.red);
        }
    }

    public void highlight2() {
        ImageView img = (ImageView) ((TableRow) InGameMode2And3Activity.board.getChildAt(this.row)).getChildAt(this.column);
        img.setBackgroundResource(R.drawable.purple);
    }

    public void highlight3(){
        ImageView img = (ImageView) ((TableRow) InGameMode2And3Activity.board.getChildAt(this.row)).getChildAt(this.column);
        img.setBackgroundResource(R.drawable.green);
    }

}

