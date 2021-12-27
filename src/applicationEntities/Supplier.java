package applicationEntities;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Supplier extends TradingPartner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	public Supplier() {}
	private int creditBalance;
	private String panNumber;
	
	public Supplier(int tradingPartnerId, String tradingPartnerName,
			String city,int c, String p) {
		super(tradingPartnerId,tradingPartnerName, city);
		this.creditBalance = c;
		this.panNumber=p;
	}
	
	@Override
	public void saveToFile(String filePath) {
		try {
			FileOutputStream file = new FileOutputStream(filePath);
			XMLEncoder out = new XMLEncoder(file);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("data is saved to file "+ filePath);
		
	}
	public int getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(int creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getPanNumber() {
		return panNumber;
	}

	@Override
	public String toString() {
		return "Supplier [creditBalance=" + creditBalance + ", panNumber=" + panNumber + ", tradingPartnerId="
				+ tradingPartnerId + ", tradingPartnerName=" + tradingPartnerName + ", city=" + city + "]";
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
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

	@Override
	public String[] validate() {
		String error[] = super.validate();
		if(error.length!=0) return error;
		ArrayList<String> list = new ArrayList<>();
		if(this.creditBalance > 150000) {
			list.add("Credit balance can't be more than 50000");
		}
		
		Pattern pat = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		if(!pat.matcher(panNumber).matches()) {
			list.add("Please enter valid PAN number");
		}
		return list.toArray(new String[list.size()]);
		
	}
	
}
