package parse;
import java.util.ArrayList;
import tree.*;
import tree.simple.simpleterm;

public class parenthesize {

//	
	public int n=0;
	public ArrayList<int[]> organize(ArrayList<simpleterm> formula){
		
	
		
		ArrayList<int[]> parenstructure = new ArrayList<int[]>();
		int[] parenplaces = new int[formula.size()];

		for(int i=0; i< formula.size();i++){
			if(formula.get(i).equals('(') ==true)
			{
				parenplaces[i]=1;  //left parentheses
			}
			if(formula.get(i).equals(')') ==true)
			{
				parenplaces[i]=2; //right parenthes
			}
		}
		boolean done = false;
		while (done ==false){
			for(int i=0; i< formula.size();i++){
			
				int left;
				int right;
				if(parenplaces[i]==1)
				{
					left = i;
					for(int j = i; j<formula.size();j++)
					{
						if(parenplaces[j]==0){}
						if(parenplaces[j]==1){left = j;}
						if(parenplaces[j]==2)
						{right = j;
						int[] ends	 = {left,right};
						parenstructure.add(n,ends);
						parenplaces[right]=0;
						parenplaces[left]=0;
						n++;
						left = 0;
						right = 0;
						j = formula.size();
						}


					}
				}
				int sum = 0;
				for(int k=0; k< formula.size();k++)
				{
					sum+=parenplaces[k];
				}
				if(sum==0){done = true;}

			}
		}
		
	
	

		return parenstructure;

	}
	
	
	
}
