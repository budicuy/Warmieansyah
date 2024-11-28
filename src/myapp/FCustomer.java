package myapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class FCustomer extends Application {

    @FXML
    private Button btTambahMakanan1, btTambahMakanan2, btTambahMakanan3, btTambahMakanan4;
    @FXML
    private Button btTambahMakanan5, btTambahMakanan6, btTambahMinuman1, btTambahMinuman2;
    @FXML
    private Button btTambahMinuman3, btTambahMinuman4, btTambahMinuman5, btTambahMinuman6;
    @FXML
    private Button btAbout, btKeluar, btOrder;
    @FXML
    private Label lblItem1, lblItem2, lblItem3, lblItem4;
    @FXML
    private Label lblHarga1, lblHarga2, lblHarga3, lblHarga4; 
    @FXML
    private Label lblTotalHarga;
    @FXML
    private Button btClear;
    @FXML
    private TextField txtNama, txtMeja;
    private Label[] lblHargaArray;
    private Label[] lblItemArray;
    

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FCostumer.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Customer");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        // Inisialisasi array label harga
        lblHargaArray = new Label[]{lblHarga1, lblHarga2, lblHarga3, lblHarga4};
    lblItemArray = new Label[]{lblItem1, lblItem2, lblItem3, lblItem4};
    }

    @FXML
    private void About() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FAboutCostumer.fxml"));
            Stage stage = (Stage) btAbout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("About");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Keluar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FLogin.fxml"));
            Stage stage = (Stage) btKeluar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addItemAndPriceToNextAvailableLabel(String item, String harga) {
        // Mencari label item dan harga yang kosong
        for (int i = 0; i < lblItemArray.length; i++) {
            if (lblItemArray[i].getText().isEmpty()) {
                lblItemArray[i].setText(item);   // Menambahkan item ke label
                lblHargaArray[i].setText(harga);  // Menambahkan harga ke label
                calculateTotalHarga();            // Menghitung total harga setiap kali ada perubahan
                break;
            }
        }
    }
    
    // Fungsi untuk menghitung total harga
    private void calculateTotalHarga() {
        int totalHarga = 0;
        for (Label lblHarga : lblHargaArray) {
            if (!lblHarga.getText().isEmpty()) {
                totalHarga += Integer.parseInt(lblHarga.getText());
            }
        }
        lblTotalHarga.setText(String.valueOf(totalHarga)); // Tampilkan total harga
    }
    @FXML
private void Clear() {

    txtMeja.setText("");
    txtNama.setText("");
    
    lblItem1.setText("");
    lblItem2.setText("");
    lblItem3.setText("");
    lblItem4.setText("");

    lblHarga1.setText("");
    lblHarga2.setText("");
    lblHarga3.setText("");
    lblHarga4.setText("");

    lblTotalHarga.setText("0");
}

    
    // Harga Tetap untuk Makanan dan Minuman
    @FXML
    private void TambahMakanan1() {
        addItemAndPriceToNextAvailableLabel("Makanan 1", "5000");
    }
    
    @FXML
    private void TambahMakanan2() {
        addItemAndPriceToNextAvailableLabel("Makanan 2", "5000");
    }
    
    @FXML
    private void TambahMakanan3() {
        addItemAndPriceToNextAvailableLabel("Makanan 3", "5500");
    }
    
    @FXML
    private void TambahMakanan4() {
        addItemAndPriceToNextAvailableLabel("Makanan 4", "5500");
    }
    
    @FXML
    private void TambahMakanan5() {
        addItemAndPriceToNextAvailableLabel("Makanan 5", "7000");
    }
    
    @FXML
    private void TambahMakanan6() {
        addItemAndPriceToNextAvailableLabel("Makanan 6", "7000");
    }
    
    @FXML
    private void TambahMinuman1() {
        addItemAndPriceToNextAvailableLabel("Minuman 1", "6000");
    }
    
    @FXML
    private void TambahMinuman2() {
        addItemAndPriceToNextAvailableLabel("Minuman 2", "5000");
    }
    
    @FXML
    private void TambahMinuman3() {
        addItemAndPriceToNextAvailableLabel("Minuman 3", "4000");
    }
    
    @FXML
    private void TambahMinuman4() {
        addItemAndPriceToNextAvailableLabel("Minuman 4", "6000");
    }
    
    @FXML
    private void TambahMinuman5() {
        addItemAndPriceToNextAvailableLabel("Minuman 5", "6000");
    }
    
    @FXML
    private void TambahMinuman6() {
        addItemAndPriceToNextAvailableLabel("Minuman 6", "7000");
    }

    @FXML
    private void Order() {
        // Mengambil data dari label item dan harga
        String[] items = {lblItem1.getText(), lblItem2.getText(), lblItem3.getText(), lblItem4.getText()};
        String[] prices = {lblHarga1.getText(), lblHarga2.getText(), lblHarga3.getText(), lblHarga4.getText()};
    
        // Ambil data tambahan dari input pengguna (misalnya menggunakan TextField)
        String nama = "Nama Pelanggan";  // Ganti dengan data dari TextField
        int noMeja = 5; // Ganti dengan data dari input pengguna (misalnya TextField)
        String tanggalPesanan = java.time.LocalDate.now().toString(); // Mengambil tanggal hari ini
    
        // Gabungkan item makanan dan minuman menjadi satu string
        StringBuilder menuMakanan = new StringBuilder();
        StringBuilder menuMinuman = new StringBuilder();
        
        for (int i = 0; i < items.length; i++) {
            if (!items[i].isEmpty() && !prices[i].isEmpty()) {
                // Pisahkan makanan dan minuman sesuai kebutuhan
                if (items[i].contains("Makanan")) {
                    if (menuMakanan.length() > 0) menuMakanan.append(", ");
                    menuMakanan.append(items[i]);
                } else if (items[i].contains("Minuman")) {
                    if (menuMinuman.length() > 0) menuMinuman.append(", ");
                    menuMinuman.append(items[i]);
                }
            }
        }
    
        // Menghitung total harga
        int totalHarga = 0;
        for (String harga : prices) {
            if (!harga.isEmpty()) {
                totalHarga += Integer.parseInt(harga);
            }
        }
    
        // Koneksi ke database menggunakan Koneksi.java
        try (Connection conn = Koneksi.configDB()) {
            // SQL untuk menyimpan data pesanan
            String sql = "INSERT INTO pesanan (nama, no_meja, tanggal_pesan, menu_makanan, menu_minuman, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nama);  // Nama pelanggan
                stmt.setInt(2, noMeja);   // Nomor meja
                stmt.setString(3, tanggalPesanan);  // Tanggal pesanan (perbaikan di sini)
                stmt.setString(4, menuMakanan.toString());  // Menu makanan yang dipilih
                stmt.setString(5, menuMinuman.toString());  // Menu minuman yang dipilih
                stmt.setInt(6, totalHarga);  // Total harga
    
                stmt.executeUpdate();  // Menjalankan query insert
            }
    
            // Setelah berhasil, tampilkan konfirmasi ke pengguna
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pesanan Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Pesanan Anda telah diterima.");
            alert.showAndWait();
            
            // Setelah berhasil, Anda dapat membersihkan atau mengatur ulang tampilan pesanan jika diperlukan
        } catch (Exception e) {
            // Menangani error koneksi database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kesalahan");
            alert.setHeaderText("Terjadi kesalahan saat memproses pesanan.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    

}
