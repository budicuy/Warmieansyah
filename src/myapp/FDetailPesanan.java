package myapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Pesanan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FDetailPesanan extends Application {
    private static Pesanan selectedPesanan; // Menyimpan data pesanan yang dipilih dari FAdmin

    // Metode untuk menerima data dari layar sebelumnya
    public static void setSelectedPesanan(Pesanan pesanan) {
        selectedPesanan = pesanan;
    }

    @FXML
    private Label lblItem1, lblItem2, lblItem3, lblItem4, lblHarga1, lblHarga2, lblHarga3, lblHarga4, lblTotalHarga, lblOrder;

    @FXML
    private Button btKeluar;

    @FXML
    private TextField txtNama, txtMeja, txtTanggal;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FDetailPesanan.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Detail Pesanan");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Ambil data dari database berdasarkan ID pesanan yang dipilih
        if (selectedPesanan != null) {
            // Menampilkan data pesanan
            loadPesananFromDatabase(selectedPesanan.getId());
        } else {
            lblOrder.setText("Data pesanan tidak ditemukan.");
        }
    }

    // Method untuk mengambil data pesanan dari database
    private void loadPesananFromDatabase(int pesananId) {
        String sql = "SELECT * FROM pesanan WHERE id = ?";
        try (Connection conn = Koneksi.configDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pesananId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Ambil data item makanan dan minuman
                String menuMakanan = rs.getString("menu_makanan");
                String menuMinuman = rs.getString("menu_minuman");
                int totalHarga = rs.getInt("total_harga");

                // Pisahkan item makanan dan minuman
                List<String> makananList = new ArrayList<>();
                List<String> minumanList = new ArrayList<>();

                // Menambahkan item makanan dan minuman ke dalam list masing-masing
                if (menuMakanan != null && !menuMakanan.isEmpty()) {
                    String[] makananItems = menuMakanan.split(",");
                    for (String makanan : makananItems) {
                        makananList.add(makanan.trim());
                    }
                }

                if (menuMinuman != null && !menuMinuman.isEmpty()) {
                    String[] minumanItems = menuMinuman.split(",");
                    for (String minuman : minumanItems) {
                        minumanList.add(minuman.trim());
                    }
                }

                // Tampilkan item makanan dan minuman ke label
                lblItem1.setText(String.join(", ", makananList));
                lblItem2.setText(String.join(", ", minumanList));

                // Update harga dan total
                lblHarga1.setText(String.valueOf(totalHarga));
                lblHarga2.setText(String.valueOf(totalHarga));
                lblTotalHarga.setText(String.valueOf(totalHarga));
                lblOrder.setText("Pesanan ID: " + pesananId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Keluar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FAdmin.fxml"));
            Stage stage = (Stage) btKeluar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
