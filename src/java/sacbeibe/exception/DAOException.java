/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sacbeibe.exception;

/**
 *
 * @author lgdsc
 */

public class DAOException extends RuntimeException {

    public DAOException() {
    }
    public DAOException(String string) {
        System.out.println(string);
    }

    public DAOException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

}
