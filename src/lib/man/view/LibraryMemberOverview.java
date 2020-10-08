package lib.man.view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lib.man.MainApp;
import lib.man.controller.SystemController;
import lib.man.model.LibraryMember;

public class LibraryMemberOverview {

	private ObservableList<LibraryMember> listLibrabryMember;
	private SystemController controller;// = new SystemController();

	@FXML
	private TableView<LibraryMember> tblLibraryMember;
	@FXML
	private TableColumn<LibraryMember, String> colMemberID;
	@FXML
	private TableColumn<LibraryMember, String> colFirstName;
	@FXML
	private TableColumn<LibraryMember, String> colLastName;

	@FXML
	private Label labelMemberID;
	@FXML
	private Label labelFirstName;
	@FXML
	private Label labelLastName;
	@FXML
	private Label labelPhone;

	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public LibraryMemberOverview() {
		controller = new SystemController();
		listLibrabryMember = FXCollections.observableArrayList();
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		/*
		 * colMemberID.setCellValueFactory(cellData ->
		 * cellData.getValue().memberIDProperty());
		 * colFirstName.setCellValueFactory(cellData ->
		 * cellData.getValue().firstNameProperty());
		 * colLastName.setCellValueFactory(cellData ->
		 * cellData.getValue().lastNameProperty());
		 */
		colMemberID.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("lastName"));

		getLibraryMembers();

		// Listen for selection changes and show the person details when changed.
		tblLibraryMember.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showLibraryMemberDetails(newValue));

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		/*
		 * // Add observable list data to the table
		 * tblLibraryMember.setItems(mainApp.getLirabryMembers());
		 */
	}

	/**
	 * Fills all text fields to show details about the LibraryMember. If the
	 * specified LibraryMember is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showLibraryMemberDetails(LibraryMember member) {
		if (member != null) {
			// Fill the labels with info from the person object.
			labelMemberID.setText(String.valueOf(member.getMemberId()));
			labelFirstName.setText(member.getFirstName());
			labelLastName.setText(member.getLastName());
			labelPhone.setText(member.getPhone());

			// TODO: We need a way to convert the birthday into a String!
			// birthdayLabel.setText(...);
		} else {
			// Person is null, remove all the text.
			labelMemberID.setText("");
			labelFirstName.setText("");
			labelLastName.setText("");
			labelPhone.setText("");
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteLirabryMember() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(mainApp.getPrimaryStage());

		int selectedIndex = tblLibraryMember.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				if (controller.deleteLibraryMember(tblLibraryMember.getItems().get(selectedIndex))) {
					tblLibraryMember.getItems().remove(selectedIndex);
				}
				/*
				 * else { alert.setAlertType(AlertType.ERROR); alert.setTitle("No Selection");
				 * alert.setHeaderText("No Library Member Selected");
				 * alert.setContentText("Please select a Member in the table.");
				 * alert.showAndWait(); }
				 */
			}

		} else {
			// Nothing selected.
			alert.setAlertType(AlertType.WARNING);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Library Member Selected");
			alert.setContentText("Please select a Member in the table.");
			alert.showAndWait();
		}
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit details
	 * for a new LibraryMember.
	 */
	@FXML
	private void handleNewLibraryMember() {
		LibraryMember tempLibraryMember = new LibraryMember();
		boolean okClicked = mainApp.showLibraryMemberEditDialog(tempLibraryMember);
		if (okClicked) {
			// mainApp.getLirabryMembers().add(tempLibraryMember);
			listLibrabryMember.add(tempLibraryMember);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit details
	 * for the selected LibraryMember.
	 */
	@FXML
	private void handleEditLibraryMember() {
		LibraryMember selectedLibraryMember = tblLibraryMember.getSelectionModel().getSelectedItem();
		if (selectedLibraryMember != null) {
			boolean okClicked = mainApp.showLibraryMemberEditDialog(selectedLibraryMember);
			if (okClicked) {
				showLibraryMemberDetails(selectedLibraryMember);
			}

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Library Member Selected");
			alert.setContentText("Please select a Library Member in the table.");

			alert.showAndWait();
		}
	}

	private void getLibraryMembers() {

		listLibrabryMember = controller.getLibraryMembers();
		tblLibraryMember.setItems(listLibrabryMember);
		// Clear person details.
		showLibraryMemberDetails(null);

	}
}