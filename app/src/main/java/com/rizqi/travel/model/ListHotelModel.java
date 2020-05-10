package com.rizqi.travel.model;

public class ListHotelModel {

    private String mIdHotel;
    private String mNamaHotel;
    private String mDeskripsi;
    private String mHarga;
    private Float mRating;
    private int mImageResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public ListHotelModel(String idHotel, String namaHotel, String deskripsi, String harga, Float rating,  int imageResourceId) {
        mIdHotel = idHotel;
        mNamaHotel = namaHotel;
        mDeskripsi = deskripsi;
        mHarga = harga;
        mRating = rating;
        mImageResourceId = imageResourceId;
    }

    public String getIdHotel() {
        return mIdHotel;
    }

    public String getNamaHotel() {
        return mNamaHotel;
    }

    public String getDeskripsi() {
        return mDeskripsi;
    }

    public String getHarga() {
        return mHarga;
    }

    public float getRating(){ return mRating; }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}