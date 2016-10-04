package by.superteam.supercheckers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.net.Socket;

public class HostActivity extends AppCompatActivity {

    public final static int WHITE = 0xFFFFFFFF;
    public final static int BLACK = 0xFF000000;
    public final static int WIDTH = 600;
    public final static int HEIGHT = 600;
    public static Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        Intent intent = getIntent();
        String a = intent.getStringExtra("button");
        Intent intent2 = new Intent(this, CommonDisplayActivity.class);
        intent2.putExtra("button", a);
        if(getIntent().getStringExtra("activity").equals("Common"))
            SuperSocket.host(intent2,this, ((ImageView)findViewById(R.id.imageView)));
        else if(getIntent().getStringExtra("activity").equals("wifi"))
            SuperSocket.host(new Intent(this,WirelessActivity.class),this, ((ImageView)findViewById(R.id.imageView)));
        WirelessActivity.user="host";

    }

}
