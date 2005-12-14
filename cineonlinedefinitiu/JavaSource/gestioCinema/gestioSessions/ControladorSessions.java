package gestioCinema.gestioSessions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;
import gestioCinema.gestioSales.Sala;

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
			 * 	id integer
			 * 	data_hora_inici: TimeStamp w/o Timezone
			 * 	data_hora_fi:	TimeStamp w/o Timezone
			 * 	preu:	money
			 * 	id_sala: int
			 * 	id_pelicula: int 
			 */
						
			sessio.setAll(
					rs.getInt(1),		// id de sessio
					rs.getString(2),	// String de la data
					rs.getString(2),	// String amb hora de inici
					null,				// Pelicula
					null);				// Sala
			
			sessions.addElement(sessio);
			System.err.println("[toVectorSessions]:"+sessio);
			
		}
		return sessions;	
	}
	
	
	
}
