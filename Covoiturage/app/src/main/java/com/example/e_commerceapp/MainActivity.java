package com.example.e_commerceapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerceapp.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button lin, sup;
    private EditText usrn, pass;
    private ProgressDialog loadingBar;
    public static String usernn;

    private String dbname = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lin = (Button) findViewById(R.id.lin);
        sup = (Button) findViewById(R.id.sup);

        //userrname = (TextView) findViewById(R.id.nav_header_textView);

        usrn = (EditText) findViewById(R.id.usrusr);
        pass = (EditText) findViewById(R.id.pswrdd);

        loadingBar = new ProgressDialog(this);

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    private void loginUser() {
        String username = usrn.getText().toString();
        String password = pass.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "please write your username", Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "please write your password", Toast.LENGTH_LONG).show();
        }
        else{
            loadingBar.setTitle("Login account");
            loadingBar.setMessage("Please wait while we check the info ... ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            
            
            Allowaccess(username, password);
        }



    }

    private void Allowaccess(final String username, final String password) {

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();


        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(dbname).child(username).exists()){

                    Users usersDATA = dataSnapshot.child(dbname).child(username).getValue(Users.class);

                    if (usersDATA.getUsername().equals(username)){
                        if (usersDATA.getPassword().equals(password)){
                            Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();

                            usernn = username;


                            //userrname.setText("rfrfr");

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                    }

                }
                else{
                    Toast.makeText(MainActivity.this, "Please create an account", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
