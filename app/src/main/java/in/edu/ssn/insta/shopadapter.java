package in.edu.ssn.insta;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shopadapter extends ArrayAdapter<shop_item_details> {
    private static final String TAG = "app_test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference postcolref = db.collection("posts");
    int likeflag = 0;
    String key_pass;

    public shopadapter(Context context, ArrayList<shop_item_details> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        final shop_item_details object = (shop_item_details) getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_list_items, parent, false);

        ImageView Img1 = convertView.findViewById(R.id.acm_1);
        ImageView Img2 = convertView.findViewById(R.id.acm_2);
        TextView text1 = convertView.findViewById(R.id.text_shop_1);
        TextView text2 = convertView.findViewById(R.id.text_shop_2);


        Img1.setBackgroundResource(object.getPic1());
        Img2.setBackgroundResource(object.getPic2());

        text1.setText(object.getProduct_name1());
        text2.setText(object.getProduct_name2());
        Log.i(TAG, "getView: "+object.getKey1());
        Log.i(TAG, "getView: "+object.getKey2());

        Img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup(object.getKey1(),parent);
            }
        });
        Img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup(object.getKey2(),parent);
            }
        });


        return convertView;
    }

    public void popup(final String key, final ViewGroup par) {

        final String[] year_list = {"FLIPKART","AMAZON"};
        AlertDialog.Builder options = new AlertDialog.Builder(par.getRootView().getContext(),R.style.MyDialogTheme);
        options.setItems(year_list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (year_list[which].equals("FLIPKART")){
                            Uri uri = Uri.parse("https://www.flipkart.com/search?q="+key);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            par.getContext().startActivity(intent);


                }
                else if (year_list[which].equals("AMAZON")){
                            Uri uri = Uri.parse("https://www.amazon.in/s?k="+key);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            par.getContext().startActivity(intent);

                }
            }
        });
        options.show();
    }
}
