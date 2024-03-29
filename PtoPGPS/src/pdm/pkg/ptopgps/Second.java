package pdm.pkg.ptopgps;

import android.app.Activity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Second extends Activity implements LocationListener {
	
	static final String tag = "Second"; // for Log

	TextView txtInfo1;
	TextView txtInfo2;
	TextView txtInfo3;
	TextView txtInfo4;
	LocationManager lm;
	StringBuilder sb;
	int noOfFixes = 0;
	float lat1;
	float lon1;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
     
        /* get TextView to display the GPS data */
		txtInfo1 = (TextView) findViewById(R.id.textView2);
		txtInfo2 = (TextView) findViewById(R.id.textView3);
		
		lat1 = getIntent().getExtras().getFloat("latitudine");
		lon1 = getIntent().getExtras().getFloat("longitudine");
		
		txtInfo3 = (TextView) findViewById(R.id.textView5);
		txtInfo4 = (TextView) findViewById(R.id.textView6);
						
		txtInfo3.setText("latitude: "+getIntent().getExtras().getFloat("latitudine"));
		txtInfo4.setText("longitude: "+getIntent().getExtras().getFloat("longitudine"));
		
		/* the location manager is the most vital part it allows access
		 * to location and GPS status services */
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		
	}

	@Override
	protected void onResume() {
		/*
		 * onResume is is always called after onStart, even if the app hasn't been
		 * paused
		 *
		 * add location listener and request updates every 1000ms or 10m
		 */
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		/* GPS, as it turns out, consumes battery like crazy */
		lm.removeUpdates(this);
		super.onPause();
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.v(tag, "Location Changed");

		sb = new StringBuilder(512);
		

		noOfFixes++;

		/* display some of the data in the TextView */
        /*
		sb.append("No. of Fixes: ");
		sb.append(noOfFixes);
		sb.append('\n');
		sb.append('\n');
        */
		sb.append("Londitude: ");
		sb.append(location.getLongitude());
		sb.append('\n');

		sb.append("Latitude: ");
		sb.append(location.getLatitude());
		sb.append('\n');

		sb.append("Altitiude: ");
		sb.append(location.getAltitude());
		sb.append('\n');

		sb.append("Accuracy: ");
		sb.append(location.getAccuracy());
		sb.append('\n');
        /*
		sb.append("Timestamp: ");
		sb.append(location.getTime());
		sb.append('\n');
        */
		txtInfo1.setText(sb.toString());
		
		float lat = (float) location.getLatitude(); 
		float lon = (float) location.getLongitude(); 
		
		txtInfo2.setText("Distance of Point:" + gps2m(lat,lon,lat1,lon1) + "Km");
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		/* this is called if/when the GPS is disabled in settings */
		Log.v(tag, "Disabled");

		/* bring up the GPS settings */
		Intent intent = new Intent(
				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.v(tag, "Enabled");
		Toast.makeText(this, "GPS Enabled", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		/* This is called when the GPS status alters */
		switch (status) {
		case LocationProvider.OUT_OF_SERVICE:
			Log.v(tag, "Status Changed: Out of Service");
			Toast.makeText(this, "Status Changed: Out of Service",
					Toast.LENGTH_SHORT).show();
			break;
		case LocationProvider.TEMPORARILY_UNAVAILABLE:
			Log.v(tag, "Status Changed: Temporarily Unavailable");
			Toast.makeText(this, "Status Changed: Temporarily Unavailable",
					Toast.LENGTH_SHORT).show();
			break;
		case LocationProvider.AVAILABLE:
			Log.v(tag, "Status Changed: Available");
			Toast.makeText(this, "Status Changed: Available",
					Toast.LENGTH_SHORT).show();
			break;
		}
	}

	@Override
	protected void onStop() {
		/* may as well just finish since saving the state is not important for this toy app */
		finish();
		super.onStop();
	}
	
	protected double gps2m(float lat_a, float lng_a, float lat_b, float lon_b) {
	    float pk = (float) (180/3.14169);

	    float a1 = lat_a / pk;
	    float a2 = lng_a / pk;
	    float b1 = lat_b / pk;
	    float b2 = lon_b / pk;

	    float t1 = FloatMath.cos(a1)*FloatMath.cos(a2)*FloatMath.cos(b1)*FloatMath.cos(b2);
	    float t2 = FloatMath.cos(a1)*FloatMath.sin(a2)*FloatMath.cos(b1)*FloatMath.sin(b2);
	    float t3 = FloatMath.sin(a1)*FloatMath.sin(b1);
	    double tt = Math.acos(t1 + t2 + t3);
	    
	    
	    return 6366*tt;
	}
}