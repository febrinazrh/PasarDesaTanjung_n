package com.example.pasardesatanjung;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    List<Model> dataAdapters;
    ImageLoader imageLoader;
    int count = 0;

    public RecyclerViewAdapter(List<Model> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_data, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        Model dataAdapterOBJ =  dataAdapters.get(position);

        imageLoader = ImageAdapter.getInstance(context).getImageLoader();

        imageLoader.get(dataAdapterOBJ.getgambar(),
                ImageLoader.getImageListener(
                        Viewholder.imgvolley,
                        R.mipmap.ic_launcher,
                        android.R.drawable.ic_dialog_alert
                )
        );

        String deskrip = dataAdapterOBJ.getDeskripsi();
        Viewholder.imgvolley.setImageUrl(dataAdapterOBJ.getgambar(), imageLoader);
        Viewholder.txtjudul.setText(dataAdapterOBJ.getnm_brg());
        Viewholder.txtHarga.setText("Rp."+String.valueOf(dataAdapterOBJ.getHarga()));

        Viewholder.txtjudul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("nama",dataAdapterOBJ.getnm_brg());
                bundle.putString("deskripsi",deskrip);
                bundle.putString("berat","500 gram");
                bundle.putInt("harga",dataAdapterOBJ.getHarga());
                bundle.putString("gambar",dataAdapterOBJ.getgambar());
                Intent intent = new Intent(context,DetilActivity.class);
                intent.putExtra("barang",bundle);
                context.startActivity(intent);
            }
        });

       /* Viewholder.imgvolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                int harga = count*dataAdapterOBJ.getHarga();
                Bundle bundle = new Bundle();
                bundle.putInt("price",harga);
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("tambah",1);
                intent.putExtra("bayar",bundle);
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtjudul,txtHarga;
        public NetworkImageView imgvolley ;

        public ViewHolder(View itemView) {

            super(itemView);

            txtjudul = (TextView) itemView.findViewById(R.id.txtjudul) ;
            txtHarga = (TextView) itemView.findViewById(R.id.txtharga) ;
            imgvolley = (NetworkImageView) itemView.findViewById(R.id.imgvolley) ;
        }
    }
}
