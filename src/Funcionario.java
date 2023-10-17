public class Funcionario extends Usuario{
    
    private Departamento departamento;

    public Funcionario(int id, String nome, String iniciais, Departamento departamento) {
        super(id, nome, iniciais);
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Funcionario [departamento=" + departamento + "]";
    }
    
}
