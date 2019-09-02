package in.edu.ssn.insta.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.edu.ssn.insta.R;
import in.edu.ssn.insta.classes.comment_details;

public class commentsadapter extends ArrayAdapter<comment_details> {

    public commentsadapter(Context context, ArrayList<comment_details> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final comment_details object = (comment_details) getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_list_items, parent, false);

        TextView comments = convertView.findViewById(R.id.comment_text);
        comments.setText(object.getComments());

        return convertView;
    }
}
