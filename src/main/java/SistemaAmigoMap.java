import java.util.*;

public class SistemaAmigoMap {

    private Map<String, Amigo> amigos;
    private List<Mensagem> mensagens;

    public SistemaAmigoMap() {
        this.amigos = new HashMap<>();
        this.mensagens = new ArrayList<>();
    }

    public void cadastraAmigo(String nome, String email) throws AmigoJaExisteException {
        if (amigos.containsKey(email)) {
            throw new AmigoJaExisteException("Amigo já existe");
        }
        amigos.put(email, new Amigo(nome, email));
    }

    public Amigo pesquisaAmigo(String email) throws AmigoInexistenteException {
        if (!amigos.containsKey(email)) {
            throw new AmigoInexistenteException("Amigo não existe");
        }
        return amigos.get(email);
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        mensagens.add(new MensagemParaTodos(texto, emailRemetente, anonima));
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean anonima) {
        mensagens.add(new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, anonima));
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return mensagens;
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> anonimas = new ArrayList<>();
        for (Mensagem m : mensagens) {
            if (m.ehAnonima()) {
                anonimas.add(m);
            }
        }
        return anonimas;
    }

    public void configuraAmigoSecretoDe(String email1, String email2)
            throws AmigoInexistenteException {

        Amigo a1 = pesquisaAmigo(email1);
        Amigo a2 = pesquisaAmigo(email2);

        a1.setAmigoSecreto(a2);
    }

    public String pesquisaAmigoSecretoDe(String email)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {

        Amigo a = pesquisaAmigo(email);

        if (a.getAmigoSecreto() == null) {
            throw new AmigoNaoSorteadoException("Amigo secreto não definido");
        }

        return a.getAmigoSecreto().getEmail();
    }
}