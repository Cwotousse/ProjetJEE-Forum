package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.Actualite;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class ActualiteDAO extends DAO<Actualite> {

	public ActualiteDAO(Connection conn) { super(conn); }

	@Override
	public void create(Actualite actualite) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour créer une actualité
			cst = connect.prepareCall(Sprocs.INSERTACTUALITE);

			cst.setString	(1, actualite.getTitre());
			cst.setString	(2, actualite.getDescription());
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
	public void delete(Actualite actualite) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour supprimer une actualité
			cst = connect.prepareCall(Sprocs.DELETEACTUALITE);

			cst.setString(1, actualite.getTitre());
			cst.setString(2, actualite.getDescription());
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
	public void update(Actualite actualite) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier une actualite
			cst = connect.prepareCall(Sprocs.UPDATEACTUALITE);
			
			cst.setInt		(1, actualite.getID());
			cst.setString	(2, actualite.getTitre());
			cst.setString	(3, actualite.getDescription());
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
	public Actualite find(int id) {
		Actualite 	 	 actualite 	= null;
		CallableStatement cst = null;
		
		try {
			//Appel de la procédure stockée pour trouver une actualite
			cst = connect.prepareCall(Sprocs.SELECTACTUALITE);
			//pst = this.connect.prepareStatement("SELECT * FROM Actualite WHERE idActualite = ?");
			//J'insère le paramètre entrant
			cst.setInt(1, id);
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);
			cst.registerOutParameter(3, java.sql.Types.VARCHAR);
			cst.executeQuery();
			
			actualite = new Actualite(
					id,
					cst.getString 	(2),
					cst.getString	(3)
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
		return actualite;
	}

	@Override
	public ArrayList<Actualite> getList() {
		Actualite 				actualite 		= null;
		CallableStatement 			cst 		= null;
		ResultSet 					rs 			= null;
		ArrayList<Actualite>	listActualite 	= new ArrayList<Actualite>();
		try {
			cst = connect.prepareCall(Sprocs.GETLISTACTUALITE);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				actualite = new Actualite(
							rs.getInt			("idActualite"), 
							rs.getString		("titre"),
							rs.getString		("description")
						);
				listActualite.add(actualite);
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
		return listActualite;
	}

}
