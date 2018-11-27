import com.fibaro.model.PurchaseOrders;
import com.fibaro.service.DBConnector;
import com.fibaro.service.PurchaseOrdersDao;

import java.sql.*;
import java.util.Set;
import java.util.SortedSet;

public class Updater {
	public static void main(String[] args) {
		try {

	        Class.forName("oracle.jdbc.driver.OracleDriver");

	    } catch (ClassNotFoundException e) {

	        System.out.println("Where is your Oracle JDBC Driver?");
	        e.printStackTrace();
	        return;

	    }
		try (

				Connection conn = DBConnector.getConnection();
		)
		{

			SortedSet<PurchaseOrders> purchaseOrdersSet = PurchaseOrdersDao.loadAllOrders(new Long(5557));
			purchaseOrdersSet.stream().forEach(System.out::println);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	}
		

