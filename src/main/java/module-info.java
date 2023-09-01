module ma.enset.examjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ma.enset.examjavafx to javafx.fxml;
    exports ma.enset.examjavafx;
    exports ma.enset.examjavafx.presentation.controllers;
    opens ma.enset.examjavafx.presentation.controllers to javafx.fxml;
    opens ma.enset.examjavafx.dao.entities;
}