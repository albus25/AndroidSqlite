package com.example.mark2;

public class book {
    private int bid;
    private String bname, bauthor, isbn;
    private double price;
    private int cID;
    private category c;

    public book() {
    }

    public book(String bname, String bauthor, String isbn, double price, int cID) {
        this.bname = bname;
        this.bauthor = bauthor;
        this.isbn = isbn;
        this.price = price;
        this.cID = cID;
    }

    public book(int bid, String bname, String bauthor, String isbn, double price, int cID) {
        this.bid = bid;
        this.bname = bname;
        this.bauthor = bauthor;
        this.isbn = isbn;
        this.price = price;
        this.cID = cID;
    }

    public book(int bid, String bname, String bauthor, String isbn, double price, int cID,category c) {
        this.bid = bid;
        this.bname = bname;
        this.bauthor = bauthor;
        this.isbn = isbn;
        this.price = price;
        this.cID = cID;

        this.c = c;
    }

    public category getC() {
        return c;
    }

    public void setC(category c) {
        this.c = c;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
