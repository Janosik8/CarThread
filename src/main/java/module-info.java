module com.example.symulator2_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.example.symulator2_javafx to javafx.fxml;
    exports com.example.symulator2_javafx;
}