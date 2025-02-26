import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Tabuleiro implements Atacavel{
    private static final int TAMANHO = 10;
    private char[][] grid;
    private List<Navio> navios;

    public Tabuleiro() {
        grid = new char[TAMANHO][TAMANHO];
        navios = new ArrayList<>();
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                grid[i][j] = '~'; // Água
            }
        }
        posicionarNavios();
    }

    private void posicionarNavios() {
        Random random = new Random();
        Navio[] tiposNavios = { new PortaAvioes(), new Destroyer(), new Submarino() };

        for (Navio navio : tiposNavios) {
            boolean posicionado = false;
            while (!posicionado) {
                int linha = random.nextInt(TAMANHO);
                int coluna = random.nextInt(TAMANHO);
                boolean horizontal = random.nextBoolean();
                
                if (podePosicionar(navio, linha, coluna, horizontal)) {
                    for (int i = 0; i < navio.tamanho; i++) {
                        int x = horizontal ? linha : linha + i;
                        int y = horizontal ? coluna + i : coluna;
                        grid[x][y] = 'N';
                        navio.posicoes.add(new int[]{x, y});
                    }
                    navios.add(navio);
                    posicionado = true;
                }
            }
        }
    }

    private boolean podePosicionar(Navio navio, int linha, int coluna, boolean horizontal) {
        if (horizontal && coluna + navio.tamanho > TAMANHO) return false;
        if (!horizontal && linha + navio.tamanho > TAMANHO) return false;

        for (int i = 0; i < navio.tamanho; i++) {
            int x = horizontal ? linha : linha + i;
            int y = horizontal ? coluna + i : coluna;
            if (grid[x][y] != '~') return false;
        }
        return true;
    }

    //Apresenta o tabuleiro do jogador
    public void mostrarTabuleiro(boolean esconderNavios) {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (esconderNavios && grid[i][j] == 'N') {
                    System.out.print("~ ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean atacar(int linha, int coluna) {
        if (linha < 0 || linha >= TAMANHO || coluna < 0 || coluna >= TAMANHO) {
            System.out.println("Coordenadas inválidas. Tente novamente.");
            return false;
        }
        if (grid[linha][coluna] == 'N') {
            grid[linha][coluna] = 'X';
            for (Navio navio : navios) {
                navio.registrarAcerto(linha, coluna);
                if (navio.foiAfundado()) {
                    System.out.println("Você afundou um " + navio.nome + "!");
                }
            }
            return true;
        } else if (grid[linha][coluna] == '~') {
            grid[linha][coluna] = 'O';
            System.out.println("Errou!");
            return false;
        } else {
            System.out.println("Já atacou essa posição antes. Tente outra.");
            return false;
        }
    }

    public boolean todosNaviosAfundados() {
        for (Navio navio : navios) {
            if (!navio.foiAfundado()) {
                return false;
            }
        }
        return true;
    }
}