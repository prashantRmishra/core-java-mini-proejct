package applicationEntities;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Customer extends TradingPartner implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	public Customer() {}

	public int getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTradingPartnerId() {
		return super.tradingPartnerId;
	}
	public String getTradingPartnerName() {
		return super.tradingPartnerName;
	}
	public String getcity() {
		return super.city;
	}
	private int creditLimit;
	private String email;
	public Customer(int tradingPartnerId, String tradingPartnerName,
			String city, int c , String e) {
		super(tradingPartnerId,tradingPartnerName, city);
		this.creditLimit=c;
		this.email=e;
	}
	@Override
	public void saveToFile(String filePath) {
		try {
			FileOutputStream file = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("data saved to file "+ filePath);
		
	}
	public void saveToFileInXmlFormat(String filePath) {
		try {
			FileOutputStream file = new FileOutputStream(filePath);
			XMLEncoder out = new XMLEncoder(file);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("data is saved to file in XML format"+ filePath);
		
	}
	@Override
	public String[] validate() {
		String error[] = super.validate();
		if(error.length!=0) return error;
		ArrayList<String> list = new ArrayList<>();
		if(this.creditLimit > 50000) {
			list.add("Credit limit can't be more than 50000");
		}
		Pattern pat = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\\\.\"+\r\n"
				+ "                            \"[a-zA-Z0-9_+&*-]+)*@\" +\r\n"
				+ "                            \"(?:[a-zA-Z0-9-]+\\\\.)+[a-z\" +\r\n"
				+ "                            \"A-Z]{2,7}$");
		if(!pat.matcher(this.email).matches()) {
			list.add("Plese provide valid email id ");
		}
		return list.toArray(new String[list.size()]);
		
	}
	@Override
	public String toString() {
		return "Customer [creditLimit=" + creditLimit + ", email=" + email + ", tradingPartnerId=" + tradingPartnerId
				+ ", tradingPartnerName=" + tradingPartnerName + ", city=" + city + "]";
	}
	
}
