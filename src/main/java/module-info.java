//Necessary file to make my/the compiler add required modules to VM options.

module com.jmmedina00.fxscores {
    //JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    //The rest of things
    requires jsefa;
    requires extjwnl;
    requires extjwnl.data.wn31;

    opens com.jmmedina00.fxscores to javafx.fxml;
    exports com.jmmedina00.fxscores;
}