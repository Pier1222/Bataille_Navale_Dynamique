package model;

import java.io.IOException;
import java.io.Serializable;

public class Joueur implements Serializable {
	private final static String DEFAULT_NAME = "Invité";
	
	private int id;
	private String nom;
	private Equipe equipe;
	private Stats_Joueur statistiques;
	
	public Joueur() {
		id     = -1;
		nom    = DEFAULT_NAME;
		equipe = null;
		statistiques = new Stats_Joueur();
	}
	
	public boolean creerPartie(int numeroPort) {
		Bataille_Server serveur;
		try {
			serveur = new Bataille_Server(numeroPort, this);
		    serveur.mainLoop();
		} catch (IOException e) {
			System.out.println("Problème demande connexion au serveur au port " + numeroPort + " : " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Essaie de rejoindre une partie déjà créé
	 * @param adresseIp
	 * @param numeroPort
	 * @return Vrai si la connection a réussit, faux sinon
	 */
	public boolean rejoindrePartie(String adresseIp, int numeroPort) {
		try {
			Bataille_Client client = new Bataille_Client(this, adresseIp, numeroPort);
		} catch (IOException e) {
			System.err.println("Erreur lors de la connexion à l'adressIP" + adresseIp + " au port " + numeroPort + ": \n " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getNom() {
		return nom;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public Stats_Joueur getStatistiques() {
		return statistiques;
	}
}
