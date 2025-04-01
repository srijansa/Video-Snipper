package org.video.videosnipper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VideoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VideoApplication.class.getResource("video-layout.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Video Snipper");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}