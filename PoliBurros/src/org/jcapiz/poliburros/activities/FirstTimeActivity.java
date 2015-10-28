package org.jcapiz.poliburros.activities;

import org.fragancias.poliburros.R;
import org.jcapiz.poliburros.fragments.welcome.PreguntaPorUbicacion;
import org.jcapiz.poliburros.fragments.welcome.RegistroUbicacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class FirstTimeActivity extends AppCompatActivity {
	
	private RegistroUbicacion reg;
	   
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
		}else{
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seller_profile);
		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction()
			.add(R.id.block_2,new PreguntaPorUbicacion(), "Pregutna por ubicaci�n")
			.commit();
		}
	}
	
	@Override
	protected void onStop(){
		if(reg != null)
			reg.disconnect();
		super.onStop();
	}
	/*
	@Override
	protected void onPause() {
	    super.onPause();
	    if( reg != null )
	    	reg.stopLocationUpdates();
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    if(reg != null )
	    if (reg.isGoogleApiClientConnected() && !reg.isRequestingLocationUpdates()) {
	        reg.startLocationUpdates();
	    }
	}
	*/
}
