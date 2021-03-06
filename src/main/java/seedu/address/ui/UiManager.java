package seedu.address.ui;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.MainApp;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.TaskAddedEvent;
import seedu.address.commons.events.model.TaskEditedEvent;
import seedu.address.commons.events.model.ViewCategoryChangedEvent;
import seedu.address.commons.events.storage.DataSavingExceptionEvent;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.ShowHelpRequestEvent;
import seedu.address.commons.events.ui.TaskCardMarkChangedEvent;
import seedu.address.commons.events.ui.LocateItemRequestEvent;
import seedu.address.commons.events.ui.MinimizeRequestEvent;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.model.UserPrefs;

import java.util.logging.Logger;

//@@author A0135767U
/**
 * The manager of the UI component.
 */
public class UiManager extends ComponentManager implements Ui {
    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/address_book_32.png";

    private Logic logic;
    private Config config;
    private UserPrefs prefs;
    private MainWindow mainWindow;
    private TaskWindow taskWindow;

    public UiManager(Logic logic, Config config, UserPrefs prefs) {
        super();
        this.logic = logic;
        this.config = config;
        this.prefs = prefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");
        primaryStage.setTitle(config.getAppTitle());

        //Set the application icon.
        primaryStage.getIcons().add(getImage(ICON_APPLICATION));
        
        try {
            //Set stage to Transparent
            primaryStage.initStyle(StageStyle.TRANSPARENT);
        } catch (IllegalStateException e) {
        	logger.warning(e.getMessage());
        }

        try {
            mainWindow = MainWindow.load(primaryStage, config, prefs, logic);
            mainWindow.show(); //This should be called before creating other UI parts
            mainWindow.fillInnerParts();
            
            taskWindow = TaskWindow.load(primaryStage, prefs);

        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error during initializing", e);
        }
    }

    @Override
    public void stop() {
        prefs.updateLastUsedGuiSetting(mainWindow.getCurrentGuiSetting());
        mainWindow.hide();
        mainWindow.releaseResources();
    }

    private void showFileOperationAlertAndWait(String description, String details, Throwable cause) {
        final String content = details + ":\n" + cause.toString();
        showAlertDialogAndWait(AlertType.ERROR, "File Op Error", description, content);
    }

    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    private static void showAlertDialogAndWait(Stage owner, AlertType type, String title, String headerText,
                                               String contentText) {
        final Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    private void showFatalErrorDialogAndShutdown(String title, Throwable e) {
        logger.severe(title + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait(Alert.AlertType.ERROR, title, e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }

    //==================== Event Handling Code =================================================================

    @Subscribe
    private void handleDataSavingExceptionEvent(DataSavingExceptionEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        showFileOperationAlertAndWait("Could not save data", "Could not save data to file", event.exception);
    }

    @Subscribe
    private void handleShowHelpEvent(ShowHelpRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.handleHelp();
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.getTaskListPanel().scrollTo(event.targetIndex);
    }

    @Subscribe
    private void handleTaskPanelSelectionChangedEvent(TaskPanelSelectionChangedEvent event){
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        taskWindow.loadTaskCard(event.getNewSelection());
        taskWindow.show();
    }
    
    @Subscribe
    private void handleLocateItemRequestEvent(LocateItemRequestEvent event) {
    	logger.info(LogsCenter.getEventHandlingLogMessage(event));
    	mainWindow.getTaskListPanel().scrollDeselect(event.targetIndex);
    	taskWindow.loadTaskPage(event.getNewSelection());
    	taskWindow.show();
    }
    
    //@@author A0135812L
    @Subscribe
    private void handleTaskCardMarkChangedEvent(TaskCardMarkChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.markTaskCard(event.getDisplayedIndex());
    }
    
    @Subscribe
    private void handleTaskAddedEvent(TaskAddedEvent event){
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.getTaskListPanel().scrollTo(event.getIndex());
        taskWindow.loadTaskPage(event.getAddedTask());
        taskWindow.show();  
    }
    
    @Subscribe
    private void handleTaskEditedEvent(TaskEditedEvent event){
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.getTaskListPanel().scrollTo(event.getIndex());
        taskWindow.loadTaskPage(event.getAddedTask());
        taskWindow.show(); 
    }
    
    @Subscribe
    private void handleMinimizeRequestEvent(MinimizeRequestEvent event) {
    	mainWindow.getTaskListPanel().deselectAll();
        taskWindow.hide();
    }
    
    //@@author
    
    //@@author A0135767U
    @Subscribe
    private void handleViewCategoryChangedEvent(ViewCategoryChangedEvent event) {
    	logger.info(LogsCenter.getEventHandlingLogMessage(event));
    	mainWindow.updateViewStatus(event.getView());
    }
    
}
