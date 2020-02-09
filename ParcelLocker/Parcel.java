package ParcelLocker;



import java.io.Serializable;
import java.util.Random;

public class Parcel implements Serializable {
	
	Random random = new Random();
	String imienadawcy;
	String numernadawcy;
	String imieodbiorcy;
	String numerodbiorcy;
	public String wiadomosc;
	String nrhostaodbiorczego;
	String nrhostanadawczego;
	public String uniquenumber;
	
	public Parcel(String imienadawcy,String numernadawcy,String imieodbiorcy,String wiadomosc,String nrhostaodbiorczego,String nrhostanadawczego,String id) {
		this.imienadawcy=imienadawcy;
		this.numernadawcy=numernadawcy;
		this.imieodbiorcy=imieodbiorcy;
		this.wiadomosc=wiadomosc;
		this.nrhostanadawczego=nrhostanadawczego;
		this.nrhostaodbiorczego=nrhostaodbiorczego;
		this.uniquenumber=id;
		
		
	}}