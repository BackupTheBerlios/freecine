package mains;

import java.util.Iterator;
import java.util.Vector;

import sala.ControladorSalas;
import sala.Sala;

/*
 * Created on 12-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Ivan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class mainSales {
	public static void main(String []args){
		ControladorSalas ctrlSala = new ControladorSalas();
		Sala sala = new Sala(1,"rosa","romantica");
		Sala sala1 = new Sala(2,"verda","novetats");
		sala.generarButaques(4,4);
		sala1.generarButaques(6,6);
		sala.setButacaNoOperativaId(1);
		sala.setButacaNoOperativaFilaColumna(2,3);
		sala.setButacaOperativaId(1);
		sala.setButacaOperativaFilaColumna(2,3);
		sala.imprimirSala();
		sala1.imprimirSala();
		
		ctrlSala.afegirSala(sala);
		ctrlSala.afegirSala(sala1);
		
		Vector vSalas = ctrlSala.llistarSales();
		Iterator itSalas= vSalas.iterator();
		System.out.println(itSalas.hasNext());
		System.out.println(ctrlSala.getNumSales());
		
		while(itSalas.hasNext()){
			System.out.println(((Sala)itSalas.next()).getNomSala());
		}	
	}

}
