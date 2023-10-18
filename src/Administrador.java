public class Administrador extends Usuario{
    
    public Administrador(int id, String nome, String iniciais, Departamento departamento) {
        super(id, nome, iniciais, departamento);
    }

    @Override
    public String toString() {
        return ("==================================\n" + "Tipo: Administrador" + "\nID: " + this.getId() + "\nNome: " + this.getNome() + "\nDepartamento: " + this.getDepartamento().getNome() + "\n==================================");
    }
}
