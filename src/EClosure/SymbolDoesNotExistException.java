/**
 *Clase <code>SymbolDoesNotExistException</code>.
 *Clase para excepciones de simbolos faltantes.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

public class SymbolDoesNotExistException extends Exception {
    /**
     * Constructor vacío.
     */
    public SymbolDoesNotExistException() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public SymbolDoesNotExistException(String mensaje) {
        super(mensaje);
    }
}
