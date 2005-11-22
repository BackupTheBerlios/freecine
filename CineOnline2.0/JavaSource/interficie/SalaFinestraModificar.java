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

import sala.ControladorSalas;
import sala.Sala;


/**
 * @author A4alumno
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SalaFinestraModificar implements ActionListener, WindowListener{
	private final int AMPLADA=300;
	private final int ALTURA=200;
	
	/* Variables Finestra Principal */
	private ControladorSalas controladorSalas;
	private Sala sala;
	private JFrame jfFinestraPrincipal;
	
	/*Estructua de la finestra*/
	String titol;
	private JDialog jdFinestraModificar;
	private Container cFinestraModificar;
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
	private JButton jbModificar;
	private JButton jbCancelar;
	
	
	
	public SalaFinestraModificar(JFrame jfFinestraPrincipal, ControladorSalas controladorSalas, Sala sala){
		titol="Afegir Sala";
		this.jfFinestraPrincipal = jfFinestraPrincipal;
		this.controladorSalas= controladorSalas;
		this.sala = sala;

		construir();
		configurar();
	}
	
	private void construir(){
		/*Unim el popup amb la finestra principal*/
		jdFinestraModificar = new JDialog(this.jfFinestraPrincipal,titol);
		
		/*Agafem el contingut de la finestraModificarSala*/
		cFinestraModificar = jdFinestraModificar.getContentPane();
		
		/*Li donem el format de finestra que volem*/
		cFinestraModificar.setLayout(new BorderLayout());
		
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
		
		/*Fem que no es pugui editar el camp id*/
		jtId.setEditable(false);
		
		/*Creem el botons*/
		jbModificar = new JButton("Modificar");
		jbCancelar = new JButton("Cancelar");
		
		/*Li afegim el escoltadors de events per escoltar el botons*/
		jbModificar.addActionListener((ActionListener) this);
		jbCancelar.addActionListener((ActionListener) this);
	}
	
	private void configurar(){
		/* Impedim que pugui anar a la finestra 
		 * principal sense haber tencat aquesta*/
		jdFinestraModificar.setModal(true);
		
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
		
		jpContingut.add(jbModificar);
		jpContingut.add(jbCancelar);
		
		/*Afegim al contingut de la finetra aquesta estructura*/
		cFinestraModificar.add(jpContingut);
	}
	
	public void obrirFinestra(ControladorSalas controladorSalas, Sala sala){
		this.controladorSalas= controladorSalas;
		this.sala = sala;
		
		jdFinestraModificar.setSize(AMPLADA,ALTURA);
		resetText();
		jdFinestraModificar.setVisible(true);
	}
	
	public void obrirFinestra(int w, int h, Sala sala){
		this.sala = sala;
		
		jdFinestraModificar.setSize(w,h);
		
		resetText();
		jdFinestraModificar.setVisible(true);
	}
	
	private void modificarSala(){
		Sala sala= new Sala();
		sala.setAll(
				Integer.parseInt(jtId.getText()),
				jtNomSala.getText(),
				Integer.parseInt(jtMaxColumnes.getText()),
				Integer.parseInt(jtMaxFiles.getText()),
				jtDescripcio.getText());
		
		sala.generarButaques(sala.getNumMaxFiles(), sala.getNumMaxColumnes());
		
		controladorSalas.actualitzarSala(sala);
		jdFinestraModificar.setVisible(false);

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

		if(origen == jbModificar){
			modificarSala();
		}else if(origen ==jbCancelar){
			jdFinestraModificar.setVisible(false);
		}		
	}

	public void windowActivated(WindowEvent e) {	
	}


	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		jdFinestraModificar.setVisible(false);
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