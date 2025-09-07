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

        // Chat history container
        VBox dialogContainer = new VBox(10);
        dialogContainer.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);

        // Input field and send button
        TextField userInput = new TextField();
        userInput.setPromptText("Type your task here");
        Button sendButton = new Button("Send");

        HBox inputContainer = new HBox(10, userInput, sendButton);
        inputContainer.setPadding(new Insets(10));
        inputContainer.setAlignment(Pos.CENTER);

        // Main layout
        VBox mainLayout = new VBox(10, scrollPane, inputContainer);
        mainLayout.setPadding(new Insets(10));


        // Show welcome message
        String welcomeText = showWelcome();
        Label welcomeLabel = new Label(welcomeText);
        welcomeLabel.setWrapText(true);
        welcomeLabel.setStyle("-fx-background-color: #e0f7fa; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
        dialogContainer.getChildren().add(welcomeLabel);

        // Event handling
        sendButton.setOnAction(e -> handleUserInput(userInput, dialogContainer));
        userInput.setOnAction(e -> handleUserInput(userInput, dialogContainer));

        // Scene setup
        Scene scene = new Scene(mainLayout, 400, 500);
        stage.setTitle("CuteOwl Chatbot");
        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        String input = userInput.getText().trim();
        if (input.isEmpty()) return;

        // Display user message
        Label userLabel = new Label("You: " + input);
        dialogContainer.getChildren().add(userLabel);

        // Generate chatbot response
        String response = getResponse(input);
        Label botLabel = new Label("CuteOwl: " + response);
        dialogContainer.getChildren().add(botLabel);

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

