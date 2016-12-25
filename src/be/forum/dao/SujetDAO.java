package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.SousCategorie;
import be.forum.pojo.Sujet;
import be.forum.pojo.Utilisateur;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class SujetDAO extends DAO<Sujet> {

	public SujetDAO(Connection conn) { super(conn); }

	@Override
	public void create(Sujet sujet) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.INSERTSUJET);
			cst.setInt		(1, sujet.getSousCategorie().getID());
			cst.setString	(2, sujet.getTitre());
			cst.setDate		(3, (Date) sujet.getDateSujet());
			cst.setInt		(4, sujet.getUtilisateur().getID());
			
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
	public void delete(Sujet sujet) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.DELETESUJET);
			cst.setString	(1, sujet.getTitre());	
			cst.setDate		(2, sujet.getDateSujet());
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
	public void update(Sujet sujet) {
		
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un sujet
			cst = connect.prepareCall(Sprocs.UPDATESUJET);

			cst.setInt		(1, sujet.getID());
			cst.setInt		(2, sujet.getSousCategorie().getID());
			cst.setString	(3, sujet.getTitre());
			cst.setDate		(4, (Date)sujet.getDateSujet());
			cst.setInt		(5, sujet.getUtilisateur().getID());
			
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
	public Sujet find(int id) {
		CallableStatement 		cst 				= null;
		Sujet	 	 	 	sujet		 	= null;
		DAO<Utilisateur> 	utilisateurDAO		= new DAOFactory().getUtilisateurDAO();
		DAO<SousCategorie> 	sousCategorieDAO 	= new DAOFactory().getSousCategorieDAO();
		
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
			sujet = new Sujet(
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
		return sujet;
	}

	@Override
	public ArrayList<Sujet> getList() {
		Sujet 					sujet 			= null;
		CallableStatement 			cst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<Sujet> 		listSujet 			= new ArrayList<Sujet>();
		DAO<Utilisateur> 	 	utilisateurDAO 		= new DAOFactory().getUtilisateurDAO();
		DAO<SousCategorie> 		sousCategorieDAO 	= new DAOFactory().getSousCategorieDAO();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTSUJET);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);

			while (rs.next()) {
				sujet = new Sujet(
								rs.getInt				("idSujet"),
								sousCategorieDAO.find	(rs.getInt("idSousCategorie")),
								rs.getString			("titre"),
								rs.getDate				("dateSujet"),
								utilisateurDAO.find		(rs.getInt("idUtilisateur"))
							);
				listSujet.add(sujet);
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
