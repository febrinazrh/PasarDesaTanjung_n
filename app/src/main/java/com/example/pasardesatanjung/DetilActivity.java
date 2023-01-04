package com.example.pasardesatanjung;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class DetilActivity extends AppCompatActivity {

    public TextView txtjudul,txtdeskripsi,txtharga,txtberat;
    public NetworkImageView imgvolley ;

    ImageLoader imageLoader;
    Context context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);

        txtjudul = (TextView) findViewById(R.id.judul);
        txtdeskripsi = (TextView) findViewById(R.id.isi);
        txtharga = findViewById(R.id.harga);
        txtberat = findViewById(R.id.berat);
        imgvolley = (NetworkImageView) findViewById(R.id.imgvolley2);

        Bundle bundle = getIntent().getBundleExtra("barang");
        String judul = bundle.getString("nama");
        String deskripsi = bundle.getString("deskripsi");
        String gambar = bundle.getString("gambar");
        String harga = String.valueOf(bundle.getInt("harga"));
        String berat = bundle.getString("berat");


        imageLoader = ImageAdapter.getInstance(context).getImageLoader();
        imageLoader.get(gambar, ImageLoader.getImageListener(
                        imgvolley,
                        R.mipmap.ic_launcher,
                        android.R.drawable.ic_dialog_alert
                )
        );

        txtjudul.setText(judul);
        txtdeskripsi.setText(deskripsi);
        txtberat.setText("Berat bersih : "+berat);
        txtharga.setText("Dapat dibeli dengan hanya Rp."+harga);
        imgvolley.setImageUrl(gambar,imageLoader);
    }
}