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
import java.sql.ResultSet;

    public class FLainnya extends Application {

        @FXML
        private Button btOrder, btAbout, btKeluar, btClear, btKembali;
        @FXML
        private Label lblItem1, lblItem2, lblItem3, lblItem4;
        @FXML
        private Label lblHarga1, lblHarga2, lblHarga3, lblHarga4, lblTotalHarga;
        @FXML
        private TextField txtNama, txtMeja;
        @FXML
        private ComboBox<String> cbMakanan1, cbMakanan2, cbMakanan3, cbMakanan4;
        @FXML
        private ComboBox<String> cbMinuman1, cbMinuman2, cbMinuman3, cbMinuman4;

        private Label[] lblHargaArray;
        private Label[] lblItemArray;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FLainnya.fxml"));
                primaryStage.setScene(new Scene(root));
                primaryStage.setTitle("Lainnya");
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

       @FXML
private void initialize() {
    lblHargaArray = new Label[] { lblHarga1, lblHarga2, lblHarga3, lblHarga4 };
    lblItemArray = new Label[] { lblItem1, lblItem2, lblItem3, lblItem4 };

    // Load makanan dan minuman dari database
    loadMakananToComboBox(cbMakanan1);
    loadMakananToComboBox(cbMakanan2);
    loadMakananToComboBox(cbMakanan3);
    loadMakananToComboBox(cbMakanan4);
    loadMinumanToComboBox(cbMinuman1);
    loadMinumanToComboBox(cbMinuman2);
    loadMinumanToComboBox(cbMinuman3);
    loadMinumanToComboBox(cbMinuman4);

    // Tambahkan listener untuk mengupdate harga otomatis
    addComboBoxListener(cbMakanan1, lblItemArray, lblHargaArray);
    addComboBoxListener(cbMakanan2, lblItemArray, lblHargaArray);
    addComboBoxListener(cbMakanan3, lblItemArray, lblHargaArray);
    addComboBoxListener(cbMakanan4, lblItemArray, lblHargaArray);
    addComboBoxListener(cbMinuman1, lblItemArray, lblHargaArray);
    addComboBoxListener(cbMinuman2, lblItemArray, lblHargaArray);
    addComboBoxListener(cbMinuman3, lblItemArray, lblHargaArray);
    addComboBoxListener(cbMinuman4, lblItemArray, lblHargaArray);
}

private void loadMakananToComboBox(ComboBox<String> comboBox) {
    try (Connection conn = Koneksi.configDB()) {
        String query = "SELECT menu_makanan FROM makanan";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                comboBox.getItems().add(rs.getString("menu_makanan"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void loadMinumanToComboBox(ComboBox<String> comboBox) {
    try (Connection conn = Koneksi.configDB()) {
        String query = "SELECT menu_minuman FROM minuman";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                comboBox.getItems().add(rs.getString("menu_minuman"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@SuppressWarnings("unused")
private void addComboBoxListener(ComboBox<String> comboBox, Label[] lblItemArray, Label[] lblHargaArray) {
    comboBox.setOnAction(event -> {
        String selectedItem = comboBox.getValue();
        if (selectedItem != null) {
            // Periksa apakah item sudah ada di dalam array Label
            for (Label lblItem : lblItemArray) {
                if (selectedItem.equals(lblItem.getText())) {
                    return; // Jangan tambahkan jika sudah ada
                }
            }

            // Temukan label kosong pertama untuk item dan harga
            for (int i = 0; i < lblItemArray.length; i++) {
                if (lblItemArray[i].getText().isEmpty()) {
                    lblItemArray[i].setText(selectedItem); // Set nama item
                    int harga = getHarga(selectedItem); // Ambil harga dari database
                    lblHargaArray[i].setText(String.valueOf(harga)); // Set harga
                    updateTotalHarga(); // Update total harga
                    return; // Keluar setelah memasukkan item pertama yang ditemukan
                }
            }

            // Jika semua label sudah terisi, jangan lakukan apa pun
        }
    });
}


private int getHarga(String item) {
    try (Connection conn = Koneksi.configDB()) {
        String query = "SELECT harga FROM makanan WHERE menu_makanan = ? UNION " +
                       "SELECT harga FROM minuman WHERE menu_minuman = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, item);
            stmt.setString(2, item);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0; // Harga default jika tidak ditemukan
}

private void updateTotalHarga() {
    int total = 0;
    for (Label lblHarga : lblHargaArray) {
        if (!lblHarga.getText().isEmpty()) {
            total += Integer.parseInt(lblHarga.getText());
        }
    }
    lblTotalHarga.setText(String.valueOf(total));
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
            cbMakanan1.setValue(null);
            cbMakanan2.setValue(null);
            cbMakanan3.setValue(null);
            cbMakanan4.setValue(null);
            cbMinuman1.setValue(null);
            cbMinuman2.setValue(null);
            cbMinuman3.setValue(null);
            cbMinuman4.setValue(null);
            lblTotalHarga.setText("");
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
        private void Kembali() {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FCostumer.fxml"));
                Stage stage = (Stage) btKeluar.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Menu");
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
    }

