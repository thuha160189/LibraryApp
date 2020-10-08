package lib.man.view;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.man.controller.SystemController;
import lib.man.model.Book;
import lib.man.model.CheckoutRecord;
import lib.man.model.LibraryMember;

public class CheckoutRecordEditDialog {

    @FXML
    private TextField txtMemberId;
    @FXML
//    private TextField txtAuthor;
//    @FXML
//    private TextField txtTitle;
//    @FXML
//    private TextField txtMaxCheckout;

    private Stage dialogStage;
    private CheckoutRecord thisCheckoutRecord;
    private boolean okClicked = false;
    private SystemController controller;// = new SystemController();

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

    /**
     * Sets the LibraryMember to be edited in the dialog.
     * 
     * @param libraryMember
     */
    public void setCheckoutRecord(CheckoutRecord item) {
        this.thisCheckoutRecord = item;
/*
        txtIsbn.setText(thisBook.getIsbn());
        //txtAuthor.setText(thisBook.getAuthor());
        txtTitle.setText(thisBook.getTitle());
        txtMaxCheckout.setText(Integer.toString(thisBook.getMaxCheckout()));
*/    }

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
        	LibraryMember libmem = controller.finMemberById(Long.valueOf(txtMemberId.getText()));
        	this.thisCheckoutRecord.setOwner(libmem);
        	
        	//Save checkout into database
        	
            okClicked = true;
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
/*
        if (txtIsbn.getText() == null || txtIsbn.getText().length() == 0) {
            errorMessage += "No valid ISBN!\n"; 
        }
        if (txtAuthor.getText() == null || txtAuthor.getText().length() == 0) {
            errorMessage += "No valid Author!\n"; 
        }
        if (txtTitle.getText() == null || txtTitle.getText().length() == 0) {
            errorMessage += "No valid Titlte!\n"; 
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
*/
        return true;
    }
}