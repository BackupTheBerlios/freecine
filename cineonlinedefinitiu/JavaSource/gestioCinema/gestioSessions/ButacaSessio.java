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
	
	public ButacaSessio(){
		super();
	}
	
	public void setAllButaca(
			int numButaca, 
			int numFila, 
			int numColumna, 
			boolean operativa, 
			boolean compradaReservada){
		setNumButaca(numButaca);
		setNumFila(numFila);
		setNumColumna(numColumna);
		setOperativa(operativa);
		setCompradaReservada(compradaReservada);
	}

	public boolean getCompradaReservada() {
		return compradaReservada;
	}

	public void setCompradaReservada(boolean compradaReservada) {
		this.compradaReservada = compradaReservada;
	}
}
