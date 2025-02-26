class Jogador {
    private String nome;
    private Tabuleiro tabuleiro;

    public Jogador(String nome) {
        this.nome = nome;
        this.tabuleiro = new Tabuleiro();
    }

    public void exibirTabuleiro(boolean esconderNavios) {
        System.out.println("Tabuleiro de " + nome + ":");
        tabuleiro.mostrarTabuleiro(esconderNavios);
    }

    public boolean atacar(Jogador oponente, int linha, int coluna) {
        return oponente.tabuleiro.atacar(linha, coluna);
    }

    public boolean perdeu() {
        return tabuleiro.todosNaviosAfundados();
    }
}