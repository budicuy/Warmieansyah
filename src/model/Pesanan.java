package model;

public class Pesanan {

    private int id;
    private String nama;
    private int noMeja;
    private String tanggalPesanan;
    private String menuMakanan;
    private String menuMinuman;
    private String totalHarga;

    // Constructor
    public Pesanan(int id, String nama, int noMeja, String tanggalPesanan, String menuMakanan, String menuMinuman, String totalHarga) {
        this.id = id;
        this.nama = nama;
        this.noMeja = noMeja;
        this.tanggalPesanan = tanggalPesanan;
        this.menuMakanan = menuMakanan;
        this.menuMinuman = menuMinuman;
        this.totalHarga = totalHarga;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNoMeja() {
        return noMeja;
    }

    public void setNoMeja(int noMeja) {
        this.noMeja = noMeja;
    }

    public String getTanggalPesanan() {
        return tanggalPesanan;
    }

    public void setTanggalPesanan(String tanggalPesanan) {
        this.tanggalPesanan = tanggalPesanan;
    }

    public String getMenuMakanan() {
        return menuMakanan;
    }

    public void setMenuMakanan(String menuMakanan) {
        this.menuMakanan = menuMakanan;
    }

    public String getMenuMinuman() {
        return menuMinuman;
    }

    public void setMenuMinuman(String menuMinuman) {
        this.menuMinuman = menuMinuman;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }
}
