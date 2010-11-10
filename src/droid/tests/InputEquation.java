package droid.tests;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
  
public class InputEquation extends Activity {
	 Button b;
	 EditText myText;
	

	/** Called when the activity is first created. */
    
	@Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //test strings
        JavaTest jt = new JavaTest();
        jt.printTerms();
        //end test
        
        b = (Button)this.findViewById(R.id.st_button);
        myText = (EditText) this.findViewById(R.id.st);
        
        b.setOnClickListener(new OnClickListener() {
        	   public void onClick(View view){
        	    	
        	    	Intent intent = new Intent(InputEquation.this, DisplayThreeViews.class);
        	    	Bundle b = new Bundle();
        	    
        	    	b.putString("st", myText.getText().toString());
        	    	intent.putExtras(b);
        	    	startActivity(intent);
        	    	
        	    }
        });
    }
    
 
}