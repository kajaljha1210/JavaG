import java.util.Scanner;
import java.util.Calendar;

public class ChequePrinter {
    private static final String[] zeroToTeen = {
            "zero", "One", "Two", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Forteen", "/fifteen", "Sixteen", "/seventeen", "Eighteen", "Nineteen"
    };
    private static final String[] twentyToNinety = {
            "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    // wrapper method to convert all numbers
    public static String ConvertToWords(int n) {
        if (n <= 0 || n > 999999999)
            return "***INVALID AMOUNT***";
        String stg = null;
        String stg1 = null;
        String stg2 = null;
        String stg3 = null;
        if (n / 10000000 != 0) {
            stg = ConvertUpto99(n / 10000000) + " Crore";
            n %= 10000000;
        }
        if (n / 100000 != 0) {
            stg1 = ConvertUpto99(n / 100000) + " Lakh";
            if (stg != null)
                stg = stg + " " + stg1;
            else
                stg = stg1;
            n %= 100000;
        }
        if (n / 1000 != 0) {
            stg2 = ConvertUpto99(n / 1000);
            stg2 = stg2 + "Thousand";
            if (stg != null)
                stg = stg + " " + stg2;
            else
                stg = stg2;

            n %= 1000;
        }
        if (n % 1000 != 0) {
            stg3 = convertUpto999(n % 1000);
            if (stg != null)
                stg = stg + " " + stg3;
            else
                stg = stg3;
        }
        return stg;

    }

    // method to convert numbers upto 999
    private static String convertUpto999(int n) {
        String stg1 = zeroToTeen[n / 100] + " Hundred";
        String stg2 = ConvertUpto99(n % 100);
        if (n <= 99)
            return stg2;
        else if (n % 100 == 0)
            return stg1;
        else
            return stg1 + " " + stg2;
    }

    // method to convert numbers upto 99
    private static String ConvertUpto99(int n) {
        if (n < 20)
            return zeroToTeen[n];
        String stg = twentyToNinety[n / 10 - 2];
        if (n % 10 == 0)
            return stg;
        return stg + " " + zeroToTeen[n % 10];
    }

    // method to print cheque
    private static void PrintCheque(int amount, String payee) {
        String amountInString;
        String ampuntInWords = ConvertToWords(amount);

        if (ampuntInWords.startsWith("***"))
            amountInString = "******";
        else
            amountInString = String.valueOf(amount);
        Calendar cal = Calendar.getInstance();
        String strDate = cal.get(Calendar.DATE) + "/" +
                (cal.get(Calendar.MONTH) + 1) +
                "/" + cal.get(Calendar.YEAR);
        String allstrs = "*********************************************************";
        System.out.println(allstrs);
        System.out.println("*--------------------   *");
        System.out.println("*A/C Payee OnlyAxis Bank Ltd.Non Negotiable       *");
        System.out.println("*      ------------- -------------*");
        System.out.println("* Pay: " + payee);
        System.out.println("*");
        System.out.println("* Rupees " + ampuntInWords);
        PrintSpaces(allstrs.length() - ampuntInWords.length() - 11);
        System.out.println("*");
        System.out.println("*              \u20B9 "
                + amountInString + "/-");
        PrintSpaces(allstrs.length() - amountInString.length() - 69);
        System.out.println("*");
        System.out.println("*   ----------- *");
        System.out.println("*  | A/C No. | 9653045454 |               *");
        System.out.println("*--------------------    *");
        System.out.println("*--------------------   *");
        System.out.println("*    IFS Code XDFCA944993");
        System.out.println(allstrs);
    }

    // Method to print spaces
    private static void PrintSpaces(int n) {
        for (int i = 1; i <= n; i++)
            System.out.println(" ");
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Cheque Amount");
        int amount = input.nextInt();
        input.nextLine(); // consume end-of-line character
        System.out.println("Enter Payee Name:  ");
        String payee = input.nextLine();
        System.out.println();
        PrintCheque(amount, payee.trim());
    }
}
