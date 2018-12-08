import com.fibaro.model.PurchaseOrders;
import com.fibaro.service.DBConnector;
import com.fibaro.service.PurchaseOrdersDao;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
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

				Connection conn = DBConnector.getConnection("raporty", "raporty");
		)
		{

			SortedSet<PurchaseOrders> purchaseOrdersSet = PurchaseOrdersDao.loadAllOrders(new Long(5557));
			purchaseOrdersSet.stream().forEach(System.out::println);
			BigDecimal l = new BigDecimal(1264465465);
			Long k = Long.valueOf(1264);

			System.out.println(l.equals(k));
			DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
			DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

			symbols.setGroupingSeparator(' ');
			formatter.setDecimalFormatSymbols(symbols);
			System.out.println(formatter.format(l));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	}
		

