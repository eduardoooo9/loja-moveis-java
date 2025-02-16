package br.ifpe.jaboatao.loja.service;

import br.ifpe.jaboatao.loja.model.Material;
import br.ifpe.jaboatao.loja.model.Movel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class MovelService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void save(Movel movel) {
        System.out.println("Nome: " + movel.getNome() + " Desc: " + movel.getDescricao());
        jdbcTemplate.update("INSERT INTO Moveis (nome, descricao, preco, id_material) VALUES (?, ?, ?, ?)", movel.getNome(), movel.getDescricao(), movel.getPreco(), movel.getId_material());
    }

    @Transactional
    public Movel findById(Integer id) throws SQLException {
        return jdbcTemplate.queryForObject("SELECT * FROM Moveis WHERE id = ?", new Object[]{id}, (rs, rowNum) -> new Movel(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getFloat("preco"), rs.getInt("id_material")));
    }

    @Transactional
    public void deleteById(Integer id) throws SQLException {
        jdbcTemplate.update("DELETE FROM Moveis WHERE id = ?", id);
    }

    @Transactional
    public List<Map<String, Object>> findAll() throws SQLException {
        return jdbcTemplate.queryForList("SELECT * FROM Moveis");
    }

    @Transactional
    public void updateById(Movel movel) {
        jdbcTemplate.update("UPDATE Moveis SET nome = ?, descricao = ?, preco = ?, id_material = ? WHERE id = ?", movel.getNome(), movel.getDescricao(), movel.getPreco(), movel.getId_material(), movel.getId());
    }



}
