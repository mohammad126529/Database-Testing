package databaseProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

//import com.mysql.cj.xdevapi.Statement;
import com.mysql.cj.protocol.Resultset;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




public class DataTest {

	//WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	Random rand2 = new Random();

	Connection con;
	Statement stmt;

	ResultSet rs;
	
	int randomnumber = rand.nextInt(500) ;

	@BeforeTest
	public void mySetup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "******");
	}

	@Test(priority = 1,invocationCount = 50)

	public void AdddataToThedataBase() throws SQLException {

randomnumber = rand.nextInt(500); 

		System.out.println(randomnumber);
		String query = "insert into customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit)VALUES ("+randomnumber+" , 'Sunrise Ventures', 'Anderson', 'Michael', '212-555-1234', '123 Main Street', 'Suite 200', 'New York', 'NY', '10001', 'USA', 1621, 50000.00)";

		stmt = con.createStatement();

		int rowInserted = stmt.executeUpdate(query);

		System.out.println(rowInserted);

	}

	@Test(priority = 2)
	public void updateData() throws SQLException {
		String query = "update customers set contactFirstName ='nooralhuda' where customerNumber = "+randomnumber;

		stmt = con.createStatement();

		int NumberOfRowUpdated = stmt.executeUpdate(query);

		System.out.println(NumberOfRowUpdated);

	}

	@Test(priority = 3)

	public void myFirstTestToGetData() throws SQLException {

		stmt = con.createStatement();
		rs = stmt.executeQuery("select * from customers where customerNumber="+randomnumber);

		while (rs.next()) {
			int CustomnumberInThedataBase = rs.getInt("customerNumber");
			String CustomerNameInTheDataBase = rs.getString("customerName");

			System.out.println(CustomnumberInThedataBase);
			System.out.println(CustomerNameInTheDataBase);

			String firstName = rs.getString("contactFirstName");
			String lastName = rs.getString("contactLastName");

			//driver.get("https://magento.softwaretestingboard.com/customer/account/create/");

			//WebElement firstnameInputField = driver.findElement(By.id("firstname"));
		//	WebElement LastNameInputField = driver.findElement(By.id("lastname"));

			//firstnameInputField.sendKeys(firstName);
			//LastNameInputField.sendKeys(lastName);
		}
	}

	@Test(priority = 4, enabled = false)

	public void DeleteData() throws SQLException {
		String query = "delete from customers where customerNumber = "+randomnumber;

		stmt = con.createStatement();

		int numberofRowdeleted = stmt.executeUpdate(query);

		System.out.println(numberofRowdeleted);
	}
}
