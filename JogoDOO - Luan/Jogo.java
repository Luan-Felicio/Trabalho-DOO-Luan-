import java.util.Random;
import java.util.Scanner;

public class Jogo {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();
            
            //Criando os jogadores
            Jogador jogador1 = new Jogador("Patrão Filipe");
            Jogador jogador2 = new Jogador("Bot");

            //Loop para rodar o jogo11
            while (true) {
                jogador2.exibirTabuleiro(true);
                System.out.print("Informe linha que deseja atacar: ");
                int linha = scanner.nextInt();
                System.out.println("Agora informe a coluna: ");
                int coluna = scanner.nextInt();
                jogador1.atacar(jogador2, linha, coluna);
                if (jogador2.perdeu()) {
                    System.out.println("Patrão Filipe venceu!");
                    break;
                }
            }
            scanner.close();
        }
}
