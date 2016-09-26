package com.booting.model;

/**
 *  @author : Daniel FJP Napitupulu
 *  @Email  : if09033@gmail.com
 *  @Website: http://booting09.com
 */
public class Dongeng {
	private String Id    = "";
	private String Judul = "";
	private String Isi   = "";
	private String Image = "";

	// constructor tanpa parameter
    public Dongeng() {
    
    }
	
	// constructor dengan parameter
    public Dongeng(String judul, String isi, String image){
        this.Judul = judul;
        this.Isi   = isi;
        this.Image = image;
    }
	
	/**
	 * 
	 * @param Id
	 */
	public void setId(String Id) {
		this.Id = Id;
	}

	public String getId() {
		return Id;
	}
	
	/**
	 * 
	 * @param Judul
	 */
	public void setJudul(String Judul) {
		this.Judul = Judul;
	}

	public String getJudul() {
		return Judul;
	}

	/**
	 * 
	 * @param Isi
	 */
	public void setIsi(String Isi) {
		this.Isi = Isi;
	}

	public String getIsi() {
		return Isi;
	}

	/**
	 * 
	 * @param Image
	 */
	public void setImage(String Image) {
		this.Image = Image;
	}

	public String getImage() {
		return Image;
	}
	
	@Override
	public String toString() {
		return "Id: " + getId() + 
		   	   "; Judul: " + getJudul() +
			   "; Isi: " + getIsi() +
			   "; Image: " + getImage();
	}
}