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
import model.Pesanan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FAdmin extends Application {

    @FXML
    private Button BtDashboard;

    @FXML
    private Button BtTambah;

    @FXML
    private Button btAbout;

    @FXML
    private TableView<Pesanan> tblPesanan;
    @FXML
    private TableColumn<Pesanan, String> tblId, tblNama, tblTanggalPesanan, tblMenuMakanan, tblMenuMinuman,
            tblTotalHarga;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FAdmin.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Admin");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void initTable() {
        // Set the cell value factories for the TableView columns
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        tblTanggalPesanan.setCellValueFactory(new PropertyValueFactory<>("tanggal_pesanan"));
        tblMenuMakanan.setCellValueFactory(new PropertyValueFactory<>("menu_makanan"));
        tblMenuMinuman.setCellValueFactory(new PropertyValueFactory<>("menu_minuman"));
        tblTotalHarga.setCellValueFactory(new PropertyValueFactory<>("total_harga"));

        // Editable columns (TableCell)
        tblMenuMakanan.setCellFactory(TextFieldTableCell.forTableColumn());
        tblMenuMakanan.setOnEditCommit(event -> {
            Pesanan pesanan = event.getRowValue();
            pesanan.setMenu_makanan(event.getNewValue());
            updatePesanan(pesanan); // Update in DB or model
        });

        tblMenuMinuman.setCellFactory(TextFieldTableCell.forTableColumn());
        tblMenuMinuman.setOnEditCommit(event -> {
            Pesanan pesanan = event.getRowValue();
            pesanan.setMenu_minuman(event.getNewValue());
            updatePesanan(pesanan); // Update in DB or model
        });

        tblTotalHarga.setCellFactory(TextFieldTableCell.forTableColumn());
        tblTotalHarga.setOnEditCommit(event -> {
            Pesanan pesanan = event.getRowValue();
            pesanan.setTotal_harga(event.getNewValue());
            updatePesanan(pesanan); // Update in DB or model
        });

        // Column for Edit and Delete buttons
        TableColumn<Pesanan, Void> actionColumn = new TableColumn<>("Aksi");

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

        tblPesanan.getColumns().add(actionColumn);
    }

    private void loadDataPesanan() {
        ObservableList<Pesanan> pesananList = FXCollections.observableArrayList();
        try (Connection conn = Koneksi.configDB(); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM pesanan");
            while (rs.next()) {
                pesananList.add(new Pesanan(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("tanggal_pesan"),
                        rs.getString("menu_makanan"),
                        rs.getString("menu_minuman"),
                        rs.getString("total_harga")));
            }
            tblPesanan.setItems(pesananList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Aksi untuk mengupdate pesanan setelah diedit
    private void updatePesanan(Pesanan pesanan) {
        try (Connection conn = Koneksi.configDB(); Statement st = conn.createStatement()) {
            String query = "UPDATE pesanan SET " +
                    "menu_makanan = '" + pesanan.getMenu_makanan() + "', " +
                    "menu_minuman = '" + pesanan.getMenu_minuman() + "', " +
                    "total_harga = '" + pesanan.getTotal_harga() + "' " +
                    "WHERE id = " + pesanan.getId();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Aksi untuk tombol Hapus
    private void handleDelete(Pesanan pesanan) {
        if (pesanan != null) {
            try (Connection conn = Koneksi.configDB(); Statement st = conn.createStatement()) {
                String query = "DELETE FROM pesanan WHERE id = " + pesanan.getId();
                st.executeUpdate(query);
                loadDataPesanan(); // Refresh data setelah menghapus
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        loadDataPesanan(); // Memuat data saat pertama kali controller diinisialisasi
        initTable(); // Initialize the table columns with action buttons
    }
}
