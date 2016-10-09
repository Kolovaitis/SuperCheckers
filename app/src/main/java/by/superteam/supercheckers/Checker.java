package by.superteam.supercheckers;

import android.widget.ImageView;

/**
 * Created by Влад on 04.10.2016.
 */
public class Checker {
    private int column;
    private int row;
    private boolean isWhite;
    private boolean isKing;
    private int skinResourceId;

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

    public ImageView getView(){
        ImageView img=new ImageView(InGameMode2And3Activity.activity);
        if(isWhite()){
            if(isKing()){
                img.setBackgroundResource(R.mipmap.white_king);
            }else{
                img.setBackgroundResource(R.mipmap.white_checker);
            }
        }else{
            if(isKing()){
                img.setBackgroundResource(R.mipmap.black_king);
            }else{
                img.setBackgroundResource(R.mipmap.black_checker);
            }
        }
        return img;
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

