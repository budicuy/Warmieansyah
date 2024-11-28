package myapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Menu_Makanan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FMakanan extends Application {

    @FXML
    private Button btMinuman;

    @FXML
    private Button btTambah;

    @FXML
    private Button btDash;

    @FXML
    private Button btAbout;

    @FXML
    private Button btKeluar;

    @FXML
    private TableView<Menu_Makanan> tblMakanan;

    @FXML
    private TableColumn<Menu_Makanan, String> tblId, tblMenuMakanan, tblHarga;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FMakanan.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Menu Makanan");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void initTable() {
        // Set the cell value factories for the TableView columns
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblMenuMakanan.setCellValueFactory(new PropertyValueFactory<>("menu_makanan"));
        tblHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        // Editable columns (TableCell)
        tblMenuMakanan.setCellFactory(TextFieldTableCell.forTableColumn());
        tblMenuMakanan.setOnEditCommit(event -> {
            Menu_Makanan makanan = event.getRowValue();
            makanan.setMenu_makanan(event.getNewValue());
            updateMakanan(makanan); // Update in DB or model
        });

        tblHarga.setCellFactory(TextFieldTableCell.forTableColumn());
        tblHarga.setOnEditCommit(event -> {
            Menu_Makanan makanan = event.getRowValue();
            makanan.setHarga(event.getNewValue());
            updateMakanan(makanan); // Update in DB or model
        });

        // Column for Edit and Delete buttons
        TableColumn<Menu_Makanan, Void> actionColumn = new TableColumn<>("Aksi");

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btnDelete = new Button("Hapus");
            private final HBox hbox = new HBox(10, btnDelete);

            {
                btnDelete.setOnAction(event -> handleDelete(getTableRow().getItem()));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hbox);
                }
            }
        });

        tblMakanan.getColumns().add(actionColumn);
    }

    private void loadDataMakanan() {
        ObservableList<Menu_Makanan> makananList = FXCollections.observableArrayList();
        try (Connection conn = Koneksi.configDB(); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM makanan");
            while (rs.next()) {
                makananList.add(new Menu_Makanan(
                        rs.getInt("id"),
                        rs.getString("menu_makanan"),
                        rs.getString("harga")));
            }
            tblMakanan.setItems(makananList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Aksi untuk mengupdate makanan setelah diedit
    private void updateMakanan(Menu_Makanan makanan) {
        try (Connection conn = Koneksi.configDB(); Statement st = conn.createStatement()) {
            String query = "UPDATE makanan SET " +
                    "menu_makanan = '" + makanan.getMenu_makanan() + "', " +
                    "harga = '" + makanan.getHarga() + "' " +
                    "WHERE id = " + makanan.getId();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Aksi untuk tombol Hapus
    private void handleDelete(Menu_Makanan makanan) {
        if (makanan != null) {
            try (Connection conn = Koneksi.configDB(); Statement st = conn.createStatement()) {
                String query = "DELETE FROM makanan WHERE id = " + makanan.getId();
                st.executeUpdate(query);
                loadDataMakanan(); // Refresh data setelah menghapus
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        loadDataMakanan(); // Memuat data saat pertama kali controller diinisialisasi
        initTable(); // Initialize the table columns with action buttons
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
    private void Admin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FAdmin.fxml"));
            Stage stage = (Stage) btDash.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void About() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FAbout.fxml"));
            Stage stage = (Stage) btAbout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("About");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Minuman() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FMinuman.fxml"));
            Stage stage = (Stage) btMinuman.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Minuman");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Tambah() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FTambahMakanan.fxml"));
            Stage stage = (Stage) btTambah.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tambah Makanan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
