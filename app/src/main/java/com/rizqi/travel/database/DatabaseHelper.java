package com.rizqi.travel.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_travel";
    public static final String TABLE_USER = "tb_user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";
    public static final String TABLE_BOOK = "tb_book";
    public static final String COL_ID_BOOK = "id_book";
    public static final String COL_ASAL = "asal";
    public static final String COL_TUJUAN = "tujuan";
    public static final String COL_TANGGAL = "tanggal";
    public static final String COL_DEWASA = "dewasa";
    public static final String COL_ANAK = "anak";
    public static final String TABLE_HARGA = "tb_harga";
    public static final String COL_HARGA_DEWASA = "harga_dewasa";
    public static final String COL_HARGA_ANAK = "harga_anak";
    public static final String COL_HARGA_TOTAL = "harga_total";
    public static final String TABLE_DAFTAR_HOTEL = "tb_daftar_hotel";
    public static final String COL_ID_HOTEL = "id_hotel";
    public static final String COL_NAMA_HOTEL = "nama_hotel";
    public static final String COL_KOTA_HOTEL = "kota_hotel";
    public static final String COL_HARGA_HOTEL = "harga_hotel";
    public static final String COL_RATING_HOTEL = "rating_hotel";
    public static final String TABLE_BOOK_HOTEL = "tb_book_hotel";
    public static final String COL_ID_BOOK_HOTEL = "id_book_hotel";
    public static final String COL_DURASI_MALAM = "durasi_hotel";
    public static final String COL_JUMLAH_KAMAR = "jumlah_kamar";
    public static final String COL_TANGGAL_HOTEL = "tanggal_hotel";
    public static final String COL_TOTAL_HARGA_HOTEL = "total_harga_hotel";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_USER + " (" + COL_USERNAME + " TEXT PRIMARY KEY, " + COL_PASSWORD +
                " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL("create table " + TABLE_BOOK + " (" + COL_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ASAL + " TEXT, " + COL_TUJUAN + " TEXT" + ", " + COL_TANGGAL + " TEXT, " + COL_DEWASA + " TEXT, "
                + COL_ANAK + " TEXT)");
        db.execSQL("create table " + TABLE_HARGA + " (" + COL_USERNAME + " TEXT, " + COL_ID_BOOK + " INTEGER, " +
                COL_HARGA_DEWASA + " TEXT, " + COL_HARGA_ANAK + " TEXT, " + COL_HARGA_TOTAL +
                " TEXT, FOREIGN KEY(" + COL_USERNAME + ") REFERENCES " + TABLE_USER
                + ", FOREIGN KEY(" + COL_ID_BOOK + ") REFERENCES " + TABLE_BOOK + ")");
        db.execSQL("insert into " + TABLE_USER + " values ('rizqirahmansyah10@gmail.com','thehunter','Rizqi Rahmansyah');");
        db.execSQL("create table " + TABLE_DAFTAR_HOTEL + " (" + COL_ID_HOTEL + " TEXT PRIMARY KEY , " +
                COL_NAMA_HOTEL + " TEXT, " + COL_KOTA_HOTEL + " TEXT, " + COL_HARGA_HOTEL + " INTEGER, "
                + COL_RATING_HOTEL + " FLOAT)");
        db.execSQL("create table " + TABLE_BOOK_HOTEL + " (" + COL_ID_BOOK_HOTEL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT," + COL_NAMA_HOTEL + " TEXT, " + COL_DURASI_MALAM + " INTEGER, " +
                COL_JUMLAH_KAMAR + " INTEGER, " + COL_TANGGAL_HOTEL + " TEXT, " + COL_TOTAL_HARGA_HOTEL +
                " INTEGER, FOREIGN KEY(" + COL_USERNAME + ") REFERENCES " + TABLE_USER +
                ", FOREIGN KEY(" + COL_NAMA_HOTEL + ") REFERENCES " + TABLE_BOOK_HOTEL +")");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0101','Hotel Indonesia','Jakarta','2500000','5');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0201','Hilton Bandung','Bandung','2300000','5');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0301','Pesona Hotel Malioboro','Yogyakarta','1800000','5');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0401','Java Heritage Semarang','Semarang','1750000','5');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0501','Hotel Majapahit','Surabaya','1550000','5');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0102','Hotel AryaDuta','Jakarta','1500000','4');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0202','West Point Hotel','Bandung','1475000','4');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0302','Jambuluwuk Malioboro','Yogyakarta','1325000','4');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0402','Louis Kienne Simpang Lima','Semarang','1200000','4');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0502','Best Western Papilios','Surabaya','1130000','4');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0103','Ibis Jakarta Thamrin','Jakarta','945000','3');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0203','eL Hotel Royale','Bandung','750000','3');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0303','Indies Heritage','Yogyakarta','725000','3');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0403','Aston Semarang','Semarang','645000','3');");
        db.execSQL("insert into " + TABLE_DAFTAR_HOTEL + " values ('0503','Gunawangsa Manyar','Surabaya','500000','3');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public boolean Update(String nama, String email) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("UPDATE " + TABLE_USER + " SET " + COL_NAME + "=? WHERE " + COL_USERNAME + "=?", new String[]{nama, email});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

}