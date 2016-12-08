package be.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.CommentairePOJO;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;

public class CommentaireDAO extends DAO<CommentairePOJO> {

	public CommentaireDAO(Connection conn) { super(conn); }

	@Override
	public void create(CommentairePOJO commentairePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"INSERT INTO Commentaire (idCommentaire, idSujet, texte, dateCommentaire, idUtilisateur)"
							+ " VALUES (?,?,?,?,?)");

			pst.setInt(1, commentairePOJO.getID());
			pst.setInt(2, commentairePOJO.getSujetPOJO().getID());
			pst.setString(3, commentairePOJO.getTexte());
			pst.setDate(4, commentairePOJO.getDateCommentaire());
			pst.setInt(5, commentairePOJO.getUtilisateurPOJO().getID());
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
	public void delete(CommentairePOJO commentairePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("DELETE FROM Categorie WHERE texte = ? AND dateCommentaire = ?");
			pst.setString(1, commentairePOJO.getTexte());
			pst.setDate(2, commentairePOJO.getDateCommentaire());
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
	public void update(CommentairePOJO commentairePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect
					.prepareStatement("UPDATE Categorie SET texte = ?, dateCommentaire = ? WHERE idCommentaire = ?");
			pst.setString(1, commentairePOJO.getTexte());
			pst.setDate(2, commentairePOJO.getDateCommentaire());
			pst.setInt(3, commentairePOJO.getID());
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
	public CommentairePOJO find(int id) {
		CommentairePOJO commentairePOJO = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DAO<UtilisateurPOJO> utilisateurDAO = new DAOFactory().getUtilisateurDAO();
		DAO<SujetPOJO> sujetDAO = new DAOFactory().getSujetDAO();

		try {
			pst = this.connect.prepareStatement("SELECT * FROM Commentaire WHERE idCommentaire = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				commentairePOJO = new CommentairePOJO(rs.getInt("idCommentaire"), sujetDAO.find(rs.getInt("idSujet")),
						utilisateurDAO.find(rs.getInt("idUtilisateur")), rs.getString("texte"),
						rs.getDate("dateCommentaire")

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
		return commentairePOJO;
	}

	@Override
	public ArrayList<CommentairePOJO> getList() {
		CommentairePOJO commentairePOJO = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DAO<UtilisateurPOJO> utilisateurDAO = new DAOFactory().getUtilisateurDAO();
		DAO<SujetPOJO> sujetDAO = new DAOFactory().getSujetDAO();
		ArrayList<CommentairePOJO> listcommentairePOJO = new ArrayList<CommentairePOJO>();
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Categorie");
			rs = pst.executeQuery();
			while (rs.next()) {
				commentairePOJO = new CommentairePOJO(rs.getInt("idCommentaire"), sujetDAO.find(rs.getInt("idSujet")),
						utilisateurDAO.find(rs.getInt("idUtilisateur")), rs.getString("texte"),
						rs.getDate("dateCommentaire"));
				listcommentairePOJO.add(commentairePOJO);
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
		return listcommentairePOJO;
	}

}
