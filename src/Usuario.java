import java.util.ArrayList;

public abstract class Usuario {
    
    private int id;
    private String nome;
    private String iniciais;
    private Departamento departamento;
    private ArrayList<Pedido> pedidos;
    
    public Usuario(int id, String nome, String iniciais, Departamento departamento) {
        this.id = id;
        this.nome = nome;
        this.iniciais = iniciais;
        this.departamento = departamento;
        this.pedidos = new ArrayList<Pedido>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIniciais() {
        return iniciais;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}