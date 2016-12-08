package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.UtilisateurPOJO;
import be.forum.sgbd.Sprocs;

public class UtilisateurDAO extends DAO<UtilisateurPOJO> {
	public UtilisateurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(UtilisateurPOJO utilisateurPOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour ajouter un utilisateur
			cst = connect.prepareCall(Sprocs.INSERTUTILISATEUR);
			
			cst.setString	(1, utilisateurPOJO.getPseudo());
			cst.setString	(2, utilisateurPOJO.getMotdepasse());
			cst.setString	(3, utilisateurPOJO.getNom());
			cst.setString	(4, utilisateurPOJO.getPrenom());
			cst.setDate		(5, (Date) utilisateurPOJO.getDateNaissance());
			cst.setString	(6, utilisateurPOJO.getType());
			cst.setString	(7, utilisateurPOJO.getMail());
			
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
	public void delete(UtilisateurPOJO utilisateurPOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour supprimer un utilisateur
			cst = connect.prepareCall(Sprocs.DELETEUTILISATEUR);
			cst.setString	(1, utilisateurPOJO.getPseudo());	
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
	public void update(UtilisateurPOJO utilisateurPOJO) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.UPDATEUTILISATEUR);
			
			cst.setString	(1, utilisateurPOJO.getMotdepasse());
			cst.setString	(2, utilisateurPOJO.getNom());
			cst.setString	(3, utilisateurPOJO.getPrenom());
			cst.setDate		(4, (Date) utilisateurPOJO.getDateNaissance());
			cst.setString	(5, utilisateurPOJO.getType());
			cst.setString	(6, utilisateurPOJO.getMail());
			cst.setString	(7, utilisateurPOJO.getPseudo());
			
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
	public UtilisateurPOJO find(int id) {
		UtilisateurPOJO 	utilisateurPOJO = null;
		CallableStatement 	cst 			= null;
		try {
			//Appel de la procédure stockée pour sélectionner un utilisateur
			cst = connect.prepareCall(Sprocs.SELECTUTILISATEUR);
			
			//J'insère le paramètre entrant
			cst.setInt(1, id);
			//Je récupère les paramètres sortants de la procédures stockées
			cst.registerOutParameter(2, java.sql.Types.VARCHAR);
			cst.registerOutParameter(3, java.sql.Types.VARCHAR);
			cst.registerOutParameter(4, java.sql.Types.VARCHAR);
			cst.registerOutParameter(5, java.sql.Types.VARCHAR);
			cst.registerOutParameter(6, java.sql.Types.DATE);
			cst.registerOutParameter(7, java.sql.Types.VARCHAR);
			cst.registerOutParameter(8, java.sql.Types.VARCHAR);

			cst.executeUpdate();
			
			utilisateurPOJO = new UtilisateurPOJO(
					id, 
					cst.getString	("pseudo"),
					cst.getString	("motdepasse"), 
					cst.getString	("nom"), 
					cst.getString	("prenom"),
					cst.getDate		("dateNaissance"), 
					cst.getString	("type"), 
					cst.getString	("mail")
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