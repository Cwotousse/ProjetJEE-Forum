package be.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.CategoriePOJO;
import be.forum.pojo.SousCategoriePOJO;

public class SousCategorieDAO extends DAO<SousCategoriePOJO>{

	public SousCategorieDAO(Connection conn) { super(conn); }

	@Override
	public void create(SousCategoriePOJO sousCategoriePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"INSERT INTO SousCategorie (SEQ_SOUSCATEGORIE.NEXTVAL, titre) "
							+ "VALUES (?,?)");

			pst.setInt		(1, sousCategoriePOJO.getID());
			pst.setString	(2, sousCategoriePOJO.getTitre());
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
	public void delete(SousCategoriePOJO sousCategoriePOJO) {
		PreparedStatement pst = null;
		try {
			// On supprime les données nécessaires dans la table Utilisateur
			pst = connect.prepareStatement("DELETE FROM SousCategorie WHERE titre = ?");

			pst.setString(1, sousCategoriePOJO.getTitre());
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
	public void update(SousCategoriePOJO sousCategoriePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"UPDATE SousCategorie SET idCategorie = ?, titre = ? WHERE idCategorie = ?");
			pst.setInt		(1, sousCategoriePOJO.getCategoriePOJO().getID());
			pst.setString	(2, sousCategoriePOJO.getTitre());
			pst.setInt		(1, sousCategoriePOJO.getID());
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
	public SousCategoriePOJO find(int id) {
		SousCategoriePOJO 	 sousCategoriePOJO 	= null;
		PreparedStatement 	 pst			 	= null;
		ResultSet			 rs					= null;
		DAO<CategoriePOJO> 	 categorieDAO	 	= new DAOFactory().getCategorieDAO();
		
		try {
			pst = this.connect.prepareStatement("SELECT * FROM SousCategorie WHERE idSousCategorie = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				sousCategoriePOJO = new SousCategoriePOJO(
										rs.getInt			("idSousCategorie"),
										categorieDAO.find	(rs.getInt("idCategorie")),
										rs.getString		("titre")
									);
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
		return sousCategoriePOJO;
	}

	@Override
	public ArrayList<SousCategoriePOJO> getList() {
		SousCategoriePOJO 				sousCategoriePOJO 	= null;
		PreparedStatement 				pst 				= null;
		ResultSet 						rs 					= null;
		ArrayList<SousCategoriePOJO> 	listSousCategorie 	= new ArrayList<SousCategoriePOJO>();
		DAO<CategoriePOJO> 	 			categorieDAO 		= new DAOFactory().getCategorieDAO();
		try {
			pst = this.connect.prepareStatement("SELECT * FROM SousCategorie");
			rs = pst.executeQuery();
			while (rs.next()) {
				sousCategoriePOJO = new SousCategoriePOJO(
							rs.getInt			("idSousCategorie"), 
							categorieDAO.find	(rs.getInt("idCategorie")),
							rs.getString		("titre")
						);
				listSousCategorie.add(sousCategoriePOJO);
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
		return listSousCategorie;
	}
}
