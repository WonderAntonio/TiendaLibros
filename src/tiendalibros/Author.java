package tiendalibros;

public class Author {
	public String auid;
	public String aulname;
	public String aufname;
	public String auphone;
	public String auaddress;
	public String aucity;
	public String austate;
	public String auzip;
	public boolean aucontract;
	
	public Author() {
		this.auid = "";
		this.aulname = "";
		this.aufname = "";
		this.auphone = "";
		this.auaddress = "";
		this.aucity = "";
		this.austate = "";
		this.auzip = "";
		this.aucontract = false;
	}
	public Author(String aid, String aln, String afn, String aph, String aadr, String aci, String ast, String azi, boolean aco) {
		this.auid = aid;
		this.aulname = aln;
		this.aufname = afn;
		this.auphone = aph;
		this.auaddress = aadr;
		this.aucity = aci;
		this.austate = ast;
		this.auzip = azi;
		this.aucontract = aco;
	}
}
