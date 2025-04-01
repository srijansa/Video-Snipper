package org.video.videosnipper;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.media.*;
import javafx.stage.FileChooser;
import java.io.File;

public class VideoController {
    private String filePath;
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button playPauseButton;
    @FXML
    private ComboBox<String> speedBox;
    public void handleOpenAction(javafx.event.ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video Files", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();
        if (filePath != null){
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            DoubleProperty height = mediaView.fitWidthProperty();
            DoubleProperty width = mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            mediaPlayer.play();
        }
    }
    public void handlePlayPauseAction(){
        if (mediaPlayer == null) return;
        MediaPlayer.Status current_status = mediaPlayer.getStatus();
        if (current_status == MediaPlayer.Status.PLAYING){
            mediaPlayer.pause();
            playPauseButton.setText("▶");
        } else {
            mediaPlayer.play();
            playPauseButton.setText("⏸");
        }
    }

    public void hanleSpeedAction(){
        String option = speedBox.getValue();
        if (mediaPlayer == null) return;
        if (option != null) {
            double rate = Double.parseDouble(option.replace("x", ""));
            mediaPlayer.setRate(rate);
        }
    }
}