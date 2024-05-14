package tiendalibros;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaleModel extends DBEntity {
	private ArrayList<Sales> samodel;
	
	public SaleModel() {
		
	}
	private void Mapping(ResultSet dset) {
		samodel = new ArrayList<Sales>();
	        try {
	            while (dset.next()) {
	            	Sales objsa = new Sales();
	            	objsa.storid = dset.getString("stor_id");
	            	objsa.ordnum = dset.getString("ord_num");
	            	objsa.orddate = dset.getTimestamp("ord_date");
	            	objsa.qty = dset.getInt("qty");
	            	objsa.payterms = dset.getString("payterms");
	            	objsa.titleid = dset.getString("title_id");       
	            	samodel.add(objsa);
	            }
	            dset.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	public ArrayList<Sales> Get(){
		String sql = "SELECT * FROM sales s ";
	    sql += "INNER JOIN stores st ON s.stor_id = st.stor_id ";
	    sql += "INNER JOIN titles t ON s.title_id = t.title_id ";
        Mapping(getData(sql));
        return samodel;
	}
	public boolean Post(Sales odata){
		// Verificar si el stor_id existe en la tabla stores
	    if (!checkStoreExistence(odata.storid)) {
	        System.out.println("El stor_id proporcionado no existe en la tabla stores.");
	        return false;
	    }
	    
	    // Verificar si el title_id existe en la tabla titles
	    if (!checkTitleExistence(odata.titleid)) {
	        System.out.println("El title_id proporcionado no existe en la tabla titles.");
	        return false;
	    }

	    // Insertar la nueva venta
	    String sql = "INSERT INTO sales (stor_id, ord_num, ord_date, qty, payterms, title_id) VALUES ('" +
                odata.storid + "', '" + odata.ordnum + "', '" + odata.orddate + "', " +
                odata.qty + ", '" + odata.payterms + "', '" + odata.titleid + "')";
	    return execSQL(sql);
	}
	public boolean Put(Sales odata) {
	    // Verificar si el ord_num existe en la tabla sales
	    if (!checkSaleExistence(odata.ordnum)) {
	        System.out.println("El ord_num proporcionado no existe en la tabla sales.");
	        return false;
	    }

	    // Verificar si el stor_id existe en la tabla stores
	    if (!checkStoreExistence(odata.storid)) {
	        System.out.println("El stor_id proporcionado no existe en la tabla stores.");
	        return false;
	    }
	    
	    // Verificar si el title_id existe en la tabla titles
	    if (!checkTitleExistence(odata.titleid)) {
	        System.out.println("El title_id proporcionado no existe en la tabla titles.");
	        return false;
	    }

	    // Actualizar la venta existente
	    String sql = "UPDATE sales SET stor_id = '" + odata.storid + "', " +
	                 "ord_date = '" + odata.orddate + "', qty = " + odata.qty + ", " +
	                 "payterms = '" + odata.payterms + "', title_id = '" + odata.titleid + "' " +
	                 "WHERE ord_num = '" + odata.ordnum + "'";

	    return execSQL(sql);
	}
	public boolean Delete(String SaleaOr) {
	    String sql = "DELETE FROM sales WHERE ord_num = '" + SaleaOr + "'";
	    return execSQL(sql);
	}
	public boolean checkStoreExistence(String storId) {
	    String sql = "SELECT COUNT(*) AS count FROM stores WHERE stor_id = '" + storId + "'";
	    ResultSet resultSet = getData(sql);
	    try {
	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	public boolean checkTitleExistence(String titleId) {
	    String sql = "SELECT COUNT(*) AS count FROM titles WHERE title_id = '" + titleId + "'";
	    ResultSet resultSet = getData(sql);
	    try {
	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	public boolean checkSaleExistence(String ordNum) {
	    String sql = "SELECT COUNT(*) AS count FROM sales WHERE ord_num = '" + ordNum + "'";
	    ResultSet resultSet = getData(sql);
	    try {
	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            resultSet.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	// Método para obtener el total de ventas por tienda ordenado por nombre
		public Map<String, Integer> getTotalSalesByStore() {
	        Map<String, Integer> salesByStore = new HashMap<>();

	        String sql = "SELECT s.stor_id, st.stor_name, SUM(s.qty) AS total_sales " +
	                     "FROM sales s " +
	                     "INNER JOIN stores st ON s.stor_id = st.stor_id " +
	                     "GROUP BY s.stor_id, st.stor_name " +
	                     "ORDER BY st.stor_name";

	        ResultSet resultSet = getData(sql);
	        
	        try {
	            while (resultSet.next()) {
	                String storeId = resultSet.getString("stor_id");
	                String storeName = resultSet.getString("stor_name");
	                int totalSales = resultSet.getInt("total_sales");
	                salesByStore.put(storeId + " - " + storeName, totalSales);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return salesByStore;
	    }

	 // Método para obtener el total de ventas por libro
		public Map<String, Integer> getTotalSalesByBook() {
	        Map<String, Integer> salesByBook = new HashMap<>();

	        String sql = "SELECT t.title_id, t.title, SUM(s.qty) AS total_sales " +
	                     "FROM sales s " +
	                     "INNER JOIN titles t ON s.title_id = t.title_id " +
	                     "GROUP BY t.title_id, t.title";

	        ResultSet resultSet = getData(sql);
	        
	        try {
	            while (resultSet.next()) {
	                String titleId = resultSet.getString("title_id");
	                String titleName = resultSet.getString("title");
	                int totalSales = resultSet.getInt("total_sales");
	                salesByBook.put(titleId + " - " + titleName, totalSales);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return salesByBook;
	    }
		public  int getTotalSales() {
	        int totalSales = 0;

	        String sql = "SELECT SUM(qty) AS total_sales FROM sales";

	        ResultSet resultSet = getData(sql);
	        
	        try {
	            if (resultSet.next()) {
	                totalSales = resultSet.getInt("total_sales");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return totalSales;
	    }
	    // Método para obtener el promedio de ventas
		public  double getAverageSales() {
	        double averageSales = 0.0;

	        String sql = "SELECT AVG(qty) AS average_sales FROM sales";

	        ResultSet resultSet = getData(sql);
	        
	        try {
	            if (resultSet.next()) {
	                averageSales = resultSet.getDouble("average_sales");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return averageSales;
	    }
}
