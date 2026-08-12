package com.nope.darkwarqr;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String URL = "https://pay.darkwar-survival.com/#/login";
    private static final String TV_BRO = "com.phlox.tvwebbrowser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openSite();
    }

    private void openSite() {
        Uri uri = Uri.parse(URL);

        // Prefer TV Bro because it is designed for Android/Google TV remotes.
        Intent tvBro = new Intent(Intent.ACTION_VIEW, uri);
        tvBro.setPackage(TV_BRO);

        try {
            startActivity(tvBro);
            finish();
            return;
        } catch (ActivityNotFoundException ignored) {
            // Fall through to whatever browser the TV has registered.
        }

        try {
            Intent generic = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(generic);
            finish();
        } catch (ActivityNotFoundException e) {
            TextView message = new TextView(this);
            message.setText("Please install TV Bro, then open Dark War QR again.");
            message.setTextSize(22);
            message.setGravity(Gravity.CENTER);
            message.setPadding(40, 40, 40, 40);
            setContentView(message);
        }
    }
}
