package jn.usbquest;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_QUESTS = "quests";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_OBJECTIVES = "objectives";
	public static final String COLUMN_STEP1 = "step1";
	public static final String COLUMN_STEP2 = "step2";
	public static final String COLUMN_STEP3 = "step3";
	public static final String COLUMN_STEP4 = "step4";
	public static final String COLUMN_STEP5 = "step5";
	public static final String COLUMN_STEP6 = "step6";
	public static final String COLUMN_STEP7 = "step7";
	public static final String COLUMN_STEP8 = "step8";
	public static final String COLUMN_STEP9 = "step9";
	public static final String COLUMN_STEP10 = "step10";

	private static final String DATABASE_NAME = "questspec.db";
	private static final int DATABASE_VERSION = 3;
	private final Context myContext;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table if not exists "
			+ TABLE_QUESTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_TITLE
			+ " text not null, " + COLUMN_STATUS 
			+ " integer not null, " + COLUMN_OBJECTIVES
			+ " blob not null, " + COLUMN_STEP1
			+ " text," + COLUMN_STEP2
			+ " text," + COLUMN_STEP3
			+ " text," + COLUMN_STEP4
			+ " text," + COLUMN_STEP5
			+ " text," + COLUMN_STEP6
			+ " text," + COLUMN_STEP7
			+ " text," + COLUMN_STEP8
			+ " text," + COLUMN_STEP9
			+ " text," + COLUMN_STEP10
			+ " text" + ");";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.myContext = context;
	}
	
	private byte[] SerializeI(ArrayList<Integer> S){
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    DataOutputStream dout = new DataOutputStream(bout);
	    try{
	    	for (int l : S) {
	    		dout.write(l);
	    	}
	    	dout.close();
	    }catch(IOException s){
	    	System.err.println("Error trying to serialize while filling DB\n");
	    }
	    byte[] asBytes = bout.toByteArray();
	    return asBytes;
	}
	
	public void createQuest(String title, long status, ArrayList<String> steps, SQLiteDatabase database){
		
		ContentValues values = new ContentValues();
		values.put(COLUMN_TITLE, title);
		
		values.put(COLUMN_STATUS, status);
		
		ArrayList<Integer> objectives = new ArrayList<Integer>();
		for (String s : steps){
			//Set all objectives unavailable
			objectives.add(0);
		}
		
		byte[] ob = SerializeI(objectives);
		values.put(COLUMN_OBJECTIVES, ob);
		
		for(int i=0; i<steps.size(); i++){
			values.put("step"+(i+1), steps.get(i));
		}
		
		database.insert(TABLE_QUESTS, null, values);
	}

	public void autoFillQuests(SQLiteDatabase database){
	
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(myContext.getAssets().open("questsrows.txt")));
			String line = "";
			String title = "";
			ArrayList<String> steps= new ArrayList<String>();
			StringTokenizer st = null;
			
			while((line=br.readLine())!=null){
				st = new StringTokenizer(line,",");
				title = st.nextToken();
				
				while(st.hasMoreTokens()){
					steps.add(st.nextToken());
				}
				
				createQuest(title,1,steps,database);
				
				steps.clear();
				
			}
		}catch(Exception e){
			System.err.println("Exception while reading csv file: "+ e);
		}
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		autoFillQuests(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTS);
		onCreate(db);
	}

}