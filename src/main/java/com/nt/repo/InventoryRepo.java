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
	private final JdbcTemplate jdbc;

	public InventoryRepo(JdbcTemplate jdbc) { 
		this.jdbc = jdbc;
	}

	public List<InventoryModel> findAll()
	{
		String sql="SELECT product_id, product_name, price, quantity FROM inventory";
		return jdbc.query(sql, (rs, rowNum) -> new InventoryModel(
				rs.getLong("product_id"),
				rs.getString("product_name"),
				rs.getFloat("price"),
				rs.getInt("quantity")
		));
	}

	public int save(InventoryModel inventory)
	{
		String sql="INSERT INTO inventory (product_name, price, quantity) VALUES (?, ?, ?)";
		return jdbc.update(sql, inventory.getProduct_name(), inventory.getPrice(), inventory.getQuantity());
	}

	public int update(Long product_id, InventoryModel inventory) {
		String sql="UPDATE inventory SET product_name=?, price=?, quantity=? WHERE product_id=?";
		return jdbc.update(sql, inventory.getProduct_name(), inventory.getPrice(), inventory.getQuantity(), product_id);
	}

	public int deleteById(Long product_id)
	{
		String sql="DELETE FROM inventory WHERE product_id=?";
		return jdbc.update(sql, product_id);
	}

	public Optional<InventoryModel> findById(Long product_id) {
		String sql="SELECT product_id, product_name, price, quantity FROM inventory WHERE product_id=?";
		RowMapper<InventoryModel> mapper = (rs, rowNum) -> new InventoryModel(
				rs.getLong("product_id"),
				rs.getString("product_name"),
				rs.getFloat("price"),
				rs.getInt("quantity")
		);	
		List<InventoryModel> result = jdbc.query(sql, mapper, product_id);
		return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
	}

	// ✅ Fixed Method Name
	public boolean existsById(Long product_id) {
		String sql = "SELECT COUNT(*) FROM inventory WHERE product_id=?";
		Integer count = jdbc.queryForObject(sql, Integer.class, product_id);
		return count != null && count > 0;
	}
}
