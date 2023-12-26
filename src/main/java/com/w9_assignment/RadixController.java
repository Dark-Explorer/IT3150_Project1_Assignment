package com.w9_assignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class RadixController implements Initializable {
    ObservableList<String> Radix = FXCollections.observableArrayList("Binary", "Decimal", "Octal", "Hexadecimal");
    Map<String, Integer> radixToNum = new HashMap<>(){
        {
            put("Binary",2); put("Octal",8); put("Decimal",10); put("Hexadecimal",16);
        }
    };

    @FXML
    private Label inputTypeLabel;
    @FXML
    private Label outputTypeLabel1;
    @FXML
    private Label outputTypeLabel2;

    @FXML
    private TextField inputField;
    @FXML
    private TextField inputRadixField;
    @FXML
    private TextField outputField1;
    @FXML
    private TextField outputRadixField1;
    @FXML
    private TextField outputField2;
    @FXML
    private TextField outputRadixField2;

    @FXML
    private ComboBox<String> inputType;
    @FXML
    private ComboBox<String> outputType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputType.setValue("Binary");
        inputType.setItems(Radix);
        outputType.setValue("Decimal");
        outputType.setItems(Radix);
        inputField.setText("");
        inputTypeLabel.setText("Enter binary number");
        outputTypeLabel1.setText("Decimal number");
        outputTypeLabel2.setText("Decimal from signed 2's complement");
        inputRadixField.setText("2");
        outputRadixField1.setText("10");
        outputRadixField2.setText("10");
        inputType.valueProperty().addListener((observable, oldValue, newValue) -> handleInputOutput());
        outputType.valueProperty().addListener((observable, oldValue, newValue) -> handleInputOutput());
    }

    private int isInt (double number){
        if (number == (int) number) return 1;
        return 0;
    }

    private String converterChoosing(int inputRadix, String inputText, String outputTypeLabel){
        switch (inputRadix){
            case 2:
                switch (outputTypeLabel){
                    case "Octal number":
                        try {
                            double tmp = Converter.binToDec(inputText);
                            return Converter.decToOct(tmp);
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Decimal number":
                        try {
                            double result = Converter.binToDec(inputText);
                            if (isInt(result) == 1) return (int) result + "";
                            else return result + "";
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Hexadecimal number":
                        try {
                            double tmp1 = Converter.binToDec(inputText);
                            return Converter.decToHex(tmp1);
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Decimal from signed 2's complement":
                        try {
                            return Integer.toString(Converter.binToDec2Complement(inputText));
                        } catch (Exception e) {
                            outputField2.setText("N/A");
                            throw new IllegalArgumentException("Invalid");
                        }
                }
                break;
            case 8:
                switch (outputTypeLabel){
                    case "Binary number":
                        try {
                            double tmp = Converter.octToDec(inputText);
                            return Converter.decToBin(tmp);
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Decimal number":
                        try {
                            double result = Converter.octToDec(inputText);
                            if (isInt(result) == 1) return (int) result + "";
                            else return result + "";
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Hexadecimal number":
                        try {
                            double tmp1 = Converter.octToDec(inputText);
                            return Converter.decToHex(tmp1);
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                }
                break;
            case 10:
                switch (outputTypeLabel){
                    case "Binary number":
                        try {
                            return Converter.decToBin(Double.parseDouble(inputText));
                        } catch (NumberFormatException e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Octal number":
                        try {
                            return Converter.decToOct(Double.parseDouble(inputText));
                        } catch (NumberFormatException e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Hexadecimal number":
                        try {
                            return Converter.decToHex(Double.parseDouble(inputText));
                        } catch (NumberFormatException e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Hex from signed 2's complement":
                        try {
                            return Converter.decToHex2Complement(Integer.parseInt(inputText));
                        } catch (NumberFormatException e) {
                            outputField2.setText("N/A");
                            throw new IllegalArgumentException("Invalid");
                        }
                    case "Binary from signed 2's complement":
                        try {
                            return Converter.decToBin2Complement(Integer.parseInt(inputText));
                        } catch (NumberFormatException e) {
                            outputField2.setText("N/A");
                            throw new IllegalArgumentException("Invalid");
                        }
                }
                break;
            case 16:
                switch (outputTypeLabel){
                    case "Binary number":
                        try {
                            double tmp = Converter.hexToDec(inputText);
                            return Converter.decToBin(tmp);
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Decimal number":
                        try {
                            double result = Converter.hexToDec(inputText);
                            if (isInt(result) == 1) return (int) result + "";
                            else return result + "";
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Octal number":
                        try {
                            double tmp1 = Converter.hexToDec(inputText);
                            return Converter.decToOct(tmp1);
                        } catch (Exception e) {
                            inputField.setStyle("-fx-background-color: #ee818d");
                            throw new RuntimeException(e);
                        }
                    case "Decimal from signed 2's complement":
                        try {
                            return Integer.toString(Converter.hexToDec2Complement(inputText));
                        } catch (Exception e) {
                            outputField2.setText("N/A");
                            throw new IllegalArgumentException("Invalid");
                        }
                }
        }
        return "";
    }

    public void convertOnClicked() {
        if (handleInputOutput() == 0) inputField.setStyle("-fx-background-color: #ee818d");
        else {
            inputField.setStyle("-fx-background-color: #ffffff");
            int input = Integer.parseInt(inputRadixField.getText());
            outputField1.setText(converterChoosing(input, inputField.getText(), outputTypeLabel1.getText()));
            outputField2.setText(converterChoosing(input, inputField.getText(), outputTypeLabel2.getText()));
        }
    }

    public void resetOnClicked() {
        inputField.setText("");
        outputField1.setText("");
        outputField2.setText("");
    }

    public void swapOnClicked() {
        String tmp = outputType.getValue();
        outputType.setValue(inputType.getValue());
        inputType.setValue(tmp);
        inputField.setText(outputField1.getText());
        convertOnClicked();
    }

    public void switchOnClicked(){
        SceneManager.switchScene("ASCIIConverter.fxml");
    }

    public int handleInputOutput() {
        if (inputType.getValue().equals(outputType.getValue())) return 0;

        // Update Input and Output1 Labels and Radix fields
        inputTypeLabel.setText("Enter " + inputType.getValue() + " number");
        outputTypeLabel1.setText(outputType.getValue() + " number");
        outputRadixField1.setText(radixToNum.get(outputType.getValue()).toString());
        inputRadixField.setText(radixToNum.get(inputType.getValue()).toString());

        // Update Output2 Data type Label and Radix field
        switch (inputType.getValue()) {
            case "Binary", "Hexadecimal":
                if (outputType.getValue().equals("Decimal")) outputTypeLabel2.setText("Decimal from signed 2's complement");
                else outputTypeLabel2.setText("Decimal number");
                outputRadixField2.setText("10");
                break;
            case "Octal":
                if (outputType.getValue().equals("Decimal")) {
                    outputTypeLabel2.setText("Hexadecimal number");
                    outputRadixField2.setText("16");
                }
                else {
                    outputTypeLabel2.setText("Decimal number");
                    outputRadixField2.setText("10");
                }
                break;
            case "Decimal":
                switch (outputType.getValue()) {
                    case "Binary":
                        outputTypeLabel2.setText("Binary from signed 2's complement");
                        outputRadixField2.setText("2");
                        break;
                    case "Octal":
                        outputTypeLabel2.setText("Hexadecimal number");
                        outputRadixField2.setText("16");
                        break;
                    case "Hexadecimal":
                        outputTypeLabel2.setText("Hex from signed 2's complement");
                        outputRadixField2.setText("16");
                        break;
                }
                break;
        }
        return 1;
    }
}