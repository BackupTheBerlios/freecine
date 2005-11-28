package gestioCinema.gestioPelicules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;

public class ControladorPelicules extends Controlador{ 
	public ControladorPelicules() throws ControladorException {
		super();
	}
	
	public void afegirPelicula(Pelicula pelicula){
		
	}
	
	public Vector toVectorPelicules(ResultSet rs) throws SQLException {
		/*
		 * Converteix un ResultSet a Vector de Pelicules
		 */
		Vector pelicules = new Vector();
		while (rs.next()) {
			Pelicula pelicula = new Pelicula();
			pelicula.setAll(
				1,
				rs.getString(1),
				rs.getString(2),
				1982,
				rs.getInt(3),
				rs.getString(4),
				rs.getInt(5),
				rs.getString(6),
				rs.getString(7),
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12),
				rs.getString(13),
				rs.getString(14),
				rs.getString(15));
				
			pelicules.addElement(pelicula);
		}
		return pelicules;	
	}

	public Vector getPelicules() throws ControladorException{
		ResultSet rsPelicules;
		String camps = Pelicula.getCamps().toString();
		
		/*rsPelicules = selectRS("SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula");*/
		rsPelicules = selectRS(	"SELECT  " +
								"titol, " +
								"titol_original, " +
								"durada, " +
								"nacionalitat, " +
								"edat_recomanada, " +
								"tius_color, " +
								"tipus_so, " +
								"genere, " +
								"director, " +
								"guionista, " +
								"productor, " +
								"actors, " +
								"sinopsis, " +
								"url_web, " +
								"url_imatge " +
								"FROM Pelicula");
		
		try {
			return toVectorPelicules(rsPelicules);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[toVectorPelicules] Error SQL:"+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[toVectorPelicules] Error SQL: "+e.getMessage());
		}
	}

}
