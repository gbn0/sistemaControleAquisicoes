import java.util.ArrayList;

public abstract class Usuario {
    
    private int id;
    private String nome;
    private String iniciais;
    private ArrayList<Pedido> pedidos;
    
    public Usuario(int id, String nome, String iniciais) {
        this.id = id;
        this.nome = nome;
        this.iniciais = iniciais;
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

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}