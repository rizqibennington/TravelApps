package com.rizqi.travel.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.rizqi.travel.R;
import com.rizqi.travel.adapter.ListHotelAdapter;
import com.rizqi.travel.database.DatabaseHelper;
import com.rizqi.travel.model.ListHotelModel;
import com.rizqi.travel.session.SessionManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ListHotelActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    SessionManager session;
    Spinner spinKota, spinKamar, spinDurasi;
    String id_hotel = "", kota, harga, namahotel, deskripsi, sKota, sKamar, sDurasi, sTanggal;
    Float rating;
    int jumlahKamar, durasiMalam, totalharga;
    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    String email;
    TextView tvNotFound;
    Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);

        //deklarasi isi spinner
        final String[] kota = {"Semua", "Jakarta", "Bandung", "Semarang", "Yogyakarta", "Surabaya"};
        final String[] kamar = {"1", "2", "3", "4", "5", "6"};
        final String[] durasi = {"1", "2", "3", "4", "5", "6"};

        //deklarasiin menu spinnernya dari xml
        spinKota = findViewById(R.id.kota);
        spinKamar = findViewById(R.id.kamar);
        spinDurasi = findViewById(R.id.durasi);

        //kasih tau ambil data dari mana spinnernya
        ArrayAdapter<CharSequence> adapterKamar = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, kamar);
        adapterKamar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinKamar.setAdapter(adapterKamar);
        ArrayAdapter<CharSequence> adapterKota = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, kota);
        adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinKota.setAdapter(adapterKota);
        ArrayAdapter<CharSequence> adapterDurasi = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, durasi);
        adapterDurasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDurasi.setAdapter(adapterDurasi);

        //ambil set isian spinner sesuai item yg dipilih
        spinKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sKota = parent.getItemAtPosition(position).toString();
                refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinKamar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sKamar = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinDurasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sDurasi = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //deklarasi plus ambil db dan sesi
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();
        tvNotFound = findViewById(R.id.noListHotel);
        session = new SessionManager(getApplicationContext());
        etTanggal = findViewById(R.id.tanggal_booking);
        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.requestFocus();
        //panggil fungsi datetime buat setting kalender dibawah
        setDateTimeField();
        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);
        //refresh halaman listnya
        refreshList();
        setupToolbar();
    }

    //nyalain toolbar
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbListHotel);
        toolbar.setTitle("Daftar Hotel");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //isi dari refreshlist
    public void refreshList() {
        final ArrayList<ListHotelModel> hasil = new ArrayList<>();
        //ambil db
        if (sKota=="Semua")
        {
            cursor = db.rawQuery("SELECT * FROM TB_DAFTAR_HOTEL", null);
        }
        else if(sKota==null){
            cursor = db.rawQuery("SELECT * FROM TB_DAFTAR_HOTEL", null);
        }
        else{
            cursor = db.rawQuery("SELECT * FROM TB_DAFTAR_HOTEL WHERE kota_hotel='" + sKota + "'", null);
        }
        //jelasin data yang diambil db sesuai kolom
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            id_hotel = cursor.getString(0);
            namahotel = cursor.getString(1);
            kota = cursor.getString(2);
            harga = cursor.getString(3);
            rating = cursor.getFloat(4);
            deskripsi = "Hotel enak dan nyaman di " + kota + ".";
            hasil.add(new ListHotelModel(id_hotel, namahotel, deskripsi, harga, rating, R.drawable.book_hotel));
        }

        //deklarasi listview
        ListView listHotel = findViewById(R.id.list_hotel);
        ListHotelAdapter arrayAdapter = new ListHotelAdapter(this, hasil);
        listHotel.setAdapter(arrayAdapter);

        //masuk proses pas item hotel di klik
        listHotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String dtIdHotel = hasil.get(i).getIdHotel();
                final String dtNamaHotel = hasil.get(i).getNamaHotel();
                final int dtHargaHotel = Integer.parseInt(hasil.get(i).getHarga());
                jumlahKamar = Integer.parseInt(sKamar);
                durasiMalam = Integer.parseInt(sDurasi);
                totalharga = dtHargaHotel * durasiMalam * jumlahKamar;
                if (sTanggal == null){
                    Toast.makeText(ListHotelActivity.this, "Pilih Tanggalnya Dulu!", Toast.LENGTH_LONG).show();
                }
                else{
                AlertDialog.Builder builder = new AlertDialog.Builder(ListHotelActivity.this);
                builder.setTitle(dtNamaHotel+" "+sDurasi+" Malam "+jumlahKamar+" Kamar Total : Rp."+totalharga);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            db.execSQL("INSERT INTO TB_BOOK_HOTEL (username, nama_hotel, durasi_hotel, jumlah_kamar, tanggal_hotel, total_harga_hotel) " +
                                    "VALUES ('" + email + "','" + dtNamaHotel + "','" + durasiMalam + "','" + jumlahKamar
                                    +  "','" + sTanggal + "','" + totalharga + "');");
                                    Toast.makeText(ListHotelActivity.this, "Pesanan berhasil", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        catch (Exception e) {
                            Toast.makeText(ListHotelActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Tidak", null);
                builder.create().show();
            }}
        });

        if (id_hotel.equals("")) {
            tvNotFound.setVisibility(View.VISIBLE);
            listHotel.setVisibility(View.GONE);
        } else {
            tvNotFound.setVisibility(View.GONE);
            listHotel.setVisibility(View.VISIBLE);
        }

    }

    //setting datetime
    private void setDateTimeField() {
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal.show();
            }
        });


        dpTanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                sTanggal = dayOfMonth + " " + bulan[monthOfYear] + " " + year;
                etTanggal.setText(sTanggal);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

}
