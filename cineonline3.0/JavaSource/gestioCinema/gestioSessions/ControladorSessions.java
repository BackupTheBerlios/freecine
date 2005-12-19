package gestioCinema.gestioSessions;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.Pelicula;
import gestioCinema.gestioSales.ControladorSales;
import gestioCinema.gestioSales.Sala;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;




public class ControladorSessions extends ControladorSales{
	
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

			Sala sala = new Sala();
			sala.setId(rs.getInt(4));
			Pelicula pelicula = new Pelicula();
			pelicula.setId(rs.getInt(5));
			
			sessio.setAll(
					rs.getInt(1),						// id de sessio
					rs.getString(2),					// String de la data --> getDate --> Sessio:new Date()
					rs.getDouble(3),					// String amb preu
					pelicula,
					sala,
					new Vector());				
			sessions.addElement(sessio);
			
			
		}
		
	/*
	 * 
	 * 
	 * 										<!-- <input type="Hidden" name="nom_pelicula" value="<%= ses.getPelicula().getTitol() %>" />
										<input type="Hidden" name="idpelicula" value="<%= ses.getPelicula().getId() %>" />
										<input type="Hidden" name="nom_sala" value="<%=ses.getSala().getNomSala() %>" />
										<input type="Hidden" name="idsala" value="<%= ses.getSala().getId() %>" />
										<input type="Hidden" name="data" value="<%= ses.getDataHora()%>" /> -->
	 * 
	 */	
		Iterator itSess = sessions.iterator();	
		Sessio sessio;
		
		while(itSess.hasNext()){
			sessio = (Sessio)itSess.next();
			sessio.setPelicula(getPelicula(sessio.getPelicula().getId()));
			sessio.setSala(getSala(sessio.getSala().getId()));
			sessio.setButaquesSessio(getButaquesSessio(sessio.getId())); 
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
	 * 
	 * @param id
	 * @return
	 * @throws ControladorException
	 */
	
	public Sessio getSessio(int id) throws ControladorException{
		ResultSet rs;
		String query =	"SELECT " +
						"id, " +
						"data_hora_inici, " +
						"preu, " +
						"id_sala, " +
						"id_pelicula " +
						"FROM sessio " +
						"WHERE id = "+id;
		
		try {
			rs = selectRS(query);
			Sessio sessio = (Sessio)toVectorSessions(rs).elementAt(0);
			return sessio;
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[getSessio(int id)] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[CControladorSessions]:[getSessio(int id)] Error SQL: "+query+"\n"+e.getMessage());
		}
	}
	/**
	 * 
	 * @param id
	 * @throws ControladorException
	 */
	public void eliminarSessio(String id) throws ControladorException {
		String query ="DELETE FROM sessio WHERE id="+id;
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[eliminarSessio(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSessions]:[eliminarSessio(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param sessio
	 * @throws ControladorException
	 */
	public void modificarSessio(Sessio sessio) throws ControladorException {
		String query ="UPDATE PELICULA SET "+sessio.sqlUpdate()+" WHERE id = "+sessio.getId();
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[modificarSessio(Sessio Sessio)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSessions]:[modificarSessio(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param sessio
	 * @throws ControladorException
	 */
	public void afegirSessio(Sessio sessio) throws ControladorException{
		
		String query ="INSERT INTO sessio "+sessio.sqlInsert();
		System.err.println("[insert]"+sessio.sqlInsert());
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[afegirSessio(Sessio sessio)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSessions]:[afegirSessio(Pelicula sessio)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public Vector toVectorButaquesSessio(ResultSet rs) throws SQLException{
		/*
		 * Converteix un ResultSet a Vector de Pelicules
		 */
	
		Vector butaques = new Vector();
		while (rs.next()) {
			ButacaSessio butacaSessio = new ButacaSessio();
			butacaSessio.setAllButaca(
				rs.getInt(1), //num de butaca
				rs.getInt(2), //num de fila
				rs.getInt(3), //num de columna
				rs.getBoolean(4), //operativa
				rs.getBoolean(5), //compradaReservada
				rs.getBoolean(6));//pagada

			butaques.addElement(butacaSessio);
		}
		return butaques;	
	}
	
	public Vector getButaquesSessio(int idSessio) throws ControladorException {
		// Aqui es tindra de fer la vista que ens dongui, ara de mentres fem una copia de la sessio i li posem un camp més al vector
		ResultSet rsSales;
		String query = "SELECT * FROM butaca_sessio (" + idSessio + ")";
		
		try {
			rsSales = selectRS(query);
			return toVectorButaquesSessio(rsSales);
		} catch (SQLException e) {
			System.err.println("[ControladorSessio]:[getButaquesSessio(int idSala)] Error SQL: "+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorSessio]:[getButaquesSessio(int idSala)] Error SQL: Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getSessionsCartellera(String data) throws ControladorException{
		ResultSet rs;
		String query = "SELECT " +
							"id, " +
							"data_hora_inici, " +
							"preu, " +
							"id_sala, " +
							"id_pelicula " +
						"FROM sessio " +
						"WHERE data_hora_inici LIKE '" + data + "%' " +
						" ORDER BY sessio.id_sala, sessio.id_pelicula, data_hora_inici ASC ";
		System.err.println("[getSessioCartellera]"+query);
		try {
			rs = selectRS(query);
			Vector sessions = toVectorSessions(rs);

			
			
			return sessions;
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[getSessionsCartellera] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorSessions]:[getSessionsCartellera] Error SQL:"+query+"\n"+e.getMessage());
		}

		
	}
	
	
/*CONTROLADOR PELICULES*/
	
	public Vector toVectorPelicules(ResultSet rs) throws SQLException {
		/*
		 * Converteix un ResultSet a Vector de Pelicules
		 */
		Vector pelicules = new Vector();
		
		while (rs.next()) {
			Pelicula pelicula = new Pelicula();
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
				
			pelicules.addElement(pelicula);
		}
		return pelicules;	
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
			return pelicula;
		} catch (SQLException e) {
			System.err.println("[ControladorSessions]:[getPelicula] Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSessions]:[getPelicula] Error SQL: "+e.getMessage());
		}
		
	}
	
	public Vector getPelicules() throws ControladorException{
		ResultSet rsPelicules;
		/*String camps = Pelicula.getCamps().toString();*/
		/*String query = "SELECT "+camps.substring(4,camps.length()-2)+" FROM Pelicula";*/
		try {
			
			String query = "SELECT  " +
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
							"FROM Pelicula ORDER BY titol ASC";
			
			rsPelicules = selectRS(query);
		
			return toVectorPelicules(rsPelicules);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[getPelicules] Error SQL:"+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[getPelicules] Error SQL: "+e.getMessage());
		}
	}
	
/*FI CONTROLADOR PELICULES*/
	
	
}
