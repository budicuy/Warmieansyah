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
    private Button btAbout, btKeluar, btOrder, btLainnya;
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
        // Inisialisasi array label harga dan item
        lblHargaArray = new Label[] { lblHarga1, lblHarga2, lblHarga3, lblHarga4 };
        lblItemArray = new Label[] { lblItem1, lblItem2, lblItem3, lblItem4 };
    }

    private void MakanandanMinuman(String item, String harga) {
        for (int i = 0; i < lblItemArray.length; i++) {
            if (lblItemArray[i].getText().isEmpty()) {
                lblItemArray[i].setText(item);
                lblHargaArray[i].setText(harga);
                TotalHarga();
                break;
            }
        }
    }

    private void TotalHarga() {
        int totalHarga = 0;
        for (Label lblHarga : lblHargaArray) {
            if (!lblHarga.getText().isEmpty()) {
                totalHarga += Integer.parseInt(lblHarga.getText());
            }
        }
        lblTotalHarga.setText(String.valueOf(totalHarga));
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

    // Fungsi untuk menambah makanan dan minuman
    @FXML
    private void TambahMakanan1() {
        MakanandanMinuman("Mie Seddap", "5000");
    }

    @FXML
    private void TambahMakanan2() {
        MakanandanMinuman("Mie Indomie", "5000");
    }

    @FXML
    private void TambahMakanan3() {
        MakanandanMinuman("Mie Indomie Soto Banjar", "5500");
    }

    @FXML
    private void TambahMakanan4() {
        MakanandanMinuman("Mie Seddap Soto", "5500");
    }

    @FXML
    private void TambahMakanan5() {
        MakanandanMinuman("Mie Sukses Ayam Kecap", "7000");
    }

    @FXML
    private void TambahMakanan6() {
        MakanandanMinuman("Mie Sarimi Ayam Kremes", "7000");
    }

    @FXML
    private void TambahMinuman1() {
        MakanandanMinuman("Cappucino", "6000");
    }

    @FXML
    private void TambahMinuman2() {
        MakanandanMinuman("Americano", "5000");
    }

    @FXML
    private void TambahMinuman3() {
        MakanandanMinuman("Ice Tea", "4000");
    }

    @FXML
    private void TambahMinuman4() {
        MakanandanMinuman("Lemon Tea", "6000");
    }

    @FXML
    private void TambahMinuman5() {
        MakanandanMinuman("Red Syrup", "6000");
    }

    @FXML
    private void TambahMinuman6() {
        MakanandanMinuman("Strawberry Milk", "7000");
    }

    @FXML
    private void Order() {
        String namaPesanan = txtNama.getText();
        int nomorMeja = 0;
        try {
            nomorMeja = Integer.parseInt(txtMeja.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kesalahan");
            alert.setHeaderText("Nomor meja tidak valid");
            alert.setContentText("Silakan masukkan nomor meja yang valid.");
            alert.showAndWait();
            return;
        }

        // Memastikan nama dan nomor meja sudah diisi
        if (namaPesanan == null || namaPesanan.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kesalahan");
            alert.setHeaderText("Nama belum diisi");
            alert.setContentText("Silakan masukkan nama pelanggan.");
            alert.showAndWait();
            return;
        }

        if (nomorMeja == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Kesalahan");
            alert.setHeaderText("Nomor Meja belum diisi");
            alert.setContentText("Silakan masukkan nomor meja.");
            alert.showAndWait();
            return;
        }

        // Mengambil data dari label item dan harga
        String[] items = { lblItem1.getText(), lblItem2.getText(), lblItem3.getText(), lblItem4.getText() };
        String[] prices = { lblHarga1.getText(), lblHarga2.getText(), lblHarga3.getText(), lblHarga4.getText() };

        String tanggalPesanan = java.time.LocalDate.now().toString();

        StringBuilder menuMakanan = new StringBuilder();
        StringBuilder menuMinuman = new StringBuilder();

        for (int i = 0; i < items.length; i++) {
            if (!items[i].isEmpty() && !prices[i].isEmpty()) {
                if (items[i].contains("Mie")) {
                    if (menuMakanan.length() > 0)
                        menuMakanan.append(", ");
                    menuMakanan.append(items[i]);
                } else {
                    if (menuMinuman.length() > 0)
                        menuMinuman.append(", ");
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

        try (Connection conn = Koneksi.configDB()) {
            String sql = "INSERT INTO pesanan (nama, no_meja, tanggal_pesan, menu_makanan, menu_minuman, total_harga) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, namaPesanan);
                stmt.setInt(2, nomorMeja);
                stmt.setString(3, tanggalPesanan);
                stmt.setString(4, menuMakanan.toString());
                stmt.setString(5, menuMinuman.toString());
                stmt.setInt(6, totalHarga);
                stmt.executeUpdate();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pesanan Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Pesanan Anda telah diterima.");
            alert.showAndWait();
            Clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kesalahan");
            alert.setHeaderText("Terjadi kesalahan saat memproses pesanan.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
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

    @FXML
    private void Lainnya() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FLainnya.fxml"));
            Stage stage = (Stage) btLainnya.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Lainnya");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
