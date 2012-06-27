package jn.usbquest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class stats extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statsj);

		ImageButton masteries = (ImageButton) findViewById(R.id.pensumbutton);
		masteries.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), masteries.class);
				startActivityForResult(myintent, 0);
			}
		});

		ImageButton calendar = (ImageButton) findViewById(R.id.calendarbutton);
		calendar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), calendar.class);
				startActivityForResult(myintent, 0);
			}
		});

		ImageButton achive = (ImageButton) findViewById(R.id.achievementbutton);
		achive.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), achievements.class);
				startActivityForResult(myintent, 0);
			}
		});

		ImageButton changedata = (ImageButton) findViewById(R.id.changedatabutton);
		changedata.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), changedata.class);
				startActivityForResult(myintent, 0);
			}
		});

		ImageButton back = (ImageButton) findViewById(R.id.backbutton);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent();
				setResult(RESULT_OK, myintent);
				finish();
			}
		});
	}
}
