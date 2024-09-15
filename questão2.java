import java.util.ArrayList;

// Classe Veiculo
class Veiculo {
    private String placa;

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }
}

// Classe VetVeiculo
class VetVeiculo {
    private ArrayList<Veiculo> veiculos;

    public VetVeiculo() {
        veiculos = new ArrayList<>();
    }

    public int getQuantidade() {
        return veiculos.size();
    }

    public Veiculo getPos(int pos) {
        if (pos >= 0 && pos < veiculos.size()) {
            return veiculos.get(pos);
        } else {
            return null;
        }
    }

    public int pesquisaPorPlaca(String placa) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(placa)) {
                return i;
            }
        }
        return -1;
    }

    public boolean insereVeiculo(Veiculo veiculo) {
        if (pesquisaPorPlaca(veiculo.getPlaca()) == -1) {
            veiculos.add(veiculo);
            return true;
        }
        return false;
    }

    public boolean removeVeiculo(String placa) {
        int pos = pesquisaPorPlaca(placa);
        if (pos != -1) {
            veiculos.remove(pos);
            return true;
        }
        return false;
    }
}

// Classe Main para testar
public class Main {
    public static void main(String[] args) {
        VetVeiculo vetVeiculo = new VetVeiculo();

        // Testa insercao de veiculos
        vetVeiculo.insereVeiculo(new Veiculo("ABC1234"));
        vetVeiculo.insereVeiculo(new Veiculo("XYZ5678"));

        // Testa obtencao de veiculos
        System.out.println("Quantidade de veiculos: " + vetVeiculo.getQuantidade());
        System.out.println("Veiculo na posicao 0: " + vetVeiculo.getPos(0).getPlaca());

        // Testa remocao de veiculo
        System.out.println("Remocao do veiculo com placa XYZ5678: " + vetVeiculo.removeVeiculo("XYZ5678"));
        System.out.println("Quantidade de veiculos apos remocao: " + vetVeiculo.getQuantidade());
    }
}
