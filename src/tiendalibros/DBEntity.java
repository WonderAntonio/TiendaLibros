package tiendalibros;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;


public class DBEntity {
	
	private String ActionMessage;
	private Connection oConn;
	private Statement stmQry;
	private String _vCatalog;

	public DBEntity () {
		try { 
			propertyFile objsetting = new propertyFile();
			String dburl = objsetting.getPropValue("dburl");
			_vCatalog = objsetting.getPropValue("dbcatalog");
			dburl += "?user=" +objsetting.getPropValue("dbuser");
			dburl += "&password=" +objsetting.getPropValue("dbpassword");
			oConn = DriverManager.getConnection(dburl); 
			stmQry = oConn.createStatement();
			}catch(SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public ResultSet getData(String p_strSQLStm) {
		try {
		ResultSet rlSet = stmQry.executeQuery(p_strSQLStm);
		//stmQry.close();
		//oConn.close();
		return rlSet ;
		}catch(SQLException E) {
		ActionMessage = E.toString();
		return null;
		}catch(Exception D) {
		ActionMessage = D.toString();
		return null;
		}
	}
	public ResultSet getMeta() {
		try {
		DatabaseMetaData dbmd = oConn.getMetaData();
		// el object MetaData responde a el command TABLEs
		ResultSet rlSet = dbmd.getTables(getDBCatalog(), null, "%", new String[] 
		{"TABLE"});
		//stmQry.close(); /// no se pueden cerrar porque mata el resultSet
		//oConn.close(); /// no se pueden cerrar porque mata el resultSet
		return rlSet ;
		}catch(SQLException E) {
		ActionMessage = E.toString();
		return null;
		}catch(Exception D) {
		ActionMessage = D.toString();
		return null;
		}
	}

	public boolean execSQL(String p_strSQLStm) {
		try {
			stmQry.execute(p_strSQLStm);
			return true;
		}catch(SQLException E) {
			ActionMessage = E.toString();
			return false;
		}catch(Exception D) {
			ActionMessage = D.toString();
			return false;
		}	
	}
	public void ShowDbObject(Scanner pin) {
		try {
			ResultSet oTbl = getMeta();
			int k = 1;
			// pasa el ResultSet a un Array List
			ArrayList<String> myList = new ArrayList<String>();
			while (oTbl.next()) {
				myList.add(k + ".- " + oTbl.getString("TABLE_NAME"));
				k++;
			}
			oTbl.close();
			short opt = 0;
			do {
				System.out.flush(); // limpia la pantalla
				// imprime un menu con las tablas como opciones
				for (String row : myList ) {
					System.out.println(row);
				}
				System.out.println("\n\t\t Entre la Opcion:");
				opt = pin.nextShort();
				if (opt >= 1 && opt < myList.size()) {
					ShowTable(pin, myList.get(opt-1), "","");
					System.out.print("\nPulse any key to cntinue.....:");
					pin.next();
				}
			}while(opt!=0);
			}catch(Exception E) {
				//ActionMessage = E.toString();
				System.out.println(E.toString()+ ActionMessage +".... continuar!!!:");
				pin.next();
			}
		}

	public void CloseDB() {
		try {
			oConn.close();
			stmQry.close();
		}catch(Exception E){
			
		}
	}
	public String getActionMessage() {
		return ActionMessage;
	}
	private void ShowTable(Scanner pin, String pstrName, String filterCols, String filterVal) throws Exception {
		if (!pstrName.matches("[1-9]{1,2}.*")) 
			return;
		String[] sql = pstrName.split("-");
		ArrayList<String> oCriteria = new ArrayList<String>();
		System.out.println(pstrName+":-----------------------------------------------------------");
		StringBuilder stm = new StringBuilder("SELECT * FROM " + sql[1]);
		if (!filterCols.equals(""))
			stm.append("\n WHERE " + filterCols + " LIKE '%"+ filterVal +"%'");
		stm.append(" LIMIT 0,10;");
		ResultSet rsSet = getData(stm.toString());
		ResultSetMetaData rsMeta = rsSet.getMetaData(); 
		//get and print the column names, column indexes start from 1
		for(int i = 1; i<= rsMeta.getColumnCount(); i++) {
			//String columnName= ;
			System.out.print(rsMeta.getColumnName(i) + "\t| ");
			if (isStringType(rsMeta.getColumnType(i)))
				oCriteria.add(rsMeta.getColumnName(i));
		}//endfor
		System.out.println();
		while (rsSet.next()) {
			for(int i= 1; i <= rsMeta.getColumnCount(); i++) {
				// Aprovechamos la propiedad getObject para leer cualquier tipo dedatos
				System.out.print(rsSet.getObject(i) + "\t| "); 
			}//endfor
			System.out.println("\n------------------------------------------------------------------");
		}
		rsSet.close();
		short vopt = 0;
		System.out.println("Desea filtrar la data[1|0]?");
		vopt = pin.nextShort();
		// FILTRA LA DATA ACTUAL BASADO EN LAS COLs string
		if (vopt == 1) {
			System.out.println("Entre el criterio del filtro:");
			String sCri = pin.next();
			char separa ='(';
			String allcolcri="Concat";
			for (String c : oCriteria) {
				allcolcri+= separa + c;
				separa = ',';
			}
			allcolcri += ")"; 
			ShowTable(pin, pstrName, allcolcri ,sCri);
		}
		//rsMeta;
	}
	public boolean isStringType(int ptype) {
		switch (ptype) {
			case java.sql.Types.CHAR:
			case java.sql.Types.VARCHAR:
			case java.sql.Types.NVARCHAR:
				return true;
			default:
				return false;
		}
	}
	// getters simple de current Catalog.
	public String getDBCatalog() {
		return _vCatalog; 
	}
	/*public boolean insertData(String tableName, HashMap<String, Object> data) {
	    try {
	        StringBuilder columns = new StringBuilder();
	        StringBuilder values = new StringBuilder();
	        for (String column : data.keySet()) {
	            columns.append(column).append(",");
	            values.append("'").append(data.get(column)).append("',");
	        }
	        columns.deleteCharAt(columns.length() - 1); // Eliminar la última coma
	        values.deleteCharAt(values.length() - 1); // Eliminar la última coma

	        String query = "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES (" + values.toString() + ")";
	        return execSQL(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	    }
	public boolean updateData(String tableName, HashMap<String, Object> data, String condition) {
	    try {
	        StringBuilder setClause = new StringBuilder();
	        for (String column : data.keySet()) {
	            setClause.append(column).append("='").append(data.get(column)).append("',");
	        }
	        setClause.deleteCharAt(setClause.length() - 1); // Eliminar la última coma

	        String query = "UPDATE " + tableName + " SET " + setClause.toString() + " WHERE " + condition;
	        return execSQL(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean deleteData(String tableName, String condition) {
	    try {
	        String query = "DELETE FROM " + tableName + " WHERE " + condition;
	        return execSQL(query);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}*/
}


