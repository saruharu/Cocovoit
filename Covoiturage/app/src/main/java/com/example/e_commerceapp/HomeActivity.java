package com.example.e_commerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerceapp.Model.Product;
import com.example.e_commerceapp.MyAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.e_commerceapp.CartActivity.ProductsCart;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<Product> Products = new ArrayList<>();
    public static HashMap<Product,Button> BtnMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        //Button addC = (Button) findViewById(R.id.add_to_cart);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.nav_header_textView);



        userNameTextView.setText(MainActivity.usernn);
        initProd();


        /*addC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView name;
                name = (TextView) findViewById(R.id.name);
                String namme = (String) name.getText();
                int count = 0;
                while (Products.size() > count) {
                    if (namme.equals(Products.get(count).name)){
                        ProductsCart.add(Products.get(count));
                    }
                    else {
                        count++;
                    }
                }

            }
        });*/


    }



    /*public boolean onCreateOptionsMenu(Menu menu) {
// TODO Auto-generated method stub
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
// On peut créer le menu via le code
            case R.id.item1: Toast.makeText(this, "à propos", Toast.LENGTH_LONG).show();
                break;
            case R.id.item2: Toast.makeText(this, "Ajouter un etablissement",
                    Toast.LENGTH_LONG).show(); break;
            case R.id.item3: Toast.makeText(this, "supprimer un etablissement",
                    Toast.LENGTH_LONG).show(); break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void initProd(){
        int stat = 0;
        Products.add(new Product("Nvidia GTX 1060","3000 DH" ,"Carte graphique Nvidia Asus ROG GTX 1060", R.drawable.nvy,stat));
        Products.add(new Product("Nvidia GTX 1080", "5000 DH" ,"Carte graphique Nvidia Asus MSI GTX 1080", R.drawable.nvi,stat));
        Products.add(new Product("Nvidia RTX 2080", "8000 DH" ,"Carte graphique Nvidia Asus MSI RTX 2080", R.drawable.nviii,stat));
        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView rv = this.findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(this, Products);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_cart)
        {
                Intent intent =new Intent(HomeActivity.this,AddTrajetActivity.class );
            startActivity(intent);
        }

        else if (id == R.id.nav_settings)
        {

            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_logout)
        {


            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            ProductsCart.clear();
            startActivity(intent);
            finish();
        }

        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //    if (id == R.id.action_settings)
        //     {
        //        return true;
        //    }

        return super.onOptionsItemSelected(item);
    }
}
