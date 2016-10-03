package by.superteam.supercheckers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    public void commonDisplay(View v) {
        Intent intent = new Intent(this, SelectActivity.class);
        intent.putExtra("activity","Common");
        startActivity(intent);

    }

    public void oneDevice(View v) {
        Intent intent = new Intent(this, InGameMode2And3Activity.class);
        startActivity(intent);
    }
    public void wireless(View v) {
        Intent intent = new Intent(this, SelectActivity.class);
        intent.putExtra("activity","wifi");
        startActivity(intent);

    }
}
