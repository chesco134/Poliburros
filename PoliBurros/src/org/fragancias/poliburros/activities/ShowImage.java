package org.fragancias.poliburros.activities;

import org.fragancias.poliburros.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowImage extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_image);
		((ImageView)findViewById(R.id.image)).setBackgroundResource(getIntent().getExtras().getInt("image"));
	}
}
