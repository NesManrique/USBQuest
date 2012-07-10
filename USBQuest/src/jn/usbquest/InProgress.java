package jn.usbquest;

import java.util.List;
import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class InProgress extends ListActivity {

	private questspecdao datasource;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inprogress);

		datasource = new questspecdao(this);
		datasource.open();
		
		ArrayList<String> S = new ArrayList<String>();
		S.add("Paso 1");
		S.add("Paso 2");
		S.add("Paso 3");
		
		questspec q = datasource.createQuest("Quest1", S);
		
		System.out.println(q);

		List<questspec> values = datasource.getAllQuests();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<questspec> adapter = new ArrayAdapter<questspec>(this,
				R.layout.questrow, values);
		setListAdapter(adapter);
		
		//datasource.deleteQuest(q);
	}
	
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}
