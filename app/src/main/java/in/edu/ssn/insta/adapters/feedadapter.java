package in.edu.ssn.insta.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

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

import in.edu.ssn.insta.R;
import in.edu.ssn.insta.Activities.commentsubmit;
import in.edu.ssn.insta.classes.post_details;
import in.edu.ssn.insta.Activities.view_comments;

public class feedadapter extends ArrayAdapter<post_details> {
    private static final String TAG ="app_test" ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference postcolref = db.collection("posts");
    int likeflag = 0;
    int like_f = 0;

    public feedadapter(Context context, ArrayList<post_details> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final post_details object = (post_details) getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.insta_layout, parent, false);

        TextView username = convertView.findViewById(R.id.username);
        TextView username_blw = convertView.findViewById(R.id.username_below);
        TextView desc = convertView.findViewById(R.id.description);
        ImageView user_img = convertView.findViewById(R.id.user_img);
        ImageView post_img = convertView.findViewById(R.id.post_pic);
        final ImageView like = convertView.findViewById(R.id.like);
        ImageView comment_submit = convertView.findViewById(R.id.comment_submit);
        TextView comment = convertView.findViewById(R.id.comment_view);


        username.setText(object.getUsername());
        username_blw.setText(object.getUsername());
        desc.setText(object.getDesc());
        Picasso.get().load(object.getUser_img()).into(user_img);
        Picasso.get().load(object.getPost_img()).into(post_img);
        Map<String, Object> likes_details = new HashMap<>();
        likes_details.put("taken",true);
        postcolref.document(object.getDocument_id()).set(likes_details, SetOptions.merge());

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (like_f == 0) {
                    like.setImageResource(R.drawable.heart);
                    like_f = 1;
                } else {
                    like.setImageResource(R.drawable.like);
                    like_f = 0;
                }
                postcolref.document(object.getDocument_id()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Long likes = (Long) task.getResult().get("like");

                        if (likeflag == 0) {
                            like.setImageResource(R.drawable.heart);
                            likes++;
                            likeflag = 1;
                        } else {
                            like.setImageResource(R.drawable.like);
                            likeflag = 0;
                            if (likes > 0) {
                                likes--;
                            }
                        }
                        Map<String, Object> likes_details = new HashMap<>();
                        likes_details.put("like", likes);
                        postcolref.document(object.getDocument_id()).set(likes_details, SetOptions.merge());
                    }
                });


            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), view_comments.class);
                intent.putExtra("docid",object.getDocument_id());
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        comment_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), commentsubmit.class);
                intent.putExtra("docid",object.getDocument_id());
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
