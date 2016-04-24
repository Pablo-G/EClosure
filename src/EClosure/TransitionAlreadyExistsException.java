/**
 *Clase <code>TransitionAlreadyExistsException</code>.
 *Clase para excepciones de transiciones duplicadas.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

public class TransitionAlreadyExistsException extends Exception {
    /**
     * Constructor vacío.
     */
    public TransitionAlreadyExistsException() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public TransitionAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}
