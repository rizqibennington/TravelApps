package com.rizqi.travel.model;

public class HistoryHotelModel {

    private String mIdBook;
    private String mNamaHotel;
    private int mDurasi;
    private int mKamar;
    private String mTanggal;
    private int mTotal;
    private String mDeskripsi;
    private int mImageResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;

    public HistoryHotelModel(String idBook, String namaHotel, int durasi, int kamar, String tanggal, int total, String deskripsi, int imageResourceId) {
        mIdBook = idBook;
        mNamaHotel = namaHotel;
        mDurasi = durasi;
        mKamar = kamar;
        mTanggal = tanggal;
        mTotal = total;
        mDeskripsi = deskripsi;
        mImageResourceId = imageResourceId;
    }

    public String getIdBook() {
        return mIdBook;
    }

    public String getNamaHotel() {
        return mNamaHotel;
    }

    public int getDurasi() {
        return mDurasi;
    }

    public int getKamar() {
        return mKamar;
    }

    public String getTanggal() {
        return mTanggal;
    }

    public int getTotal() {
        return mTotal;
    }

    public String getDeskripsi() { return mDeskripsi;}

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}