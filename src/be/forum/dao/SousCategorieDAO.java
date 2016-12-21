package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.CategoriePOJO;
import be.forum.pojo.SousCategoriePOJO;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class SousCategorieDAO extends DAO<SousCategoriePOJO>{

	public SousCategorieDAO(Connection conn) { super(conn); }

	@Override
	public void create(SousCategoriePOJO sousCategoriePOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour créer une actualité
			cst = connect.prepareCall(Sprocs.INSERTSOUSCATEGORIE);

			cst.setInt		(1, sousCategoriePOJO.getCategoriePOJO().getID());
			cst.setString	(2, sousCategoriePOJO.getTitre());
			cst.setString	(3, sousCategoriePOJO.getIcone());
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
	public void delete(SousCategoriePOJO sousCategoriePOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour supprimer une sous catégorie
			cst = connect.prepareCall(Sprocs.DELETESOUSCATEGORIE);
			cst.setString(1, sousCategoriePOJO.getTitre());
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
	public void update(SousCategoriePOJO sousCategoriePOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier une sous catégorie
			cst = connect.prepareCall(Sprocs.UPDATESOUSCATEGORIE);
			
			cst.setInt		(1, sousCategoriePOJO.getID());
			cst.setInt		(2, sousCategoriePOJO.getCategoriePOJO().getID());
			cst.setString	(3, sousCategoriePOJO.getTitre());
			cst.setString	(4, sousCategoriePOJO.getIcone());
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
	public SousCategoriePOJO find(int id) {
		SousCategoriePOJO 	sousCategoriePOJO 	= null;
		CallableStatement 	cst = null;
		DAO<CategoriePOJO> 	categorieDAO	 	= new DAOFactory().getCategorieDAO();
		
		try {
			//Appel de la procédure stockée pour trouver une sous catégorie
			cst = connect.prepareCall(Sprocs.SELECTSOUSCATEGORIE);
			//J'insère le paramètre entrant
			cst.setInt(1, id);
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.NUMERIC);
			cst.registerOutParameter(3, java.sql.Types.VARCHAR);
			cst.registerOutParameter(4, java.sql.Types.VARCHAR);
			cst.executeQuery();
			
			sousCategoriePOJO = new SousCategoriePOJO(
				id,
				categorieDAO.find	(cst.getInt(2)),
				cst.getString		(3),
				cst.getString		(4)	
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
		return sousCategoriePOJO;
	}

	@Override
	public ArrayList<SousCategoriePOJO> getList() {
		SousCategoriePOJO 				sousCategoriePOJO 	= null;
		CallableStatement 				cst 				= null;
		ResultSet 						rs 					= null;
		ArrayList<SousCategoriePOJO> 	listSousCategorie 	= new ArrayList<SousCategoriePOJO>();
		DAO<CategoriePOJO> 	 			categorieDAO 		= new DAOFactory().getCategorieDAO();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTSOUSCATEGORIE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				sousCategoriePOJO = new SousCategoriePOJO(
							rs.getInt			("idSousCategorie"), 
							categorieDAO.find	(rs.getInt("idCategorie")),
							rs.getString		("titre"),
							rs.getString		("icone")
						);
				listSousCategorie.add(sousCategoriePOJO);
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
		return listSousCategorie;
	}
}
