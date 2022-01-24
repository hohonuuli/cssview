package com.github.hohonuuli.cssview;

import java.net.MalformedURLException;
import java.net.URL;
import com.dlsc.showcase.CssShowcaseView;
import com.dlsc.showcase.CssShowcaseView.CssConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    BorderPane borderPane = new BorderPane();
    CssShowcaseView view = new CssShowcaseView();

    @Override
    public void start(Stage stage) {
        
        // Open button on a toolbar
        var toolbar = new ToolBar();
        var openButton = new Button("Load CSS File");
        openButton.setOnAction(activeEvent -> {
            var choose = new FileChooser();
            choose.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSS Files", "*.css"));
            var selectedFile = choose.showOpenDialog(stage);
            URL selectedUrl = null;
            try {
                selectedUrl = selectedFile.toURI().toURL();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            var config = new CssConfiguration("Resolved Style", selectedUrl.toExternalForm());
            view.getConfigurations().add(config);
            view.setSelectedConfiguration(config);
        });
        toolbar.getItems().add(openButton);
        borderPane.setCenter(view);
        borderPane.setTop(toolbar);



        var scene = new Scene(borderPane, 640, 900);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}