package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public void incluir(PessoaFisica pessoaFisica) {
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "INSERT INTO Pessoas (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                pessoaFisica.setIdPessoa(SequenceManager.nextId("Pessoas", "idPessoa"));
                stmt.setInt(1, pessoaFisica.getIdPessoa());
                stmt.setString(2, pessoaFisica.getNome());
                stmt.setString(3, pessoaFisica.getLogradouro());
                stmt.setString(4, pessoaFisica.getCidade());
                stmt.setString(5, pessoaFisica.getEstado());
                stmt.setString(6, pessoaFisica.getTelefone());
                stmt.setString(7, pessoaFisica.getEmail());
                stmt.executeUpdate();
            }

            sql = "INSERT INTO PessoaFisica (cpf, idPessoa) VALUES (?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setLong(1, pessoaFisica.getCpf());
                stmt.setInt(2, pessoaFisica.getIdPessoa());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(PessoaFisica pessoaFisica) {
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "UPDATE Pessoas SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE idPessoa=?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, pessoaFisica.getNome());
                stmt.setString(2, pessoaFisica.getLogradouro());
                stmt.setString(3, pessoaFisica.getCidade());
                stmt.setString(4, pessoaFisica.getEstado());
                stmt.setString(5, pessoaFisica.getTelefone());
                stmt.setString(6, pessoaFisica.getEmail());
                stmt.setInt(7, pessoaFisica.getIdPessoa());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PessoaFisica> consultarTodos() {
        List<PessoaFisica> pessoasFisicas = new ArrayList<>();
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "SELECT * FROM Pessoas p JOIN PessoaFisica pf ON p.idPessoa = pf.idPessoa";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        PessoaFisica pessoaFisica = new PessoaFisica(
                                rs.getLong("cpf"),
                                rs.getInt("idPessoa"),
                                rs.getString("nome"),
                                rs.getString("logradouro"),
                                rs.getString("cidade"),
                                rs.getString("estado"),
                                rs.getString("telefone"),
                                rs.getString("email")
                        );
                        pessoasFisicas.add(pessoaFisica);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoasFisicas;
    }

    public void excluir(PessoaFisica pessoaFisica) {
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "DELETE FROM PessoaFisica WHERE idPessoa=?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, pessoaFisica.getIdPessoa());
                stmt.executeUpdate();
            }

            sql = "DELETE FROM Pessoas WHERE idPessoa=?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, pessoaFisica.getIdPessoa());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PessoaFisica buscarPorId(int id) {

        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }
}
