package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.CategoriePOJO;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class CategorieDAO extends DAO<CategoriePOJO> {

	public CategorieDAO(Connection conn) { super(conn); }

	@Override
	public void create(CategoriePOJO categoriePOJO) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.INSERTCATEGORIE);
			
			cst.setString(1, categoriePOJO.getTitre());
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
	public void delete(CategoriePOJO categoriePOJO) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.DELETECATEGORIE);
			
			cst.setString(1, categoriePOJO.getTitre());
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
	public void update(CategoriePOJO categoriePOJO) {
		CallableStatement cst = null;
		try {
			cst = connect.prepareCall(Sprocs.UPDATECATEGORIE);
			cst.setInt		(1, categoriePOJO.getID());
			cst.setString	(2, categoriePOJO.getTitre());
			
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
	public CategoriePOJO find(int id) {
		CategoriePOJO 		categoriePOJO 	= null;
		CallableStatement 	cst 			= null;

		try {
			cst = connect.prepareCall(Sprocs.SELECTCATEGORIE);
			
			//J'insère le paramètre entrant
			cst.setInt(1, id);
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);

			cst.executeUpdate();
			
			categoriePOJO = new CategoriePOJO(
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
		return categoriePOJO;
	}

	@Override
	public ArrayList<CategoriePOJO> getList() {
		CategoriePOJO 				categoriePOJO 		= null;
		CallableStatement 			cst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<CategoriePOJO> 	listcategoriePOJO 	= new ArrayList<CategoriePOJO>();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTCATEGORIE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				categoriePOJO = new CategoriePOJO(rs.getInt("idCategorie"), rs.getString("titre"));
				listcategoriePOJO.add(categoriePOJO);
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
		return listcategoriePOJO;
	}
}
