import java.util.Scanner;

public class ArvoreTrie {

    // Nó pra guardar as letras da árvore
    // Fiz como uma classe dentro da outra pra ficar mais organizado.
    private static class NodeTrie {
        private static final int TAMANHO_ALFABETO = 26;

        // Cada nó pode ter vários filhos um pra cada letra do alfabeto.
        private NodeTrie[] filhos;

        // variavel booleana pra saber se uma palavra de verdade termina aqui
        private boolean isFimDaPalavra;

        public NodeTrie() {
            this.filhos = new NodeTrie[TAMANHO_ALFABETO];
            this.isFimDaPalavra = false;
        }
    }

    // A árvore começa com uma raiz que no início não tem nada.
    private NodeTrie raiz;

    // Construtor da árvore
    public ArvoreTrie() {
        raiz = new NodeTrie();
    }

    // Método pra colocar uma palavra nova na árvore.
    public void inserir(String palavra) {
        NodeTrie noAtual = raiz;

        // laço pra percorrer a palavra letra por letra
        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            int indice = caractere - 'a';

            // Se não tiver um caminho para essa letra o programa cria um
            if (noAtual.filhos[indice] == null) {
                noAtual.filhos[indice] = new NodeTrie();
            }

            // Depois disso avança pro próximo nó
            noAtual = noAtual.filhos[indice];
        }

        // Quando a palavra do laço acaba o programa marca o último nó como fim de palavra
        noAtual.isFimDaPalavra = true;
    }

    // Busca palavra na árvore.
    public boolean buscar(String palavra) {
        NodeTrie noAtual = raiz;

        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            int indice = caractere - 'a';

            // Se a letra não existe na árvore então a palavra inteira também não
            if (noAtual.filhos[indice] == null) {
                return false;
            }
            noAtual = noAtual.filhos[indice];
        }

        return noAtual.isFimDaPalavra;
    }

    // Remove uma palavra da árvore
    public boolean remover(String palavra) {
        NodeTrie noAtual = raiz;

        for (int i = 0; i < palavra.length(); i++) {
            char caractere = palavra.charAt(i);
            int indice = caractere - 'a';

            if (noAtual.filhos[indice] == null) {
                return false;
            }
            noAtual = noAtual.filhos[indice];
        }

        if (!noAtual.isFimDaPalavra) {
            return false;
        }

        // se achar a palavra então só desmarca ela os nós continuam lá caso precisarem
        noAtual.isFimDaPalavra = false;
        return true;
    }

    // Main do programa para testes
    public static void main(String[] args) {
        // pra ler o que o usuário digita
        Scanner sc = new Scanner(System.in);
        ArvoreTrie arvore = new ArvoreTrie();
        int opcao;

        do {
            System.out.println("\nMENU ÁRVORE TRIE ");
            System.out.println("1 - Inserir palavra");
            System.out.println("2 - Buscar palavra");
            System.out.println("3 - Remover palavra");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("Digite a palavra para inserir: ");
                String palavra = sc.nextLine().toLowerCase().trim();
                arvore.inserir(palavra);
                System.out.println("-> Palavra '" + palavra + "' inserida.");
            } else if (opcao == 2) {
                System.out.print("Digite a palavra para buscar: ");
                String palavra = sc.nextLine().toLowerCase().trim();
                if (arvore.buscar(palavra)) {
                    System.out.println("-> Palavra '" + palavra + "' encontrada!");
                } else {
                    System.out.println("-> Palavra '" + palavra + "' NÃO encontrada.");
                }
            } else if (opcao == 3) {
                System.out.print("Digite a palavra para remover: ");
                String palavra = sc.nextLine().toLowerCase().trim();
                if (arvore.remover(palavra)) {
                    System.out.println("-> Palavra '" + palavra + "' removida.");
                } else {
                    System.out.println("-> Não foi possível remover, palavra não encontrada.");
                }
            }

        } while (opcao != 0);

        System.out.println("Programa encerrado.");
        sc.close();
    }
}