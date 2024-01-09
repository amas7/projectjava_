module com.example.projectjava_ {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectjava_ to javafx.fxml;
    exports com.example.projectjava_;
}