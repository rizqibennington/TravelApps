package com.rizqi.travel.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rizqi.travel.R;
import com.rizqi.travel.model.HistoryHotelModel;

import java.util.ArrayList;

public class HistoryHotelAdapter extends ArrayAdapter<HistoryHotelModel> {

    public HistoryHotelAdapter(Activity context, ArrayList<HistoryHotelModel> notification) {
        super(context, 0, notification);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_history_hotel, parent, false);
        }

        HistoryHotelModel current = getItem(position);

        TextView idBook = listItemView.findViewById(R.id.id_booking);
        idBook.setText("ID : " + current.getIdBook());

        TextView tanggal = listItemView.findViewById(R.id.tanggal);
        tanggal.setText(current.getTanggal());

        TextView riwayat = listItemView.findViewById(R.id.deskripsi);
        riwayat.setText(current.getDeskripsi());

        TextView tvTotal = listItemView.findViewById(R.id.tv_total);
        tvTotal.setText("Total :");

        TextView total = listItemView.findViewById(R.id.total);
        total.setText("Rp. " + current.getTotal());

        ImageView imageIcon = listItemView.findViewById(R.id.image);

        if (current.hasImage()) {
            imageIcon.setImageResource(current.getImageResourceId());
            imageIcon.setVisibility(View.VISIBLE);
        } else {
            imageIcon.setVisibility(View.GONE);
        }

        return listItemView;
    }
}