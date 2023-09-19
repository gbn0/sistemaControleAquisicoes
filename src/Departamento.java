public class Departamento {
    
    private String nome;
    private String descricao;
    private double valorMaximo;
    
    public Departamento(String nome, String descricao, double valorMaximo) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorMaximo = valorMaximo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    
}
