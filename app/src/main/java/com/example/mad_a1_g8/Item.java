package com.example.mad_a1_g8;

public class Item {

    private String title;
    private String price;
    private String rating;
    private String address;
    private int image;

    public Item(String title,String price,String rating,String address,int image){
        this.title=title;
        this.price=price;
        this.rating=rating;
        this.address=address;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
