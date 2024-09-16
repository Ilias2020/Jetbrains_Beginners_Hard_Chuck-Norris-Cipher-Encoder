import java.util.Scanner;

public class Main {

    public static String chuckCodeToBinaryString(String str) {
        String binStr = "";
        int len = str.length();
        for (int i = 0; i < len - 1; i++) {
            // System.out.println(i);
            char num1 = str.charAt(i);
            char num2 = str.charAt(i + 1);

            if (num1 == '0' && num2 == ' ') {
                int k;
                for (k = i + 2; k < len; k++) {
                    if (str.charAt(k) == '0') {
                        binStr += "1";

                    } else {
                        break;
                    }
                }
                i = k;
            } else if (num1 == '0' && num2 == '0') {
                int k;
                for (k = i + 3; k < len; k++) {
                    if (str.charAt(k) == '0') {
                        binStr += "0";
                    } else {
                        break;
                    }
                }
                i = k;
            }
        }
        if (binStr.length() % 7 != 0) {

        }
        return binStr;
    }

    public static String binaryStringToDecodedMessage(String str) {
        String[] strArr = str.split("(?<=\\G.{7})");
        String strFinal = "";
        for (int i = 0; i < strArr.length; i++) {
            strFinal = String.format("%s%c", strFinal, (char) Integer.parseInt(strArr[i], 2));
        }
        return strFinal;
    }

    public static String encodeOneString(String arr) {
        String str = "";
        for (int k = 0; k < arr.length(); ) {
            //System.out.println("k= " + k);
            if (arr.charAt(k) == '1') {

                str += "0 ";
                for (int n = k; n < arr.length(); n++) {
                    //System.out.println("n= " + n);
                    if (arr.charAt(n) == '1') {

                        str += "0";
                        //System.out.println("str= " + str + " n= " + n);
                        k++;
                    } else if (arr.charAt(n) == '0') {
                        //k = n - 1;
                        //System.out.println("k2 = " + k);
                        str += " ";
                        //System.out.println("break 1");
                        break;
                    }
                }
            } else {

                str += "00 ";
                for (int d = k; d < arr.length(); d++) {
                    //System.out.println("d= " + d);
                    if (arr.charAt(d) == '1') {

                        //k = d - 1;
                        //System.out.println("k2 = " + k);
                        str += " ";
                        //System.out.println("break 0");
                        break;


                    } else {
                        str += "0";
                        //System.out.println("str= " + str + " d= " + d);
                        k++;
                    }
                }
            }
        }
        return str;
    }

    public static void encode() {
        System.out.println("Input string:");
        String input = new Scanner(System.in).nextLine();
        String str = "";

        char[] inputAscii = input.toCharArray();

        String inputBin = "";
        for (int i = 0; i < inputAscii.length; i++) {
            //System.out.println(inputAscii[i]);
            //minis = String.format("%7s", Integer.toBinaryString(ch)).replace(" ", "0");
            String str2 = Integer.toBinaryString(inputAscii[i]);
            if (str2.length() == 6) {
                str2 = "0" + str2;
            }
            inputBin += str2;
            //System.out.println("inputBin " + inputBin);
        }
        str = encodeOneString(inputBin);

        System.out.printf("Encoded string:%n%s%n", str);
    }

    public static void decode() {
        System.out.println("Input encoded string:");
        String input = new Scanner(System.in).nextLine();
        try {
            String str = chuckCodeToBinaryString(input);
            if (str.length() % 7 != 0) {
                System.out.println("Encoded string is not valid.");
            } else {
                System.out.printf("Decoded string:%n%s%n", binaryStringToDecodedMessage(str));
            }
        } catch (Exception e) {
            System.out.println("Encoded string is not valid.");
        }
        /*String str = chuckCodeToBinaryString(input);
        if (str.length() % 7 != 0) {
            System.out.println("Encoded string is not valid.");
        } else {
            System.out.printf("Decoded string:%n%s%n", binaryStringToDecodedMessage(str));
        }*/

        /*if (decodeError(input) == false) {
            System.out.println("Encoded string is not valid.");
        } else {
            System.out.printf("Decoded string:%n%s%n", binaryStringToDecodedMessage(chuckCodeToBinaryString(input)));
        }*/
    }

    /*public static boolean decodeError(String input) {
        boolean flagError = true;
        String[] inputArr = input.split(" ");

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '0' || input.charAt(i) != ' ') {
                flagError = false;
                break;
            }
        }
        flagError = inputArr.length % 2 != 0 ? false : flagError;
        for (int j = 0; j < inputArr.length; j += 2) {
            if (inputArr[j].length() > 2) {
                flagError = false;
                break;
            }
        }
        flagError = chuckCodeToBinaryString(input).length() % 7 == 0 ? flagError : false;
        return flagError;
    }*/

    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("Please input operation (encode/decode/exit):");
            String input = new Scanner(System.in).nextLine();
            switch (input) {
                case "encode":
                    encode();
                    break;
                case "decode":
                    decode();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    flag = false;
                    break;
                default:
                    System.out.printf("There is no '%s' operation%n", input);
            }
        }
    }
}
