package myapp;


import java.sql.Connection;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Statement;

public class FTambahMakanan extends Application {
    @FXML
    private Button btKeluar, btTambah, btClear;

    @FXML
    private TextField txtNama, txtHarga;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FTambahMakanan.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Tambah Makanan");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

}
    }
    @FXML
    private void tambahMakanan() {
        String menuMakanan = txtNama.getText();
        String harga = txtHarga.getText();
        String query = "INSERT INTO makanan (menu_makanan, harga) VALUES ('" + menuMakanan + "', '" + harga + "')";
        try {
            Connection conn = Koneksi.configDB();
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan");
        }
        clear();
    }

    @FXML
    private void clear() {
        txtNama.setText("");
        txtHarga.setText("");
    }


    @FXML
    private void keluar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FMakanan.fxml"));
            Stage stage = (Stage) btKeluar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Makanan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
