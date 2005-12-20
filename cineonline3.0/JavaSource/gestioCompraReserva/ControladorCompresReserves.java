package gestioCompraReserva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import gestioCinema.ControladorException;
import gestioCinema.gestioSessions.ButacaSessio;
import gestioCinema.gestioSessions.ControladorSessions;
import gestioCinema.gestioSessions.Sessio;

public class ControladorCompresReserves extends ControladorSessions{

	public ControladorCompresReserves() throws ControladorException {
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
				butaca);		//id_butaca
				
			compresReserves.addElement(compraReserva);
		}
		
		Iterator itCompresReserves= compresReserves.iterator();
		
		while(itCompresReserves.hasNext()){
			CompraReserva cr = (CompraReserva)itCompresReserves.next();
			cr.setSessio(getSessio(cr.getSessio().getId()));
			cr.setButacaSessio(getButacaSessio(cr.getSessio().getId(),cr.getButacaSessio().getNumButaca()));
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
	
	public Vector getCompresReservesOrdenatCodiEntrega() throws ControladorException{
		ResultSet rsCR;
		String query = "SELECT " +
							"id, " +
							"pagada, " +
							"codi_entrega, " +
							"data_hora, " +
							"id_sessio, " +
							"id_butaca " +
						"FROM compra_reserva " +
						"ORDER BY codi_entrega";
		
		try {
			rsCR = selectRS(query);
			return toVectorSales(rsCR);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompresReserves] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorCompresReserves]:[getCompresReserves] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getCompresReservesOrdenatPerSessioButaca() throws ControladorException{
		ResultSet rsCR;
		String query = "SELECT " +
							"id, " +
							"pagada, " +
							"codi_entrega, " +
							"data_hora, " +
							"id_sessio, " +
							"id_butaca " +
						"FROM compra_reserva " +
						"ORDER BY id_sessio, id_butaca";
		
		try {
			rsCR = selectRS(query);
			return toVectorSales(rsCR);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompresReserves] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[getCompresReserves] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getCompresReservesPagadesOrderSessioButaca() throws ControladorException{
		ResultSet rsCR;
		String query = "SELECT " +
							"id, " +
							"pagada, " +
							"codi_entrega, " +
							"data_hora, " +
							"id_sessio, " +
							"id_butaca " +
						"FROM compra_reserva " +
						"WHERE pagada = 'true'" +
						"ORDER BY id_sessio, id_butaca";
		
		try {
			rsCR = selectRS(query);
			return toVectorSales(rsCR);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompresReservesPagadesOrderSessioButaca] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[getCompresReservesPagadesOrderSessioButaca] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getCompresReservesNoPagadesOrderSessioButaca() throws ControladorException{
		ResultSet rsCR;
		String query = "SELECT " +
							"id, " +
							"pagada, " +
							"codi_entrega, " +
							"data_hora, " +
							"id_sessio, " +
							"id_butaca " +
						"FROM compra_reserva " +
						"WHERE pagada = 'false'" +
						"ORDER BY id_sessio, id_butaca";
		
		try {
			rsCR = selectRS(query);
			return toVectorSales(rsCR);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompresReservesNoPagadesOrderSessioButaca] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[getCompresReservesNoPagadesOrderSessioButaca] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public Vector getCompresReservesCodiEntrega(String codiEntrega) throws ControladorException{
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
						"ORDER BY id_butaca";
		
		try {
			rsCR = selectRS(query);
			Vector vCR = toVectorCompraReserva(rsCR);
			if(vCR!=null){
				return vCR;
			}else{
				throw new ControladorException("[ControladorCompresReserves]:[getCompraReservaCodiEntrega] Error -> El codiEntrega de la compra reserva no es vàlit");
			}
			
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompraReservaCodiEntrega] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorCompresReserves]:[getCompraReservaCodiEntrega] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public CompraReserva getCompraReserva(int idCR) throws ControladorException{
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
			Vector vCR = toVectorSales(rsCR);
			if(vCR!=null){
				return (CompraReserva) vCR.firstElement();
			}else{
				throw new ControladorException("[ControladorCompresReserves]:[getCompraReserva] Error -> El id de la compra reserva no es vàlit");
			}
			
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[getCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
			e.printStackTrace();
			throw new ControladorException("[ControladorCompresReserves]:[getCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public void setCompresReservesPagada(int idCR) throws ControladorException{
		String query ="UPDATE compra_reserva SET pagada = 'true' WHERE id = "+idCR;
		System.err.println("[ControladorCompresReserves]:[modificarCompraReserva]: query"+query);
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[setCompraReservaPagada(int idCR)] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[setCompraReservaPagada(int idCR)] Error SQL:"+query+"\n"+e.getMessage());
		}
	}
	
	public void setCompresReservesPagadaCodiEntrega(String codiEntrega) throws ControladorException{
		String query ="UPDATE compra_reserva SET pagada = 'true' WHERE codi_entrega = "+codiEntrega;
		System.err.println("[ControladorCompresReserves]:[modificarCompraReserva]: query"+query);
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[setCompresReservesPagadaCodiEntrega(int idCR)] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[setCompresReservesPagadaCodiEntrega(int idCR)] Error SQL:"+query+"\n"+e.getMessage());
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
		String query = null;
		//String query =cr.sqlUpdate();
		System.err.println("[ControladorCompresReserves]:[modificarCompraReserva]: query"+ query);
		/*try {
			//update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorCompresReserves]:[modificarCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
			throw new ControladorException("[ControladorCompresReserves]:[modificarCompraReserva] Error SQL:"+query+"\n"+e.getMessage());
		}*/
	}
	
	public void afegirCompraReserva(CompraReserva cr) throws ControladorException{
		String query ="INSERT INTO compra_reserva "+cr.sqlInsert();
		System.err.println("[ControladorSala]:[afegirSala]"+query);
		try {
			update(query);
		} catch (SQLException e) {
			System.err.println("[ControladorSales]:[afegirCompraReserva(CompraReserva)] query:"+query+"\n Error SQL: "+e.getMessage());
			throw new ControladorException("[ControladorSales]:[afegirCompraReserva(CompraReserva)] query:"+query+"\n Error SQL: "+e.getMessage());
		}
	}
}