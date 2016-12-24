package be.forum.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.forum.pojo.Utilisateur;
import be.forum.sgbd.Sprocs;
import oracle.jdbc.OracleTypes;

public class UtilisateurDAO extends DAO<Utilisateur> {
	public UtilisateurDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Utilisateur utilisateur) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour ajouter un utilisateur
			cst = connect.prepareCall(Sprocs.INSERTUTILISATEUR);
			
			cst.setString	(1, utilisateur.getPseudo());
			cst.setString	(2, utilisateur.getMotdepasse());
			cst.setString	(3, utilisateur.getNom());
			cst.setString	(4, utilisateur.getPrenom());
			cst.setDate		(5, (Date) utilisateur.getDateNaissance());
			cst.setString	(6, utilisateur.getType());
			cst.setString	(7, utilisateur.getMail());
			
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
	public void delete(Utilisateur utilisateur) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour supprimer un utilisateur
			cst = connect.prepareCall(Sprocs.DELETEUTILISATEUR);
			cst.setString(1, utilisateur.getPseudo());	
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
	public void update(Utilisateur utilisateur) {
		CallableStatement cst = null;
		try {
			//Appel de la procédure stockée pour modifier un utilisateur
			cst = connect.prepareCall(Sprocs.UPDATEUTILISATEUR);
			
			cst.setString	(1, utilisateur.getPseudo());
			cst.setString	(2, utilisateur.getMotdepasse());
			cst.setString	(3, utilisateur.getNom());
			cst.setString	(4, utilisateur.getPrenom());
			cst.setDate		(5, (Date) utilisateur.getDateNaissance());
			cst.setString	(6, utilisateur.getType());
			cst.setString	(7, utilisateur.getMail());
			cst.setInt		(8, utilisateur.getID());
			
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
	public Utilisateur find(int id) {
		Utilisateur 	utilisateur = null;
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
			
			utilisateur = new Utilisateur(
					id, 
					cst.getString	(2),
					cst.getString	(3), 
					cst.getString	(4), 
					cst.getString	(5),
					cst.getDate		(6), 
					cst.getString	(8), 
					cst.getString	(7)
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
		return utilisateur;
	}

	/**
	 * Récupère la liste des utilisateurs présents dans la base de données
	 * 
	 * @return listUtilisateur : liste des utilisateurs
	 */
	@Override
	public ArrayList<Utilisateur> getList() {
		Utilisateur 			utilisateur = null;
		ArrayList<Utilisateur> 	listUtilisateur = new ArrayList<Utilisateur>();
		CallableStatement			cst				= null;
		ResultSet 					rs 				= null;
		try {
			cst = connect.prepareCall(Sprocs.GETLISTUTILISATEUR);

			cst.registerOutParameter(1, OracleTypes.CURSOR);
			cst.executeUpdate();

			// On récupère le curseur et on le cast à ResultSet
			rs = (ResultSet) cst.getObject(1);
			while (rs.next()) {
				utilisateur = new Utilisateur(
							rs.getInt	("idUtilisateur"), 
							rs.getString("pseudo"),
							rs.getString("motdepasse"), 
							rs.getString("nom"), 
							rs.getString("prenom"),
							rs.getDate	("dateNaissance"), 
							rs.getString("mail"),
							rs.getString("typeUtilisateur")
						);
				listUtilisateur.add(utilisateur);
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
		return listUtilisateur;
	}
}