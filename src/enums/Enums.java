package enums;

public class Enums {
    
    public enum StatutChambre {
        DISPONIBLE, OCCUPEE, MAINTENANCE, NETTOYAGE
    }

    public enum TypeClient {
        INDIVIDUEL, ENTREPRISE, VIP
    }

    public enum StatutReservation {
        CONFIRMEE, EN_ATTENTE, ANNULEE, TERMINEE
    }

    public enum Departement {
        RECEPTIONNISTE,
        SERVICE, 
        MAINTENANCE,
        ADMIN;

    }

    public enum StatutEmploye {
        ACTIF, INACTIF, CONGE
    }

    public enum CategorieService {
        RESTAURATION, SPA, TRANSPORT, BLANCHISSERIE, AUTRE
    }

    public enum StatutCommande {
        EN_ATTENTE, EN_COURS, TERMINEE, ANNULEE
    }

    public enum StatutEvenement {
        PLANIFIE, EN_COURS, TERMINE, ANNULE
    }

    public enum StatutFacture {
        EN_ATTENTE, PAYEE, PARTIELLEMENT_PAYEE, ANNULEE
    }

    public enum ModePaiement {
        ESPECES, CARTE_CREDIT, CARTE_DEBIT, VIREMENT, CHEQUE
    }

    public enum TypeMaintenance {
        NETTOYAGE, REPARATION, ENTRETIEN, INSPECTION
    }

    public enum PrioriteMaintenance {
        BASSE, NORMALE, HAUTE, URGENTE
    }

    public enum StatutMaintenance {
        PLANIFIEE, EN_COURS, TERMINEE, ANNULEE
    }
}
