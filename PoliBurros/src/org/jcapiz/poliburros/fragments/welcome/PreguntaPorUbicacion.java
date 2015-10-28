package org.jcapiz.poliburros.fragments.welcome;

import org.fragancias.poliburros.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PreguntaPorUbicacion extends Fragment{
	
	public PreguntaPorUbicacion(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.locacion_chooser_fragment, parent,false);
		return rootView;
	}
}