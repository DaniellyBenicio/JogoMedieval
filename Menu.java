package Jogo;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        ArrayList<Arma> armasGuerreiro = new ArrayList<>();
        armasGuerreiro.add(new Arma("Espada", 40, 40, "Corte Profundo"));
        armasGuerreiro.add(new Arma("Machado", 40, 40, "Esmagamento Brutal"));
        

        ArrayList<Arma> armasMago = new ArrayList<>();
        armasMago.add(new Arma("Cajado de gelo", 30, 40, "Congelamento Total"));
        armasMago.add(new Arma("Cedro de fogo", 45, 45, "Queimadura Intensa"));

        ArrayList<Arma> armasNinja = new ArrayList<>();
        armasNinja.add(new Arma("Punhal", 40, 20, "Sangramento Profundo"));
        armasNinja.add(new Arma("Katana", 60, 40, "Perfuração Rápida"));

        ArrayList<Arma> armasArqueiro = new ArrayList<>();
        armasArqueiro.add(new Arma("Flecha Explosiva", 35, 40, "Chuva de Fogo"));
        armasArqueiro.add(new Arma("Besta Venenosa", 60, 30, "Envenenamento Mortal"));


        Scanner entrada = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Torneio Medieval ---");
            System.out.println("1. Deseja iniciar a Batalha?");
            System.out.println("2. Sair do jogo");
            System.out.print("Escolha uma opção: ");
            int escolha = entrada.nextInt();
            entrada.nextLine();

            switch (escolha) {
                case 1:
                    menuDePersonagens(entrada, armasGuerreiro, armasMago, armasNinja, armasArqueiro);
                    break;
                case 2:
                    System.out.println("Até logo.");
                    entrada.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void menuDePersonagens(Scanner entrada, ArrayList<Arma> armasGuerreiro, ArrayList<Arma> armasMago,
            ArrayList<Arma> armasNinja, ArrayList<Arma> armasArqueiro) {
        while (true) {
            System.out.println("\n--- Personagens Disponíveis ---");
            System.out.println("1. Guerreiro");
            System.out.println("2. Mago");
            System.out.println("3. Ninja");
            System.out.println("4. Arqueiro");
            System.out.println();

            Personagem jogador1 = escolherPersonagem(entrada, "Jogador 1", armasGuerreiro, armasMago, armasNinja, armasArqueiro);
            Personagem jogador2 = escolherPersonagem(entrada, "Jogador 2", armasGuerreiro, armasMago, armasNinja, armasArqueiro);

            System.out.println("Jogador 1 escolheu: " + jogador1.tipoPersonagem());
            System.out.println("Jogador 2 escolheu: " + jogador2.tipoPersonagem());


            Batalha batalha = new Batalha(jogador1, jogador2);
            batalha.iniciarBatalha();

            System.out.println("\nDeseja jogar novamente? (1. Sim / 2. Não): ");
            int jogarNovamente = entrada.nextInt();
            entrada.nextLine();
            if (jogarNovamente != 1) {
                System.out.println("Até logo.");
                entrada.close();
                System.exit(0);
            }
        }
    }

    public static Personagem escolherPersonagem(Scanner entrada, String jogadorNome, ArrayList<Arma> armasGuerreiro,
            ArrayList<Arma> armasMago, ArrayList<Arma> armasNinja, ArrayList<Arma> armasArqueiro) {
        while (true) {
            System.out.println(jogadorNome + ", escolha um personagem:");
            System.out.println("1. Guerreiro");
            System.out.println("2. Mago");
            System.out.println("3. Ninja");
            System.out.println("4. Arqueiro");

            int escolhaPersonagem;
            System.out.print("Escolha um personagem: ");
            escolhaPersonagem = entrada.nextInt();
            entrada.nextLine();

            switch (escolhaPersonagem) {
                case 1:
                    return new Guerreiro(jogadorNome, 100, armasGuerreiro);
                case 2:
                    return new Mago(jogadorNome, 100, armasMago);
                case 3:
                    return new Ninja(jogadorNome, 100, armasNinja);
                case 4:
                    return new Arqueiro(jogadorNome, 100, armasArqueiro);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
