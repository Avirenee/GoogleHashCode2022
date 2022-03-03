public class Personne {
    
    String nom;
    Skill[] skills;
    int[] lvlSkills;
    boolean occupe;

    Personne(String nom , Skill[] skillTemp , int[] lvlTemp)
    {
        this.nom = nom;
        this.skills = skillTemp;
        this.lvlSkills = lvlTemp;
        this.occupe = false;
    }

    String getNom()
    {
        return this.nom;
    }

    boolean estOccupee()
    {
        return this.occupe;
    }

    boolean possedeSkill(Skill skill)
    {
        for (int k = 0 ; k < this.skills.length ; k++)
        {
            if(this.skills[k] == skill)
            {
                return true;
            }
        }
        return false;
    }

    int skillToLevel(Skill skill)
    {
        for (int k = 0 ; k < this.skills.length ; k++)
        {
            if(this.skills[k] == skill)
            {
                return this.lvlSkills[k];
            }
        }
        return 0;
    }

    boolean niveauSuffisant(Skill skill , int level)
    {
        if(skillToLevel(skill) >= level)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    boolean niveauMentor(Skill skill , int level)
    {
        if(skillToLevel(skill) >= level - 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    void debutProjet()
    {
        this.occupe = true;
    }

    void finProjet()
    {
        this.occupe = false;
    }

}
