package mains;

/**
 * @author Sergi
 *
 * Projecte Webs v0
 */
import java.io.IOException;

import Pelicula.Console;
import Pelicula.ControladorPeliculesException;
import Pelicula.Menu;
/**
 * @author Sergi
 *
 * Projecte Webs v0
 */
public class Aplicacio {
		

	public static void main(String[] args) throws IOException, ControladorPeliculesException
	{		
		desenvolupamentAplicacio();

	}

	private static void desenvolupamentAplicacio() throws IOException, ControladorPeliculesException
	{
		mostrarMenu();

	}

	private static void mostrarMenu() throws IOException, ControladorPeliculesException
	{
		int resp;
		do
		{
			// Menu per a gestionar les Aplicacios
		//	Console.liniapunt('*');
			System.out.println("Menu de l'aplicacio ");
			System.out.println("1. Gestionar pelicules");
			System.out.println("2. Gestionar sales");
			System.out.println("3. Gestionar sessions");
			System.out.println("0. Sortir de l'aplicacio ");
		//	Console.liniapunt('*');
			do
			{				
				resp = Console.readInt("Escull la teva opcio: ");								
			} while(resp<0 || resp>3);

			switch (resp)
			{
				case 0:
				//Console.liniapunt('>');								
				System.out.println("> Sortint de l'aplicacio ");
				//Console.liniapunt('>');
				break;
				case 1:
				int resp2;		
				do
				{
					//Console.liniapunt('>');
					System.out.println("> 1. Gestiona pelicula video");
					Menu.MenuPelicules();
					//Console.liniapunt('>');
					do
					{
						resp2 = Console.readInt("Escull la teva opcio: ");
					} while(resp2<0 || resp2>2);
					if(resp2!=0)
					{
						System.out.println("Generar " + resp2);
						//Pelicula.main(resp2);
					}
					
					
				}while (resp2!=0);
				break;
				case 2:
				int resp3;
				do
				{
					//Console.liniapunt('>');
					System.out.println("> 2. Visualitzar video");
					System.out.println("> ** 1. Visualitzar video original");
					System.out.println("> ** 2. Visualitzar video codificat");
					System.out.println("> ** 0. Sortir de Visualitzar video");
					//Console.liniapunt('>');
					do
					{
						resp3 = Console.readInt("Escull la teva opcio: ");
					} while(resp3<0 || resp3>2);					
					if(resp3!=0)
					{
						System.out.println("Visualitzar " + resp3);
						//Pelicula.main(resp3+2);
					}

				}while (resp3!=0);									
				break;				
			}
		}while (resp!=0);
		System.out.println("Aplicacio acabada.");
	}
	

	private boolean escullOpcioBool()
	{
		int resp;
		boolean retorn = false;
		try 
		{
			do
			{
				System.out.print("Escull la teva opcio: ");			
				resp= System.in.read();
						
			}while (resp<1 || resp>2);
			if (resp==1)
			{
				retorn = true;
			}
			else
			{
				retorn = false;
			}
		}
		catch (IOException e) 
		{
				   
		}
		return retorn;
	}	
}



