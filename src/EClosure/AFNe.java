/**
 *Clase <code>AFNe</code>.
 *Clase que modela al Automata Finito no Determinista con transiciones epsilon.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

import java.util.HashSet;
import  java.util.Stack;

public class AFNe{

	public static final String EPSILON = "EPSILON";

	private HashSet<Estado> estados;
	private HashSet<String> simbolos;
	private Estado edoInicial;

    /**
     * Constructor.
     * El AFNe siempre se inicializará con un Edo. Inicial y con EPSILON en su conjunto de simbolos.
     * ¡¡SE DEBE TOMAR EN CUENTA QUE ESTO ES UNICAMENTE PARA FINES DE LA IMPLEMENTACION, YA QUE EPSILON
     * ES UNA CADENA Y NO PUEDE PERTENECER A SIGMA!!.
     */
	public AFNe(){
		Estado inicial = new Estado("Inicial", false);
		this.estados = new HashSet<Estado>();
		this.estados.add(inicial);
		this.simbolos = new HashSet<String>();
		this.simbolos.add(AFNe.EPSILON);
		this.edoInicial = inicial;
	}

    /**
     *<code>getInicial</code> Método que devuelve el estado inicial.
     *@return tipo <code>Estado<Estado></code>: Estado inicial del automata.
     */
	public Estado getInicial(){
		return this.edoInicial;
	}

    /**
     *<code>getSimbolos</code> Método que devuelve a Sigma.
     *@return tipo <code>HashSet<String><Estado></code>: Sigma: Conjunto de simbolos que reconoce el automata.
     */
	public HashSet<String> getSimbolos(){
		return this.simbolos;
	}

    /**
     *<code>getEstados</code> Método que devuelve a Q.
     *@return tipo <code>HashSet<Estado></code>: Q: Conjunto de estados del automata.
     */
	public HashSet<Estado> getEstados(){
		return this.estados;
	}

    /**
     *<code>agregaSimbolo</code> Método que agrega un simbolo a Sigma.
     *@param s tipo <code>String</code>: Simbolo a agregar.
     */
	public void agregaSimbolo(String s) throws SymbolAlreadyExistsException{
		if (!simbolos.add(s)) {
			throw new SymbolAlreadyExistsException("El simbolo " + s + " ya se encuentra en Sigma.");
		}
	}

    /**
     *<code>agregaEstado</code> Método que agrega un estado a Q.
     *@param e tipo <code>Estado</code>: Estado a agregar.
     */
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

    /**
     *<code>modificaEstado</code> Método que modifica a un estado.
     *@param e tipo <code>Estado</code>: Estado a modificar.
     *@param nNombre tipo <code>String</code>: Nombre Nuevo.
     *@param neFinal tipo <code>boolean</code>: True si se desea que se convierta en final; False en otro caso.
     */
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

    /**
     *<code>eliminaEstado</code> Método que elimina un estado de Q. (Se eliminaran las transiciones que llegaban a el)
     *@param e tipo <code>Estado</code>: Estado a eliminar.
     */
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

    /**
     *<code>agregaTrans</code> Método que agrega una transicion d(q,a)={p}.
     *@param e tipo <code>Estado</code>: q
     *@param s tipo <code>String</code>: a.
     *@param estados tipo <code>HashSet<Estado></code>: {p}.
     */
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

    /**
     *<code>eliminaTrans</code> Método que elimina una transicion d(q,a)={p}.
     *@param e tipo <code>Estado</code>: q.
     *@param s tipo <code>String</code>: a.
     */
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

    /**
     *<code>getEstado</code> Método que busca a un estado por su nombre y lo devuelve.
     *@param nombre tipo <code>String</code>: Nombre del estado a buscar.
     *@return tipo <code>Estado</code>: Estado.
     */
	public Estado getEstado(String nombre) throws StateDoesNotExistException{
		for (Estado e:estados) {
			if (nombre.equals(e.getNombre())) {
				return e;
			}
		}
		throw new StateDoesNotExistException("El estado " + nombre + " no esta en Q.");
	}

    /**
     *<code>graphFormat</code> Método que devuelve una cadena con la representacion del automata en DOT (graph description language).
     *@return tipo <code>String<Estado></code>: Cadena con la representacion del automata en DOT (graph description language).
     */
	public String graphFormat(){
		String f = "";
		f = f + "digraph G{\n";
		for (Estado e:estados) {
			for (Transicion t:e.getTransiciones()) {
				for (Estado et:t.getEstados()) {
					f = f + e.getNombre() + " -> " + et.getNombre() + " [label=\"" + t.getSimbolo() + "\"]\n";
				}
			}
		}
		f = f + "}";
		return f;
	}

    /**
     *<code>eCerradura</code> Método que calcula la EPSILON Cerradura de todos los estados.
     *@return tipo <code>HashSet<Estado>[][]</code>: Arreglo con la EPSILON Cerradura de todos los estados.
     *La entrada [n][1] contiene la epsilon cerradura del estado [n][0].
     */
	public HashSet<Estado>[][] eCerradura(){
		Estado[] pilaQ = new Estado[estados.size()];
		int i = 0;
		for (Estado e:estados) {
			pilaQ[i] = e;
			i++;
		}
		HashSet<Estado>[][] tabla = nuevaTabla();
		i = 0;
		for (Estado pilaQPop: pilaQ) {
			Stack<Estado> pila = new Stack<Estado>();
			Estado p = pilaQPop;
			HashSet<Estado> shP = new HashSet<Estado>();
			shP.add(p);
			tabla[i][0] = shP;
			HashSet<Estado> cerradura = new HashSet<Estado>();
			cerradura.add(p);
			pila.push(p);
			while(!pila.empty()){
				p = pila.pop();
				for (Transicion t:p.getTransiciones()) {
					if (t.getSimbolo().equals(AFNe.EPSILON)) {
						HashSet<Estado> transEpsilon = t.getEstados();
						for (Estado teep:transEpsilon) {
							Estado q = teep;
							if (!cerradura.contains(q)) {
								pila.push(q);
								cerradura.add(q);
							}
						}
					}
				}
			}
			tabla[i][1] = cerradura;
			i++;
		}
		return tabla;
	}

    /**
     *<code>nuevaTabla</code> Método para crear "Arreglos Genericos".
     *@return tipo <code>HashSet<Estado>[][]</code>: Arreglo Generico.
     */
    @SuppressWarnings("unchecked") private HashSet<Estado>[][] nuevaTabla() {
		HashSet[][] tabla = new HashSet[estados.size()][2];
		return (HashSet<Estado>[][])tabla;
    }

}