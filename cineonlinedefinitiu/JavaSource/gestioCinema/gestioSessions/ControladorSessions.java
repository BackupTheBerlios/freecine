package gestioCinema.gestioSessions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;
import gestioCinema.gestioSales.*;
import gestioCinema.gestioPelicules.*;


public class ControladorSessions extends Controlador{
	
	public ControladorSessions() throws ControladorException  {
		super();
	}
	
	/**
	 * Funció que converteix un RS a un vector de sessions.
	 * 
	 * @param rs: Resultset que conté sessions
	 * @return Vector de Objectes, en aquest cas de sessions.
	 * @throws SQLException
	 * @throws ControladorException
	 */	
	public Vector toVectorSessions(ResultSet rs) throws SQLException, ControladorException {
		
		Vector sessions = new Vector();
		while (rs.next()) {
			Sessio sessio= new Sessio();
			
			/*
			 *	taula sessio SQL
			 *
			 * 	1. id:					integer
			 * 	2. data_hora_inici:		TimeStamp w/o Timezone
			 * 	3. data_hora_fi:		TimeStamp w/o Timezone
			 * 	4. preu:				money
			 * 	5. id_sala:				integer
			 * 	6. id_pelicula:			int
			 */
					
			sessio.setAll(
					rs.getInt(1),						// id de sessio
					rs.getString(2),					// String de la data --> getDate --> Sessio:new Date()
					rs.getString(2),					// String amb hora de inici
					getPelicula(rs.getInt(6)),			// Pelicula
					getSala(rs.getInt(5)));				// Sala
			
			sessions.addElement(sessio);
			System.err.println("[toVectorSessions]:"+sessio);
			
		}
		return sessions;	
	}
	
	/**
	 * getSales Retorna un Vector amb totes les sessions existents a la BBDD.
	 * 
	 * @return Vector que conté totes les sessions.
	 * @throws ControladorException
	 */
	
	public Vector getSessions() throws ControladorException{
		ResultSet rs;
		String query = "SELECT " +
							"id, " +
							"data_hora_inici, " +
							"data_hora_fi, " +
							"preu, " +
							"id_sala, " +
							"id_pelicula " +
						"FROM sessio";
		
		try {
			rs = selectRS(query);
			return toVectorSessions(rs);
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[getSessions] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorSessions]:[getSessions] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	
	
	/**
	 * getSala: Retorna una sala amb l'id subministrat
	 * 
	 * @param idSala: Codi de la sala que volem obtenir
	 * @return Sala amb id = idSala
	 * @throws ControladorException
	 */
	
	public Sala getSala(int idSala) throws ControladorException{
		ResultSet rs;
		
		
		String query = "SELECT " +
							"id, " +
							"nom, " +
							"num_butaques, " +
							"num_columnes, " +
							"num_files, " +
							"descripcio " +
						"FROM sala " +
						"WHERE id="+idSala;
		
		try {
			Sala sala= new Sala();
			rs = selectRS(query);
			while (rs.next()) {
				sala.setAll(
					rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getInt(4),
					rs.getInt(5),
					rs.getString(6),
					null);
			}		
			System.err.println("\n[ControladorSessions]:[getSala] -> sala"+sala+"\n");
			return sala;
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[getSala(int idSala)] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorSessions]:[getSala(int idSala)] Error SQL: "+query+"\n"+e.getMessage());
		}
	}
	
	/**
	 * getPelicula: Retorna una pelicula amb l'id subministrat
	 * 
	 * @param id: Codi de la pelicula que volem obtenir
	 * @return Pelicula amb id = id
	 * @throws ControladorException
	 */
	public Pelicula getPelicula(int id) throws ControladorException{
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula WHERE id="+id;";*/
		try {
			ResultSet rs;
			String query ="SELECT  " +
								"id, " +
								"titol, " +
								"titol_original, " +
								"anny, " +
								"durada, " +
								"id_nacionalitat, " +
								"edat_recomanada, " +
								"tipus_color, " +
								"tipus_so, " +
								"id_genere, " +
								"director, " +
								"guionista, " +
								"productor, " +
								"actors, " +
								"sinopsis, " +
								"url_web, " +
								"url_imatge " +
							"FROM Pelicula "+
							"WHERE id="+id;
								
			Pelicula pelicula= new Pelicula();
			rs = selectRS(query);
			while (rs.next()) {
				pelicula.setAll(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12),
						rs.getString(13),
						rs.getString(14),
						rs.getString(15),
						rs.getString(16),
						rs.getString(17));
			}		
			System.err.println("\n[ControladorSessions]:[getPelicula] -> pelicula"+pelicula+"\n");
			return pelicula;
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[getPelicula] Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSessions]:[getPelicula] Error SQL: "+e.getMessage());
		}
		
	}
	
	
}
