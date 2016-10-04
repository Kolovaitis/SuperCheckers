package by.superteam.supercheckers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class CommonDisplayActivity extends AppCompatActivity {
    int height;
    int width;
    static int blackcounter = 0;
    static int whitecounter = 0;
    static TableLayout board;
    static Square[][] squares = new Square[8][8];
    static ArrayList<Checker> whites = new ArrayList<Checker>();
    static ArrayList<Checker> blacks = new ArrayList<Checker>();
    String a;
    public static String width2string;
    boolean isHost;
    static ArrayList<Square> necessarySquares = new ArrayList<Square>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_display);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        board=(TableLayout)findViewById(R.id.board) ;
        Intent intent = getIntent();
        a = intent.getStringExtra("button");
        if(a.equals("1")){
            isHost=true;
        }

        Point size = new Point();
        WindowManager w = getWindowManager();


            w.getDefaultDisplay().getSize(size);



        String widthstring33 = Integer.toString(width);

        try {
            SuperSocket.send(widthstring33);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isWhite = true;
        try {
            SuperSocket.startChecking();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(width2string==null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int width2 = Integer.parseInt(width2string);
        width = board.getWidth();
        height = board.getHeight();
        if (width < width2) {
            board.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        } else {
            board.setLayoutParams(new RelativeLayout.LayoutParams(width2, height));
        }

        board = (TableLayout) findViewById(R.id.board);

        board.setLayoutParams(new RelativeLayout.LayoutParams(width, height));


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


    public void draw() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if(isHost){
                    squares[i][j].drawSquare2();}
                else{
                    squares[i][j].drawSquare();
                }
            }
        }
        for (int i = 0; i < necessarySquares.size(); i++) {
            necessarySquares.get(i).highlight3();
        }
    }
    @Override
    public void onBackPressed() {
        try {
            SuperSocket.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}