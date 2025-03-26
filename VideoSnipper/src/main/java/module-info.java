module org.video.videosnipper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens org.video.videosnipper to javafx.fxml;
    exports org.video.videosnipper;
}