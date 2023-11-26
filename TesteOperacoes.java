package prova04;

import java.util.ArrayList;
import java.util.Scanner;

public class TesteOperacoes {
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Conta> listaContas;
    private Scanner sc;

    public TesteOperacoes() {
        listaClientes = new ArrayList<>();
        listaContas = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    // Método para criar uma nova conta
    public void criarConta() {
        // Solicita informações do cliente
        System.out.println("Informe o nome do cliente: ");
        String nome = sc.nextLine();
        System.out.println("Informe o endereço do cliente: ");
        String endereco = sc.nextLine();
        System.out.println("Informe a profissão do cliente: ");
        String profissao = sc.nextLine();

        // Cria um novo cliente e o adiciona à lista de clientes
        Cliente cliente = new Cliente(nome, endereco, profissao);
        listaClientes.add(cliente);

        // Solicita informações da conta a ser criada
        System.out.println("Informe o tipo de conta (poupança ou corrente): ");
        String tipoConta = sc.nextLine();

        try {
            // Solicita número da agência, número da conta e saldo inicial
            System.out.println("Informe o número da agência: ");
            int numeroAgencia = Integer.parseInt(sc.nextLine());
            System.out.println("Informe o número da conta: ");
            int numeroConta = Integer.parseInt(sc.nextLine());
            System.out.println("Informe o saldo inicial: ");
            double saldo = Double.parseDouble(sc.nextLine());

            // Verifica se os valores são válidos e cria a nova conta
            if (numeroAgencia <= 0 || numeroConta <= 0 || saldo < 0) {
                throw new IllegalArgumentException("Número de agência/conta inválido ou saldo menor que zero.");
            }

            Conta novaConta;
            if (tipoConta.equalsIgnoreCase("poupança")) {
                novaConta = new ContaPoupanca(numeroAgencia, numeroConta, saldo, cliente);
            } else if (tipoConta.equalsIgnoreCase("corrente")) {
                novaConta = new ContaCorrente(numeroAgencia, numeroConta, saldo, cliente);
            } else {
                throw new IllegalArgumentException("Tipo de conta inválido.");
            }

            // Adiciona a nova conta à lista de contas
            listaContas.add(novaConta);
        } catch ( IllegalArgumentException e) {
            System.out.println("Erro ao criar a conta: " + e.getMessage());
        }
    }

    // Método para realizar operações entre contas
    public void realizarOperacoes() {
        // Solicita os números das contas envolvidas na operação
        System.out.println("Informe o número da agência e conta do cliente que deseja enviar dinheiro: ");
        int agenciaEnvio = Integer.parseInt(sc.nextLine());
        int contaEnvio = Integer.parseInt(sc.nextLine());

        System.out.println("Informe o número da agência e conta do cliente que receberá o dinheiro: ");
        int agenciaRecebimento = Integer.parseInt(sc.nextLine());
        int contaRecebimento = Integer.parseInt(sc.nextLine());

        Conta contaEnviar = null;
        Conta contaReceber = null;

        // Procura pelas contas nas listas
        for (Conta conta : listaContas) {
            if (conta.getNumeroAgencia() == agenciaEnvio && conta.getNumeroConta() == contaEnvio) {
                contaEnviar = conta;
            }
            if (conta.getNumeroAgencia() == agenciaRecebimento && conta.getNumeroConta() == contaRecebimento) {
                contaReceber = conta;
            }
        }

        // Se as contas existirem, realiza a transferência entre elas
        if (contaEnviar != null && contaReceber != null) {
            System.out.println("Informe o valor a ser transferido: ");
            double valorTransferencia = Double.parseDouble(sc.nextLine());
            contaEnviar.transferencia(contaReceber, valorTransferencia);
        } else {
            System.out.println("Conta de envio ou recebimento não encontrada.");
        }
    }

    // Método para exibir saldo
    public void exibirSaldo() {
        // Solicita os números da agência e conta para verificar o saldo
        System.out.println("Informe o número da agência e conta para verificar o saldo: ");
        int agencia = Integer.parseInt(sc.nextLine());
        int conta = Integer.parseInt(sc.nextLine());

        // Procura pela conta na lista
        for (Conta c : listaContas) {
            if (c.getNumeroAgencia() == agencia && c.getNumeroConta() == conta) {
                System.out.println("Informe a quantidade de meses para simular o saldo: ");
                int meses = Integer.parseInt(sc.nextLine());
                if (c instanceof ContaPoupanca) {
                    double saldoSimulado = ((ContaPoupanca) c).simularOperacao(meses);
                    System.out.println("Saldo após " + meses + " meses: " + saldoSimulado);
                } else if (c instanceof ContaCorrente) {
                    double saldoSimulado = ((ContaCorrente) c).simularOperacao(meses);
                    System.out.println("Saldo após " + meses + " meses: " + saldoSimulado);
                }
                return;
            }
        }
        System.out.println("Conta não encontrada.");
    }

    // Método para apresentar o menu de opções ao usuário
    public void apresentarMenu() {
        boolean continuar = true;
        while (continuar) {
            // Mostra as opções disponíveis
            System.out.println("Escolha uma opção:");
            System.out.println("1. Criar conta");
            System.out.println("2. Realizar operações");
            System.out.println("3. Exibir saldo");
            System.out.println("4. Sair");

            int opcao = Integer.parseInt(sc.nextLine());

            // Executa a ação correspondente à opção escolhida
            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    realizarOperacoes();
                    break;
                case 3:
                    exibirSaldo();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    // Método principal para iniciar o programa
    public static void main(String[] args) {
        TesteOperacoes teste = new TesteOperacoes();
        teste.apresentarMenu();
    }
}
