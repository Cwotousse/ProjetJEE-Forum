package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.CommentairePOJO;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class CommentaireDAO extends DAO<CommentairePOJO> {

	public CommentaireDAO(Connection conn) { super(conn); }

	@Override
	public void create(CommentairePOJO commentairePOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour ajouter un utilisateur
			cst = connect.prepareCall(Sprocs.INSERTCOMMENTAIRE);

			//cst.setInt		(1, commentairePOJO.getID());
			cst.setInt		(1, commentairePOJO.getSujetPOJO().getID());
			cst.setString	(2, commentairePOJO.getTexte());
			cst.setDate		(3, commentairePOJO.getDateCommentaire());
			cst.setInt		(4, commentairePOJO.getUtilisateurPOJO().getID());
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
	public void delete(CommentairePOJO commentairePOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.DELETECOMMENTAIRE);
			cst.setString	(1, commentairePOJO.getTexte());
			cst.setDate		(2, commentairePOJO.getDateCommentaire());
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
	public void update(CommentairePOJO commentairePOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.UPDATECOMMENTAIRE);
			
			cst.setString	(1, commentairePOJO.getTexte());
			cst.setDate		(2, commentairePOJO.getDateCommentaire());
			cst.setInt		(3, commentairePOJO.getID());
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
	public CommentairePOJO find(int id) {
		CommentairePOJO commentairePOJO = null;
		CallableStatement cst = null;
		DAO<UtilisateurPOJO> utilisateurDAO = new DAOFactory().getUtilisateurDAO();
		DAO<SujetPOJO> sujetDAO = new DAOFactory().getSujetDAO();

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
			
			commentairePOJO = new CommentairePOJO(
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
		return commentairePOJO;
	}

	@Override
	public ArrayList<CommentairePOJO> getList() {
		CommentairePOJO 			commentairePOJO 	= null;
		CallableStatement 			cst 				= null;
		ResultSet 					rs 					= null;
		DAO<UtilisateurPOJO> 		utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		DAO<SujetPOJO> 				sujetDAO 			= new DAOFactory().getSujetDAO();
		ArrayList<CommentairePOJO> 	listcommentairePOJO = new ArrayList<CommentairePOJO>();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTCOMMENTAIRE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				commentairePOJO = new CommentairePOJO(
						rs.getInt("idCommentaire"),
						sujetDAO.find(rs.getInt("idSujet")),
						rs.getString("texte"),
						rs.getDate("dateCommentaire"),
						utilisateurDAO.find(rs.getInt("idUtilisateur")));
				listcommentairePOJO.add(commentairePOJO);
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
		return listcommentairePOJO;
	}

}
