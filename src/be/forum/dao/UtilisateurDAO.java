package be.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.UtilisateurPOJO;

public class UtilisateurDAO extends DAO<UtilisateurPOJO> {
	public UtilisateurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(UtilisateurPOJO obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(UtilisateurPOJO obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(UtilisateurPOJO obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public UtilisateurPOJO find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Récupère la liste des utilisateurs présents dans la base de données
	 * @return listUtilisateur : liste des utilisateurs
	 */
	@Override
	public ArrayList<UtilisateurPOJO> getList() {
		UtilisateurPOJO 			utilisateurPOJO = null;
		ArrayList<UtilisateurPOJO> 	listUtilisateur = new ArrayList<UtilisateurPOJO>();
		PreparedStatement 			pst 			= null;
		ResultSet 					rs 				= null;
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Utilisateur");
			rs = pst.executeQuery();
			while (rs.next()) {
				utilisateurPOJO = new UtilisateurPOJO(
									rs.getInt	("idUtilisateur"),
									rs.getString("pseudo"),
									rs.getString("motdepasse"),
									rs.getString("nom"),
									rs.getString("prenom"),
									rs.getDate	("dateNaissance"),
									rs.getString("type"),
									rs.getString("mail")
									);
				listUtilisateur.add(utilisateurPOJO);
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
		return listUtilisateur;
	}
}