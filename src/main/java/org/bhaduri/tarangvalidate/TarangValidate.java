/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.bhaduri.tarangvalidate;
import java.text.ParseException;

/**
 *
 * @author sb
 */
public class TarangValidate {

    public static void main(String[] args) throws ParseException {
//        System.out.println("Hello World!");
        int valueToRun = 0;
        if (args.length > 0) {
            try {
                // args[0] is the first argument passed from command line
                valueToRun = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Error: The argument '" + args[0] + "' is not a valid integer.");
                return; // Exit if input is bad
            }
        }
//        new FillValidateCall(61).updateValidatecall();
//        new FillValidateCall(2).updateValidatecall();
        // Now use the variable instead of the hardcoded number
        new FillValidateCall(valueToRun).updateValidatecall();
    }
}
