/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gestioCinema.gestioSessions;

import gestioCinema.gestioSales.Butaca;

/**
 * @author Ivan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ButacaSessio extends Butaca{
	private boolean compradaReservada;
	private boolean pagada;
	
	public ButacaSessio(){
		super();
		compradaReservada = false;
		pagada = false;
	}
	
	public void setAllButaca(
			int numButaca, 
			int numFila, 
			int numColumna, 
			boolean operativa, 
			boolean compradaReservada,
			boolean pagada){
		setNumButaca(numButaca);
		setNumFila(numFila);
		setNumColumna(numColumna);
		setOperativa(operativa);
		setCompradaReservada(compradaReservada);
		setPagada(pagada);
	}

	public boolean getCompradaReservada() {
		return compradaReservada;
	}

	public void setCompradaReservada(boolean compradaReservada) {
		this.compradaReservada = compradaReservada;
	}

	public boolean isPagada() {
		return pagada;
	}

	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}
	
	
}
