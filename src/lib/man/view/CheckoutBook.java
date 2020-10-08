package lib.man.view;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lib.man.MainApp;
import lib.man.controller.SystemController;
import lib.man.model.Book;
import lib.man.model.CheckoutRecord;
import lib.man.model.CheckoutRecordEntry;
import lib.man.model.LibraryMember;

public class CheckoutBook {
	// Reference to the main application.
	private MainApp mainApp;
	// Checkout Record
	private ObservableList<CheckoutRecord> listCheckoutRecord = FXCollections.observableArrayList();
	private SystemController controller;// = new SystemController();

	@FXML
	private TableView<LibraryMember> tblCheckoutRecord;
	@FXML
	private TableColumn<LibraryMember, String> colMemberID;
	@FXML
	private TableColumn<LibraryMember, String> colFirstName;
	@FXML
	private TableColumn<LibraryMember, String> colLastName;

	// ---------- Checkout Record Entry
	private ObservableList<CheckoutRecordEntry> listCheckoutRecordEntry = FXCollections.observableArrayList();
	@FXML
	private TableView<CheckoutRecordEntry> tblCheckoutRecordEntry;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> colIsbn;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> colTitle;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> colCheckoutDate;
	@FXML
	private TableColumn<CheckoutRecordEntry, String> colDueDate;
	
	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public CheckoutBook() {
		listCheckoutRecord = FXCollections.observableArrayList();
		controller = new SystemController();
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		colMemberID.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberID"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));
		
//		colIsbn.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("Isbn"));
//		colTitle.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("Title"));
		colCheckoutDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("checkoutDate"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<CheckoutRecordEntry, String>("dueDate"));
		
		getCheckouts();
		
		// Listen for selection changes and show the person details when changed.
		tblCheckoutRecord.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCheckoutRecordDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		// tblBook.setItems(mainApp.getBooks());
	}


	// Fills all text fields to show details about the Book. If the specified Book
	// is null, all text fields are cleared.
	private void showCheckoutRecordDetails(LibraryMember checkoutRecord) {
		if(checkoutRecord == null)
			return;
		ObservableList<CheckoutRecordEntry> listEntries = FXCollections.observableArrayList();
		checkoutRecord.getCheckoutRecord().getCheckoutEntries().forEach(c -> {
			listEntries.add(c);
		});
		tblCheckoutRecordEntry.setItems(listEntries);
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteCheckoutRecord() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Under construction.");
		alert.showAndWait();
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit details
	 * for a new Book.
	 */
	@FXML
	private void handleNewCheckoutRecord() {
//		CheckoutRecord temp = new CheckoutRecord();
		CheckoutRecord temp = null; // Using Temp
		boolean okClicked = mainApp.showCheckoutRecordEditDialog(temp);
		if (okClicked) {
			listCheckoutRecord.add(temp);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit details
	 * for the selected Book.
	 */
	@FXML
	private void handleEditCheckoutRecord() {
//		CheckoutRecord selectedItem = tblCheckoutRecord.getSelectionModel().getSelectedItem();
//
//		if (selectedItem != null) {
//			boolean okClicked = mainApp.showCheckoutRecordEditDialog(selectedItem);
//			if (okClicked) {
//				showCheckoutRecordDetails(selectedItem);
//			}
//
//		} else {
//			// Nothing selected.
//			Alert alert = new Alert(AlertType.WARNING);
//			alert.initOwner(mainApp.getPrimaryStage());
//			alert.setTitle("No Selection");
//			alert.setHeaderText("No Checkout Record Selected");
//			alert.setContentText("Please select a Checkout Record in the table.");
//
//			alert.showAndWait();
//		}
	}
	
	// Called when the user clicks on the delete button.
	@FXML
	private void handleDeleteCheckoutRecordEntry() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setContentText("Under construction.");
		alert.showAndWait();
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit details
	 * for a new Book.
	 */
	@FXML
	private void handleNewCheckoutRecordEntry() {
//		CheckoutRecordEntry temp = new CheckoutRecord();
		CheckoutRecordEntry temp = null; // Using Temp
		boolean okClicked = mainApp.showCheckoutRecordEntryEditDialog(temp);
		if (okClicked) {
			listCheckoutRecordEntry.add(temp);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit details
	 * for the selected Book.
	 */
	@FXML
	private void handleEditCheckoutRecordEntry() {
//		CheckoutRecordEntry selectedItem = tblCheckoutRecordEntry.getSelectionModel().getSelectedItem();
//
//		if (selectedItem != null) {
//			boolean okClicked = mainApp.showCheckoutRecordEntryEditDialog(selectedItem);
//			if (okClicked) {
//				showCheckoutRecordEntryDetails(selectedItem);
//			}
//
//		} else {
//			// Nothing selected.
//			Alert alert = new Alert(AlertType.WARNING);
//			alert.initOwner(mainApp.getPrimaryStage());
//			alert.setTitle("No Selection");
//			alert.setHeaderText("No Checkout Record Entry Selected");
//			alert.setContentText("Please select a Checkout Record Entry in the table.");
//
//			alert.showAndWait();
//		}
	}
	private void getCheckouts() {
		listCheckoutRecord = controller.getCheckoutRecordEntries();
		ObservableList<LibraryMember> listMembers = FXCollections.observableArrayList();
		listCheckoutRecord.forEach(c -> {
			listMembers.add(c.getOwner());
		});
		tblCheckoutRecord.setItems(listMembers);

		// Clear person details.
		showCheckoutRecordDetails(null);
	}
}