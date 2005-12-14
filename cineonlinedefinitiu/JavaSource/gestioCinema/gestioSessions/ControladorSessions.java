package gestioCinema.gestioSessions;

import gestioCinema.ControladorException;
import gestioCinema.gestioPelicules.Pelicula;
import gestioCinema.gestioSales.ControladorSales;

import java.sql.ResultSet;
import java.sql.SQLException;
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
			System.err.println("[toVectorSessions]");
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
					rs.getDouble(3),						// String amb preu
					null,//getPelicula(rs.getInt(4)),			// Pelicula
					null,//getSala(rs.getInt(5)),
					null);				
			System.err.println("[toVectorSessions]"+sessio);
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
							"preu, " +
							"id_sala, " +
							"id_pelicula " +
						"FROM sessio";
		
		try {
			System.err.println("[getSession]");
			rs = selectRS(query);
			System.err.println("[getSession]"+rs);
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
			System.err.println("[getSession]"+rs);
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
				rs.getInt(1),
				rs.getInt(2),
				rs.getInt(3),
				rs.getBoolean(4),
				true);//s'ha de posar adecuadament
				
			
			butaques.addElement(butacaSessio);
		}
		return butaques;	
	}
	
	public Vector getButaquesSessio(int idSala) throws ControladorException {
		ResultSet rsSales;
		String query = "SELECT " +
							"id, " +
							"num_fila, " +
							"num_columna, " +
							"operativa " +
						"FROM butaca " +
						"WHERE id_sala = "+idSala;
		
		try {
			rsSales = selectRS(query);
			return toVectorButaquesSessio(rsSales);
		} catch (SQLException e) {
			System.err.println("[ControladorSessio]:[getButaquesSessio(int idSala)] Error SQL: "+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorSessio]:[getButaquesSessio(int idSala)] Error SQL: Error SQL:"+query+"\n"+e.getMessage());
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
			System.err.println("\n[ControladorSessions]:[getPelicula] -> pelicula"+pelicula+"\n");
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
