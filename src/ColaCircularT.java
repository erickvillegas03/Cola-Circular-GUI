
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author The Survivor
 * @param <T>
 */
public class ColaCircularT<T> {
    //atributos de la clase
    Object[] datos;
    int tam;
    int P, U;
    
    public ColaCircularT(int n)
    {
        tam = n;
        datos = new Object[tam];
        P = U = -1;
    }
    
   public boolean insertar(T dato , DefaultTableModel modelo)
    {
        if( (U == tam - 1) && (P == 0) || (U+1 == P) )
            return false;
        else if(U == tam - 1)
            U = 0;
        else
            U++;
        //cuando inserte el dato en la cola
        datos[U] = dato;
        //lo debe de insertar en jtable atravez del modelo
        modelo.setValueAt(datos[U], 0, U);
        if(P == -1)
            P = 0;
        return true;
    }
   
     public T eliminar(DefaultTableModel modelo, JTextField campo)
    {
        if(P == -1)//cola vacia
            return null;
       T dato = (T)datos[P];
        //lo marcamos en el table con el -1
        modelo.setValueAt(-1, 0, P);
        if(P == U) //hay un solo elemento
        {
            datos[P] = -1;
            P = U = -1;
        }
        else
        {
            datos[P] = -1;
            P++;
        }
        campo.setText(dato.toString());
        return dato;
        
    }
    
    //utilizar estos metodos en la clase y en la aplicacion
    public boolean esta_vacia()
    {
        return (P == -1);
    }
    
    public boolean esta_llena()
    {
        int resul = ( U + 1 ) % tam;
        return resul == P;
    }
    
   public DefaultTableModel lista() {
        Vector cabecera = new Vector();
        for (int i = 0; i <= this.datos.length; i++) {
           cabecera.addElement(i);
       }
        
        DefaultTableModel modelo = new DefaultTableModel(cabecera, 0);

        try {
            FileReader fr = new FileReader("datos.txt");
            BufferedReader br = new BufferedReader(fr);
            String d;

            while ((d = br.readLine()) != null) {
                StringTokenizer dato = new StringTokenizer(d, "|");
                Vector x = new Vector();
                while (dato.hasMoreTokens()) {
                    x.addElement(dato.nextToken());
                }
                modelo.addRow(x);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }

}
