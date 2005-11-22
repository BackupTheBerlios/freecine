/*
 * Created on 14-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package interficie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import pelicula.ControladorPelicula;
import pelicula.ControladorPeliculesException;
import pelicula.Pelicula;

import sala.ControladorSalas;
import sala.ControladorSalasException;
import sala.Sala;


/**
 * @author A4alumno
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinestraPrincipalNoAcavada implements ActionListener, WindowListener{
	private final int AMPLADA=400;
	private final int ALTURA=400;
	
	/* Variables Finestra Principal */
	private JFrame jfFinestraPrincipal;
	private JTabbedPane jtpPestanyes;
	private boolean conectat;
	
	
	/* MENU */
	private JMenuBar jmbFinestra;
	
	/* 		>>menu BBDD*/
	private JMenu jmBBDD;
	private JMenuItem jmiConectar;
	private JMenuItem jmiDesconectar;
	
	/* 		>>menu Ayuda*/
	private JMenu jmAjuda;
	private JMenuItem jmiAjudar;
	private JMenuItem jmiAcerca;
	/* FI MENU */
	
	/* PESTANYA 1*/
	private JPanel jpPestanyaSales;

	/*Etiquetes*/
	private JLabel jlP1Id;
	private JLabel jlP1NomSala;
	private JLabel jlP1NumButaquesOperatives;
	private JLabel jlP1MaxColumnes;
	private JLabel jlP1MaxFiles;
	private JLabel jlP1Descripcio;
	
	/*TextArea*/
	private JTextField jtP1Id;
	private JTextField jtP1NomSala;
	private JTextField jtP1NumButaquesOperatives; 
	private JTextField jtP1MaxColumnes;
	private JTextField jtP1MaxFiles;
	private JTextField jtP1Descripcio;
	private JTextField jtP1AnarId;
	
	//botons
	private JButton jbP1Inici;
	private JButton jbP1Fi;
	private JButton jbP1Esq;
	private JButton jbP1Drt;
	private JButton jbP1Afegir;
	private JButton jbP1Modificar;
	private JButton jbP1Eliminar;
	private JButton jbP1Llistar;
	
	private JButton jbP1AnarId;
	private JButton jbP1ActualitzarSala;
	
	//Gestio SALES
	private ControladorSalas controladorSalas;
	private Vector sales;
	private Sala sala;
	int punterSala;
	boolean controladorSalasConectat;

	//PopUps Sales
	SalaFinestraInserir salaInserirPopUp;
	SalaFinestraModificar salaModificarPopUp;

	/* FI PESTANYA 1*/
	
	
	/* PESTANYA 2*/
	private JPanel jpPestanyaPelicules;	

	/*Etiquetes*/
	private JLabel jlP2Id;
	private JLabel jlP2Titol;
	private JLabel jlP2Durada;
	private JLabel jlP2EdatRecomenada;
	private JLabel jlP2Genere;
	private JLabel jlP2Sinopsis;
	
	/*TextArea*/
	private JTextField jtP2Id;
	private JTextField jtP2Titol;
	private JTextField jtP2Durada; 
	private JTextField jtP2EdatRecomanada;
	private JTextField jtP2Genere;
	private JTextField jtP2Sinopsis;
	
	private JTextField jtP2AnarId;
	
	//botons
	private JButton jbP2Inici;
	private JButton jbP2Fi;
	private JButton jbP2Esq;
	private JButton jbP2Drt;
	private JButton jbP2Afegir;
	private JButton jbP2Modificar;
	private JButton jbP2Eliminar;
	private JButton jbP2Llistar;
	
	private JButton jbP2AnarId;
	private JButton jbP2ActualitzarPelicula;
	
	//Gestio Pelicules
	private ControladorPelicula controladorPelicules;
	private Vector pelicules;
	private Pelicula pelicula;
	int punterPelicula;
	boolean controladorPeliculasConectat;

	//PopUps Pelicules
	PeliculaFinestraInserir peliculaInserirPopUp;
	PeliculaFinestraModificar peliculaModificarPopUp;
	/* FI PESTANYA 2*/
	
	
	/* PESTANYA 3*/
	private JPanel jpPestanyaSessio;	
	/* FI PESTANYA 3*/

	String titol;
	
	public FinestraPrincipalNoAcavada(){
		titol="Gestio Cinema";
		construirFinestraPrincipal();
		configurarFinestraPrincipal();
		conectat=false;
		obrirFinestra();
	}
	
	private void construirFinestraPrincipal(){
		System.out.println("[FinestraPrincipal]:[contruirFinestraPrincipal]");
		jfFinestraPrincipal = new JFrame(titol);
		jfFinestraPrincipal.addWindowListener(this);
		jtpPestanyes = new JTabbedPane();
		construirMenu();
		
		/*pestanya 1*/
		construirPestanya1();
		conectarSalesBBDDPestanya1();
		construirPopUpPestanya1();
		/*fi pestanya 1*/
	}
	
	private void configurarFinestraPrincipal(){
		System.out.println("[FinestraPrincipal]:[cofigurarFinestraPrincipal]");
		configurarPestanya1();
		jfFinestraPrincipal.add(jtpPestanyes);
	}
	
	
	
/**************************************************************************************/
/********************************PESTANYA 1 SALES**************************************/
/**************************************************************************************/
	
	private void construirPestanya1(){
		System.out.println("[FinestraPrincipal]:[construirPestanya1]");
		/*Creem un panell de n files i m columnes*/
		jpPestanyaSales = new JPanel(new GridLayout(12,2));
		
		
		/*Creem les etiquetes*/
		jlP1Id = new JLabel("id");
		jlP1NomSala = new JLabel("Nom Sala");
		jlP1NumButaquesOperatives = new JLabel("Butaques operatives");
		jlP1MaxColumnes = new JLabel("Nombre màxim columnes");
		jlP1MaxFiles = new JLabel("Nombre màxim files");
		jlP1Descripcio = new JLabel("Descripció");
		
		/*Creem els texts areas*/
		jtP1Id = new JTextField();
		jtP1Descripcio = new JTextField();
		jtP1NumButaquesOperatives = new JTextField();
		jtP1MaxFiles = new JTextField();
		jtP1NomSala = new JTextField();
		jtP1MaxColumnes = new JTextField();
		jtP1AnarId = new JTextField();
		
		/*Set que no es pugui modificar*/
		jtP1Id.setEditable(false);
		jtP1Descripcio.setEditable(false);
		jtP1NumButaquesOperatives.setEditable(false);
		jtP1MaxFiles.setEditable(false);
		jtP1NomSala.setEditable(false);
		jtP1MaxColumnes.setEditable(false);
		
		jtP1AnarId.setEditable(true);
		
		/*Creem el botons*/
		jbP1Inici = new JButton("|<");
		jbP1Fi = new JButton(">|");
		jbP1Esq = new JButton("<<");
		jbP1Drt = new JButton(">>");
		jbP1Afegir = new JButton("Afegir");
		jbP1Modificar = new JButton("Modficar");
		jbP1Eliminar = new JButton("Eliminar");
		jbP1Llistar = new JButton("Llistar");
		jbP1ActualitzarSala = new JButton("Actualitzar");
		jbP1AnarId = new JButton("Anar a id");
		
		/*Li afegim el escoltadors de events per escoltar el botons*/
		jbP1Inici.addActionListener((ActionListener) this); 
		jbP1Fi.addActionListener((ActionListener) this);
		jbP1Esq.addActionListener((ActionListener) this);
		jbP1Drt.addActionListener((ActionListener) this);
		jbP1Afegir.addActionListener((ActionListener) this);
		jbP1Modificar.addActionListener((ActionListener) this);
		jbP1Eliminar.addActionListener((ActionListener) this);
		jbP1Llistar.addActionListener((ActionListener) this);
		jbP1ActualitzarSala.addActionListener((ActionListener)this);
		
		jbP1AnarId.addActionListener((ActionListener) this);
		
	}
	
	private void configurarPestanya1(){
		System.out.println("[FinestraPrincipal]:[configurarPestanya1]");
		/*Afegim etiquetes, textsAreas i botons a la graella*/
		jpPestanyaSales.add(jlP1Id);
		jpPestanyaSales.add(jtP1Id);
		
		jpPestanyaSales.add(jlP1NomSala);
		jpPestanyaSales.add(jtP1NomSala);
		
		jpPestanyaSales.add(jlP1NumButaquesOperatives);
		jpPestanyaSales.add(jtP1NumButaquesOperatives);
		
		jpPestanyaSales.add(jlP1MaxColumnes);
		jpPestanyaSales.add(jtP1MaxColumnes);
		
		jpPestanyaSales.add(jlP1MaxFiles);
		jpPestanyaSales.add(jtP1MaxFiles);
		
		jpPestanyaSales.add(jlP1Descripcio);
		jpPestanyaSales.add(jtP1Descripcio);
		
		jpPestanyaSales.add(jbP1Inici);
		jpPestanyaSales.add(jbP1Fi);

		jpPestanyaSales.add(jbP1Esq);
		jpPestanyaSales.add(jbP1Drt);
		
		jpPestanyaSales.add(jbP1Modificar);
		jpPestanyaSales.add(jbP1Eliminar);
		
		jpPestanyaSales.add(jbP1Afegir);
		jpPestanyaSales.add(jbP1Llistar);
		
		jpPestanyaSales.add(jbP1AnarId);
		jpPestanyaSales.add(jtP1AnarId);
		
		jpPestanyaSales.add(jbP1ActualitzarSala);
		
		/*Afegim la pestanya*/
		jtpPestanyes.add("Sales",jpPestanyaSales);
	}
	private void conectarSalesBBDDPestanya1(){
		System.out.println("[FinestraPrincipal]:[conectarSalesBBDDPestanya1]");
		controladorSalas = new ControladorSalas();
		sales = controladorSalas.llistarSales();
		punterSala = 0;
		System.out.println("sales null?"+sales!=null);
		controladorSalasConectat = (sales!=null);
		publicarSala();
	}
	
	private void construirPopUpPestanya1(){
		System.out.println("[FinestraPrincipal]:[construirPopUpPestanya1]");
		salaInserirPopUp = new SalaFinestraInserir(jfFinestraPrincipal, controladorSalas, sales);
		salaModificarPopUp = new SalaFinestraModificar(jfFinestraPrincipal, controladorSalas, sala);
	}
	
	private void afegirSala(){
		System.out.println("[FinestraPrincipal]:[afegirSala]1");
		salaInserirPopUp.obrirFinestra(controladorSalas, sales);
		publicarSala();
	}
	
	private void modificarSala(){
		System.out.println("[FinestraPrincipal]:[modificarSala]");
		salaModificarPopUp.obrirFinestra(controladorSalas, sala);
		publicarSala();
	}
	
	private void eliminarSala(){
		System.out.println("[FinestraPrincipal]:[eliminarSala]");
		System.out.println(sales.size());
		System.out.println("elimina"+punterSala+", "+sala);
		sales.remove(punterSala);
		try {
			controladorSalas.eliminarSala(sala);
		} catch (ControladorSalasException e) {
			System.out.println("no s'ha pogut eliminar la sala");
			e.printStackTrace();
		}
		if(punterSala > 0)punterSala--;
		System.out.println(sales.size());
		System.out.println("punter sala"+punterSala+", "+sala);
		publicarSala();
	}
	
	private void anarASala(int i){
		Iterator itSales= sales.iterator();
		int j=0;
		while(itSales.hasNext()){
			j++;
			if(((Sala)itSales.next()).getId()==i){
				punterSala=j;
				break;
			}
		}
		publicarSala();
	}
	private void seguentSala(){
		System.out.println("seguent");
		System.out.println((punterSala+1)+"<"+(sales.size())+"="+((punterSala+1) < sales.size()));
		if((punterSala+1) < sales.size()){
			System.out.println(punterSala);
			System.out.println("->");
			++punterSala;
			System.out.println(punterSala);
			publicarSala((Sala)sales.elementAt(punterSala));
		}
	}
	
	private void anteriorSala(){
		System.out.println("anterior");
		System.out.println(punterSala+">"+0+"="+(punterSala > 0));
		if(punterSala > 0){
			System.out.println(punterSala);
			System.out.println("<-");
			--punterSala;
			System.out.println(punterSala);
			publicarSala((Sala)sales.elementAt(punterSala));
		}

	}
	
	private void primeraSala(){
		punterSala=0;
		publicarSala();
	}
	
	private void ultimaSala(){
		punterSala = sales.size()-1;
		publicarSala();
	}
	private void publicarSala(Sala sala){
		System.out.println(sala);
		jtP1Id.setText(""+sala.getId());
		jtP1NomSala.setText(sala.getNomSala());
		jtP1NumButaquesOperatives.setText(""+sala.getNumButaquesOperatives());
		jtP1MaxFiles.setText(""+sala.getNumMaxFiles());
		jtP1MaxColumnes.setText(""+sala.getNumMaxColumnes());
		jtP1Descripcio.setText(sala.getDescripcio());
	}
	
	private void publicarSala(){
		System.out.println("[FinestraPrincipal]:[publicarSala]->"+sala);
		if(sales.size() > 0 && controladorSalasConectat){
			sala=(Sala)sales.elementAt(punterSala);
			jtP1Id.setText(""+sala.getId());
			jtP1NomSala.setText(sala.getNomSala());
			jtP1NumButaquesOperatives.setText(""+sala.getNumButaquesOperatives());
			jtP1MaxFiles.setText(""+sala.getNumMaxFiles());
			jtP1MaxColumnes.setText(""+sala.getNumMaxColumnes());
			jtP1Descripcio.setText(sala.getDescripcio());			
			
		}else{
			jtP1Id.setText("");
			jtP1NomSala.setText("");
			jtP1NumButaquesOperatives.setText("");
			jtP1MaxFiles.setText("");
			jtP1MaxColumnes.setText("");
			jtP1Descripcio.setText("");
		}
	}
	
	public void resetTextSala(){
		/*Posem els textArea buits*/
		jtP1Descripcio.setText("");
		jtP1Id.setText("");
		jtP1MaxFiles.setText("");
		jtP1NomSala.setText("");
		jtP1MaxColumnes.setText("");
	}
/**************************************************************************************/
/********************************FI PESTANYA 1 SALES***********************************/
/**************************************************************************************/

	
	
/**************************************************************************************/
/********************************PESTANYA 1 SALES**************************************/
/**************************************************************************************/
		
		private void construirPestanya2(){
			System.out.println("[FinestraPrincipal]:[construirPestanya2]");
			/*Creem un panell de n files i m columnes*/
			jpPestanyaSales = new JPanel(new GridLayout(12,2));
			
			
			/*Creem les etiquetes*/
			jlP2Id = new JLabel("Nom Pelicula");
			jlP2Titol = new JLabel("Titol");
			jlP2Durada = new JLabel("Durada");
			jlP2EdatRecomenada = new JLabel("Edat Recomenada");
			jlP2Genere = new JLabel("Genere");
			jlP2Sinopsis = new JLabel("Sinopsis");
			
			/*Creem els texts areas*/
			jtP2Id = new JTextField();
			jtP2Titol = new JTextField();
			jtP2Durada = new JTextField();
			jtP2EdatRecomanada = new JTextField();
			jtP2Genere = new JTextField();
			jtP2Sinopsis = new JTextField();

			jtP2AnarId = new JTextField();
			
			/*Set que no es pugui modificar*/
			jtP2Id.setEditable(false);
			jtP2Titol.setEditable(false);
			jtP2Durada.setEditable(false);
			jtP2EdatRecomanada.setEditable(false);
			jtP2Genere.setEditable(false);
			jtP2Sinopsis.setEditable(false);
			
			jtP1AnarId.setEditable(true);
			
			/*Creem el botons*/
			jbP1Inici = new JButton("|<");
			jbP1Fi = new JButton(">|");
			jbP1Esq = new JButton("<<");
			jbP1Drt = new JButton(">>");
			jbP1Afegir = new JButton("Afegir");
			jbP1Modificar = new JButton("Modficar");
			jbP1Eliminar = new JButton("Eliminar");
			jbP1Llistar = new JButton("Llistar");
			jbP1ActualitzarSala = new JButton("Actualitzar");
			jbP1AnarId = new JButton("Anar a id");
		
			/*Li afegim el escoltadors de events per escoltar el botons*/
			jbP1Inici.addActionListener((ActionListener) this); 
			jbP1Fi.addActionListener((ActionListener) this);
			jbP1Esq.addActionListener((ActionListener) this);
			jbP1Drt.addActionListener((ActionListener) this);
			jbP1Afegir.addActionListener((ActionListener) this);
			jbP1Modificar.addActionListener((ActionListener) this);
			jbP1Eliminar.addActionListener((ActionListener) this);
			jbP1Llistar.addActionListener((ActionListener) this);
			jbP1ActualitzarSala.addActionListener((ActionListener)this);
			
			jbP1AnarId.addActionListener((ActionListener) this);
			
		}
		
		private void configurarPestanya2(){
			System.out.println("[FinestraPrincipal]:[configurarPestanya1]");
			/*Afegim etiquetes, textsAreas i botons a la graella*/
			jpPestanyaSales.add(jlP1Id);
			jpPestanyaSales.add(jtP1Id);
			
			jpPestanyaSales.add(jlP2Titol);
			jpPestanyaSales.add(jtP2Titol);
			
			jpPestanyaSales.add(jlP2Durada);
			jpPestanyaSales.add(jtP2Durada);
			
			jpPestanyaSales.add(jlP2EdatRecomenada);
			jpPestanyaSales.add(jtP2EdatRecomanada);
			
			jpPestanyaSales.add(jlP2Genere);
			jpPestanyaSales.add(jtP2Genere);
			
			jpPestanyaSales.add(jlP2Sinopsis);
			jpPestanyaSales.add(jtP2Sinopsis);
			
			jpPestanyaSales.add(jbP2Inici);
			jpPestanyaSales.add(jbP2Fi);

			jpPestanyaSales.add(jbP2Esq);
			jpPestanyaSales.add(jbP2Drt);
			
			jpPestanyaSales.add(jbP2Modificar);
			jpPestanyaSales.add(jbP2Eliminar);
			
			jpPestanyaSales.add(jbP2Afegir);
			jpPestanyaSales.add(jbP2Llistar);
			
			jpPestanyaSales.add(jbP2AnarId);
			jpPestanyaSales.add(jtP2AnarId);
			
			jpPestanyaSales.add(jbP2ActualitzarPelicula);
			
			/*Afegim la pestanya*/
			jtpPestanyes.add("Sales",jpPestanyaSales);
		}
		private void conectarSalesBBDDPestanya2(){
			System.out.println("[FinestraPrincipal]:[conectarSalesBBDDPestanya1]");
			controladorPelicules = new ControladorPelicula();
			pelicules = controladorPelicules.getCatalegpelicules();
			punterPelicula = 0;
			System.out.println("pelicules null?"+sales!=null);
			controladorPeliculasConectat = (sales!=null);
			publicarPelicula();
		}
		
		private void construirPopUpPestanya2(){
			System.out.println("[FinestraPrincipal]:[construirPopUpPestanya1]");
			peliculaInserirPopUp = new PeliculaFinestraInserir(jfFinestraPrincipal, controladorPelicules, pelicules);
			peliculaModificarPopUp = new PeliculaFinestraModificar(jfFinestraPrincipal, controladorPelicules, pelicula);
		}
		
		private void afegirPelicula(){
			System.out.println("[FinestraPrincipal]:[afegirPelicula]1");
			peliculaInserirPopUp.obrirFinestra(controladorPelicules, pelicules);
			publicarPelicula();
		}
		
		private void modificarPelicula(){
			System.out.println("[FinestraPrincipal]:[modificarPelicula]");
			peliculaModificarPopUp.obrirFinestra(controladorPelicules, pelicula);
			publicarPelicula();
		}
		
		private void eliminarPelicula(){
			System.out.println("[FinestraPrincipal]:[eliminarPelicula]");
			System.out.println(sales.size());
			System.out.println("elimina"+punterPelicula+", "+pelicula);
			sales.remove(punterPelicula);
			
				try {
					controladorPelicules.eliminarPelicula(pelicula);
				} catch (ControladorPeliculesException e) {
					// TODO Auto-generated catch block
					System.out.println("no s'ha pogut eliminar la pelicula");
					e.printStackTrace();
				}
				
			
			if(punterPelicula > 0)punterPelicula--;
			System.out.println(sales.size());
			System.out.println("punter Pelicula"+punterPelicula+", "+pelicula.getId());
			publicarPelicula();
		}
		
		private void anarAPelicula(int i){
			Iterator itSales= sales.iterator();
			int j=0;
			while(itSales.hasNext()){
				j++;
				if(((Pelicula)itSales.next()).getId()==i){
					punterPelicula=j;
					break;
				}
			}
			publicarPelicula();
		}
		private void seguentPelicula(){
			System.out.println("seguent");
			System.out.println((punterPelicula+1)+"<"+(sales.size())+"="+((punterPelicula+1) < sales.size()));
			if((punterPelicula+1) < sales.size()){
				System.out.println(punterPelicula);
				System.out.println("->");
				++punterPelicula;
				System.out.println(punterPelicula);
				publicarPelicula((Pelicula)sales.elementAt(punterPelicula));
			}
		}
		
		private void anteriorPelicula(){
			System.out.println("anterior");
			System.out.println(punterPelicula+">"+0+"="+(punterPelicula > 0));
			if(punterPelicula > 0){
				System.out.println(punterPelicula);
				System.out.println("<-");
				--punterPelicula;
				System.out.println(punterPelicula);
				publicarPelicula((Pelicula)sales.elementAt(punterPelicula));
			}

		}
		
		private void primeraPelicula(){
			punterPelicula=0;
			publicarPelicula();
		}
		
		private void ultimaPelicula(){
			punterPelicula = sales.size()-1;
			publicarPelicula();
		}
		private void publicarPelicula(Pelicula Pelicula){
			System.out.println(Pelicula);
			jtP2Id.setText(""+Pelicula.getId());
			jtP2Titol.setText(pelicula.getTitol());
			jtP2.setText(""+Pelicula.getNumButaquesOperatives());
			jtP2MaxFiles.setText(""+Pelicula.getNumMaxFiles());
			jtP2MaxColumnes.setText(""+Pelicula.getNumMaxColumnes());
			jtP2Descripcio.setText(Pelicula.getDescripcio());
		}
		
		private void publicarPelicula(){
			System.out.println("[FinestraPrincipal]:[publicarPelicula]->"+Pelicula);
			if(sales.size() > 0 && controladorPeliculasConectat){
				Pelicula=(Pelicula)sales.elementAt(punterPelicula);
				jtP1Id.setText(""+sala.getId());
				jtP1NomPelicula.setText(Pelicula.getNomPelicula());
				jtP1NumButaquesOperatives.setText(""+Pelicula.getNumButaquesOperatives());
				jtP1MaxFiles.setText(""+Pelicula.getNumMaxFiles());
				jtP1MaxColumnes.setText(""+Pelicula.getNumMaxColumnes());
				jtP1Descripcio.setText(Pelicula.getDescripcio());			
				
			}else{
				jtP1Id.setText("");
				jtP1NomPelicula.setText("");
				jtP1NumButaquesOperatives.setText("");
				jtP1MaxFiles.setText("");
				jtP1MaxColumnes.setText("");
				jtP1Descripcio.setText("");
			}
		}
		
		public void resetTextPelicula(){
			/*Posem els textArea buits*/
			jtP1Descripcio.setText("");
			jtP1Id.setText("");
			jtP1MaxFiles.setText("");
			jtP1NomPelicula.setText("");
			jtP1MaxColumnes.setText("");
		}
/**************************************************************************************/
/********************************FI PESTANYA 1 SALES***********************************/
/**************************************************************************************/

	
	
	
	private void construirMenu(){
		System.out.println("[FinestraPrincipal]:[construirMenu]");
		jmbFinestra = new JMenuBar();
		
		/* 		>>menu BBDD*/
		jmBBDD = new JMenu("BBDD");
		jmiConectar = new JMenuItem("Conectar");
		jmiDesconectar = new JMenuItem("Desconectar");

		jmiConectar.addActionListener((ActionListener) this);
		jmiDesconectar.addActionListener((ActionListener) this);
		
		jmBBDD.add(jmiConectar);
		jmBBDD.add(jmiDesconectar);
		
		/* 		>>menu Ajuda*/
		jmAjuda = new JMenu("Ajuda");
		jmiAjudar = new JMenuItem("Ajuda");
		jmiAcerca = new JMenuItem("Acerca de ...");
				
		jmiAjudar.addActionListener((ActionListener) this);
		jmiAcerca.addActionListener((ActionListener) this);
		
		jmAjuda.add(jmiAjudar);
		jmAjuda.add(jmiAcerca);
		
		/* MENU */
		jmbFinestra.add(jmBBDD);
		jmbFinestra.add(jmAjuda);
		
		jfFinestraPrincipal.setJMenuBar(jmbFinestra);
	}
	
	public void obrirFinestra(){		
		jfFinestraPrincipal.setSize(AMPLADA,ALTURA);
		resetTextSala();
		jfFinestraPrincipal.setVisible(true);
	}
	
	public void obrirFinestra(int w, int h){
		jfFinestraPrincipal.setSize(w,h);
		resetTextSala();
		jfFinestraPrincipal.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		AbstractButton origen = (AbstractButton) e.getSource();

		if(origen == jbP1Afegir && controladorSalasConectat){
			afegirSala();
		}if(origen == jbP1Modificar && controladorSalasConectat){
			modificarSala();
		}if(origen == jbP1ActualitzarSala && controladorSalasConectat){
			publicarSala();
		}if(origen == jbP1Eliminar && controladorSalasConectat){
			eliminarSala();
		}if(origen == jbP1Drt && controladorSalasConectat){
			seguentSala();
		}if(origen == jbP1Esq && controladorSalasConectat){
			anteriorSala();
		}if(origen == jbP1Inici && controladorSalasConectat){
			primeraSala();
		}if(origen == jbP1Fi && controladorSalasConectat){
			ultimaSala();
		}
	}

	public void windowActivated(WindowEvent e) {	
	}


	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		System.out.println("Tancant aplicacio");
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {	
	}

	public void windowOpened(WindowEvent e) {
		
	}
}
