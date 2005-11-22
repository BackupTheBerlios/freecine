/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sessio;

import sala.Butaca;

/**
 * @author Oriol
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ButacaSessio extends Butaca{
	private final int NO_DISPONIBLE = 0;
	private final int DISPONIBLE = 1;
	private final int RESERVADA = 2;	
	private final int COMPRADA = 3;
	
	private int estat;
	
	public ButacaSessio(){
		this.estat=1;
	}
	
	public ButacaSessio(int numButaca, int numFila, int numColumna, boolean operativa){
		super(numButaca, numFila, numColumna, operativa);
		this.estat = 1;
	}

	public int getEstat() {
		return estat;
	}
	
	public void setEstat(int estat) {
		this.estat = estat;
	}
}
