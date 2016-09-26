package com.booting.activity;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import com.booting.database.DongengDB;
import android.view.View.OnClickListener;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  @author : Daniel FJP Napitupulu
 *  @Email  : if09033@gmail.com
 *  @Website: http://booting09.com
 */
public class AddActivity extends DashBoardActivity implements OnClickListener {
	DongengDB db = new DongengDB(this);
	TextView txtIsi, txtJudul;
	Button get_image;
	private String selectedImagePath;
	private static final int SELECT_PICTURE = 1;
	String DB_NAME    = "DongengDB";
	String TABLE_NAME = "tbl_m_dongeng";
	String _isi, _judul;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.add);
        setHeader(getString(R.string.AddActivityTitle), true, true);
     
        txtIsi    = (TextView) findViewById(R.id.txtIsi);
        txtJudul  = (TextView) findViewById(R.id.txtJudul);
        get_image = (Button) findViewById(R.id.get_image);
        get_image.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	int id = v.getId();
	    	switch (id) {
	    	 
	    	case R.id.get_image:
		    	Intent intent = new Intent();
		    	intent.setType("image/*");
		    	intent.setAction(Intent.ACTION_GET_CONTENT);
		    	startActivityForResult(
		    	Intent.createChooser(intent, "Select Picture"),
		    	SELECT_PICTURE);
		    	break;
	    	default:
	    		break;
    	}
    }
    
    public void btnResetClick(View v) {
    	txtIsi.setText("");
    	txtJudul.setText("");
    }
    
    public void btnSimpanClick(View v) {    
    	_isi   = txtIsi.getText().toString();
        _judul = txtJudul.getText().toString();
        
        if (_isi.length() == 0 && _judul.length() == 0) {
        	txtIsi.setError("Judul is required!!!");
        	txtJudul.setError("Isi is required!!!");
        } else if (_isi.length() == 0) {
        	txtIsi.setError("Judul is required!!!");
        } else if (_judul.length() == 0) {
        	txtJudul.setError("Isi is required!!!");
        } else {
        	saveInDB();
	        
	        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
	    	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
	    	startActivity(intent);
	    	finish();
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK) {
	    	if (requestCode == SELECT_PICTURE) {
		    	Uri selectedImageUri = data.getData();
		    	selectedImagePath    = getPath(selectedImageUri);
		    	get_image.setText("Image Path : " + selectedImagePath);
	    	}
    	}
    }
    	 
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor    = managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		
		return cursor.getString(column_index);
	}
		 
    void saveInDB() {
    	SQLiteDatabase myDb = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    	byte[] byteImage1 = null;
    	@SuppressWarnings("unused")
		String s = myDb.getPath();
    	 
    	//myDb.execSQL("delete from " + TABLE_NAME);
    	ContentValues newValues = new ContentValues();
    	newValues.put("Judul", _judul);
    	newValues.put("Isi", _isi);
    	
    	try {
	    	FileInputStream instream = new FileInputStream(selectedImagePath);
	    	BufferedInputStream bif  = new BufferedInputStream(instream);
	    	byteImage1 = new byte[bif.available()];
	    	bif.read(byteImage1);
	    	newValues.put("Image", byteImage1);
	    	@SuppressWarnings("unused")
			long ret = myDb.insert(TABLE_NAME, null, newValues);
    	} catch (IOException e) {
    		
    	}
    	
    	myDb.close();
    	Toast.makeText(this.getBaseContext(), "Successfully...", Toast.LENGTH_SHORT).show();
    }
}