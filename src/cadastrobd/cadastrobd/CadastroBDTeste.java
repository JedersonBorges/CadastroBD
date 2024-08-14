package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;

import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    private static final PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Escolha uma opcao:");
            System.out.println("1. Incluir Pessoa Fisica");
            System.out.println("2. Alterar Pessoa Fisica");
            System.out.println("3. Consultar Pessoas Fisicas");
            System.out.println("4. Excluir Pessoa Fisica");
            System.out.println("5. Incluir Pessoa Juridica");
            System.out.println("6. Alterar Pessoa Juridica");
            System.out.println("7. Consultar Pessoas Juridicas");
            System.out.println("8. Excluir Pessoa Juridica");
            System.out.println("9. Buscar Pessoa");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    incluirPessoaFisica();
                    break;
                case 2:
                    alterarPessoaFisica();
                    break;
                case 3:
                    consultarPessoasFisicas();
                    break;
                case 4:
                    excluirPessoaFisica();
                    break;
                case 5:
                    incluirPessoaJuridica();
                    break;
                case 6:
                    alterarPessoaJuridica();
                    break;
                case 7:
                    consultarPessoasJuridicas();
                    break;
                case 8:
                    excluirPessoaJuridica();
                    break;
                case 9:
                    buscarPessoa();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opcao inválida. Tente novamente.");
            }
        }
    }

    private static void incluirPessoaFisica() {
        System.out.println("Digite o CPF da Pessoa Fisica:");
        long cpf = scanner.nextLong();
        scanner.nextLine(); 

        System.out.println("Digite o ID da Pessoa:");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Digite o nome da Pessoa Fisica:");
        String nome = scanner.nextLine();

        System.out.println("Digite o logradouro:");
        String logradouro = scanner.nextLine();

        System.out.println("Digite a cidade:");
        String cidade = scanner.nextLine();

        System.out.println("Digite o estado:");
        String estado = scanner.nextLine();

        System.out.println("Digite o telefone:");
        String telefone = scanner.nextLine();

        System.out.println("Digite o email:");
        String email = scanner.nextLine();

        PessoaFisica pessoaFisica = new PessoaFisica(cpf, idPessoa, nome, logradouro, cidade, estado, telefone, email);
        pessoaFisicaDAO.incluir(pessoaFisica);
        System.out.println("Pessoa Fisica incluida com sucesso.");
    }

    private static void alterarPessoaFisica() {
        System.out.println("Digite o ID da Pessoa Fisica para alterar:");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); 

        PessoaFisica pessoaFisica = buscarPessoaFisicaPorId(idPessoa);

        if (pessoaFisica != null) {
            System.out.println("Digite o novo nome da Pessoa Fisica:");
            pessoaFisica.setNome(scanner.nextLine());

            System.out.println("Digite o novo logradouro:");
            pessoaFisica.setLogradouro(scanner.nextLine());

            System.out.println("Digite a nova cidade:");
            pessoaFisica.setCidade(scanner.nextLine());

            System.out.println("Digite o novo estado:");
            pessoaFisica.setEstado(scanner.nextLine());

            System.out.println("Digite o novo telefone:");
            pessoaFisica.setTelefone(scanner.nextLine());

            System.out.println("Digite o novo email:");
            pessoaFisica.setEmail(scanner.nextLine());

            pessoaFisicaDAO.atualizar(pessoaFisica);
            System.out.println("Pessoa Fisica atualizada com sucesso.");
        } else {
            System.out.println("Pessoa Fisica nao encontrada.");
        }
    }

    private static void consultarPessoasFisicas() {
        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.consultarTodos();
        System.out.println("Pessoas Fisicas:");
        for (PessoaFisica pf : pessoasFisicas) {
            pf.exibir();
        }
    }

    private static void excluirPessoaFisica() {
        System.out.println("Digite o ID da Pessoa Fisica para excluir:");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); 

        PessoaFisica pessoaFisica = buscarPessoaFisicaPorId(idPessoa);

        if (pessoaFisica != null) {
            pessoaFisicaDAO.excluir(pessoaFisica);
            System.out.println("Pessoa Fisica excluida com sucesso.");
        } else {
            System.out.println("Pessoa Fisica nao encontrada.");
        }
    }

    private static void incluirPessoaJuridica() {
        System.out.println("Digite o CNPJ da Pessoa Juridica:");
        long cnpj = scanner.nextLong();
        scanner.nextLine(); 

        System.out.println("Digite o ID da Pessoa:");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Digite o nome da Pessoa Juridica:");
        String nome = scanner.nextLine();

        System.out.println("Digite o logradouro:");
        String logradouro = scanner.nextLine();

        System.out.println("Digite a cidade:");
        String cidade = scanner.nextLine();

        System.out.println("Digite o estado:");
        String estado = scanner.nextLine();

        System.out.println("Digite o telefone:");
        String telefone = scanner.nextLine();

        System.out.println("Digite o email:");
        String email = scanner.nextLine();

        PessoaJuridica pessoaJuridica = new PessoaJuridica(cnpj, idPessoa, nome, logradouro, cidade, estado, telefone, email);
        pessoaJuridicaDAO.incluir(pessoaJuridica);
        System.out.println("Pessoa Juridica incluida com sucesso.");
    }

    private static void alterarPessoaJuridica() {
        System.out.println("Digite o ID da Pessoa Juridica para alterar:");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); 

        PessoaJuridica pessoaJuridica = buscarPessoaJuridicaPorId(idPessoa);

        if (pessoaJuridica != null) {
            System.out.println("Digite o novo nome da Pessoa Juridica:");
            pessoaJuridica.setNome(scanner.nextLine());

            System.out.println("Digite o novo logradouro:");
            pessoaJuridica.setLogradouro(scanner.nextLine());

            System.out.println("Digite a nova cidade:");
            pessoaJuridica.setCidade(scanner.nextLine());

            System.out.println("Digite o novo estado:");
            pessoaJuridica.setEstado(scanner.nextLine());

            System.out.println("Digite o novo telefone:");
            pessoaJuridica.setTelefone(scanner.nextLine());

            System.out.println("Digite o novo email:");
            pessoaJuridica.setEmail(scanner.nextLine());

            pessoaJuridicaDAO.atualizar(pessoaJuridica);
            System.out.println("Pessoa Juridica atualizada com sucesso.");
        } else {
            System.out.println("Pessoa Juridica nao encontrada.");
        }
    }

    private static void consultarPessoasJuridicas() {
        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.consultarTodos();
        System.out.println("Pessoas Juridicas:");
        for (PessoaJuridica pj : pessoasJuridicas) {
            pj.exibir();
        }
    }

    private static void excluirPessoaJuridica() {
        System.out.println("Digite o ID da Pessoa Juridica para excluir:");
        int idPessoa = scanner.nextInt();
        scanner.nextLine(); 

        PessoaJuridica pessoaJuridica = buscarPessoaJuridicaPorId(idPessoa);

        if (pessoaJuridica != null) {
            pessoaJuridicaDAO.excluir(pessoaJuridica);
            System.out.println("Pessoa Juridica excluida com sucesso.");
        } else {
            System.out.println("Pessoa Juridica nao encontrada.");
        }
    }

    private static void buscarPessoa() {
        System.out.println("Escolha o tipo: F - Pessoa Fisica | J - Pessoa Juridica");
        String tipo = scanner.nextLine().toUpperCase();

        if (tipo.equals("F")) {
            System.out.println("Digite o ID da Pessoa Fisica para buscar:");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            buscarPessoaFisicaPorId(id);
        } else if (tipo.equals("J")) {
            System.out.println("Digite o ID da Pessoa Juridica para buscar:");
            int id = scanner.nextInt();
            scanner.nextLine(); 
            buscarPessoaJuridicaPorId(id);
        } else {
            System.out.println("Tipo de pessoa inválido.");
        }
    }

    private static PessoaFisica buscarPessoaFisicaPorId(int id) {
        PessoaFisica pessoaFisica = pessoaFisicaDAO.buscarPorId(id);
        if (pessoaFisica != null) {
            pessoaFisica.exibir();
        } else {
            System.out.println("Pessoa Fisica nao encontrada.");
        }
        return pessoaFisica;
    }

    private static PessoaJuridica buscarPessoaJuridicaPorId(int id) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.buscarPorId(id);
        if (pessoaJuridica != null) {
            pessoaJuridica.exibir();
        } else {
            System.out.println("Pessoa Juridica nao encontrada.");
        }
        return pessoaJuridica;
    }
}
