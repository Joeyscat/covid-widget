package fun.oook.cw.gui.widget;

import fun.oook.cw.config.ConfigModel;
import fun.oook.cw.config.ConfigurationService;
import fun.oook.cw.datafetch.DataProviderService;
import fun.oook.cw.datafetch.model.CountryData;
import fun.oook.cw.datafetch.model.CovidDataModel;
import fun.oook.cw.datafetch.model.GlobalData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class WidgetController implements Initializable {

    private ScheduledExecutorService executorService;

    private ConfigModel configModel;

    @FXML
    public AnchorPane rootPane;
    @FXML
    public Text textGlobalReport, textCountryCode, textCountryReport;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initializeContextMenu();

        try {
            configModel = new ConfigurationService().getConfiguration();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        textCountryCode.setText(configModel.getCountryCode());
        initializeScheduler();
    }

    private void initializeScheduler() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::loadData, 0,
                configModel.getRefreshIntervalInSeconds(), TimeUnit.SECONDS);
    }

    private void loadData() {
        System.out.println("Loading data...");

        final DataProviderService dataProviderService = new DataProviderService();
        final CovidDataModel covidDataModel = dataProviderService.getDate(configModel.getCountryName());
        Platform.runLater(() -> inflateData(covidDataModel));
    }

    private void inflateData(final CovidDataModel covidDataModel) {
        final GlobalData globalData = covidDataModel.getGlobalData();
        final CountryData countryData = covidDataModel.getCountryData();

        textGlobalReport.setText(getFormattedData(globalData.getCases(), globalData.getRecovered(), globalData.getDeaths()));
        textCountryReport.setText(getFormattedData(countryData.getCases(), countryData.getRecovered(), countryData.getDeaths()));

        readjustStage(textGlobalReport.getScene().getWindow());
    }

    private String getFormattedData(final long cases, final long recovered, final long deaths) {
        return String.format("Cases: %-8d | Recovered: %-6d | Deaths: %-6d", cases, recovered, deaths);
    }

    private void readjustStage(final Window stage) {
        stage.sizeToScene();

        final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(visualBounds.getMaxX() - 25 - textGlobalReport.getScene().getWidth());
        stage.setY(visualBounds.getMinY() + 25);
    }

    private void initializeContextMenu() {
        final MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> System.exit(0));

        final MenuItem refreshItem = new MenuItem("Refresh Now");
        refreshItem.setOnAction(event -> {
            executorService.schedule(this::loadData, 0, TimeUnit.SECONDS);
        });

        final ContextMenu contextMenu = new ContextMenu(exitItem, refreshItem);
        rootPane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (event.isSecondaryButtonDown()) {
                contextMenu.show(rootPane, event.getScreenX(), event.getScreenY());
            } else {
                if (contextMenu.isShowing()) {
                    contextMenu.hide();
                }
            }
        });
    }
}
