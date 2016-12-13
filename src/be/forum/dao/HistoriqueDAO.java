package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.HistoriquePOJO;
import be.forum.pojo.UtilisateurPOJO;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class HistoriqueDAO extends DAO<HistoriquePOJO> {

	public HistoriqueDAO(Connection conn) { super(conn); }

	@Override
	public void create(HistoriquePOJO historiquePOJO) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.INSERTHISTORIQUE);

			cst.setDate	(1, historiquePOJO.getDateConnexion());
			cst.setInt	(2, historiquePOJO.getUtilisateurPOJO().getID());
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
	public void delete(HistoriquePOJO historiquePOJO) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.DELETEHISTORIQUE);

			cst.setDate	(1, historiquePOJO.getDateConnexion());
			cst.setInt	(2, historiquePOJO.getUtilisateurPOJO().getID());
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
	public void update(HistoriquePOJO historiquePOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.UPDATEHISTORIQUE);
			cst.setDate	(1, historiquePOJO.getDateConnexion());
			cst.setInt	(2, historiquePOJO.getUtilisateurPOJO().getID());
			cst.setInt	(3, historiquePOJO.getID());
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
	public HistoriquePOJO find(int id) {
		CallableStatement 	 cst 				= null;
		HistoriquePOJO 	 	 historiquePOJO 	= null;
		DAO<UtilisateurPOJO> utilisateurDAO		= new DAOFactory().getUtilisateurDAO();
		
		try {
			cst = connect.prepareCall(Sprocs.SELECTHISTORIQUE);
			cst.setInt(1, id);
			
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.DATE);
			cst.registerOutParameter(3, java.sql.Types.NUMERIC);
	
			cst.executeUpdate();
			historiquePOJO = new HistoriquePOJO(
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
		return historiquePOJO;
	}

	@Override
	public ArrayList<HistoriquePOJO> getList() {
		HistoriquePOJO 				historiquePOJO 		= null;
		CallableStatement 			cst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<HistoriquePOJO> 	listHistorique 		= new ArrayList<HistoriquePOJO>();
		DAO<UtilisateurPOJO> 	 	utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTHISTORIQUE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				historiquePOJO = new HistoriquePOJO(
							rs.getInt			("idHistorique"), 
							rs.getDate			("dateConnexion"),
							utilisateurDAO.find	(rs.getInt("idUtilisateur"))
						);
				listHistorique.add(historiquePOJO);
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
