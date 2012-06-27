package jn.usbquest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class achievements extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.achievements);

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
