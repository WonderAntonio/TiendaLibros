package tiendalibros;

import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

public class Titles {
	public String titleid;
	public String title;
	public String type;
	public String pubid;
	public Double price;
	public Double advance;
	public BigDecimal royalty;
	public BigDecimal ytdsales;
	public String notes;
	public Timestamp pubdate;
	
	public Titles() {
		this.titleid = "";
		this.title = "";
		this.type = "";
		this.pubid = "";
		this.price = 0.0;
		this.advance = 0.0;
		this.royalty = BigDecimal.ZERO;;
		this.ytdsales = BigDecimal.ZERO;;
		this.notes = "";
		this.pubdate = new Timestamp(new Date().getTime());
	}
	public Titles(String tid, String t, String ty, String pid, Double p, Double a, BigDecimal r, BigDecimal y, String n, Timestamp pd) {
		this.titleid = tid;
		this.title = t;
		this.type = ty;
		this.pubid = pid;
		this.price = p;
		this.advance = a;
		this.royalty = r;
		this.ytdsales = y;
		this.notes = n;
		this.pubdate = pd;;
	}
}
