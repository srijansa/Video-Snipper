module org.video.videosnipper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires javafx.swing;

    opens org.video.videosnipper to javafx.fxml;
    exports org.video.videosnipper;
}