package myapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Pesanan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FDetailPesanan extends Application {

    private static Pesanan Pesan;

    public static void Pesan(Pesanan pesanan) {
        Pesan = pesanan;
    }

    @FXML
    private Label lblNama, lblTanggal, lblNoMeja, lblItem1, lblItem2, lblItem3, lblItem4, lblHarga1, lblHarga2,
            lblHarga3, lblHarga4, lblTotalHarga, lblOrder;

    @FXML
    private Button btKeluar;

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
        lblOrder.setText("" + Pesan.getId());
        loadPesananDetails(Pesan.getId());
    }

    private String getHarga(String item) {
        switch (item) {
            case "Mie Seddap":
                return "5000";
            case "Mie Indomie":
                return "5000";
            case "Mie Indomie Soto Banjar":
                return "5500";
            case "Mie Seddap Soto":
                return "5500";
            case "Mie Sukses Ayam Kecap":
                return "7000";
            case "Mie Sarimi Ayam Kremes":
                return "7000";
            case "Cappucino":
                return "6000";
            case "Americano":
                return "5000";
            case "Ice Tea":
                return "4000";
            case "Lemon Tea":
                return "6000";
            case "Red Syrup":
                return "6000";
            case "Strawberry Milk":
                return "7000";
            default:
                return "0"; 
        }
    }

    private void loadPesananDetails(int pesananId) {
        String sql = "SELECT * FROM pesanan WHERE id = ?";
        try (Connection conn = Config.configDB();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pesananId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lblNama.setText(rs.getString("nama"));
                lblTanggal.setText(rs.getString("tanggal_pesan"));
                lblNoMeja.setText(String.valueOf(rs.getInt("no_meja")));

                // Pisahkan makanan dan minuman
                String[] makanan = rs.getString("menu_makanan").split(",");
                String[] minuman = rs.getString("menu_minuman").split(",");

                // Tampilkan makanan dan harganya di label
                lblItem1.setText(makanan.length > 0 ? makanan[0].trim() : "");
                lblHarga1.setText(makanan.length > 0 ? getHarga(makanan[0].trim()) : "");

                lblItem2.setText(makanan.length > 1 ? makanan[1].trim() : "");
                lblHarga2.setText(makanan.length > 1 ? getHarga(makanan[1].trim()) : "");

                // Tampilkan minuman dan harganya di label
                lblItem3.setText(minuman.length > 0 ? minuman[0].trim() : "");
                lblHarga3.setText(minuman.length > 0 ? getHarga(minuman[0].trim()) : "");

                lblItem4.setText(minuman.length > 1 ? minuman[1].trim() : "");
                lblHarga4.setText(minuman.length > 1 ? getHarga(minuman[1].trim()) : "");

                // Total harga
                lblTotalHarga.setText(rs.getString("total_harga"));
                lblOrder.setText(" " + pesananId);
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
