package componentesSwingAvanzados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

//@SuppressWarnings("unused")
public class JMenuItemConIconosyCasillasVerificacion_107_108 {

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame marco = new Menuprocesador();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setVisible(true);
			}
}

class Menuprocesador extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public Menuprocesador () {
		setTitle("Procesador de textos Prehistoric 1.1");
		setSize(600,510);
		setLocationRelativeTo(null);		
		add(new LaminaProcesadora());	
		
		Toolkit mipantalla=Toolkit.getDefaultToolkit();
		Image miIcono=mipantalla.getImage("../Imagenes/mon.png");
		setIconImage(miIcono);		
		
	}
	
}	

@SuppressWarnings("serial")
class LaminaProcesadora extends JPanel{	
	
	JMenuItem abrir,nuevo,guardar;
	
	private class Cazaeventos implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==nuevo) {
				
				int opcion = JOptionPane.showConfirmDialog(LaminaProcesadora.this, "¿Desea borrar todo?", "Atención", JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
				
				if(opcion == 0) {miarea.setText("");}
				
			}
			
			if(e.getSource()==abrir) {		
				
				JFileChooser seleccionAbrir = new JFileChooser();
				File f = new File("C:/Users/oscar/OneDrive/Escritorio");
				seleccionAbrir.setCurrentDirectory(f);	
				
			  //  seleccionAbrir.setCurrentDirectory(null);
				
			    int valorRetorno = seleccionAbrir.showOpenDialog(LaminaProcesadora.this);
			    								
				//getSelectedFile() devuelve el archivo ya sea por acciones en la UI o por código	
			    
			
				
				try {	
					
					if(valorRetorno==JFileChooser.CANCEL_OPTION) {
						
						return;						
					}	
					
					
					File archivo = new File(seleccionAbrir.getSelectedFile()+"");					
					
					if(archivo.exists()) {
					JOptionPane.showMessageDialog(null, "Ha abierto el archivo " + archivo.getName());
					}
					//Abriendo desde un buffer
					
					BufferedReader br = new BufferedReader(new FileReader(archivo.getPath()));
					miarea.read(br, null);				
					String linea=br.readLine();
					while (linea != null) {
					//System.out.println(linea);					 
					
						miarea.read(br, null);	
						linea = br.readLine();
					
					}	
					
					br.close();
										
					
				} catch (IOException ex) {
					
					System.out.println("Ha ocurrido un error de tipo" + ex.getClass().getName());
				}				
				
			} else if(e.getSource()==guardar) {
				
				
                
				JFileChooser seleccionGuardar = new JFileChooser();
				
				//cambiar el directorio por defecto
				File f = new File("C:/Users/oscar/OneDrive/Escritorio");
				seleccionGuardar.setCurrentDirectory(f);		
				
				int valorRetorno = seleccionGuardar.showSaveDialog(LaminaProcesadora.this);	
				
				if(valorRetorno==JFileChooser.CANCEL_OPTION) {
					
					return;						
				}						
				//seleccionGuardar.setApproveButtonToolTipText("Pincha aqui para guardar el archivo como .txt");
				//seleccionGuardar.setApproveButtonText("ke pasa");				
								
				File archivo = new File(seleccionGuardar.getSelectedFile() + ".txt");
				
				try {
					//for (String line : textArea.getText().split("\\n")) doStuffWithLine(line);
		           
		            if(miarea.getText().equals("")) {
		            	JOptionPane.showConfirmDialog(LaminaProcesadora.this, "El archivo esta vacío.Imposible realizar la acción", "Menu de Guardado", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
		            	}else {
		            		 BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		          
		            		 for(String line : miarea.getText().split("\\n")) {
		            	 bw.write(line);
				       //bw.newLine();
		            }
		           
		            bw.close();
		            	
				JOptionPane.showMessageDialog(LaminaProcesadora.this, "Archivo Guardado Correctamente", "Menu de Guardado", JOptionPane.INFORMATION_MESSAGE);
		            	}
		            
		        } catch (Exception ex) {
		        
		            System.out.println("Ha ocurrido un error de tipo " + ex.getClass().getName());
		        
		        }
				
			}			
			
		}
	}
	
	@SuppressWarnings({ })
	public LaminaProcesadora() {
		
		setLayout(new BorderLayout());		
		
		new JPanel();
		
		//-------creacion barra de menu
		mibarra = new JMenuBar();
		
		//mibarra.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//--------creacion de los menus de la barra
		fuente = new JMenu("Fuente");
		fuente.setToolTipText("Cambio de fuente");
		fuente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		estilo = new JMenu("Estilo");
		estilo.setToolTipText("Cambio del estilo de la fuente");
		estilo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tamaño = new JMenu("Tamaño");
		tamaño.setToolTipText("Cambio de la medidad de la fuente");
		tamaño.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JMenu archivo = new JMenu("Archivo");
		archivo.setToolTipText("Archivo");
		archivo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JMenu edicion = new JMenu("Edición");
		edicion.setToolTipText("Herramientas para Edición de texto");
		edicion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JMenu herramientas = new JMenu("Herramientas");
		herramientas.setToolTipText("Herramientas(En proceso)");
		herramientas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//objetos de cada componente de la barra(Archivo)
		
		nuevo = new JMenuItem("Nuevo Archivo",new ImageIcon("../Imagenes/copiar.gif"));
		
		abrir = new JMenuItem("Abrir Archivo",new ImageIcon("../Imagenes/transfer.png"));		
		
		guardar = new JMenuItem("Guardar Archivo",new ImageIcon("../Imagenes/transfer.png"));
		
		//objetos de cada componente de la barra(Edicion)
		
		JMenuItem cortar = new JMenuItem("Cortar",new ImageIcon("../Imagenes/cortar.gif"));
		cortar.setToolTipText("Presiona CTRL + X para cortar");
		JMenuItem copiar = new JMenuItem("Copiar",new ImageIcon("../Imagenes/copiar.gif"));
		copiar.setToolTipText("Presiona CTRL + C para copiar");
		JMenuItem pegar = new JMenuItem("Pegar",new ImageIcon("../Imagenes/pegar.gif"));
		pegar.setToolTipText("Presiona CTRL + V para pegar");
		copiar.setHorizontalTextPosition(JMenuItem.LEFT);
		cortar.setHorizontalTextPosition(SwingConstants.LEFT);
		pegar.setHorizontalTextPosition(SwingConstants.LEFT);
		
		JMenuItem generales = new JMenuItem("Generales");
		
		//se añaden los objetos con cuidado y en orden 
		
		mibarra.add(archivo);
		archivo.add(nuevo);
		archivo.add(abrir);
		archivo.add(guardar);
		
		//eventos filechooser
		Cazaeventos e = new Cazaeventos();
		nuevo.addActionListener(e);
		abrir.addActionListener(e);
		guardar.addActionListener(e);
		
		
		
		mibarra.add(edicion);
		edicion.add(cortar);
		edicion.add(copiar);
		edicion.add(pegar);
		
		//Añadiendo el KeyBinding a Edicion 
		
		cortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		copiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		pegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		
		mibarra.add(herramientas);
		herramientas.add(generales);
				
		//creacion de los JMenuItems
		
		configura_menu("Broadway","fuente","Broadway", 9,12,"../Imagenes/Broadway24.png");
		configura_menu("Courier","fuente","Courier", 9,12,"../Imagenes/minicourier.png");
		configura_menu("Ravie","fuente","Ravie", 9,12,"../Imagenes/ravie24.png");
		
		JCheckBoxMenuItem negrita = new JCheckBoxMenuItem("Negrita",new ImageIcon("../Imagenes/mininegrita.png"));
		JCheckBoxMenuItem cursiva = new JCheckBoxMenuItem("Cursiva",new ImageIcon("../Imagenes/kursive.png"));
		JCheckBoxMenuItem subrayado = new JCheckBoxMenuItem("Subrayado",new ImageIcon("../Imagenes/Sub24.png"));
		
		negrita.addActionListener(new StyledEditorKit.BoldAction());
		cursiva.addActionListener(new StyledEditorKit.ItalicAction());
		subrayado.addActionListener(new StyledEditorKit.UnderlineAction());
		
		estilo.add(negrita);
		estilo.add(cursiva);
		estilo.add(subrayado);
		
		ButtonGroup migrupo= new ButtonGroup();
		JRadioButtonMenuItem doce = new JRadioButtonMenuItem("12", new ImageIcon("../Imagenes/minidoceB.png"));
		JRadioButtonMenuItem dieciseis = new JRadioButtonMenuItem("16", new ImageIcon("../Imagenes/minidieciseisB.png"));
		JRadioButtonMenuItem veinte = new JRadioButtonMenuItem("20", new ImageIcon("../Imagenes/miniveinteB.png"));
		JRadioButtonMenuItem veinticuatro = new JRadioButtonMenuItem("24", new ImageIcon("../Imagenes/miniveinticuatroB.png"));
		
		//creación de atajos de teclado(hay mas formas de pasar los argumentos)
		
		doce.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		dieciseis.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		veinte.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
		veinticuatro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
		
		migrupo.add(doce);
		migrupo.add(dieciseis);
		migrupo.add(veinte);
		migrupo.add(veinticuatro);
		
		doce.addActionListener(new StyledEditorKit.FontSizeAction("", 12));
		dieciseis.addActionListener(new StyledEditorKit.FontSizeAction("", 16));
		veinte.addActionListener(new StyledEditorKit.FontSizeAction("", 20));
		veinticuatro.addActionListener(new StyledEditorKit.FontSizeAction("", 24));
				
		tamaño.add(doce);
		tamaño.add(dieciseis);
		tamaño.add(veinte);
		tamaño.add(veinticuatro);			
		
		//--------agregar los menus a la barra
		
		mibarra.add(fuente);
		mibarra.add(estilo);
		mibarra.add(tamaño);
		mibarra.setBackground(Color.ORANGE);
		add(mibarra,BorderLayout.NORTH);
		
		//construyendo el area de texto		
		miarea = new JTextPane();
		miarea.setBackground(Color.BLACK);
		miarea.setForeground(Color.GREEN);
		
		miarea.setCaretColor(new Color(205,20,20,220));
		//miarea.setCaretColor(new Color(200,160,76,200));
		//miarea.setCaretColor(Color.MAGENTA);
		
		//construccion de la barra
		JScrollPane scrollbar= new JScrollPane(miarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollbar.setBackground(Color.BLUE);
		
		add(scrollbar,BorderLayout.CENTER);
		
		//creación menu emergente 
		
		JPopupMenu emergente = new JPopupMenu();
		
		JCheckBoxMenuItem negritaE = new JCheckBoxMenuItem("Negrita",new ImageIcon("../Imagenes/mininegrita.png"));
		JCheckBoxMenuItem cursivaE = new JCheckBoxMenuItem("Cursiva",new ImageIcon("../Imagenes/kursive.png"));
		JCheckBoxMenuItem subrayadoE = new JCheckBoxMenuItem("subrayado",new ImageIcon("../Imagenes/Sub24.png"));
		
		negrita.addChangeListener(new javax.swing.event.ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				if(negrita.getState()==true) {negritaE.setState(true);}
				if(negrita.getState()==false) {negritaE.setState(false);}
				
				}
			
		});
		
		negritaE.addChangeListener(new javax.swing.event.ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				if(negritaE.getState()==true) {negrita.setState(true);}
				if(negritaE.getState()==false) {negrita.setState(false);}
				
				}
			
		});

		cursiva.addChangeListener(new javax.swing.event.ChangeListener() {
	
			@Override
			public void stateChanged(ChangeEvent e) {
		
				if(cursiva.getState()==true) { cursivaE.setState(true);}
				if(cursiva.getState()==false) { cursivaE.setState(false);}
		
		}
	
		});

		cursivaE.addChangeListener(new javax.swing.event.ChangeListener() {
	
			@Override
			public void stateChanged(ChangeEvent e) {
		
				if(cursivaE.getState()==true) {cursiva.setState(true);}
				if(cursivaE.getState()==false) {cursiva.setState(false);}
		
			}
	
		});
		
		subrayado.addChangeListener(new javax.swing.event.ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
		
				if(subrayado.getState()==true) { subrayadoE.setState(true);}
				if(subrayado.getState()==false) { subrayadoE.setState(false);}
		
		}
	
		});

		subrayadoE.addChangeListener(new javax.swing.event.ChangeListener() {
	
			@Override
			public void stateChanged(ChangeEvent e) {
		
				if(subrayadoE.getState()==true) {subrayado.setState(true);}
				if(subrayadoE.getState()==false) {subrayado.setState(false);}
		
			}
	
		});

		
		negritaE.addActionListener(new StyledEditorKit.BoldAction());
		cursivaE.addActionListener(new StyledEditorKit.ItalicAction());
		subrayadoE.addActionListener(new StyledEditorKit.UnderlineAction());
		
		
		emergente.add(negritaE);
		emergente.add(cursivaE);
		emergente.add(subrayadoE);
		
		miarea.setComponentPopupMenu(emergente);
		
		//fin del menú emergente
		
		//------------Video 112 menu emergente
		/*JToolBar barra = new JToolBar(SwingConstants.HORIZONTAL); //psé -_- ya iba a ser horizontal,y es un 0
		
		//botones para fuentes
		JButton arialBarra= new JButton(new ImageIcon("../Imagenes/miniarial.png"));
		JButton courierBarra= new JButton(new ImageIcon("../Imagenes/minicourier.png"));
		JButton verdanaBarra= new JButton(new ImageIcon("../Imagenes/miniverdana.png"));
		
		//botones para colores
		JButton amarilloBarra= new JButton(new ImageIcon("../Imagenes/Amarillo.png"));
		JButton azulBarra= new JButton(new ImageIcon("../Imagenes/Azul.png"));
		JButton rojoBarra= new JButton(new ImageIcon("../Imagenes/Rojo.png"));
		
		//botones para alineación
		JButton a_izquierda= new JButton(new ImageIcon("../Imagenes/izquierda.png"));
		JButton a_centrado= new JButton(new ImageIcon("../Imagenes/centrado.png"));
		JButton a_derecha= new JButton(new ImageIcon("../Imagenes/derecha.png"));
		JButton a_justificado= new JButton(new ImageIcon("../Imagenes/justify.png"));
		
		
		//funcionalidad de los botones del menu de  herramientas
		  
		arialBarra.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Arial"));
		courierBarra.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Courier"));
		verdanaBarra.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Verdana"));
		
		amarilloBarra.addActionListener(new StyledEditorKit.ForegroundAction("", Color.YELLOW));
		azulBarra.addActionListener(new StyledEditorKit.ForegroundAction("", Color.BLUE));
		rojoBarra.addActionListener(new StyledEditorKit.ForegroundAction("", Color.RED));
		
		a_izquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda", StyleConstants.ALIGN_LEFT)); //Es el 0,la constante es un int
		a_centrado.addActionListener(new StyledEditorKit.AlignmentAction("Centrado",StyleConstants.ALIGN_CENTER)); //Valor=1
		a_derecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha",StyleConstants.ALIGN_RIGHT)); //Valor=2
		a_justificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado",StyleConstants.ALIGN_JUSTIFIED)); //Valor=3
		
		//situando los botones(irán en orden vertical de arriba hacia abajo)
		 
		barra.add(arialBarra);
		barra.add(courierBarra);
		barra.add(verdanaBarra);

		barra.add(azulBarra);
		barra.add(amarilloBarra);
		barra.add(rojoBarra);
		
		barra.add(a_izquierda);
		barra.add(a_centrado);
		barra.add(a_derecha);
		barra.add(a_justificado);
		barra.setOrientation(SwingConstans.VERTICAL); //ganas de escribir asinto,era un 1		
		
		add(barra,BorderLayout.WEST);
		*/

		//forma simplificada con un método
		
		barra= new JToolBar();	
		Cursor c= new Cursor(Cursor.HAND_CURSOR);
		barra.setCursor(c);
		
		configura_barra("../Imagenes/broad24.png","Fuente Broadway").addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Broadway"));
		configura_barra("../Imagenes/minicourier.png","Fuente Courier").addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Courier"));
		configura_barra("../Imagenes/ravie24.png","Fuente Ravie").addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Ravie"));
		
		barra.addSeparator();
		
		configura_barra("../Imagenes/Azul.png","Fuente a Azul").addActionListener(new StyledEditorKit.ForegroundAction("",Color.BLUE));
		configura_barra("../Imagenes/Amarillo.png","Fuente a Amarillo").addActionListener(new StyledEditorKit.ForegroundAction("",Color.YELLOW));
		configura_barra("../Imagenes/Rojo.png","Fuente a Rojo").addActionListener(new StyledEditorKit.ForegroundAction("",Color.RED));
		
		barra.addSeparator();
		
		configura_barra("../Imagenes/izquierda.png","Alineación a Izquierda").addActionListener(new StyledEditorKit.AlignmentAction("",StyleConstants.ALIGN_LEFT));
		configura_barra("../Imagenes/centrado.png","Alineación Centrada").addActionListener(new StyledEditorKit.AlignmentAction("",StyleConstants.ALIGN_CENTER));
		configura_barra("../Imagenes/derecha.png","Alineación a Derecha").addActionListener(new StyledEditorKit.AlignmentAction("",StyleConstants.ALIGN_RIGHT));
		configura_barra("../Imagenes/justify.png","Alineación Justificada").addActionListener(new StyledEditorKit.AlignmentAction("",StyleConstants.ALIGN_JUSTIFIED));
		
		barra.addSeparator();
		
		// boton salida aplicación
		
		Action accionSalir= new AbstractAction("Salir",new ImageIcon("../Imagenes/Salir.png")) {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}		
			
		};		
		
		barra.add(accionSalir).setToolTipText("Salir");;
		barra.setOrientation(1); //ganas de escribir asinto
		barra.setBackground(Color.RED);
		add(barra,BorderLayout.WEST);
		
	}

	public JButton configura_barra(String ruta,String texto) {
		
		JButton boton= new JButton(new ImageIcon(ruta));
		boton.setToolTipText(texto);
		barra.add(boton);
		return boton;
		//return (JButton)barra.add(new JButton(new ImageIcon(ruta)));
		
	}
	
	public void configura_menu(String texto_item,String menu,String tipo_letra,int estilos,int tam,String ruta_icono) {
	
		JMenuItem elem_menu= new JMenuItem(texto_item,new ImageIcon(ruta_icono));
		
				
		//ojo,la llamada es a la clase interna estatica  heredada  de StyledEditorKit (a su constructor) => FontSizeAction() , FontFamilyAction(),boldAction() <= son métodos 
		
		if(menu.equalsIgnoreCase("fuente")) {
			fuente.add(elem_menu);
			if(tipo_letra.equals("Broadway")) {
				elem_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
				elem_menu.setToolTipText("Fuente Broadway");
				elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Broadway"));
			} else if(tipo_letra.equals("Courier")) {
				elem_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
				elem_menu.setToolTipText("Fuente Courier");
				elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Courier"));
			}else if(tipo_letra.contentEquals("Ravie")) {
				elem_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
				elem_menu.setToolTipText("Fuente Ravie");
				elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Ravie"));
			}			
			
		}else if(menu.equalsIgnoreCase("estilo")) {
			estilo.add(elem_menu);
		
			if(estilos==Font.BOLD) {
				elem_menu.addActionListener(new StyledEditorKit.BoldAction());
			}else if(estilos==Font.ITALIC) {
				elem_menu.addActionListener(new StyledEditorKit.ItalicAction());
			}
	
		} else if(menu.equalsIgnoreCase("tamaño")) {
		
			tamaño.add(elem_menu);
			
			elem_menu.addActionListener(new StyledEditorKit.FontSizeAction("",tam));
		
		}		

	}	

	private JTextPane miarea;
	
	private JMenu fuente,estilo,tamaño;	
	
	private JMenuBar mibarra;
	
	//private JButton negritaBarra, cursivaBarra, subrayadoBarra, azulBarra, amarilloBarra, rojoBarra, a_izquierda, a_centrado, a_derecha, a_justificado;
	
	private JToolBar barra;
	}	