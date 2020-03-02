package ParcelLocker;

import java.math.BigDecimal;

public class ParcelLocker {

	
	int localhost;
	
	
	public ParcelLocker(int localhost) {
		super();
		
		this.localhost=localhost;
		
	}


	

	public int getLocalhost() {
		return localhost;
	}

	public void setLocalhost(int localhost) {
		this.localhost = localhost;
	}

	@Override
	public String toString() {
		return "ParcelLocker [localhost=" + localhost + "]";
	}
	
}
