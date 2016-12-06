package be.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.SousCategoriePOJO;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;

public class SujetDAO extends DAO<SujetPOJO> {

	public SujetDAO(Connection conn) { super(conn); }

	@Override
	public void create(SujetPOJO sujetPOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"INSERT INTO Sujet (idSousCategorie, titre, dateSujet, idUtilisateur) "
							+ "VALUES (?,?,?,?)");

			pst.setInt		(1, sujetPOJO.getSousCategoriePOJO().getID());
			pst.setString	(2, sujetPOJO.getTitre());
			pst.setDate		(3, sujetPOJO.getDateSujet());
			pst.setInt		(4, sujetPOJO.getUtilisateurPOJO().getID());
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
	public void delete(SujetPOJO sujetPOJO) {
		PreparedStatement pst = null;
		try {
			// On supprime les données nécessaires dans la table Utilisateur
			pst = connect.prepareStatement("DELETE FROM Sujet WHERE titre = ? AND dateSujet = ?");

			pst.setString	(1, sujetPOJO.getTitre());
			pst.setDate		(2, sujetPOJO.getDateSujet());
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
	public void update(SujetPOJO sujetPOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"UPDATE Sujet SET idSousCategorie = ?, titre = ?, dateSujet = ?, idUtilisateur = ? WHERE titre = ? AND dateSujet = ?");
			pst.setInt		(1, sujetPOJO.getSousCategoriePOJO().getID());
			pst.setString	(2, sujetPOJO.getTitre());
			pst.setDate		(3, sujetPOJO.getDateSujet());
			pst.setInt		(4, sujetPOJO.getUtilisateurPOJO().getID());
			pst.setString	(5, sujetPOJO.getTitre());
			pst.setDate		(6, sujetPOJO.getDateSujet());
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
	public SujetPOJO find(int id) {
		SujetPOJO	 	 	 	sujetPOJO		 	= null;
		PreparedStatement 	 	pst			 		= null;
		ResultSet			 	rs					= null;
		DAO<UtilisateurPOJO> 	utilisateurDAO		= new DAOFactory().getUtilisateurDAO();
		DAO<SousCategoriePOJO> 	sousCategorieDAO 	= new DAOFactory().getSousCategorieDAO();
		
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Sujet WHERE idSujet = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				sujetPOJO = new SujetPOJO(
										rs.getInt				("idSujet"),
										sousCategorieDAO.find	(rs.getInt("idSousCategorie")),
										rs.getString			("titre"),
										rs.getDate				("dateSujet"),
										utilisateurDAO.find		(rs.getInt("idUtilisateur"))
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
		return sujetPOJO;
	}

	@Override
	public ArrayList<SujetPOJO> getList() {
		SujetPOJO 					sujetPOJO 			= null;
		PreparedStatement 			pst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<SujetPOJO> 		listSujet 			= new ArrayList<SujetPOJO>();
		DAO<UtilisateurPOJO> 	 	utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		DAO<SousCategoriePOJO> 		sousCategorieDAO 	= new DAOFactory().getSousCategorieDAO();
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Sujet");
			rs = pst.executeQuery();
			while (rs.next()) {
				sujetPOJO = new SujetPOJO(
								rs.getInt				("idSujet"),
								sousCategorieDAO.find	(rs.getInt("idSousCategorie")),
								rs.getString			("titre"),
								rs.getDate				("dateSujet"),
								utilisateurDAO.find		(rs.getInt("idUtilisateur"))
							);
				listSujet.add(sujetPOJO);
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
		return listSujet;
	}
}
