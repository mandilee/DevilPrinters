package uk.co.mandilee.devilprinters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private boolean isTelephonyEnabled() {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        return tm != null && tm.getSimState() == TelephonyManager.SIM_STATE_READY;
    }


    public void callUsNow(View v) {
        if (isTelephonyEnabled()) {
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + getString(R.string.company_phone).replace(" ", "");
            i.setData(Uri.parse(p));
            startActivity(i);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Cannot dial number";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void emailUsNow(View v) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("plain/text");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.info_email)});
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));
        startActivity(Intent.createChooser(i, ""));

    }

    public void visitWebsite(View v) {
        String url = "http://" + getString(R.string.company_url);
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(intent);

    }

}
