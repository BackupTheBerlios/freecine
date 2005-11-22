package mains;

import sessio.DataIncorrectaException;
import sessio.FormatDataHora;


public class Prova {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(FormatDataHora.sumarDies("31/12/05",2));
		} catch (DataIncorrectaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
