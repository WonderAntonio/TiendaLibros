package tiendalibros;

	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	public class AuthorModel extends DBEntity {
		
		private ArrayList<Author> aumodel;
		
		public AuthorModel() {
			
		}
		private void Mapping(ResultSet dset) {
		    aumodel = new ArrayList<Author>();
		        try {
		            while (dset.next()) {
		                Author objau = new Author();
		                objau.auid = dset.getString("au_id");
		                objau.aulname = dset.getString("au_lname");
		                objau.aufname = dset.getString("au_fname");
		                objau.auphone = dset.getString("phone");
		                objau.auaddress = dset.getString("address");
		                objau.aucity = dset.getString("city");
		                objau.austate = dset.getString("state");
		                objau.auzip = dset.getString("zip");
		                objau.aucontract = dset.getBoolean("contract");
		                aumodel.add(objau);
		            }
		            dset.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		public ArrayList<Author> Get(){
			String sql = "SELECT * FROM authors WHERE ";
	        sql += "Concat(au_id,au_lname,au_fname,phone,address,city,state,zip,contract) ";
	        Mapping(getData(sql));
	        return aumodel;
		}
		public boolean Post(Author odata){
			String sql = "INSERT INTO authors (au_id,au_lname,au_fname,phone,address,city,state,zip,contract) VALUES ( ";
		    sql += "'" + odata.auid + "', ";
		    sql += "'" + odata.aulname + "', ";
		    sql += "'" + odata.aufname + "', ";
		    sql += "'" + odata.auphone + "', ";
		    sql += "'" + odata.auaddress + "', ";
		    sql += "'" + odata.aucity + "', ";
		    sql += "'" + odata.austate + "', ";
		    sql += "'" + odata.auzip + "', ";
		    // El campo contract es booleano, entonces necesitas manejarlo adecuadamente
		    sql += (odata.aucontract ? "1" : "0") + ")";
			return  execSQL(sql);
		}
		public boolean Put(Author odata){
			String sql = "UPDATE authors SET ";
		    sql += "au_lname = '" + odata.aulname + "', ";
		    sql += "au_fname = '" + odata.aufname + "', ";
		    sql += "phone = '" + odata.auphone + "', ";
		    sql += "address = '" + odata.auaddress + "', ";
		    sql += "city = '" + odata.aucity + "', ";
		    sql += "state = '" + odata.austate + "', ";
		    sql += "zip = '" + odata.auzip + "', ";
		    sql += "contract = " + (odata.aucontract ? "1" : "0");
		    sql += " WHERE au_id = '" + odata.auid + "'";
			return execSQL(sql);
		}
		public boolean Delete(String authorId) {
		    String sql = "DELETE FROM authors WHERE au_id = '" + authorId + "'";
		    return execSQL(sql);
		}
}
