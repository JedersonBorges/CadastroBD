package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    public void incluir(PessoaJuridica pessoaJuridica) {
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "INSERT INTO Pessoas (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                pessoaJuridica.setIdPessoa(SequenceManager.nextId("Pessoas", "idPessoa"));
                stmt.setInt(1, pessoaJuridica.getIdPessoa());
                stmt.setString(2, pessoaJuridica.getNome());
                stmt.setString(3, pessoaJuridica.getLogradouro());
                stmt.setString(4, pessoaJuridica.getCidade());
                stmt.setString(5, pessoaJuridica.getEstado());
                stmt.setString(6, pessoaJuridica.getTelefone());
                stmt.setString(7, pessoaJuridica.getEmail());
                stmt.executeUpdate();
            }

            sql = "INSERT INTO PessoaJuridica (cnpj, idPessoa) VALUES (?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setLong(1, pessoaJuridica.getCnpj());
                stmt.setInt(2, pessoaJuridica.getIdPessoa());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(PessoaJuridica pessoaJuridica) {
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "UPDATE Pessoas SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE idPessoa=?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, pessoaJuridica.getNome());
                stmt.setString(2, pessoaJuridica.getLogradouro());
                stmt.setString(3, pessoaJuridica.getCidade());
                stmt.setString(4, pessoaJuridica.getEstado());
                stmt.setString(5, pessoaJuridica.getTelefone());
                stmt.setString(6, pessoaJuridica.getEmail());
                stmt.setInt(7, pessoaJuridica.getIdPessoa());
                stmt.executeUpdate();
            }
        } catch (Exception
        e) {
            e.printStackTrace();
        }
    }

    public List<PessoaJuridica> consultarTodos() {
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "SELECT * FROM Pessoas p JOIN PessoaJuridica pj ON p.idPessoa = pj.idPessoa";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        PessoaJuridica pessoaJuridica = new PessoaJuridica(
                                rs.getLong("cnpj"),
                                rs.getInt("idPessoa"),
                                rs.getString("nome"),
                                rs.getString("logradouro"),
                                rs.getString("cidade"),
                                rs.getString("estado"),
                                rs.getString("telefone"),
                                rs.getString("email")
                        );
                        pessoasJuridicas.add(pessoaJuridica);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoasJuridicas;
    }

    public void excluir(PessoaJuridica pessoaJuridica) {
        try (Connection conexao = ConectorBD.getConnection()) {
            String sql = "DELETE FROM PessoaJuridica WHERE idPessoa=?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, pessoaJuridica.getIdPessoa());
                stmt.executeUpdate();
            }

            sql = "DELETE FROM Pessoas WHERE idPessoa=?";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, pessoaJuridica.getIdPessoa());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PessoaJuridica buscarPorId(int id) {
  
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }
}
