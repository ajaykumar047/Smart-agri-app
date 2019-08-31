package in.edu.ssn.insta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class upload extends AppCompatActivity {

    private static final int IMAGE_CAPTURE = 2;
    private static final String TAG = "app_test";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference postcolref = db.collection("posts");
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://insta-fa46a.appspot.com/");

    Button uploadbtn;
    Button uploadimg_btn;
    EditText desc;
    ImageView img_preview;

    Bitmap imageBitmap;

    final static String sname = "name";
    final static String sdesc = "desc";
    final static String slike = "like";
    final static String staken = "taken";
    final static String sdocid = "docid";
    final static String scomments = "comment";
    final static String spost_img = "post_img";
    final static String suser_img = "user_img";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        uploadbtn = (Button) findViewById(R.id.upload_btn_up);
        uploadimg_btn = (Button) findViewById(R.id.upload_img_btn);
        desc = (EditText) findViewById(R.id.desc_up);
        img_preview = (ImageView) findViewById(R.id.image_preview);

        uploadbtn.setOnClickListener(uploading_data);
        uploadimg_btn.setOnClickListener((pic_image));


    }

    View.OnClickListener pic_image = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Camera_intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(Camera_intent, IMAGE_CAPTURE);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            img_preview.setImageBitmap(imageBitmap);
        }
    }

    View.OnClickListener uploading_data = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ArrayList<String> comments = new ArrayList<>();
            final Map<String, Object> user_details = new HashMap<>();
            user_details.put(sname, SharedPref.getString(getApplicationContext(),"sp_Username"));
            user_details.put(sdesc, desc.getText().toString());
            user_details.put(slike,0);
            user_details.put(staken,false);
            user_details.put(scomments,comments);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if(imageBitmap!=null) {
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            }
            final byte[] data = baos.toByteArray();


            //Adding User data to the firestore.................
            postcolref.add(user_details)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            if (documentReference != null) {
                                final String document_id = documentReference.getId();
                                StorageReference sto_ref = storageRef.child(document_id);
                                UploadTask uploadTask = sto_ref.putBytes(data);
                                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                String download_url = uri.toString();
                                                Map<String, Object> user_img_details = new HashMap<>();
                                                user_img_details.put(sdocid,document_id);
                                                user_img_details.put(spost_img, download_url);
                                                user_img_details.put(suser_img, SharedPref.getString(getApplicationContext(),"sp_image_url"));
                                                postcolref.document(document_id).set(user_img_details, SetOptions.merge());
                                                Toast.makeText(upload.this, "Upload success", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(upload.this, "Upload failure", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    };
}
