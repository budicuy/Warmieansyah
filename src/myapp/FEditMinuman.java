package myapp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Menu_Minuman;


public class FEditMinuman extends Application {
    
    private static Menu_Minuman Minum;

    public static void Minuman(Menu_Minuman minuman) {
        Minum = minuman;
    }

    @FXML
    private Button btSimpan, btClear, btKeluar;

    @FXML
    private TextField txtNama, txtHarga;


    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FEditMinuman.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Edit Minuman");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
       MinumanTampil(Minum.getId());
    }

    private void MinumanTampil(int id) {
        String sql = "Select * from minuman where id = ?";    
        try (Connection conn = Config.configDB();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                txtNama.setText(rs.getString("menu_minuman"));
                txtHarga.setText(rs.getString("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btSimpan() {
        String sql = "UPDATE minuman SET menu_minuman = ?, harga = ? WHERE id = ?";
        try (Connection conn = Config.configDB();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, txtNama.getText());
            stmt.setString(2, txtHarga.getText());
            stmt.setInt(3, Minum.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            Minum.setMenu_minuman(txtNama.getText());
            Minum.setHarga(txtHarga.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btClear() {
        txtNama.clear();
        txtHarga.clear();
    }

    @FXML
    private void btKeluar() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FMinuman.fxml"));
            Stage stage = (Stage) btKeluar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Minuman");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}