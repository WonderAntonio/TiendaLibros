package tiendalibros;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TitleModel extends DBEntity {
	private ArrayList<Titles> titlemodel;
	
	public TitleModel() {
		
	}
	private void Mapping(ResultSet dset) {
		titlemodel = new ArrayList<Titles>();
	        try {
	            while (dset.next()) {
	                Titles objti = new Titles();
	                objti.titleid = dset.getString("title_id");
	                objti.title = dset.getString("title");
	                objti.type = dset.getString("type");
	                objti.pubid = dset.getString("pub_id");
	                objti.price = dset.getDouble("price");
	                objti.advance = dset.getDouble("advance");
	                objti.royalty = dset.getBigDecimal("royalty");
	                objti.ytdsales = dset.getBigDecimal("ytd_sales");
	                objti.notes = dset.getString("notes");
	                objti.pubdate = dset.getTimestamp("pubdate");
	                titlemodel.add(objti);
	            }
	            dset.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	public ArrayList<Titles> Get(){
		// Consulta SQL para obtener todos los títulos y unirlos con la tabla de publishers
        String sql = "SELECT t.*, p.pub_name FROM titles t JOIN publishers p ON t.pub_id = p.pub_id";
        Mapping(getData(sql)); // Mapear los resultados de la consulta
        return titlemodel; // Devolver la lista de títulos
	}
	public boolean Post(Titles odata){
		// Validar si el pub_id existe en la tabla publishers
        if (publisherExists(odata.pubid)) {
            // La validación pasó, proceder con la inserción
            String sql = "INSERT INTO titles (title_id,title,type,pub_id,price,advance,royalty,ytd_sales,notes,pubdate) VALUES ( ";
            sql += "'" + odata.titleid + "', ";
            sql += "'" + odata.title + "', ";
            sql += "'" + odata.type + "', ";
            sql += "'" + odata.pubid + "', ";
            sql += odata.price + ", ";
            sql += odata.advance + ", ";
            sql += odata.royalty + ", ";
            sql += odata.ytdsales + ", ";
            sql += "'" + odata.notes + "', ";
            sql += "'" + odata.pubdate + "')";
            return execSQL(sql);
        } else {
            // El pub_id no existe en la tabla publishers, manejar el error
            System.out.println("El ID del publicador no existe en la base de datos.");
            return false;
        }
	}
	public boolean Put(Titles odata){
		 // Validar si el pub_id existe en la tabla publishers
        if (publisherExists(odata.pubid)) {
            // La validación pasó, proceder con la actualización
            String sql = "UPDATE titles SET ";
            sql += "title = '" + odata.title + "', ";
            sql += "type = '" + odata.type + "', ";
            sql += "pub_id = '" + odata.pubid + "', ";
            sql += "price = " + odata.price + ", ";
            sql += "advance = " + odata.advance + ", ";
            sql += "royalty = " + odata.royalty + ", ";
            sql += "ytd_sales = " + odata.ytdsales + ", ";
            sql += "notes = '" + odata.notes + "', ";
            sql += "pubdate = '" + odata.pubdate + "' ";
            sql += " WHERE title_id = '" + odata.titleid + "'";
            return execSQL(sql);
        } else {
            // El pub_id no existe en la tabla publishers, manejar el error
            System.out.println("El ID del publicador no existe en la base de datos.");
            return false;
        }
	}
	 public boolean Delete(String titleId) {
	        // Consulta SQL para eliminar el título con el título_id dado
	        String sql = "DELETE FROM titles WHERE title_id = '" + titleId + "'";
	        return execSQL(sql); // Ejecutar la consulta SQL de eliminación y devolver el resultado
	    }
        // Método para verificar si un publisher existe en la tabla publishers
        private boolean publisherExists(String pubId) {
            String sql = "SELECT COUNT(*) FROM publishers WHERE pub_id = '" + pubId + "'";
            ResultSet resultSet = getData(sql);
            try {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
}
