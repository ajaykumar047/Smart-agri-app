package in.edu.ssn.insta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login_activity extends AppCompatActivity {

    private static final String TAG = "app_test";
    EditText user_id;
    EditText password;
    Button submit_but;
    Button info;
    Intent intent;

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 111;

    private View.OnClickListener submit_pressed = new View.OnClickListener() {
        public void onClick(View v) {
            mAuth.signOut();
            mGoogleSignInClient.signOut();
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    };
    private View.OnClickListener info_pressed = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(login_activity.this, "Use your SSN mail id", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        user_id = (EditText) findViewById(R.id.user_id_login);
        password = (EditText) findViewById(R.id.password_login);
        submit_but = (Button) findViewById(R.id.submit_login);
        info = (Button) findViewById(R.id.info_login);

        submit_but.setOnClickListener(submit_pressed);
        intent = new Intent(login_activity.this, home.class);
        user_id.setPaintFlags(0);
        password.setPaintFlags(0);
        info.setOnClickListener(info_pressed);
        initGoogleSignIn();

    }

    public void initGoogleSignIn() {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(login_activity.this, gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                final GoogleSignInAccount acct = task.getResult(ApiException.class);
                SharedPref.putString(getApplicationContext(), "sp_Username", acct.getDisplayName());
                SharedPref.putString(getApplicationContext(), "image_url", acct.getPhotoUrl().toString());
                SharedPref.putString(getApplicationContext(), "email", acct.getEmail());
                Log.i(TAG, "onActivityResult: "+SharedPref.getString(getApplicationContext(),"image_url"));


                AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

                mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),home.class);
                            startActivity(intent);
                        } else {
                            Log.d(TAG, "signInWithCredential:failure  "+task.getException().toString());
                        }
                    }
                });
                //else
                //Toast.makeText(this, "Please use SSN mail ID", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d(TAG, "error: " + e.toString());


            }
        }
    }

}
