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
import model.Menu_Minuman;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FMinuman extends Application {

    @FXML
    private Button btMakanan, btTambah, btDash, btAbout, btKeluar, btDetail;

    @FXML
    private TableView<Menu_Minuman> tblMinuman;

    @FXML
    private TableColumn<Menu_Minuman, String> tblId, tblMenuMinuman, tblHarga;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FMinuman.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Menu Minuman");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void initTable() {
        
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblMenuMinuman.setCellValueFactory(new PropertyValueFactory<>("menu_minuman"));
        tblHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        tblMenuMinuman.setCellFactory(TextFieldTableCell.forTableColumn());
        tblMenuMinuman.setOnEditCommit(event -> {
            Menu_Minuman minuman = event.getRowValue();
            minuman.setMenu_minuman(event.getNewValue());
            updateMinuman(minuman);
        });

        tblHarga.setCellFactory(TextFieldTableCell.forTableColumn());
        tblHarga.setOnEditCommit(event -> {
            Menu_Minuman minuman = event.getRowValue();
            minuman.setHarga(event.getNewValue());
            updateMinuman(minuman); 
        });

        
        TableColumn<Menu_Minuman, Void> actionColumn = new TableColumn<>("Aksi");

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btnDelete = new Button("Hapus");
            private final Button btEdit = new Button("Edit");
            private final HBox hbox = new HBox(10, btnDelete);
            private final HBox ebox = new HBox(10, btEdit);

            {
                btnDelete.setOnAction(event -> handleDelete(getTableRow().getItem()));
            }

            {
                btEdit.setOnAction(event -> {
                    Menu_Minuman minuman = getTableRow().getItem();
                    if (minuman != null) {
                        FEditMinuman.Minuman(minuman); // Pemanggilan metode yang benar
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("FEditMinuman.fxml"));
                            Stage stage = (Stage) btEdit.getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.setTitle("Edit Minuman");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                
            }


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox(10, btnDelete, btEdit);
                    setGraphic(container);
                }
            }
        });

        tblMinuman.getColumns().add(actionColumn);
    }

    private void loadDataMinuman() {
        ObservableList<Menu_Minuman> minumanList = FXCollections.observableArrayList();
        try (Connection conn = Config.configDB(); Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM minuman");
            while (rs.next()) {
                minumanList.add(new Menu_Minuman(
                        rs.getInt("id"),
                        rs.getString("menu_minuman"),
                        rs.getString("harga")));
            }
            tblMinuman.setItems(minumanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void updateMinuman(Menu_Minuman minuman) {
        try (Connection conn = Config.configDB(); Statement st = conn.createStatement()) {
            String query = "UPDATE minuman SET " +
                    "menu_minuman = '" + minuman.getMenu_minuman() + "', " +
                    "harga = '" + minuman.getHarga() + "' " +
                    "WHERE id = " + minuman.getId();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleDelete(Menu_Minuman minuman) {
        if (minuman != null) {
            try (Connection conn = Config.configDB(); Statement st = conn.createStatement()) {
                String query = "DELETE FROM minuman WHERE id = " + minuman.getId();
                st.executeUpdate(query);
                loadDataMinuman(); // Refresh data setelah menghapus
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        loadDataMinuman(); 
        initTable(); 
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
    private void Makanan() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FMakanan.fxml"));
            Stage stage = (Stage) btMakanan.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Makanan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void Tambah() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FTambahMinuman.fxml"));
            Stage stage = (Stage) btAbout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tambah Minuman");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
