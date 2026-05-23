import java.util.Scanner;

public class TestaSistemaAmigoGUI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaAmigo sistema = new SistemaAmigo();

        try {
            // b) quantidade de amigos
            System.out.print("Quantos amigos participarão? ");
            int qtd = Integer.parseInt(scanner.nextLine());

            // c) cadastrar amigos
            for (int i = 0; i < qtd; i++) {
                System.out.println("\nAmigo " + (i + 1));

                System.out.print("Nome: ");
                String nome = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                sistema.cadastraAmigo(nome, email);
            }

            // d) configurar amigo secreto
            System.out.println("\n--- Cadastro do sorteio ---");
            for (int i = 0; i < qtd; i++) {
                System.out.print("Email de quem tirou: ");
                String email1 = scanner.nextLine();

                System.out.print("Email do amigo secreto: ");
                String email2 = scanner.nextLine();

                sistema.configuraAmigoSecretoDe(email1, email2);
            }

            // e) enviar mensagem para todos
            System.out.println("\n--- Enviar mensagem para todos ---");

            System.out.print("Email do remetente: ");
            String remetente = scanner.nextLine();

            System.out.print("Texto da mensagem: ");
            String texto = scanner.nextLine();

            System.out.print("Mensagem anônima? (true/false): ");
            boolean anonima = Boolean.parseBoolean(scanner.nextLine());

            sistema.enviarMensagemParaTodos(texto, remetente, anonima);

            System.out.println("\nMensagem enviada com sucesso!");

        } catch (AmigoJaExisteException e) {
            System.out.println("Erro: amigo já cadastrado.");
        } catch (AmigoInexistenteException e) {
            System.out.println("Erro: amigo não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        scanner.close();
    }
}