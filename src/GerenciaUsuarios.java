import java.util.ArrayList;

public class GerenciaUsuarios {
    private ArrayList<Usuario> usuarios;
    
    public GerenciaUsuarios () {
        usuarios = new ArrayList<>();
    }

    public void adicionaUsuario (Usuario u)  {
        usuarios.add(u);
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
}
