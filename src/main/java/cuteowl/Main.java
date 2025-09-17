package cuteowl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";

    private final String filePath;
    private CuteOwl cuteOwl;

    // Existing constructor
    public Main(String filePath) {
        this.filePath = filePath;
    }

    // Overloaded constructor
    public Main() {
        this(DEFAULT_FILE_PATH);
    }

    @Override
    public void start(Stage stage) {
        cuteOwl = new CuteOwl(filePath);

        VBox dialogContainer = createDialogContainer();
        TextField userInput = createUserInputField();
        Button sendButton = createSendButton();
        ScrollPane scrollPane = createScrollPane(dialogContainer);
        HBox inputContainer = createInputContainer(userInput, sendButton);
        VBox mainLayout = createMainLayout(scrollPane, inputContainer);

        showWelcomeMessage(dialogContainer);
        setupEventHandlers(userInput, sendButton, dialogContainer);

        // Scene setup
        Scene scene = new Scene(mainLayout, 400, 500);
        stage.setTitle("CuteOwl Chatbot");
        stage.setScene(scene);
        stage.show();
    }

    private VBox createDialogContainer() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(10));
        return container;
    }

    private TextField createUserInputField() {
        TextField input = new TextField();
        input.setPromptText("Type your task here");
        return input;
    }

    private Button createSendButton() {
        return new Button("Send");
    }

    private ScrollPane createScrollPane(VBox dialogContainer) {
        ScrollPane scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        return scrollPane;
    }

    private HBox createInputContainer(TextField userInput, Button sendButton) {
        HBox inputBox = new HBox(10, userInput, sendButton);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER);
        return inputBox;
    }

    private VBox createMainLayout(ScrollPane scrollPane, HBox inputContainer) {
        VBox layout = new VBox(10, scrollPane, inputContainer);
        layout.setPadding(new Insets(10));
        return layout;
    }

    private void showWelcomeMessage(VBox dialogContainer) {
        String welcomeText = showWelcome();
        Label welcomeLabel = new Label(welcomeText);
        welcomeLabel.setWrapText(true);
        welcomeLabel.setStyle("-fx-background-color: #e0f7fa; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
        dialogContainer.getChildren().add(welcomeLabel);
    }

    private void setupEventHandlers(TextField userInput, Button sendButton, VBox dialogContainer) {
        sendButton.setOnAction(e -> handleUserInput(userInput, dialogContainer));
        userInput.setOnAction(e -> handleUserInput(userInput, dialogContainer));
    }

    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        String input = getTrimmedInput(userInput);
        if (input.isEmpty()) return;

        displayUserMessage(input, dialogContainer);
        displayBotResponse(input, dialogContainer);

        userInput.clear();
    }

    private String getTrimmedInput(TextField userInput) {
        return userInput.getText().trim();
    }

    private void displayUserMessage(String input, VBox dialogContainer) {
        Label userLabel = new Label("You: " + input);
        dialogContainer.getChildren().add(userLabel);
    }

    private void displayBotResponse(String input, VBox dialogContainer) {
        String response = getResponse(input);
        Label botLabel = new Label("CuteOwl: " + response);
        dialogContainer.getChildren().add(botLabel);
    }

    private void clearInput(TextField userInput) {
        userInput.clear();
    }


    private String getResponse(String input) {
        try {
            return cuteOwl.getResponse(input);
        } catch (Exception e) {
            return "Oops! Something went wrong: " + e.getMessage();
        }
    }


    private String showWelcome() {
        return "Welcome to CuteOwl!\n" +
                "Fly away with your tasks here.\n" +
                "Input your tasks whenever you're ready!";
    }

    public static void main(String[] args) {
        launch(args);
    }
}

