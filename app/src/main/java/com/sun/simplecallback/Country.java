package com.sun.simplecallback;

public class Country {
    private int mImageResource;
    private String mCountryName;

    public Country(int mImageResource, String mCountryName) {
        this.mImageResource = mImageResource;
        this.mCountryName = mCountryName;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getmCountryName() {
        return mCountryName;
    }

    public void setmCountryName(String mCountryName) {
        this.mCountryName = mCountryName;
    }
}
