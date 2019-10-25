package com.example.e_commerceapp.Model;

import android.widget.ImageView;

public class Product {

    public String name,price,Desc;
    public int pic;
    private int stat ;

    public Product(String name, String price, String desc, int pic, int stat) {
        this.name = name;
        this.price = price;
        Desc = desc;
        this.pic = pic;
        this.stat = stat;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return Desc;
    }

    public int getPic() {
        return pic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
}
