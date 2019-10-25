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
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerceapp.Model.Product;
import com.example.e_commerceapp.MyAdapter;

import java.util.ArrayList;



public class CartActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static ArrayList<Product> ProductsCart = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.nav_header_textView);



        userNameTextView.setText(MainActivity.usernn);
        initProd();
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

        initRecyclerView();
    }
    private void initRecyclerView(){
        RecyclerView rv = this.findViewById(R.id.list);
        MyAdapterCart adapter = new MyAdapterCart(this, ProductsCart);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.nav_cart)
        {
            Intent intent =new Intent(CartActivity.this,CartActivity.class );
            startActivity(intent);
        }

        else if (id == R.id.nav_settings)
        {

            Intent intent = new Intent(CartActivity.this, SettingActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_logout)
        {


            Intent intent = new Intent(CartActivity.this, MainActivity.class);
            ProductsCart.clear();
            startActivity(intent);
            finish();
        }

        return false;
    }
}
