package gestioCompraReserva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import gestioCinema.Controlador;
import gestioCinema.ControladorException;

public class ControladorCompraReserva extends Controlador{

	public ControladorCompraReserva() throws ControladorException {
		super();
	}
	
	public Vector toVectorCompraReserva(ResultSet rs) throws SQLException, ControladorException {
		/* 	
		 * Converteix un ResultSet a Vector de Pelicules
		 */
		

		Vector compresReserves = new Vector();
		while (rs.next()) {
			CompraReserva compraReserva = new CompraReserva();
			int idCompraReserva = rs.getInt(1);
			compraReserva.setAll(
				idCompraReserva,
				rs.getBoolean(2),
				rs.getString(3),
				rs.getString(4),
				rs.getInt(5),
				rs.getInt(6),
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
			Vector vec = toVectorSales(rsSales);
			if(vec.size()>0){
				
				Sala sala = (Sala) vec.firstElement();
				return sala;
			}
			return new Sala();
			
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
			/* Mirem que no existeixi cap sessio que depengui d'aquesta sala */
			ResultSet rs = selectRS("SELECT id FROM SESSIO WHERE id_sala = "+idSala);
			if(rs.next()){
				System.err.println("[ControladorSales]:[eliminarSales(int idSala)] query:"+query+"\n Error: Hi han session que depenent d'aquesta pelicula");
				throw new ControladorException("[ControladorSales]:[eliminarSales(int idSala)] query:"+query+"\n Error: Hi han session que depenent d'aquesta pelicula");
			}else{
				update(query);
			}
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[eliminarSala(String idSala)] query:"+query1+"\n query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorPelicules]:[eliminarPelicula(String id)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
	
	public void modificarSala(Sala sala) throws ControladorException {
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