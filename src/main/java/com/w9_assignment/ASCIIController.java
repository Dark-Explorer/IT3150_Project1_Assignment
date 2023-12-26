package com.w9_assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ASCIIController implements Initializable{
    @FXML
    private AnchorPane ap;
    private String choseDelimiter;

    ObservableList<String> delimiter = FXCollections.observableArrayList("None", "Space", "Comma", "User defined");
    ObservableList<String> numOfBits = FXCollections.observableArrayList("8 bit", "16 bit", "32 bit");
    ObservableList<String> typeOfCheckSum = FXCollections.observableArrayList("Sum", "2's complement", "XOR");
    @FXML
    private ComboBox<String> delimiterChoice;
    @FXML
    private ComboBox<String> checkSumBitChoice;
    @FXML
    private ComboBox<String> typeOfCheckSumChoice;

    @FXML
    private TextArea inputText;
    @FXML
    private TextArea hex;
    @FXML
    private TextArea binary;
    @FXML
    private TextArea decimal;

    @FXML
    private TextField checkSum;
    @FXML
    private TextField userDefined;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        delimiterChoice.setValue("Space");
        delimiterChoice.setItems(delimiter);
        checkSumBitChoice.setValue("8 bit");
        checkSumBitChoice.setItems(numOfBits);
        typeOfCheckSumChoice.setValue("Sum");
        typeOfCheckSumChoice.setItems(typeOfCheckSum);
        delimiterChoice.valueProperty().addListener((observable, oldValue, newValue) -> update());
        inputText.textProperty().addListener((observable, oldValue, newValue) -> update());
        userDefined.textProperty().addListener((observable, oldValue, newValue) -> update());
        checkSumBitChoice.valueProperty().addListener((observable, oldValue, newValue) -> update());
        typeOfCheckSumChoice.valueProperty().addListener((observable, oldValue, newValue) -> update());
    }

    private void update (){
        updateDelimiter();
        updateBinary();
        updateDecimal();
        updateHex();
        updateCheckSum();
    }

    private void updateDelimiter (){
        switch (delimiterChoice.getValue()){
            case "Space":
                userDefined.setEditable(false);
                choseDelimiter = " ";
                break;
            case "Comma":
                userDefined.setEditable(false);
                choseDelimiter = ",";
                break;
            case "None":
                userDefined.setEditable(false);
                choseDelimiter = "";
                break;
            case "User defined":
                userDefined.setEditable(true);
                choseDelimiter = userDefined.getText();
                break;
        }
    }

    public void switchOnClicked (){
        SceneManager.switchScene("RadixConverter.fxml");
    }

    private void updateBinary (){
        String tmp = Converter.stringToNum(inputText.getText(), choseDelimiter, 2);
        binary.setText(tmp);
    }
    private void updateDecimal (){
        String tmp = Converter.stringToNum(inputText.getText(), choseDelimiter, 10);
        decimal.setText(tmp);
    }

    private void updateHex (){
        String tmp = Converter.stringToNum(inputText.getText(), choseDelimiter, 16);
        hex.setText(tmp);
    }

    private void updateCheckSum(){
        switch (checkSumBitChoice.getValue()){
            case "8 bit":
                switch (typeOfCheckSumChoice.getValue()){
                    case "Sum":
                        checkSum.setText(Converter.checkSum_Sum(inputText.getText(), 8));
                        break;
                    case "2's complement":
                        checkSum.setText(Converter.checkSum_2complement(inputText.getText(), 8));
                        break;
                    case "XOR":
                        checkSum.setText(Converter.checkSum_XOR(inputText.getText(), 8));
                        break;
                }
                break;
            case "16 bit":
                switch (typeOfCheckSumChoice.getValue()){
                    case "Sum":
                        checkSum.setText(Converter.checkSum_Sum(inputText.getText(), 16));
                        break;
                    case "2's complement":
                        checkSum.setText(Converter.checkSum_2complement(inputText.getText(), 16));
                        break;
                    case "XOR":
                        checkSum.setText(Converter.checkSum_XOR(inputText.getText(), 16));
                        break;
                }
                break;
            case "32 bit":
                switch (typeOfCheckSumChoice.getValue()){
                    case "Sum":
                        checkSum.setText(Converter.checkSum_Sum(inputText.getText(), 32));
                        break;
                    case "2's complement":
                        checkSum.setText(Converter.checkSum_2complement(inputText.getText(), 32));
                        break;
                    case "XOR":
                        checkSum.setText(Converter.checkSum_XOR(inputText.getText(), 32));
                        break;
                }
                break;
        }
    }

    public void resetOnClicked(){
        inputText.setText("");
        checkSumBitChoice.setValue("8 bit");
        typeOfCheckSumChoice.setValue("Sum");
        delimiterChoice.setValue("Space");
        userDefined.setText("");
        userDefined.setEditable(false);
    }

    public void openFileOnClicked() {
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        FileChooser.ExtensionFilter textFilter = new FileChooser.ExtensionFilter("Text Filter", "*.txt");
        fileChooser.getExtensionFilters().add(textFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null){
            try {
                FileReader reader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line, result = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                    result += "\n";
                }
                result = result.substring(0, result.length() - 1);
                inputText.setText(result);
                reader.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
