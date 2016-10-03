package by.superteam.supercheckers;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class WirelessActivity extends AppCompatActivity {
    static String user = "";
    static String color = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooser);
        if (user.equals("host")) {
            boolean blackOrWhite = (new Random()).nextBoolean();
            if (blackOrWhite) {
                try {
                    SuperSocket.send("white");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                color = "black";

           /* ((ImageView)findViewById(R.id.arrow)).setImageResource(R.drawable.anim1);
            AnimationDrawable frameAnimation = (AnimationDrawable) ((ImageView)findViewById(R.id.arrow)).getBackground();

            // Start the animation (looped playback by default).
            frameAnimation.start();*/
            } else {
                try {
                    SuperSocket.send("black");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                color = "white";

          /*  ((ImageView)findViewById(R.id.arrow)).setImageResource(R.drawable.anim2);
            AnimationDrawable frameAnimation = (AnimationDrawable) ((ImageView)findViewById(R.id.arrow)).getBackground();
            color="white";
            // Start the animation (looped playback by default).
            frameAnimation.start();*/

            }
        }
        while (color.equals("")) {

        }
        Toast.makeText(this, "hey, you are " + color + "!", Toast.LENGTH_LONG).show();

        ImageView img = (ImageView) findViewById(R.id.arrow);
        if (color.equals("white")) {
            img.setBackgroundResource(R.drawable.anim2);
        }
        if (color.equals("black")) {
            img.setBackgroundResource(R.drawable.anim1);
        }
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
frameAnimation.setOneShot(true);
        // Start the animation (looped playback by default).
        frameAnimation.start();
    }

    public static void forclient(String message) {
        if (message.equals("white")) {


            color = "white";
            // Start the animation (looped playback by default).
        /*frameAnimation.start();*/
        }
        if (message.equals("black")) {

            color = "black";

           /* AnimationDrawable frameAnimation = (AnimationDrawable) ((ImageView)findViewById(R.id.arrow)).getBackground();

            // Start the animation (looped playback by default).
            frameAnimation.start();*/
        }
    }
}



