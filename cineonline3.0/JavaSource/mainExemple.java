
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;


import gestioCinema.Controlador;
import gestioCinema.ControladorException;

public class mainExemple {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		try {
			Controlador ctrl = new Controlador();
			ctrl = new Controlador();
			Vector vc = null;
			try {
				vc = ctrl.selectVector("SELECT * FROM PELICULA");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator it= vc.iterator();
			while(it.hasNext()){
				Iterator it2 = ((Vector)it.next()).iterator();
				while(it2.hasNext()){
					String str = ""+it2.next();
					if(!str.equals("null")){
						System.out.print(str+"\t ");
					}
				}
				System.out.println("");
			}
		} catch (ControladorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
