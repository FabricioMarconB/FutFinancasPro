package model;

public class Main {
    public static void main(String[] args) throws Exception {
        
        TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
        if (tipoUsuarioDAO.getUnique(1) == null) {
            TipoUsuario tp = new TipoUsuario(1);
            tp.setModuloAdministrativo("S");
            tp.setModuloElenco("S");
            tp.setModuloFinanceiro("S");
            tipoUsuarioDAO.insert(tp);
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.getUnique(1) == null) {
            Usuario usuario = new Usuario(1);
            usuario.setNome("Administrador FutFinanças");
            usuario.setSenha("1234");
            usuario.setTipoUsuarioId(1);
            usuarioDAO.insert(usuario);
        }

        System.out.println("Tipos de Usuário no banco: " + tipoUsuarioDAO.getAll());
        System.out.println("Usuários no banco: " + usuarioDAO.getAll());
    }
}