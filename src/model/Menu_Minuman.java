package model;

public class Menu_Minuman {
    private int id;
    private String menu_minuman;
    private String harga;

   
    public Menu_Minuman(int id, String menu_minuman, String harga) {
        this.id = id;
        this.menu_minuman = menu_minuman;
        this.harga = harga;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu_minuman() {
        return menu_minuman;
    }

    public void setMenu_minuman(String menu_minuman) {
        this.menu_minuman = menu_minuman;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
