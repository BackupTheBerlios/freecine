/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gestioCinema.gestioSales;

/**
 * @author Ivan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Butaca {
	private int numButaca;
	private int numFila;
	private int numColumna;
	private boolean operativa;
	
	public Butaca(){
		numButaca=-1;
		numFila=-1;
		numColumna=-1;
		operativa=false;
	}
	
	public void setAll(int numButaca, int numFila, int numColumna, boolean operativa){
		this.numButaca = numButaca;
		this.numFila = numFila;
		this.numColumna = numColumna;
		this.operativa = operativa;
	}
	
	public int getNumButaca() {
		return numButaca;
	}
	
	public void setNumButaca(int numButaca) {
		this.numButaca = numButaca;
	}
	public int getNumColumna() {
		return numColumna;
	}
	public void setNumColumna(int numColumna) {
		this.numColumna = numColumna;
	}
	public int getNumFila() {
		return numFila;
	}
	public void setNumFila(int numFila) {
		this.numFila = numFila;
	}
	public boolean getOperativa() {
		return operativa;
	}
	public void setOperativa(boolean operativa) {
		this.operativa = operativa;
	}
	
}
