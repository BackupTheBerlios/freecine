import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.text.StyledEditorKit.ItalicAction;

import gestioCinema.Controlador;

public class mainExemple {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Controlador ctrl = new Controlador();
		try {
			
			Vector vc = ctrl.select("SELECT * FROM PELICULA");
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}

}
