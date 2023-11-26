package prova04;

public class ContaPoupanca extends Conta{
    //Valor fixo de 0.5
    private static final double taxaRendimento = 0.5; //Variável constante da classe, caso ela seja acessada, o seu valor não se altera

     // Construtor que chama o construtor da superclasse e define a taxa de rendimento
    public ContaPoupanca(int numeroAgencia, int numeroConta, double saldo, Cliente cliente) {
        super(numeroAgencia, numeroConta, saldo, cliente);
    }

    //Mêtodo getter
    public static double getTaxarendimento() {
        return taxaRendimento;
    }

     // Mêtodo que recebe como parâmetro um número inteiro, que representa a quantidade de meses para simulsr o rendimento da conta polpança(questão 7)
    //Ele retorna o rendimento do saldo atual de acordo com a taxa de rendimento(0.5)
    //O rendimento ocorre a cada mês, por isso o uso do loop neste mêtodo
    public double simularOperacao(int quantidadeMeses){
        double saldoAtual = getSaldo();
        for (int i = 0; i < quantidadeMeses; i++) {
          saldoAtual += taxaRendimento * saldoAtual;
        }
        return saldoAtual;
    }
}
