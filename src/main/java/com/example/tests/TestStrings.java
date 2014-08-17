package com.example.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStrings {
	
	final static String[] equations = {
		"2x",
		"2x-5",
		"-5+2x",
		"-5-3x",
		"-3x-5",
		"-2/x"
	};

	final static String[] integerAddition = {

		//test integer addition
        "5",
        "x",
        "-2",
		"-4+5=1",
		"-4-6=-10",
		"4-7=-3",
		"3+3x-5=-2+3x",
		"2-5+3=-3+3"
	
	};
	final static String[] decimalAddition = {
		//test decimal addition
		"1.1+2.3=3.4",
		"1-5.4=-4.4",
		"2-e=1.7"

	};

	final static String[] integerMultiplication = {

		//test integer multiplication
		"4*5=20",
		"2*x*7*6=14*x*6",
		"2/3*5*1/3*7=2/3*35*1/3",
		"-3*4=-12",
		"2*e=2.9",
		"-2*-2.1=4.2",
		"-2*3.2=-6.4",
		"(1)*(12)=12"

	};

	final static String[] fractionMultiplication = {

		//test fraction multiplication
		"1/2*3/4=(1*3)/(2*4)",
		"1/2*3/4*5/6=(1*3)/(2*4)*5/6",
		"1/2*3=(1*3)/2",
		"4.1*5/2.3=(4.1*5)/2.3",
		"3/4*2=(3*2)/4",
		"3x/5*4=12x/5",
		"1/3*2x/5=(1*2)/3*x/5",
		"(3x-5)/(2y-1)*(3x)/2=((3x-5)*(3x))/((2y-1)*2)"
	};

	final static String[] decimalDivision = {

		"6/3=2",
		"-6/2=-3",
		"1.5/.1=15"
	};

    final static String[] exponents = {
        "2^3=8",
        "2^(-3)=1/8",
        "-3^2=-9",
        "(-3)^2=9"
    };

	public static ArrayList<String> getStrings(){

		ArrayList<String> all = new ArrayList<String>();

		List<String> equations = Arrays.asList(integerAddition);
		all.addAll(equations);

		equations = Arrays.asList(decimalAddition);
		all.addAll(equations);

		equations = Arrays.asList(integerMultiplication);
		all.addAll(equations);

		equations = Arrays.asList(fractionMultiplication);
		all.addAll(equations);

		equations = Arrays.asList(decimalDivision);
		all.addAll(equations);

        equations = Arrays.asList(exponents);
        all.addAll(equations);

		return all;

	}

    public static List<String> getResults() {
        return Arrays.asList(results);
    }

	final static String[] results = {

            "5",
            "x",
            "2",
            "[-4]+[5]=[1]",
            "[-4]+[-6]=[-10]",
            "[4]+[-7]=[-3]",
            "[3]+[3]*[x]+[-5]=[-2]+[3]*[x]",
            "[2]+[-5]+[3]=[-3]+[3]+",
            "[1.1]+[2.3]=[3.4]",
            "[1]+[-5.4]=[-4.4]",
            "[2]+[-e]=[1.7]",
            "[4]*[5]=[20]",
            "[2]*[x]*[7]*[6]=[14]*[x]*[6]",
            "[2]*[1/3]*[5]*[1]*[1/3]*[7]=[2]*[1/3]*[35]*[1]*[1/3]*",
            "[-3]*[4]=[-12]",
            "[2]*[e]=[2.9]",
            "[-2]*[-2.1]=[4.2]",
            "[-2]*[3.2]=[-6.4]",
            "[(1)]*[(12)]=[12]",
            "[1]*[1/2]*[3]*[1/4]=([1]*[3])*1/([2]*[4])",
            "[1]*[1/2]*[3]*[1/4]*[5]*[1/6]=([1]*[3])*1/([2]*[4])*[5]*[1/6]",
            "[1]*[1/2]*[3]=([1]*[3])*[1/2]",
            "[4.1]*[5]*[1/2.3]=([4.1]*[5])*[1/2.3]",
            "[3]*[1/4]*[2]=([3]*[2])*[1/4]",
            "[3]*[x]*[1/5]*[4]=[12]*[x]*[1/5]",
            "[1]*[1/3]*[2]*[x]*[1/5]=([1]*[2])*[1/3]*[x]*[1/5]",
            "([3]*[x]+[-5])*1/([2]*[y]+[-1])*([3]*[x])*[1/2]=(([3]*[x]+[-5])*([3]*[x]))*1/(([2]*[y]+[-1])*[2])",
            "[6]*[1/3]=[2]",
            "[-6]*[1/2]=[-3]",
            "[1.5]*[1/0.1]=[15]",
            "[2]^[3]=[8]",
            "[2]^[-(3)]=[1]*[1/8]",
            "-[3]^[2]=[-9]",
            "[-(3)]^[2]=[9]"
    };
	
	
}
