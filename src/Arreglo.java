
public class Arreglo { 
	  int tamaño;  
	  int contador; 
	  int datos[] ;

	  public Arreglo(int t) { 
	       tamaño = t; 
	       contador = 0; 
	       datos = new int[tamaño]; 
	   } 

	  public int agregar_dato(int d) { 
	      int insertado = 0; 
	      if(contador < tamaño) { 
	          datos[contador] = d; 
	          contador ++; 
	          insertado = 1;  
	       } 
	       return insertado; 
	       }
	 

	//  public int alta_ordenada(int d)
	  //{
	    //      int i, insertado = 0;
	      //    if(contador == tamaño) ; /* Verifica si esta lleno */ 
	                 
	        //  else {
	                //  insertado = 0; /*variable que se usará para encontrar el lugar donde insertar el dato */
	          //        for(i=0; i<contador; i++){/* Busca la posición */
	            //              if(d < datos[i]){
	                             //   insertado = i;
	              //            }
	                         
	                //  }
	                   /* Inserta el dato en la posición insertar. Si es necesario
	                       recorre los datos para hacer espacio para el nuevo*/
	                  //          for(i=contador; i>insertado; i--)
	                    //               datos[i] = datos[i-1];
	                      //       datos[insertado] = d; /* Inserta el dato */
	                        
	                       //     contador++;  /* Incrementa cantidad */
	                        // }
			//return insertado;
	        
//	                 } 

	   public String desplegar_arreglo() { 
	      String salida=" ";
	      int i;
	      
	      for(i=0; i<contador;i++)
	    	  salida = salida + "  "+ datos[i];
	      return salida; 
	   } 

	 

	   public int baja(int dato)
	   {
	           int i, pos, eliminado; 
	           if(contador == 0) /* Verifica si hay datos */
	                  eliminado = 0; // no hay datos
	           else {
	                  pos = -1; /* Recorre el arreglo buscando dato */
	                  for(i=0; i<contador ;i++)
	                          if(datos[i] == dato) /* Si lo encontró */
	                               pos = i;
	                  if(pos == -1) /* No lo encontró */
	                          eliminado = -1;
	                  else {  /* Recorre los datos eliminando el que se encontraba en pos*/
	                          for(i=pos; i<contador-1; i++)
	                                  datos[i] = datos[i+1];
	                          contador--; /* Decrementa cantidad */
	                          eliminado =1;
	                  }
	           }
	           return eliminado;
	   }
	    public int  buscar  (int dato)
	    { 
	    	int i;
	    	int encontrado = -1;
	    
		    for(i=0; i< contador; i++)
		    {
		    	if (dato== datos [i])
		    	{
		    		encontrado = i;
		    	}
		    }
		    return encontrado ; 
	    }
	}
