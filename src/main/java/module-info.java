module com.ek.practise2javafxml {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.ek.practise2javafxml to javafx.fxml;
    exports com.ek.practise2javafxml;
    exports com.ek.practise2javafxml.components;
    opens com.ek.practise2javafxml.components to javafx.fxml;
}