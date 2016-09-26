package com.booting.activity;

import com.booting.database.DongengDB;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 *  @author : Daniel FJP Napitupulu
 *  @Email  : if09033@gmail.com
 *  @Website: http://booting09.com
 */
public class EditActivity extends DashBoardActivity {
	DongengDB db = new DongengDB(this);
	TextView txtIsi, txtJudul;
	String id    = MainActivity.id;
	String judul = MainActivity.judul;
	String isi   = MainActivity.isi;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.add);
        setHeader(getString(R.string.EditActivityTitle), true, true);
     
        txtIsi   = (TextView) findViewById(R.id.txtIsi);
        txtJudul = (TextView) findViewById(R.id.txtJudul);
        
        txtJudul.setText(judul);
        txtIsi.setText(isi);
    }
    
    public void btnResetClick(View v) {
    	txtIsi.setText("");  
    	txtJudul.setText("");
    }
    
    public void btnSimpanClick(View v) {
    	String _isi   = txtIsi.getText().toString();
        String _judul = txtJudul.getText().toString();
        
        String[] data = new String[] {id, _judul, _isi }; 
        
        db.updateDongeng(data);
        
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
    	startActivity(intent);
    	finish();
    }
}