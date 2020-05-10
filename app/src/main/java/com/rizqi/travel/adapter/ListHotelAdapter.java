package com.rizqi.travel.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rizqi.travel.R;
import com.rizqi.travel.model.ListHotelModel;

import java.util.ArrayList;

public class ListHotelAdapter extends ArrayAdapter<ListHotelModel> {

    public ListHotelAdapter(Activity context, ArrayList<ListHotelModel> notification) {
        super(context, 0, notification);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_hotel, parent, false);
        }

        ListHotelModel current = getItem(position);

        TextView idHotel = listItemView.findViewById(R.id.id_hotel);
        idHotel.setText(current.getIdHotel());

        TextView namaHotel = listItemView.findViewById(R.id.namahotel);
        namaHotel.setText(current.getNamaHotel());

        TextView deskripsi = listItemView.findViewById(R.id.deskripsi);
        deskripsi.setText(current.getDeskripsi());

        TextView tvHarga = listItemView.findViewById(R.id.tv_harga);
        tvHarga.setText("Harga / Kamar");

        TextView harga = listItemView.findViewById(R.id.hargahotel);
        harga.setText("Rp. " + current.getHarga());

        RatingBar ratingBar = listItemView.findViewById(R.id.ratingBar);
        ratingBar.setRating(current.getRating());

        ImageView imageIcon = listItemView.findViewById(R.id.gambarhotel);

        if (current.hasImage()) {
            imageIcon.setImageResource(current.getImageResourceId());
            imageIcon.setVisibility(View.VISIBLE);
        } else {
            imageIcon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}