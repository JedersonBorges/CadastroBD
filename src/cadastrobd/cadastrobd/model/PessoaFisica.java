package cadastrobd.model;

public class PessoaFisica extends Pessoa {
    private long cpf;

    public PessoaFisica() {}

    public PessoaFisica(long cpf, int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(idPessoa, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }
}
