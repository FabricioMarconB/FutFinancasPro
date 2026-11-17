package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import framework.log.ExceptionLogTrack;
import jakarta.servlet.http.Cookie;
import model.*;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        try {
            switch (task) {
                case "login": doDefault(req, resp); break;
                case "logout": doGetLogout(req, resp); break;
                case "tipousuario": doGetTipoUsuario(req, resp); break;
                case "usuario": doGetUsuario(req, resp); break;
                case "clube": doGetClube(req, resp); break;
                case "atleta": doGetAtleta(req, resp); break;
                case "contrato": doGetContrato(req, resp); break;
                case "lancamento": doGetLancamento(req, resp); break;
                case null:
                default:
                    doDefault(req, resp);
            }
        } catch (Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        try {
            switch (task) {
                case "login": doPostLogin(req, resp); break;
                case "tipousuario": doPostTipoUsuario(req, resp); break;
                case "usuario": doPostUsuario(req, resp); break;
                case "clube": doPostClube(req, resp); break;
                case "atleta": doPostAtleta(req, resp); break;
                case "contrato": doPostContrato(req, resp); break;
                case "lancamento": doPostLancamento(req, resp); break;
                case null:
                default:
                    doDefault(req, resp);
            }
        } catch (Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            throw new ServletException(ex);
        }
    }

    private void doDefault(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
    }

    private void doGetLogout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession sessao = req.getSession(false);
        if (sessao != null) {
            sessao.removeAttribute("tipo_usuario_sessao");
            sessao.removeAttribute("usuario_sessao");
            sessao.invalidate();
        }
        req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
    }

    private void doGetTipoUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            TipoUsuario usTp = new TipoUsuario(id);
            TipoUsuarioDAO dao = new TipoUsuarioDAO();
            dao.delete(usTp);
        }
        req.getRequestDispatcher("/home/app/adm/tipousuario.jsp").forward(req, resp);
    }

    private void doGetUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            Usuario us = new Usuario(id);
            UsuarioDAO dao = new UsuarioDAO();
            dao.delete(us);
        }
        req.getRequestDispatcher("/home/app/adm/usuario.jsp").forward(req, resp);
    }

    private void doGetClube(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            Clube cl = new Clube(id);
            ClubeDAO dao = new ClubeDAO();
            dao.delete(cl);
        }
        req.getRequestDispatcher("/home/app/adm/clube.jsp").forward(req, resp);
    }

    private void doGetAtleta(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            Atleta at = new Atleta(id);
            AtletaDAO dao = new AtletaDAO();
            dao.delete(at);
        }
        req.getRequestDispatcher("/home/app/adm/atleta.jsp").forward(req, resp);
    }

    private void doGetContrato(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            Contrato ct = new Contrato(id);
            ContratoDAO dao = new ContratoDAO();
            dao.delete(ct);
        }
        req.getRequestDispatcher("/home/app/elenco/contrato.jsp").forward(req, resp);
    }

    private void doGetLancamento(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        if ((action != null) && (action.equals("delete"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            LancamentoFinanceiro lf = new LancamentoFinanceiro(id);
            LancamentoFinanceiroDAO dao = new LancamentoFinanceiroDAO();
            dao.delete(lf);
        }
        req.getRequestDispatcher("/home/app/financeiro/lancamento.jsp").forward(req, resp);
    }

    private void doPostLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("id"));
        String senha = req.getParameter("senha");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario us = dao.getUnique(id);

        Usuario usTry = new Usuario(id);
        usTry.setSenha(senha);

        Cookie cookieId = new Cookie("id", String.valueOf(id));
        cookieId.setMaxAge(60 * 2);
        resp.addCookie(cookieId);

        if ((us != null) && (us.getSenha().equals(usTry.getSenha()))) {
            TipoUsuario usTp = new TipoUsuarioDAO().getUnique(us.getTipoUsuarioId());

            HttpSession sessao = req.getSession(false);
            if (sessao != null) {
                sessao.invalidate();
            }

            sessao = req.getSession(true);
            sessao.setMaxInactiveInterval(60 * 10);
            sessao.setAttribute("tipo_usuario_sessao", usTp);
            sessao.setAttribute("usuario_sessao", us);

            req.getRequestDispatcher("/home/app/menu.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "id e/ou senha incorreto(s)!");
            req.getRequestDispatcher("/home/login.jsp").forward(req, resp);
        }
    }

    private void doPostTipoUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));

        String moduloAdministrativo = req.getParameter("modulo_administrativo");
        if (moduloAdministrativo == null) moduloAdministrativo = "N";

        String moduloElenco = req.getParameter("modulo_elenco");
        if (moduloElenco == null) moduloElenco = "N";

        String moduloFinanceiro = req.getParameter("modulo_financeiro");
        if (moduloFinanceiro == null) moduloFinanceiro = "N";

        TipoUsuario usTp = new TipoUsuario(id);
        usTp.setModuloAdministrativo(moduloAdministrativo);
        usTp.setModuloElenco(moduloElenco);
        usTp.setModuloFinanceiro(moduloFinanceiro);

        TipoUsuarioDAO dao = new TipoUsuarioDAO();
        if (action.equals("new")) dao.insert(usTp);
        if (action.equals("update")) dao.update(usTp);

        req.getRequestDispatcher("/home/app/adm/tipousuario.jsp").forward(req, resp);
    }

    private void doPostUsuario(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String senha = req.getParameter("senha");
        int tipoUsuarioId = Integer.parseInt(req.getParameter("tipo_usuario_id"));

        Usuario us = new Usuario(id);
        us.setNome(nome);

        if (senha.length() > 20) {
            us.setSenhaHash(senha);
        } else {
            us.setSenha(senha);
        }

        us.setTipoUsuarioId(tipoUsuarioId);

        String nascimento = req.getParameter("nascimento");
        if (nascimento == null || nascimento.isBlank()) nascimento = null;

        String logradouro = req.getParameter("logradouro");
        if (logradouro == null || logradouro.isBlank()) logradouro = null;

        String numero = req.getParameter("numero");
        if (numero == null || numero.isBlank()) numero = null;

        String bairro = req.getParameter("bairro");
        if (bairro == null || bairro.isBlank()) bairro = null;

        String cidade = req.getParameter("cidade");
        if (cidade == null || cidade.isBlank()) cidade = null;

        String estado = req.getParameter("estado");
        if (estado == null || estado.isBlank()) estado = null;

        String uf = req.getParameter("uf");
        if (uf == null || uf.isBlank()) uf = null;

        String cep = req.getParameter("cep");
        if (cep == null || cep.isBlank()) cep = null;

        us.setNascimento(nascimento);
        us.setLogradouro(logradouro);
        us.setNumero(numero);
        us.setBairro(bairro);
        us.setCidade(cidade);
        us.setEstado(estado);
        us.setUf(uf);
        us.setCep(cep);

        UsuarioDAO dao = new UsuarioDAO();
        if (action.equals("new")) dao.insert(us);
        if (action.equals("update")) dao.update(us);

        req.getRequestDispatcher("/home/app/adm/usuario.jsp").forward(req, resp);
    }

    private void doPostClube(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));

        Clube cl = new Clube(id);
        cl.setNome(req.getParameter("nome"));
        cl.setSigla(req.getParameter("sigla").toUpperCase());
        cl.setCnpj(req.getParameter("cnpj"));
        cl.setEstadio(req.getParameter("estadio"));
        cl.setOrcamento(Double.parseDouble(req.getParameter("orcamento")));

        ClubeDAO dao = new ClubeDAO();
        if (action.equals("new")) dao.insert(cl);
        if (action.equals("update")) dao.update(cl);

        req.getRequestDispatcher("/home/app/adm/clube.jsp").forward(req, resp);
    }

    private void doPostAtleta(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));

        Atleta at = new Atleta(id);
        at.setNome(req.getParameter("nome"));
        at.setCpf(req.getParameter("cpf"));
        at.setDataNascimento(req.getParameter("data_nascimento"));
        at.setPosicao(req.getParameter("posicao"));
        at.setNumeroCamisa(Integer.parseInt(req.getParameter("numero_camisa")));

        AtletaDAO dao = new AtletaDAO();
        if (action.equals("new")) dao.insert(at);
        if (action.equals("update")) dao.update(at);

        req.getRequestDispatcher("/home/app/adm/atleta.jsp").forward(req, resp);
    }

    private void doPostContrato(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));

        Contrato ct = new Contrato(id);
        ct.setClubeId(Integer.parseInt(req.getParameter("clube_id")));
        ct.setAtletaId(Integer.parseInt(req.getParameter("atleta_id")));
        ct.setSalario(Double.parseDouble(req.getParameter("salario")));
        ct.setMultaRescisoria(Double.parseDouble(req.getParameter("multa_rescisoria")));
        ct.setDataInicio(req.getParameter("data_inicio"));
        ct.setDataFim(req.getParameter("data_fim"));
        ct.setStatus(req.getParameter("status"));

        ContratoDAO dao = new ContratoDAO();
        if (action.equals("new")) dao.insert(ct);
        if (action.equals("update")) dao.update(ct);

        req.getRequestDispatcher("/home/app/elenco/contrato.jsp").forward(req, resp);
    }

    private void doPostLancamento(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));

        LancamentoFinanceiro lf = new LancamentoFinanceiro(id);
        lf.setClubeId(Integer.parseInt(req.getParameter("clube_id")));
        lf.setDescricao(req.getParameter("descricao"));
        lf.setTipo(req.getParameter("tipo"));
        lf.setCategoria(req.getParameter("categoria"));
        lf.setValor(Double.parseDouble(req.getParameter("valor")));
        lf.setDataCompetencia(req.getParameter("data_competencia"));

        LancamentoFinanceiroDAO dao = new LancamentoFinanceiroDAO();
        if (action.equals("new")) dao.insert(lf);
        if (action.equals("update")) dao.update(lf);

        req.getRequestDispatcher("/home/app/financeiro/lancamento.jsp").forward(req, resp);
    }
}