package com.example.e_commerceapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {

    Button update;
    private EditText email,password,mobilenum,username;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        update = (Button) findViewById(R.id.updt);

        email = (EditText) findViewById(R.id.maill);
        password = (EditText) findViewById(R.id.pswrddd);
        mobilenum = (EditText) findViewById(R.id.mobphonee);
        username = (EditText) findViewById(R.id.usrusrr);

        loadingBar = new ProgressDialog(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateAccount();
            }
        });

    }

    private void UpdateAccount() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String mobnum = mobilenum.getText().toString();
        String usrn = username.getText().toString();

        if (TextUtils.isEmpty(mail)){
            Toast.makeText(this, "please write your e-mail", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "please write your password", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(mobnum)){
            Toast.makeText(this, "please write your mobile number", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(usrn)){
            Toast.makeText(this, "please write your username", Toast.LENGTH_LONG);
        }
        else {
            loadingBar.setTitle("Creating account");
            loadingBar.setMessage("Please wait while we check the info ... ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validPHN(mail,pass,mobnum,usrn);
        }


    }

    private void validPHN(final String mail, final String pass, final String mobnum, final String usrn) {

            final DatabaseReference rootRef;
            rootRef = FirebaseDatabase.getInstance().getReference();

            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                        HashMap<String, Object> userdataMap = new HashMap<>();
                        userdataMap.put("username", usrn);
                        userdataMap.put("email", mail);
                        userdataMap.put("password", pass);
                        userdataMap.put("phone", mobnum);

                        rootRef.child("Users").child(MainActivity.usernn).updateChildren(userdataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(SettingActivity.this, "your account have been created", Toast.LENGTH_LONG).show();
                                            loadingBar.dismiss();
                                        }
                                    }
                                });




                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }





}
