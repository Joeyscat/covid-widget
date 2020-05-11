package fun.oook.cw;

import fun.oook.cw.datafetch.DataProviderService;
import fun.oook.cw.datafetch.model.CovidDataModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class Launch extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Scene scene = new Scene(new StackPane(new Label("JavaFX")), 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        final CovidDataModel covidDataModel = new DataProviderService().getDate("china");
        System.out.println(covidDataModel);
    }
}
