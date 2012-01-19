package pdm.pkg.myplayer;


import android.app.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyPlayerActivity extends Activity {
    /** Called when the activity is first created. */
    
    MediaPlayer mp; 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mp = MediaPlayer.create(MyPlayerActivity.this, R.raw.dst);
    
    
    Button StartButton1 = (Button)findViewById(R.id.button1);
    StartButton1.setOnClickListener(new OnClickListener() {
    	public void onClick(View v) {
    		mp.start();
    	}});
      		
  
    	    Button PauseButton2 = (Button)findViewById(R.id.button2);
    	    PauseButton2.setOnClickListener(new OnClickListener() {
    	    	public void onClick(View v) {
    	    		mp.pause();
    	    	
    	    }
    
    	    
    });
   }
    
    public void onDestroy(){
    	mp.release();
    }
 }	

