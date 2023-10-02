import java.util.Scanner;

public class SistemaControle {
    
    private Scanner in;
    private GerenciaPedidos gerenciaPedidos;
    private Usuario usuario;


    private void AprovaPedido() {
        if(!(usuario instanceof Administrador)) {
            System.out.println("Você não tem permissão para acessar essa função");
            loop();
        }
        System.out.println("Digite o codigo do pedido a ser buscado");
        int c = in.nextInt();
        in.nextLine();
        Pedido p = gerenciaPedidos.pesquisaPedido(c);

        if(p == null) {
            System.out.println("Pedido não encontrado");
        }else {
            switch(p.getStatus()) {
                case "Aberto":
                    System.out.println("O pedido está aberto, detalhes do pedido:\n" + p);
                    System.out.println("--------------------------------------------------");
                    System.out.println("1- Aprovar\n2-Reprovar\n0-Sair");
                    int op = in.nextInt();
                    if(op == 1) {
                        p.setStatus("Aprovado");
                        System.out.println("Pedido aprovado");
                    }else if(op == 2) {
                        p.setStatus("Reprovado");
                        System.out.println("Pedido reprovado");
                    }else if(op == 0) {
                        loop();
                    }else {
                        System.out.println("Opção invalida, voltando");
                        loop();
                    }
                    break;
                case "Aprovado":
                    System.out.println("O pedido já foi aprovado");
                    break;
                case "Reprovado":
                    System.out.println("O pedido já foi reprovado");
                    break;
                case "Concluido":
                    System.out.println("O pedido já está concluído");
                    break;
            }
        }
    }
}
