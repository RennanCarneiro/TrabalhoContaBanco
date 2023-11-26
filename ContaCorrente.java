package prova04;

public class ContaCorrente extends Conta{
    //Valor fixo de 50 reais
    private static final double taxaManutencao = 50.0; //Variável constante da classe, caso ela seja acessada, o seu valor não se altera

    public ContaCorrente(int numeroAgencia, int numeroConta, double saldo, Cliente cliente) {
        super(numeroAgencia, numeroConta, saldo, cliente);
    }

    //Método getter
    public static double getTaxamanutencao() {
        return taxaManutencao;
    }

    //Método que recebe um numero inteiro, que representa a quantidade de meses desejados para simular o valor do custo das operações da conta corrente(Questão 9)
    //Ele retorna o saldo final depois de retirado a taxa de manutenção mensal
    public double simularOperacao(int quantidadeMeses){
        double saldoAtual = getSaldo();

        for (int i = 0; i < quantidadeMeses; i++) {
            saldoAtual -= taxaManutencao;
        }

        return saldoAtual;
    }
}