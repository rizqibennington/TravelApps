package com.rizqi.travel.model;

public class ListHotelModel {

    private String mIdHotel;
    private String mNamaHotel;
    private String mDeskripsi;
    private String mHarga;
    private int mImageResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public ListHotelModel(String idHotel, String namaHotel, String deskripsi, String harga, int imageResourceId) {
        mIdHotel = idHotel;
        mNamaHotel = namaHotel;
        mDeskripsi = deskripsi;
        mHarga = harga;
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

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}