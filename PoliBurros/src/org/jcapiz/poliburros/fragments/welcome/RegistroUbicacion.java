package org.jcapiz.poliburros.fragments.welcome;

import org.fragancias.poliburros.R;
import org.jcapiz.poliburros.gps.CurrentLocation;

import com.google.android.gms.common.api.GoogleApiClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegistroUbicacion extends Fragment{

	CurrentLocation loc;
	GoogleApiClient mGoogleApiClient;
	
	public RegistroUbicacion(){
	}
	
	public void setMGoogleApiClient(GoogleApiClient Api){
		mGoogleApiClient=Api;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.gps_for_locacion_def_fragment, root,false);
		loc = new CurrentLocation(mGoogleApiClient);
		TextView res = ((TextView) rootView.findViewById(R.id.longi_lati));
		res.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				((TextView)view).append("\n"+loc.mLatitudeText+loc.mLongitudeText);
			}
		});
		return rootView;
	}
}
