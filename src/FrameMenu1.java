import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *   class Frame Menu
 *   Este frame incluye un MenuBar , dos objetos meun de archivo y editar,
 *   y ocho MenuItem objects. Cuando un elemento del menu es seleccionado,
 *   un string muestra la opcion la cual es seleccionada .
 *
 *  
 *
 * @author Sean Burger
 */

class FrameMenu1 extends JFrame implements ActionListener  
{

//----------------------------------
//    Cualidades del frame
//----------------------------------

    /**
     * Ancho por default del frame
     */
     int Frame_Width    = 500;

    /**
     * Altura default del frame
     */
     int Frame_Height   = 250;

    /**
     * coordenada en el eje de las x del frame
     */
     int Frame_X_Origin = 150;

    /**
     * coordenada en el eje de las y del frame
     */
     int FRAME_Y_ORIGIN = 250;

    /**
     * Al seleccionar se muestra texto como respuesta
     */
     private static String fileSeparator  = System.getProperty("file.separator");
     

    /**
     * Grupo de Archivo
     */
    private JMenu    AccionesMenu;
    private JTextArea  textArea;
    File           inFile, outFile;
    FileReader     fr;
    BufferedReader bufReader;
    JFileChooser chooser;
    FileOutputStream  outFileStream;
    PrintWriter       outStream;

   public Arreglo miArreglo;

//----------------------------------
//      metodo Main
//----------------------------------
    public static void main(String[] args) {
        FrameMenu1 frame = new FrameMenu1();
        frame.setVisible(true);
    }

//----------------------------------
//    Constructores
//----------------------------------

    /**
     * constructor default
     */
    public FrameMenu1()
    {
        Container contentPane; 
    	String  entrada ;
    	int tam;

        //usamos para ponerle las propiedades al frame.
        setTitle     ("FrameMenu1");
        setSize      (Frame_Width, Frame_Height);
        setResizable (false);
        setLocation  (Frame_X_Origin, FRAME_Y_ORIGIN);

// Nuevamente usamos contentPane para ponerle color al frame asi como su layout.
        contentPane = getContentPane( );
        contentPane.setLayout(null);
        contentPane.setBackground( Color.green ); 
        // creamos 2 menus con sus items menu
        //create two menus and their menu items
        crearAccionesMenu();
        
  
        //Los añadimos al menubar
        JMenuBar menuBar = new JMenuBar();        setJMenuBar(menuBar);  
        menuBar.add(AccionesMenu);
        
        //creamos , posicionamos y agregamos el JTEXTAREA.
        //
        textArea = new JTextArea();
        textArea.setBounds(5, 5, Frame_Width-15, Frame_Height-65);
        textArea.setBorder(BorderFactory.createLineBorder(Color.red));
        textArea.setEditable(false);
        JScrollPane scrollText= new JScrollPane(textArea);
        scrollText.setBounds (5, 5, Frame_Width-15, Frame_Height-65);
        scrollText.setBorder(BorderFactory.createLineBorder(Color.red));
        contentPane.add(scrollText);
     
        
       

        
// Con el siguiente metodo  va a salir del programa cuando se cierre el frame.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

		entrada=JOptionPane.showInputDialog(null,"De qué tamaño va a ser el arreglo?");
		tam=Integer.parseInt(entrada);
		miArreglo = new Arreglo(tam);
    }


//-------------------------------------------------
//      Public Methods:
//
//          void    actionPerformed   (   ActionEvent        )
//
//------------------------------------------------

    /**
     * Standard method to respond the action event.
     *
     * @param event the ActionEvent object
     *
     */
    public void actionPerformed(ActionEvent event) {
        String  menuName, entrada;
        int d,pos;
        
        menuName = event.getActionCommand();

        if (menuName.equals("Salir")) {
           System.exit(0);
        } else { 
        		if (menuName.equals("Guardar")) {
        			saveFile();
        		}else {
		        	if (menuName.equals("Agregar dato")) {
		        		entrada=JOptionPane.showInputDialog(null,"Dato que vas a agregar:");
		        		d=Integer.parseInt(entrada);
		        		if(miArreglo.agregar_dato(d)==0)
		        			JOptionPane.showMessageDialog(null,"El dato no se pudo insertar, el arreglo esta lleno.");
		        		textArea.setText(miArreglo.desplegar_arreglo());
		             } else {
		            	 if (menuName.equals("Eliminar dato")) {
		            		 	entrada=JOptionPane.showInputDialog(null,"Dato que vas a eliminar:");
		             			d=Integer.parseInt(entrada);
		             			pos = miArreglo.baja(d);  
		               		if(pos==0)
		            			JOptionPane.showMessageDialog(null,"El arreglo esta vacío.");
		               		else if(pos==-1)
		               			JOptionPane.showMessageDialog(null,"El dato no se encuentra en el arreglo.");
		               		else textArea.setText(miArreglo.desplegar_arreglo());
		                  } else {//
		                	  if (menuName.equals("Buscar")) {
		                		  entrada=JOptionPane.showInputDialog(null,"Dato que vas a buscar:");
		                   			d=Integer.parseInt(entrada); 
		                   			pos = miArreglo.buscar(d);
		                   			
			                   		if(pos == -1)
			                   		{
			                   			textArea.append("\n El dato que buscas no se encuentra.");
			                   		}
			                   		else{
			                   			textArea.append("\n El dato "+d+" se encuentra en la posición "+pos);                   			
		                   		}
		                   		
		                       } else {
		                    	   if(menuName.equals("Leer del archivo"))
		                    	   {
		                    		   openFile();
		                    	   }
		                    	   else// Desplegar
		                    	   textArea.setText(miArreglo.desplegar_arreglo());
		                       }
		                  }
		             }
        		}
        }  
    }

//-------------------------------------------------
//      Metodos Privados
//
//          void   crearAccionesMenu   (           )
//          
//
//------------------------------------------------

    /**
     * Creamos el menu file y le añadimos sus items
     *
     */ 
    private void crearAccionesMenu( ) {
        JMenuItem    item; 
        JMenu       fileMenu = new JMenu();
        JMenuBar    menuBar  = new JMenuBar();

        AccionesMenu = new JMenu("Acciones");
        
        item = new JMenuItem("Leer del archivo");    //Open...
        item.addActionListener( this );
        AccionesMenu.add( item );

        item = new JMenuItem("Guardar");    //Save...
        item.addActionListener( this );
        AccionesMenu.add( item );

          

        item = new JMenuItem("Agregar dato");        //Agregar dato
        item.addActionListener( this );
        AccionesMenu.add( item );

        item = new JMenuItem("Eliminar dato");    //Eliminar dato ...
        item.addActionListener( this );
        AccionesMenu.add( item );

        item = new JMenuItem("Desplegar");       //Desplegar 
        item.addActionListener( this );
        AccionesMenu.add( item );
        
        item = new JMenuItem("Buscar");
        item.addActionListener( this );
        AccionesMenu.add( item );

        AccionesMenu.addSeparator();           //añadimos una barra horizontal separadora

        item = new JMenuItem("Salir");       //Salir
        item.addActionListener( this );
        AccionesMenu.add( item );
       
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
    }
    /**
     * Let the end user select the Java source file
     * to open.
     */
    private void openFile( ) {
    	int i,reply;
    	String line, filename, doc;
    	
    	doc = "";
    	
        try {
        	 chooser = new JFileChooser();
             reply = chooser.showOpenDialog(null);
             
            
			doc = chooser.getCurrentDirectory().getPath() + fileSeparator + chooser.getSelectedFile().getName();
             
             inFile     = new File(doc);
             fr = new FileReader(inFile);
             bufReader  = new BufferedReader(fr);
             
             textArea.setText("");
         
            while (true) {
                line = bufReader.readLine();
               if (line == null) {
                    return;
                }
                if(miArreglo.agregar_dato(Integer.parseInt(line))==1)
                {
                	textArea.append("  "+line);
                }
                else return;
            }
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in opening a file: \n"
                                            + e.getMessage());
      }
        finally{
        for(i=0;i<miArreglo.tamaño;i++)
        	textArea.append("\nEl dato en posición "+i+" es "+miArreglo.datos[i]);
        }
    }
     
    public void saveFile() {
		int reply, i;
		String line, doc; 
		
		doc = "";
		
	    	 chooser = new JFileChooser();
	         reply = chooser.showSaveDialog(null);
	         
	         doc = chooser.getCurrentDirectory().getPath() + fileSeparator
	                 + chooser.getSelectedFile().getName();
	    try {
	         
	         outFile       = new File(doc);
	         outFileStream = new FileOutputStream(outFile);
	         outStream     = new PrintWriter(outFileStream);
	
	        	for(i=0; i<miArreglo.contador;i++)
	        		outStream.println(miArreglo.datos[i]);
	        
	        
	        
		} catch (IOException e) {
		        JOptionPane.showMessageDialog(null, "Error in opening a file: \n"
		                                        + e.getMessage());
		}
	    finally
	    {
	    	outStream.close();
	    }
	    
    }
   
    }


