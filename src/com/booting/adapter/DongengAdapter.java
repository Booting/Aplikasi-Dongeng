package com.booting.adapter;

import java.util.ArrayList;

import com.booting.activity.R;
import com.booting.model.Dongeng;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DongengAdapter extends BaseAdapter {
	private ArrayList<Dongeng> listDongeng = null;
	private static LayoutInflater inflater = null;
	public int count = 10;
	public int totAl;
	
	public DongengAdapter(Activity activity) {
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void updatelistDongeng(ArrayList<Dongeng> newListBook) {
		this.listDongeng = newListBook;
	}

	@Override
	public int getCount() {
		totAl = this.listDongeng.size();
		
		if (this.listDongeng.size() < 10) {
			return this.listDongeng.size();
		} else {
			return count;
		}
	}

	@Override
	public Dongeng getItem(int position) {
		return this.listDongeng.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		View vi = view;
		if (vi == null) {
			vi = inflater.inflate(R.layout.list_dongeng, null);
		}

		Dongeng psn = this.listDongeng.get(position);

		TextView txtIsi  = (TextView) vi.findViewById(R.id.txtIsi);
		TextView txtName = (TextView) vi.findViewById(R.id.txtName);
		TextView txtId   = (TextView) vi.findViewById(R.id.txtId);
		
		txtIsi.setText(psn.getIsi());
		txtName.setText(psn.getJudul());
		txtId.setText(psn.getId());
			
		return vi;
	}
}