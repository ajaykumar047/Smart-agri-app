package in.edu.ssn.insta.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import in.edu.ssn.insta.R;
import in.edu.ssn.insta.adapters.shopadapter;
import in.edu.ssn.insta.classes.shop_item_details;

public class shop extends AppCompatActivity {

    ArrayList<shop_item_details> arr_list = new ArrayList<>();
    shopadapter arr_adp;
    ListView shop_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shop_listView = (ListView)findViewById(R.id.shop_item_list);

        arr_list.add(new shop_item_details("Epsom Salt" , "Cow Manure","epsom+salt+for+plants","cow+manure ",R.drawable.magnesium,R.drawable.cow_manure));
        arr_list.add(new shop_item_details("Seedling Tray" , "Gardening Tools Cutter","Kraft+Seeds+Seedling+Tray+Hole","Truphe+Gardening+Tools+Cutter+Gloves",R.drawable.unique,R.drawable.truphe));
        arr_list.add(new shop_item_details("Growth Booster" , "Vermicompost","plant+growth_booster","Vermicompost+for+plants",R.drawable.growth_booster,R.drawable.vermicompost));
        arr_list.add(new shop_item_details("Orange seeds" , "Dragon fruits","Vamsha+Nature+Care+Tangerine+Darjeeling","dragon+fruit+plant+seeds",R.drawable.orange,R.drawable.dragon));
        arr_list.add(new shop_item_details("Vertical pots" , "Watering can","vertical+plant+pots","plants_watering+cans",R.drawable.verticalpot,R.drawable.watercan));
        arr_adp = new shopadapter(getApplicationContext(), arr_list);
        shop_listView.setAdapter(arr_adp);

    }


}
