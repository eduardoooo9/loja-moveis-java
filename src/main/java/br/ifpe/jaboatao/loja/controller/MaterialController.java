package br.ifpe.jaboatao.loja.controller;

import org.springframework.ui.Model;
import br.ifpe.jaboatao.loja.model.Material;
import org.springframework.http.ResponseEntity;
import br.ifpe.jaboatao.loja.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    @GetMapping("/cadastrar-material")
    public String cadastrarMaterial() {
        return "cadastrarMaterial";
    }

    @PostMapping("/cadastrar-material")
    public String cadastrarMaterial(@RequestParam String nome, @RequestParam String descricao)
    {
        Material material = new Material(nome, descricao);
        System.out.println(material);
        materialService.save(material);
        return "pesquisarMaterial";
    }

    @GetMapping("/pesquisar-material")
    public String materialCadastrado()
    {
        return "pesquisarMaterial";
    }


    @PostMapping(value = "/pesquisar-material", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public String materiaisCadastrados(@RequestParam Integer id, Model model) throws SQLException {
        Material material = materialService.findById(id);
        model.addAttribute("nome", material.getNome());
        model.addAttribute("descricao", material.getDescricao());
        model.addAttribute("id",  id);
        return "pesquisarMaterial";

    }

    @PostMapping("deletar-material")
    public ResponseEntity<String> deletaMaterial(@RequestParam Integer id) throws SQLException {
        materialService.deleteById(id);
        return ResponseEntity.ok("Deletado com Sucesso!");
    }

    @GetMapping("atualizar-material")
    public String atualizaMaterial(@RequestParam Integer id, @RequestParam String nome, @RequestParam String descricao, Model model) {
        Material material = new Material(id, nome, descricao);
        model.addAttribute("id", material.getId());
        model.addAttribute("nome", material.getNome());
        model.addAttribute("desc", material.getDescricao());
        return "atualizarMaterial";
    }

    @PostMapping(value = "atualizar-material",  consumes = {"application/json", "application/x-www-form-urlencoded"})
    public ResponseEntity<String> atualizar_material(@RequestParam Integer id, @RequestParam String nome, @RequestParam String descricao) {
        Material material = new Material(id, nome, descricao);
        System.out.println("ID: " + material.getId() + " NOME: " + material.getNome() + " Desc: " + material.getDescricao());
        materialService.updateById(material);
        return ResponseEntity.ok("Atualizado com sucesso");
    }

}
