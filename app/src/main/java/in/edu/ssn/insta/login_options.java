package in.edu.ssn.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class login_options extends AppCompatActivity {

    ImageView farming;
    ImageView gardeneing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
        farming = (ImageView)findViewById(R.id.farmining_button);
        gardeneing=(ImageView)findViewById(R.id.gardening_btn);

        farming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_options.this,Extensive_farming.class);
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
}
