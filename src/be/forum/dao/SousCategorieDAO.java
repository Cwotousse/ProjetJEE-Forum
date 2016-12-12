package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.CategoriePOJO;
import be.forum.pojo.SousCategoriePOJO;
import be.forum.sgbd.Sprocs;

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
			// On supprime les données nécessaires dans la table Utilisateur
			//pst = connect.prepareStatement("DELETE FROM SousCategorie WHERE titre = ?");

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
			//pst = connect.prepareStatement(
			//		"UPDATE SousCategorie SET idCategorie = ?, titre = ? WHERE idCategorie = ?");
			cst.setInt		(1, sousCategoriePOJO.getCategoriePOJO().getID());
			cst.setString	(2, sousCategoriePOJO.getTitre());
			cst.setInt		(1, sousCategoriePOJO.getID());
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
		SousCategoriePOJO 	 sousCategoriePOJO 	= null;
		CallableStatement cst = null;
		DAO<CategoriePOJO> 	 categorieDAO	 	= new DAOFactory().getCategorieDAO();
		
		try {
			//Appel de la procédure stockée pour trouver une sous catégorie
			cst = connect.prepareCall(Sprocs.SELECTSOUSCATEGORIE);
			//pst = this.connect.prepareStatement("SELECT * FROM Actualite WHERE idActualite = ?");
			//J'insère le paramètre entrant
			cst.setInt(1, id);
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);
			cst.registerOutParameter(3, java.sql.Types.VARCHAR);
			cst.executeQuery();
			
			sousCategoriePOJO = new SousCategoriePOJO(
				id,
				categorieDAO.find	(cst.getInt("idCategorie")),
				cst.getString		("titre")
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
		PreparedStatement 				pst 				= null;
		ResultSet 						rs 					= null;
		ArrayList<SousCategoriePOJO> 	listSousCategorie 	= new ArrayList<SousCategoriePOJO>();
		DAO<CategoriePOJO> 	 			categorieDAO 		= new DAOFactory().getCategorieDAO();
		try {
			pst = this.connect.prepareStatement("SELECT * FROM SousCategorie");
			rs = pst.executeQuery();
			while (rs.next()) {
				sousCategoriePOJO = new SousCategoriePOJO(
							rs.getInt			("idSousCategorie"), 
							categorieDAO.find	(rs.getInt("idCategorie")),
							rs.getString		("titre")
						);
				listSousCategorie.add(sousCategoriePOJO);
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
		return listSousCategorie;
	}
}
