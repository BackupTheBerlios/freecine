/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gestioCinema.gestioSales;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;
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
	
	public Vector toVectorSales(ResultSet rs) throws SQLException, ControladorException {
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
				rs.getInt(5),
				rs.getString(6),
				null);
				
			sales.addElement(sala);
			
			System.err.println("[toVectorSales][]");
			System.err.print(" id "+sala.getId());
			System.err.print(", numMaxColumnes "+sala.getNumMaxColumnes());
			System.err.print(", numMaxFiles "+sala.getNumMaxFiles());
			System.err.print(", numButaques "+sala.getNumButaques());
			System.err.println("");
		}
		
		Iterator itSales= sales.iterator();
		
		while(itSales.hasNext()){
			Sala sala = (Sala)itSales.next();
			sala.setButaques(getButaques(sala.getId()));
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
		ResultSet rsSales;
		String query = "SELECT " +
							"id, " +
							"nom, " +
							"num_butaques, " +
							"num_columnes, " +
							"num_files, " +
							"descripcio " +
						"FROM sala";
		try {
			rsSales = selectRS(query);
			return toVectorSales(rsSales);
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[getSales] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorSales]:[getSales] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Sala getSala(int idSala) throws ControladorException{
		ResultSet rsSales;
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
			rsSales = selectRS(query);
			Sala sala = (Sala)toVectorSales(rsSales).elementAt(0);
			return sala;
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[getSala(int idSala)] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorSales]:[getSala(int idSala)] Error SQL: "+query+"\n"+e.getMessage());
		}
	}
	
	public void eliminarSala(int id) throws ControladorException{
		String query1 ="DELETE FROM BUTACA WHERE id_sala="+id;
		String query2 ="DELETE FROM SALA WHERE id="+id;
		try {
			//update(query1);
			update(query2);
		} catch (SQLException e) {
			System.err.println("[ControladorPelicules]:[eliminarSala(String id)] query1:"+query1+"\n query2:"+query2+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[eliminarSala(String id)] query:"+query1+"\n query2:"+query2+"\n Error SQL: "+e.getMessage());
		}
	}	
	
	public Butaca getButacaSala(int idButaca, int idSala) throws ControladorException{
		ResultSet rsSales;
		String query = "SELECT " +
							"id, " +
							"num_fila, " +
							"num_columna, " +
							"operativa " +
						"FROM butaca " +
						"WHERE id_sala = "+idSala+" " +
						"AND id = "+idButaca;
		
		try {
			rsSales = selectRS(query);
			Vector butaques = toVectorButaques(rsSales);
			return (Butaca) butaques.firstElement();
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[getButacaSala(int idButaca, int idSala)] Error SQL: "+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorSales]:[getSala(int idButaca, int idSala) Error SQL: Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getButaques(int idSala) throws ControladorException {
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
			return toVectorButaques(rsSales);
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[getButaques(int idSala)] Error SQL: "+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorSales]:[getButaques(int idSala)] Error SQL: Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public void eliminarSala(String idSala) throws ControladorException {
		String query1 = "DELETE FROM SALA WHERE id_sala="+idSala;
		String query ="DELETE FROM SALA WHERE id="+idSala;
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[eliminarSala(String idSala)] query:"+query1+"\n query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[eliminarPelicula(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void modificarSala(Sala sala) throws ControladorException {
		System.err.println("[ControladorSala]:[modificarSala]: "+sala.getId()+""+sala.getNomSala());
		String query ="UPDATE SALA SET "+sala.sqlUpdate()+" WHERE id = "+sala.getId();
		System.err.println("[ControladorSala]:[modificarSala]: query"+query);
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[modificarSala(Sala sala)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSales]:[modificarPelicula(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void afegirSala(Sala sala) throws ControladorException{
		System.err.println(sala);
		String query ="INSERT INTO SALA "+sala.sqlInsert();
		System.err.println("[ControladorSala]:[afegirSala]"+query);
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[afegirSala(Sala sala)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSales]:[afegirSala(Sala sala)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
}
