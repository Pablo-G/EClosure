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

	public Estado(String nombre, HashSet<Transicion> transiciones, boolean eFinal){
		this.nombre = nombre;
		this.transiciones = transiciones;
		this.eFinal = eFinal;
	}

	public Estado(String nombre, boolean eFinal){
		this.nombre = nombre;
		this.transiciones = new HashSet<Transicion>();
		this.eFinal = eFinal;
	}

	public String getNombre(){
		return this.nombre;
	}

	public HashSet<Transicion> getTransiciones(){
		return this.transiciones;
	}

	public boolean geteFinal(){
		return this.eFinal;
	}

	public void setNombre(String nNombre){
		this.nombre = nNombre;
	}

	public void seteFinal(boolean neFinal){
		this.eFinal = neFinal;
	}

	public void agregaTrans(Transicion t) throws TransitionAlreadyExistsException{
		if (!this.transiciones.add(t)) {
			throw new TransitionAlreadyExistsException("Esta transicion ya esta en Delta");
		}
	}

	public StringProperty getTNombre(){
		return new SimpleStringProperty(nombre);
	}

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

    @Override public String toString(){
    	return this.nombre;
    }

}