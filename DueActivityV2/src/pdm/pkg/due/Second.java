package pdm.pkg.due;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Second extends Activity {
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
    
	TextView label = (TextView) findViewById(R.id.textView1);
	String iltestoricevuto = getIntent().getExtras().getString("iltestonelbox");
	label.setText(iltestoricevuto);
	}
	
	protected void onStart(){
		super.onStart();
		String str = "START";
		Log.d("XML",str);
	}
	
	protected void onRestart(){
		super.onRestart();
		String str = "RESTART";
		Log.d("XML",str);
	}
	protected void onResume(){
		super.onResume();
		String str = "RESUME";
		Log.d("XML",str);
	}
	protected void onPause(){
		super.onPause();
		String str = "PAUSE";
		Log.d("XML",str);
	}	
	protected void onStop(){
		super.onStop();
		String str = "STOP";
		Log.d("XML",str);
	}
	protected void onDestroy(){
		super.onStart();
		String str = "DESTROY";
		Log.d("XML",str);
	}
}

