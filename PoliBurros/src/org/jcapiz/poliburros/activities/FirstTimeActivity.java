package org.jcapiz.poliburros.activities;

import org.fragancias.poliburros.R;
import org.jcapiz.poliburros.fragments.welcome.PreguntaPorUbicacion;
import org.jcapiz.poliburros.fragments.welcome.RegistroUbicacion;
import org.jcapiz.poliburros.gps.LocationManager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FirstTimeActivity extends AppCompatActivity {
	
	private RegistroUbicacion reg;
	private LocationManager locationManager;
	   
	public void si(View view){
		reg = new RegistroUbicacion();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.block_2, reg)
		.addToBackStack("Regitro_Ubicacion")
		.commit();
	}
	
	public void no(View view){
		
	}
	
	public void appendMessage(String message){
		if(reg != null){
			reg.appendMessage(message);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seller_profile);
		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction()
			.add(R.id.block_2,new PreguntaPorUbicacion(), "Pregutna por ubicación")
			.commit();
			locationManager = new LocationManager(this);
		}else
			locationManager.updateValuesFromBundle(savedInstanceState);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    locationManager.stopLocationUpdates();
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    if (locationManager.isGoogleApiClientConnected() && !locationManager.isRequestingLocationUpdates()) {
	        locationManager.startLocationUpdates();
	    }
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState){
	    super.onSaveInstanceState(outState);
	    locationManager.onSaveInstanceState(outState);
	}
}
