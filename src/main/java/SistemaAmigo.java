import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {
    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo(){
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        for(Amigo a : amigos){
            if(a.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("Amigo já existe");
            }
        }
        amigos.add(new Amigo(nomeAmigo, emailAmigo));
    }

    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        for (Amigo a : amigos) {
            if (a.getEmail().equals(emailAmigo)) {
                return a;
            }
        }
        throw new AmigoInexistenteException("Amigo não encontrado");
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
        mensagens.add(new MensagemParaTodos(texto, emailRemetente, ehAnonima));
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
        mensagens.add(new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima));
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> lista = new ArrayList<>();
        for(Mensagem m : mensagens){
            if(m.ehAnonima()){
                lista.add(m);
            }
        }
        return lista;
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return mensagens;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado)
            throws AmigoInexistenteException {

        Amigo a = pesquisaAmigo(emailDaPessoa);
        a.setEmailAmigoSorteado(emailAmigoSorteado);
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {

        Amigo a = pesquisaAmigo(emailDaPessoa);

        if(a.getEmailAmigoSorteado() == null){
            throw new AmigoNaoSorteadoException("Ainda não sorteado");
        }

        return a.getEmailAmigoSorteado();
    }
}