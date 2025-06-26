-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 08, 2025 at 05:24 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `chambre`
--

CREATE TABLE `chambre` (
  `id` int(11) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `etage` int(11) NOT NULL,
  `type_chambre_id` int(11) NOT NULL,
  `statut` enum('disponible','occupee','maintenance','nettoyage') DEFAULT 'disponible',
  `description` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `adresse` text DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `nationalite` varchar(50) DEFAULT NULL,
  `numero_passeport` varchar(50) DEFAULT NULL,
  `type_client` enum('individuel','entreprise','vip') DEFAULT 'individuel',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `commande_service`
--

CREATE TABLE `commande_service` (
  `id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `quantite` int(11) DEFAULT 1,
  `prix_unitaire` decimal(10,2) NOT NULL,
  `montant_total` decimal(10,2) NOT NULL,
  `statut` enum('en_attente','en_cours','terminee','annulee') DEFAULT 'en_attente',
  `date_commande` timestamp NOT NULL DEFAULT current_timestamp(),
  `date_livraison` timestamp NULL DEFAULT NULL,
  `commentaires` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

CREATE TABLE `employe` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `poste` varchar(100) NOT NULL,
  `departement` enum('reception','menage','restauration','maintenance','direction','securite') NOT NULL,
  `salaire` decimal(10,2) DEFAULT NULL,
  `date_embauche` date NOT NULL,
  `statut` enum('actif','inactif','conge') DEFAULT 'actif',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `description` text DEFAULT NULL,
  `date_debut` datetime NOT NULL,
  `date_fin` datetime NOT NULL,
  `lieu` varchar(200) DEFAULT NULL,
  `capacite_max` int(11) DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  `organisateur_id` int(11) DEFAULT NULL,
  `statut` enum('planifie','en_cours','termine','annule') DEFAULT 'planifie',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evenement_participant`
--

CREATE TABLE `evenement_participant` (
  `id` int(11) NOT NULL,
  `evenement_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `date_inscription` timestamp NOT NULL DEFAULT current_timestamp(),
  `statut` enum('inscrit','confirme','annule') DEFAULT 'inscrit'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  `numero_facture` varchar(50) NOT NULL,
  `montant_total` decimal(10,2) NOT NULL,
  `montant_paye` decimal(10,2) DEFAULT 0.00,
  `statut` enum('en_attente','payee','partiellement_payee','annulee') DEFAULT 'en_attente',
  `date_emission` date DEFAULT curdate(),
  `date_echeance` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `maintenance`
--

CREATE TABLE `maintenance` (
  `id` int(11) NOT NULL,
  `chambre_id` int(11) NOT NULL,
  `employe_id` int(11) DEFAULT NULL,
  `type_maintenance` enum('nettoyage','reparation','entretien','inspection') NOT NULL,
  `description` text NOT NULL,
  `priorite` enum('basse','normale','haute','urgente') DEFAULT 'normale',
  `statut` enum('planifiee','en_cours','terminee','annulee') DEFAULT 'planifiee',
  `date_planifiee` datetime DEFAULT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `cout` decimal(10,2) DEFAULT NULL,
  `commentaires` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `paiement`
--

CREATE TABLE `paiement` (
  `id` int(11) NOT NULL,
  `facture_id` int(11) NOT NULL,
  `montant` decimal(10,2) NOT NULL,
  `mode_paiement` enum('especes','carte_credit','carte_debit','virement','cheque') NOT NULL,
  `reference_transaction` varchar(100) DEFAULT NULL,
  `date_paiement` timestamp NOT NULL DEFAULT current_timestamp(),
  `statut` enum('reussi','echec','en_attente') DEFAULT 'reussi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `nombre_adultes` int(11) DEFAULT 1,
  `nombre_enfants` int(11) DEFAULT 0,
  `statut` enum('confirmee','en_attente','annulee','terminee') DEFAULT 'en_attente',
  `montant_total` decimal(10,2) DEFAULT NULL,
  `acompte` decimal(10,2) DEFAULT 0.00,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservation_chambre`
--

CREATE TABLE `reservation_chambre` (
  `id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  `chambre_id` int(11) NOT NULL,
  `prix_nuit` decimal(10,2) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `prix` decimal(10,2) NOT NULL,
  `categorie` enum('restauration','spa','transport','blanchisserie','autre') NOT NULL,
  `disponible` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `nom`, `description`, `prix`, `categorie`, `disponible`, `created_at`, `updated_at`) VALUES
(1, 'Petit-déjeuner', 'Buffet petit-déjeuner continental', 25.00, 'restauration', 1, '2025-06-06 13:49:13', '2025-06-06 13:49:13'),
(2, 'Room Service', 'Service en chambre 24h/24', 15.00, 'restauration', 1, '2025-06-06 13:49:13', '2025-06-06 13:49:13'),
(3, 'Spa Massage', 'Massage relaxant 1h', 80.00, 'spa', 1, '2025-06-06 13:49:13', '2025-06-06 13:49:13'),
(4, 'Blanchisserie', 'Service de nettoyage vêtements', 20.00, 'blanchisserie', 1, '2025-06-06 13:49:13', '2025-06-06 13:49:13'),
(5, 'Transport Aéroport', 'Navette aéroport', 35.00, 'transport', 1, '2025-06-06 13:49:13', '2025-06-06 13:49:13');

-- --------------------------------------------------------

--
-- Table structure for table `type_chambre`
--

CREATE TABLE `type_chambre` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `description` text DEFAULT NULL,
  `prix_base` decimal(10,2) NOT NULL,
  `capacite_max` int(11) NOT NULL,
  `equipements` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `type_chambre`
--

INSERT INTO `type_chambre` (`id`, `nom`, `description`, `prix_base`, `capacite_max`, `equipements`, `created_at`, `updated_at`) VALUES
(1, 'Standard', 'Chambre standard avec les équipements de base', 80.00, 2, 'TV, WiFi, Salle de bain privée', '2025-06-06 13:49:13', '2025-06-06 13:49:13'),
(2, 'Deluxe', 'Chambre deluxe avec vue sur jardin', 120.00, 2, 'TV, WiFi, Minibar, Balcon, Vue jardin', '2025-06-06 13:49:13', '2025-06-06 13:49:13'),
(3, 'Suite Junior', 'Suite junior avec salon séparé', 180.00, 3, 'TV, WiFi, Minibar, Salon, Balcon', '2025-06-06 13:49:13', '2025-06-06 13:49:13'),
(4, 'Suite Présidentielle', 'Suite de luxe avec tous les équipements', 350.00, 4, 'TV, WiFi, Minibar, Salon, Jacuzzi, Butler', '2025-06-06 13:49:13', '2025-06-06 13:49:13');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `email` varchar(150) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `role` enum('admin','client','employe') NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `employe_id` int(11) DEFAULT NULL,
  `actif` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero` (`numero`),
  ADD KEY `type_chambre_id` (`type_chambre_id`),
  ADD KEY `idx_chambre_statut` (`statut`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idx_client_email` (`email`);

--
-- Indexes for table `commande_service`
--
ALTER TABLE `commande_service`
  ADD PRIMARY KEY (`id`),
  ADD KEY `reservation_id` (`reservation_id`),
  ADD KEY `service_id` (`service_id`);

--
-- Indexes for table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `organisateur_id` (`organisateur_id`),
  ADD KEY `idx_evenement_dates` (`date_debut`,`date_fin`);

--
-- Indexes for table `evenement_participant`
--
ALTER TABLE `evenement_participant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_evenement_participant` (`evenement_id`,`client_id`),
  ADD KEY `client_id` (`client_id`);

--
-- Indexes for table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero_facture` (`numero_facture`),
  ADD KEY `reservation_id` (`reservation_id`),
  ADD KEY `idx_facture_statut` (`statut`);

--
-- Indexes for table `maintenance`
--
ALTER TABLE `maintenance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `chambre_id` (`chambre_id`),
  ADD KEY `employe_id` (`employe_id`);

--
-- Indexes for table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `facture_id` (`facture_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `idx_reservation_dates` (`date_debut`,`date_fin`);

--
-- Indexes for table `reservation_chambre`
--
ALTER TABLE `reservation_chambre`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_reservation_chambre` (`reservation_id`,`chambre_id`),
  ADD KEY `chambre_id` (`chambre_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_chambre`
--
ALTER TABLE `type_chambre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `employe_id` (`employe_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `commande_service`
--
ALTER TABLE `commande_service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employe`
--
ALTER TABLE `employe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evenement_participant`
--
ALTER TABLE `evenement_participant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `maintenance`
--
ALTER TABLE `maintenance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservation_chambre`
--
ALTER TABLE `reservation_chambre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `type_chambre`
--
ALTER TABLE `type_chambre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chambre`
--
ALTER TABLE `chambre`
  ADD CONSTRAINT `chambre_ibfk_1` FOREIGN KEY (`type_chambre_id`) REFERENCES `type_chambre` (`id`);

--
-- Constraints for table `commande_service`
--
ALTER TABLE `commande_service`
  ADD CONSTRAINT `commande_service_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
  ADD CONSTRAINT `commande_service_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`);

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `evenement_ibfk_1` FOREIGN KEY (`organisateur_id`) REFERENCES `client` (`id`);

--
-- Constraints for table `evenement_participant`
--
ALTER TABLE `evenement_participant`
  ADD CONSTRAINT `evenement_participant_ibfk_1` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `evenement_participant_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Constraints for table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `facture_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`);

--
-- Constraints for table `maintenance`
--
ALTER TABLE `maintenance`
  ADD CONSTRAINT `maintenance_ibfk_1` FOREIGN KEY (`chambre_id`) REFERENCES `chambre` (`id`),
  ADD CONSTRAINT `maintenance_ibfk_2` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`id`);

--
-- Constraints for table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `paiement_ibfk_1` FOREIGN KEY (`facture_id`) REFERENCES `facture` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Constraints for table `reservation_chambre`
--
ALTER TABLE `reservation_chambre`
  ADD CONSTRAINT `reservation_chambre_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reservation_chambre_ibfk_2` FOREIGN KEY (`chambre_id`) REFERENCES `chambre` (`id`);

--
-- Constraints for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `utilisateur_ibfk_2` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
