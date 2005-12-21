package gestioCinema.gestioUsuaris;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
				rs.getInt(1),		//id
				rs.getString(2),	//login
				rs.getString(3),	//password
				rs.getInt(4));		//tipus
			usuaris.add(usuari);
		}
		return usuaris;	
	}
	
	public Vector getUsuaris() throws ControladorException{
		ResultSet rsUsuari;
		String query = "SELECT " +
							"id, " +
							"login, " +
							"password, " +
							"tipus " +
						"FROM treballador " +
						"ORDER BY login";
		
		try {
			rsUsuari = selectRS(query);
			Vector usuaris = toVectorUsuaris(rsUsuari);
			return usuaris;
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[getUsuaris] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[getUsuaris] Error SQL:"+query+"\n"+e.getMessage());
		}	
	}
	
	public Usuari getUsuari(int id) throws ControladorException{
		ResultSet rsUsuari;
		String query = "SELECT " +
							"id, " +
							"login, " +
							"password, " +
							"tipus " +
						"FROM treballador " +
						"WHERE id =" + id;
		
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
	
	public Vector getUsuarisOrderRol() throws ControladorException{
		ResultSet rsUsuari;
		String query = "SELECT " +
							"id, " +
							"login, " +
							"password, " +
							"tipus " +
						"FROM treballador " +
						"ORDER BY tipus";
		
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
							"login, " +
							"password, " +
							"tipus " +
						"FROM TREBALLADOR " +
						"WHERE tipus > "+rol+" "+
						"ORDER BY login";
		
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
		String query ="DELETE FROM TREBALLADOR WHERE id="+idSala;

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
							"tipus " +
						"FROM treballador " +
						"WHERE login = '" + usuari.getLogin() + "' AND password = '" + usuari.getPassword()+"'";
		
		try {
			rsUsuari = selectRS(query);
			Vector usuaris = toVectorUsuaris(rsUsuari);
			
			if(usuaris!=null && usuaris.size()>0){
				return (Usuari)usuaris.firstElement();
			}else{
				throw new ControladorException("[ControladorUsuaris]:[getUsuari(Usuari usuari)] Error -> Usuari no existeix");
			}
			
		} catch (SQLException e) {
			System.err.println("[ControladorUsuaris]:[getUsuari(Usuari usuari)] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorUsuaris]:[getUsuari(Usuari usuari)] Error SQL:"+query+"\n"+e.getMessage());
		}	
	}

	public Vector getRols() {
		Vector rols = new Vector();
		rols.add("0");
		rols.add("1");
		rols.add("2");
		return rols;
	}		
}
