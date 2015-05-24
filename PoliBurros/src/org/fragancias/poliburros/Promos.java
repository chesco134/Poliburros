package org.fragancias.poliburros;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.anothercode.scanning_barcode.IntentIntegrator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Promos extends Fragment{

	public TextView qrChallenge;
	private static final String ARG_SECTION_NUMBER = "arg_section_number";
	private LinearLayout colors;
	private Activity activity;
	private static List<View> blocks = new ArrayList<View>(5);
	
	public Promos(){
		codigosColor.put("3a8beb6c1a12916eace5e82e7be41d797836b8c7bdf2ab10a1d3802005fc3896", Color.RED);
		codigosColor.put("a61a36528ba8e5fb0790232d925349b7d7a029bfece457abab4e046be0579070", Color.BLUE);
		codigosColor.put("4902b6d75136d9eb0b61330011d6e07a13a065cb83e5858e8ddbac1ce188e7ef", Color.GREEN);
		codigosColor.put("4289bb599219330b1e265166e5f334344b30ac8f9405caa56bfb977c3766617c", Color.MAGENTA);
		codigosColor.put("1c172fe438196c2a6bf1e9c030bb8abe7f2c753b9f7c32bb0f5bbd71230558ce", Color.YELLOW);
	}
	
	public LinearLayout getColorsContainer(){
		return colors;
	}
	
	public List<View> getBlocks(){
		return blocks;
	}
	
	public boolean addBlock(View block){
		if(blocks.size() == 5){
			return false;
		}else{
			blocks.add(block);
			try{
				colors.addView(block);
			}catch(NullPointerException e){
				reloadBlocks();
			}
			return true;
		}
	}
	
	private void reloadBlocks(){
		colors = (LinearLayout)getView().findViewById(R.id.color_blocks);
		for(View view : blocks){
			colors.addView(view);
		}
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		this.activity = activity;
	}
	
	public static Promos newInstance(int sectionNumber) {
		Promos fragment = new Promos();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstaceState){
		View rootView = inflater.inflate(R.layout.promos, parent, false);
		colors = (LinearLayout)rootView.findViewById(R.id.color_blocks);
		qrChallenge = (TextView)rootView.findViewById(R.id.trivia_qr);		
		qrChallenge.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				switch(arg1.getAction()){
				case MotionEvent.ACTION_DOWN:
					((TextView)arg0).setTextColor(getResources().getColor(R.color.ipn));
					break;
				case MotionEvent.ACTION_UP:
					((TextView)arg0).setTextColor(Color.GRAY);
					break;
				}
				return false;
			}
			
		});
		qrChallenge.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view){
				IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
				scanIntegrator.initiateScan();
			}
		});
		return rootView;
	}
	
	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		for(View view : blocks){
			colors.addView(view);
		}		
	}

	public static final Map<String,Integer> codigosColor = new TreeMap<String,Integer>();
}
