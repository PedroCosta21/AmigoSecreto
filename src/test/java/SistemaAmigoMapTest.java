import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaAmigoMapTest {

    SistemaAmigoMap sistema;

    @BeforeEach
    void setUp() {
        this.sistema = new SistemaAmigoMap();
    }

    @Test
    void testSistemaAmigo() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        assertThrows(AmigoInexistenteException.class,
                () -> sistema.pesquisaAmigo("ayla@teste.com"));
    }

    @Test
    void testPesquisaECadastraAmigo() {
        try {
            sistema.pesquisaAmigo("ayla@teste.com");
            fail("Deveria falhar pois não existe ainda");
        } catch (AmigoInexistenteException e) {
            // ok
        }

        try {
            sistema.cadastraAmigo("ayla", "ayla@teste.com");
            Amigo a = sistema.pesquisaAmigo("ayla@teste.com");
            assertEquals("ayla", a.getNome());
            assertEquals("ayla@teste.com", a.getEmail());
        } catch (Exception e) {
            fail("Não deveria lançar exceção");
        }
    }

    @Test
    void testEnviarMensagemParaTodos() {
        assertTrue(sistema.pesquisaTodasAsMensagens().isEmpty());
        sistema.enviarMensagemParaTodos("texto", "ayla@dcx.ufpb.br", true);
        List<Mensagem> msgs = sistema.pesquisaTodasAsMensagens();
        assertEquals(1, msgs.size());
    }

    @Test
    void testEnviarMensagemParaAlguem() {
        sistema.enviarMensagemParaAlguem("texto", "a@a.com", "b@b.com", true);
        List<Mensagem> msgs = sistema.pesquisaTodasAsMensagens();
        assertEquals(1, msgs.size());
        assertTrue(msgs.get(0) instanceof MensagemParaAlguem);
    }

    @Test
    void testPesquisaMensagensAnonimas() {
        sistema.enviarMensagemParaAlguem("t1", "a", "b", false);
        assertTrue(sistema.pesquisaMensagensAnonimas().isEmpty());

        sistema.enviarMensagemParaAlguem("t2", "a", "b", true);
        assertEquals(1, sistema.pesquisaMensagensAnonimas().size());
    }

    @Test
    void testPesquisaTodasAsMensagens() {
        sistema.enviarMensagemParaAlguem("t1", "a", "b", false);
        sistema.enviarMensagemParaAlguem("t2", "a", "b", true);
        assertEquals(2, sistema.pesquisaTodasAsMensagens().size());
    }

    @Test
    void testPesquisaAmigoEConfiguraAmigoSecretoDe() {
        assertThrows(AmigoInexistenteException.class,
                () -> sistema.pesquisaAmigoSecretoDe("a@a.com"));

        try {
            sistema.cadastraAmigo("A", "a@a.com");
            sistema.cadastraAmigo("B", "b@b.com");

            sistema.configuraAmigoSecretoDe("a@a.com", "b@b.com");
            sistema.configuraAmigoSecretoDe("b@b.com", "a@a.com");

            assertEquals("b@b.com", sistema.pesquisaAmigoSecretoDe("a@a.com"));
        } catch (Exception e) {
            fail("Não deveria lançar exceção");
        }
    }
}