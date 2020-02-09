package ParcelLocker;

import java.util.ArrayList;

public class ControllPackage {
	public  ArrayList<Parcel>helping;
	
	public static void main(String[]args) {
		new ControllPackage();
	}
	public ControllPackage() {
		this.helping=new ArrayList<Parcel>();
	}
	void add_parcel(Parcel p) {
		this.helping.add(p);
	}
		
}
