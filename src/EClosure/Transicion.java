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

    /**
     * Constructor.
     */
	public Transicion(String simbolo, HashSet<Estado> estados){
		this.simbolo = simbolo;
		this.estados = estados;
	}

    /**
     *<code>getSimbolo</code> Método que regresa el simbolo de la transicion.
     *@return tipo <code>String</code>: Simbolo con el que se realiza la transicion.
     */
	public String getSimbolo(){
		return this.simbolo;
	}

    /**
     *<code>getEstados</code> Método que regresa el conjunto de estados al que se hace la transicion.
     *@return tipo <code>HashSet<Estado></code>: Conjunto de estados al que se hace la transicion.
     */
	public HashSet<Estado> getEstados(){
		return this.estados;
	}

    /**
     *<code>equals</code> Método que dice si un objeto es igual.
     *@param o tipo <code>Object</code>: Objeto a comparar.
     *@return tipo <code>boolean<Estado></code>: True si y solo si el simbolo y los estados de la transicion son los mismos.
     */
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
        if (!this.estados.equals(t.getEstados())){
        	return false;
        }
        return true;
    }
}