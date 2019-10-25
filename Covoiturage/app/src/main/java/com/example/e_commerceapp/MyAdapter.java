package com.example.e_commerceapp;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import android.content.Context;
import android.widget.Toast;

import com.example.e_commerceapp.Model.Product;

import static com.example.e_commerceapp.CartActivity.ProductsCart;
import static com.example.e_commerceapp.HomeActivity.BtnMap;


public class MyAdapter extends RecyclerView.Adapter {
    ArrayList Products;
    Context context;
    private int lastPosition = -1;



    // for getting the data from Activity "Mylist"
    public MyAdapter(Context context, ArrayList Prod) {
        this.context = context;
        this.Products = Prod;

    }


    @Override

    //inflate the layout item xml and pass it to View Holder
    //associer notre adapter à notre vu
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product, parent, false);
        return new MyViewHolder(view);

    }

    //
    @Override
    //set the data in the view’s by way of ViewHolder.
    //affecte les données aux widgets de la vue
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final Product Prod= (Product) Products.get(position);
        ((MyViewHolder)    holder).name.setText(Prod.getName());
        ((MyViewHolder)    holder).desc.setText(Prod.getDesc());
        ((MyViewHolder)    holder).price.setText(Prod.getPrice());
        ((MyViewHolder)    holder).propic.setImageResource(Prod.getPic());
        ((MyViewHolder)    holder).display(Prod);
        setAnimation(holder.itemView, position);

        ((MyViewHolder) holder).addC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle(((MyViewHolder) holder).currentProd.getName())
                        .show();*/
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                ProductsCart.add(((MyViewHolder) holder).currentProd);

            }
        });


    }

    private void setAnimation(View itemView, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {

        return Products.size();
    }


    //get the reference of item view's
    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView desc;
        TextView name;
        TextView price;
        ImageView propic;
        Button addC;
        private Product currentProd;
        public MyViewHolder(final View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.desc);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            propic=  itemView.findViewById(R.id.propic);
            addC = itemView.findViewById(R.id.add_to_cart);



            /*addC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentProd.getName())
                            .show();


                }
            });*/



        }

        public void display(Product Prod) {
            currentProd = Prod;
            name.setText(Prod.getName());
            price.setText(Prod.getPrice());
            desc.setText(Prod.getDesc());
            propic.setImageResource(Prod.getPic());







        }


    }



}
