package be.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.HistoriquePOJO;
import be.forum.pojo.UtilisateurPOJO;

public class HistoriqueDAO extends DAO<HistoriquePOJO> {

	public HistoriqueDAO(Connection conn) { super(conn); }

	@Override
	public void create(HistoriquePOJO historiquePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"INSERT INTO Historique (dateConnexion, idUtilisateur) "
							+ "VALUES (?,?)");

			pst.setDate	(1, historiquePOJO.getDateConnexion());
			pst.setInt	(2, historiquePOJO.getUtilisateurPOJO().getID());
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
	public void delete(HistoriquePOJO historiquePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("DELETE FROM Historique WHERE dateConnexion = ? AND idUtilisateur = ?");

			pst.setDate	(1, historiquePOJO.getDateConnexion());
			pst.setInt	(2, historiquePOJO.getUtilisateurPOJO().getID());
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
	public void update(HistoriquePOJO historiquePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"UPDATE Historique SET dateConnexion = ?, idUtilisateur = ? WHERE idHistorique = ?");
			pst.setDate	(1, historiquePOJO.getDateConnexion());
			pst.setInt	(2, historiquePOJO.getUtilisateurPOJO().getID());
			pst.setInt	(3, historiquePOJO.getID());
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
	public HistoriquePOJO find(int id) {
		HistoriquePOJO 	 	 historiquePOJO 	= null;
		PreparedStatement 	 pst			 	= null;
		ResultSet			 rs					= null;
		DAO<UtilisateurPOJO> utilisateurDAO		= new DAOFactory().getUtilisateurDAO();
		
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Historique WHERE idHistorique = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				historiquePOJO = new HistoriquePOJO(
										rs.getInt			("idHistorique"),
										utilisateurDAO.find	(rs.getInt("idUtilisateur")),
										rs.getDate			("dateConnexion")
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
		return historiquePOJO;
	}

	@Override
	public ArrayList<HistoriquePOJO> getList() {
		HistoriquePOJO 				historiquePOJO 		= null;
		PreparedStatement 			pst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<HistoriquePOJO> 	listHistorique 		= new ArrayList<HistoriquePOJO>();
		DAO<UtilisateurPOJO> 	 	utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Historique");
			rs = pst.executeQuery();
			while (rs.next()) {
				historiquePOJO = new HistoriquePOJO(
							rs.getInt			("idHistorique"), 
							utilisateurDAO.find	(rs.getInt("idUtilisateur")),
							rs.getDate			("dateConnexion")
						);
				listHistorique.add(historiquePOJO);
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
		return listHistorique;
	}
}
