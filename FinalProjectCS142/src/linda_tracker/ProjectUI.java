package linda_tracker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import numberlist.BirthDateException;
import numberlist.IndexException;

import java.time.LocalDate;

/**
 * This class represents the presentation layer of the program, allowing users
 * to enter and remove data to their free will. Additional features include the
 * availability of statistical information.
 *
 * @author Phuoc Nguyen
 * @author Samuel Adrian Kosasih
 * @author Joyce Weng
 * 
 * @version %I%, %G%
 * @since JDK 8u271
 *
 * @see java.time.LocalDate
 * @see javafx.application.Application
 * @see javafx.geometry.Insets
 * @see javafx.geometry.Pos
 * @see javafx.scene.Scene
 * @see javafx.scene.control.Alert
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.ComboBox
 * @see javafx.scene.control.DatePicker
 * @see javafx.scene.control.Label
 * @see javafx.scene.control.ScrollPane
 * @see javafx.scene.control.TextField
 * @see javafx.scene.layout.Background
 * @see javafx.scene.layout.BackgroundFill
 * @see javafx.scene.layout.BorderPane
 * @see javafx.scene.layout.CornerRadii 
 * @see javafx.scene.layout.FlowPane
 * @see javafx.scene.layout.GridPane
 * @see javafx.scene.layout.HBox
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.paint.Color
 * @see javafx.scene.text.Font
 * @see javafx.scene.text.TextAlignment
 * @see javafx.stage.Stage
 * @see numberlist.BirthDateException
 * @see numberlist.IndexException
 */
public class ProjectUI extends Application {

    // Fields
    private SubscriptionServices list = new SubscriptionServices();
    // Buttons
    private Button btnViewStat;
    private Button btnReadFromFile;
    private Button btnAddPerson;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnAdd;
    private Button btnClear;
    // TextFields and combo box
    private TextField txtName;
    private TextField txtMonths;
    private ComboBox cmbSubsType;
    private DatePicker birthday;
    private ComboBox sortBy;
    //TextFields for view
    private TextField txtPremUsers;
    private TextField txtStanUsers;
    private TextField txtAvgMonths;
    private TextField txtAvgPayment;
    private TextField txtTotalMonth;
    private TextField txtTotalUsers;
    private TextField txtTotalPayment;
    private TextField txtAvgAge;
    //VBox
    private VBox customerList;
    //person selected
    private int personSelected;
    //array of buttons
    private Button[] people;

    /**
     * This method generates and forms a usable GUI for the user to interact
     * with.
     *
     * @param stage the JavaFX stage used to display the program
     */
    @Override
    public void start(Stage stage) {
        //top pane
        FlowPane topPane = getTopPane();
        //left pane
        ScrollPane leftPane = getLeftPane();
        leftPane.setPrefSize(370, 700);
        //center pane
        //Welcome Screen
        VBox welcomePane = getWelcomePane();
        //add button display
        GridPane gridAdd = getAddButtonDisplay();
        //view stat display
        GridPane gridView = getViewButtonDisplay();
        //parent
        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setLeft(leftPane);
        root.setCenter(welcomePane);
        btnAddPerson.setOnAction(e -> {
            clearButtonAction();
            root.setCenter(gridAdd);
        });
        btnUpdate.setOnAction(e -> {
            root.setCenter(gridAdd);
            updateButtonAction();
        });
        btnDelete.setOnAction(e -> deleteButtonAction());
        btnViewStat.setOnAction(e -> {
            root.setCenter(gridView);
            getStat();
        });
        //scene
        Scene scene = new Scene(root);
        //stage
        stage.setScene(scene);
        stage.setTitle("LindaTracker+ Subscription Manager");
        stage.setWidth(750);
        stage.setHeight(400);
        stage.setX(300);
        stage.setY(400);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Creates a VBox Pane representing the welcome screen of our GUI. This pane
     * takes over the right side and only shows at start-up.
     *
     * @return VBox the welcome pane
     */
    VBox getWelcomePane() {
        VBox welcomePane = new VBox();
        welcomePane.setSpacing(20);
        Label lblWelcome = new Label("Welcome to LindaTracker+");
        lblWelcome.setFont(new Font("Arial", 20));
        lblWelcome.setWrapText(true);
        Label lblAddInstruction = new Label("Click" + " Add a person" + " to "
                + "create a new user to be stored into the database.");
        lblAddInstruction.setFont(new Font("Arial", 13));
        lblAddInstruction.setTextAlignment(TextAlignment.CENTER);
        lblAddInstruction.setWrapText(true);
        Label lblReadFromFile = new Label("Alternatively, read from a file to "
                + "view a previously created database, and make edits from there.");
        lblReadFromFile.setFont(new Font("Arial", 13));
        lblReadFromFile.setTextAlignment(TextAlignment.CENTER);
        lblReadFromFile.setWrapText(true);
        welcomePane.getChildren().addAll(lblWelcome, lblAddInstruction, lblReadFromFile);
        welcomePane.setAlignment(Pos.CENTER);
        return welcomePane;
    }

    /**
     * Creates a FlowPane located at the top of the GUI, to allocate user
     * buttons that is used to switch between different panes, sort data to a
     * specific attribute, and make changes towards existing data.
     *
     * @return FlowPane the top pane to interact with the program
     */
    FlowPane getTopPane() {
        FlowPane topPane = new FlowPane();
        Label lblSortBy = new Label("Sort By:");
        sortBy = new ComboBox();
        sortBy.getItems().add("Name");
        sortBy.getItems().add("Total Money");
        sortBy.getItems().add("Total Months");
        sortBy.getItems().add("Age");
        sortBy.getSelectionModel().select(0);
        sortBy.setOnAction(e -> sortingAction());
        btnViewStat = new Button("View Stats");
        btnViewStat.setOnAction(e -> getStat());
        btnAddPerson = new Button("Add a Person");
        btnUpdate = new Button("Update");
        btnDelete = new Button("Delete");
        topPane.getChildren().addAll(lblSortBy, sortBy,
                btnViewStat, btnAddPerson, btnUpdate, btnDelete);
        FlowPane.setMargin(sortBy, new Insets(10, 10, 10, 10));
        FlowPane.setMargin(btnViewStat, new Insets(10, 10, 10, 10));
        FlowPane.setMargin(btnAddPerson, new Insets(10, 10, 10, 10));
        FlowPane.setMargin(btnUpdate, new Insets(10, 10, 10, 10));
        topPane.setAlignment(Pos.CENTER);
        topPane.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,
                CornerRadii.EMPTY, Insets.EMPTY)));

        return topPane;
    }

    /**
     * Creates a ScrollPane to display out customerList VBox and populates it
     * with data entered by the user in the form of buttons.
     *
     * @return ScrollPane the left pane to display data
     */
    ScrollPane getLeftPane() {
        customerList = makeCustomerList();
        ScrollPane leftPane = new ScrollPane();
        leftPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        leftPane.setContent(customerList);
        return leftPane;
    }

    /**
     * Creates a GridPane to display text fields for the user to enter data in
     * specific to a customer they wish to add.
     *
     * @return GridPane the right pane representing the add display
     */
    GridPane getAddButtonDisplay() {
        GridPane gridAdd = new GridPane();
        gridAdd.setHgap(10);
        gridAdd.setVgap(12);
        Label lblName = new Label("Name: ");
        txtName = new TextField();
        Label lblBirthDate = new Label("Birth Date: ");
        birthday = new DatePicker();
        birthday.setPromptText("Month/Day/Year");
        Label lblSubsType = new Label("Subscription Type: ");
        cmbSubsType = new ComboBox();
        cmbSubsType.setPrefWidth(175);
        cmbSubsType.getItems().add("Premium $12.00");
        cmbSubsType.getItems().add("Standard $9.00");
        cmbSubsType.getSelectionModel().select(0);
        Label lblMonths = new Label("Total Months Subscribed: ");
        txtMonths = new TextField();
        btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> {
            addButtonAction();
            clearButtonAction();
        });
        btnClear = new Button("Clear");
        btnClear.setOnAction(e -> clearButtonAction());
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.getChildren().addAll(btnAdd, btnClear);
        gridAdd.add(lblName, 0, 0);
        gridAdd.add(txtName, 1, 0);
        gridAdd.add(lblBirthDate, 0, 1);
        gridAdd.add(birthday, 1, 1);
        gridAdd.add(lblSubsType, 0, 2);
        gridAdd.add(cmbSubsType, 1, 2);
        gridAdd.add(lblMonths, 0, 3);
        gridAdd.add(txtMonths, 1, 3);
        gridAdd.add(hbButtons, 0, 4, 2, 6);
        gridAdd.setAlignment(Pos.CENTER);
        return gridAdd;
    }

    /**
     * Creates a GridPane to display statistical data of all attributes to the
     * user.
     *
     * @return GridPane the right pane representing statistics
     */
    GridPane getViewButtonDisplay() {
        GridPane gridView = new GridPane();
        gridView.setHgap(10);
        gridView.setVgap(12);
        Label lblTotalUsers = new Label("Total Number of Users: ");
        txtTotalUsers = new TextField();
        txtTotalUsers.setEditable(false);
        Label lblAverageAge = new Label("Average Age of Users: ");
        txtAvgAge = new TextField();
        txtAvgAge.setEditable(false);
        Label lblPremUsers = new Label("Total Premium Users: ");
        txtPremUsers = new TextField();
        txtPremUsers.setEditable(false);
        Label lblStanUsers = new Label("Total Standard Users: ");
        txtStanUsers = new TextField();
        txtStanUsers.setEditable(false);
        Label lblAvgMonths = new Label("Average Months Subscribed: ");
        txtAvgMonths = new TextField();
        txtAvgMonths.setEditable(false);
        Label lblAvgPayment = new Label("Average Payment: ");
        txtAvgPayment = new TextField();
        txtAvgPayment.setEditable(false);
        Label lblTotalMonth = new Label("Total Months: ");
        txtTotalMonth = new TextField();
        txtTotalMonth.setEditable(false);
        Label lblTotalPayment = new Label("Total Payment: ");
        txtTotalPayment = new TextField();
        txtTotalPayment.setEditable(false);
        gridView.add(lblTotalUsers, 0, 0);
        gridView.add(txtTotalUsers, 1, 0);
        gridView.add(lblAverageAge, 0, 1);
        gridView.add(txtAvgAge, 1, 1);
        gridView.add(lblPremUsers, 0, 2);
        gridView.add(txtPremUsers, 1, 2);
        gridView.add(lblStanUsers, 0, 3);
        gridView.add(txtStanUsers, 1, 3);
        gridView.add(lblAvgMonths, 0, 4);
        gridView.add(txtAvgMonths, 1, 4);
        gridView.add(lblAvgPayment, 0, 5);
        gridView.add(txtAvgPayment, 1, 5);
        gridView.add(lblTotalMonth, 0, 6);
        gridView.add(txtTotalMonth, 1, 6);
        gridView.add(lblTotalPayment, 0, 7);
        gridView.add(txtTotalPayment, 1, 7);
        gridView.setAlignment(Pos.CENTER);
        return gridView;
    }

    /**
     * Fills the text fields at the statistics display with necessary
     * information.
     */
    public void getStat() {
        try {
            if (list.getCount() == 0) {
                getEmptyListAlert();
            } else {
                txtTotalUsers.setText(
                        Integer.toString(list.getCount()));
                txtAvgAge.setText(
                        Double.toString(list.getAverageAges()));
                txtPremUsers.setText(
                        Integer.toString(list.getPremiumUsers()));
                txtStanUsers.setText(
                        Integer.toString(list.getStandardUsers()));
                txtAvgMonths.setText(
                        Double.toString(list.getAverageMonths()));
                txtAvgPayment.setText(
                        list.getAveragePayment().toString());
                txtTotalMonth.setText(
                        Integer.toString(list.getTotalMonths()));
                txtTotalPayment.setText(
                        list.getTotalPayment().toString());
            }
        } catch (IndexException ie) {
            getIndexAlert();
        }
    }

    /**
     * Creates a VBox to fit in the list of customers while initializing the
     * Button array used to represent customers with.
     *
     * @return a VBox representing the customer list.
     */
    VBox makeCustomerList() {
        people = new Button[list.getCount()];
        customerList = new VBox();
        populateCustomerList();
        return customerList;
    }

    /**
     * Extracts information from the add display, and takes them into the list
     * at the click of the add button.
     */
    public void addButtonAction() {
        if (txtName.getText().isEmpty()
                || txtMonths.getText().isEmpty()) {
            getMissingInputAlert();
        } else {
            try {
                String name = txtName.getText();
                int month = birthday.getValue().getMonthValue();
                int day = birthday.getValue().getDayOfMonth();
                int year = birthday.getValue().getYear();
                boolean premium = cmbSubsType.getValue().toString().equals(
                        "Premium $12.00");
                int totalMonths = Integer.parseInt(txtMonths.getText());
                list.addCustomer(name, month, day, year, premium,
                        totalMonths);
                populateCustomerList();
                sortingAction();
            } catch (IndexException ex) {
                getIndexAlert();
            } catch (BirthDateException ex) {
                getBirthDateAlert();
            }

        }
    }

    /**
     * Removes user from the list and avoid presenting them onto the customer
     * list.
     */
    public void deleteButtonAction() {
        try {
            if (list.getCount() == 0) {
                getEmptyListAlert();
            } else {
                list.deleteCustomer(personSelected);
            }
            populateCustomerList();
        } catch (IndexException ex) {
            getIndexAlert();
        } catch (BirthDateException ex) {
            getBirthDateAlert();
        }
    }

    /**
     * Updates information on a specific customer if the user decides to create
     * any change on the data.
     */
    public void updateButtonAction() {
        if (txtName.getText().isEmpty() || birthday.getValue().toString().isEmpty() || txtMonths.getText().isEmpty()) {
            getMissingInputAlert();
        } else {
            try {
                String name = txtName.getText();
                int month = birthday.getValue().getMonthValue();
                int day = birthday.getValue().getDayOfMonth();
                int year = birthday.getValue().getYear();
                boolean premium = false;
                if (cmbSubsType.getValue().toString().equals(
                        "Premium $12.00")) {
                    premium = true;
                }
                int totalMonths = Integer.parseInt(txtMonths.getText());
                list.updateCustomer(personSelected, name, month, day, year, premium, totalMonths);
                populateCustomerList();
            } catch (IndexException ex) {
                getIndexAlert();
            } catch (BirthDateException ex) {
                getBirthDateAlert();
            }
        }
    }

    /**
     * Creates an array of buttons and fill them with customer information.
     */
    public void populateCustomerList() {
        customerList.getChildren().clear();
        people = new Button[list.getCount()];
        try {
            for (int i = 0; i < list.getCount(); i++) {

                String str = list.getName(i) + " | "
                        + list.getBirthDate(i).toString() + " | "
                        + (list.isPremium(i) ? "Premium"
                        : "Standard") + " | "
                        + Integer.toString(list.getMonths(i)) + " | "
                        + list.getSubTotal(i).toString();
                int index = i;
                Button btnCustomer = new Button(str);
                btnCustomer.setOnAction(e -> {
                    try {
                        selectedPerson(index);
                    } catch (IndexException ex) {
                        getIndexAlert();
                    }
                });
                people[i] = btnCustomer;
                btnCustomer.setAlignment(Pos.CENTER);
                btnCustomer.setPrefWidth(368);
                customerList.getChildren().add(btnCustomer);
            }
        } catch (IndexException ex) {
            getIndexAlert();
        }

    }

    /**
     * Allows the user to sort to a specific attribute on the data display.
     */
    public void sortingAction() {
        String option = sortBy.getValue().toString();
        try {
            switch (option) {
                case "Name":
                    list.sortName();
                    populateCustomerList();
                    break;
                case "Total Money":
                    list.sortSubtotal();
                    populateCustomerList();
                    break;
                case "Total Months":
                    list.sortMonths();
                    populateCustomerList();
                    break;
                case "Age":
                    list.sortAge();
                    populateCustomerList();
                    break;
                default:
                    break;
            }
        } catch (IndexException ie) {
            getIndexAlert();
        }
    }

    /**
     * Clears all text fields in the add display, to give users ease.
     */
    public void clearButtonAction() {
        txtName.clear();
        birthday.setValue(null);
        txtMonths.clear();
        cmbSubsType.getSelectionModel().clearSelection();
    }

    /**
     * Finds the customer currently selected, according to the index, and sends
     * their specific information for the user to view.
     *
     * @param index the location of the customer in the array
     * @throws numberlist.IndexException
     */
    public void selectedPerson(int index) throws IndexException {
        personSelected = index;
        clearButtonAction();
        txtName.setText(list.getName(index));
        birthday.setValue(LocalDate.of(list.getBirthDate(index).getYear(),
                list.getBirthDate(index).getMonth(),
                list.getBirthDate(index).getDay()));
        cmbSubsType.setValue(list.isPremium(index) ? "Premium $12.00"
                : "Standard $9.00");
        txtMonths.setText(Integer.toString(list.getMonths(index)));
    }

    /**
     * An alert to warn the user there is an IndexException error. This usually
     * means there is an internal problem in the program.
     *
     * @return an Alert to catch an IndexException
     */
    private void getIndexAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Error");
        errorAlert.setContentText("Application Error. Please contact "
                + "developer.");
        errorAlert.showAndWait();
    }

    /**
     * An alert to warn the user there is an BirthDateException error. This
     * means the user incorrectly entered the date of birth, or user is under
     * the age of 13.
     *
     * @return an Alert to catch a BirthDateException
     */
    private void getBirthDateAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid birth date");
        errorAlert.setContentText(
                "You must be at least 13 years old.");
        errorAlert.showAndWait();
    }

    /**
     * An alert to warn the user there are fields with missing input, and
     * incomplete information cannot be sent to the database.
     *
     * @return an Alert to warn the users there are missing fields.
     */
    private void getMissingInputAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid Input");
        errorAlert.setContentText("Ensure all fields are filled out "
                + "with proper information.");
        errorAlert.showAndWait();
    }

    private void getEmptyListAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setHeaderText("List is empty");
        errorAlert.setContentText("List currently empty. Add customers "
                + "to continue.");
        errorAlert.showAndWait();
    }
}
