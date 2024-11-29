package model;

public class Menu_Makanan {
    private int id;
    private String menu_makanan;
    private String harga;

    public Menu_Makanan(int id, String menu_makanan, String harga) {
        this.id = id;
        this.menu_makanan = menu_makanan;
        this.harga = harga;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu_makanan() {
        return menu_makanan;
    }

    public void setMenu_makanan(String menu_makanan) {
        this.menu_makanan = menu_makanan;
    }

    public String getHarga() {
        return harga; 
    }

    public void setHarga(String harga) {
        this.harga = harga; 
    }
}
