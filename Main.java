package converter;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int sourceRadix = 1;
        int targetRadix = 1;
        String answer;
        int decimalNumber = 0;
        String wholeNum;
        String fractionNum = "";
        double fractionHolder = 0.0;

        Scanner input = new Scanner(System.in);

        // get the source radix and check input
        String userEntered = input.nextLine();
        System.out.println(userEntered + " user entered (source Radix)");
        if (!userEntered.matches("\\d+")) {
            printErrorAndExit();
        } else {
            sourceRadix = Integer.parseInt(userEntered);    
            if (sourceRadix < 1 || sourceRadix > 36) {
                printErrorAndExit();
            }
        }

        // get the number to convert and check input
        String orgNum = input.nextLine();
        String[] splitArr = orgNum.split("\\.");
        if (splitArr.length > 0) {
            wholeNum = splitArr[0];
        } else {
            wholeNum = orgNum;
        }
        if (splitArr.length > 1) {
            fractionNum = splitArr[1];
        }

        if (sourceRadix == 1) {
            decimalNumber = wholeNum.length();
        } else {
            //covert whole number to decimal
            try {
                decimalNumber = Integer.parseInt(wholeNum, sourceRadix);
            } catch (Exception e) {
                printErrorAndExit();
            }
        }

        // get target radix and check input
        String userEnteredTarget = input.nextLine();
        System.out.println(userEnteredTarget + " user entered (target radix)");
        if (!userEnteredTarget.matches("\\d+")) {
            printErrorAndExit();
        } else {
            targetRadix = Integer.parseInt(userEnteredTarget);
            if (targetRadix < 1 || targetRadix > 36) {
                printErrorAndExit();
            }
        }

       //convert fraction to decimal
       char[] holder = fractionNum.toCharArray();
       //convert any letters to numbers
       for (int i = 0; i < holder.length; i++) {
           if (holder[i] > 57) {
               holder[i] -= 87; //converts to a - 97 to 10
           } else {
               holder[i] -= 48;
           }
       }

       for (int i = 0; i < holder.length; i++) {
           int numerator = holder[i];
           fractionHolder += numerator / (Math.pow(sourceRadix, i + 1));
       }

       if (targetRadix == 1) {
           String one = "1";
           answer = one.repeat(decimalNumber);
       } else {
           answer = Integer.toString(decimalNumber, targetRadix);
       }
       if (fractionHolder != 0.0) {
           StringBuilder conversionFractionHolder = new StringBuilder(".");
           for (int i = 0; i < 5; i++) {
             double temp = fractionHolder * targetRadix;
             int nextDigit = (int)temp;
             if (nextDigit < 9) {
                 conversionFractionHolder.append(nextDigit);
             } else {
                 char letter = (char)(nextDigit + 87);
                 conversionFractionHolder.append(letter);
             }
             fractionHolder = temp - nextDigit;
           }
           answer += conversionFractionHolder.toString();
       }
       System.out.println(answer);
    }
    public static void printErrorAndExit() {
        System.out.println("Input error");
        System.exit(0);
    }
}
