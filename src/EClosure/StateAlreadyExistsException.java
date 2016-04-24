/**
 *Clase <code>StateAlreadyExistsException</code>.
 *Clase para excepciones de estados duplicados.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

public class StateAlreadyExistsException extends Exception {
    /**
     * Constructor vacío.
     */
    public StateAlreadyExistsException() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public StateAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}
