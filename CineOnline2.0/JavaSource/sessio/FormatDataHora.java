package sessio;


public class FormatDataHora {

//	 ------------>	MÈTODES DE DATA    <---------------
	
	
//	retorna true si la data està en format dd/mm/aa correcte 
	public static boolean dataValida (String data){
		if (data.length() != 8){
			return false;
		}
		int index1 = data.indexOf("/");
		if (index1 != 2){
			return false;
		}
		String diaS = data.substring(0, index1);
		try{
			int dia=Integer.parseInt(diaS);
			if (dia > 31){
				return false;
			}
		}catch (NumberFormatException e){
			return false;
		}
		int index2 = data.indexOf("/", index1+1);
		if (index2 != 5){
			return false;
		}
		
		String mesS = data.substring(index1+1, index2);
		try{
			int mes = Integer.parseInt(mesS);
			if (mes > 12){
				return false;
			}
		}catch (NumberFormatException e){
			return false;
		}
		String anyS = data.substring(index2+1, data.length());
		try{
			Integer.parseInt(anyS);
		}catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	//retorna en int el dia d'una data en format dd/mm/aa
	public static int getDia (String data) throws DataIncorrectaException {
		if (!dataValida (data)){
			throw new DataIncorrectaException ("[FormatData]:[getDia] --> Format de la data incorrecte");
		}
		int dia;
		String diaS = data.substring(0, 2);
		try{
			dia = Integer.parseInt(diaS);
			if (dia > 31 ) throw new DataIncorrectaException ("[FormatData]:[getDia] --> Format de la data incorrecte");
		}catch (NumberFormatException e){
			throw new DataIncorrectaException ("[FormatData]:[getDia] --> Format de la data incorrecte");
		}
		return dia;
	}
	
	//retorna en int el mes d'una data en format dd/mm/aa
	public static int getMes (String data) throws DataIncorrectaException {
		if (!dataValida (data)){
			throw new DataIncorrectaException ("[FormatData]:[getMes] --> Format de la data incorrecte");
		}
		int mes;
		String mesS = data.substring(3,5);
		try{
			mes = Integer.parseInt(mesS);
			if (mes > 12 ) throw new DataIncorrectaException ("[FormatData]:[getMes] --> Format de la data incorrecte");
		}catch (NumberFormatException e){
			throw new DataIncorrectaException ("[FormatData]:[getMes] --> Format de la data incorrecte");
		}
		return mes;
	}
	
	//	retorna en int el dia d'una data en format dd/mm/aa
	public static int getAny (String data) throws DataIncorrectaException {
		if (!dataValida (data)){
			throw new DataIncorrectaException ("[FormatData]:[getAny] --> Format de la data incorrecte");
		}
		int any;
		String anyS = data.substring(6, data.length());
		try{
			any = Integer.parseInt(anyS);
		}catch (NumberFormatException e){
			throw new DataIncorrectaException ("[FormatData]:[getMes] --> Format de la data incorrecte");
		}
		return any;
	}
	
	//suma dies a la data i la retorna amb un String
	public static String sumarDies (String data, int dies) throws DataIncorrectaException{
		if (!dataValida (data)){
			throw new DataIncorrectaException ("[FormatData]:[getAny] --> Format de la data incorrecte");
		}
		int any = getAny (data);
		int mes = getMes (data);
		int dia = getDia (data);
		int resultDia = (dia+dies) %31;
		int resultMes = (((dia+dies) /31)+mes)%12 ;
		int resultAny = ((((dia+dies) /31)+mes)/12)+any; 
		return getData (resultDia, resultMes, resultAny);
	}
	
	//passa a format String dd/mm/aa els ints passats com a paràmetre
	public static String getData (int dia, int mes, int any){
		String diaS=dia+"", mesS=mes+"", anyS=any+"";
		if (dia < 10) diaS = "0"+dia;
		if (mes < 10) mesS = "0"+mes;
		if (any < 10) anyS = "0"+any;
		return diaS+"/"+mesS+"/"+anyS;
	}
	
	//retorna true si data1 es mes gran que data2
	public static boolean esMesGran (String data1, String data2) throws DataIncorrectaException{
		int any1 = getAny(data1);
		int any2 = getAny(data2);
		int mes1 = getMes(data1);
		int mes2 = getMes(data2);
		int dia1 = getDia(data1);
		int dia2 = getDia(data2);
		if (any1 > any2){
			return true;
		}else if (any1 == any2 && mes1 > mes2){
			return true;
		}else if (any1 == any2 && mes1 == mes2 && dia1 > dia2){
			return true;
		}
		return false;
	}
	
	//retorna true si data1 es igual a data2
	public static boolean esIgual (String data1, String data2) throws DataIncorrectaException{
		int any1 = getAny(data1);
		int any2 = getAny(data2);
		int mes1 = getMes(data1);
		int mes2 = getMes(data2);
		int dia1 = getDia(data1);
		int dia2 = getDia(data2);
		
		if (any1 == any2 && mes1 == mes2 && dia1 == dia2){
			return true;
		}
		return false;
	}
	
	//retorna en dies la diferencia de dates data1-data2
	public static int diferenciaDies (String data1, String data2) throws DataIncorrectaException{
		if (!dataValida (data1) || !dataValida (data2)){
			throw new DataIncorrectaException ("[FormatData]:[getAny] --> Format de la data incorrecte");
		}
		int any1 = getAny (data1);
		int mes1 = getMes (data1);
		int dia1 = getDia (data1);
		int any2 = getAny (data2);
		int mes2 = getMes (data2);
		int dia2 = getDia (data2);
		int diferenciaDies =0;
		diferenciaDies += any1-any2*31*12;
		diferenciaDies += mes1-mes2*31;
		diferenciaDies += dia1-dia2;
		return 1;
	}
	
	
	// ------------>	MÈTODES D'HORA    <---------------
	
	
	//retorna true si la hora està en format hh:mm correcte 
	public static boolean horaValida (String horaMin){
		if (horaMin.length()!=5){
			return false;
		}
		int index = horaMin.indexOf(":");
		if (index != 2){
			return false;
		}
		String horaS = horaMin.substring(0, index);
		try{
			int hora = Integer.parseInt(horaS);
			if (hora > 23 ) return false;
		}catch (NumberFormatException e){
			return false;
		}
		String minutsS = horaMin.substring(index+1, horaMin.length());
		try{
			int minuts = Integer.parseInt(minutsS);
			if (minuts >59) return false;
			
		}catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	//Retorna en int la hora corresponent a horaMin en format hh:mm
	public static int getHora (String horaMin) throws DataIncorrectaException {
		if (!horaValida (horaMin)){
			throw new DataIncorrectaException ("[FormatHora]:[gethora] --> Format de la data incorrecte");
		}
		int hora;
		String horaS = horaMin.substring(0, 2);
		try{
			hora = Integer.parseInt(horaS);
			if (hora > 23 ) throw new DataIncorrectaException ("[FormatHora]:[getHora] --> Format de la data incorrecte");
		}catch (NumberFormatException e){
			throw new DataIncorrectaException ("[FormatHora]:[getHora] --> Format de la data incorrecte");
		}
		return hora;
	}
	
	//Retorna en int els minuts corresponents a horaMin en format hh:mm
	public static int getMinuts (String horaMin) throws DataIncorrectaException {
		if (!horaValida (horaMin)){
			throw new DataIncorrectaException ("[FormatHora]:[getMinuts] --> Format de la data incorrecte");
		}
		int min;
		String minS = horaMin.substring(3, 5);
		try{
			min = Integer.parseInt(minS);
			if (min > 59 ) throw new DataIncorrectaException ("[FormatHora]:[getMinuts] --> Format de la data incorrecte");
		}catch (NumberFormatException e){
			throw new DataIncorrectaException ("[FormatHora]:[getMinuts] --> Format de la data incorrecte");
		}
		return min;
	}
	
	
	public static String getHora (int hora, int minuts){
		String horaS=hora+"", minutsS=minuts+"";
		if (hora < 10) horaS = "0"+hora;
		if (minuts < 10) minutsS = "0"+minuts;
		return horaS+":"+minutsS;
	}
	
//	 ------------>	MÈTODES DE DATA/HORA    <---------------
	
//	 suma horesSum en format hh:mm a data i hora i guarda el resultat als mateixos data i hora
	//Ex: 29/08/05 23:45 + 01:12 = 30/08/05 00:57
	public static void sumaHores (String data, String hora, String horesSum) throws DataIncorrectaException, HoraIncorrectaException{
		if (!dataValida (data)){
			throw new DataIncorrectaException ("[FormatDataHora]:[sumaMinuts] --> Format de la data incorrecte");
		}
		if (!horaValida (hora)){
			throw new DataIncorrectaException ("[FormatDataHora]:[sumaMinuts] --> Format de la data incorrecte");
		}
		if (!horaValida (horesSum)){
			throw new DataIncorrectaException ("[FormatDataHora]:[sumaMinuts] --> Format de la data incorrecte");
		}
		int any = getAny (data);
		int mes = getMes (data);
		int dia = getDia (data);
		int horaAct = getHora (hora);
		int minsAct = getMinuts (hora);
		int horesSuma = getHora (horesSum);
		int minsSuma = getMinuts (horesSum);
		
		int minutsNous = (minsAct+minsSuma)%60;
		int horesResta = (minsAct+minsSuma)/60;
		int horaNova = (horaAct+horesResta+horesSuma)%24;
		int diesResta = (horaAct+horesResta+horesSuma)/24;
		int diaNou = (dia+diesResta)%31;
		int mesosResta = (dia+diesResta)/31;
		int mesNou = (mes+mesosResta)%12;
		int anysResta = (mes+mesosResta)/12;
		int anyNou = any + anysResta;
		data = getData (diaNou, mesNou, anyNou);
		hora = getHora (horaNova, minutsNous);
	}
	
	
	// retorna en minuts la diferencia de dates data1:hora1-data2:hora2
	public static int diferenciaMinuts (String data1, String hora1, String data2, String hora2) throws DataIncorrectaException, HoraIncorrectaException{
		if (!dataValida (data1) || !dataValida (data2)){
			throw new DataIncorrectaException ("[FormatData]:[getAny] --> Format de la data incorrecte");
		}
		int any1 = getAny (data1);
		int mes1 = getMes (data1);
		int dia1 = getDia (data1);
		int horas1 = getHora (hora1);
		int minuts1 = getMinuts (hora1);
		int any2 = getAny (data2);
		int mes2 = getMes (data2);
		int dia2 = getDia (data2);
		int horas2 = getHora (hora2);
		int minuts2 = getMinuts (hora2);
		int diferenciaMinuts =0;
		diferenciaMinuts += any1-any2*12*31*24*60;
		diferenciaMinuts += mes1-mes2*31*24*60;
		diferenciaMinuts += dia1-dia2*60*24;
		diferenciaMinuts += horas1-horas2*60;
		diferenciaMinuts += minuts1-minuts2;
		
		return 1;
	}
	
	//retorna true si dos rangs de data:hora es solapen
	public static boolean esSolapen ( String dataInici1, String dataFi1, String horaInici1, String horaFi1, String dataInici2, String dataFi2, String horaInici2, String horaFi2 ) throws DataIncorrectaException, HoraIncorrectaException{

		if (diferenciaMinuts(dataInici1, horaInici1, dataInici2, horaInici2) < 0){
			if (diferenciaMinuts(dataInici1, horaInici1, dataFi2, horaFi2) > 0){
				return true;
			}
		}else {
			if (diferenciaMinuts(dataInici1, horaInici1, dataFi2, horaFi2) < 0){
				return true;
			}
		}
		if (diferenciaMinuts(dataFi1, horaFi1, dataInici2, horaInici2) < 0){
			if (diferenciaMinuts(dataFi1, horaFi1, dataFi2, horaFi2) > 0){
				return true;
			}
		}else {
			if (diferenciaMinuts(dataFi1, horaFi1, dataFi2, horaFi2) < 0){
				return true;
			}
		}
		if (diferenciaMinuts(dataInici2, horaInici2, dataInici1, horaInici1) < 0){
			if (diferenciaMinuts(dataInici2, horaInici2, dataFi1, horaFi1) > 0){
				return true;
			}
		}else {
			if (diferenciaMinuts(dataInici2, horaInici2, dataFi1, horaFi1) < 0){
				return true;
			}
		}
		if (diferenciaMinuts(dataFi2, horaFi2, dataInici1, horaInici1) < 0){
			if (diferenciaMinuts(dataFi2, horaFi2, dataFi1, horaFi1) > 0){
				return true;
			}
		}else {
			if (diferenciaMinuts(dataFi2, horaFi2, dataFi1, horaFi1) < 0){
				return true;
			}
		}
		
		return false;
		
	}
	
	public static String passaMinutsHora(int minuts){
		int horesInt = ((int)minuts/60);
		int minInt = minuts%60;
		String horaS="";
		String minutsS="";
		
		if(horesInt<10){
			horaS ="0"+horesInt;
		}else{
			horaS = ""+horesInt;
		}
		
		if(minuts<10){
			minutsS ="0"+minInt; 
		}else {
			minutsS =""+minInt;
		}
		return horaS+":"+minutsS;
	}
}
