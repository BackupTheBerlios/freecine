package gestioCompraReserva;

import gestioCinema.gestioSessions.ButacaSessio;
import gestioCinema.gestioSessions.Sessio;


public class CompraReserva{
	private int id;
	private boolean pagada;
	private String numeroReserva;
	private String data;
	private Sessio sessio;
	private ButacaSessio butacaSessio;
	

	public CompraReserva(){
		id = -1;
		pagada = false;
		numeroReserva = "";
		data = "";
		sessio = new Sessio();
		butacaSessio = new ButacaSessio();
	}
	
	public void setAll(
			 int id,
			 boolean pagada,
			 String numeroReserva,
			 String data,
			 Sessio sessio,
			 ButacaSessio butacaSessio){
		
		this.id = id;
		this.pagada = pagada;
		this.numeroReserva = numeroReserva;
		this.data = data;
		this.sessio = sessio;
		this.butacaSessio = butacaSessio;	
	}


	public ButacaSessio getButacaSessio() {
		return butacaSessio;
	}


	public void setButacaSessio(ButacaSessio butacaSessio) {
		this.butacaSessio = butacaSessio;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
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
	
}

