package in.edu.ssn.insta.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import in.edu.ssn.insta.R;
import in.edu.ssn.insta.classes.scheme_details;

public class schemesadapter extends ArrayAdapter<scheme_details> {
    private static final String TAG = "app_test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference postcolref = db.collection("posts");
    int likeflag = 0;
    String key_pass;

    public schemesadapter(Context context, ArrayList<scheme_details> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final scheme_details object = (scheme_details) getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.schemes_items, parent, false);


        TextView text1 = convertView.findViewById(R.id.scheme_name);
        TextView text2 = convertView.findViewById(R.id.scheme_desc);
        Button btn = convertView.findViewById(R.id.scheme_btn);




        text1.setText(object.getName());
        text2.setText(object.getDesc());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(String.valueOf(object.getLink()));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });


        return convertView;
    }

}
