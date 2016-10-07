package by.superteam.supercheckers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void play(View v){
        Intent intent = new Intent(this,PlayActivity.class);
        startActivity(intent);
    }

    public void shop(View v){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }
}
