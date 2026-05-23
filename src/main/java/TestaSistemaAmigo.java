import java.util.List;

public class TestaSistemaAmigo {
    public static void main(String[] args) {

        SistemaAmigo sistema = new SistemaAmigo();

        try {
            // a) cadastrar
            sistema.cadastraAmigo("José", "jose123@gmail.com");
            sistema.cadastraAmigo("Maria", "maria123@gmail.com");

            // b) configurar sorteio
            sistema.configuraAmigoSecretoDe("jose123@gmail.com", "maria123@gmail.com");
            sistema.configuraAmigoSecretoDe("maria123@gmail.com", "jose123@gmail.com");

            // c) mensagem anônima para alguém
            sistema.enviarMensagemParaAlguem("Feliz Natal, José!!!",
                    "maria123@gmail.com",
                    "jose123@gmail.com",
                    true);

            // d) mensagem para todos
            sistema.enviarMensagemParaTodos("Feliz Natal a todos!!!",
                    "maria123@gmail.com",
                    true);

            // e) pesquisar mensagens anônimas
            List<Mensagem> anonimas = sistema.pesquisaMensagensAnonimas();

            for (Mensagem m : anonimas) {
                System.out.println(m.getTextoCompletoAExibir());
            }

            // f) verificar amigo secreto
            String amigo = sistema.pesquisaAmigoSecretoDe("jose123@gmail.com");

            if (amigo.equals("maria123@gmail.com")) {
                System.out.println("Ok");
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}