import java.util.ArrayList;

public class Funcionario extends Usuario{
    
    private Departamento departamento;
    private ArrayList<Pedido> pedidos;

    public Funcionario(int id, String nome, String iniciais, Departamento departamento) {
        super(id, nome, iniciais);
        this.departamento = departamento;
        this.pedidos = new ArrayList<Pedido>();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    
}
