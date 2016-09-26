package com.booting.database;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.booting.model.Dongeng;
import android.content.ContentValues;

/**
 *  @author : Daniel FJP Napitupulu
 *  @Email  : if09033@gmail.com
 *  @Website: http://booting09.com
 */
public class DongengDB extends SQLiteOpenHelper {
	private Context context = null;
	private SQLiteDatabase database = null;
	private static final String DATABASE_NAME  = "DongengDB";
	private static final String DATABASE_TABLE = "tbl_m_dongeng";
	
	// TODO The name and column index of each column in your database.
	public static final String KEY_ID    = "Id";
	public static final String KEY_JUDUL = "Judul";
	public static final String KEY_ISI   = "Isi";
	public static final String KEY_IMAGE = "Image";
	
	// TODO Constructor with parameter context 
	public DongengDB(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}
	
	// TODO Drop Table 'tbl_m_dongeng'
	public void dropTable(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
	}
	
	// TODO Create Table 'tbl_m_dongeng'
	public void createTable(SQLiteDatabase db, Context context) {
		this.context = context;
		database = this.context.openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY, null);
		String sqlCreate = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " ("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ KEY_JUDUL + " varchar(50)," 
				+ KEY_ISI + " text," 
				+ KEY_IMAGE + " BLOB" + ");";
		DBUtil.createTable(database, DATABASE_TABLE, sqlCreate); 
	}

	// TODO Generate data to database SQLite
	public void generateData(SQLiteDatabase db){
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, 1);
		cv.put(KEY_JUDUL, "Terselamatkan");  
		cv.put(KEY_ISI, "5 Maret 1973, Daly City, California—Aku terlambat. Aku " +
				"harus menyelesaikan pekerjaan mencuci peralatan makan " +
				"secepatnya, kalau tidak aku tidak dapat jatah sarapan; dan " +
				"karena semalam aku tidak makan, jadi sekarang aku harus " +
				"makan sesuatu. Ibu mondar-mandir sambil berteriak " +
				"kepada saudara-saudara lelakiku. Aku bisa mendengar " +
				"langkahlangkahnya yang berat menuju dapur. Cepat-cepat " +
				"aku membilas lagi. Tapi terlambat. Ibu menarikku dengan " +
				"kasar. \n \n" +
				"Plak! Ibu memukul mukaku, dan aku terjatuh. Aku tahu " +
				"lebih baik aku menjatuhkan diri daripada tetap berdiri dan " +
				"dipukul lagi. Kalau aku tetap berdiri, Ibu akan menganggap " +
				"itu sebagai sikap membantah, dan itu artinya beberapa " +
				"pukulan lagi atau, yang paling kutakutkan, tidak diberi " +
				"makan. Baru kemudian aku berdiri pelan-pelan sambil " +
				"memiringkan mukaku agar tidak menatapnya, sementara " +
				"Ibu berteriak di telingaku. \n \n" +
				"Aku menunjukkan sikap ketakutan, sambil terus-menerus " +
				"mengangguk seakan memahami arti ancaman-ancaman " +
				"yang keluar dari mulutnya. \"Ya, ya,\" kataku dalam hati, " +
				"\"asalkan aku boleh makan. Pukul aku lagi, asalkan aku " +
				"dapat makanan karena aku harus makan.\" Satu pukulan " +
				"lagi menyentakkan kepalaku hingga membentur pinggiran " +
				"dinding. Aku meneteskan air mata sebagai tanda tak tahan " +
				"menerima cemoohan Ibu.");
		cv.put(KEY_IMAGE, "");
		db.insert(DATABASE_TABLE, null, cv);
		
		cv.put(KEY_ID, 2);
		cv.put(KEY_JUDUL, "Masa-Masa Bahagia");  
		cv.put(KEY_ISI, "Tahun-tahun sebelum aku mengalami perlakuan buruk, " +
				"keluargaku adalah keluarga kulit putih ideal, layaknya " +
				"kisah keluarga Brady Bunch di tahun 1960-an. Aku dan " +
				"kedua saudara lelakiku dikaruniai orangtua yang sempuma. " +
				"Segala kebutuhan kami selalu terpenuhi dengan rasa cinta " +
				"dan perhatian. \n \n" +
				"Kami tinggal di sebuah rumah yang biasa-biasa saja, " +
				"dengan dua kamar tidur, di sebuah kawasan hunian yang " +
				"\"baik\" di Daly City. Aku ingat setiap kali memandang ke " +
				"luar dari bay window, jendela tiga sisi yang dibuat agak " +
				"menjorok keluar dari dinding rumah, ruang keluarga pada " +
				"saat cuaca cerah, akan terlihat jelas tiang-tiang jembatan " +
				"Golden Gate berwama orange dan skyline San Francisco " +
				"yang cantik. \n \n" +
				"Ayahku, Stephen Joseph, adalah petugas pemadam " +
				"kebakaran. Kantomya di jantung kota San Francisco. Tinggi " +
				"badannya hampir 1,8 meter, beratnya sekitar 86 kilogram. " +
				"bahunya lebar dan lengannya besar, bentuk badan idaman " +
				"pria pada umumnya. Alis matanya yang hitam tebal " +
				"sepadan dengan rambutnya. Aku merasa jadi anak " +
				"istimewa ketika ia memandangiku dengan bangga dan " +
				"memanggilku \"Tiger\".");
		cv.put(KEY_IMAGE, "");
		db.insert(DATABASE_TABLE, null, cv);
		
		cv.put(KEY_ID, 3);
		cv.put(KEY_JUDUL, "Anak Nakal");  
		cv.put(KEY_ISI, "Hubunganku dengan Ibu berubah drastis, dari tempaan " +
				"disiplin menjadi hukuman yang semakin membabi-buta. " +
				"Kadangkala hukuman itu sedemikian menyakitkan sampaisampai " +
				"aku harus merangkak untuk menghindarinya, " +
				"bahkan aku bisa menyebutnya sebagai menyelamatkan " +
				"hidupku. \n\n" +
				"Sebagai anak kecil, suaraku mungkin terdengar lebih keras " +
				"dibandingkan anak-anak kecil lainnya. Tampaknya aku " +
				"juga selalu bemasib sial, selalu ketahuan bersikap nakal, " +
				"sekalipun aku dan kedua saudaraku sering sama-sama " +
				"mengaku melakukan \"kejahata\" yang sama. \n\n" +
				"Pada awalnya, aku disuruh berdiri atau jongkok di pojok " +
				"kamar tidur kami. Pada saat itulah aku mulai takut " +
				"terhadap Ibu. Sangat takut. Aku pemah aku meminta Ibu " +
				"agar aku boleh keluar kamar. Aku akan diam dalam posisi " +
				"dan tempat yang sama, menunggu sampai salah seorang " +
				"saudaraku masuk ke kamar tidur kami dan bertanya pada " +
				"Ibu apakah David sudah boleh keluar dan ikut main " +
				"bersama.");
		cv.put(KEY_IMAGE, "");
		db.insert(DATABASE_TABLE, null, cv);
	}
	
	// TODO Array all list dongeng
	public ArrayList<Dongeng> toArray() {
		return this.toArray("");
	}

	// TODO Array list dongeng filter by 'String filterText Judul'
	public ArrayList<Dongeng> toArray(String filterText) {
		Cursor cursor = null;
		ArrayList<Dongeng> allContact = null;
		
		String filterQuery = "";
		if( !filterText.equalsIgnoreCase("") ) {
			filterQuery = " WHERE Judul = '" + filterText + "'";
		}

		try {
			allContact = new ArrayList<Dongeng>();

			cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE + filterQuery, null);
			if (cursor.getCount() > 0) {
				int indexId    = cursor.getColumnIndex("Id");
				int indexJudul = cursor.getColumnIndex("Judul");
				int indexIsi   = cursor.getColumnIndex("Isi");
				cursor.moveToFirst();
				do {
					String Isi   = cursor.getString(indexIsi);
					String Judul = cursor.getString(indexJudul);
					String Id 	 = cursor.getString(indexId);

					Dongeng contact = new Dongeng();
					contact.setIsi(Isi);
					contact.setJudul(Judul);
					contact.setId(Id);

					allContact.add(contact);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return allContact;
	}
	
	// Updating single dongeng
    public void updateDongeng(String[] data){  
    	SQLiteDatabase db = this.getWritableDatabase();
    	
        ContentValues cv=new ContentValues();  
        cv.put(KEY_JUDUL, data[1]);  
        cv.put(KEY_ISI, data[2]);   
        cv.put(KEY_IMAGE, data[3]);
        String whereClause = "Id=?";  
        String[] whereArgs = new String[] {String.valueOf(data[0])};  
        db.update(DATABASE_TABLE, cv, whereClause, whereArgs);  
    }  
	
	// Adding new dongeng
    public void addDongeng(Dongeng contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_JUDUL, contact.getJudul());
        values.put(KEY_ISI, contact.getIsi());
        values.put(KEY_IMAGE, contact.getImage());
        
        // Inserting Row
        db.insert(DATABASE_TABLE, null, values);
        db.close(); // Closing database connection
    }
	
	public boolean delete(Dongeng contact) {
		try {
			database.beginTransaction();

			// TODO Delete this record
			String delete = "DELETE FROM " + DATABASE_TABLE + " WHERE Id='"
					+ contact.getId() + "'";
			database.execSQL(delete);

			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		return true;
	}
		
	// TODO Get data from dongeng with ID
	public Dongeng get(String ID) {
		Dongeng returnContact = null;
		Cursor cursor = null;

		try {
			cursor = database.rawQuery("SELECT * FROM " + DATABASE_TABLE
					+ " WHERE Id = '" + ID + "'", null);
			if (cursor.getCount() > 0) {
				int indexId    = cursor.getColumnIndex("Id");
				int indexJudul = cursor.getColumnIndex("Judul");
				int indexIsi   = cursor.getColumnIndex("Isi");
				cursor.moveToFirst();
				do {
					String Isi   = cursor.getString(indexIsi);
					String Judul = cursor.getString(indexJudul);
					String Id 	 = cursor.getString(indexId);

					returnContact = new Dongeng();
					returnContact.setIsi(Isi);
					returnContact.setJudul(Judul);
					returnContact.setId(Id);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}

		return returnContact;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}
	
}