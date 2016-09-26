package com.booting.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;

/**
 *  @author : Daniel FJP Napitupulu
 *  @Email  : if09033@gmail.com
 *  @Website: http://booting09.com
 */
public abstract class DashBoardActivity extends Activity {
	// Constant for identifying the dialog
	private static final int DIALOG_ALERT = 10; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    public void setHeader(String title, boolean btnHomeVisible, boolean btnFeedbackVisible) {
    	ViewStub stub = (ViewStub) findViewById(R.id.vsHeader);
    	View inflated = stub.inflate();
          
    	TextView txtTitle = (TextView) inflated.findViewById(R.id.txtHeading);
    	txtTitle.setText(title);
    		
    	Button btnHome = (Button) inflated.findViewById(R.id.btnHome);
    	if(!btnHomeVisible)
    		btnHome.setVisibility(View.INVISIBLE);
    		
    	Button btnFeedback = (Button) inflated.findViewById(R.id.btnFeedback);
    	if(!btnFeedbackVisible)
    		btnFeedback.setVisibility(View.INVISIBLE);
    }
    
    /**
     * Home button click handler
     * @param v
     */
    public void btnHomeClick(View v) {
    	Intent intent = null;
    	intent = new Intent(getApplicationContext(), MainActivity.class);
    	intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
    	startActivity(intent);
		
    }
    
    /**
     * Logout button click handler
     * @param v
     */
    public void btnFeedbackClick(View v) {
    	showDialog(DIALOG_ALERT);
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
      switch (id) {
        case DIALOG_ALERT:
        // Create out AlterDialog
        Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Benar-Benar ingin keluar?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ya", new OkOnClickListener());
        builder.setNegativeButton("Tidak", new CancelOnClickListener());
        AlertDialog dialog = builder.create();
        dialog.show();
       }
      return super.onCreateDialog(id);
    }

    private final class CancelOnClickListener implements DialogInterface.OnClickListener {
    	@Override
    	public void onClick(DialogInterface dialog, int which) {
    		dialog.cancel();
    	}
    }

    private final class OkOnClickListener implements DialogInterface.OnClickListener {
    	@Override
    	public void onClick(DialogInterface dialog, int which) {
    		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		intent.putExtra("EXIT", true);
    		overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
    		startActivity(intent);
    		finish();
      }
    } 
        
    public void btnAddClick(View v) {
    	Intent intent = new Intent(getApplicationContext(), AddActivity.class);
    	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
    	startActivity(intent);
    }
    
    // TODO Wrapper method to encapsulate getApplicationContext() that is needed for displaying Toast message
	protected void showToast(String text) {
		Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
	}
}