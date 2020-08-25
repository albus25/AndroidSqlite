package com.example.mark2;

public class category {
    private int categoryID;
    private String categoryName;

    public category() {
    }

    public category(String categoryName) {
        this.categoryName = categoryName;
    }

    public category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
