package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.util.List;

public interface DAO<T> {

    T buscar(String s) throws DAOException;

    T buscarPorId(int id) throws DAOException;

    List<T> buscarTodos() throws DAOException;

    void inserir(T t) throws DAOException;

    void atualizar(T t) throws DAOException;

    void remover(T t) throws DAOException;

}
