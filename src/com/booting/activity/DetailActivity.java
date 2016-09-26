package com.booting.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  @author : Daniel FJP Napitupulu
 *  @Email  : if09033@gmail.com
 *  @Website: http://booting09.com
 */
public class DetailActivity extends DashBoardActivity {
	String id    = MainActivity.id;
	String judul = MainActivity.judul;
	String isi   = MainActivity.isi;
	protected static ImageView image2;
	String DB_NAME    = "DongengDB";
	String TABLE_NAME = "tbl_m_dongeng";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.detail);
        setHeader(judul, true, true);
        
        image2 = (ImageView) findViewById(R.id.imageView2);
        TextView txtIsi = (TextView) findViewById(R.id.txtIsi);
        
        readFromDB();
        txtIsi.setText(isi);
    }   
    
    void readFromDB() {
    	byte[] byteImage2 = null;
    	SQLiteDatabase myDb;
    	myDb = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    	Cursor cur = myDb.rawQuery("SELECT Image FROM " + TABLE_NAME + " where Id = '" + id + "';", null); 
        
    	cur.moveToFirst();
    	
    	while (cur.isAfterLast() == false) {
	    	cur.moveToNext();
    	}
    	 
    	// TODO Read data from blob field
    	cur.moveToFirst();
    	byteImage2 = cur.getBlob(cur.getColumnIndex("Image"));
    	setImage(byteImage2);
    	cur.close();
    	myDb.close();
    }
    	 
    void setImage(byte[] byteImage2) {
    	image2.setImageBitmap(BitmapFactory.decodeByteArray(byteImage2, 0, byteImage2.length));
    }
}