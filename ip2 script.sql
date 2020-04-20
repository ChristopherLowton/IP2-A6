-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 20, 2020 at 05:54 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ip2`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `AnswerID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Text` varchar(200) NOT NULL,
  `Correct` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`AnswerID`, `QuestionID`, `Text`, `Correct`) VALUES
(1, 1, '{1, 2, 3, 4, 5, 6, ...}', 0),
(2, 1, '{..., -4, -3, -2, -1, 0, 1, 2, 3, 4, ...}', 1),
(3, 1, '{0, 1, 2, 3, 4, ...}', 0),
(4, 1, '{..., -4, -3, -2, -1, 1, 2, 3, 4, ...}', 0),
(5, 2, '{j}', 0),
(6, 2, '{c, d, f, f, g, j}', 0),
(7, 2, '{c, d, g}', 0),
(8, 2, '{f}', 1),
(9, 3, '{a, b, c, e, f, h, i, j, k, l, m}', 0),
(10, 3, '{a, b, e, f, h, i, k, l, m}', 0),
(11, 3, '{a, b, e, h, k, l, m}', 0),
(12, 3, '{a, b, e, f, h, i, j, k, l, m}', 1),
(13, 4, '{j}{(f,c),(f,d),(f,f),(f,g),(j,c),(j,d),(j,f),(j,g)}', 0),
(14, 4, '{c, d{(f,c),(f,d),(f,g),(c,j),(d,j),(g,j)}g}', 0),
(15, 4, '{(c,f),(d,f),(f,f),(g,f),(c,j),(d,j),(f,j),(g,j)}}', 1),
(16, 4, '{(c,f),(c,j),(d,f),(d,j),(f,f),(f,j)}', 0),
(17, 5, '{6}', 1),
(18, 5, '{1, 5, 7}', 0),
(19, 5, '{1, 3, 5, 6, 7, 9}', 0),
(20, 5, '{3, 3, 6, 9, 9}', 0),
(21, 7, '(5, 2) (9, 4) (13, 6)', 0),
(22, 7, '(2, 5) (9, 4) (6, 13)', 0),
(23, 7, '(2, 5) (4, 9) (6, 13) ', 1),
(24, 7, '(5, 2) (4, 9) (13, 6)', 0),
(25, 6, 'None of them are functions', 1),
(26, 6, 'All of them are functions', 0),
(27, 6, 'U and V are the only functions', 0),
(28, 6, 'W is the only function', 0),
(29, 8, 'All three relations are functions', 0),
(30, 8, 'Only relation H is a function', 1),
(31, 8, 'Relations F and G are functions', 0),
(32, 8, 'None of the relations are functions', 0),
(33, 9, 'Both functions are \'one-to-one\'', 1),
(34, 9, 'Both functions are \'onto\'', 0),
(35, 9, 'Both functions are \'one-to-one\' and \'onto\'', 0),
(36, 9, 'Neither of the functions are \'one-to-one\' or \'onto\'', 0),
(37, 10, '{xER: x<−1 and 2<=x}', 0),
(38, 10, '{xER: −1>=x>2}', 0),
(39, 10, '{xER: −1>x<=2}', 0),
(40, 10, '{xER: x<=−1 and x>=2}', 1),
(41, 11, '45', 1),
(42, 11, '-13', 0),
(43, 11, '18', 0),
(44, 11, '-19', 0),
(45, 12, '0101101', 0),
(46, 12, '1010010', 1),
(47, 12, '0101110', 0),
(48, 12, '1101110', 0),
(49, 13, '101110000', 0),
(50, 13, '010001111', 0),
(51, 13, '010010000', 1),
(52, 13, '110010000', 0),
(53, 14, '000111', 0),
(54, 14, '111001', 0),
(55, 14, '100110', 0),
(56, 14, '111000', 1),
(57, 15, '01111101 + 01111010 = 10000110', 0),
(58, 15, '10000101 + 01111101 = 00000011', 0),
(59, 15, '10000110 + 01111101 = 00000011', 1),
(60, 15, '01111101 + 01111010 = 00000101', 0);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `CategoryID` int(11) NOT NULL,
  `Title` varchar(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`CategoryID`, `Title`) VALUES
(1, 'Sets'),
(2, 'Relations'),
(3, 'Number Systems');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `QuestionID` int(11) NOT NULL,
  `CategoryID` int(11) NOT NULL,
  `Text` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QuestionID`, `CategoryID`, `Text`) VALUES
(1, 1, 'Which one of the following represents the set of Integers?'),
(2, 1, 'Let A = {c, d, f, g}, B = {f, j} and C = {d, g}. Which one of the following is correct for A n B?'),
(3, 1, 'Let  E = {a, b, c, d, e, f, g, h, i, j, k, l, m}, A = {c, d, f, g}, B = {f, j} and C = {d, g}. Which one of the following is correct for A^c u B?'),
(4, 1, 'Let A = {c, d, f, g}, B = {f, j} and C = {d, g}. Which one of the following is correct for A x B?'),
(5, 1, 'Let A = {1, 3, 5, 7, 9}, B = {3, 6, 9} and C = {2, 4, 6, 8}. Which one of the following is correct for B - A?'),
(6, 2, 'Let  A = {2, 4} and B = {1, 3, 5} and let U,V and W be the relationships defined as follows:\r\nFor all (x,y) E AXB, xWy if and only if (x,y) E {(2,5),(4,1),(2,3)}\r\nFor all (x,y) E AXB, xUy if and only if y−x>2\r\nFor all (x,y) E AxB, xVy if and only if y−1=x2\r\nWhich of the three listed relations are functions?'),
(7, 2, 'Let  A = {2,4,6} and B = {5,9,13} and let P be the relationship defined as follows: For all (x,y) E AxB, xPy, if and only if y−1=2x. Which one of the following correctly identifies the ordered pairs in P?'),
(8, 2, 'Let A = {1, 2, 3, 4) and the relations. F, G and H on AxA are defined as follows: F = {(2,3), (1,4), (2,1), (3,2), (4,4)}, G = {(3,1), (4,2), (1,1)}, H = {(2,1), (3,4), (1,4), (2,1), (4,4)}. Identify which one of the following statements is true for these relations.'),
(9, 2, 'Let the functions f and g  on NxN be defined as follows: f (n) = n + 2, g(n) = 2n. Identify which one of the following statements is true for the functions f and g.'),
(10, 2, 'Let the universal set be the set R of all real numbers. If A={xER:−3<=x<=0}, B={xER:−1<x<2} and C={xER:6<x<=8}, which of the following represents B^c?'),
(11, 3, 'Convert the binary number 101101 to base 10.'),
(12, 3, 'Convert base 10 number 82 to a binary number'),
(13, 3, 'Convert -368 base 10 number to a binary number'),
(14, 3, 'Calculate the following in binary arithmetic: 11011 + 11101'),
(15, 3, 'Calculate the expression -122 + 125 using 8 bit binary representation and identify which gives the correct expression and answer');

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `ResultID` int(11) NOT NULL,
  `CategoryID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `Score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `results`
--

INSERT INTO `results` (`ResultID`, `CategoryID`, `UserID`, `Score`) VALUES
(1, 1, 1, 4),
(2, 1, 6, 3),
(3, 1, 2, 2),
(4, 1, 10, 5),
(5, 1, 9, 5),
(6, 2, 12, 5),
(7, 2, 2, 1),
(8, 2, 4, 1),
(9, 2, 3, 3),
(10, 2, 5, 4),
(11, 3, 2, 2),
(12, 3, 4, 5),
(13, 3, 6, 5),
(14, 3, 11, 3),
(15, 3, 9, 4);

-- --------------------------------------------------------

--
-- Table structure for table `scoreboards`
--

CREATE TABLE `scoreboards` (
  `ResultID` int(11) NOT NULL,
  `CategoryID` int(11) NOT NULL,
  `Position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `scoreboards`
--

INSERT INTO `scoreboards` (`ResultID`, `CategoryID`, `Position`) VALUES
(1, 1, 2),
(2, 1, 3),
(3, 1, 4),
(4, 1, 1),
(5, 1, 1),
(6, 2, 1),
(7, 2, 4),
(8, 2, 4),
(9, 2, 3),
(10, 2, 2),
(11, 3, 4),
(12, 3, 1),
(13, 3, 1),
(14, 3, 3),
(15, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `AdminRights` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `FirstName`, `LastName`, `Email`, `Password`, `AdminRights`) VALUES
(1, 'Gottlieb', 'Sauveterre', 'gsauveterre@yahoo.co.uk', 'ebrinatent', 0),
(2, 'Adrian', 'Zegers', 'aZegers@gmail.com', 'apbontenex', 0),
(3, 'Anna', 'Auteberry', '542Auteberry@myemail.tv', 'iliancenst', 0),
(4, 'Nicola', 'Romagna', 'nicola7823@yahoo.com', 'undualcoin', 0),
(5, 'Zelda', 'Kostelecky', 'mynameiszelda92@gmail.com', 'inevericac', 0),
(6, 'Svend', 'Arkwright', 'svarkxxxxx@garfieldmail.com', 'randraterm', 0),
(7, 'Alyssa', 'Caron', 'a.caron@thedrg.co.uk', 'thytontrat', 0),
(8, 'Jonathan', 'Magorian', 'john@admin.ac.uk', 'password', 1),
(9, 'Isaac', 'Geiszler', 'isaacgeisz@outlook.com', 'aicuiterse', 0),
(10, 'Polina', 'Stanek', 'pstanek@myemail.com', 'licendfico', 0),
(11, 'Nataliya', 'O\'Brian', 'nobrian@idontlikebrians.tv', 'definitelynotbrian', 0),
(12, 'Liang', 'Zhihao', 'liangzhihao2@one.lt', 'linightemo', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`AnswerID`),
  ADD KEY `answers_question_fk` (`QuestionID`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`CategoryID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`QuestionID`),
  ADD KEY `questions_category_fk` (`CategoryID`);

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`ResultID`),
  ADD KEY `results_user_fk` (`UserID`),
  ADD KEY `results_category_fk` (`CategoryID`);

--
-- Indexes for table `scoreboards`
--
ALTER TABLE `scoreboards`
  ADD PRIMARY KEY (`ResultID`,`CategoryID`),
  ADD KEY `scoreboards_category_fk` (`CategoryID`),
  ADD KEY `scoreboards_result_fk` (`ResultID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `AnswerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `CategoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `QuestionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `ResultID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_question_fk` FOREIGN KEY (`QuestionID`) REFERENCES `questions` (`QuestionID`) ON DELETE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_category_fk` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`) ON DELETE CASCADE;

--
-- Constraints for table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_category_fk` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`) ON DELETE CASCADE,
  ADD CONSTRAINT `results_user_fk` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `scoreboards`
--
ALTER TABLE `scoreboards`
  ADD CONSTRAINT `scoreboards_category_fk` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`) ON DELETE CASCADE,
  ADD CONSTRAINT `scoreboards_result_fk` FOREIGN KEY (`ResultID`) REFERENCES `results` (`ResultID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
