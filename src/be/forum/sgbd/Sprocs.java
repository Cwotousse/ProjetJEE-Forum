package be.forum.sgbd;

public class Sprocs {
	/**
	 * PROCEDURES STOCKEES POUR LA CLASSE UTILISATEURDAO
	 */
	public final static String INSERTUTILISATEUR 	= "{CALL INSERTUTILISATEUR(?,?,?,?,?,?,?)}";
	public final static String DELETEUTILISATEUR 	= "{CALL DELETEUTILISATEUR(?)}";
	public final static String UPDATEUTILISATEUR 	= "{CALL UPDATEUTILISATEUR(?,?,?,?,?,?,?)}";
	public final static String SELECTUTILISATEUR 	= "{CALL SELECTUTILISATEUR(?,?,?,?,?,?,?,?)}";
	
	/**
	 * PROCEDURES STOCKEES POUR LA CLASSE ACTUALITEDAO
	 */
	public final static String INSERTACTUALITE 		= "{CALL INSERTACTUALITE(?,?,?,?,?,?,?)}";
	public final static String DELETEACTUALITE 		= "{CALL DELETEACTUALITE(?,?,?,?,?,?,?)}";
	public final static String UPDATEACTUALITE 		= "{CALL UPDATEACTUALITE(?,?,?,?,?,?,?)}";
	public final static String SELECTACTUALITE 		= "{CALL SELECTACTUALITE(?,?,?,?,?,?,?)}";
	
	/**
	 * PROCEDURES STOCKEES POUR LA CLASSE CATEGORIEDAO
	 */
	public final static String INSERTCATEGORIE 		= "{CALL INSERTCATEGORIE(?)}";
	public final static String DELETECATEGORIE 		= "{CALL DELETECATEGORIE(?)}";
	public final static String UPDATECATEGORIE 		= "{CALL UPDATECATEGORIE(?,?)}";
	public final static String SELECTCATEGORIE 		= "{CALL SELECTCATEGORIE(?,?)}";
	
	/**
	 * PROCEDURES STOCKEES POUR LA CLASSE SOUSCATEGORIEDAO
	 */
	public final static String INSERTSOUSCATEGORIE 	= "{CALL INSERTSOUSCATEGORIE(?,?,?,?,?,?,?)}";
	public final static String DELETESOUSCATEGORIE 	= "{CALL DELETESOUSCATEGORIE(?,?,?,?,?,?,?)}";
	public final static String UPDATESOUSCATEGORIE 	= "{CALL UPDATESOUSCATEGORIE(?,?,?,?,?,?,?)}";
	public final static String SELECTSOUSCATEGORIE 	= "{CALL SELECTSOUSCATEGORIE(?,?,?,?,?,?,?)}";
	
	/**
	 * PROCEDURES STOCKEES POUR LA CLASSE SUJETDAO
	 */
	public final static String INSERTSUJET 			= "{CALL INSERTSUJET(?,?,?,?)}";//
	public final static String DELETESUJET 			= "{CALL DELETESUJET(?,?)}";//
	public final static String UPDATESUJET 			= "{CALL UPDATESUJET(?,?,?,?,?)}";//
	public final static String SELECTSUJET 			= "{CALL SELECTSUJET(?,?,?,?,?,?)}";//
	
	/**
	 * PROCEDURES STOCKEES POUR LA CLASSE COMMENTAIREDAO
	 */
	public final static String INSERTCOMMENTAIRE 	= "{CALL INSERTCOMMENTAIRE(?,?,?,?,?,?,?)}";
	public final static String DELETECOMMENTAIRE 	= "{CALL DELETECOMMENTAIRE(?,?,?,?,?,?,?)}";
	public final static String UPDATECOMMENTAIRE 	= "{CALL UPDATECOMMENTAIRE(?,?,?,?,?,?,?)}";
	public final static String SELECTCOMMENTAIRE 	= "{CALL SELECTCOMMENTAIRE(?,?,?,?,?,?,?)}";
	
	/**
	 * PROCEDURES STOCKEES POUR LA CLASSE HISTORIQUEDAO
	 */
	public final static String INSERTHISTORIQUE 	= "{CALL INSERTHISTORIQUE(?,?,?,?,?,?,?)}";
	public final static String DELETEHISTORIQUE 	= "{CALL DELETEHISTORIQUE(?,?,?,?,?,?,?)}";
	public final static String UPDATEHISTORIQUE 	= "{CALL UPDATEHISTORIQUE(?,?,?,?,?,?,?)}";
	public final static String SELECTHISTORIQUE 	= "{CALL SELECTHISTORIQUE(?,?,?,?,?,?,?)}";	
}
