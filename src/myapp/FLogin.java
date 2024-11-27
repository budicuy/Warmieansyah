package myapp;

import java.io.IOException;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FLogin {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfName;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private void login() {

        // ambil username dan password dari textfield
        String username = tfName.getText();
        String password = tfPassword.getText();
        String query = "SELECT * FROM user WHERE name = '" + username + "' AND password = '" + password + "'";

        try {
            Connection conn = Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(query);

            // jika data ditemukan
            if (res.next()) {
                // cek role user
                String role = res.getString("role");
                if (role.equals("admin")) {
                    // tampilkan dialog login berhasil
                    JOptionPane.showMessageDialog(null, "Login berhasil sebagai admin");
                    // print info login berhasil
                    System.out.println("Login berhasil sebagai admin");

                    // buka scene admin
                    FAdmin admin = new FAdmin();
                    admin.start(new Stage());
                    // tutup scene login
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();
                } else {
                    // tampilkan dialog login berhasil
                    JOptionPane.showMessageDialog(null, "Login berhasil sebagai user");
                    // print info login berhasil
                    System.out.println("Login berhasil sebagai user");

                    // buka scene user
                    UserController user = new UserController();
                    user.start(new Stage());
                    // tutup scene login
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.close();
                }
            } else {
                // tampilkan dialog login gagal
                JOptionPane.showMessageDialog(null, "Login gagal");
                // print error
                System.out.println("Login gagal");
            }

        } catch (Exception e) {
            // tampilkan JOptionPane error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            // print error
            System.out.println("Error: " + e.getMessage());

        }

    }

    @FXML
    void btnLogin(ActionEvent event) {
        // panggil method login
        login();
    }

    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FLogin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
