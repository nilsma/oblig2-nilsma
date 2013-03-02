package oblig2;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// To be revised
/**
 * @author Bjørnar Fjeldstad
 */

public class View<contentPane> extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JMenuBar menuBar;
	JMenu menu;
	JMenuItem open ; 
	JMenuItem saveAs;
	JMenuItem close;
	JLabel label1;
	JTextArea searchString;
	JTextArea document;
	JPanel plSearchString;
	JScrollPane plDocument;
	JPanel plSearch;
	JPanel plFrequency;
	JPanel plNext;
	JButton search;
	JButton frequency;
	JButton next;
	File fil;
	FileOutputStream fOut;
	PrintWriter pw;
	JFileChooser fChoose;
	int returnValg;
	Action a;
	String textToView;
	//private JScrollPane scrollpane;
	//private JScrollBar scrollbar;
	//private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int tempWidth = 1300;
	private int tempHeight = 700;
	
	/**
	 * Konstruktøren til View som oppretter eit frame objekt frå metoden frame.
	 */
	public View(){
		frame();
	}

	/**
	 * Metoden frame setter opp ny instans av contentpane frå klassen JFrame 
	 * Containter contentpane har ein flowlayout frå venstre mot høgre.
	 * Den tar inn objekter i frå metodene setPanelFornamn, setPanelEtternamn,setPanelFirma,setPanelTlf, setPanelKommentar,
	 * setPanelLagre og setMenu som festes i containeren ved å ta inn variabelen contentpane som argument.
	 * contentPane er satt til å vere synlig og har ein fast størrelse og lokasjon på skjermen som ikkje kan endres.
	 * contentPane er også satt til å lukkes og programmet skal termineres nåt bruker lukker vinduet.
	 * Bakgrunnsfargen er satt til gul.
	 * Det opprettes også ein ny KontaktListe som brukes til å legge input frå brukeren inn 
	 * som objekter i ein ArrayList i klassen Kontaktliste og lagre det. 
	 * @throws IOException
	 */
	public void frame() {		
		//StreamReader reader = new StreamReader();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setPanelShowFrequency(contentPane);
		setPanelSearchString(contentPane);
		setPanelSearchText(contentPane);
		setPanelNext(contentPane);
		setPanelDocument(contentPane);
		setMenu(contentPane);
		//setScrollBar(scrollbar);
		setVisible(true);
		//setSize(screenSize.width,screenSize.height);
		//setSize(1200,700);
		setSize(tempWidth, tempHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * 
	 * @param scrollbar
	 */
	public void setScrollBar(JScrollBar scrollbar) {
		scrollbar = new JScrollBar();	
	}
	
	/**
	 * Metoden setMeny intialize a new menu in the container contentPane
	 * Variabelen menyBar initalize a new JMenuBar and sets title to "Document Reader"
	 * @param contentPane
	 * @param fileR 
	 */
	public void setMenu(Container contentPane) {
		menuBar = new JMenuBar();
		setTitle("Document Reader");
		menu = new JMenu("File");
		open = new JMenuItem("Open");
		saveAs = new JMenuItem("Save as");
		close = new JMenuItem("Close");
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(open);
		open.addActionListener(new Action());
		menu.add(saveAs);
		saveAs.addActionListener(this);
		menu.add(close);
		close.addActionListener(new Action());
	}
	
	/**
	 * Method setPanelSearchString.
	 * @param contentPane
	 */
	public void setPanelSearchString(Container contentPane){
		label1 = new JLabel("Search field");
		label1.setPreferredSize(new Dimension(75,30));
		JPanel plSearchString = new JPanel();
		searchString = new JTextArea("");
		searchString.setPreferredSize(new Dimension(350,25));
		contentPane.add(plSearchString);
		plSearchString.add(label1);
		plSearchString.add(searchString);
	}
	
	/**
	 * Method setPanelDocument 
	 */
	public void setPanelDocument(Container contentPane){
		try {
			SomeClass someClass = new SomeClass();
			//textToView = Action.textToString();
			textToView = someClass.readFile("/home/nilsma/pg100.txt");
			document = new JTextArea(textToView);
			//scrollpane = new JScrollPane(document);
			//document = new JScrollPane();
			//document.setText(textToView);
			//document.setPreferredSize(new Dimension(1000,1000));
			//document.setPreferredSize(new Dimension(600, 500));
			plDocument = new JScrollPane(document);
			plDocument.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			//plDocument.setPreferredSize(new Dimension(600, 500));
			//plDocument.setPreferredSize(new Dimension(screenSize.width - 50, screenSize.height - 50));
			plDocument.setPreferredSize(new Dimension(tempWidth - 50, tempHeight - 200));
			//plDocument.setLineWrap(true);
			//plDocument.setPreferredSize(new Dimension(1900,900));
			contentPane.add(plDocument);
		} catch(IOException e) {
			System.out.println("IOException found!");
		}
	}

	/**
	 * Method setPanelSearchText 
	 * @param contentPane 
	 */
	public void setPanelSearchText(Container contentPane){
		plSearch = new JPanel();
		search = new JButton("Search for phrase");
		search.setPreferredSize(new Dimension(215,50));
		contentPane.add(plSearch);
		plSearch.add(search);
		search.addActionListener(this);
	}
	
	/**
	 * Method setPanelShowFrequency
	 * @param contentPane
	 */
	public void setPanelShowFrequency(Container contentPane){
		plFrequency = new JPanel();
		frequency = new JButton("Show Frequency");
		frequency.setPreferredSize(new Dimension(215,50));
		contentPane.add(plFrequency);
		plFrequency.add(frequency);
		frequency.addActionListener(this);
	}
	
	/**
	 * Method setPanelNext
	 * @param contentPane
	 */	
	public void setPanelNext(Container contentPane){
		plNext = new JPanel();
		next = new JButton("Next phrase");
		next.setPreferredSize(new Dimension(215,50));
		contentPane.add(plNext);
		plNext.add(next);
		next.addActionListener(a);
	}
	
	/**
	 * Method clearFields resets the textareas in search and documents.
	 * 
	 */
	public void clearFields(){
		search.setText("");
		document.setText("");	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}



