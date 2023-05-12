/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sacbeibe.facade;

import sacbeibe.beans.Pessoa;
import sacbeibe.dao.ConnectionFactory;
import sacbeibe.dao.PessoaDAO;
import sacbeibe.exception.DAOException;

/**
 *
 * @author Luis Ferraz
 */
public class PessoaFacade {

    public static void inserePessoa(Pessoa p) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PessoaDAO daoP = new PessoaDAO(factory.getConnection());
            //Busca a pessoa pelo CPF, e caso nao encontre, cadastra
            Pessoa pessoaBusca = daoP.buscar(p.getCpf());
            if (pessoaBusca == null) {
                daoP.inserir(p);
            } else {
                System.out.println("Pessoa encontrada: " + pessoaBusca.getNome() + " :: " + pessoaBusca.getCpf());
            }
        } catch (DAOException e) {
            System.err.println("Erro inserindo pessoa :: " + e.getMessage());
        }

    }

    public static Pessoa buscarPorCpf(String cpf) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PessoaDAO daoP = new PessoaDAO(factory.getConnection());
            //Busca a pessoa pelo CPF
            Pessoa pessoaBusca = daoP.buscar(cpf);
            return pessoaBusca;
        } catch (DAOException e) {
            System.err.println("Erro buscando pessoa :: " + e.getMessage());
        }
        return null;
    }

    public static void alterar(Pessoa p) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            PessoaDAO daoP = new PessoaDAO(factory.getConnection());
            daoP.atualizar(p);
        } catch (DAOException e) {
            System.err.println("Erro buscando pessoa :: " + e.getMessage());
        }
        return;
    }

}
