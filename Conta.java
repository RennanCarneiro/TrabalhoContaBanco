package prova04;

    public class Conta {
        //A classe conta é uma super classe e tem os seguintes atributos privados: (questão 4)
        private int numeroAgencia;
        private int numeroConta;
        private double saldo;
        private Cliente cliente;
    
        //Método construtor
        public Conta(int numeroAgencia, int numeroConta, double saldo, Cliente cliente) {
            this.numeroAgencia = numeroAgencia;
            this.numeroConta = numeroConta;
            this.saldo = saldo;
            this.cliente = cliente;
        }
    
        //Métodos(questão 5)
        //Getters e setters de todos os atributos
        public int getNumeroAgencia() {
            return numeroAgencia;
        }
    
        public void setNumeroAgencia(int numeroAgencia) {
            this.numeroAgencia = numeroAgencia;
        }
    
        public int getNumeroConta() {
            return numeroConta;
        }
    
        public void setNumeroConta(int numeroConta) {
            this.numeroConta = numeroConta;
        }
    
        public double getSaldo() {
            return saldo;
        }
    
        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
    
        public Cliente getCliente() {
            return cliente;
        }
    
        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }
    
        //Recebe uma quantia de dinheiro e salva no saldo da conta
        public void deposito(double quantia){
            saldo += quantia;
        }
    
        //Recebe uma quantia de dinheiro e retira do saldo da conta
        public void saque(double quantia){
            //Para realizar um saque é necessario que a conta tenha saldo suficiente para realizar ela
            if (saldo >= quantia) {
                saldo -= quantia;
                System.out.println("Saque de" + quantia + "realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para que o saque seja realizado. Tente novamente.");
            }
        }
    
        //Recebe como parâmetro um objeto do tipo conta e transfere uma quantia do saldo da conta atual para a conta recebida como parâmetro
        //Garantindo que exista saldo suficiente para isso.
        public boolean transferencia(Conta contaParametro, double quantia){
            if(saldo >= quantia) {
                 // Para realizar um saque seguro é necessario que a conta tenha saldo suficiente para realizar ela, 
                //mas caso não tivesse esse metodo de decisão, poderia ocorrer a transição mesmo não tendo saldo suficiente para ela
                saque(quantia); // Utiliza o método saque da própria conta
                contaParametro.saque(quantia);// Realiza o saque da quantia desejada e o transfere pra conta parametro
                System.out.println("Transferência de " + quantia + " para a conta " + contaParametro.getNumeroConta() + " realizada com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para que o saque seja realizado. Tente novamente.");
            }
            return false;
        }
    
        //Exibe uma mensagem informando o nome do usuario e qual seu saldo na conta
        public void exibirSaldo(){
            System.out.println("Nome do usuário: " + cliente.getNome());
            System.out.println("Saldo da conta: " + this.saldo);
        }
        
    }

