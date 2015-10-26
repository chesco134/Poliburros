package org.jcapiz.poliburros.gps;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

public class CurrentLocation implements ConnectionCallbacks, OnConnectionFailedListener {

	Context context;
	public String mLatitudeText;
	public String mLongitudeText;
	private GoogleApiClient mGoogleApiClient;

	public CurrentLocation(){
	}
	
	public void SetmGoogleApiClient(GoogleApiClient Api){
		mGoogleApiClient = Api;
		
	}
	@Override
	public void onConnected(Bundle connectionHint) {
		
		if (mRequestingLocationUpdates) {
	        startLocationUpdates();
	    }
		
		Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
		if (mLastLocation != null) {
			mLatitudeText = (String.valueOf(mLastLocation.getLatitude()));
			mLongitudeText = (String.valueOf(mLastLocation.getLongitude()));
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	protected void startLocationUpdates() {
	    LocationServices.FusedLocationApi.requestLocationUpdates(
	            mGoogleApiClient, mLocationRequest, this);
	}
}