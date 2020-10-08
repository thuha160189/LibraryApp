package lib.man.view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.man.controller.SystemController;
import lib.man.model.LibraryMember;


public class LibraryMemberEditDialog {

    @FXML
    private TextField txtMemberID;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtPhone;

    private Stage dialogStage;
    private LibraryMember libraryMember;
    private boolean okClicked = false;
    private SystemController controller;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	this.controller = new SystemController();
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the LibraryMember to be edited in the dialog.
     * 
     * @param libraryMember
     */
    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;

        txtMemberID.setText(String.valueOf(libraryMember.getMemberId()));
        txtFirstName.setText(libraryMember.getFirstName());
        txtLastName.setText(libraryMember.getLastName());
        txtPhone.setText(libraryMember.getPhone());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	if(txtMemberID.getText() != "")
        		libraryMember.setMemberId(Long.valueOf(txtMemberID.getText()));
            libraryMember.setFirstName(txtFirstName.getText());
            libraryMember.setLastName(txtLastName.getText());
            libraryMember.setPhone(txtPhone.getText());
            if(txtMemberID.getText() != "")
            	okClicked = controller.updateLibraryMember(libraryMember);
            else {
            	long memberId = controller.addNewLibraryMember(libraryMember);
            	if(memberId > 0)
            		libraryMember.setMemberId(memberId);
            	okClicked = memberId > 0;
            }
            dialogStage.close();
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

        if (txtFirstName.getText() == null || txtFirstName.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (txtLastName.getText() == null || txtLastName.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
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