package org.video.videosnipper;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.media.*;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;

public class VideoController {
    private String filePath;
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button playPauseButton;
    @FXML
    private ComboBox<String> speedBox;
    @FXML
    private CheckBox loopCheckBox;
    @FXML
    private void skipBackwardTen(){
        skipBySeconds(-10);
    }
    @FXML
    private void skipForwardTen(){
        skipBySeconds(10);
    }
    @FXML
    private void skipBackwardTwenty(){
        skipBySeconds(-20);
    }
    @FXML
    private void skipForwardTwenty(){
        skipBySeconds(20);
    }
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
    private void skipBySeconds(int seconds) {
        if (mediaPlayer != null) {
            javafx.util.Duration currentTime = mediaPlayer.getCurrentTime();
            javafx.util.Duration newTime = currentTime.add(javafx.util.Duration.seconds(seconds));
            if (newTime.lessThan(javafx.util.Duration.ZERO)) {
                newTime = javafx.util.Duration.ZERO;
            }
            mediaPlayer.seek(newTime);
        }
    }
    @FXML
    private void handleScreenshot() {
        if (mediaView != null && mediaView.getMediaPlayer() != null) {
            WritableImage image = mediaView.snapshot(null, null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Screenshot");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void handleLoopToggle(){
        if (mediaPlayer == null) return;
        boolean loopCheck = loopCheckBox.isSelected();
        mediaPlayer.setCycleCount(loopCheck? MediaPlayer.INDEFINITE : 1);
    }
}