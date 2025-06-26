# Application de Gestion Hôtelière

## Description
Cette application de gestion hôtelière permet de gérer tous les aspects d'un hôtel : réservations, assignation des chambres, services aux clients, restauration et événements. L'application optimise l'expérience client et facilite les opérations hôtelières.

## Fonctionnalités Principales

### 🔐 Système d'Authentification
- **Connexion sécurisée** avec email et mot de passe
- **Inscription client** avec formulaire de complétion de profil
- **Gestion des rôles** : Admin, Client, Réceptionniste, Service, Maintenance

### 🎨 Interface Utilisateur
- **Thème moderne** avec couleurs rose, blanc et noir
- **Interface responsive** adaptée aux différents rôles
- **Tableaux de bord personnalisés** pour chaque type d'utilisateur
- **Navigation intuitive** avec menus latéraux

### 👤 Gestion des Utilisateurs
- **Profils clients** avec informations détaillées
- **Gestion des employés** par département
- **Système de permissions** basé sur les rôles

### 🏨 Gestion Hôtelière
- **Réservations** en ligne et gestion des créneaux
- **Chambres** : types, statuts, assignation
- **Services** : room service, ménage, blanchisserie
- **Événements** : organisation et participation
- **Maintenance** : préventive et corrective
- **Facturation** : génération et suivi des paiements

## Architecture

### Structure des Packages
```
src/
├── dao/                 # Data Access Objects
├── models/              # Modèles de données
├── enums/               # Énumérations (statuts, types, etc.)
├── ui/                  # Interfaces utilisateur
│   ├── admin/           # Dashboard administrateur
│   ├── client/          # Dashboard client
│   ├── receptionniste/  # Dashboard réception
│   ├── service/         # Dashboard service
│   └── maintenance/     # Dashboard maintenance
└── utils/               # Utilitaires
```

### Modèles Principaux
- **Utilisateur** : gestion des comptes et authentification
- **Client** : informations personnelles et préférences
- **Employé** : données RH et départements
- **Chambre** : caractéristiques et disponibilité
- **Réservation** : bookings et périodes
- **Service** : prestations et commandes
- **Événement** : manifestations et participations
- **Maintenance** : interventions et planning
- **Facture** : facturation et paiements

## Rôles et Permissions

### 🔧 Administrateur
- Gestion complète du système
- Statistiques globales
- Configuration des services
- Gestion des employés
- Rapports financiers

### 👥 Client
- Réservations en ligne
- Gestion du profil
- Commande de services
- Participation aux événements
- Historique et factures

### 🏢 Réceptionniste
- Check-in/Check-out
- Gestion des réservations
- État des chambres
- Assistance client
- Facturation

### 🛎️ Service
- Commandes en cours
- Room service
- Ménage et blanchisserie
- Planning des tâches
- Suivi qualité

### 🔧 Maintenance
- Interventions urgentes
- Maintenance préventive
- Gestion des pannes
- Inventaire des pièces
- Rapports techniques

## Base de Données

### Tables Principales
- `utilisateur` : comptes et authentification
- `client` : profils clients
- `employe` : données employés
- `chambre` : inventaire des chambres
- `type_chambre` : catégories de chambres
- `reservation` : bookings
- `reservation_chambre` : liaison réservation-chambre
- `service` : catalogue des services
- `commande_service` : commandes clients
- `evenement` : événements organisés
- `evenement_participant` : inscriptions
- `maintenance` : interventions techniques
- `facture` : facturation
- `paiement` : transactions

## Installation et Configuration

### Prérequis
- Java 8 ou supérieur
- NetBeans IDE
- Base de données MySQL/PostgreSQL
- Driver JDBC approprié

### Configuration de la Base de Données
1. Créer la base de données `gestion_hotel`
2. Exécuter les scripts SQL de création des tables
3. Configurer la connexion dans `DatabaseService.java`

### Lancement de l'Application
1. Ouvrir le projet dans NetBeans
2. Compiler le projet
3. Exécuter `ConnexionForm.java` comme classe principale

## Utilisation

### Première Connexion
1. **Inscription** : créer un compte client
2. **Complétion du profil** : renseigner les informations personnelles
3. **Navigation** : explorer les fonctionnalités disponibles

### Fonctionnalités par Rôle
- **Client** : réserver, commander des services, participer aux événements
- **Personnel** : gérer les tâches selon le département
- **Admin** : superviser l'ensemble des opérations

## Développement Futur

### Fonctionnalités Prévues
- [ ] Module de reporting avancé
- [ ] Intégration système de paiement
- [ ] Application mobile
- [ ] API REST
- [ ] Synchronisation calendrier
- [ ] Système de notifications
- [ ] Chat en temps réel
- [ ] Gestion des inventaires
- [ ] Programme de fidélité
- [ ] Analytics et KPIs

### Améliorations Techniques
- [ ] Migration vers Spring Boot
- [ ] Architecture microservices
- [ ] Tests unitaires et d'intégration
- [ ] CI/CD pipeline
- [ ] Monitoring et logs
- [ ] Sécurité renforcée
- [ ] Performance optimization

## Contribution
Ce projet est ouvert aux contributions. Merci de suivre les bonnes pratiques de développement et de documenter vos modifications.

## Licence
Projet développé dans un cadre éducatif.

---
**Auteur**: diaom  
**Version**: 1.0  
**Date**: Juin 2025
