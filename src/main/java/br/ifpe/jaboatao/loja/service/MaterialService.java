package br.ifpe.jaboatao.loja.service;

import br.ifpe.jaboatao.loja.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class MaterialService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void save(Material material)
    {
        System.out.println("Nome: " + material.getNome() + " Desc: " + material.getDescricao());
        jdbcTemplate.update("INSERT INTO Materiais (nome, descricao) VALUES (?, ?)", material.getNome(), material.getDescricao());
    }

    @Transactional
    public Material findById(Integer id) throws SQLException  {
        return jdbcTemplate.queryForObject("SELECT * FROM Materiais WHERE id = ?", new Object[]{id}, (rs, rowNum) -> new Material(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao")));
    }

    @Transactional
    public List<Map<String, Object>> findAll() throws SQLException {
        return jdbcTemplate.queryForList("SELECT * FROM Materiais");
    }

    @Transactional
    public void deleteById(Integer id) throws SQLException {
        jdbcTemplate.update("DELETE FROM Materiais WHERE id = ?", id);
    }

    @Transactional
    public void updateById(Material material) {
        jdbcTemplate.update("UPDATE Materiais SET nome = ?, descricao = ? WHERE id = ?", material.getNome(), material.getDescricao(), material.getId());
    }
}

