package com.booting.activity;

import java.util.ArrayList;
import com.booting.adapter.DongengAdapter;
import com.booting.model.Dongeng;
import com.booting.database.DongengDB;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  @author : Daniel FJP Napitupulu
 *  @Email  : if09033@gmail.com
 *  @Website: http://booting09.com
 */
public class MainActivity extends DashBoardActivity {
	private ListView lsvDongengView = null;
	private DongengAdapter adapter  = null;
	private DongengDB DongengDB  	= null;
	private SQLiteDatabase db       = null;
	private ArrayList<Dongeng> listDongeng = null;
	private EditText txtSearchText = null;
	private TextView txtNoDataDongeng;
	AlertDialog.Builder alertDialogConfirmDelete = null;
	private Dongeng bookIWantToDelete = null;
	public static String id    = "";
	public static String judul = "";
	public static String isi   = "";
	private static void getId(String id) { MainActivity.id = id; }
	private static void getJudul(String judul) { MainActivity.judul = judul; }
	private static void getIsi(String isi) { MainActivity.isi = isi; }
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.main);
        setHeader(getString(R.string.HomeActivityTitle), false, true);
    
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        
        txtNoDataDongeng = (TextView) findViewById(R.id.txtNoDataDongeng);
        lsvDongengView   = (ListView) findViewById(R.id.lsvListDongeng);
        lsvDongengView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				String _id    = ((TextView) view.findViewById(R.id.txtId)).getText().toString();
				String _judul = ((TextView) view.findViewById(R.id.txtName)).getText().toString();
				String _isi   = ((TextView) view.findViewById(R.id.txtIsi)).getText().toString();
				getId(_id);
				getJudul(_judul);
				getIsi(_isi);
				Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
		    	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
		    	startActivity(intent);
			}
		});
        
        refreshListAdapter();
        registerForContextMenu(lsvDongengView);
        
        txtSearchText = (EditText) findViewById(R.id.searchText);
		txtSearchText.addTextChangedListener( new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				//we don't need this
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				//we don't need this
			}
			@Override
			public void afterTextChanged(Editable text) {
				refreshListAdapter( text.toString() );
			}
		});
		
		DialogInterface.OnClickListener dialogConfirmDeleteClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		        	if( bookIWantToDelete != null ) {
						Log.i("Log", "item " + bookIWantToDelete.getJudul() + " deleted!");
						showToast( "item " + bookIWantToDelete.getJudul() + " deleted!" );
						DongengDB.delete(bookIWantToDelete);
						bookIWantToDelete = null;
						refreshListAdapter();
		        	}
		            break;
		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		            break;
		        }
		    }
		};
		
		alertDialogConfirmDelete = new AlertDialog.Builder(this);
		alertDialogConfirmDelete.setPositiveButton("Yes", dialogConfirmDeleteClickListener);
		alertDialogConfirmDelete.setNegativeButton("No", dialogConfirmDeleteClickListener);
    }
    
    // TODO Get data from database SQLite
    private void getListDongeng() { 
    	DongengDB = new DongengDB(getApplicationContext());
		adapter = new DongengAdapter(this);
		db = DongengDB.getWritableDatabase();
		DongengDB.createTable(db, getBaseContext());
    } 
    
    // TODO We need to refresh adapter for every data update. Without it, ListView will never be refreshed
	protected void refreshListAdapter() {
		try {
			getListDongeng();
		} catch(Exception e) {
        	Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
			listDongeng = DongengDB.toArray();
			adapter.updatelistDongeng(listDongeng);
			lsvDongengView.setAdapter(adapter);
			
			if (adapter.getCount() == 0) {
				txtNoDataDongeng.setVisibility(View.VISIBLE);
			} else {
				txtNoDataDongeng.setVisibility(View.GONE);
			}
        }
	}

	// TODO We need to refresh adapter for every data update. Without it, ListView will never be refreshed
	protected void refreshListAdapter(String filterText) {
		listDongeng = DongengDB.toArray( filterText );
		adapter.updatelistDongeng(listDongeng);
		lsvDongengView.setAdapter(adapter);
		
		if (adapter.getCount() == 0) {
			txtNoDataDongeng.setVisibility(View.VISIBLE);
		} else {
			txtNoDataDongeng.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo cmInfo) {
		if (v.getId() == R.id.lsvListDongeng) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) cmInfo;
			menu.setHeaderTitle(listDongeng.get(info.position).getJudul());
			String[] menuItems = { "Update", "Delete" };
			
			for (int menuIndex = 0; menuIndex < menuItems.length; menuIndex++) {
				menu.add(Menu.NONE, menuIndex, menuIndex, menuItems[menuIndex]); 
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		int menuItemIndex = item.getItemId();
		Dongeng book = listDongeng.get(info.position);

		switch (menuItemIndex) {
		case 0:
			String _id    = book.getId();
			String _judul = book.getJudul();
			String _isi   = book.getIsi();
			getId(_id);
			getJudul(_judul);
			getIsi(_isi);
			Intent intent = new Intent(getApplicationContext(), EditActivity.class);
	    	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_out_left );
	    	startActivity(intent);
			break;
		case 1:
			bookIWantToDelete = book;
			alertDialogConfirmDelete.setMessage("Delete " + book.getJudul() + "?");
			alertDialogConfirmDelete.show();
			break;
		}

		return true;
	}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
        if ((DongengDB != null)) {
        	DongengDB.close();
        }
        if (db != null) {
            db.close();
        }
    }
}