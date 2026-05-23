public class Amigo {
    private String nome;
    private String email;
    private String emailAmigoSorteado;
    private Amigo amigoSecreto;

    public Amigo(String nomeAmigo, String emailAmigo){
        this.nome = nomeAmigo;
        this.email = emailAmigo;
        this.amigoSecreto = null;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailAmigoSorteado() {
        return emailAmigoSorteado;
    }

    public void setEmailAmigoSorteado(String emailAmigoSorteado) {
        this.emailAmigoSorteado = emailAmigoSorteado;
    }

    public void setAmigoSecreto(Amigo amigo) {
        this.amigoSecreto = amigo;
    }

    public Amigo getAmigoSecreto() {
        return amigoSecreto;
    }
}