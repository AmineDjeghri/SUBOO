package calculator;

import bouchons.CalculatorBouchon;
import itf.ICalculator;

public class CalculatorSingleton {

	private static ICalculator calculator;
	
	public static ICalculator getInstance()
	{
		if(calculator==null)
			calculator = new Calculator();
		return calculator;
	}
}
