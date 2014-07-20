package tree.compound;

import java.math.BigInteger;

import tree.simple.Number;

public class FractionUtil {

	public static boolean isReducible(Fraction fraction) {
		try {
			Number a = (Number)(fraction.getNumerator());
			Number b = (Number)(fraction.getDenominator());
			if (getGcd(a.getValue(), b.getValue()) > 1) {
				return true;
			}
		} catch (ClassCastException e) {
			return false;
		}
		return false;
	}
	
	public static void reduceFraction(Fraction fraction) {
		Number a = (Number)(fraction.getNumerator());
		Number b = (Number)(fraction.getDenominator());
		int gcd = getGcd(a.getValue(), b.getValue());
		a.setvalue(a.getValue()/gcd);
		b.setvalue(b.getValue()/gcd);
	}

	private static int getGcd(double a, double b) {
		
		BigInteger b1 = new BigInteger(""+(int)a);
	    BigInteger b2 = new BigInteger(""+(int)b);
	    BigInteger gcd = b1.gcd(b2);
	    return gcd.intValue();
	}
}
