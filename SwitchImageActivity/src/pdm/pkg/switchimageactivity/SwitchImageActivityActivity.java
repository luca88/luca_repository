package pdm.pkg.switchimageactivity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class SwitchImageActivityActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        ToggleButton button1 = (ToggleButton)findViewById(R.id.toggleButton1);
        button1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton button1, boolean isChecked) {
				
				if(isChecked){
					iv.setImageResource(R.drawable.button_pause);
				}else{
					iv.setImageResource(R.drawable.button_play);
				}
			}
				// TODO Auto-generated method stub
				
			});

    
    }}
		