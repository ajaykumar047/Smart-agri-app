package in.edu.ssn.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class shop extends AppCompatActivity {
    ImageView flipkart;
    ImageView amazon;
    ImageView plants;
    ImageView youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        flipkart=(ImageView)findViewById(R.id.acm_1);
        amazon=(ImageView)findViewById(R.id.acm_2);
        plants = (ImageView)findViewById(R.id.acm_3);
        youtube = (ImageView)findViewById(R.id.acm4);

        flipkart.setOnClickListener(f_redirect);
        amazon.setOnClickListener(A_redirect);
        plants.setOnClickListener(w_redirect);
        youtube.setOnClickListener(y_redirect);


    }

    View.OnClickListener f_redirect=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse("https://www.flipkart.com/search?q="+"iphones");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }
    };
    View.OnClickListener A_redirect=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse("https://www.amazon.in/s?k="+"socks");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }
    };
    View.OnClickListener w_redirect=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse("https://www.wikihow.com/wikiHowTo?search=grow+tomato");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }
    };
    View.OnClickListener y_redirect=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse("https://www.youtube.com/results?search_query=grow+tomatoes");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        }
    };
}
