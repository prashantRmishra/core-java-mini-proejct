package myConsoleApplication;
import java.io.*;
import java.sql.SQLException;
import java.util.Iterator;
import applicationDAL.DALService;
import applicationEntities.Customer;
import applicationEntities.Supplier;

public class Main {
	public static void  main(String[] a) throws Exception{
		boolean loop = true;
		while(loop) {
			System.out.println("----------");
			System.out.println("Please choose an operation you want to perform\n1. Add Customer\n2. Add Supplier\n3. Show All Customers\n"
					+ "4. Show All Suppliers\n5. Export a Customer\n6. Export a Supplier\n"
					+ "7. Update Customer Details\n8. Update Supplier Details\n"
					+ "9. Exit");
			System.out.println("----------");
			int tradingPartnerId,creditLimit,creditBalance;
			String tradingPartnerName,city,email,panNumber;
			BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
			int choice=Integer.MAX_VALUE;
			try {
				choice = Integer.parseInt(br.readLine());
			}
			catch(Exception e) {
				System.out.println("Please Enter Valid choice !");
			}
			
			switch(choice) {
			case 1: {
				System.out.println("Enter TradingPartnerId,TradingPartnerName,city,CreditLimit,email\n For Example 12122,prashant mishra,mumbai,4000,pm7044872@gmail.com");
				
				String customerDetails[] = br.readLine().split(",");
				try {
						tradingPartnerId = Integer.parseInt(customerDetails[0]);
						tradingPartnerName = customerDetails[1];
						city = customerDetails[2];
						creditLimit = Integer.parseInt(customerDetails[3]);
						email = customerDetails[4];
						Customer customer = new Customer(tradingPartnerId,tradingPartnerName,city,creditLimit,email);
						
						addCustomer(customer);
						
				    }catch(Exception e ) {System.out.println("Please Enter valid number value for customerId/CreditLimit and do not leave any required value empty !");}
				break;
				}
			case 2: {
				System.out.println("Enter TradingPartnerId,TradingPartnerName,city,CreditBalance,PanNumber\n For Example 12122,prashant mishra,mumbai,4000,ERFJN333D4");
				
				String customerDetails[] = br.readLine().split(",");
				try {
						tradingPartnerId = Integer.parseInt(customerDetails[0]);
						tradingPartnerName = customerDetails[1];
						city = customerDetails[2];
						creditBalance = Integer.parseInt(customerDetails[3]);
						panNumber = customerDetails[4];
						Supplier supplier = new Supplier(tradingPartnerId,tradingPartnerName,city,creditBalance,panNumber);
						
						addSupplier(supplier);
						
				    }catch(Exception e ) {System.out.println("Please Enter valid number value for customerId/CreditBalance and do not leave any required value empty !");}
				break;
				}
			case 3: System.out.println("All Customer Details ");
			showAllCustomers();
			break;
			case 4: System.out.println("All Suppliers Details ");
			showAllSuppliers();
			break;
			case 5: System.out.println("Enter the Customer ID you want to export");
			tradingPartnerId = Integer.parseInt(br.readLine());
			exportCustomer(tradingPartnerId);
			break;
			case 6: System.out.println("Enter the Supplier ID you want to export");
			tradingPartnerId = Integer.parseInt(br.readLine());
			exportSupplier(tradingPartnerId);
			break;
			case 7:System.out.println("Enter customer Id ");
			tradingPartnerId = Integer.parseInt(br.readLine());
			Customer c = getCusotmerById(tradingPartnerId);
			if(c!=null) {
				
				updateCustomerDetails(c, tradingPartnerId);
			}
			else System.out.println("Please provide valid Customer Id");
			break;
			case 8:System.out.println("Enter Supplier Id");
			tradingPartnerId = Integer.parseInt(br.readLine());
			Supplier s  = getSupplierById(tradingPartnerId);
			if(s!=null) {
				updateSupplierDetails(s,tradingPartnerId);
			}
			else System.out.println("Plese provide valid Supplier Id");
			break;
			default: loop = false; break;
			}
		}
	}
	static void addCustomer(Customer customer) {
		String[] error = customer.validate();
		if(error.length==0)
			try {
				DALService.saveCustomerDetails(customer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		else {
			for(String s : error) {
				System.out.println(s);
			}
		}
	}
	static void addSupplier(Supplier supplier) {
		String[] error = supplier.validate();
		if(error.length==0)
			try {
				DALService.saveSupplierDetails(supplier);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		else {
			for(String s : error) {
				System.out.println(s);
			}
		}
	}
	static void showAllCustomers() {
		Iterator<Customer> it = null;
		try {
			it = DALService.getAllCustomers().iterator();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	static void showAllSuppliers() {
		Iterator<Supplier> it = null;
		try {
			it = DALService.getAllSuppliers().iterator();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	static void exportCustomer(int customerId) {
		try {
			DALService.getCustomerById(customerId).saveToFile("customer.txt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	static void exportCustomerToXML(int cusomerId) {
		try {
			DALService.getCustomerById(cusomerId).saveToFileInXmlFormat("customer.txt");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	static void exportSupplier(int supplierId) {
		try {
			DALService.getSupplierById(supplierId).saveToFile("supplier.txt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	static Customer getCusotmerById(int id) {
		Customer c = null;
		try {
			c = DALService.getCustomerById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	static Supplier getSupplierById(int id) {
		Supplier c = null;
		try {
			c = DALService.getSupplierById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	static void updateCustomerDetails(Customer c,int id) {
		DALService.updateCustomerDetails(c,id);
	}
	static void updateSupplierDetails(Supplier s, int id) {
		DALService.updateSupplierDetails(s, id);
	}
}
