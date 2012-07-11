package jn.usbquest;

import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class questspecdao {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_STATUS,
			MySQLiteHelper.COLUMN_OBJECTIVES,
			MySQLiteHelper.COLUMN_TITLE,
			MySQLiteHelper.COLUMN_STEP1,
			MySQLiteHelper.COLUMN_STEP2,
			MySQLiteHelper.COLUMN_STEP3,
			MySQLiteHelper.COLUMN_STEP4,
			MySQLiteHelper.COLUMN_STEP5,
			MySQLiteHelper.COLUMN_STEP6,
			MySQLiteHelper.COLUMN_STEP7,
			MySQLiteHelper.COLUMN_STEP8,
			MySQLiteHelper.COLUMN_STEP9,
			MySQLiteHelper.COLUMN_STEP10 };

	public questspecdao(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public questspec createQuest(String title, long status, ArrayList<String> steps) {
		
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_TITLE, title);
		
		values.put(MySQLiteHelper.COLUMN_STATUS, 0);
		
		ArrayList<Integer> objectives = new ArrayList<Integer>();
		for (String s : steps){
			//Set all objectives unavailable
			objectives.add(0);
		}
		byte[] ob = SerializeI(objectives);
		values.put(MySQLiteHelper.COLUMN_OBJECTIVES, ob);
		
		for(int i=0; i<steps.size(); i++){
			values.put("step"+(i+1), steps.get(i));
		}
		
		long insertId = database.insert(MySQLiteHelper.TABLE_QUESTS, null,
				values);
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_QUESTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		questspec newQuest = cursorToQuest(cursor);
		cursor.close();
		
		return newQuest;
	}
	
	public int updateQuest(questspec q) {
		
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_TITLE, q.getTitle());
		
		values.put(MySQLiteHelper.COLUMN_STATUS, q.getStatus());
		
		byte[] ob = SerializeI(q.getObjs());
		values.put(MySQLiteHelper.COLUMN_OBJECTIVES, ob);
		
		ArrayList<String> steps = q.getSteps();
		
		for(int i=0; i<steps.size(); i++){
			values.put("step"+(i+1), steps.get(i));
		}
		
		return database.update(MySQLiteHelper.TABLE_QUESTS, values, MySQLiteHelper.COLUMN_ID+"=?", 
				new String[]{String.valueOf(q.getId())});
		
	}


	public void deleteQuest(questspec quest) {
		long id = quest.getId();
		System.out.println("Quest deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_QUESTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public void deleteQuest(long id) {
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_QUESTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<questspec> getAllQuests() {
		List<questspec> quests = new ArrayList<questspec>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_QUESTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			questspec quest = cursorToQuest(cursor);
			quests.add(quest);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return quests;
	}
	
	public List<questspec> getInProgressQuests(){
		
		List<questspec> quests = new ArrayList<questspec>();

		Cursor cursor = database.rawQuery("SELECT * FROM quests WHERE status=2;",null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			questspec quest = cursorToQuest(cursor);
			quests.add(quest);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return quests;
	}
	
	public List<questspec> getAvailableQuests(){
		
		List<questspec> quests = new ArrayList<questspec>();

		Cursor cursor = database.rawQuery("SELECT * FROM quests WHERE status=1;",null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			questspec quest = cursorToQuest(cursor);
			quests.add(quest);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return quests;
	}

	private questspec cursorToQuest(Cursor cursor) {
		questspec quest = new questspec();
		quest.setId(cursor.getLong(0));
		quest.setTitle(cursor.getString(1));
		quest.setStatus(cursor.getLong(2));
		quest.setObj(UnserializeI(cursor.getBlob(3)));
		
		ArrayList<String> steps = new ArrayList<String>();
		
		for(int i=4; i<cursor.getColumnCount(); i++){
			steps.add(cursor.getString(i));
		}
		
		quest.setSteps(steps);
		
		return quest;
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
	    	System.err.println("Error trying to serialize integer\n");
	    }
	    byte[] asBytes = bout.toByteArray();
	    return asBytes;
	}
	
	private ArrayList<Integer> UnserializeI(byte[] S){
		ArrayList<Integer> obj = new ArrayList<Integer>();
		
	    ByteArrayInputStream bin = new ByteArrayInputStream(S);
	    DataInputStream din = new DataInputStream(bin);
	    try{
	    	for (int i = 0; i < S.length/4; i++) {
	    		obj.add(din.readInt());
	    	}
	    }catch(IOException s){
	    	System.err.println("Error trying to unserialize integer\n");
	    }
	    
	    return obj;
	}
}