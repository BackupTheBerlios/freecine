package gestioCompraReserva;

import java.util.Iterator;
import java.util.Vector;

import gestioCinema.gestioSessions.ButacaSessio;
import gestioCinema.gestioSessions.Sessio;


public class CompraReserva{
	private int id;						//id
	private boolean pagada;				//pagada
	private String numeroReserva;		//codi_entrega
	private String dataHora;			//data_hora
	private Sessio sessio;				//id_sessio
	private ButacaSessio butacaSessio;	//id_butaca
	

	public CompraReserva(){
		id = -1;
		pagada = false;
		numeroReserva = "";
		dataHora = "";
		sessio = new Sessio();
		butacaSessio = new ButacaSessio();
	}
	
	public void setAll(
			 int id,
			 boolean pagada,
			 String numeroReserva,
			 String dataHora,
			 Sessio sessio,
			 ButacaSessio butacaSessio){
		
		this.id = id;
		this.pagada = pagada;
		this.numeroReserva = numeroReserva;
		this.dataHora = dataHora;
		this.sessio = sessio;
		this.butacaSessio = butacaSessio;	
	}


	public ButacaSessio getButacaSessio() {
		return butacaSessio;
	}


	public void setButacaSessio(ButacaSessio butacaSessio) {
		this.butacaSessio = butacaSessio;
	}


	public String getDataHora() {
		return dataHora;
	}


	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNumeroReserva() {
		return numeroReserva;
	}


	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}


	public boolean isPagada() {
		return pagada;
	}


	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}


	public Sessio getSessio() {
		return sessio;
	}

	public void setSessio(Sessio sessio) {
		this.sessio = sessio;
	}
	
	public Vector getCamps(){
		Vector campsCR;
		campsCR = new Vector();
		campsCR.add("id");
		campsCR.add("pagada");
		campsCR.add("codi_entrega");
		campsCR.add("id_sessio");
		campsCR.add("id_butaca");
		
		return campsCR;
	}
	
	
	public Vector getValors(){
		Vector valorsCR;
		valorsCR = new Vector();
		
		valorsCR.add(""+id);
		valorsCR.add(""+pagada);
		valorsCR.add(""+numeroReserva);
		valorsCR.add(""+sessio.getId());
		valorsCR.add(""+butacaSessio.getNumButaca());
		
			
		return valorsCR;
	}
	
	public Vector getFormats(){	
		Vector valorsCR;
		valorsCR = new Vector();
		
		valorsCR.add(""+id);
		valorsCR.add(""+pagada+"");
		valorsCR.add(""+numeroReserva);
		valorsCR.add(""+sessio.getId());
		valorsCR.add(""+butacaSessio.getNumButaca());
		
		
			
		return valorsCR;
	}

	public String sqlInsert(){
		Iterator itCamps = getCamps().iterator();
		Iterator itFormats = getFormats().iterator();
		String values = "(";
		String valor ="";
		int i=0;
		while(itCamps.hasNext()){
			i++;
			valor= (String)itCamps.next();			
			if (i==1)
			{
				
				values+=(String) itCamps.next();
				
			}else{
				
				values+=", "+valor;
				
			}
		}
		
		
		values+=") VALUES (";
		
		i=0;
		valor="";
		while(itFormats.hasNext()){
			i++;
			valor= (String)itFormats.next();
			
			if (i==1)
			{
				
				values+=""+ itFormats.next();
				
			}else{
				
				values+=", "+valor;
				
			}
		}
		values+=")";
		
		return values;
	}
	
	public String sqlUpdate(){
		Iterator itCamps = getCamps().iterator();
		Iterator itValors = getFormats().iterator();
		String values = "";
		
		if(itCamps.hasNext() && itValors.hasNext()){
			/*Treiem el id perque el id no es pot modificar*/
			itCamps.next();
			itValors.next();
		}
		
		while(itCamps.hasNext() && itValors.hasNext()){
			values = values + itCamps.next() +" = "+itValors.next();
			if(itValors.hasNext()){
				values = values +", ";
			}
		}
		return values;
	}

	
	public String toString(){
		return ""+getValors();
	}
	
}

