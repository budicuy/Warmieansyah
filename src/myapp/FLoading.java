package myapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FLoading {

    @FXML
    private ProgressBar PBLoading;

    @FXML
    private Text txtLoading;

    private double progress = 0;

    public void initialize() {
        // Timeline untuk memperbarui progress bar
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(50), e -> updateProgress()));
        timeline.setCycleCount(100); // Total 100 langkah (100%)
        timeline.play();
    }

    private void updateProgress() {
        progress += 0.01; // Tambah progress
        PBLoading.setProgress(progress);
        txtLoading.setText("Loading... " + (int) (progress * 100) + "%");

        // Jika progress mencapai 100%, pindah ke halaman FLogin
        if (progress >= 1) {
            FLogin login = new FLogin();
            login.start(new Stage());
            PBLoading.getScene().getWindow().hide();

        }
    }
}
