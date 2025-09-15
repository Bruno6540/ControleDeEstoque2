import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ArrayList<Produto> estoque = new ArrayList<>();
        ArrayList<String> historico = new ArrayList<>();

        // Produtos iniciais
        estoque.add(new Produto("Açucar", 3.10, 0));
        estoque.add(new Produto("Arroz", 5.50, 0));
        estoque.add(new Produto("Café", 12.00, 0));
        estoque.add(new Produto("Farinha de Trigo", 4.00, 0));
        estoque.add(new Produto("Feijão", 7.80, 0));
        estoque.add(new Produto("Leite", 4.90, 0));
        estoque.add(new Produto("Macarrão", 4.20, 0));
        estoque.add(new Produto("Molho de Tomate", 2.50, 0));
        estoque.add(new Produto("Óleo", 6.70, 0));
        estoque.add(new Produto("Sal", 2.00, 0));

        System.out.println("\nBem-vindo ao estoque");
        System.out.println("----------------------------------------------");

        int escolha;
        do {
            double valorTotal = 0;
            System.out.println("\n==== Estoque Atual ====");
            for (Produto p : estoque) {
                System.out.println(p);
                valorTotal += p.getPreco() * p.getQuantidade();
            }
            System.out.println("Valor total do estoque: R$ " + String.format("%.2f", valorTotal) + ".\n");
            System.out.println("----------------------------------------------");

            System.out.println("\n*** Menu ***");
            System.out.println("1- Adicionar produto ao estoque");
            System.out.println("2- Remover unidades de um produto");
            System.out.println("3- Ver histórico de movimentação");
            System.out.println("4- Excluir um produto do estoque");
            System.out.println("5- Encerrar o programa");
            System.out.print("Escolha uma opção: ");
            escolha = leitura.nextInt();
            leitura.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("\nNome do produto: ");
                    String nome = leitura.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = leitura.nextInt();
                    leitura.nextLine();

                    boolean existe = false;
                    for (Produto p : estoque) {
                        if (p.getNome().equalsIgnoreCase(nome)) {
                            p.adicionarQuantidade(quantidade);
                            existe = true;
                            historico.add("Adicionado +" + quantidade + " ao produto " + nome);
                            break;
                        }
                    }

                    if (!existe) {
                        System.out.print("Preço do produto: ");
                        double preco = leitura.nextDouble();
                        leitura.nextLine();
                        estoque.add(new Produto(nome, preco, quantidade));
                        historico.add("Novo produto cadastrado: " + nome + " (" + quantidade + " unidades)");
                    }

                    System.out.println("\nProduto adicionado com sucesso!");
                    break;

                case 2:
                    System.out.print("\nNome do produto para remover unidades: ");
                    String removerNome = leitura.nextLine();
                    boolean encontrado = false;
                    for (Produto p : estoque) {
                        if (p.getNome().equalsIgnoreCase(removerNome)) {
                            System.out.print("Quantidade para remover: ");
                            int removerQtd = leitura.nextInt();
                            leitura.nextLine();
                            if (removerQtd > 0) {
                                p.removerQuantidade(removerQtd);
                                historico.add("Removido -" + removerQtd + " do produto " + removerNome);
                            }
                            System.out.println("Produto atualizado com sucesso!\n");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Produto não encontrado no estoque!\n");
                    }
                    break;

                case 3:
                    System.out.println("\n==== Histórico de movimentação ====");
                    if (historico.isEmpty()) {
                        System.out.println("Nenhuma movimentação registrada ainda.");
                    } else {
                        for (String registro : historico) {
                            System.out.println("- " + registro);
                        }
                    }
                    System.out.println("---------------------");
                    break;

                case 4:
                    System.out.print("\nNome do produto para excluir: ");
                    String excluirNome = leitura.nextLine();
                    boolean achou = false;
                    for (int i = 0; i < estoque.size(); i++) {
                        Produto p = estoque.get(i);
                        if (p.getNome().equalsIgnoreCase(excluirNome)) {
                            estoque.remove(i);
                            historico.add("Produto " + excluirNome + " removido do estoque completamente");
                            System.out.println("Produto excluído do estoque!\n");
                            achou = true;
                            break;
                        }
                    }
                    if (!achou) {
                        System.out.println("Produto não encontrado!\n");
                    }
                    break;

                case 5:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
            }

        } while (escolha != 5);

        leitura.close();
    }
}
