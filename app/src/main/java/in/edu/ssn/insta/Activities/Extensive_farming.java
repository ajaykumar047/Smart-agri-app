package in.edu.ssn.insta.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import in.edu.ssn.insta.R;
import in.edu.ssn.insta.adapters.schemesadapter;
import in.edu.ssn.insta.classes.scheme_details;

public class Extensive_farming extends AppCompatActivity {

    ArrayList<scheme_details> arr_list = new ArrayList<>();
    schemesadapter arr_adp;
    ListView scheme_listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extensive_farming);
        scheme_listView = (ListView) findViewById(R.id.Ext_farm_lv);


        arr_list.add(new scheme_details(R.string.sch1, R.string.sch1_d, "http://www.pmkisan.gov.in/"));
        arr_list.add(new scheme_details(R.string.sch2, R.string.sch2_d,"https://pmksy.gov.in/"));
        arr_list.add(new scheme_details(R.string.sch3, R.string.sch3_d,"https://pgsindia-ncof.gov.in/pkvy/index.aspx" ));
        arr_list.add(new scheme_details(R.string.sch4, R.string.sch4_d,"https://pib.gov.in/newsite/mbErel.aspx?relid=96201"));
        arr_adp = new schemesadapter(getApplicationContext(), arr_list);
        scheme_listView.setAdapter(arr_adp);
    }


}
