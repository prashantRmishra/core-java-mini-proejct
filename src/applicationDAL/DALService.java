package applicationDAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import applicationEntities.Customer;
import applicationEntities.Supplier;

public class DALService {

	static Connection conn=null;
	static PreparedStatement statement=null;
	static ResultSet result=null;
	static {
		try {
			Class.forName("org.postgresql.Driver");
			conn  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/myApp","postgres","postgres");
			
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
	public static void saveCustomerDetails(Customer c) throws SQLException {
		try {
			statement = conn.prepareStatement("insert into customer(tradingPartnerId,tradingPartnerName,"
					+ "city,creditLimit,email) values (?,?,?,?,?)");
			statement.setInt(1, c.getTradingPartnerId());
			statement.setString(2, c.getTradingPartnerName());
			statement.setString(3, c.getcity());
			statement.setInt(4, c.getCreditLimit());
			statement.setString(5, c.getEmail());
			statement.executeUpdate();
			
		}
		catch(Exception e ) {
			e.printStackTrace();
			conn.rollback();
		}
		System.out.println("Customer details inserted successfully ");
	}
	public static void saveSupplierDetails(Supplier c) throws SQLException {
		try {
			statement = conn.prepareStatement("insert into supplier(tradingPartnerId, tradingPartnerName,"
					+ "city, creditBalance,panNumber) values (?,?,?,?,?)");
			statement.setInt(1, c.getTradingPartnerId());
			statement.setString(2, c.getTradingPartnerName());
			statement.setString(3, c.getcity());
			statement.setInt(4, c.getCreditBalance());
			statement.setString(5, c.getPanNumber());
			statement.executeUpdate();
			statement.close();
		}
		catch(Exception e ) {
			conn.rollback();
			e.printStackTrace();
			
		}
		System.out.println("Supplier details inserted successfully ");
	}
	public static List<Customer> getAllCustomers() throws SQLException{
		List<Customer> list  = new ArrayList<>();
		try {
			statement = conn.prepareStatement("select * from customer");
			result = statement.executeQuery();
			
			while(result.next()) {
				list.add(new Customer(result.getInt("tradingPartnerId"),
						result.getString("tradingPartnerName"),
						result.getString("city"),
						result.getInt("creditLimit"),
						result.getString("email")));
			}
			result.close();
			statement.close();
		}
		catch(Exception e ) {
			e.printStackTrace();
			conn.rollback();
		}
		return list;
	}
	public static List<Supplier> getAllSuppliers() throws SQLException{
		List<Supplier> list  = new ArrayList<>();
		try {
			statement = conn.prepareStatement("select * from supplier");
			result = statement.executeQuery();
			
			while(result.next()) {
				list.add(new Supplier(result.getInt("tradingPartnerId"),
						result.getString("tradingPartnerName"),
						result.getString("city"),
						result.getInt("creditBalance"),
						result.getString("panNumber")));
			}
			result.close();
			statement.close();
		}
		catch(Exception e ) {
			e.printStackTrace();
			conn.rollback();
		}
		return list;
	}
	
	public static Customer getCustomerById(int customerId) throws SQLException {
		Customer customer = null;
		try {
			statement = conn.prepareStatement("select * from customer where tradingPartnerId = ?");
			statement.setInt(1, customerId);
			result = statement.executeQuery();
			if(result.next()) {
				customer = new Customer(result.getInt("tradingPartnerId"),
						result.getString("tradingPartnerName"),
						result.getString("city"),
						result.getInt("creditLimit"),
						result.getString("email"));
			}
			result.close();
			statement.close();
			
			
		}
		catch(Exception e ) {
			conn.rollback();
		}
		return customer;
	}
	public static Supplier getSupplierById(int supplierId) throws SQLException {
		Supplier supplier = null;
		try {
			statement = conn.prepareStatement("select * from supplier where tradingPartnerId = ?");
			statement.setInt(1, supplierId);
			result = statement.executeQuery();
			if(result.next()) {
				supplier = new Supplier(result.getInt("tradingPartnerId"),
						result.getString("tradingPartnerName"),
						result.getString("city"),
						result.getInt("creditBalance"),
						result.getString("panNumber"));
			}
			result.close();
			statement.close();
			
			
		}
		catch(Exception e ) {
			e.printStackTrace();
			conn.rollback();
		}
		return supplier;
	}
	
	public static void updateCustomerDetails(Customer c,int id) {
		try {
			statement = conn.prepareStatement("update customer set tradingPartnerName = ?,"
					+ "tradingPartnerId=?,"
					+ "city = ?,"
					+ "creditLimit = ?,"
					+ "email = ? where tradingPartnerId = ? ;");
			statement.setString(1, c.getTradingPartnerName());
			statement.setInt(2, c.getTradingPartnerId());
			statement.setString(3, c.getcity());
			statement.setInt(4, c.getCreditLimit());
			statement.setString(5, c.getEmail());
			statement.setInt(6, id);
			statement.executeUpdate();
			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateSupplierDetails(Supplier c,int id) {
		try {
			statement = conn.prepareStatement("update customer set tradingPartnerName = ?,"
					+ "tradingPartnerId=?,"
					+ "city = ?,"
					+ "creditLimit = ?,"
					+ "email = ? where tradingPartnerId = ? ;");
			statement.setString(1, c.getTradingPartnerName());
			statement.setInt(2, c.getTradingPartnerId());
			statement.setString(3, c.getcity());
			statement.setInt(4, c.getCreditBalance());
			statement.setString(5, c.getPanNumber());
			statement.setInt(6, id);
			statement.executeUpdate();
			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
