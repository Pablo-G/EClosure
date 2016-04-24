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
		this.simbolos.add("EPSILON");
		this.edoInicial = inicial;
	}

	public Estado getInicial(){
		return this.edoInicial;
	}

	public HashSet<String> getSimbolos(){
		return this.simbolos;
	}

	public HashSet<Estado> getEstados(){
		return this.estados;
	}

	public void agregaSimbolo(String s) throws SymbolAlreadyExistsException{
		if (!simbolos.add(s)) {
			throw new SymbolAlreadyExistsException("El simbolo " + s + " ya se encuentra en Sigma.");
		}
	}

	public void agregaEstado(Estado e) throws StateAlreadyExistsException{
		for (Estado es: estados) {
			if (e.getNombre().equals(es.getNombre())) {
				throw new StateAlreadyExistsException("Ya existe un estado llamado " + e.getNombre() + ".");
			}
		}
		if (!estados.add(e)) {
			throw new StateAlreadyExistsException("El estado " + e.getNombre() + " ya se encuentra en Q.");
		}
	}

	public void modificaEstado(Estado e, String nNombre, boolean neFinal) throws StateAlreadyExistsException{
		for (Estado es: estados) {
			if (!e.equals(es)) {			
				if (nNombre.equals(es.getNombre())) {
					throw new StateAlreadyExistsException("Ya existe un estado llamado " + nNombre + ".");
				}
			}
		}
		e.setNombre(nNombre);
		e.seteFinal(neFinal);
	}

	public void eliminaEstado(Estado e) throws StateDoesNotExistException{
		if (!estados.remove(e)) {
			throw new StateDoesNotExistException("El estado " + e.getNombre() + " no esta en Q.");
		}
		for (Estado es:estados) {
			HashSet<Transicion> trans = es.getTransiciones();
			for (Transicion t:trans) {
				HashSet<Estado> testados = t.getEstados();
				if (testados.contains(e)) {
					testados.remove(e);
					if (testados.isEmpty()) {
						trans.remove(t);
					}
				}
			}
		}
	}

	public void agregaTrans(Estado e, String s, HashSet<Estado> estados) throws Exception{
		if (this.estados.contains(e)) {
			if (simbolos.contains(s)) {
				Transicion t = new Transicion(s,estados);
				e.agregaTrans(t);
			}else{
				throw new SymbolDoesNotExistException("El simbolo " + s + " no esta en Sigma.");
			}
		}else{
			throw new StateDoesNotExistException("El estado " + e.getNombre() + " no esta en Q.");
		}
	}

	public void eliminaTrans(Estado e, String s) throws Exception{
		if (estados.contains(e)) {
			if (simbolos.contains(s)) {
				HashSet<Transicion> trans = e.getTransiciones();
				for (Transicion t: trans) {
					if (s.equals(t.getSimbolo())) {
						trans.remove(t);
					}
				}
			}else{
				throw new SymbolDoesNotExistException("El simbolo " + s + " no esta en Sigma.");
			}
		}else{
			throw new StateDoesNotExistException("El estado " + e.getNombre() + " no esta en Q.");
		}
	}

	public Estado getEstado(String nombre) throws StateDoesNotExistException{
		for (Estado e:estados) {
			if (nombre.equals(e.getNombre())) {
				return e;
			}
		}
		throw new StateDoesNotExistException("El estado " + nombre + " no esta en Q.");
	}

}