/**
 *Clase <code>Estado</code>.
 *Clase que modela un estado de un AFN con transiciones epsilon.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

import java.util.HashSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Estado{

	private String nombre;
	private HashSet<Transicion> transiciones;
	private boolean eFinal;

    /**
     * Constructor.
     */
	public Estado(String nombre, HashSet<Transicion> transiciones, boolean eFinal){
		this.nombre = nombre;
		this.transiciones = transiciones;
		this.eFinal = eFinal;
	}

    /**
     * Constructor.
     */
	public Estado(String nombre, boolean eFinal){
		this.nombre = nombre;
		this.transiciones = new HashSet<Transicion>();
		this.eFinal = eFinal;
	}

    /**
     *<code>getNombre</code> Método que devuelve el nombre el estado.
     *@return tipo <code>String<Estado></code>: Nombre del estado.
     */
	public String getNombre(){
		return this.nombre;
	}

    /**
     *<code>getTransiciones</code> Método que devuelve las transiciones de un estado.
     *@return tipo <code>HashSet<Transicion><Estado></code>: Conjunto de transiciones del estado.
     */
	public HashSet<Transicion> getTransiciones(){
		return this.transiciones;
	}

    /**
     *<code>geteFinal</code> Método nos dice si el estado es final.
     *@return tipo <code>boolean<Estado></code>: True si y solo si el estado es final.
     */
	public boolean geteFinal(){
		return this.eFinal;
	}

    /**
     *<code>setNombre</code> Método que permite cambiar de nombre al estado.
     *@param nNombre tipo <code>String</code>: Nuevo nombre para el estado.
     */
	public void setNombre(String nNombre){
		this.nombre = nNombre;
	}

    /**
     *<code>seteFinal</code> Método que permite cambiar el estado de no final a final o viceversa.
     *@param neFinal tipo <code>boolean</code>: Nuevo estado Final.
     */
	public void seteFinal(boolean neFinal){
		this.eFinal = neFinal;
	}

    /**
     *<code>agregaTrans</code> Método que dice si un objeto es igual.
     *@param t tipo <code>Transicion</code>: Transicion a agregar.
     */
	public void agregaTrans(Transicion t) throws TransitionAlreadyExistsException{
		if (!this.transiciones.add(t)) {
			throw new TransitionAlreadyExistsException("Esta transicion ya esta en Delta");
		}
	}

    /**
     *<code>equals</code> Método que dice si un objeto es igual.
     *@param o tipo <code>Object</code>: Objeto a comparar.
     *@return tipo <code>boolean<Estado></code>: True si y solo si el nombre del estado es el mismo, tiene las mismas transiciones y ambos son finales o no.
     */
	@Override public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") Estado e = (Estado)o;
        if (this.nombre != e.getNombre()){
            return false;
        }
        if (this.eFinal != e.geteFinal()){
        	return false;
        }
        if (!this.transiciones.equals(e.getTransiciones())){
        	return false;
        }
        return true;
    }

    /**
     *<code>toString</code> Representacion en cadena del objeto.
     *@return tipo <code>String<Estado></code>: El nombre del estado.
     */
    @Override public String toString(){
    	return this.nombre;
    }

}