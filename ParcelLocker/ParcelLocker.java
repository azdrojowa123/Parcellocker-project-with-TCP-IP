package ParcelLocker;

import java.math.BigDecimal;

public class ParcelLocker {

	private int id;
	int localhost;
	
	
	public ParcelLocker(int id,int localhost) {
		super();
		this.id = id;
		this.localhost=localhost;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public int getLocalhost() {
		return localhost;
	}

	public void setLocalhost(int localhost) {
		this.localhost = localhost;
	}

	@Override
	public String toString() {
		return "ParcelLocker [id=" + id + ", localhost=" + localhost + "]";
	}
	
}
