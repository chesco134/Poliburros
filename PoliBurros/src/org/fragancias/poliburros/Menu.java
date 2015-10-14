package org.fragancias.poliburros;
// BELE BERGA LA BIDA.
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Menu extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
	}
	
	public static Menu newInstance(int sectionNumber) {
		Menu fragment = new Menu();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.menu, parent, false);
		return rootView;
	}
}
