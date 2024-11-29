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

public class FTambahMinuman extends Application {
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
            Parent root = FXMLLoader.load(getClass().getResource("FTambahMinuman.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Tambah Minuman");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

}
    }
    @FXML
    private void tambahMinuman() {
        String menuMinuman = txtNama.getText();
        String harga = txtHarga.getText();
        String query = "INSERT INTO minuman (menu_minuman, harga) VALUES ('" + menuMinuman + "', '" + harga + "')";
        try {
            Connection conn = Config.configDB();
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
            Parent root = FXMLLoader.load(getClass().getResource("FMinuman.fxml"));
            Stage stage = (Stage) btKeluar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Minuman");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
