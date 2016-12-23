package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.Commentaire;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class CommentaireDAO extends DAO<Commentaire> {

	public CommentaireDAO(Connection conn) { super(conn); }

	@Override
	public void create(Commentaire commentaire) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour ajouter un utilisateur
			cst = connect.prepareCall(Sprocs.INSERTCOMMENTAIRE);

			cst.setInt		(1, commentaire.getSujet().getID());
			cst.setString	(2, commentaire.getTexte());
			cst.setDate		(3, commentaire.getDateCommentaire());
			cst.setInt		(4, commentaire.getUtilisateur().getID());
			cst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(Commentaire commentaire) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.DELETECOMMENTAIRE);
			cst.setString	(1, commentaire.getTexte());
			cst.setDate		(2, commentaire.getDateCommentaire());
			cst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(Commentaire commentaire) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.UPDATECOMMENTAIRE);
			
			cst.setString	(1, commentaire.getTexte());
			cst.setDate		(2, commentaire.getDateCommentaire());
			cst.setInt		(3, commentaire.getID());
			cst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Commentaire find(int id) {
		Commentaire commentaire = null;
		CallableStatement cst = null;
		DAO<Utilisateur> utilisateurDAO = new DAOFactory().getUtilisateurDAO();
		DAO<Sujet> sujetDAO = new DAOFactory().getSujetDAO();

		try {
			cst = connect.prepareCall(Sprocs.SELECTCOMMENTAIRE);
			//J'insère le paramètre entrant
			cst.setInt(1, id);
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.NUMERIC);
			cst.registerOutParameter(3, java.sql.Types.VARCHAR);
			cst.registerOutParameter(4, java.sql.Types.DATE);
			cst.registerOutParameter(5, java.sql.Types.NUMERIC);
			cst.executeQuery();
			
			commentaire = new Commentaire(
					id,
					sujetDAO.find		(cst.getInt(2)),
					cst.getString		(3),
					cst.getDate			(4),
					utilisateurDAO.find	(cst.getInt(5))
			);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return commentaire;
	}

	@Override
	public ArrayList<Commentaire> getList() {
		Commentaire 			commentaire 	= null;
		CallableStatement 			cst 				= null;
		ResultSet 					rs 					= null;
		DAO<Utilisateur> 		utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		DAO<Sujet> 				sujetDAO 			= new DAOFactory().getSujetDAO();
		ArrayList<Commentaire> 	listcommentaire = new ArrayList<Commentaire>();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTCOMMENTAIRE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				commentaire = new Commentaire(
						rs.getInt("idCommentaire"),
						sujetDAO.find(rs.getInt("idSujet")),
						rs.getString("texte"),
						rs.getDate("dateCommentaire"),
						utilisateurDAO.find(rs.getInt("idUtilisateur")));
				listcommentaire.add(commentaire);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listcommentaire;
	}

}
