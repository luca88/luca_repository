package pdm.pkg.ptopgps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
	float number1;
	float number2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       
        
    final EditText text1 = (EditText) findViewById(R.id.editText1);
    final EditText text2 = (EditText) findViewById(R.id.editText2);
        
    Button startButton = (Button) findViewById(R.id.button1);
        
    startButton.setOnClickListener(new OnClickListener(){
    	public void onClick(View v) {
       		Intent intent = new Intent(Main.this, Second.class);
       	   
       	
       		number1 = Float.valueOf(text1.getText().toString());
       		number2 = Float.valueOf(text2.getText().toString());
       		       		
       		intent.putExtra("latitudine",number1);
       		intent.putExtra("longitudine",number2);
       		
        	startActivity(intent);
        	}
        });
    }
    /*
    public class position {
    	float lat;
    	float lon;
    	public position(float uno, float due){lat=uno;lon=due;}
    	public float getlan(){return(lat);}
    	public float getlon(){return(lon);}
   
    }
    */	
}