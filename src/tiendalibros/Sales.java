package tiendalibros;

import java.sql.Timestamp;
import java.util.Date;

public class Sales {
	public String storid;
	public String ordnum;
	public Timestamp orddate;
	public int qty;
	public String payterms;
	public String titleid;
	
	public Sales() {
		this.storid = "";
		this.ordnum = "";
		this.orddate = new Timestamp(new Date().getTime());
		this.qty = 0;
		this.payterms = "";
		this.titleid = "";
	}
	public Sales(String st, String or, Timestamp ord, int q, String p, String t) {
		this.storid = st;
		this.ordnum = or;
		this.orddate = ord;
		this.qty = q;
		this.payterms = p;
		this.titleid = t;
	}
}
