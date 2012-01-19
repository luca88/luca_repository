package pdm.pkg.dotoast;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DoToastActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	EditText edt ;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        
        LinearLayout ll = new LinearLayout(DoToastActivity.this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
         
        edt = new EditText(this);
        edt.setText("Saluta");
        edt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
   

        ll.addView(edt);
        setContentView(ll);
   
        
        
        Button btn =new Button(this);
         btn.setText("Saluta");
         btn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
         
         
         ll.addView(btn);
         setContentView(ll);
    
         
  
    }

	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		String iltesto = edt.getText().toString();
		Toast.makeText(getApplicationContext(), iltesto,Toast.LENGTH_LONG).show();
	}
    	
    	
    }
    
    
    
