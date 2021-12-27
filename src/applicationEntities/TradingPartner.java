package applicationEntities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class TradingPartner implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int tradingPartnerId;
	protected String tradingPartnerName;
	protected String city;
	
	public TradingPartner(int tradingPartnerId, String tradingPartnerName,
			String city){
		this.tradingPartnerId= tradingPartnerId;
		this.tradingPartnerName = tradingPartnerName;
		this.city = city;
	}
	public TradingPartner() {}	
	
	public String[] validate() {
		ArrayList<String> list = new ArrayList<>();
		if(this.tradingPartnerId<0) {
			list.add("Trading partner id can't be negative");
		}
		
		Pattern pat = Pattern.compile("[a-zA-Z ]{5,}");
		if(!pat.matcher(this.tradingPartnerName).matches()) {
			list.add("Trading partner name should be of atleast 5 characters");
		}
		Pattern pat2 = Pattern.compile("[a-zA-Z ]{3,}");
		if(!pat2.matcher(city).matches()) {
			list.add("City length must be atleast 3 character in length");
		}
		return list.toArray(new String[0]);
	}
	
	public abstract void saveToFile(String filePath);
	
}
