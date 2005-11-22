/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package sala;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author Ivan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ControladorSalas {	
	private Vector sales;
	private int numSales;
	
	public int getNumSales(){
		return numSales;
	}
	
	public ControladorSalas(){
		sales = new Vector();
		numSales = 0;
	}
	
	public Vector llistarSales(){
		return sales;
	}
	
	
	public void afegirSala(Sala sala){
		sales.add(sala);
		numSales++;
	}
	
	public void actualitzarSala(Sala sala){
		try {
			getSala(sala.getId()).setSala(sala);
		} catch (ControladorSalasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void eliminarSala(int idSala) throws ControladorSalasException{
		sales.remove(getSala(idSala));
	}
	
	public void eliminarSala(Sala sala) throws ControladorSalasException{
		if(!sales.remove(sala)){
			throw new ControladorSalasException("[ControladorSalas]:[getSala] -> " +
					"Error: No s'ha trobat cap Sala = "+sala);
		}
	}
	
	public Sala getSala(int idSala) throws ControladorSalasException {
		Iterator itSales = sales.iterator();
		Sala sala;
		while(itSales.hasNext()){
			sala =(Sala) itSales.next();
			if(sala.getId()==idSala){
				return sala;
			}
		}
		throw new ControladorSalasException("[ControladorSalas]:[getSala] -> " +
				"Error: No s'ha trobat cap Sala amb el id = "+idSala);
	}
}
