package br.ifpe.jaboatao.loja.controller;

import br.ifpe.jaboatao.loja.model.Movel;
import br.ifpe.jaboatao.loja.service.MovelService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class MovelController {

    @Autowired
    private MovelService movelService;

    @GetMapping("cadastrar-movel")
    public String cadastrarMovel() {
        return "cadastrarMovel";
    }

    @GetMapping("pesquisar-movel")
    public String pesquisar_movel() {
        return "pesquisarMovel";
    }

    @PostMapping("cadastrar-movel")
    public String cadastrar_movel(@RequestParam String nome, @RequestParam String descricao, @RequestParam Float preco, @RequestParam Integer id_material) {
        Movel movel = new Movel(nome, descricao, preco, id_material);
        movelService.save(movel);
        return "pesquisarMovel";
    }

    @PostMapping("pesquisar-movel")
    public String pesquisarMovel(@RequestParam Integer id, Model model) throws SQLException {
        Movel movel = movelService.findById(id);
        model.addAttribute("id",  id);
        model.addAttribute("nome", movel.getNome());
        model.addAttribute("descricao", movel.getDescricao());
        model.addAttribute("preco", movel.getPreco());
        model.addAttribute("id_material", movel.getId_material());

        return "pesquisarMovel";
    }

    @PostMapping("deletar-movel")
    public ResponseEntity<String> deletarMovel(@RequestParam Integer id) throws SQLException {
        movelService.deleteById(id);
        return ResponseEntity.ok("Deletado com Sucesso");
    }

    @GetMapping("atualizar-movel")
    public String atualizar_movel(@RequestParam Integer id, @RequestParam String nome, @RequestParam String descricao, @RequestParam Float preco, @RequestParam Integer id_material, Model model) {
        model.addAttribute("id",  id);
        model.addAttribute("nome", nome);
        model.addAttribute("descricao", descricao);
        model.addAttribute("preco", preco);
        model.addAttribute("id_material", id_material);
        return "atualizarMovel";
    }

    @PostMapping("atualizar-movel")
    public ResponseEntity<String> atualizarMovel(@RequestParam Integer id, @RequestParam String nome, @RequestParam String descricao, @RequestParam Float preco, @RequestParam Integer id_material) {
        Movel movel = new Movel(id, nome, descricao, preco, id_material);
        movelService.updateById(movel);
        return ResponseEntity.ok("Atualizado com Sucesso");
    }
}
