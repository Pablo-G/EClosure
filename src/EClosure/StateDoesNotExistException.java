/**
 *Clase <code>StateDoesNotExistException</code>.
 *Clase para excepciones de estados faltantes.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

public class StateDoesNotExistException extends Exception {
    /**
     * Constructor vacío.
     */
    public StateDoesNotExistException() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public StateDoesNotExistException(String mensaje) {
        super(mensaje);
    }
}
