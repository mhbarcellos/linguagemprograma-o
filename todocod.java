import java.util.ArrayList;
import java.util.Scanner;

// Define the Veiculo class
class Veiculo {
    // Attributes
    private String placa;
    private String modelo;
    private String problema;
    private double valorDoServico;

    // Default constructor
    public Veiculo() {
    }

    // Constructor with initial values
    public Veiculo(String placa, String modelo, String problema, double valorDoServico) {
        this.placa = placa;
        this.modelo = modelo;
        this.problema = problema;
        this.valorDoServico = valorDoServico;
    }

    // Getters
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

    // Setters
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

    // toString method
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

// Define the VetVeiculo class
class VetVeiculo {
    // Attribute: ArrayList of Veiculo
    private ArrayList<Veiculo> veiculos;

    // Default constructor
    public VetVeiculo() {
        this.veiculos = new ArrayList<>();
    }

    // Method to get the number of registered vehicles
    public int getQuantidadeVeiculos() {
        return veiculos.size();
    }

    // Method to get a vehicle at the given position or null if the position is invalid
    public Veiculo getPos(int posicao) {
        if (posicao >= 0 && posicao < veiculos.size()) {
            return veiculos.get(posicao);
        }
        return null; // Returns null if the position is invalid
    }

    // Search for a vehicle by plate (returns the position or -1 if not found)
    public int pesquisaPorPlaca(String placa) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equalsIgnoreCase(placa)) {
                return i; // Returns the position of the vehicle
            }
        }
        return -1; // Returns -1 if not found
    }

    // Insert a vehicle into the ArrayList
    public boolean inserirVeiculo(Veiculo veiculo) {
        if (pesquisaPorPlaca(veiculo.getPlaca()) != -1) {
            return false; // Returns false if the vehicle already exists
        }
        veiculos.add(veiculo); // Adds the vehicle if it does not exist
        return true;
    }

    // Remove a vehicle from the ArrayList (removes and returns true if found, or false if not found)
    public boolean removerVeiculo(String placa) {
        int pos = pesquisaPorPlaca(placa);
        if (pos != -1) {
            veiculos.remove(pos); // Removes the vehicle from the found position
            return true; // Returns true if the vehicle was removed
        }
        return false; // Returns false if the vehicle does not exist
    }
}

// Define the Oficina class
public class Oficina {
    private static VetVeiculo vetVeiculo = new VetVeiculo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            while (!scanner.hasNextInt()) {  // Checks if the input is an integer
                System.out.println("Opcao invalida. Digite um numero.");
                scanner.next(); // Discards the invalid input
            }
            opcao = scanner.nextInt();
            scanner.nextLine(); // Clear the scanner buffer
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

    // Method to display the menu
    public static void exibirMenu() {
        System.out.println("\n--- Menu Oficina ---");
        System.out.println("1. Adicionar um novo veiculo a oficina");
        System.out.println("2. Pesquisar um veiculo pela placa");
        System.out.println("3. Remover um veiculo da oficina pela placa");
        System.out.println("4. Listar todos os veiculos");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opcao: ");
    }

    // Method to add a new vehicle
    public static void adicionarVeiculo() {
        System.out.print("Digite a placa do veiculo: ");
        String placa = scanner.nextLine();

        System.out.print("Digite o modelo do veiculo: ");
        String modelo = scanner.nextLine();

        System.out.print("Descreva o problema do veiculo: ");
        String problema = scanner.nextLine();

        System.out.print("Informe o valor do servico: ");
        while (!scanner.hasNextDouble()) {  // Checks if the input is a valid number (double)
            System.out.println("Valor invalido. Informe um numero para o valor do servico.");
            scanner.next(); // Discards the invalid input
        }
        double valorDoServico = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        Veiculo veiculo = new Veiculo(placa, modelo, problema, valorDoServico);
        if (vetVeiculo.inserirVeiculo(veiculo)) {
            System.out.println("Veiculo adicionado com sucesso!");
        } else {
            System.out.println("Erro: Veiculo ja existe.");
        }
    }

    // Method to search for a vehicle by plate
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

    // Method to remove a vehicle by plate
    public static void removerVeiculo() {
        System.out.print("Digite a placa do veiculo que deseja remover: ");
        String placa = scanner.nextLine();

        if (vetVeiculo.removerVeiculo(placa)) {
            System.out.println("Veiculo removido com sucesso.");
        } else {
            System.out.println("Veiculo nao encontrado.");
        }
    }

    // Method to list all vehicles
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
