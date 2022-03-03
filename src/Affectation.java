public class Affectation {
    
    Projet[] projets;
    Personne[] personnes;

    Affectation(Projet[] projets , Personne[] personnes)
    {
        this.projets = projets;
        this.personnes = personnes;
    }

    Personne personneCorrespondantNaif(Skill skill , int level)
    {
        for (int k = 0 ; k < this.personnes.length ; k++)
        {
            if(this.personnes[k].niveauSuffisant(skill, level))
            {
                return this.personnes[k];
            }
        }
        return null;
    }

    void attributionNaif()
    {
        for (int k = 0 ; k < this.projets.length ; k++)
        {
            for (int j = 0 ; j < this.projets[k].skills.length ; j++)
            {
                if(this.personneCorrespondantNaif(projets[k].skills[j], projets[k].lvlRequis[j]) != null)
                {
                    projets[k].ajoutePersonne(this.personneCorrespondantNaif(projets[k].skills[j], projets[k].lvlRequis[j]));
                }
            }
        }
    }

}
