import java.util.ArrayList;
import java.util.Scanner;

public class Oficina {

    private static VetVeiculo vetVeiculo = new VetVeiculo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            while (!scanner.hasNextInt()) {  // Verifica se a entrada e um numero inteiro
                System.out.println("Opcao invalida. Digite um numero.");
                scanner.next(); // Descarta a entrada invalida
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (opcao) {
                case 1:
                    adicionarVeiculo();
                    break;
                case 2:
                    pesquisarVeiculo();
                    break;
                case 3:
                    removerVeiculo();
                    break;
                case 4:
                    listarVeiculos();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente.");
            }
        } while (opcao != 5);
    }

    // Metodo para exibir o menu
    public static void exibirMenu() {
        System.out.println("\n--- Menu Oficina ---");
        System.out.println("1. Adicionar um novo veiculo a oficina");
        System.out.println("2. Pesquisar um veiculo pela placa");
        System.out.println("3. Remover um veiculo da oficina pela placa");
        System.out.println("4. Listar todos os veiculos");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    // Metodo para adicionar um novo veiculo
    public static void adicionarVeiculo() {
        System.out.print("Digite a placa do veiculo: ");
        String placa = scanner.nextLine();

        System.out.print("Digite o modelo do veiculo: ");
        String modelo = scanner.nextLine();

        System.out.print("Descreva o problema do veiculo: ");
        String problema = scanner.nextLine();

        System.out.print("Informe o valor do servico: ");
        while (!scanner.hasNextDouble()) {  // Verifica se a entrada e um numero valido (double)
            System.out.println("Valor invalido. Informe um numero para o valor do servico.");
            scanner.next(); // Descarta a entrada invalida
        }
        double valorDoServico = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer

        Veiculo veiculo = new Veiculo(placa, modelo, problema, valorDoServico);
        if (vetVeiculo.inserirVeiculo(veiculo)) {
            System.out.println("Veiculo adicionado com sucesso!");
        } else {
            System.out.println("Erro: Veiculo ja existe.");
        }
    }

    // Metodo para pesquisar um veiculo pela placa
    public static void pesquisarVeiculo() {
        System.out.print("Digite a placa do veiculo que deseja pesquisar: ");
        String placa = scanner.nextLine();

        int posicao = vetVeiculo.pesquisaPorPlaca(placa);
        if (posicao != -1) {
            Veiculo veiculo = vetVeiculo.getPos(posicao);
            System.out.println("Informacoes do Veiculo:");
            System.out.println(veiculo);
        } else {
            System.out.println("Veiculo nao encontrado.");
        }
    }

    // Metodo para remover um veiculo pela placa
    public static void removerVeiculo() {
        System.out.print("Digite a placa do veiculo que deseja remover: ");
        String placa = scanner.nextLine();

        if (vetVeiculo.removerVeiculo(placa)) {
            System.out.println("Veiculo removido com sucesso.");
        } else {
            System.out.println("Veiculo nao encontrado.");
        }
    }

    // Metodo para listar todos os veiculos
    public static void listarVeiculos() {
        System.out.println("Veiculos cadastrados na oficina:");
        if (vetVeiculo.getQuantidadeVeiculos() > 0) {
            for (int i = 0; i < vetVeiculo.getQuantidadeVeiculos(); i++) {
                Veiculo veiculo = vetVeiculo.getPos(i);
                System.out.println("Modelo: " + veiculo.getModelo() + " | Placa: " + veiculo.getPlaca());
            }
        } else {
            System.out.println("Nenhum veiculo cadastrado.");
        }
    }
}

// Classe Veiculo
class Veiculo {
    private String placa;
    private String modelo;
    private String problema;
    private double valorDoServico;

    public Veiculo(String placa, String modelo, String problema, double valorDoServico) {
        this.placa = placa;
        this.modelo = modelo;
        this.problema = problema;
        this.valorDoServico = valorDoServico;
    }

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

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", problema='" + problema + '\'' +
                ", valorDoServico=" + valorDoServico +
                '}';
    }
}

// Classe VetVeiculo
class VetVeiculo {
    private ArrayList<Veiculo> veiculos;

    public VetVeiculo() {
        this.veiculos = new ArrayList<>();
    }

    public int getQuantidadeVeiculos() {
        return veiculos.size();
    }

    public Veiculo getPos(int posicao) {
        if (posicao >= 0 && posicao < veiculos.size()) {
            return veiculos.get(posicao);
        }
        return null;
    }

    public int pesquisaPorPlaca(String placa) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equalsIgnoreCase(placa)) {
                return i;
            }
        }
        return -1;
    }

    public boolean inserirVeiculo(Veiculo veiculo) {
        if (pesquisaPorPlaca(veiculo.getPlaca()) != -1) {
            return false;
        }
        veiculos.add(veiculo);
        return true;
    }

    public boolean removerVeiculo(String placa) {
        int pos = pesquisaPorPlaca(placa);
        if (pos != -1) {
            veiculos.remove(pos);
            return true;
        }
        return false;
    }
}
