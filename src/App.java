import java.io.*;
//import java.util.function.Function;

//import javax.lang.model.util.ElementScanner6;

public class App {

    int nbContributeurs;
    int indicePersonne;
    int nbProjets;
    Skill[] skills;
    Personne[] personnes;
    Projet[] projets;

    App(){}

    public static void main(String[] args) throws Exception 
    {
        App app = new App();
        File doc = new File("C:\\Users\\natha\\Desktop\\Projet\\Train HashCode 2022\\f_find_great_mentors.in.txt");
        app.lineariseDonnee(doc);
        File docUtile = new File("C:\\Users\\natha\\Desktop\\Projet\\Train HashCode 2022\\test.txt");
        app.creeSkill(docUtile);
        app.traiteDonnees(docUtile);
        //app.verifFonctionnement();
        app.attributionProjet();
        app.ecritureLivrable();
    }


    // Prepare les données à leur utilisation
    void lineariseDonnee(File doc) throws Exception
    {
        String chaine;
        String temp = "";
        boolean estNom = true;
        int nbContrib;
        int nbSkills = 0;
        PrintWriter writer = new PrintWriter("C:\\Users\\natha\\Desktop\\Projet\\Train HashCode 2022\\test.txt", "UTF-8");
        try (BufferedReader obj = new BufferedReader(new FileReader(doc))) 
        {
            chaine = obj.readLine();
            nbContrib = Integer.parseInt(chaine.split(" ")[0]);
            this.nbContributeurs = Integer.parseInt(chaine.split(" ")[0]);
            this.personnes = new Personne[this.nbContributeurs];
            this.indicePersonne = 0;
            this.nbProjets = Integer.parseInt(chaine.split(" ")[1]);
            this.projets = new Projet[this.nbProjets];
            while((chaine = obj.readLine()) != null && nbContrib != 0)
            {
                if(estNom)
                {
                    nbSkills = Integer.parseInt(chaine.split(" ")[1]);
                    temp = chaine;
                    estNom = !estNom;
                }
                else
                {
                    temp += " " + chaine;
                    nbSkills--;
                    if(nbSkills == 0)
                    {
                        estNom = !estNom;
                        writer.println(temp);
                        nbContrib--;
                    }
                }
            }
            temp = "";
            nbSkills = Integer.parseInt(chaine.split(" ")[4]);
            temp = chaine;
            estNom = !estNom;
            while((chaine = obj.readLine()) != null)
            {
                if(estNom)
                {
                    nbSkills = Integer.parseInt(chaine.split(" ")[4]);
                    temp = chaine;
                    estNom = !estNom;
                }
                else
                {
                    temp += " " + chaine;
                    nbSkills--;
                    if(nbSkills == 0)
                    {
                        estNom = !estNom;
                        writer.println(temp);
                        nbContrib--;
                    }
                }
            }
                
        }
        writer.close();
    }

    // Traite les données préparées
    void traiteDonnees(File doc) throws Exception
    {
        try (BufferedReader obj = new BufferedReader(new FileReader(doc))) 
        {
            String chaine;
            String[] donnees;
            int indice;
            for (int k = 0 ; k < this.nbContributeurs ; k++)
            {
                chaine = obj.readLine();
                donnees = chaine.split(" ");
                Skill[] skilltemp = new Skill[Integer.parseInt(donnees[1])];
                int[] lvltemp = new int[Integer.parseInt(donnees[1])];
                indice = 0;
                for (int j = 2 ; j < donnees.length - 1 ; j += 2)
                {
                    skilltemp[indice] = rechercheSkill(donnees[j]);
                    lvltemp[indice] = Integer.parseInt(donnees[j+1]);
                    indice++;
                }
                Personne personne = new Personne(donnees[0], skilltemp , lvltemp);
                this.ajoutePersonne(personne);
            }

            // Creation projet
            for(int k = 0 ; k < this.nbProjets ; k++)
            {
                donnees = obj.readLine().split(" ");
                Skill[] skilltemp = new Skill[Integer.parseInt(donnees[4])];
                int[] lvltemp = new int[Integer.parseInt(donnees[4])];
                indice = 0;
                for (int j = 5 ; j < donnees.length ; j += 2)
                {
                    skilltemp[indice] = rechercheSkill(donnees[j]);
                    lvltemp[indice] = Integer.parseInt(donnees[j+1]);
                    indice++;
                }
                Projet projet = new Projet(donnees[0], skilltemp, Integer.parseInt(donnees[1]), Integer.parseInt(donnees[2]) , lvltemp);
                this.ajouteProjet(projet);
            }
        }
    }

    // Crée tous les skills possible
    void creeSkill(File doc) throws Exception
    {
        this.skills = new Skill[1];
        try (BufferedReader obj = new BufferedReader(new FileReader(doc))) 
        {
            int nbContrib = this.nbContributeurs;
            String[] chaine;
            boolean premier = true;
            while(nbContrib != 0)
            {
                chaine = obj.readLine().split(" ");
                if(premier)
                {
                    this.skills[0] = new Skill(chaine[2]);
                    premier = !premier;
                }
                else
                {
                    for (int k = 2 ; k < chaine.length ; k +=2)
                    {
                        if(!skillDejaExistant(chaine[k]))
                        {
                            Skill skill = new Skill(chaine[k]);
                            this.skills = ajouteSkill(skill);
                        }
                    }
                    nbContrib--;
                }
            }
        }
    }

    boolean skillDejaExistant(String nomSkill)
    {
        if(this.skills.length == 0)
        {
            return false;
        }
        else
        {
            for (int k = 0 ; k < this.skills.length ; k++)
            {
                if(this.skills[k].getNom().equals(nomSkill))
                {
                    return true;
                }
            }
        }
        return false;
    }

    Skill rechercheSkill(String nomSkill)
    {
        for (int k = 0 ; k < this.skills.length ; k++)
        {
            if(this.skills[k].getNom().equals(nomSkill))
            {
                return this.skills[k];
            }
        }
        return null;
    }

    Personne recherchePersonne(String nom)
    {
        for (int k = 0 ; k < this.personnes.length ; k++)
        {
            if(this.personnes[k].getNom().equals(nom))
            {
                return this.personnes[k];
            }
        }
        return null;
    }

    Projet rechercheProjet(String nom)
    {
        for (int k = 0 ; k < this.projets.length ; k++)
        {
            if(this.projets[k].getNom().equals(nom))
            {
                return this.projets[k];
            }
        }
        return null;
    }

    Skill[] ajouteSkill(Skill skill)
    {
        Skill[] newSkill = new Skill[this.skills.length + 1];
        for (int k = 0 ; k < this.skills.length ; k++)
        {
            newSkill[k] = this.skills[k];
        }
        newSkill[newSkill.length - 1] = skill;
        return newSkill;
    }

    void ajoutePersonne(Personne personne)
    {
        this.personnes[indicePersonne] = personne;
        this.indicePersonne++;
    }

    void ajouteProjet(Projet projet)
    {
        int indice = 0;
        while(this.projets[indice] != null)
        {
            indice++;
        }
        this.projets[indice] = projet;
    }

    void verifFonctionnement() throws Exception
    {
        PrintWriter writer = new PrintWriter("C:\\Users\\natha\\Desktop\\Projet\\Train HashCode 2022\\verifFonctionnement.txt", "UTF-8");
        writer.println("Skills");
        for(int k = 0 ; k < this.skills.length ; k++)
        {
            writer.println(this.skills[k].getNom());
        }
        writer.println("\n" + "Projets");
        for(int k = 0 ; k < this.projets.length ; k++)
        {
            writer.print(projets[k].getNom() + " " );
            for (int j = 0 ; j < this.projets[k].lvlRequis.length ; j++)
            {
                writer.println(this.projets[k].skills[j].getNom() + " " + this.projets[k].lvlRequis[j]);
            }
            writer.println(" ");
        }
        writer.println("\n" + "Personnes");
        for (int k = 0 ; k < this.personnes.length ; k++)
        {
            writer.print(this.personnes[k].getNom() + " ");
            for (int j = 0 ; j < personnes[k].skills.length ; j++)
            {
                writer.print(personnes[k].skills[j].getNom() + " " + personnes[k].lvlSkills[j]);
            }
            writer.println(" ");
        }
        writer.close();
    }

    void ecritureLivrable() throws Exception
    {
        PrintWriter writer = new PrintWriter("C:\\Users\\natha\\Desktop\\Projet\\Train HashCode 2022\\LivrableF.txt", "UTF-8");
        int nbProjets = 0;
        Projet[] projetPret = new Projet[this.projets.length];
        for (int k = 0 ; k < this.projets.length ; k++)
        {
            if(this.projets[k].estComplet())
            {
                projetPret[nbProjets] = this.projets[k];
                nbProjets++;
            }
        }
        writer.println(nbProjets);
        for (int k = 0 ; k < nbProjets ; k++)
        {
            writer.println(projetPret[k].getNom());
            for (int j = 0 ; j < projetPret[k].personnes.length ; j++)
            {
                writer.print(projetPret[k].personnes[j].getNom() + " ");
            }
            writer.println("");
        }
        writer.close();
    }

    Personne[] triePersonneSkill(Skill skill)
    {
        Personne[] newPersonnes = new Personne[this.personnes.length];
        int indiceMax = 0;
        for (int k = 0 ; k < this.personnes.length ; k++)
        {
            if(this.personnes[k].possedeSkill(skill))
            {
                newPersonnes[indiceMax] = this.personnes[k];
                indiceMax++;
            }
        }
        Personne[] retourPersonne = new Personne[indiceMax];
        for (int k = 0  ; k < indiceMax ; k++)
        {
            retourPersonne[k] = newPersonnes[k];
        }
        return retourPersonne;
    }

    boolean pasDeCorrespondant(Skill skill , int level)
    {
        for (int k = 0 ; k < this.personnes.length ; k++)
        {
            if(!this.personnes[k].possedeSkill(skill) || !this.personnes[k].niveauSuffisant(skill, level))
            {
                return false;
            }
        }
        return true;
    }

    void attributionProjet()
    {
        Personne personne;
        for (int k = 0 ; k < this.projets.length ; k++)
        {
            for (int j = 0 ; j < this.projets[k].skills.length ; j++)
            {
                personne = choisisPersonneNaif(this.projets[k].skills[j], this.projets[k].lvlRequis[j]);
                if(!pasDeCorrespondant(this.projets[k].skills[j], this.projets[k].lvlRequis[j]) && personne != null)
                {
                    this.projets[k].ajoutePersonne(personne);
                }
            }
            if(this.projets[k].estComplet())
            {
                this.projets[k].debutProjet();
            }
        }
    }

    Personne choisisPersonneNaif(Skill skill , int level)
    {
        for (int k = 0 ; k < this.personnes.length ; k++)
        {
            if(!this.personnes[k].estOccupee() && this.personnes[k].possedeSkill(skill) && this.personnes[k].niveauSuffisant(skill, level))
            {
                return this.personnes[k];
            }
        }
        return null;
    }

}
