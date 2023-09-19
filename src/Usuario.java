public abstract class Usuario {
    
    private int id;
    private String nome;
    private String iniciais;
    
    public Usuario(int id, String nome, String iniciais) {
        this.id = id;
        this.nome = nome;
        this.iniciais = iniciais;
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

}