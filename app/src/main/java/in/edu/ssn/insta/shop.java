package in.edu.ssn.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class shop extends AppCompatActivity {

    ArrayList<shop_item_details> arr_list = new ArrayList<>();
    shopadapter arr_adp;
    ListView shop_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shop_listView = (ListView)findViewById(R.id.shop_item_list);

        arr_list.add(new shop_item_details("Magnesium" , "Cow-Manure","epsom+salt+for+plants","cow+manure ",R.drawable.magnesium,R.drawable.cow_manure));
        arr_adp = new shopadapter(getApplicationContext(), arr_list);
        shop_listView.setAdapter(arr_adp);

    }


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
