package be.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.CategoriePOJO;

public class CategorieDAO extends DAO<CategoriePOJO> {

	public CategorieDAO(Connection conn) { super(conn); }

	@Override
	public void create(CategoriePOJO categoriePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("INSERT INTO Categorie (idCategorie, titre) " + "VALUES (?,?)");

			pst.setInt(1, categoriePOJO.getID());
			pst.setString(2, categoriePOJO.getTitre());
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(CategoriePOJO categoriePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("DELETE FROM Categorie WHERE titre = ?");

			pst.setString(1, categoriePOJO.getTitre());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(CategoriePOJO categoriePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("UPDATE Categorie SET titre = ? WHERE idCategorie = ?");
			pst.setString(1, categoriePOJO.getTitre());
			pst.setInt(2, categoriePOJO.getID());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public CategoriePOJO find(int id) {
		CategoriePOJO categoriePOJO = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = this.connect.prepareStatement("SELECT * FROM Categorie WHERE idCategorie = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				categoriePOJO = new CategoriePOJO(rs.getInt("idCategorie"), rs.getString("titre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return categoriePOJO;
	}

	@Override
	public ArrayList<CategoriePOJO> getList() {
		CategoriePOJO categoriePOJO = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<CategoriePOJO> listcategoriePOJO = new ArrayList<CategoriePOJO>();
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Categorie");
			rs = pst.executeQuery();
			while (rs.next()) {
				categoriePOJO = new CategoriePOJO(rs.getInt("idCategorie"), rs.getString("titre"));
				listcategoriePOJO.add(categoriePOJO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listcategoriePOJO;
	}
}
