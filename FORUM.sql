/*
  USER  : exa2
  MDP   : tribuLugubre
  HOTE  : char-oracle11.condorcet.be
*/
/*
  CREATE USER forum IDENTIFIED BY pwd;
  GRANT CONNECT, RESOURCE, DBA to forum;
*/

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
CREATE TABLE UTILISATEUR  (
   IDUTILISATEUR        INTEGER                    NOT NULL,
   PSEUDO               VARCHAR2(100),
   MOTDEPASSE           VARCHAR2(100),
   NOM                  VARCHAR2(100),
   PRENOM               VARCHAR2(100),
   DATENAISSANCE        DATE,
   TYPEUTILISATEUR      VARCHAR2(100),
   MAIL                 VARCHAR2(100),
   CONSTRAINT PK_UTILISATEUR PRIMARY KEY (IDUTILISATEUR)
);

--insert into UTILISATEUR values(SEQ_UTILISATEUR.nextval, '...');
/*==============================================================*/
/* Table : HISTORIQUE                                           */
/*==============================================================*/
CREATE TABLE HISTORIQUE  (
   IDHISTORIQUE         INTEGER                         NOT NULL,
   DATECONNEXION        DATE,
   IDUTILISATEUR        INTEGER                         NOT NULL,
   CONSTRAINT PK_HISTORIQUE PRIMARY KEY (IDHISTORIQUE)
);

/*==============================================================*/
/* Table : CATEGORIE                                           */
/*==============================================================*/
CREATE TABLE CATEGORIE  (
   IDCATEGORIE          INTEGER                         NOT NULL,
   TITRE                VARCHAR2(100),
   CONSTRAINT PK_CATEGORIE PRIMARY KEY (IDCATEGORIE)
);

/*==============================================================*/
/* Table : SOUSCATEGORIE                                        */
/*==============================================================*/
CREATE TABLE SOUSCATEGORIE  (
   IDSOUSCATEGORIE      INTEGER                         NOT NULL,
   IDCATEGORIE          INTEGER                         NOT NULL,
   TITRE                VARCHAR2(100),
   CONSTRAINT PK_SOUSCATEGORIE PRIMARY KEY (IDSOUSCATEGORIE)
   
);

/*==============================================================*/
/* Table : SUJET                                                */
/*==============================================================*/
CREATE TABLE SUJET  (
   IDSUJET              INTEGER                         NOT NULL,
   IDSOUSCATEGORIE      INTEGER                         NOT NULL,
   TITRE                VARCHAR2(200),
   DATESUJET            DATE,
   IDUTILISATEUR        INTEGER                         NOT NULL,
   CONSTRAINT PK_SUJET PRIMARY KEY (IDSUJET)
);

/*==============================================================*/
/* Table : COMMENTAIRE                                          */
/*==============================================================*/
CREATE TABLE COMMENTAIRE  (
   IDCOMMENTAIRE        INTEGER                         NOT NULL,
   IDSUJET              INTEGER                         NOT NULL,
   TEXTE                VARCHAR2(1000),
   DATECOMMENTAIRE      DATE,
   IDUTILISATEUR        INTEGER                         NOT NULL,
   CONSTRAINT PK_COMMENTAIRE PRIMARY KEY (IDCOMMENTAIRE)
);

/*==============================================================*/
/* Table : ACTUALITE                                            */
/*==============================================================*/
CREATE TABLE ACTUALITE  (
   IDACTUALITE          INTEGER                         NOT NULL,
   TITRE                VARCHAR2(100),
   DESCRIPTION          VARCHAR2(1000),
   IDCATEGORIE          INTEGER                         NOT NULL,
   CONSTRAINT PK_ACTUALITE PRIMARY KEY (IDACTUALITE)
);

/*==============================================================*/
/* FOREIGN KEY                                                  */
/*==============================================================*/

ALTER TABLE HISTORIQUE
   ADD CONSTRAINT FK_HISTORIQUE_UTILISATEUR FOREIGN KEY (IDUTILISATEUR)
      REFERENCES UTILISATEUR (IDUTILISATEUR)
      ON DELETE CASCADE;

ALTER TABLE SOUSCATEGORIE
   ADD CONSTRAINT FK_SOUSCATEGORIE_CATEGORIE FOREIGN KEY (IDCATEGORIE)
      REFERENCES CATEGORIE (IDCATEGORIE)
      ON DELETE CASCADE;
      
ALTER TABLE SUJET
   ADD CONSTRAINT FK_SUJET_SOUSCATEGORIE FOREIGN KEY (IDSOUSCATEGORIE)
      REFERENCES SOUSCATEGORIE (IDSOUSCATEGORIE)
      ON DELETE CASCADE;
      
ALTER TABLE SUJET
   ADD CONSTRAINT FK_SUJET_UTILISATEUR FOREIGN KEY (IDUTILISATEUR)
      REFERENCES UTILISATEUR (IDUTILISATEUR)
      ON DELETE CASCADE;
      
ALTER TABLE COMMENTAIRE
   ADD CONSTRAINT FK_COMMENTAIRE_SUJET FOREIGN KEY (IDSUJET)
      REFERENCES SUJET (IDSUJET)
      ON DELETE CASCADE;
      
ALTER TABLE COMMENTAIRE
   ADD CONSTRAINT FK_COMMENTAIRE_UTILISATEUR FOREIGN KEY (IDUTILISATEUR)
      REFERENCES UTILISATEUR (IDUTILISATEUR)
      ON DELETE CASCADE;
      
ALTER TABLE ACTUALITE
   ADD CONSTRAINT FK_ACTUALITE_CATEGORIE FOREIGN KEY (IDCATEGORIE)
      REFERENCES CATEGORIE (IDCATEGORIE)
      ON DELETE CASCADE;
      
/*==============================================================*/
/* SEQUENCES                                                    */
/*==============================================================*/

--ON CREE UNE SEQUENCE POUR L'ID
CREATE SEQUENCE SEQ_UTILISATEUR   START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_HISTORIQUE    START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_CATEGORIE     START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_SOUSCATEGORIE START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_SUJET         START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_COMMENTAIRE   START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_ACTUALITE     START WITH 1 INCREMENT BY 1;

/*==============================================================*/
/* INSERT INTO                                                  */
/*==============================================================*/

INSERT INTO UTILISATEUR VALUES(SEQ_UTILISATEUR.NEXTVAL, 'Adista', 'test', 'DI STASIO', 'Anthony', '09/07/1994', 'Admin', 'anthony.distasio@hotmail.com');
INSERT INTO UTILISATEUR VALUES(SEQ_UTILISATEUR.NEXTVAL, 'Cwotousse', 'test', 'MOUSTY', 'Adrien', '16/12/1992', 'Admin', 'moustyadrien@hotmail.com');

INSERT INTO CATEGORIE VALUES(SEQ_CATEGORIE.NEXTVAL, 'Sports');
INSERT INTO CATEGORIE VALUES(SEQ_CATEGORIE.NEXTVAL, 'Jeux vidéos');
INSERT INTO CATEGORIE VALUES(SEQ_CATEGORIE.NEXTVAL, 'Technologie');
INSERT INTO CATEGORIE VALUES(SEQ_CATEGORIE.NEXTVAL, 'Blabla');

INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 1, 'Football');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 1, 'Cyclisme');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 1, 'Handball');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 1, 'Basketball');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 1, 'Boxe');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 1, 'Hockey');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 2, 'PS4');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 2, 'XBOX One');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 2, 'PC');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 2, 'IOS');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 2, 'Android');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 2, 'Wii U');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 2, 'PS3');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 3, 'Programmation');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 3, 'Réseaux');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 3, 'Domotique');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 4, '-15');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 4, '15-18');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 4, '18-25');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 4, '25-35');
INSERT INTO SOUSCATEGORIE VALUES(SEQ_SOUSCATEGORIE.NEXTVAL, 4, '+35');

/*==============================================================*/
/* PROCEDURES STOCKEES - UTILISATEUR                            */
/*==============================================================*/
create or replace PROCEDURE INSERTUTILISATEUR(
	   P_PSEUDO IN UTILISATEUR.PSEUDO%TYPE,
	   P_MOTDEPASSE IN UTILISATEUR.MOTDEPASSE%TYPE,
     P_NOM IN UTILISATEUR.NOM%TYPE,
     P_PRENOM IN UTILISATEUR.PRENOM%TYPE,
     P_DATENAISSANCE IN UTILISATEUR.DATENAISSANCE%TYPE,
     P_TYPE IN UTILISATEUR.TYPEUTILISATEUR%TYPE,
	   P_MAIL IN UTILISATEUR.MAIL%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    parametre_null exception;
  BEGIN
    -- Si les valeurs sont nulles on throw une exception
    IF P_PSEUDO IS NULL OR P_MOTDEPASSE IS NULL OR P_NOM IS NULL OR P_PRENOM IS NULL 
    OR P_DATENAISSANCE IS NULL OR P_TYPE IS NULL OR P_MAIL IS NULL THEN
      -- Déclenche l'exception
      RAISE parametre_null; 
    ELSE
      -- Sinon on commit
      INSERT INTO UTILISATEUR
  VALUES (SEQ_UTILISATEUR.NEXTVAL, P_PSEUDO, P_MOTDEPASSE, P_NOM, P_PRENOM, P_DATENAISSANCE, P_TYPE, P_MAIL);
    END IF;
    
    EXCEPTION
      WHEN parametre_null THEN
      DBMS_OUTPUT.PUT_LINE('les parametres sont nulls. (UTILISATEUR)');
    END; 
  COMMIT;
END;
/

create or replace PROCEDURE DELETEUTILISATEUR(
	   P_PSEUDO IN UTILISATEUR.PSEUDO%TYPE)
IS
BEGIN
  DELETE FROM UTILISATEUR
  WHERE P_PSEUDO = PSEUDO;
  
  COMMIT;
END;
/

create or replace PROCEDURE UPDATEUTILISATEUR(
	   P_MOTDEPASSE IN UTILISATEUR.MOTDEPASSE%TYPE,
     P_NOM IN UTILISATEUR.NOM%TYPE,
     P_PRENOM IN UTILISATEUR.PRENOM%TYPE,
     P_DATENAISSANCE IN UTILISATEUR.DATENAISSANCE%TYPE,
     P_TYPE IN UTILISATEUR.TYPEUTILISATEUR%TYPE,
	   P_MAIL IN UTILISATEUR.MAIL%TYPE,
     P_PSEUDO IN UTILISATEUR.PSEUDO%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    idIncorrect exception;
    nbrID number := 0;
  BEGIN
    -- Si l'id est inconnu, on throw une exception
    SELECT COUNT(*) INTO nbrID FROM UTILISATEUR WHERE PSEUDO = P_PSEUDO;
    IF nbrID != 1 then 
      -- Déclenche l'exception s'il y a 0 ou > 1 id correspondant
      RAISE idIncorrect; 
    ELSE
      UPDATE UTILISATEUR
      SET MOTDEPASSE = P_MOTDEPASSE, NOM = P_NOM, PRENOM = P_PRENOM, DATENAISSANCE = P_DATENAISSANCE, TYPEUTILISATEUR = P_TYPE, MAIL = P_MAIL
      WHERE PSEUDO = P_PSEUDO;
    END IF;
    
    EXCEPTION
      WHEN idIncorrect THEN
      DBMS_OUTPUT.PUT_LINE('l''id n''existe pas dans la base de donnees (UTILISATEUR).');
  END;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE SELECTUTILISATEUR(
     -- PARAMETRE ENTRANT
	   P_IDUTILISATEUR IN UTILISATEUR.IDUTILISATEUR%TYPE,
     -- LES PARAMETRES SORTANTS
	   O_PSEUDO OUT UTILISATEUR.PSEUDO%TYPE,
	   O_MOTDEPASSE OUT UTILISATEUR.MOTDEPASSE%TYPE,
	   O_NOM OUT UTILISATEUR.NOM%TYPE,
     O_PRENOM OUT UTILISATEUR.PRENOM%TYPE,
     O_DATENAISSANCE OUT UTILISATEUR.DATENAISSANCE%TYPE,
     O_TYPE OUT UTILISATEUR.TYPEUTILISATEUR%TYPE,
     O_MAIL OUT UTILISATEUR.MAIL%TYPE)
IS
BEGIN
  SELECT PSEUDO, MOTDEPASSE, NOM, PRENOM, DATENAISSANCE, TYPEUTILISATEUR, MAIL
  INTO O_PSEUDO,  O_MOTDEPASSE, O_NOM, O_PRENOM, O_DATENAISSANCE, O_TYPE, O_MAIL
  FROM UTILISATEUR WHERE IDUTILISATEUR = P_IDUTILISATEUR;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (UTILISATEUR)');
END;
/

create or replace PROCEDURE GETLISTUTILISATEUR(
	   CUR_UTILISATEUR OUT SYS_REFCURSOR)
IS
BEGIN
  --On crée un curseur
  OPEN CUR_UTILISATEUR FOR
  SELECT * FROM UTILISATEUR;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (UTILISATEUR)');
END;
/

/*==============================================================*/
/* PROCEDURES STOCKEES - ACTUALITE                              */
/*==============================================================*/
create or replace PROCEDURE INSERTACTUALITE(
	   P_TITRE IN ACTUALITE.TITRE%TYPE,
	   P_DESCRIPTION IN ACTUALITE.DESCRIPTION%TYPE,
     P_IDCATEGORIE IN ACTUALITE.IDCATEGORIE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    parametre_null exception;
  BEGIN
    -- Si les valeurs sont nulles on throw une exception
    IF P_TITRE IS NULL OR P_DESCRIPTION IS NULL THEN
      -- Déclenche l'exception
      RAISE parametre_null; 
    ELSE
      -- Sinon on commit
      INSERT INTO ACTUALITE
      VALUES (SEQ_ACTUALITE.NEXTVAL, P_TITRE, P_DESCRIPTION, P_IDCATEGORIE);
    END IF;
    
    EXCEPTION
      WHEN parametre_null THEN
      DBMS_OUTPUT.PUT_LINE('les parametres sont nulls. (ACTUALITE)');
    END; 
  COMMIT;
END;
/
create or replace PROCEDURE DELETEACTUALITE(
	   P_TITRE IN ACTUALITE.TITRE%TYPE,
     P_DESCRIPTION IN ACTUALITE.DESCRIPTION%TYPE)
IS
BEGIN
  DELETE FROM ACTUALITE
  WHERE P_TITRE = TITRE AND P_DESCRIPTION = DESCRIPTION;
  
  COMMIT;
END;
/
create or replace PROCEDURE UPDATEACTUALITE(
     P_IDACTUALITE IN ACTUALITE.IDACTUALITE%TYPE,
	   P_TITRE IN ACTUALITE.TITRE%TYPE,
     P_DESCRIPTION IN ACTUALITE.DESCRIPTION%TYPE,
     P_IDCATEGORIE IN ACTUALITE.IDCATEGORIE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    idIncorrect exception;
    nbrID number := 0;
  BEGIN
    -- Si l'id est inconnu, on throw une exception
    SELECT COUNT(*) INTO nbrID FROM ACTUALITE WHERE IDACTUALITE = P_IDACTUALITE;
    IF nbrID != 1 then 
      -- Déclenche l'exception s'il y a 0 ou > 1 id correspondant
      RAISE idIncorrect; 
    ELSE
      UPDATE ACTUALITE
      SET TITRE = P_TITRE, DESCRIPTION = P_DESCRIPTION
      WHERE IDACTUALITE = P_IDACTUALITE;
    END IF;
    
    EXCEPTION
      WHEN idIncorrect THEN
      DBMS_OUTPUT.PUT_LINE('l''id n''existe pas dans la base de donnees (ACTUALITE).');
  END;
  COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE SELECTACTUALITE(
     -- PARAMETRE ENTRANT
	   P_IDACTUALITE IN ACTUALITE.IDACTUALITE%TYPE,
     -- LES PARAMETRES SORTANTS
	   O_TITRE OUT ACTUALITE.TITRE%TYPE,
	   O_DESCRIPTION OUT ACTUALITE.DESCRIPTION%TYPE,
     O_IDCATEGORIE OUT ACTUALITE.IDCATEGORIE%TYPE)
IS
BEGIN
  SELECT TITRE, DESCRIPTION, IDCATEGORIE
  INTO O_TITRE,  O_DESCRIPTION, O_IDCATEGORIE
  FROM ACTUALITE WHERE IDACTUALITE = P_IDACTUALITE;
END;
/
create or replace PROCEDURE GETLISTACTUALITE(
	   CUR_ACTUALITE OUT SYS_REFCURSOR)
IS
BEGIN
  --On crée un curseur
  OPEN CUR_ACTUALITE FOR
  SELECT * FROM ACTUALITE;
  CLOSE CUR_ACTUALITE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (ACTUALITE)');
END;
/

/*==============================================================*/
/* PROCEDURES STOCKEES - CATEGORIE                              */
/*==============================================================*/
create or replace PROCEDURE INSERTCATEGORIE(
	   P_TITRE IN CATEGORIE.TITRE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    parametre_null exception;
  BEGIN
    -- Si les valeurs sont nulles on throw une exception
    IF P_TITRE IS NULL THEN
      -- Déclenche l'exception
      RAISE parametre_null; 
    ELSE
      -- Sinon on commit
      INSERT INTO CATEGORIE
      VALUES (SEQ_CATEGORIE.NEXTVAL, P_TITRE);
    END IF;
    
    EXCEPTION
      WHEN parametre_null THEN
      DBMS_OUTPUT.PUT_LINE('les parametres sont nulls. (CATEGORIE)');
    END; 
  COMMIT;
END;
/

create or replace PROCEDURE DELETECATEGORIE(
	   P_TITRE IN CATEGORIE.TITRE%TYPE)
IS
BEGIN
  DELETE FROM CATEGORIE
  WHERE P_TITRE = TITRE;
  
  COMMIT;
END;
/

create or replace PROCEDURE UPDATECATEGORIE(
     P_IDCATEGORIE IN CATEGORIE.IDCATEGORIE%TYPE,
	   P_TITRE IN CATEGORIE.TITRE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    idIncorrect exception;
    nbrID number := 0;
  BEGIN
    -- Si l'id est inconnu, on throw une exception
    SELECT COUNT(*) INTO nbrID FROM CATEGORIE WHERE IDCATEGORIE = P_IDCATEGORIE;
    IF nbrID != 1 then 
      -- Déclenche l'exception s'il y a 0 ou > 1 id correspondant
      RAISE idIncorrect; 
    ELSE
      UPDATE CATEGORIE
      SET TITRE = P_TITRE
      WHERE IDCATEGORIE = P_IDCATEGORIE;
    END IF;
    
    EXCEPTION
      WHEN idIncorrect THEN
      DBMS_OUTPUT.PUT_LINE('l''id n''existe pas dans la base de donnees (CATEGORIE).');
  END;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE SELECTCATEGORIE(
     -- PARAMETRE ENTRANT
	   P_IDCATEGORIE IN CATEGORIE.IDCATEGORIE%TYPE,
     -- LES PARAMETRES SORTANTS
	   O_TITRE OUT CATEGORIE.TITRE%TYPE)
IS
BEGIN
  SELECT TITRE
  INTO O_TITRE
  FROM CATEGORIE WHERE IDCATEGORIE = P_IDCATEGORIE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (CATEGORIE)');
END;
/

create or replace PROCEDURE GETLISTCATEGORIE(
	   CUR_CATEGORIE OUT SYS_REFCURSOR)
IS
BEGIN
  --On crée un curseur
  OPEN CUR_CATEGORIE FOR
  SELECT * FROM CATEGORIE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (CATEGORIE)');
END;
/

/*==============================================================*/
/* PROCEDURES STOCKEES - SOUSCATEGORIE                          */
/*==============================================================*/
create or replace PROCEDURE INSERTSOUSCATEGORIE(
     P_IDCATEGORIE IN SOUSCATEGORIE.IDCATEGORIE%TYPE,
	   P_TITRE IN SOUSCATEGORIE.TITRE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    parametre_null exception;
  BEGIN
    -- Si les valeurs sont nulles on throw une exception
    IF P_IDCATEGORIE IS NULL OR P_TITRE IS NULL THEN
      -- Déclenche l'exception
      RAISE parametre_null; 
    ELSE
      -- Sinon on commit
      INSERT INTO SOUSCATEGORIE
      VALUES (SEQ_SOUSCATEGORIE.NEXTVAL, P_IDCATEGORIE, P_TITRE);
    END IF;
    
    EXCEPTION
      WHEN parametre_null THEN
      DBMS_OUTPUT.PUT_LINE('les parametres sont nulls. (SOUSCATEGORIE)');
    END; 
  COMMIT;
END;
/

create or replace PROCEDURE DELETESOUSCATEGORIE(
     P_TITRE IN SOUSCATEGORIE.TITRE%TYPE)
IS
BEGIN
  DELETE FROM SOUSCATEGORIE
  WHERE P_TITRE = TITRE;
  COMMIT;
END;
/

create or replace PROCEDURE UPDATESOUSCATEGORIE(
     P_IDSOUSCATEGORIE IN SOUSCATEGORIE.IDSOUSCATEGORIE%TYPE,
     P_IDCATEGORIE IN SOUSCATEGORIE.IDCATEGORIE%TYPE,
	   P_TITRE IN SOUSCATEGORIE.TITRE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    idIncorrect exception;
    nbrID number := 0;
  BEGIN
    -- Si l'id est inconnu, on throw une exception
    SELECT COUNT(*) INTO nbrID FROM SOUSCATEGORIE WHERE IDSOUSCATEGORIE = P_IDSOUSCATEGORIE;
    IF nbrID != 1 then 
      -- Déclenche l'exception s'il y a 0 ou > 1 id correspondant
      RAISE idIncorrect; 
    ELSE
      UPDATE SOUSCATEGORIE
      SET TITRE = P_TITRE, IDCATEGORIE = P_IDCATEGORIE
      WHERE IDSOUSCATEGORIE = P_IDSOUSCATEGORIE;
    END IF;
    
    EXCEPTION
      WHEN idIncorrect THEN
      DBMS_OUTPUT.PUT_LINE('l''id n''existe pas dans la base de donnees (SOUSCATEGORIE).');
  END;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE SELECTSOUSCATEGORIE(
     -- PARAMETRE ENTRANT
	   P_IDSOUSCATEGORIE IN SOUSCATEGORIE.IDSOUSCATEGORIE%TYPE,
     -- LES PARAMETRES SORTANTS
     O_IDCATEGORIE OUT SOUSCATEGORIE.IDCATEGORIE%TYPE,
	   O_TITRE OUT SOUSCATEGORIE.TITRE%TYPE)
IS
BEGIN
  SELECT IDCATEGORIE, TITRE
  INTO O_IDCATEGORIE, O_TITRE
  FROM SOUSCATEGORIE WHERE IDSOUSCATEGORIE = P_IDSOUSCATEGORIE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (SOUSCATEGORIE)');
END;
/

create or replace PROCEDURE GETLISTSOUSCATEGORIE(
	   CUR_SOUSCATEGORIE OUT SYS_REFCURSOR)
IS
BEGIN
  --On crée un curseur
  OPEN CUR_SOUSCATEGORIE FOR
  SELECT * FROM SOUSCATEGORIE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (SOUSCATEGORIE)');
END;
/

/*==============================================================*/
/* PROCEDURES STOCKEES - SUJET                                  */
/*==============================================================*/
create or replace PROCEDURE INSERTSUJET(
	   --P_IDSUJET IN SUJET.IDSUJET%TYPE,
	   P_IDSOUSCATEGORIE IN SUJET.IDSOUSCATEGORIE%TYPE,
     P_TITRE IN SUJET.TITRE%TYPE,
     P_DATESUJET IN SUJET.DATESUJET%TYPE,
     P_IDUTILISATEUR IN SUJET.IDUTILISATEUR%TYPE)
IS
  BEGIN
    DECLARE
      -- Exception
      parametre_null exception;
    BEGIN
      -- Si les valeurs sont nulles on throw une exception
      IF P_IDSOUSCATEGORIE IS NULL OR P_TITRE IS NULL OR P_DATESUJET IS NULL OR P_IDUTILISATEUR IS NULL then 
        -- Déclenche l'exception
        RAISE parametre_null; 
      ELSE
        -- Sinon on commit
        INSERT INTO SUJET
        VALUES (SEQ_SUJET.NEXTVAL, P_IDSOUSCATEGORIE, P_TITRE, P_DATESUJET, P_IDUTILISATEUR);
      END IF;
      
      EXCEPTION
        WHEN parametre_null THEN
        DBMS_OUTPUT.PUT_LINE('les parametres sont nulls.');
      END;
  COMMIT;
END;
/

create or replace PROCEDURE DELETESUJET(
	   P_TITRE IN SUJET.TITRE%TYPE,
     P_DATESUJET IN SUJET.DATESUJET%TYPE)
IS
BEGIN
  DELETE FROM SUJET
  WHERE P_TITRE = TITRE AND P_DATESUJET = DATESUJET;
  
  COMMIT;
END;
/

create or replace PROCEDURE UPDATESUJET(
     P_IDSUJET IN SUJET.IDSUJET%TYPE,
	   P_IDSOUSCATEGORIE IN SUJET.IDSOUSCATEGORIE%TYPE,
     P_TITRE IN SUJET.TITRE%TYPE,
     P_DATESUJET IN SUJET.DATESUJET%TYPE,
     P_IDUTILISATEUR IN SUJET.IDUTILISATEUR%TYPE)
IS
BEGIN
 DECLARE
      -- Exception
      idIncorrect exception;
      nbrID number := 0;
    BEGIN
      -- Si l'id est inconnu, on throw une exception
      SELECT COUNT(*) INTO nbrID FROM SUJET WHERE IDSUJET = P_IDSUJET;
      IF nbrID != 1 then 
        -- Déclenche l'exception s'il y a 0 ou > 1 id egal à ce sujet
        RAISE idIncorrect; 
      ELSE
        UPDATE SUJET
        SET IDSOUSCATEGORIE = P_IDSOUSCATEGORIE, TITRE = P_TITRE, DATESUJET = P_DATESUJET, IDUTILISATEUR = P_IDUTILISATEUR
        WHERE IDSUJET = P_IDSUJET;
      END IF;
          
      EXCEPTION
        WHEN idIncorrect THEN
        DBMS_OUTPUT.PUT_LINE('l''id n''existe pas dans la base de donnees.');
      END;
  COMMIT;
END;
/

create or replace PROCEDURE SELECTSUJET(
     -- PARAMETRE ENTRANT
	   P_IDSUJET IN SUJET.IDSUJET%TYPE,
     -- LES PARAMETRES SORTANTS
	   O_IDSUJET OUT SUJET.IDSUJET%TYPE,
	   O_IDSOUSCATEGORIE OUT SUJET.IDSOUSCATEGORIE%TYPE,
	   O_TITRE OUT SUJET.TITRE%TYPE,
     O_DATESUJET OUT SUJET.DATESUJET%TYPE,
     O_IDUTILISATEUR OUT SUJET.IDUTILISATEUR%TYPE)
IS
BEGIN
    SELECT IDSUJET, IDSOUSCATEGORIE, TITRE, DATESUJET, IDUTILISATEUR
    INTO O_IDSUJET,  O_IDSOUSCATEGORIE, O_TITRE, O_DATESUJET, O_IDUTILISATEUR
    FROM SUJET WHERE IDSUJET = P_IDSUJET;
    
    EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (SUJET)');
END;
/

create or replace PROCEDURE GETLISTSUJET(
	   CUR_SUJET OUT SYS_REFCURSOR)
IS
BEGIN
 
  --On crée un curseur
  OPEN CUR_SUJET FOR
  SELECT * FROM SUJET;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (SUJET)');
END;
/

/*==============================================================*/
/* PROCEDURES STOCKEES - HISTORIQUE                             */
/*==============================================================*/
create or replace PROCEDURE INSERTHISTORIQUE(
	   --P_IDHISTORIQUE IN HISTORIQUE.IDHISTORIQUE%TYPE,
	   P_DATECONNEXION IN HISTORIQUE.DATECONNEXION%TYPE,
	   P_IDUTILISATEUR IN HISTORIQUE.IDUTILISATEUR%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    parametre_null exception;
  BEGIN
    -- Si les valeurs sont nulles on throw une exception
    IF P_DATECONNEXION IS NULL OR P_IDUTILISATEUR IS NULL THEN
      -- Déclenche l'exception
      RAISE parametre_null; 
    ELSE
      -- Sinon on commit
      INSERT INTO HISTORIQUE
      VALUES (SEQ_HISTORIQUE.NEXTVAL, P_DATECONNEXION, P_IDUTILISATEUR);
    END IF;
    
    EXCEPTION
      WHEN parametre_null THEN
      DBMS_OUTPUT.PUT_LINE('les parametres sont nulls. (HISTORIQUE)');
    END;
  COMMIT;
END;
/

create or replace PROCEDURE DELETEHISTORIQUE(
	   P_DATECONNEXION IN HISTORIQUE.DATECONNEXION%TYPE,
     P_IDUTILISATEUR IN HISTORIQUE.IDUTILISATEUR%TYPE)
IS
BEGIN
  DELETE FROM HISTORIQUE
  WHERE P_DATECONNEXION = DATECONNEXION AND P_IDUTILISATEUR = IDUTILISATEUR;
  
  COMMIT;
END;
/

create or replace PROCEDURE UPDATEHISTORIQUE(
      P_DATECONNEXION IN HISTORIQUE.DATECONNEXION%TYPE,
      P_IDUTILISATEUR IN HISTORIQUE.IDUTILISATEUR%TYPE,
      P_IDHISTORIQUE IN HISTORIQUE.IDHISTORIQUE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    idIncorrect exception;
    nbrID number := 0;
  BEGIN
    -- Si l'id est inconnu, on throw une exception
    SELECT COUNT(*) INTO nbrID FROM HISTORIQUE WHERE IDHISTORIQUE = P_IDHISTORIQUE;
    IF nbrID != 1 then 
      -- Déclenche l'exception s'il y a 0 ou > 1 id correspondant
      RAISE idIncorrect; 
    ELSE
      UPDATE HISTORIQUE
        SET DATECONNEXION = P_DATECONNEXION, IDUTILISATEUR = P_IDUTILISATEUR
        WHERE IDHISTORIQUE = P_IDHISTORIQUE;
    END IF;
    
    EXCEPTION
      WHEN idIncorrect THEN
      DBMS_OUTPUT.PUT_LINE('l''id n''existe pas dans la base de donnees. (HISTORIQUE)');
  END;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE SELECTHISTORIQUE(
     -- PARAMETRE ENTRANT
	   P_IDHISTORIQUE IN HISTORIQUE.IDHISTORIQUE%TYPE,
     -- LES PARAMETRES SORTANTS
	   O_DATECONNEXION OUT HISTORIQUE.DATECONNEXION%TYPE,
	   O_IDUTILISATEUR OUT HISTORIQUE.IDUTILISATEUR%TYPE)
IS
BEGIN
  SELECT DATECONNEXION, IDUTILISATEUR
  INTO O_DATECONNEXION,  O_IDUTILISATEUR
  FROM HISTORIQUE WHERE IDHISTORIQUE = P_IDHISTORIQUE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (HISTORIQUE)');
END;
/

create or replace PROCEDURE GETLISTHISTORIQUE(
	   CUR_HISTORIQUE OUT SYS_REFCURSOR)
IS
BEGIN
  --On crée un curseur
  OPEN CUR_HISTORIQUE FOR
  SELECT * FROM HISTORIQUE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (HISTORIQUE)');
END;
/

/*==============================================================*/
/* PROCEDURES STOCKEES - COMMENTAIRE                            */
/*==============================================================*/
create or replace PROCEDURE INSERTCOMMENTAIRE(
	   --P_IDCOMMENTAIRE IN COMMENTAIRE.IDCOMMENTAIRE%TYPE,
	   P_IDSUJET IN COMMENTAIRE.IDSUJET%TYPE,
	   P_TEXTE IN COMMENTAIRE.TEXTE%TYPE,
     P_DATECOMMENTAIRE IN COMMENTAIRE.DATECOMMENTAIRE%TYPE,
     P_IDUTILISATEUR IN COMMENTAIRE.IDUTILISATEUR%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    parametre_null exception;
  BEGIN
    -- Si les valeurs sont nulles on throw une exception
    IF P_IDSUJET IS NULL OR P_TEXTE IS NULL OR P_DATECOMMENTAIRE IS NULL OR P_IDUTILISATEUR IS NULL THEN
      -- Déclenche l'exception
      RAISE parametre_null; 
    ELSE
      -- Sinon on commit
      INSERT INTO COMMENTAIRE
      VALUES (SEQ_COMMENTAIRE.NEXTVAL, P_IDSUJET, P_TEXTE, P_DATECOMMENTAIRE, P_IDUTILISATEUR);
    END IF;
    
    EXCEPTION
      WHEN parametre_null THEN
      DBMS_OUTPUT.PUT_LINE('les parametres sont nulls. (COMMENTAIRE)');
    END;
  COMMIT;
END;
/

create or replace PROCEDURE DELETECOMMENTAIRE(
	   P_TEXTE IN COMMENTAIRE.TEXTE%TYPE,
     P_DATECOMMENTAIRE IN COMMENTAIRE.DATECOMMENTAIRE%TYPE)
IS
BEGIN
  DELETE FROM COMMENTAIRE
  WHERE P_TEXTE = TEXTE AND P_DATECOMMENTAIRE = DATECOMMENTAIRE;
  
  COMMIT;
END;
/

create or replace PROCEDURE UPDATECOMMENTAIRE(
	   P_TEXTE IN COMMENTAIRE.TEXTE%TYPE,
     P_DATECOMMENTAIRE IN COMMENTAIRE.DATECOMMENTAIRE%TYPE,
     P_IDCOMMENTAIRE IN COMMENTAIRE.IDCOMMENTAIRE%TYPE)
IS
BEGIN
  DECLARE
    -- Exception
    idIncorrect exception;
    nbrID number := 0;
  BEGIN
    -- Si l'id est inconnu, on throw une exception
    SELECT COUNT(*) INTO nbrID FROM COMMENTAIRE WHERE IDCOMMENTAIRE = P_IDCOMMENTAIRE;
    IF nbrID != 1 then 
      -- Déclenche l'exception s'il y a 0 ou > 1 id correspondant
      RAISE idIncorrect; 
    ELSE
      UPDATE COMMENTAIRE
      SET TEXTE = P_TEXTE, DATECOMMENTAIRE = P_DATECOMMENTAIRE
      WHERE IDCOMMENTAIRE = P_IDCOMMENTAIRE;
    END IF;
    
    EXCEPTION
      WHEN idIncorrect THEN
      DBMS_OUTPUT.PUT_LINE('l''id n''existe pas dans la base de donnees. (COMMENTAIRE)');
  END;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE SELECTCOMMENTAIRE(
     -- PARAMETRE ENTRANT
	   P_IDCOMMENTAIRE IN COMMENTAIRE.IDCOMMENTAIRE%TYPE,
     -- LES PARAMETRES SORTANTS
	   O_IDSUJET OUT COMMENTAIRE.IDSUJET%TYPE,
	   O_TEXTE OUT COMMENTAIRE.TEXTE%TYPE,
	   O_DATECOMMENTAIRE OUT COMMENTAIRE.DATECOMMENTAIRE%TYPE,
     O_IDUTILISATEUR OUT COMMENTAIRE.IDUTILISATEUR%TYPE)
IS
BEGIN
  SELECT IDSUJET, TEXTE, DATECOMMENTAIRE, IDUTILISATEUR
  INTO O_IDSUJET,  O_TEXTE, O_DATECOMMENTAIRE, O_IDUTILISATEUR
  FROM COMMENTAIRE WHERE IDCOMMENTAIRE = P_IDCOMMENTAIRE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (COMMENTAIRE)');
END;
/

create or replace PROCEDURE GETLISTCOMMENTAIRE(
	   CUR_COMMENTAIRE OUT SYS_REFCURSOR)
IS
BEGIN
  --On crée un curseur
  OPEN CUR_COMMENTAIRE FOR
  SELECT * FROM COMMENTAIRE;
  
  EXCEPTION
  WHEN NO_DATA_FOUND THEN
  DBMS_OUTPUT.PUT_LINE('Pas de donnees trouvee. (COMMENTAIRE)');
END;
/
