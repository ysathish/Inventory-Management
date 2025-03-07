package com.nt.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nt.model.InventoryModel;

@Repository
public class InventoryRepo 
{
	@Autowired
	private JdbcTemplate jdbc;
	
	public InventoryRepo(JdbcTemplate jdbc) { // Constructor Injection
		this.jdbc = jdbc;
	}

	public JdbcTemplate getJdbc() {
		return jdbc;
	}

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<InventoryModel> findAll()
	{
		String sql="select product_id,product_name,price,quantity from inventory";
		return jdbc.query(sql, (rs, rowNum) -> {
			return new InventoryModel(
					rs.getLong("product_id"),  // Fetch 'id' but don't set it on insert
					rs.getString("product_name"),
					rs.getFloat("price"),
					rs.getInt("quantity")
					);
		});
	}
	public void save(InventoryModel inventory)
	{
		String sql="insert into inventory (product_name,price,quantity)values(?,?,?)";
		jdbc.update(sql,inventory.getProduct_name(),inventory.getPrice(),inventory.getQuantity());
	}



	public int update(Long product_id, InventoryModel inventory) {
		String sql="update inventory set product_name=?,price=?,quantity=? where product_id=?";

		return jdbc.update(sql,inventory.getProduct_name(),inventory.getPrice(),inventory.getQuantity(),product_id);
	}

	public void deleteInventory(Long product_id)
	{
		String sql="delete from inventory where product_id=?";

		jdbc.update(sql,product_id);
	}

	public Optional<InventoryModel> findById(Long product_id) {
		String sql="select product_id,product_name,price,quantity from inventory where product_id=?";
		RowMapper<InventoryModel> mapper=(rs,rowNum)->new InventoryModel(
				rs.getLong("product_id"),
				rs.getString("product_name"),
				rs.getFloat("price"),
				rs.getInt("quantity")
				);	
		List<InventoryModel> result=jdbc.query(sql, mapper,product_id);
		return result.isEmpty()? Optional.empty():Optional.of(result.get(0));
	}
}
