public class Projet {
    
    String nom;
    Skill[] skills;
    int[] lvlRequis;
    Personne[] personnes;
    int indicePersonne;
    int score;
    int jourNecessaire;
    int jourCourant;

    Projet(String nom , Skill[] skills , int jourNecessaire , int score , int[] lvltemp)
    {
        this.nom = nom;
        this.skills = skills;
        this.personnes = new Personne[skills.length];
        this.jourNecessaire = jourNecessaire;
        this.score = score;
        this.lvlRequis = lvltemp;
        this.indicePersonne = 0;
    }

    String getNom()
    {
        return this.nom;
    }

    void ajoutePersonne(Personne personne)
    {
        this.personnes[this.indicePersonne] = personne;
        this.indicePersonne++;
        personne.debutProjet();
    }

    boolean estComplet()
    {
        if(this.indicePersonne == this.personnes.length)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    void setIndicePersonne(int newIndice)
    {
        this.indicePersonne = newIndice;
    }

    void debutProjet()
    {
        for (int k = 0 ; k < this.personnes.length ; k++)
        {
            personnes[k].debutProjet();
        }
        this.jourCourant = 0;
    }
}
