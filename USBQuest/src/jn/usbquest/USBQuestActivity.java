package jn.usbquest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

public class USBQuestActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageButton quests = (ImageButton) findViewById(R.id.questsbutton);
		quests.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), quests.class);
				startActivityForResult(myintent, 0);
			}
		});

		ImageButton survival = (ImageButton) findViewById(R.id.survivalbutton);
		survival.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), survival.class);
				startActivityForResult(myintent, 0);
			}
		});

		ImageButton stats = (ImageButton) findViewById(R.id.statsbutton);
		stats.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), stats.class);
				startActivityForResult(myintent, 0);
			}
		});

		ImageButton map = (ImageButton) findViewById(R.id.mapbutton);
		map.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myintent = new Intent(v.getContext(), map.class);
				startActivityForResult(myintent, 0);
			}
		});
	}
}