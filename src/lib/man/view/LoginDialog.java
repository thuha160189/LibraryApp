package lib.man.view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.man.MainApp;
import lib.man.controller.SystemController;
import lib.man.model.User;


public class LoginDialog {
    // Reference to the main application
    private MainApp mainApp;
    // Is called by the main application to give a reference back to itself.
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;

    private Stage dialogStage;
    private boolean okClicked = false;

    private String userName;
    private String password;
    SystemController controller;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	controller = new SystemController();
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

	// Returns true if the user clicked OK, false otherwise.
    public boolean isOkClicked() {
        return okClicked;
    }

	// Called when the user clicks ok.
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	userName = txtUserName.getText().trim();
            password = txtPassword.getText();
            // Update return value
            User loggedInUser = controller.checkUser(new User(userName, password));
            boolean isAuthenticate = loggedInUser != null ? true : false;
            
            if(!isAuthenticate) {
            	// Show the error message.
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid User");
                alert.setHeaderText("Login error");
                alert.setContentText("Username or password is invalid!");
                
                alert.showAndWait();
            } else {
            	mainApp.setSystemRoles(loggedInUser.getRole());
	            okClicked = true;
	            dialogStage.close();
            }
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (txtUserName.getText() == null || txtUserName.getText().length() == 0) {
            errorMessage += "Invalid User Name!\n"; 
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}