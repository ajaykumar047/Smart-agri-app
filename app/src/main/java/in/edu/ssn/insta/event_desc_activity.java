package in.edu.ssn.insta;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class event_desc_activity extends AppCompatActivity {

    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_description);
        desc=(TextView)findViewById(R.id.event_desc_tv);
        desc.setText(getIntent().getExtras().getString("desc"));
    }
}
