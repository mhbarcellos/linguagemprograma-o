public class Veiculo {
    // Atributos
    private String placa;
    private String modelo;
    private String problema;
    private double valorDoServico;

    // Construtor vazio
    public Veiculo() {
    }

    // Construtor recebendo valores iniciais
    public Veiculo(String placa, String modelo, String problema, double valorDoServico) {
        this.placa = placa;
        this.modelo = modelo;
        this.problema = problema;
        this.valorDoServico = valorDoServico;
    }

    // Gets
    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getProblema() {
        return problema;
    }

    public double getValorDoServico() {
        return valorDoServico;
    }

    // Sets
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public void setValorDoServico(double valorDoServico) {
        this.valorDoServico = valorDoServico;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", problema='" + problema + '\'' +
                ", valorDoServico=" + valorDoServico +
                '}';
    }

    // Metodo main
    public static void main(String[] args) {
        Veiculo veiculo = new Veiculo("ABC-1234", "Sedan", "Motor nao liga", 1500.00);
        System.out.println(veiculo);
    }
}
