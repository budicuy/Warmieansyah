package myapp;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Pesanan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FDetailPesanan extends Application {
    @FXML
    private Label lblItem1, lblItem2, lblItem3, lblItem4, lblHarga1, lblHarga2, lblHarga3, lblHarga4, lblTotalHarga, lblOrder;

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
    
}