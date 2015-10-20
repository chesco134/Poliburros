package org.jcapiz.poliburros.fragments.welcome;

import org.fragancias.poliburros.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RegistroUbicacion extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.gps_for_locacion_def_fragment, root,false);
		
		return rootView;
	}
}
