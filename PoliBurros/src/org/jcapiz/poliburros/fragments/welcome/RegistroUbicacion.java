package org.jcapiz.poliburros.fragments.welcome;

import org.fragancias.poliburros.R;
import org.jcapiz.poliburros.activities.FirstTimeActivity;
import org.jcapiz.poliburros.gps.LocationManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegistroUbicacion extends Fragment{
	
	private static final String REQUESTING_LOCATION_UPDATES_KEY = "requesting_location_updates";
	private static final String LOCATION_KEY = "location";
	private static final String LAST_UPDATED_TIME_STRING_KEY = "last_updated_time_string";
	private boolean mRequestingLocationUpdates;
	private TextView res;
	private LocationManager locationManager;
	
	public RegistroUbicacion(){
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.gps_for_locacion_def_fragment, root,false);
		res = ((TextView) rootView.findViewById(R.id.longi_lati));
		if( savedInstanceState == null )
			locationManager = new LocationManager((FirstTimeActivity) getActivity());
		else
			locationManager = new LocationManager(savedInstanceState);
		return rootView;
	}
	
	@Override
	public void onStart(){
		super.onStart();
		connect();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
				mRequestingLocationUpdates);
		savedInstanceState.putParcelable(LOCATION_KEY, locationManager.getMCurrentLocation());
		savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY,
				locationManager.getMLastUpdateTime());
	}
	
	public void appendMessage(String message){
		res.append(message);
	}

	public void connect() {
		locationManager.connect();
	}

	public void disconnect() {
		locationManager.disconnect();
	}

	public void stopLocationUpdates() {
		locationManager.stopLocationUpdates();		
	}

	public boolean isGoogleApiClientConnected() {
		return locationManager.isGoogleApiClientConnected();
	}

	public boolean isRequestingLocationUpdates() {
		return isRequestingLocationUpdates();
	}

	public void startLocationUpdates() {
		locationManager.startLocationUpdates();
	}
}