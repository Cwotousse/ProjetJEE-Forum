package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.Categorie;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class CategorieDAO extends DAO<Categorie> {

	public CategorieDAO(Connection conn) { super(conn); }

	@Override
	public void create(Categorie categorie) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.INSERTCATEGORIE);
			
			cst.setString(1, categorie.getTitre());
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
	public void delete(Categorie categorie) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.DELETECATEGORIE);
			
			cst.setString(1, categorie.getTitre());
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
	public void update(Categorie categorie) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.UPDATECATEGORIE);
			cst.setInt		(1, categorie.getID());
			cst.setString	(2, categorie.getTitre());
			
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
	public Categorie find(int id) {
		Categorie 		categorie 	= null;
		CallableStatement 	cst 			= null;

		try {
			cst = connect.prepareCall(Sprocs.SELECTCATEGORIE);
			
			//J'insère le paramètre entrant
			cst.setInt(1, id);
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);

			cst.executeUpdate();
			
			categorie = new Categorie(
					id, 
					cst.getString(2)
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
		return categorie;
	}

	@Override
	public ArrayList<Categorie> getList() {
		Categorie 				categorie 		= null;
		CallableStatement 			cst 		= null;
		ResultSet 					rs 			= null;
		ArrayList<Categorie> 	listcategorie 	= new ArrayList<Categorie>();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTCATEGORIE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				categorie = new Categorie(rs.getInt("idCategorie"), rs.getString("titre"));
				listcategorie.add(categorie);
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
		return listcategorie;
	}
}
