import java.util.ArrayList;
import java.util.List;

abstract class Navio implements Atacavel {
    protected int tamanho;
    protected String nome;
    protected boolean[] acertos;
    protected List<int[]> posicoes;

    public Navio(String nome, int tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.acertos = new boolean[tamanho];
        this.posicoes = new ArrayList<>();
    }

    public boolean foiAfundado() {
        for (boolean acerto : acertos) {
            if (!acerto) return false;
        }
        return true;
    }

    public void registrarAcerto(int x, int y) {
        for (int i = 0; i < posicoes.size(); i++) {
            if (posicoes.get(i)[0] == x && posicoes.get(i)[1] == y) {
                acertos[i] = true;
                break;
            }
        }
    }

    @Override
    public boolean atacar(int linha, int coluna) {
        registrarAcerto(linha, coluna);
        return foiAfundado();
    }

    @Override
    public boolean todosNaviosAfundados() {
        return foiAfundado();
    }
}
