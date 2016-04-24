/**
 *Clase <code>Transicion</code>.
 *Clase que modela una transicion de un AFN con transiciones epsilon.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

import java.util.HashSet;

public class Transicion{

	private String simbolo;
	private HashSet<Estado> estados;

	public Transicion(String simbolo, HashSet<Estado> estados){
		this.simbolo = simbolo;
		this.estados = estados;
	}

	public String getSimbolo(){
		return this.simbolo;
	}

	public HashSet<Estado> getEstados(){
		return this.estados;
	}

	@Override public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        @SuppressWarnings("unchecked") Transicion t = (Transicion)o;
        if (this.simbolo != t.getSimbolo()){
            return false;
        }
        if (!this.estados.equals(e.getEstados())){
        	return false;
        }
        return true;
    }
}