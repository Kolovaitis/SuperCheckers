package by.superteam.supercheckers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by pasha on 01.10.2016.
 */
public class InGameMode2And3Activity extends AppCompatActivity {
    static Activity activity;
    static boolean searching = false;
    static boolean searching2 = false;
    static int blackcounter = 0;
    static int whitecounter = 0;
    static Square purple = null;
    static Square redrb = null;
    static Square redlb = null;
    static Square redlt = null;
    static Square redrt = null;
    static ArrayList<Square> green = new ArrayList<Square>();
    static ArrayList<Square> deadGreenrb = new ArrayList<Square>();
    static ArrayList<Square> deadGreenlb = new ArrayList<Square>();
    static ArrayList<Square> deadGreenlt = new ArrayList<Square>();
    static ArrayList<Square> deadGreenrt = new ArrayList<Square>();
    static ArrayList<Square> veryDeadGreen = new ArrayList<Square>();
    static TableLayout board;
    static Square[][] squares = new Square[8][8];
    static ArrayList<Checker> whites = new ArrayList<Checker>();
    static ArrayList<Checker> blacks = new ArrayList<Checker>();
    static int progress = 1;
    static int lastDirection;
    static boolean canChange = true;
    static ArrayList<Square> necessarySquares = new ArrayList<Square>();
    static int yourColor=0;


    public static void draw() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].drawSquare();
            }
        }
        for (int i = 0; i < necessarySquares.size(); i++) {
            necessarySquares.get(i).highlight3();
        }
    }

    public static void clean() {
        purple = null;
        redrb = null;
        redlb = null;
        redlt = null;
        redrt = null;
        green = new ArrayList<Square>();
        deadGreenrb = new ArrayList<Square>();
        deadGreenlb = new ArrayList<Square>();
        deadGreenlt = new ArrayList<Square>();
        deadGreenrt = new ArrayList<Square>();
        veryDeadGreen = new ArrayList<Square>();
        necessarySquares = new ArrayList<Square>();
        canChange = true;
    }

    public static void clean2() {
        purple = null;
        redrb = null;
        redlb = null;
        redlt = null;
        redrt = null;
        green = new ArrayList<Square>();
        deadGreenrb = new ArrayList<Square>();
        deadGreenlb = new ArrayList<Square>();
        deadGreenlt = new ArrayList<Square>();
        deadGreenrt = new ArrayList<Square>();
        veryDeadGreen = new ArrayList<Square>();
        canChange = true;
    }

    public static void clean3() {
        purple = null;
        redrb = null;
        redlb = null;
        redlt = null;
        redrt = null;
        canChange = true;
        veryDeadGreen = new ArrayList<Square>();
    }

    public static void clean4() {
        purple = null;
        redrb = null;
        redlb = null;
        redlt = null;
        redrt = null;
        green = new ArrayList<Square>();
        deadGreenrb = new ArrayList<Square>();
        deadGreenlb = new ArrayList<Square>();
        deadGreenlt = new ArrayList<Square>();
        deadGreenrt = new ArrayList<Square>();
        canChange=true;
    }

    public static void setVeryDeadGreen(int row, int column, boolean isWhite) {
        Square redrb2 = redrb;
        Square redlb2 = redlb;
        Square redrt2 = redrt;
        Square redlt2 = redlt;
        Square purple2 = purple;
        ArrayList<Square> deadGreenrb2 = deadGreenrb;
        ArrayList<Square> deadGreenlb2 = deadGreenlb;
        ArrayList<Square> deadGreenlt2 = deadGreenlt;
        ArrayList<Square> deadGreenrt2 = deadGreenrt;
        clean4();
        int p = progress;
        Checker c;
        if (isWhite) {
            progress = 3;
            c = new Checker(row, column, true);
        } else {
            progress = 6;
            c = new Checker(row, column, false);
        }
        c.setKing(true);
        squares[row][column].setChecker(c);
        searching2 = true;
        if (isWhite) {
            ifprogress1(row, column);
        } else {
            ifprogress4(row, column);
        }
        searching2 = false;
        draw();
        progress = p;
        squares[row][column].setChecker(null);

        if (redlb != null || redlt != null || redrb != null || redrt != null) {
            veryDeadGreen.add(squares[row][column]);
        }
        redrb = redrb2;
        redlb = redlb2;
        redlt = redlt2;
        redrt = redrt2;
        deadGreenrb = deadGreenrb2;
        deadGreenlb = deadGreenlb2;
        deadGreenlt = deadGreenlt2;
        deadGreenrt = deadGreenrt2;
        purple = purple2;
        lastDirection = 0;

    }


    public void onSquareClick(View v) {
        int column = ((TableRow) v.getParent()).indexOfChild(v);
        int row = ((TableLayout) ((TableRow) v.getParent()).getParent()).indexOfChild((TableRow) v.getParent());
        /*
        тут нужно отправить сопернику row и column и передать их в onEnemyClick   ------------------------------------------------------------------------------------------------------------
        в этот момент(в начале мотода onSquareClick на этот устройстве) у соперника должен вызываться метод onEnemyClick(row,column)   -------------------------------------------------------
        так же когда нам прилетают row и column нужно на этом устройстве тут же вызывать onEnemyClick(row,column)   --------------------------------------------------------------------------
         */
        try {
         SuperSocket.send(new int[]{row, column});
        } catch (Exception e) {

        }
        if (progress == 1) {
            if(yourColor!=2) {
                canChange = true;
                ifprogress1(row, column);
            }else{
                Toast.makeText(this,"не твой ход",Toast.LENGTH_SHORT).show();
            }
        } else {
            if (progress == 2) {
                if(yourColor!=2) {
                    ifprogress2(row, column);
                }else{
                    Toast.makeText(this,"не твой ход",Toast.LENGTH_SHORT).show();
                }
            } else {
                if (progress == 4) {
                    if(yourColor!=1) {
                        canChange = true;
                        ifprogress4(row, column);
                    }else{
                        Toast.makeText(this,"не твой ход",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (progress == 5) {
                        if(yourColor!=1) {
                            ifprogress5(row, column);
                        }else{
                            Toast.makeText(this,"не твой ход",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

        }
    }


    public static void onEnemyClick(int row, int column){
        if (progress == 1) {
            if(yourColor==2) {
                canChange = true;
                ifprogress1(row, column);
            }
        } else {
            if (progress == 2) {
                if(yourColor==2) {
                    ifprogress2(row, column);
                }
            } else {
                if (progress == 4) {
                    if(yourColor==1) {
                        canChange = true;
                        ifprogress4(row, column);
                    }
                } else {
                    if (progress == 5) {
                        if(yourColor==1) {
                            ifprogress5(row, column);
                        }
                    }
                }
            }

        }
    }


    public static void ifprogress1(int row, int column) {

        if (necessarySquares.size() > 0 && necessarySquares.indexOf(squares[row][column]) == -1 && searching == false && searching2 == false) {
            progress = 1;
        } else {
            if (squares[row][column].getChecker() != null) {
                if (squares[row][column].getChecker().isWhite()) {
                    squares[row][column].highlight2();
                    if (progress == 1) {
                        progress = 2;
                    }
                    purple = squares[row][column];
                    if (squares[row][column].getChecker().isKing()) {
                        //rb
                        if (lastDirection != 4) {
                            int i = row;
                            int j = column;
                            i++;
                            j++;
                            boolean temp = false;
                            while (i < 8 && j < 8 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {
                                    if (squares[i][j].getChecker().isWhite()) {
                                        break;
                                    } else {
                                        if (i < 7 && j < 7) {
                                            if (squares[i + 1][j + 1].getChecker() == null) {
                                                temp = true;
                                                squares[i][j].highlight();
                                                redrb = squares[i][j];
                                                i++;
                                                j++;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    if (temp) {
                                        squares[i][j].highlight();
                                        deadGreenrb.add(squares[i][j]);
                                        if (!searching2) {
                                            lastDirection = 2;
                                            setVeryDeadGreen(i, j, true);
                                        }

                                    } else {
                                        if (progress != 3 && (necessarySquares.size() == 0 || searching == true)) {
                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i++;
                                    j++;

                                }
                            }
                        }
                        //lb
                        if (lastDirection != 1) {

                            int i = row;
                            int j = column;
                            i++;
                            j--;
                            boolean temp = false;
                            while (i < 8 && j > -1 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {

                                    if (squares[i][j].getChecker().isWhite()) {

                                        break;
                                    } else {
                                        if (i < 7 && j > 0) {
                                            if (squares[i + 1][j - 1].getChecker() == null) {

                                                temp = true;
                                                squares[i][j].highlight();
                                                redlb = squares[i][j];
                                                i++;
                                                j--;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {

                                    if (temp) {

                                        squares[i][j].highlight();
                                        deadGreenlb.add(squares[i][j]);
                                        if (!searching2) {
                                            lastDirection = 3;
                                            setVeryDeadGreen(i, j, true);
                                        }
                                    } else {

                                        if (progress != 3 && (necessarySquares.size() == 0 || searching == true)) {

                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i++;
                                    j--;

                                }
                            }
                        }
                        //lt
                        if (lastDirection != 2) {

                            int i = row;
                            int j = column;
                            i--;
                            j--;
                            boolean temp = false;
                            while (i > -1 && j > -1 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {

                                    if (squares[i][j].getChecker().isWhite()) {

                                        break;
                                    } else {
                                        if (i > 0 && j > 0) {
                                            if (squares[i - 1][j - 1].getChecker() == null) {

                                                temp = true;
                                                squares[i][j].highlight();
                                                redlt = squares[i][j];
                                                i--;
                                                j--;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {

                                    if (temp) {

                                        squares[i][j].highlight();
                                        deadGreenlt.add(squares[i][j]);
                                        if (!searching2) {
                                            lastDirection = 4;
                                            setVeryDeadGreen(i, j, true);
                                        }
                                    } else {

                                        if (progress != 3 && (necessarySquares.size() == 0 || searching == true)) {

                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i--;
                                    j--;

                                }
                            }
                        }
                        //rt
                        if (lastDirection != 3) {

                            int i = row;
                            int j = column;
                            i--;
                            j++;
                            boolean temp = false;
                            while (i > -1 && j < 8 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {

                                    if (squares[i][j].getChecker().isWhite()) {

                                        break;
                                    } else {
                                        if (i > 0 && j < 7) {
                                            if (squares[i - 1][j + 1].getChecker() == null) {

                                                temp = true;
                                                squares[i][j].highlight();
                                                redrt = squares[i][j];
                                                i--;
                                                j++;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {

                                    if (temp) {

                                        squares[i][j].highlight();
                                        deadGreenrt.add(squares[i][j]);
                                        if (!searching2) {
                                            lastDirection = 1;
                                            setVeryDeadGreen(i, j, true);
                                        }
                                    } else {

                                        if (progress != 3 && (necessarySquares.size() == 0 || searching == true)) {

                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i--;
                                    j++;

                                }
                            }
                        }
                    } else {

                        if (column < 7) {

                            if (row > 0) {

                                if (squares[row - 1][column + 1].getChecker() == null) {

                                    if (progress != 3 && (necessarySquares.size() == 0 || searching == true)) {

                                        squares[row - 1][column + 1].highlight();
                                        green.add(squares[row - 1][column + 1]);
                                    }
                                } else {

                                    if (squares[row - 1][column + 1].getChecker().isWhite() == false && row > 1 && column < 6 && squares[row - 2][column + 2].getChecker() == null) {

                                        squares[row - 1][column + 1].highlight();
                                        redrt = squares[row - 1][column + 1];
                                        squares[row - 2][column + 2].highlight();
                                        deadGreenrt.add(squares[row - 2][column + 2]);
                                    }
                                }
                            }
                            if (row < 6) {

                                if (squares[row + 1][column + 1].getChecker() != null) {

                                    if (squares[row + 1][column + 1].getChecker().isWhite() == false && column < 6 && squares[row + 2][column + 2].getChecker() == null) {

                                        squares[row + 1][column + 1].highlight();
                                        redrb = squares[row + 1][column + 1];
                                        squares[row + 2][column + 2].highlight();
                                        deadGreenrb.add(squares[row + 2][column + 2]);
                                    }
                                }
                            }
                        }
                        if (column > 0) {

                            if (row > 0) {

                                if (squares[row - 1][column - 1].getChecker() == null) {

                                    if (progress != 3 && (necessarySquares.size() == 0 || searching == true)) {

                                        squares[row - 1][column - 1].highlight();
                                        green.add(squares[row - 1][column - 1]);
                                    }
                                } else {

                                    if (squares[row - 1][column - 1].getChecker().isWhite() == false && row > 1 && column > 1 && squares[row - 2][column - 2].getChecker() == null) {

                                        squares[row - 1][column - 1].highlight();
                                        redlt = squares[row - 1][column - 1];
                                        squares[row - 2][column - 2].highlight();
                                        deadGreenlt.add(squares[row - 2][column - 2]);
                                    }
                                }
                            }
                            if (row < 6) {

                                if (squares[row + 1][column - 1].getChecker() != null) {

                                    if (squares[row + 1][column - 1].getChecker().isWhite() == false && column > 1 && squares[row + 2][column - 2].getChecker() == null) {

                                        squares[row + 1][column - 1].highlight();
                                        redlb = squares[row + 1][column - 1];
                                        squares[row + 2][column - 2].highlight();
                                        deadGreenlb.add(squares[row + 2][column - 2]);
                                    }
                                }
                            }
                        }
                    }


                    if ((redlb != null || redlt != null || redrb != null || redrt != null) && progress == 3) {
                        canChange = false;
                        progress = 2;
                    } else {

                        if (progress == 3 && !searching2) {

                            progress = 4;
                            finder(false);
                            draw();
                        }
                    }

                    highlight();
                }
            }
        }
    }

    public static void ifprogress2(int row, int column) {
        if (row == purple.getRow() && column == purple.getColumn() && canChange) {
            draw();
            clean2();
            progress = 1;
            lastDirection = 0;
        } else {
            if (squares[row][column].getChecker() != null && canChange) {
                if (squares[row][column].getChecker().isWhite()) {
                    draw();
                    clean2();
                    ifprogress1(row, column);
                    lastDirection = 0;
                }
            } else {
                if (row == 0) {
                    purple.getChecker().setKing(true);
                }
                if (veryDeadGreen.indexOf(squares[row][column]) != -1) {
                    if (deadGreenlb.indexOf(squares[row][column]) != -1) {
                        squares[row][column].setChecker(purple.getChecker());
                        purple.setChecker(null);
                        redlb.setChecker(null);
                        blackcounter--;
                        lastDirection = 3;
                        progress = 3;
                        clean();
                        draw();
                        ifprogress1(row, column);
                    } else {
                        if (deadGreenlt.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redlt.setChecker(null);
                            blackcounter--;
                            lastDirection = 4;
                            progress = 3;
                            clean();
                            draw();
                            ifprogress1(row, column);
                        } else {
                            if (deadGreenrb.indexOf(squares[row][column]) != -1) {
                                squares[row][column].setChecker(purple.getChecker());
                                purple.setChecker(null);
                                redrb.setChecker(null);
                                blackcounter--;
                                lastDirection = 2;
                                progress = 3;
                                clean();
                                draw();
                                ifprogress1(row, column);
                            } else {
                                if (deadGreenrt.indexOf(squares[row][column]) != -1) {
                                    squares[row][column].setChecker(purple.getChecker());
                                    purple.setChecker(null);
                                    redrt.setChecker(null);
                                    blackcounter--;
                                    lastDirection = 1;
                                    progress = 3;
                                    clean();
                                    draw();
                                    ifprogress1(row, column);
                                }
                            }
                        }
                    }
                } else {
                    if (veryDeadGreen.size() == 0) {
                        if (green.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            clean();
                            draw();
                            lastDirection = 0;
                            progress = 4;
                            finder(false);
                        }
                        if (deadGreenlb.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redlb.setChecker(null);
                            blackcounter--;
                            lastDirection = 3;
                            progress = 3;
                            clean();
                            draw();
                            ifprogress1(row, column);
                        }
                        if (deadGreenrb.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redrb.setChecker(null);
                            blackcounter--;
                            lastDirection = 2;
                            progress = 3;
                            clean();
                            draw();
                            ifprogress1(row, column);
                        }
                        if (deadGreenrt.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redrt.setChecker(null);
                            blackcounter--;
                            lastDirection = 1;
                            progress = 3;
                            clean();
                            draw();
                            ifprogress1(row, column);
                        }
                        if (deadGreenlt.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redlt.setChecker(null);
                            blackcounter--;
                            lastDirection = 4;
                            progress = 3;
                            clean();
                            draw();
                            ifprogress1(row, column);
                        }
                    }
                }
            }
        }
    }

    public static void ifprogress4(int row, int column) {
        if (necessarySquares.size() > 0 && necessarySquares.indexOf(squares[row][column]) == -1 && searching == false && searching2 == false) {
            progress = 4;
        } else {
            if (squares[row][column].getChecker() != null) {
                if (!squares[row][column].getChecker().isWhite()) {
                    squares[row][column].highlight2();
                    if (progress == 4) {
                        progress = 5;
                    }
                    purple = squares[row][column];
                    if (squares[row][column].getChecker().isKing()) {
                        //rb
                        if (lastDirection != 4) {
                            int i = row;
                            int j = column;
                            i++;
                            j++;
                            boolean temp = false;
                            while (i < 8 && j < 8 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {
                                    if (!squares[i][j].getChecker().isWhite()) {
                                        break;
                                    } else {
                                        if (i < 7 && j < 7) {
                                            if (squares[i + 1][j + 1].getChecker() == null) {
                                                temp = true;
                                                squares[i][j].highlight();
                                                redrb = squares[i][j];
                                                i++;
                                                j++;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    if (temp) {
                                        squares[i][j].highlight();
                                        deadGreenrb.add(squares[i][j]);
                                        lastDirection = 2;
                                        setVeryDeadGreen(i, j, false);
                                    } else {
                                        if (progress != 6 && (necessarySquares.size() == 0 || searching == true)) {
                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i++;
                                    j++;

                                }
                            }
                        }
                        //lb
                        if (lastDirection != 1) {
                            int i = row;
                            int j = column;
                            i++;
                            j--;
                            boolean temp = false;
                            while (i < 8 && j > -1 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {
                                    if (!squares[i][j].getChecker().isWhite()) {
                                        break;
                                    } else {
                                        if (i < 7 && j > 0) {
                                            if (squares[i + 1][j - 1].getChecker() == null) {
                                                temp = true;
                                                squares[i][j].highlight();
                                                redlb = squares[i][j];
                                                i++;
                                                j--;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    if (temp) {
                                        squares[i][j].highlight();
                                        deadGreenlb.add(squares[i][j]);
                                        lastDirection = 3;
                                        setVeryDeadGreen(i, j, false);
                                    } else {
                                        if (progress != 6 && (necessarySquares.size() == 0 || searching == true)) {
                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i++;
                                    j--;

                                }
                            }
                        }
                        //lt
                        if (lastDirection != 2) {
                            int i = row;
                            int j = column;
                            i--;
                            j--;
                            boolean temp = false;
                            while (i > -1 && j > -1 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {
                                    if (!squares[i][j].getChecker().isWhite()) {
                                        break;
                                    } else {
                                        if (i > 0 && j > 0) {
                                            if (squares[i - 1][j - 1].getChecker() == null) {
                                                temp = true;
                                                squares[i][j].highlight();
                                                redlt = squares[i][j];
                                                i--;
                                                j--;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    if (temp) {
                                        squares[i][j].highlight();
                                        deadGreenlt.add(squares[i][j]);
                                        lastDirection = 4;
                                        setVeryDeadGreen(i, j, false);
                                    } else {
                                        if (progress != 6 && (necessarySquares.size() == 0 || searching == true)) {
                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i--;
                                    j--;

                                }
                            }
                        }
                        //rt
                        if (lastDirection != 3) {
                            int i = row;
                            int j = column;
                            i--;
                            j++;
                            boolean temp = false;
                            while (i > -1 && j < 8 && (squares[i][j].getChecker() == null || !temp)) {
                                if (squares[i][j].getChecker() != null) {
                                    if (!squares[i][j].getChecker().isWhite()) {
                                        break;
                                    } else {
                                        if (i > 0 && j < 7) {
                                            if (squares[i - 1][j + 1].getChecker() == null) {
                                                temp = true;
                                                squares[i][j].highlight();
                                                redrt = squares[i][j];
                                                i--;
                                                j++;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    if (temp) {
                                        squares[i][j].highlight();
                                        deadGreenrt.add(squares[i][j]);
                                        lastDirection = 1;
                                        setVeryDeadGreen(i, j, false);
                                    } else {
                                        if (progress != 6 && (necessarySquares.size() == 0 || searching == true)) {
                                            squares[i][j].highlight();
                                            green.add(squares[i][j]);
                                        }
                                    }
                                    i--;
                                    j++;

                                }
                            }
                        }
                    } else {
                        if (column < 7) {
                            if (row < 7) {
                                if (squares[row + 1][column + 1].getChecker() == null) {
                                    if (progress != 6 && (necessarySquares.size() == 0 || searching == true)) {
                                        squares[row + 1][column + 1].highlight();
                                        green.add(squares[row + 1][column + 1]);
                                    }
                                } else {
                                    if (squares[row + 1][column + 1].getChecker().isWhite() == true && row < 6 && column < 6 && squares[row + 2][column + 2].getChecker() == null) {
                                        squares[row + 1][column + 1].highlight();
                                        redrb = squares[row + 1][column + 1];
                                        squares[row + 2][column + 2].highlight();
                                        deadGreenrb.add(squares[row + 2][column + 2]);
                                    }
                                }
                            }
                            if (row > 1) {
                                if (squares[row - 1][column + 1].getChecker() != null) {
                                    if (squares[row - 1][column + 1].getChecker().isWhite() == true && column < 6 && squares[row - 2][column + 2].getChecker() == null) {
                                        squares[row - 1][column + 1].highlight();
                                        redrt = squares[row - 1][column + 1];
                                        squares[row - 2][column + 2].highlight();
                                        deadGreenrt.add(squares[row - 2][column + 2]);
                                    }
                                }
                            }
                        }
                        if (column > 0) {
                            if (row < 7) {
                                if (squares[row + 1][column - 1].getChecker() == null) {
                                    if (progress != 6 && (necessarySquares.size() == 0 || searching == true)) {
                                        squares[row + 1][column - 1].highlight();
                                        green.add(squares[row + 1][column - 1]);
                                    }
                                } else {
                                    if (squares[row + 1][column - 1].getChecker().isWhite() == true && row < 6 && column > 1 && squares[row + 2][column - 2].getChecker() == null) {
                                        squares[row + 1][column - 1].highlight();
                                        redlb = squares[row + 1][column - 1];
                                        squares[row + 2][column - 2].highlight();
                                        deadGreenlb.add(squares[row + 2][column - 2]);
                                    }
                                }
                            }
                            if (row > 1) {
                                if (squares[row - 1][column - 1].getChecker() != null) {
                                    if (squares[row - 1][column - 1].getChecker().isWhite() == true && column > 1 && squares[row - 2][column - 2].getChecker() == null) {
                                        squares[row - 1][column - 1].highlight();
                                        redlt = squares[row - 1][column - 1];
                                        squares[row - 2][column - 2].highlight();
                                        deadGreenlt.add(squares[row - 2][column - 2]);
                                    }
                                }
                            }
                        }
                    }


                    if ((redlb != null || redlt != null || redrb != null || redrt != null) && progress == 6) {
                        progress = 5;
                        canChange = false;
                    } else {
                        if (progress == 6 && !searching2) {
                            progress = 1;
                            finder(true);
                            draw();
                        }
                    }
                    highlight();
                }
            }
        }
    }


    public static void ifprogress5(int row, int column) {
        if (row == purple.getRow() && column == purple.getColumn() && canChange) {
            draw();
            clean2();
            progress = 4;
            lastDirection = 0;
        } else {
            if (squares[row][column].getChecker() != null && canChange) {
                if (!squares[row][column].getChecker().isWhite()) {
                    draw();
                    clean2();
                    ifprogress4(row, column);
                    lastDirection = 0;
                }
            } else {
                if (row == 7) {
                    purple.getChecker().setKing(true);
                }
                if(veryDeadGreen.indexOf(squares[row][column])!=-1){
                    if (deadGreenlb.indexOf(squares[row][column]) != -1) {
                        squares[row][column].setChecker(purple.getChecker());
                        purple.setChecker(null);
                        redlb.setChecker(null);
                        whitecounter--;
                        lastDirection = 3;
                        progress = 6;
                        clean();
                        draw();
                        ifprogress4(row, column);
                    } else {
                        if (deadGreenlt.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redlt.setChecker(null);
                            whitecounter--;
                            lastDirection = 4;
                            progress = 6;
                            clean();
                            draw();
                            ifprogress4(row, column);
                        } else {
                            if (deadGreenrb.indexOf(squares[row][column]) != -1) {
                                squares[row][column].setChecker(purple.getChecker());
                                purple.setChecker(null);
                                redrb.setChecker(null);
                                whitecounter--;
                                lastDirection = 2;
                                progress = 6;
                                clean();
                                draw();
                                ifprogress4(row, column);
                            } else {
                                if (deadGreenrt.indexOf(squares[row][column]) != -1) {
                                    squares[row][column].setChecker(purple.getChecker());
                                    purple.setChecker(null);
                                    redrt.setChecker(null);
                                    whitecounter--;
                                    lastDirection = 1;
                                    progress = 6;
                                    clean();
                                    draw();
                                    ifprogress4(row, column);
                                }
                            }
                        }
                    }
                }else {
                    if (veryDeadGreen.size() == 0) {
                        if (green.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            clean();
                            draw();
                            lastDirection = 0;
                            progress = 1;
                            finder(true);
                        }
                        if (deadGreenlb.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redlb.setChecker(null);
                            whitecounter--;
                            lastDirection = 3;
                            progress = 6;
                            clean();
                            draw();
                            ifprogress4(row, column);
                        }
                        if (deadGreenrb.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redrb.setChecker(null);
                            whitecounter--;
                            lastDirection = 2;
                            progress = 6;
                            clean();
                            draw();
                            ifprogress4(row, column);
                        }
                        if (deadGreenrt.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redrt.setChecker(null);
                            whitecounter--;
                            lastDirection = 1;
                            progress = 6;
                            clean();
                            draw();
                            ifprogress4(row, column);
                        }
                        if (deadGreenlt.indexOf(squares[row][column]) != -1) {
                            squares[row][column].setChecker(purple.getChecker());
                            purple.setChecker(null);
                            redlt.setChecker(null);
                            whitecounter--;
                            lastDirection = 4;
                            progress = 6;
                            clean();
                            draw();
                            ifprogress4(row, column);
                        }
                    }
                }
            }
        }
    }


    public static void finder(boolean isWhite) {

        searching = true;
        ArrayList<Checker> temp;
        int temp2;
        if (isWhite) {
            temp = whites;
            temp2 = 1;
        } else {
            temp = blacks;
            temp2 = 4;
        }
        for (int i = 0; i < temp.size(); i++) {
            if (isWhite) {
                ifprogress1(temp.get(i).getRow(), temp.get(i).getColumn());
            } else {
                ifprogress4(temp.get(i).getRow(), temp.get(i).getColumn());
            }
            draw();
            progress = temp2;
            if (redlb != null || redlt != null || redrb != null || redrt != null) {
                necessarySquares.add(squares[temp.get(i).getRow()][temp.get(i).getColumn()]);
            }
            clean3();
        }
        for (int i = 0; i < necessarySquares.size(); i++) {
            necessarySquares.get(i).highlight3();
        }

        if (green.size() == 0 && deadGreenlb.size() == 0 && deadGreenlt.size() == 0 && deadGreenrb.size() == 0 && deadGreenrt.size() == 0) {
        }
        clean2();
        draw();
        searching = false;
    }

    public static void highlight() {
        draw();
        if (purple != null)
            if (purple.getChecker() != null)
                purple.highlight2();

        if (redlb != null)
            redlb.highlight();
        if (redlt != null)
            redlt.highlight();
        if (redrb != null)
            redrb.highlight();
        if (redrt != null)
            redrt.highlight();

        if (deadGreenlb.size() == 0 && deadGreenlt.size() == 0 && deadGreenrb.size() == 0 && deadGreenrt.size() == 0 && veryDeadGreen.size() == 0) {
        } else {
            green = new ArrayList<Square>();
        }
        if (veryDeadGreen.size() == 0) {
            for (int i = 0; i < green.size(); i++) {
                green.get(i).highlight();
            }
            for (int i = 0; i < deadGreenlb.size(); i++) {
                deadGreenlb.get(i).highlight();
            }
            for (int i = 0; i < deadGreenlt.size(); i++) {
                deadGreenlt.get(i).highlight();
            }
            for (int i = 0; i < deadGreenrb.size(); i++) {
                deadGreenrb.get(i).highlight();
            }
            for (int i = 0; i < deadGreenrt.size(); i++) {
                deadGreenrt.get(i).highlight();
            }
        } else {
            for (int i = 0; i < veryDeadGreen.size(); i++) {
                veryDeadGreen.get(i).highlight();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_layout_mode_2_and_3);
        activity=this;
        boolean isWhite = true;
        board = (TableLayout) findViewById(R.id.board);
        Intent intent=getIntent();
        yourColor=intent.getIntExtra("color",0);
        // если игра идёт на 1 устройстве, значение(yourColor) должно быть 0   ---------------------------------------------------------------------------------------------------------------
        // если на разных, то 1 для белого и 2 для чёрного   ---------------------------------------------------------------------------------------------------------------------------------




        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(isWhite, i, j);
                if (isWhite == false) {
                    if (i < 3) {
                        blacks.add(new Checker(i, j, false));
                        squares[i][j].setChecker(blacks.get(blackcounter));
                        blackcounter++;
                    }
                    if (i > 4) {
                        whites.add(new Checker(i, j, true));
                        squares[i][j].setChecker(whites.get(whitecounter));
                        whitecounter++;
                    }
                }

                if (j < 7) {
                    if (isWhite == true) {
                        isWhite = false;
                    } else {
                        isWhite = true;
                    }
                }
            }
        }
        draw();
    }

    public void test(View v) {
        Toast.makeText(this,"В разработке",Toast.LENGTH_SHORT).show();
    }

}
