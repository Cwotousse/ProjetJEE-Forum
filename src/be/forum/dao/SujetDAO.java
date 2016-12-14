package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.SousCategoriePOJO;
import be.forum.pojo.SujetPOJO;
import be.forum.pojo.UtilisateurPOJO;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class SujetDAO extends DAO<SujetPOJO> {

	public SujetDAO(Connection conn) { super(conn); }

	@Override
	public void create(SujetPOJO sujetPOJO) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.INSERTSUJET);
			
			cst.setInt		(1, sujetPOJO.getSousCategoriePOJO().getID());
			cst.setString	(2, sujetPOJO.getTitre());
			cst.setDate		(3, (Date) sujetPOJO.getDateSujet());
			cst.setInt		(4, sujetPOJO.getUtilisateurPOJO().getID());
			
			cst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
	public void delete(SujetPOJO sujetPOJO) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.DELETESUJET);
			cst.setString	(1, sujetPOJO.getTitre());	
			cst.setDate		(2, sujetPOJO.getDateSujet());
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
	public void update(SujetPOJO sujetPOJO) {
		
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un sujet
			cst = connect.prepareCall(Sprocs.UPDATESUJET);

			cst.setInt		(1, sujetPOJO.getID());
			cst.setInt		(2, sujetPOJO.getSousCategoriePOJO().getID());
			cst.setString	(3, sujetPOJO.getTitre());
			cst.setDate		(4, (Date)sujetPOJO.getDateSujet());
			cst.setInt		(5, sujetPOJO.getUtilisateurPOJO().getID());
			
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
	public SujetPOJO find(int id) {
		CallableStatement 		cst 				= null;
		SujetPOJO	 	 	 	sujetPOJO		 	= null;
		DAO<UtilisateurPOJO> 	utilisateurDAO		= new DAOFactory().getUtilisateurDAO();
		DAO<SousCategoriePOJO> 	sousCategorieDAO 	= new DAOFactory().getSousCategorieDAO();
		
		try {
			cst = connect.prepareCall(Sprocs.SELECTSUJET);
			cst.setInt(1, id);
			
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.NUMERIC);
			cst.registerOutParameter(3, java.sql.Types.NUMERIC);
			cst.registerOutParameter(4, java.sql.Types.VARCHAR);
			cst.registerOutParameter(5, java.sql.Types.DATE);
			cst.registerOutParameter(6, java.sql.Types.NUMERIC);
	
			cst.executeUpdate();
			sujetPOJO = new SujetPOJO(
					id, 
					sousCategorieDAO.find	(cst.getInt(3)),
					cst.getString			(4), 
					cst.getDate				(5), 
					utilisateurDAO.find		(cst.getInt(6))
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
		return sujetPOJO;
	}

	@Override
	public ArrayList<SujetPOJO> getList() {
		SujetPOJO 					sujetPOJO 			= null;
		CallableStatement 			cst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<SujetPOJO> 		listSujet 			= new ArrayList<SujetPOJO>();
		DAO<UtilisateurPOJO> 	 	utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		DAO<SousCategoriePOJO> 		sousCategorieDAO 	= new DAOFactory().getSousCategorieDAO();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTSUJET);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);

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
			if (cst != null) {
				try {
					cst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listSujet;
	}
}
