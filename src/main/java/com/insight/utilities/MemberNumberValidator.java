package com.insight.utilities;

/**
 * Got the code source from this URL:
 * https://www.admfactory.com/luhn-algorithm-implementation-in-java/
 * @author nmccormick
 *
 */
public class MemberNumberValidator {

	/**
     * Checks if the card is valid
     * 
     * @param card
     *           {@link String} card number
     * @return result {@link boolean} true of false
     */
    public static boolean luhnCheck(String card) {
        if (card == null)
            return false;
        char checkDigit = card.charAt(card.length() - 1);
        String digit = calculateCheckDigit(card.substring(0, card.length() - 1));
        return checkDigit == digit.charAt(0);
    }
    
    /**
     * Corrects the last digit for the number provided.
     * 
     * @param card
     * @return
     */
    public static String getCorrectNumber(String card){
    	String toValidate = card.substring(0, card.length() - 1);
    	String digit = calculateCheckDigit(toValidate);
    	return toValidate + digit;
    }
    
    /**
     * Calculates the last digits for the card number received as parameter
     * 
     * @param card
     *           {@link String} number
     * @return {@link String} the check digit
     */
    public static String calculateCheckDigit(String card) {
        if (card == null)
            return null;
        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[card.length()];
        for (int i = 0; i < card.length(); i++) {
            digits[i] = Character.getNumericValue(card.charAt(i));
        }
        
        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2)	{
            digits[i] += digits[i];
            
            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }
        /* multiply by 9 step */
        sum = sum * 9;
        
        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }
    
    /**
     * Extracts the club number from the 16 digit member number. 
     * Will throw an exception if the member number isn't 16 characters long.
     * @param memberNumber
     * @return
     */
    public static String getClubFromMemberNumber(String memberNumber) throws Exception {
    	String clubNumber = "";
    	
    	if(memberNumber.length() != 16){
    		throw new Exception("The member number must be 16 digits long!");
    	}
    	
    	clubNumber = memberNumber.substring(0, 6);
    	// Had to do this crazy hack because substring(3,3) was returning a null string from the memberNumber.
    	clubNumber = clubNumber.replaceFirst(memberNumber.substring(0,3), "");
    	
    	return clubNumber;
    }
    
    /**
     * Demo of how to use the code set.
     * @param args
     */
//    public static void main(String[] args)	{
//        String pan1 = "429981129260380";
//        System.out.println("Cards luhn calculator");
//        System.out.println();
//        System.out.println("Calculate check digit for: " + pan1);
//        String digit = calculateCheckDigit(pan1);
//        System.out.println("Check digit: " + digit);
//        System.out.println("It should be: 2");
//        
//        String pan2 = "4012888888881881";
//        System.out.println("Validate pan number '" + pan2 + "': " + luhnCheck(pan2));
//    }
}