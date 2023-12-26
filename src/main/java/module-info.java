module com.w9_assignment {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.w9_assignment to javafx.fxml;
    exports com.w9_assignment;
}