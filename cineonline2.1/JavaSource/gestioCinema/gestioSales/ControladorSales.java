/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gestioCinema.gestioSales;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.Pelicula;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Ivan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ControladorSales extends Controlador{
	public ControladorSales() throws ControladorException {
		super();
	}
	
	public Vector toVectorSales(ResultSet rs) throws SQLException {
		/*
		 * Converteix un ResultSet a Vector de Pelicules
		 */
		Vector sales = new Vector();
		while (rs.next()) {
			Sala sala= new Sala();
			int idSala = rs.getInt(1);
			sala.setAll(
				idSala,
				rs.getString(2),
				rs.getInt(3),
				rs.getInt(4),
				rs.getString(5),
				getButaques(idSala));
				
			sales.addElement(sala);
		}
		return sales;	
	}

	public Vector toVectorButaques(ResultSet rs) throws SQLException{
		/*
		 * Converteix un ResultSet a Vector de Pelicules
		 */
		Vector butaques = new Vector();
		while (rs.next()) {
			Butaca butaca = new Butaca();
			butaca.setAll(
				rs.getInt(1),
				rs.getInt(2),
				rs.getInt(3),
				rs.getBoolean(4));
				
			butaques.addElement(butaca);
		}
		return butaques;	
	}
	
	public Vector getSales() throws ControladorException{
		return null;
	}
	
	public Sala getSala(int idSala) throws ControladorException{
		return null;
	}
	
	public void eliminarSala(int id) throws ControladorException{
	}
	
	public void modificarSala(Sala sala) throws ControladorException{
	}
	
	public void afegirSala(Sala sala) throws ControladorException{
	}
	
	
	public Butaca getButacaSala(int idButaca, int idSala){
		return null;
	}
	
	public Vector getButaques(int idSala){
		return null;
	}
}
