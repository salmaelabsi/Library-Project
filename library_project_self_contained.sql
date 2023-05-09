-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: library_project
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `BookID` int NOT NULL AUTO_INCREMENT,
  `BookTitle` varchar(45) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `BookPrice` decimal(10,2) NOT NULL,
  `BookEdition` varchar(45) NOT NULL,
  `BookCategory` varchar(45) NOT NULL,
  `BookFloor` int NOT NULL,
  `BookShelf` int NOT NULL,
  `ISBN` varchar(45) NOT NULL,
  PRIMARY KEY (`BookID`),
  UNIQUE KEY `table1_id_unique` (`BookID`),
  KEY `BookFloor_foreignKey` (`BookFloor`),
  KEY `shelfID_foreignKey` (`BookShelf`),
  CONSTRAINT `BookFloor_foreignKey` FOREIGN KEY (`BookFloor`) REFERENCES `floors` (`FloorID`),
  CONSTRAINT `BookShelf_foreignKey` FOREIGN KEY (`BookShelf`) REFERENCES `shelves` (`ShelfID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'International Finance','Paul R. Krugman',59.99,'12th','Business',1,1,'9781292417004'),(2,'Marketing Management','Philip Kotler',68.25,'4th','Business',1,1,'9781292248448'),(3,'The Diary of a Young Girl','Anne Frank',20.00,'New Edition','History',1,2,'9780141315188'),(4,'Killing Hope','William Blum',17.99,'Updated Edition','History',1,2,'9781350348196'),(5,'An Introduction to Moral Philosophy','Johnathan Wolff',22.50,'2nd','Philosophy',1,3,'9780393428179'),(6,'Early Greek Philosophy','Jonathan Barnes',50.75,'2nd','Philosophy',1,3,'9780140448153'),(7,'Global Poverty','David Hulme',35.99,'2nd','Politics',1,4,'9781138826823'),(8,'Global Terrorism','James M. Lutz, Brenda J. Lutz',35.99,'4th','Politics',1,4,'9780815357353'),(9,'Social Work Theories in Context','Karen Healy',25.25,'3rd','Social Sciences',1,5,'9781350321571'),(10,'The Globalization of World Politics','John Baylis',39.99,'8th','Social Sciences',1,5,'9780198825548'),(11,'C# Programming in Easy Steps','Mike McGrath',10.25,'3rd','Computing',2,6,'9781840789737'),(12,'Big Data, Big Design','Helen Armstrong',45.99,'1st','Computing',2,6,'9781616899158'),(13,'Android Programmig','Kristin Marsicano, Brian Gardner',27.99,'5th','Computing',2,6,'9780137645541'),(14,'Deep Learnig with Python','FranÃ§ois Chollet',38.75,'2nd','Computing',2,6,'9781617296864'),(15,'Calculus: A complete introduction','Paul Abbott, Hugh Neill',46.50,'New Edition','Mathematics',2,7,'9781473678446'),(16,'Elementary Technical Mathematics','Dale Ewen, C. Robert Nelson',55.00,'12th','Mathematics',2,7,'9781337630580'),(17,'Inorganic Chemistry','Martin Weller, Tina Overton',57.00,'7th','Science',2,8,'9780198768128'),(18,'A First Course in General Relativity','Bernard F. Schutz',44.99,'3rd','Sciece',2,8,'9781108492676'),(19,'High Resolution Optical Satellite Imagery','Ian J. Dowman, Karsten Jacobsen',79.99,'2nd','Technology',2,9,'9781849953900'),(20,'Elements of Electromagnetics','Matthew N. O. Sadiku, Sudarshan Nelatury',59.99,'7th','Technology',2,9,'9780190698621'),(21,'Oxford English Mini Dictionary','Charlotte Buxton',20.50,'8th','Language',3,11,'9780199640966'),(22,'Oxford A-Z of Grammar and Punctuation','John Seely',25.75,'3rd','Language',3,11,'9780198849889'),(23,'Troublesome Words','Bill Bryson',29.99,'3rd','Language',3,12,'9780141040394'),(24,'Easywriter','Andrea A Lunsford',35.50,'8th','Language',3,12,'9781319244224'),(25,'How to Write a Lot: Academic Writing Guide','Paul J. Silvia',14.99,'2nd','Language',3,12,'9781433829734'),(26,'Pride and Prejudice','Jane Austen',39.99,'New Edition','Literature',3,13,'9780198826736'),(27,'A Tale of Two Cities','Charles Dickens, Andrew Sanders',31.75,'Reprint','Literature',3,13,'9780199536238'),(28,'Hamlet','William Shakespeare',30.00,'Revised Edition','Literature',3,13,'9781472518385'),(29,'Macbeth','William Shakespeare',30.00,'2nd','Literature',3,13,'9780393923261'),(30,'A View from the Bridge: A Play in Two Acts','Arthur Miller',25.99,'New Edition','Literature',3,13,'9780141189963');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `CustomerFirstName` varchar(45) NOT NULL,
  `CustomerLastName` varchar(45) NOT NULL,
  `IsMaxLoans` tinyint NOT NULL DEFAULT '0',
  `FineAmount` decimal(5,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1000,'Sam','Winchester',0,0.00),(1001,'Ahmed','Mohsen',0,0.00),(1002,'Sarah','Mohamed',1,0.00),(1003,'Ela','Yiğit',0,10.40),(1004,'Mehmet','Can',0,10.10),(1005,'Sally','Wally',0,0.00);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floors`
--

DROP TABLE IF EXISTS `floors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floors` (
  `FloorID` int NOT NULL AUTO_INCREMENT,
  `FloorName` varchar(45) NOT NULL,
  PRIMARY KEY (`FloorID`),
  CONSTRAINT `ShelfID_foreignKey` FOREIGN KEY (`FloorID`) REFERENCES `shelves` (`ShelfID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floors`
--

LOCK TABLES `floors` WRITE;
/*!40000 ALTER TABLE `floors` DISABLE KEYS */;
INSERT INTO `floors` VALUES (1,'Business and Humanities'),(2,'STEM'),(3,'Language and Literature');
/*!40000 ALTER TABLE `floors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarians`
--

DROP TABLE IF EXISTS `librarians`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarians` (
  `LibrarianID` int NOT NULL AUTO_INCREMENT,
  `LibrarianName` varchar(45) NOT NULL,
  PRIMARY KEY (`LibrarianID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarians`
--

LOCK TABLES `librarians` WRITE;
/*!40000 ALTER TABLE `librarians` DISABLE KEYS */;
INSERT INTO `librarians` VALUES (1,'Abderrahmane'),(2,'Omar'),(3,'Muhsin'),(4,'Salma');
/*!40000 ALTER TABLE `librarians` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loans` (
  `LoanID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int NOT NULL,
  `BookID` int NOT NULL,
  `LoanDate` datetime NOT NULL,
  `ExpectedReturnDate` datetime NOT NULL,
  `ActualReturnDate` datetime DEFAULT NULL,
  `LibrarianID` int NOT NULL,
  PRIMARY KEY (`LoanID`),
  KEY `BookID_foreignKey` (`BookID`),
  KEY `CustomerID_foreignKey_idx` (`CustomerID`),
  KEY `libID_fk_idx` (`LibrarianID`),
  CONSTRAINT `BookID_foreignKey` FOREIGN KEY (`BookID`) REFERENCES `books` (`BookID`),
  CONSTRAINT `CustomerID_foreignKey` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`),
  CONSTRAINT `LibrarianID_foreignKey` FOREIGN KEY (`LibrarianID`) REFERENCES `librarians` (`LibrarianID`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (100,1002,21,'2023-04-03 00:00:00','2023-04-18 00:00:00','2023-04-17 00:00:00',4),(101,1004,9,'2023-04-20 00:00:00','2023-05-05 00:00:00','2023-05-06 00:00:00',1),(102,1001,15,'2023-05-01 00:00:00','2023-05-16 00:00:00','2023-05-10 00:00:00',3),(103,1002,2,'2023-05-05 00:00:00','2023-05-20 00:00:00',NULL,2),(104,1002,6,'2023-05-05 00:00:00','2023-05-20 00:00:00',NULL,2),(105,1003,30,'2023-03-09 00:00:00','2023-04-24 00:00:00','2023-05-01 00:00:00',4),(106,1000,11,'2023-03-12 00:00:00','2023-03-27 00:00:00','2023-03-20 00:00:00',1);
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shelves`
--

DROP TABLE IF EXISTS `shelves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shelves` (
  `ShelfID` int NOT NULL AUTO_INCREMENT,
  `ShelfName` varchar(45) NOT NULL,
  `FloorID` int NOT NULL,
  PRIMARY KEY (`ShelfID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shelves`
--

LOCK TABLES `shelves` WRITE;
/*!40000 ALTER TABLE `shelves` DISABLE KEYS */;
INSERT INTO `shelves` VALUES (1,'a',1),(2,'b',1),(3,'c',1),(4,'d',1),(5,'e',1),(6,'f',2),(7,'g',2),(8,'h',2),(9,'i',2),(10,'j',2),(11,'k',3),(12,'l',3),(13,'m ',3),(14,'n',3),(15,'o',3);
/*!40000 ALTER TABLE `shelves` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-09 19:22:03
