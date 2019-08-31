package in.edu.ssn.insta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Extensive_farming_tamil extends AppCompatActivity {

    ArrayList<scheme_details> arr_list = new ArrayList<>();
    schemesadapter arr_adp;
    ListView scheme_listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extensive_farming);
        scheme_listView = (ListView) findViewById(R.id.Ext_farm_lv);


        arr_list.add(new scheme_details(R.string.sch_t1, R.string.sch_t1_d, "http://www.pmkisan.gov.in/"));
        arr_list.add(new scheme_details(R.string.sch_t2, R.string.sch_t2_d,"https://pmksy.gov.in/"));
        arr_list.add(new scheme_details(R.string.sch_t3, R.string.sch_t3_d,"https://pgsindia-ncof.gov.in/pkvy/index.aspx" ));
        arr_list.add(new scheme_details(R.string.sch_t4, R.string.sch_t4_d,"https://pib.gov.in/newsite/mbErel.aspx?relid=96201"));
        arr_adp = new schemesadapter(getApplicationContext(), arr_list);
        scheme_listView.setAdapter(arr_adp);
    }


}
