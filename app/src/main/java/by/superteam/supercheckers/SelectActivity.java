package by.superteam.supercheckers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SelectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }
    public void client(View v) {
        SuperSocket.conectAsClient(this);
    }
    public void host(View v) {
        Intent intent = new Intent(this, HostActivity.class);
        intent.putExtra("activity",getIntent().getStringExtra("activity"));
        startActivity(intent);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                if(getIntent().getStringExtra("activity").equals("Common"))
                SuperSocket.forActivityResult(result.getContents(), new Intent(this, CommonDisplayActivity.class), this);
                else if(getIntent().getStringExtra("activity").equals("wifi"))
                    SuperSocket.forActivityResult(result.getContents(), new Intent(this, WirelessActivity.class), this);
            }
        } else { // This is important, otherwise the result will not be passed to the fragment super.onActivityResult(requestCode, resultCode, data);
        } }

    @Override
    public void onStart () {
        super.onStart();

    }

    @Override
    public void onStop () {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Client Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://by.superteam.checkerswifi/http/host/path")
        );

    }
}