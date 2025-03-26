module org.video.videosnipper {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.video.videosnipper to javafx.fxml;
    exports org.video.videosnipper;
}