package mains;

/*
 * Created on 09-nov-2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import pelicula.ControladorPelicula;
import pelicula.ControladorPeliculesException;
import pelicula.Pelicula;

/**
 * @author Òscar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Menu {

	public static void MenuPelicules() throws ControladorPeliculesException {
		int intro1,intro2;
		String intro3;
		int id=1;
		boolean ok=false;
		Pelicula peli;
		ControladorPelicula controlador=new ControladorPelicula();
		controlador.printPelicules();
		System.out.println();
		System.out.println("Menú d'opcions a realitzar per les pel·lícules:");
		do{
			intro1=Console.readInt("1->Llistar pel·lícules\n2->Introduir una pel·lícula\n3->Modificar pel·lícula\n4->Eliminar pel·lícula\n5->Sortir menú\n");
			if(intro1==1)controlador.printPelicules();
			else if(intro1==2){
				controlador.afegirPelicula(id,"Prova"+id+"","Prova",2005,30,"catalana",18,"Color","Sound","terror","Òscar","Òscar","Òscar","Òscar","Prova","www.com","c:");
				id++;
				controlador.printPelicules();
			}
			else if(intro1==3){
				controlador.printPelicules();
				do{
					intro2=Console.readInt("Introdueix el id de la pel·lícula a modificar:\n");
					ok=controlador.modificarPelicula(intro2,"Prova"+id+"","Prova",2005,30,"catalana",18,"Color","Sound","terror","Òscar","Òscar","Òscar","Òscar","Prova","www.com","c:");
					if(ok){
						id++;
						controlador.printPelicules();
					}
					else {
						System.out.println("Id incorrecte");
						controlador.printPelicules();
					}
				}while(!ok);
			}
			else if (intro1==4){
				controlador.printPelicules();
				do{
					intro2=Console.readInt("Introdueix el id de la pel·lícula a eliminar:\n");
					peli=controlador.getPelicula(intro2);
					if(peli!=null){
						do{
							intro3=Console.readString("Introdueix 's' per confirmar que vols eliminar la pel·lícula amb id "+intro2+" o 'n' per tornar al menú:\n");
							controlador.printPelicules();
							if (intro3.charAt(0)=='s'){
								controlador.eliminarPelicula(intro2);
								ok=true;
							}
							else if (intro3.charAt(0)=='n')ok=true;
						}while(intro3.charAt(0)!='n'&&intro3.charAt(0)!='s');
					}
						
					else {
						System.out.println("Id incorrecte");
						controlador.printPelicules();
					}
				}while(!ok);
			}
		} while (intro1!=5);
	}
}
