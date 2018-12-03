package com.example.janieamyot.chippy;

public class ListItem {
    private String title;
    private String subTitle;
    private int id;

    public ListItem(String title, String subTitle, int id) {
        this.title = title;
        this.subTitle = subTitle;
        this.id = id;
    }

    public ListItem(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;

    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
