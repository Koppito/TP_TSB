
package desarrollo.tsb.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class Archivo {

    private File file;
    private TSB_OAHashtable<String, Integer> ht;
    
    public Archivo()
    {
        //ht = new TSB_OAHashtable<String, Integer>();
    }
    
    public void setFile(File file)
    {
        this.file = file;
    }
    
    public void leerArchivo()
    {
        ht.clear();
        try{
            Scanner sc = new Scanner(file);
            //limpiar el archivo de caracteres que no sean palabras
            
            while(sc.hasNext())
            {
            	//limpiar el archivo de 
                //buscar si k esta repetida para no ingreser repetido
                //buscar cantidad de repeticiones para agregar en put
                int cant = 1;
                ht.put(sc.nextLine(), cant);
            }
        }
        catch(FileNotFoundException e)
        {
            System.err.print("Error de lectura");
                    
        }
    }
    
//    public boolean buscarPalabra(String x)
//    {
//        //return ht.contains(x);
//    }
    
    public String toString()
    {
     	//No esta probado este metodo, revisar cuando imprima en textArchivo
        StringBuffer s = new StringBuffer();
        Set<Map.Entry<String, Integer>> se = ht.entrySet();
        Iterator<Map.Entry<String, Integer>> it = se.iterator();
        while(it.hasNext())
        {
            Map.Entry<String, Integer> entry = it.next();
            s.append("Par: " + entry);
        }
        
        return s.toString();
    }
  }
    

