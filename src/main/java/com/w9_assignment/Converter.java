package com.w9_assignment;

import static java.lang.Math.*;

public class Converter {

    public static String decToBin (double decimal){
        // Phân biệt các trường hợp khi xử lí số nguyên hay số thập phân
        int int_part = (int) decimal;
        String result = "";
        if (int_part < 0) result += '-';
        result += Integer.toBinaryString(abs(int_part));

        // Đối với trường hợp số thập phân, xử lí thủ công
        double remain = abs(decimal - int_part);
        if (remain == 0) return result;
        else {
            result += ".";
            int next_digit;
            while (remain > 0){
                remain *= 2;
                next_digit = (int) remain;
                result += next_digit;
                remain -= next_digit;
            }
        }
        return result;
    }

    public static String decToOct (double decimal){
        int int_part = (int) decimal;
        String result = "";
        if (int_part < 0) result += '-';
        result += Integer.toOctalString(abs(int_part));

        double remain = abs(decimal - int_part);
        if (remain == 0) return result;
        else {
            result += ".";
            int next_digit;
            while (remain > 0){
                remain *= 8;
                next_digit = (int) remain;
                result += next_digit;
                remain -= next_digit;
            }
        }
        return result;
    }

    public static String decToHex (double decimal){
        int int_part = (int) decimal;
        String result = "";
        if (int_part < 0) result += '-';
        result += Integer.toHexString(abs(int_part)).toUpperCase();

        double remain = abs(decimal - int_part);
        if (remain == 0) return result;
        else {
            result += ".";
            int next_digit;
            while (remain > 0){
                remain *= 16;
                next_digit = (int) remain;
                if (next_digit < 10) result += next_digit;
                else result += (char) (next_digit + 55);
                remain -= next_digit;
            }
        }
        return result;
    }

    public static double binToDec (String binary){
        // Phân biệt các trường hợp khi xử lí số nguyên hay số thập phân
        if (binary.indexOf('.') == -1) return Integer.parseInt(binary, 2);
        int int_part = Integer.parseInt(binary.substring(0, binary.indexOf('.')), 2);

        int tmp = 1;
        if (int_part < 0) tmp = -1;
        double remain = 0;
        int counter = -1;
        for (int i = binary.indexOf('.') + 1; i < binary.length(); i++) {
            // Xử lí trường hợp người dùng nhập sai
            if (binary.charAt(i) != '0' && binary.charAt(i) != '1') throw new IllegalArgumentException("Invalid");
            if (binary.charAt(i) == '1') remain += tmp * pow(2, counter);
            counter--;
        }
        return int_part + remain;
    }

    public static double octToDec (String octal){
        if (octal.indexOf('.') == -1) return Integer.parseInt(octal, 8);
        int int_part = Integer.parseInt(octal.substring(0, octal.indexOf('.')), 8);

        int tmp = 1;
        if (int_part < 0) tmp = -1;
        double remain = 0;
        int counter = -1;
        for (int i = octal.indexOf('.') + 1; i < octal.length(); i++) {
            if (octal.charAt(i) < '0' || octal.charAt(i) > '7') throw new IllegalArgumentException("Invalid");
            remain += tmp * ((int) octal.charAt(i) - 48) * pow(8, counter);
            counter--;
        }
        return int_part + remain;
    }

    public static double hexToDec (String hexa){
        if (hexa.indexOf('.') == -1) return Integer.parseInt(hexa, 16);
        int int_part = Integer.parseInt(hexa.substring(0, hexa.indexOf('.')), 16);

        int tmp = 1;
        if (int_part < 0) tmp = -1;
        double remain = 0;
        int counter = -1;
        for (int i = hexa.indexOf('.') + 1; i < hexa.length(); i++) {
            if (hexa.charAt(i) >= '0' && hexa.charAt(i) <= '9')
                remain += tmp * ((int) hexa.charAt(i) - 48) * pow(16, counter);
            else
                if (hexa.charAt(i) >= 'A' && hexa.charAt(i) <= 'F') remain += tmp * ((int) hexa.charAt(i) - 55) * pow(16, counter);
                else throw new IllegalArgumentException("Invalid");
            counter--;
        }
        return int_part + remain;
    }

    public static String decToBin2Complement (int decimal){
        int max = 16;   //Min 4 digits
        while (max < 2 * abs(decimal)){
            max *= 16;  //Add more 4 digits
        }
        String result;
        if (decimal > 0) {
            result = Integer.toBinaryString(decimal);
            int missingNumber = (int) (log(max)/log(2) - result.length());
            for (int i = 0; i < missingNumber; i++) result = "0" + result;
        }
        else {
            result = Integer.toBinaryString(max - abs(decimal));
            int missingNumber = (int) (log(max)/log(2) - result.length());
            for (int i = 0; i < missingNumber; i++) result = "1" + result;
        }
        return result;
    }

    public static int binToDec2Complement (String binary){
        if (binary.charAt(0) == '0') return Integer.parseInt(binary, 2);
        else if (binary.charAt(0) != '1') throw new IllegalArgumentException("Invalid");
        else {
            int result = - (int) pow(2, binary.length() - 1);
            for (int i = 1; i < binary.length(); i++){
                if (binary.charAt(i) != '0' && binary.charAt(i) != '1') throw new IllegalArgumentException("Invalid");
                if (binary.charAt(i) == '1') result += pow(2, binary.length() - i - 1);
            }
            return result;
        }
    }

    public static String decToHex2Complement (int decimal){
        int max = 65536;   //Min 4 digits
        while (max < 2 * abs(decimal)){
            max *= 65536;  //Add more 4 digits
        }
        String result;
        if (decimal > 0) {
            result = Integer.toHexString(decimal).toUpperCase();
            int missingNumber = (int) (log(max)/log(16) - result.length());
            for (int i = 0; i < missingNumber; i++) result = "0" + result;
        }
        else {
            result = Integer.toHexString(max - abs(decimal)).toUpperCase();
            int missingNumber = (int) (log(max)/log(16) - result.length());
            for (int i = 0; i < missingNumber; i++) result = "F" + result;
        }
        return result;
    }

    public static int hexToDec2Complement (String hex){
        double tmp = Converter.hexToDec(hex);
        String hexToBin = Converter.decToBin(tmp);
        return binToDec2Complement(hexToBin);
    }

    public static String stringToNum (String text, String delimiter, int radix){
        String result = "";
        for (int i = 0; i < text.length(); i++){
            result += Integer.toString((int) text.charAt(i), radix).toUpperCase();
            result += delimiter;
        }
        if (result.length() != 0) result = result.substring(0, result.length() - delimiter.length());
        return result;
    }

    public static String checkSum_Sum (String text, int bit){
        int sum = 0;
        for (int i = 0; i < text.length(); i++) sum += text.charAt(i);
        String value = Integer.toHexString(sum).toUpperCase();
        if (value.length() < bit / 4) {
            while (bit / 4 != value.length()) value = "0" + value;
        }
        // Chỉnh sửa kết quả khi đưa ra màn hình theo số bit được chọn
        value = value.substring(value.length() - bit / 4);
        return value;
    }

    public static String checkSum_2complement (String text, int bit){
        int sum = 0;
        for (int i = 0; i < text.length(); i++) sum += text.charAt(i);
        double tmp = pow(2, bit);
        if (sum > tmp) sum -= (int) tmp;
        sum = (~sum);
        sum++;
        String value = Integer.toHexString(sum).toUpperCase();

        if (value.length() < bit / 4) {
            while (bit / 4 != value.length()) value = "F" + value;
        }
        value = value.substring(value.length() - bit / 4);
        return value;
    }

    public static String checkSum_XOR (String text, int bit) {
        int result = 0;
        for (int i = 0; i < text.length(); i++) result ^= text.charAt(i);
        String value = Integer.toHexString(result).toUpperCase();

        if (value.length() < bit / 4) {
            while (bit / 4 != value.length()) value = "0" + value;
        }
        value = value.substring(value.length() - bit / 4);
        return value;
    }
}