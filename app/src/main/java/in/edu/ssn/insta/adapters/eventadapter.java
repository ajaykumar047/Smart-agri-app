package in.edu.ssn.insta.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import in.edu.ssn.insta.R;
import in.edu.ssn.insta.Activities.event_desc_activity;
import in.edu.ssn.insta.classes.event_item_details;

public class eventadapter extends ArrayAdapter<event_item_details> {
    private static final String TAG = "app_test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference postcolref = db.collection("posts");
    int likeflag = 0;
    String key_pass;

    public eventadapter(Context context, ArrayList<event_item_details> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final event_item_details object = (event_item_details) getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_list_items, parent, false);

        LinearLayout base = convertView.findViewById(R.id.base_linear);
        TextView name = convertView.findViewById(R.id.event_name);
        TextView date = convertView.findViewById(R.id.event_date);
        TextView obj = convertView.findViewById(R.id.event_obj);

        name.setText(object.getName());
        date.setText(object.getDate());
        obj.setText(object.getObj());
        base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), event_desc_activity.class);
                intent.putExtra("desc",object.getDesc());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }


}
