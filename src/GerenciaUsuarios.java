import java.util.ArrayList;

public class GerenciaUsuarios {
    private ArrayList<Usuario> usuarios;
    
    public GerenciaUsuarios () {
        usuarios = new ArrayList<>();
    }

    public void adicionaUsuario (Usuario u)  {
        usuarios.add(u);
    }

    //pesquisaUsuario pelo id e nome
}
