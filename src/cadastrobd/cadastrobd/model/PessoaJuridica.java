package cadastrobd.model;

public class PessoaJuridica extends Pessoa {
    private long cnpj;

    public PessoaJuridica() {}

    public PessoaJuridica(long cnpj, int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(idPessoa, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
}
