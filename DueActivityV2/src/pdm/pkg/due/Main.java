package pdm.pkg.due;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
    /** Called when the activity is first created. */
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText  text = (EditText) findViewById(R.id.editText1);
        
     
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
        	
          	public void onClick(View v) {
          		Intent intent = new Intent(Main.this,Second.class);
        		String iltesto = text.getText().toString();
          		intent.putExtra("iltestonelbox", iltesto);
          		startActivity(intent);
        	}
        });	
        
    }
}