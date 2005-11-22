package sessio;
import java.util.Iterator;
import java.util.Vector;

import pelicula.Pelicula;


import sala.Butaca;
import sala.ControladorSalasException;
import sala.Sala;
public class ControladorSessio {
	
	private Vector sessions;
	
	public ControladorSessio (){
		sessions = new Vector();
	}
	
	
	//retorna true si aconsegueix afegir la sessio
	public boolean afegirSessio(int id, 
			String data, 
			String hora, 
			Pelicula pelicula, 
			Sala sala) 
			throws DataIncorrectaException, HoraIncorrectaException 
	{
		Sessio actual = crearSessio (id, data, hora, pelicula, sala);
		if (!esSolapa (actual)){
			sessions.add(actual);
			return true;
		}
			return false;
	}
	
	//afegeix sessions en un rang de dates a la mateixa hora
	public boolean afegirSessions( int idInicial, String dataInici, 
			String dataFi, String hora, 
			Pelicula pelicula, Sala sala)
			throws DataIncorrectaException, HoraIncorrectaException
	{
		if (!FormatDataHora.dataValida(dataFi)) throw new DataIncorrectaException();
		if (!FormatDataHora.dataValida(dataFi)) throw new DataIncorrectaException();
		if (!FormatDataHora.horaValida(hora)) throw new HoraIncorrectaException();
		
		Vector sessionsAfegides = new Vector();
		if (FormatDataHora.esMesGran(dataInici, dataFi)){
			String aux = dataFi;
			dataFi = dataInici;
			dataInici = aux;
		}
		String dataActual = dataInici;
		while (FormatDataHora.esMesGran (dataFi, dataActual)){
			Sessio nova = crearSessio (idInicial, dataActual, hora, pelicula, sala);
			if (esSolapa(nova)) 
				return false;
			sessionsAfegides.add(nova);
			idInicial ++;
			FormatDataHora.sumarDies(dataActual, 1);
		}
		sessions.addAll(sessionsAfegides);
		return true;
	}
	
	//retorna la sessio corresponent a la data, hora i sala. Si no existeix llança una excepcio
	public Sessio getSessio(String data, String hora, Sala sala) throws ControladorSessioException{
		Iterator itSessions = sessions.iterator();
		Sessio sessio;
		while(itSessions.hasNext()){
			sessio =(Sessio) itSessions.next();
			if(sessio.getSala().getId()==sala.getId() && 
					sessio.getData() == data &&
					sessio.getHoraInici() == hora){
				return sessio;
			}
		}
		throw new ControladorSessioException("[ControladorSessio]:[getSessio] -> " +
				"Error: No s'ha trobat cap Sessio amb data "+data+", hora: "+hora+", a la sala "+sala.getDescripcio());
	}
	
	public boolean eliminarSessio(int id){
		Iterator itSes = sessions.iterator();
		while (itSes.hasNext()){
			Sessio actual = (Sessio)itSes.next();
			if (actual.getId()==id){
				return sessions.remove(actual);
			}
		}
		return false;
	}
	
	public void eliminarSessions (String diaInici, String diaFinal) throws DataIncorrectaException {
		Iterator itSes = sessions.iterator();
		while (itSes.hasNext()) {
			Sessio actual = (Sessio)itSes.next();
			if (actual.getData().compareTo (diaInici) == 0 || actual.getData().compareTo(diaFinal)==0
					|| (FormatDataHora.esMesGran(actual.getData(), diaInici) && FormatDataHora.esMesGran(diaFinal, actual.getData()))
					|| (FormatDataHora.esMesGran(actual.getData(), diaFinal) && FormatDataHora.esMesGran(diaInici, actual.getData()))) {
				sessions.remove(actual);				
			}
				
		}
		FormatDataHora.esMesGran(diaInici, diaFinal);
	}
	
	//	crea una sessió nova 
	private Sessio crearSessio (int id, 
			String data, String hora, 
			Pelicula pelicula, Sala sala) throws DataIncorrectaException, HoraIncorrectaException{
		if (!FormatDataHora.dataValida(data)) throw new DataIncorrectaException();
		if (!FormatDataHora.horaValida(hora)) throw new HoraIncorrectaException();		
		Vector butaquesSessio = new Vector();
		Vector butaques = sala.getButaquesOperatives();
		Iterator i = butaques.iterator();
		while (i.hasNext()){
			Butaca butacaActual = (Butaca)i.next();
			butaquesSessio.add(new ButacaSessio(butacaActual.getNumButaca(), butacaActual.getNumFila(), butacaActual.getNumColumna(), butacaActual.getOperativa()));
		}
		return new Sessio (id, data, hora, pelicula, sala, butaquesSessio);
		
	}
	
	//retorna true si solap es solapa amb alguna sessio de les sessions del Vector
	private boolean esSolapa (Sessio solap) throws DataIncorrectaException, HoraIncorrectaException{
		boolean solapa = false;
		String horaIniciSolap = solap.getHoraInici();
		String horaFiSolap = horaIniciSolap.substring(0, horaIniciSolap.length());
		String dataIniciSolap = solap.getData();
		String dataFiSolap = dataIniciSolap.substring(0, dataIniciSolap.length());
		FormatDataHora.sumaHores(dataFiSolap, horaFiSolap, FormatDataHora.passaMinutsHora(solap.getPelicula().getDurada()));
		Iterator i = sessions.iterator();
		boolean esSolapa=false;
		while (i.hasNext()){
			Sessio actual = (Sessio) i.next();
			String horaIniciActual = actual.getHoraInici();
			String horaFiActual = horaIniciSolap.substring(0, horaIniciSolap.length());
			String dataIniciActual = actual.getData();
			String dataFiActual = dataIniciSolap.substring(0, dataIniciSolap.length());
			if(FormatDataHora.esSolapen(dataIniciSolap, dataFiSolap, horaIniciSolap, horaFiSolap, dataIniciActual, dataFiActual, horaIniciActual, horaFiActual )){
				esSolapa=true;
				break;
			}
		}
		return esSolapa;
	}
	
}
