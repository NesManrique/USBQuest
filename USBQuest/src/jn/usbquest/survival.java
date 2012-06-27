package jn.usbquest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

public class survival extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.survival);

		TabHost m = getTabHost();

		m.addTab(m.newTabSpec("tips").setIndicator("Tips")
				.setContent(R.id.tab1));
		m.addTab(m.newTabSpec("notes").setIndicator("Notes")
				.setContent(R.id.tab2));

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