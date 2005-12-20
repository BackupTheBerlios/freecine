package gestioCompraReserva;

import gestioCinema.ControladorException;
import gestioCinema.gestioSessions.ButacaSessio;
import gestioCinema.gestioSessions.ControladorSessions;
import gestioCinema.gestioSessions.Sessio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class ControladorCompraReserva extends ControladorSessions{

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
			Sessio sessio = new Sessio();
			sessio.setId(rs.getInt(5));
			ButacaSessio butaca = new ButacaSessio();
			butaca.setNumButaca(rs.getInt(6));
			compraReserva.setAll(
				idCompraReserva, 	//id
				rs.getBoolean(2), 	//pagada
				rs.getString(3),	//codi_entrega
				rs.getString(4),	//data_hora
				sessio,				//id_sessio
				butaca);			//id_butaca
				
			compresReserves.addElement(compraReserva);
		}
		
		Iterator itCompresReserves= compresReserves.iterator();
		
		while(itCompresReserves.hasNext()){
			CompraReserva compraReserva = (CompraReserva)itCompresReserves.next();
			compraReserva.setSessio(getSessio(compraReserva.getSessio().getId()));
			compraReserva.setButacaSessio(getButacaSessio(compraReserva.getSessio().getId(),compraReserva.getButacaSessio().getNumButaca()));
		}
		return compresReserves;	
	}

	
	
	public Vector getCompresReserves() throws ControladorException{
		ResultSet rsCR;
		String query = "SELECT " +
							"id, " +
							"pagada, " +
							"codi_entrega, " +
							"data_hora, " +
							"id_sessio, " +
							"id_butaca " +
						"FROM compra_reserva " +
						"ORDER BY data_hora";
		
		try {
			rsCR = selectRS(query);
			return toVectorSales(rsCR);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompresReserves] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorCompresReserves]:[getCompresReserves] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getCompraReserva(int idCR) throws ControladorException{
		ResultSet rsCR;
		String query = "SELECT " +
							"id, " +
							"pagada, " +
							"codi_entrega, " +
							"data_hora, " +
							"id_sessio, " +
							"id_butaca " +
						"FROM compra_reserva " +
						"WHERE id = " + idCR +
						"ORDER BY data_hora";
		
		try {
			rsCR = selectRS(query);
			return toVectorSales(rsCR);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorCompresReserves]:[getCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getCompraReservaCodiEntrega(int codiEntrega) throws ControladorException{
		ResultSet rsCR;
		String query = "SELECT " +
							"id, " +
							"pagada, " +
							"codi_entrega, " +
							"data_hora, " +
							"id_sessio, " +
							"id_butaca " +
						"FROM compra_reserva " +
						"WHERE codi_entrega = " + codiEntrega +
						"ORDER BY data_hora";
		
		try {
			rsCR = selectRS(query);
			return toVectorSales(rsCR);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompraReservaCodiEntrega] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorCompresReserves]:[getCompraReservaCodiEntrega] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public void eliminarCompraReserva(String idCompraReserva) throws ControladorException {
		String query ="DELETE FROM compra_reserva WHERE id="+idCompraReserva;
		try {
				update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[eliminarCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[eliminarCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public void modificarCompraReserva(CompraReserva cr) throws ControladorException {
		String query ="UPDATE compra_reserva SET "+cr.sqlUpdate()+" WHERE id = "+cr.getId();
		System.err.println("[ControladorCompresReserves]:[modificarCompraReserva]: query"+query);
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[modificarCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[modificarCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public void afegirCompraReserva(CompraReserva cr) throws ControladorException{
		String query ="INSERT INTO compra_reserva "+cr.sqlInsert();
		System.err.println("[ControladorSala]:[afegirSala]"+query);
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[afegirSala(Sala sala)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSales]:[afegirSala(Sala sala)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
}