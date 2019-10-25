package com.example.e_commerceapp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class AddTrajetActivity extends AppCompatActivity {


    private EditText depart, destination, car, route, nbrPlace,date;

    private Button Addd;
    private EditText InputConductorName, Inputdepart, Inputdestination, Inputcar, InputRoute;

    private DatabaseReference TrajectRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trajet);

        Addd = (Button) findViewById(R.id.add);

        depart = (EditText) findViewById(R.id.dpt);

        destination = (EditText) findViewById(R.id.arv);

        date = (EditText) findViewById(R.id.dat);
        car = (EditText) findViewById(R.id.car);
        nbrPlace = (EditText) findViewById(R.id.nbrp);
        route = (EditText) findViewById(R.id.route);

        loadingBar = new ProgressDialog(this);

        Addd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTrajet();
            }
        });

    }

    private void CreateTrajet() {

        String dep = depart.getText().toString();
        String arrv = destination.getText().toString();
        String nbrplace = nbrPlace.getText().toString();
        String carr = car.getText().toString();
        String routee = route.getText().toString();
        String datee = date.getText().toString();

        if (TextUtils.isEmpty(dep)){
            Toast.makeText(this, "please write your e-mail", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(arrv)){
            Toast.makeText(this, "please write your password", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(nbrplace)){
            Toast.makeText(this, "please write your mobile number", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(carr)){
            Toast.makeText(this, "please write your username", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(routee)){
            Toast.makeText(this, "please write your username", Toast.LENGTH_LONG);
        }
        else if (TextUtils.isEmpty(datee)){
            Toast.makeText(this, "please write your username", Toast.LENGTH_LONG);
        }

        else {
            loadingBar.setTitle("Creating account");
            loadingBar.setMessage("Please wait while we check the info ... ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validPN(dep,arrv,nbrplace,carr,routee,datee);
        }
    }

    private void validPN(final String dep, final String arrv, final String nbrplace, final String carr, final String routee, final String datee) {
        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("depart", dep);
                    userdataMap.put("destination", arrv);
                    userdataMap.put("nbrplace", nbrplace);
                    userdataMap.put("car", carr);
                    userdataMap.put("routee", routee);
                    userdataMap.put("datee", datee);

                    rootRef.child("Trajets").child(dep).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(AddTrajetActivity.this, "your account have been created", Toast.LENGTH_LONG).show();
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
