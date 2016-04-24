/**
 *Clase <code>AFNe</code>.
 *Clase que modela al Automata Finito no Determinista con transiciones epsilon.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

import java.util.HashSet;

public class AFNe{

	private HashSet<Estado> estados;
	private HashSet<String> simbolos;
	private Estado edoInicial;

	public AFNe(){
		Estado inicial = new Estado("Inicial", false);
		this.estados = new HashSet<Estado>();
		this.estados.add(inicial);
		this.simbolos = new HashSet<String>();
		this.edoInicial = inicial;
	}

}