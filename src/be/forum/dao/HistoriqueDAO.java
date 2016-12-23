package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.Historique;
import be.forum.pojo.Utilisateur;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class HistoriqueDAO extends DAO<Historique> {

	public HistoriqueDAO(Connection conn) { super(conn); }

	@Override
	public void create(Historique historique) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.INSERTHISTORIQUE);

			cst.setDate	(1, historique.getDateConnexion());
			cst.setInt	(2, historique.getUtilisateur().getID());
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
	public void delete(Historique historique) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.DELETEHISTORIQUE);

			cst.setDate	(1, historique.getDateConnexion());
			cst.setInt	(2, historique.getUtilisateur().getID());
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
	public void update(Historique historique) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.UPDATEHISTORIQUE);
			cst.setDate	(1, historique.getDateConnexion());
			cst.setInt	(2, historique.getUtilisateur().getID());
			cst.setInt	(3, historique.getID());
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
	public Historique find(int id) {
		CallableStatement 	 cst 				= null;
		Historique 	 	 historique 	= null;
		DAO<Utilisateur> utilisateurDAO		= new DAOFactory().getUtilisateurDAO();
		
		try {
			cst = connect.prepareCall(Sprocs.SELECTHISTORIQUE);
			cst.setInt(1, id);
			
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.DATE);
			cst.registerOutParameter(3, java.sql.Types.NUMERIC);
	
			cst.executeUpdate();
			historique = new Historique(
						id,
						cst.getDate			(2),
						utilisateurDAO.find(cst.getInt(3))
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
		return historique;
	}

	@Override
	public ArrayList<Historique> getList() {
		Historique 				historique 		= null;
		CallableStatement 			cst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<Historique> 	listHistorique 		= new ArrayList<Historique>();
		DAO<Utilisateur> 	 	utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTHISTORIQUE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				historique = new Historique(
							rs.getInt			("idHistorique"), 
							rs.getDate			("dateConnexion"),
							utilisateurDAO.find	(rs.getInt("idUtilisateur"))
						);
				listHistorique.add(historique);
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
		return listHistorique;
	}
}
