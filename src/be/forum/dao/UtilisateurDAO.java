package be.forum.dao;

import java.sql.Connection;
import java.sql.Date;
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
	public void create(UtilisateurPOJO utilisateurPOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"INSERT INTO Utilisateur (pseudo, motdepasse, nom, prenom, dateNaissance, type, mail) "
							+ "VALUES (?,?,?,?,?,?,?)");

			pst.setString(1, utilisateurPOJO.getPseudo());
			pst.setString(2, utilisateurPOJO.getMotdepasse());
			pst.setString(3, utilisateurPOJO.getNom());
			pst.setString(4, utilisateurPOJO.getPrenom());
			pst.setDate(5, (Date) utilisateurPOJO.getDateNaissance());
			pst.setString(6, utilisateurPOJO.getType());
			pst.setString(7, utilisateurPOJO.getMail());

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
	public void delete(UtilisateurPOJO utilisateurPOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement("DELETE FROM Utilisateur WHERE pseudo = ?");

			pst.setString(1, utilisateurPOJO.getPseudo());
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
	public void update(UtilisateurPOJO utilisateurPOJO) {
		PreparedStatement pst = null;
		try {
			pst = connect.prepareStatement(
					"UPDATE Utilisateur SET motdepasse = ?, nom = ?, prenom = ?, dateNaissance = ?, type = ?, mail = ? WHERE pseudo = ?");
			pst.setString	(1, utilisateurPOJO.getMotdepasse());
			pst.setString	(2, utilisateurPOJO.getNom());
			pst.setString	(3, utilisateurPOJO.getPrenom());
			pst.setDate		(4, (Date) utilisateurPOJO.getDateNaissance());
			pst.setString	(5, utilisateurPOJO.getType());
			pst.setString	(6, utilisateurPOJO.getMail());
			pst.setString	(7, utilisateurPOJO.getPseudo());
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
	public UtilisateurPOJO find(int id) {
		UtilisateurPOJO 	utilisateurPOJO = null;
		PreparedStatement 	pst 			= null;
		ResultSet 			rs 				= null;
		try {
			pst = this.connect.prepareStatement("SELECT * FROM Utilisateur WHERE idUtilisateur = ?");
			pst.setInt(1, id);
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
		return utilisateurPOJO;
	}

	/**
	 * Récupère la liste des utilisateurs présents dans la base de données
	 * 
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