import java.util.ArrayList;

public class GerenciaUsuarios {
    private ArrayList<Usuario> usuarios;
    private int ultimoId;
    
    public GerenciaUsuarios () {
        usuarios = new ArrayList<>();
        ultimoId = 1;
    }

    public void adicionaUsuario (Usuario u)  {
        usuarios.add(u);
        ultimoId++;
    }

    public int getUltimoId() {
        return ultimoId;
    }

    public Usuario pesquisaUsuarioId (int id) {
        Usuario aux = null;
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                aux = u;
            }
        }
        return aux;
    }

    public Usuario pesquisaUsuarioNome (String nome) {
        Usuario aux = null;
        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome)) {
                aux = u;
            }
        }
        return aux;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
