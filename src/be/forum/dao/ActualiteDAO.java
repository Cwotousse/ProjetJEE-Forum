package be.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.ActualitePOJO;

public class ActualiteDAO extends DAO<ActualitePOJO> {

	public ActualiteDAO(Connection conn) { super(conn); }

	@Override
	public void create(ActualitePOJO actualitePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect
					.prepareStatement("INSERT INTO Actualite (idActualite, titre, description) " + "VALUES (?,?,?)");

			pst.setInt(1, actualitePOJO.getID());
			pst.setString(2, actualitePOJO.getTitre());
			pst.setString(3, actualitePOJO.getDescription());
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
	public void delete(ActualitePOJO actualitePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("DELETE FROM Actualite WHERE titre = ? AND description = ?");

			pst.setString(1, actualitePOJO.getTitre());
			pst.setString(2, actualitePOJO.getDescription());
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
	public void update(ActualitePOJO actualitePOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("UPDATE Actualite SET titre = ?, description = ? WHERE idActualite = ?");
			pst.setString(1, actualitePOJO.getTitre());
			pst.setString(2, actualitePOJO.getDescription());
			pst.setInt(3, actualitePOJO.getID());
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
	public ActualitePOJO find(int id) {
		ActualitePOJO 	 	 actualitePOJO 	= null;
		PreparedStatement 	 pst			= null;
		ResultSet			 rs				= null;
		
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Actualite WHERE idActualite = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				actualitePOJO = new ActualitePOJO(
										rs.getInt		("idActualite"),
										rs.getString 	("titre"),
										rs.getString	("description")
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
		return actualitePOJO;
	}

	@Override
	public ArrayList<ActualitePOJO> getList() {
		ActualitePOJO 				actualitePOJO 		= null;
		PreparedStatement 			pst 				= null;
		ResultSet 					rs 					= null;
		ArrayList<ActualitePOJO>	listActualitePOJO 	= new ArrayList<ActualitePOJO>();
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Actualite");
			rs = pst.executeQuery();
			while (rs.next()) {
				actualitePOJO = new ActualitePOJO(
							rs.getInt			("idActualite"), 
							rs.getString		("titre"),
							rs.getString		("description")
						);
				listActualitePOJO.add(actualitePOJO);
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
		return listActualitePOJO;
	}

}
