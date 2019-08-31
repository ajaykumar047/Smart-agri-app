package in.edu.ssn.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class map extends AppCompatActivity {

    private static final String TAG = "app_test";
    WebView map;
    ListView plant_sugg_lv;
    ArrayList<plant_sugg_details> arr_list = new ArrayList<>();
    plant_sugg_adapter arr_adp;
    String longitude;
    String latitude;
    String loc;
    String month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        map = (WebView) findViewById(R.id.map_webview);
        plant_sugg_lv = (ListView) findViewById(R.id.plant_suggestion_lv);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            @SuppressLint("MissingPermission")
            Location location = (Location) lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            longitude = String.valueOf(location.getLongitude());
            latitude = String.valueOf(location.getLatitude());
            Log.i("app_test", "longitude: " + longitude);
            Log.i("app_test", "latitude: " + latitude);

            map.setWebViewClient(new WebViewClient());
            map.getSettings().setJavaScriptEnabled(true);
            map.loadUrl("https://www.google.com/maps/place/" + latitude + "+" + longitude);
            Log.i(TAG, "https://www.google.com/maps/place/" + latitude + "+" + longitude);

            if (Double.parseDouble(latitude) > 23.43674) {
                loc = "North";
            } else
                loc = "South";

            SimpleDateFormat formatter = new SimpleDateFormat("MM");
            Date date = new Date();
            month = formatter.format(date);
            if (loc.equals("North")) {
                switch (month) {
                    case "01":
                    case "02":
                    case "03":
                    case "04":
                    case "05":
                    case "06":
                    case "07":
                    case "08":
                    case "09":
                    case "10":
                    case "11":
                    case "12":

                }
            } else if (loc.equals("South")) {
                switch (month) {
                    case "08":
                        Log.i(TAG, "south onCreate:sdfaew ");
                }
            }
        }
    }
}
