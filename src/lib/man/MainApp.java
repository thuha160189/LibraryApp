package lib.man;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lib.man.model.*;
import lib.man.view.*;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private Role systemRoles = Role.LIBRARIAN;
	private RootLayout rootController = null;

	// System Roles
	public void setSystemRoles(Role systemRoles) {
		this.systemRoles = systemRoles;
	}

	public Role getSystemRoles() {
		return systemRoles;
	}

	// Root Controller
	public RootLayout getRootController() {
		return rootController;
	}

	// Begin ---- Using for LibraryMember
//    private ObservableList<LibraryMember> listLibrabryMember = FXCollections.observableArrayList();
//    private ObservableList<Book> listBook = FXCollections.observableArrayList();

	public MainApp() {
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library Management Application");

		initRootLayout();
		// Show Login Dlg
		if (showLoginDialog()) {
			// Load Roles
			rootController.loadSystemRoles(systemRoles);
		}
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			rootController = loader.getController();
			rootController.setMainApp(this);

			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Shows the LibraryMember overview inside the root layout.
	public void showLibraryMemberOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LibraryMemberOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			LibraryMemberOverview controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Shows the LibraryMember overview inside the root layout.
	public void showBookOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BookOverview.fxml"));
			AnchorPane bookOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(bookOverview);

			// Give the controller access to the main app.
			BookOverview controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Shows the LibraryMember overview inside the root layout.
	public void showCheckoutBook() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CheckoutBook.fxml"));
			AnchorPane checkoutBook = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(checkoutBook);

			// Give the controller access to the main app.
			CheckoutBook controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Opens a dialog to edit details for the specified LibraryMember. If the user
	 * clicks OK, the changes are saved into the provided LibraryMember object and
	 * true is returned.
	 * 
	 * @param libraryMember the LibraryMember object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showLibraryMemberEditDialog(LibraryMember libraryMember) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LibraryMemberEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Library Member");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			LibraryMemberEditDialog controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setLibraryMember(libraryMember);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showBookEditDialog(Book book) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/BookEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Book");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the book into the controller.
			BookEditDialog controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setBook(book);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Show Login Dialog
	public boolean showLoginDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoginDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Login");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the User into the controller.
			LoginDialog controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showCheckoutRecordEditDialog(CheckoutRecord checkoutRecord) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CheckoutRecordEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Checkout Record");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the book into the controller.
			CheckoutRecordEditDialog controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCheckoutRecord(checkoutRecord);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showCheckoutRecordEntryEditDialog(CheckoutRecordEntry checkoutRecordEntry) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CheckoutRecordEntryEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Checkout Record Entry");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the book into the controller.
			CheckoutRecordEntryEditDialog controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setCheckoutRecordEntry(checkoutRecordEntry);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
