package CalculationHandler;

import java.util.ArrayList;

public class CalculatorModel {

  public static Float calculation(ArrayList<CalculatorDTO> calculatorLogs) {
    Float firstNumber = null;
    String operator = null;

    for (int i = 0; i < calculatorLogs.size(); i++) {

      if (calculatorLogs.get(i).getType().equals("NUMBER")) {
        if (firstNumber == null) {
          firstNumber = Float.valueOf(calculatorLogs.get(i).getValue());
          System.out.println(firstNumber);


        } else {

          System.out.println(firstNumber);
          firstNumber=operations(firstNumber,operator,Float.parseFloat(calculatorLogs.get(i).getValue()));

        }

      } else if (calculatorLogs.get(i).getType().equals("OPERATOR")) {
        operator = String.valueOf(calculatorLogs.get(i).getValue());
        System.out.println(operator);
      }
    }
    return firstNumber;
  }
  public static Float operations(Float firstNumber, String operator,Float secondNumber){
    System.out.println("IN Operation method");
    Float result=null;
    switch(operator){
      case "ADD":  result = firstNumber+secondNumber;
        System.out.println(result);
        break;

      case "SUBTRACT":  result =firstNumber-secondNumber;
        System.out.println(result);
        break;

      case "MULTIPLY":  result =firstNumber*secondNumber;
        System.out.println(result);
        break;
      case "DIVIDE":  result =firstNumber/secondNumber;
        System.out.println(result);
        break;

    }
    return result;

  }
}
