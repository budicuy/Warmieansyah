package model;

public class Pesanan {

    private int id;
    private String nama;
    private String tanggal_pesanan;
    private String menu_makanan;
    private String menu_minuman;
    private String total_harga;

    // Constructor
    public Pesanan(int id, String nama, String tanggal_pesanan, String menu_makanan, String menu_minuman,
            String total_harga) {
        this.id = id;
        this.nama = nama;
        this.tanggal_pesanan = tanggal_pesanan;
        this.menu_makanan = menu_makanan;
        this.menu_minuman = menu_minuman;
        this.total_harga = total_harga;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_pesanan() {
        return tanggal_pesanan;
    }

    public void setTanggal_pesanan(String tanggal_pesanan) {
        this.tanggal_pesanan = tanggal_pesanan;
    }

    public String getMenu_makanan() {
        return menu_makanan;
    }

    public void setMenu_makanan(String menu_makanan) {
        this.menu_makanan = menu_makanan;
    }

    public String getMenu_minuman() {
        return menu_minuman;
    }

    public void setMenu_minuman(String menu_minuman) {
        this.menu_minuman = menu_minuman;
    }

    public String getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(String total_harga) {
        this.total_harga = total_harga;
    }
}
