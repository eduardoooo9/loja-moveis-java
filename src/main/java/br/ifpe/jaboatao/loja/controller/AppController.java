package br.ifpe.jaboatao.loja.controller;

import br.ifpe.jaboatao.loja.service.MaterialService;
import br.ifpe.jaboatao.loja.service.MovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private MovelService movelService;

    @GetMapping("/")
    public String App(Model model) {
        model.addAttribute("htmlContent", "<strong>This is bold text</strong>");
        return "index";
    }

    @GetMapping("/listar-id")
    public String listar_id(Model model) throws SQLException  {
        List<Map<String, Object>> materiais = materialService.findAll();
        model.addAttribute("materiais", materiais);


        List<Map<String, Object>> moveis = movelService.findAll();
        model.addAttribute("moveis", moveis);

        return "IDs";
    }

}
