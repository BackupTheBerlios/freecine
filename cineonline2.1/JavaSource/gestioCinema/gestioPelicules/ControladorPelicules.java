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
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula";*/
		try {
			String query = "SELECT  " +
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
							"FROM Pelicula";
			
			rsPelicules = selectRS(query);
		
			return toVectorPelicules(rsPelicules);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getPelicules] Error SQL:"+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getPelicules] Error SQL: "+e.getMessage());
		}
	}

	public Pelicula getPelicula(int id) throws ControladorException{
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula WHERE id="+id;";*/
		try {
			String query ="SELECT  " +
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
						" FROM Pelicula" +
						" WHERE id="+id;
								
			Pelicula pelicula;
		
			pelicula = (Pelicula) (toVectorPelicules(selectRS(query))).firstElement();
			return pelicula;
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getPelicula] Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getPelicula] Error SQL: "+e.getMessage());
		}
		
	}
	
	

	public void eliminarPelicula(int id) throws ControladorException {
		String query ="DELETE FROM PELICULA WHERE id="+id;
		update(query);
	}
	
	public void modificarPelicula(Pelicula pelicula) throws ControladorException {
		String query ="UPDATE PELICULA SET "+pelicula.sqlUpdate()+" WHERE id = "+pelicula.getId();
		update(query);
	}
	
	public void afegirPelicula(Pelicula pelicula) throws ControladorException{
		String query ="INSERT INTO PELICULA "+pelicula.sqlInsert();
		update(query);
	}
}
