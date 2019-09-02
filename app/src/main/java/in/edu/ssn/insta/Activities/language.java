package in.edu.ssn.insta.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import in.edu.ssn.insta.R;

public class language extends AppCompatActivity {

    TextView english;
    TextView tamil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        english=(TextView) findViewById(R.id.english_button);
        tamil=(TextView)  findViewById(R.id.tamil_button);

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(language.this,Extensive_farming.class);
                startActivity(intent);
            }
        });
        tamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(language.this,Extensive_farming_tamil.class);
                startActivity(intent);
            }
        });

    }
}
