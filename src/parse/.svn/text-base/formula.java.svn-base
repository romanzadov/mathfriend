package parse;
import java.util.ArrayList;
import tree.*;

public class formula {

	
	int equal_location;
	int[] symbols;
	//int[] parenplaces;
	ArrayList<int[]>parenstructure = new ArrayList<int[]>();
	
	public ArrayList<Character> toarray(String st){
		ArrayList<Character> formula = new ArrayList<Character>();
		char[] form = st.toCharArray();
		for(int i=0;i<form.length;i++){
			formula.add(form[i]);
		}
		return formula;
	}
	
	public ArrayList<Character>  removespaces(ArrayList<Character> formula)
	{
		
		for(int i=0;i<formula.size();i++){
			if(Character.isSpaceChar(formula.get(i))==true)
			{
				formula.remove(i);
			}
		}
		return formula;
	}
	

	
	public void equals(ArrayList<Character> formula){
		int j = 0;
		
		for(int i=0;i<formula.size();i++)
		{if (formula.get(i)==61)
			{equal_location = i;
			j++;}
		if(j==2){
				System.out.println("too many equals");
				}	
		
		}
		
		
		
		
		
	}
	


	
	
	
	public void test(ArrayList<Character> formula){
		for(int i = 0;i<formula.size();i++)
		{
			System.out.print(formula.get(i));
			
		}
		System.out.println("");
		System.out.println("paren  ");
		for(int i = 0;i<parenstructure.size();i++)
		{
			System.out.println(parenstructure.get(i)[0]+"  "+parenstructure.get(i)[1]);
		}
			
	}
	
	
	
	
	
	
	
	
}
