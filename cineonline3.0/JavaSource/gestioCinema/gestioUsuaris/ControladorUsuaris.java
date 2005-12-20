package gestioCinema.gestioUsuaris;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;
import gestioCinema.gestioSales.Sala;

public class ControladorUsuaris extends Controlador{

	public ControladorUsuaris() throws ControladorException {
		super();
	}
	
	public Vector toVectorUsuaris(ResultSet rs) throws SQLException {
		/* 	
		 * Converteix un ResultSet a Vector de Usuaris
		 */
		

		Vector usuaris = new Vector();
		while (rs.next()) {
			Usuari usuari= new Usuari();
			usuari.setAll(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getInt(4));
			usuaris.addElement(usuaris);
		}
		return usuaris;	
	}
	
	public Vector getUsuaris() throws ControladorException{
		ResultSet rsUsuari;
		String query = "SELECT " +
							"id, " +
							"login, " +
							"password, " +
							"rol, " +
						"FROM treballador " +
						"ORDER BY loguin";
		
		try {
			rsUsuari = selectRS(query);
			return toVectorUsuaris(rsUsuari);
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[getUsuaris] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[getUsuaris] Error SQL:"+query+"\n"+e.getMessage());
		}	
	}
	
	public Vector getUsuarisOrderRol() throws ControladorException{
		ResultSet rsUsuari;
		String query = "SELECT " +
							"id, " +
							"login, " +
							"password, " +
							"rol, " +
						"FROM treballador " +
						"ORDER BY rol";
		
		try {
			rsUsuari = selectRS(query);
			return toVectorUsuaris(rsUsuari);
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[getUsuaris] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[getUsuaris] Error SQL:"+query+"\n"+e.getMessage());
		}	
	}
	
	public Vector getUsuarisRolInf(int rol) throws ControladorException{
		ResultSet rsUsuari;
		String query = "SELECT " +
							"id, " +
							"loin, " +
							"password, " +
							"rol, " +
						"FROM TREBALLADOR " +
						"WHERE rol > "+rol+" "+
						"ORDER BY loguin";
		
		try {
			rsUsuari = selectRS(query);
			return toVectorUsuaris(rsUsuari);
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[getUsuarisRolInf] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[getUsuarisRolInf] Error SQL:"+query+"\n"+e.getMessage());
		}	
	}
	
	public void modificarUsuari(Usuari usuari) throws ControladorException{
		String query ="UPDATE TREBALLADOR SET "+usuari.sqlUpdate()+" WHERE id = "+usuari.getId();
		
		try {
				update(query);
			
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[modificarUsuari(Usuari usuari)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[modificarUsuari(Usuari usuari)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void afegirUsuari(Usuari usuari) throws ControladorException{
		String query ="INSERT INTO TREBALLADOR "+usuari.sqlInsert();
		
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[afegirUsuari(Usuari usuari)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[afegirUsuari(Usuari usuari)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void eliminarUsuari(String idSala) throws ControladorException {
		String query ="DELETE TREBALLADOR WHERE id="+idSala;

		try {
			update(query);	
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[eliminarSala(String idSala)] query:"+query+"\n query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[eliminarPelicula(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}

	public Usuari getUsuari(Usuari usuari) throws ControladorException {
		ResultSet rsUsuari;
		String query = "SELECT " +
							"id, " +
							"login, " +
							"password, " +
							"rol, " +
						"FROM treballador " +
						"WHERE login" +
						"ORDER BY loguin";
		
		try {
			rsUsuari = selectRS(query);
			Vector usuaris = toVectorUsuaris(rsUsuari);
			
			if(usuaris!=null){
				return (Usuari)usuaris.firstElement();
			}else{
				throw new ControladorException("Nom d'usuari o contrasenya incorrectes");
			}
			
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[getUsuari(Usuari usuari)] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[getUsuari(Usuari usuari)] Error SQL:"+query+"\n"+e.getMessage());
		}	
	}		
}
