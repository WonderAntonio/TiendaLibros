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
		                objau.auphone = dset.getString("au_phone");
		                objau.auaddress = dset.getString("au_address");
		                objau.aucity = dset.getString("au_city");
		                objau.austate = dset.getString("au_state");
		                objau.auzip = dset.getString("au_zip");
		                objau.aucontract = dset.getBoolean("au_contract");
		                aumodel.add(objau);
		            }
		            dset.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		public ArrayList<Author> Get(String shc){
			String sql = "SELECT * FROM authors WHERE ";
	        sql += "Concat(au_id,au_lname,au_fname,au_phone,au_address,au_city,au_state,au_zip,au_contract) LIKE '%" + shc + "%'";
	        Mapping(getData(sql));
	        return aumodel;
		}
		public boolean Post(Author odata){
			String sql = "insert into authors(au_id,au_lname,au_fname,au_phone,au_address,au_city,au_state,au_zip,au_contract) values ( ";
			sql += "'"+odata.auid +"',\"'"+odata.aulname+"',\"'"+odata.aufname+"',\"'"+odata.auphone+"',\"'"+odata.auaddress+"',\"'"+odata.aucity+"',\"'"+odata.austate+"',\"'"+odata.auzip+"',\"'"+odata.aucontract+"');";
			return execSQL(sql);
		}
		public boolean Put(Author odata){
			String sql = "update authors set campo = valor ***.... where ";
			sql += " au_id ='"+odata.auid +"'";
			return execSQL(sql);
		}
}
