package com.example.pasardesatanjung;

public class Model {
    public String id,nama,deskripsi,gambar;
    public int harga;

    public Model() {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
        this.gambar=gambar;
       // this.uri = uri;
    }

    public String getId() {
        return id;
    }

//    public String getSatuan() {
//        return satuan;
//    }
//
//    public void setSatuan(String satuan) {
//        this.satuan = satuan;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public String getnm_brg() {
        return nama;
    }

    public void setnm_brg(String nm_brg) {
        this.nama = nm_brg;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getgambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
