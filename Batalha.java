package Jogo;

import java.util.Scanner;

public class Batalha {
    private Personagem jogador1;
    private Personagem jogador2;

    public Batalha(Personagem jogador1, Personagem jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        System.out.println();
    }

    public Personagem getJogador1() {
        return jogador1;
    }

    public void setJogador1(Personagem jogador1) {
        this.jogador1 = jogador1;
    }

    public Personagem getJogador2() {
        return jogador2;
    }

    public void setJogador2(Personagem jogador2) {
        this.jogador2 = jogador2;
    }

public int escolherArma(Personagem jogador) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Escolha uma arma para " + jogador.tipoPersonagem() + ":");
        
        for (int i = 0; i < jogador.getArmas().size(); i++) {
            System.out.println((i + 1) + ". " + jogador.getArmas().get(i).getNome());
        }
        System.out.println();
    
        int escolha = ler.nextInt();
        
        while (escolha < 1 || escolha > jogador.getArmas().size()) {
            System.out.println("Escolha inválida. Por favor, escolha uma arma:");
            escolha = ler.nextInt();
        }
    
        Arma armaEscolhida = jogador.getArmas().get(escolha - 1);
        System.out.println("O " + jogador.tipoPersonagem() + " escolheu a arma " + armaEscolhida.getNome()); 
        armaEscolhida.exibirDetalhesArma();
        return escolha - 1;
    }
    
    public void realizarTurno(Personagem atacante, Personagem defensor) {
        System.out.println("\nTurno de " + atacante.tipoPersonagem());
        
        int indiceArmaAtacante = escolherArma(atacante);
        int indiceArmaDefensor = escolherArma(defensor);
        Arma armaAtacante = atacante.getArmas().get(indiceArmaAtacante);
        Arma armaDefensor = defensor.getArmas().get(indiceArmaDefensor);
    
        double chanceAcertoAtacante = (armaAtacante.getPrecisao() * 0.5) + (armaAtacante.getForca() * 0.5);
        double chanceAcertoDefensor = (armaDefensor.getPrecisao() * 0.5) + (armaDefensor.getForca() * 0.5);
    
        if (chanceAcertoAtacante > chanceAcertoDefensor) {
            System.out.println();
            System.out.println("O ataque com " + armaAtacante.getNome() + " é bem-sucedido! " + atacante.tipoPersonagem() + " ganha a jogada.");
    
            if (armaAtacante.getEfeitoEspecial() != null && !armaAtacante.getEfeitoEspecial().isEmpty()) {
                System.out.println(defensor.tipoPersonagem() + " foi acertado com o poder '" + armaAtacante.getEfeitoEspecial() + "'.");
            }
    
            defensor.receberDano(armaAtacante.getForca() + armaAtacante.getPrecisao());
    
            if (defensor.getVida() <= 0) {
                System.out.println(defensor.tipoPersonagem() + " foi derrotado!");
                exibirResultado();
                System.exit(0);
            }
    
            System.out.println();
        } else if (chanceAcertoAtacante < chanceAcertoDefensor) {
            System.out.println("O ataque com " + armaDefensor.getNome() + " é bem-sucedido! " + defensor.tipoPersonagem() + " ganha a jogada.");
    
            if (armaDefensor.getEfeitoEspecial() != null && !armaDefensor.getEfeitoEspecial().isEmpty()) {
                System.out.println(defensor.tipoPersonagem() + " acertou com o poder '" + armaDefensor.getEfeitoEspecial() + "' o " + atacante.tipoPersonagem() + ".");
            }
    
            atacante.receberDano(armaDefensor.getForca() + armaDefensor.getPrecisao());
    
            if (atacante.getVida() <= 0) {
                System.out.println(atacante.tipoPersonagem() + " foi derrotado!");
                exibirResultado();
                System.exit(0);
            }
        } else {
            System.out.println("Ambos os ataques falharam!");
        }
    
        System.out.println("Vida restante de " + atacante.tipoPersonagem() + ": " + atacante.getVida());
        System.out.println("Vida restante de " + defensor.tipoPersonagem() + ": " + defensor.getVida());
    }

    public void iniciarBatalha() {
        System.out.println();
        System.out.println("Batalha iniciada: " + jogador1.tipoPersonagem() + " vs " + jogador2.tipoPersonagem());
        
        while (jogador1.getVida() > 0 && jogador2.getVida() > 0) {
            realizarTurno(jogador1, jogador2);
            if (jogador2.getVida() <= 0) {
                System.out.println(jogador1.tipoPersonagem()  + " é o vencedor!");
                break;
            }

            realizarTurno(jogador2, jogador1);
            if (jogador1.getVida() <= 0) {
                System.out.println(jogador2.tipoPersonagem() + " é o vencedor!");
                break;
            }
        }

        exibirResultado();
    }  

    public void exibirResultado() {
        System.out.println("\n--- Resultado da Batalha ---");
    
        if (jogador1.getVida() <= 0) {
            System.out.println("Vida restante de " + jogador1.tipoPersonagem() + ": 0");
            System.out.println("Vida restante de " + jogador2.tipoPersonagem() + ": " + jogador2.getVida());
            System.out.println(jogador2.tipoPersonagem() + " é o grande vencedor do Torneio Medieval!");
        } else if (jogador2.getVida() <= 0) {
            System.out.println("Vida restante de " + jogador1.tipoPersonagem() + ": " + jogador1.getVida());
            System.out.println("Vida restante de " + jogador2.tipoPersonagem() + ": 0");
            System.out.println(jogador1.tipoPersonagem() + " é o grande vencedor do Torneio Medieval!");
        } else {
            System.out.println("Ambos os jogadores têm vida restante.");
            System.out.println("Vida restante de " + jogador1.tipoPersonagem() + ": " + jogador1.getVida());
            System.out.println("Vida restante de " + jogador2.tipoPersonagem() + ": " + jogador2.getVida());
        }
    }
    
}
    


