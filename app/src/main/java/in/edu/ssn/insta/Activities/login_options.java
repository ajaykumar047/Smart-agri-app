package in.edu.ssn.insta.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import in.edu.ssn.insta.R;

public class login_options extends AppCompatActivity {

    ImageView farming;
    ImageView gardeneing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
        farming = (ImageView)findViewById(R.id.farmining_button);
        gardeneing=(ImageView)findViewById(R.id.gardening_btn);


        final String[] year_list = {"Before pumping, properly agitate the manure in your storage tank to make sure you get it as uniform as possible"};
        final AlertDialog.Builder options = new AlertDialog.Builder(login_options.this,R.style.MyDialogTheme);
        options.setTitle("Hints and tips");
        options.setItems(year_list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        options.setPositiveButton(
                "Got It !",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        options.show();


        farming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_options.this,language.class);
                startActivity(intent);

            }
        });
        gardeneing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPref.getBoolean(getApplicationContext(),"sp_loggedin") == true)
                {
                    Intent intent = new Intent(login_options.this,home.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(login_options.this,login_activity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(startMain);
        finishAffinity();
        finish();
    }
}
