package org.jcapiz.poliburros.gps;

import java.text.DateFormat;
import java.util.Date;

import org.jcapiz.poliburros.activities.FirstTimeActivity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationManager implements ConnectionCallbacks,
		OnConnectionFailedListener, LocationListener {

	FirstTimeActivity activity;
	private static final String REQUESTING_LOCATION_UPDATES_KEY = "requesting_location_updates";
	private static final String LOCATION_KEY = "location";
	private static final String LAST_UPDATED_TIME_STRING_KEY = "last_updated_time_string";
	private boolean mRequestingLocationUpdates;
	public String mLatitudeText;
	public String mLongitudeText;
	private GoogleApiClient mGoogleApiClient;
	private LocationRequest mLocationRequest;
	private Location mCurrentLocation;
	private String mLastUpdateTime;
	private boolean mResolvingError = false;

	public LocationManager(FirstTimeActivity activity) {
		this.activity = activity;
		buildGoogleApiClient(activity);
	}
	
	public LocationManager(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			// Update the value of mRequestingLocationUpdates from the Bundle,
			// and
			// make sure that the Start Updates and Stop Updates buttons are
			// correctly enabled or disabled.
			if (savedInstanceState.keySet().contains(
					REQUESTING_LOCATION_UPDATES_KEY)) {
				mRequestingLocationUpdates = savedInstanceState
						.getBoolean(REQUESTING_LOCATION_UPDATES_KEY);
			}

			// Update the value of mCurrentLocation from the Bundle and update
			// the
			// UI to show the correct latitude and longitude.
			if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
				// Since LOCATION_KEY was found in the Bundle, we can be sure
				// that
				// mCurrentLocationis not null.
				mCurrentLocation = savedInstanceState
						.getParcelable(LOCATION_KEY);

				// Update the value of mLastUpdateTime from the Bundle and update
				// the UI.
				if (savedInstanceState.keySet().contains(
						LAST_UPDATED_TIME_STRING_KEY)) {
					mLastUpdateTime = savedInstanceState
							.getString(LAST_UPDATED_TIME_STRING_KEY);
				}
				updateUI();
			}
		}
	}

	protected synchronized void buildGoogleApiClient(Activity activity) {
		mGoogleApiClient = new GoogleApiClient.Builder(activity)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	protected void createLocationRequest() {
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(10000);
		mLocationRequest.setFastestInterval(5000);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		String message;
		Location mLastLocation = LocationServices.FusedLocationApi
				.getLastLocation(mGoogleApiClient);
		if (mLastLocation != null) {
			message = "\nLastKnownLatitude: "
					+ (String.valueOf(mLastLocation.getLatitude()))
					+ "\nLast Known Longitude: "
					+ (String.valueOf(mLastLocation.getLongitude()));
		} else
			message = "\nWe do not have a last known location.";
		activity.appendMessage(message);
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Toast.makeText(activity, "u.u\n" + arg0.toString(), Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		Toast.makeText(activity, "u.u", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onLocationChanged(Location location) {
		mCurrentLocation = location;
		mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
		updateUI();
	}
	
	public Location getMCurrentLocation(){
		return mCurrentLocation;
	}

	public String getMLastUpdateTime() {
		return mLastUpdateTime;
	}
	
	public void setMCurrentLocation(Location location){
		mCurrentLocation = location;
	}

	public void startLocationUpdates() {
		LocationServices.FusedLocationApi.requestLocationUpdates(
				mGoogleApiClient, mLocationRequest, this);
	}

	public void stopLocationUpdates() {
		LocationServices.FusedLocationApi.removeLocationUpdates(
				mGoogleApiClient, this);
	}
	
	public void disconnect(){
        mGoogleApiClient.disconnect();
	}

	public boolean isGoogleApiClientConnected() {
		return mGoogleApiClient.isConnected();
	}
	
	public void connect(){
        if (!mResolvingError) {  // more about this later
            mGoogleApiClient.connect();
        }
	}

	private void updateUI() {
		String message = "\nLastKnownLatitude: "
				+ (String.valueOf(mCurrentLocation.getLatitude()))
				+ "\n Last Known Longitude: "
				+ (String.valueOf(mCurrentLocation.getLongitude()))
				+ "\nUpdated at: " + mLastUpdateTime;
		activity.appendMessage(message);
	}
}