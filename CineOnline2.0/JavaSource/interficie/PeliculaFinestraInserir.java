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
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pelicula.ControladorPelicula;

import sala.ControladorSalas;
import sala.Sala;


/**
 * @author A4alumno
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PeliculaFinestraInserir implements ActionListener, WindowListener{
	private final int AMPLADA=300;
	private final int ALTURA=200;
	
	/* Variables Finestra Principal */
	private ControladorPelicula controladorPelicules;
	private Vector sales;
	private JFrame jfFinestraPrincipal;
	
	/*Estructua de la finestra*/
	String titol;
	private JDialog jdFinestraInserir;
	private Container cFinestraInserir;
	private JPanel jpContingut;
	
	/*Etiquetes*/
	private JLabel jlId;
	private JLabel jlNomSala;
	private JLabel jlMaxColumnes;
	private JLabel jlMaxFiles;
	private JLabel jlDescripcio;
	
	/*TextArea*/
	private JTextField jtId;
	private JTextField jtNomSala;
	private JTextField jtMaxColumnes;
	private JTextField jtMaxFiles;
	private JTextField jtDescripcio;
	
	/*Botons*/
	private JButton jbInserir;
	private JButton jbCancelar;
	
	
	
	public PeliculaFinestraInserir(JFrame jfFinestraPrincipal, ControladorPelicula controladorPelicules, Vector pelicules){
		titol="Afegir Sala";
		this.jfFinestraPrincipal = jfFinestraPrincipal;
		this.controladorPelicules= controladorPelicules;
		this.sales = sales;

		construir();
		configurar();
	}
	
	private void construir(){
		/*Unim el popup amb la finestra principal*/
		jdFinestraInserir = new JDialog(this.jfFinestraPrincipal,titol);
		
		/*Agafem el contingut de la finestraInserirSala*/
		cFinestraInserir = jdFinestraInserir.getContentPane();
		
		/*Li donem el format de finestra que volem*/
		cFinestraInserir.setLayout(new BorderLayout());
		
		/*Creem un panell de n files i m columnes*/
		jpContingut = new JPanel(new GridLayout(7,2));
		
		/*Creem les etiquetes*/
		jlId = new JLabel("id");
		jlNomSala = new JLabel("Nom Sala");
		jlMaxColumnes = new JLabel("Nombre màxim columnes");
		jlMaxFiles = new JLabel("Nombre màxim files");
		jlDescripcio = new JLabel("Descripcio");
		
		/*Creem els texts areas*/
		jtDescripcio = new JTextField();
		jtId = new JTextField();
		jtMaxFiles = new JTextField();
		jtNomSala = new JTextField();
		jtMaxColumnes = new JTextField();
		
		/*Creem el botons*/
		jbInserir = new JButton("Inserir");
		jbCancelar = new JButton("Cancelar");
		
		/*Li afegim el escoltadors de events per escoltar el botons*/
		jbInserir.addActionListener((ActionListener) this);
		jbCancelar.addActionListener((ActionListener) this);
	}
	
	private void configurar(){
		/* Impedim que pugui anar a la finestra 
		 * principal sense haber tencat aquesta*/
		jdFinestraInserir.setModal(true);
		
		/*Afegim etiquetes, textsAreas i botons a la graella*/
		jpContingut.add(jlId);
		jpContingut.add(jtId);
		
		jpContingut.add(jlNomSala);
		jpContingut.add(jtNomSala);
		
		jpContingut.add(jlMaxColumnes);
		jpContingut.add(jtMaxColumnes);
		
		jpContingut.add(jlMaxFiles);
		jpContingut.add(jtMaxFiles);
		
		jpContingut.add(jlDescripcio);
		jpContingut.add(jtDescripcio);
		
		jpContingut.add(jbInserir);
		jpContingut.add(jbCancelar);
		
		/*Afegim al contingut de la finetra aquesta estructura*/
		cFinestraInserir.add(jpContingut);
	}
	
	public void obrirFinestra(controladorPelicules controladorPelicules, Vector sales){
		this.controladorPelicules= controladorPelicules;
		this.sales = sales;
		
		jdFinestraInserir.setSize(AMPLADA,ALTURA);
		resetText();
		jdFinestraInserir.setVisible(true);
	}
	
	public void obrirFinestra(int w, int h, Vector sales){
		this.sales = sales;
		
		jdFinestraInserir.setSize(w,h);
		
		resetText();
		jdFinestraInserir.setVisible(true);
	}
	
	private void afegirSala(){
		Sala sala= new Sala();
		sala.setAll(
				Integer.parseInt(jtId.getText()),
				jtNomSala.getText(),
				Integer.parseInt(jtMaxColumnes.getText()),
				Integer.parseInt(jtMaxFiles.getText()),
				jtDescripcio.getText());
		
		sala.generarButaques();
		
		controladorPelicules.afegirPelicula(pelicula);
		//sales.add(sala);
		
		jdFinestraInserir.setVisible(false);
	}
	
	public void resetText(){
		/*Posem els textArea buits*/
		jtDescripcio.setText("");
		jtId.setText("");
		jtMaxFiles.setText("");
		jtNomSala.setText("");
		jtMaxColumnes.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		AbstractButton origen = (AbstractButton) e.getSource();

		if(origen == jbInserir){
			afegirSala();
		}else if(origen ==jbCancelar){
			jdFinestraInserir.setVisible(false);
		}		
	}

	public void windowActivated(WindowEvent e) {	
	}


	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		jdFinestraInserir.setVisible(false);
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