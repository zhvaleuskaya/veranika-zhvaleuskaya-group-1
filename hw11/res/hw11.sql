SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hw11`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin NOT NULL,
  `category_id` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=104 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `category_id`) VALUES
(1, 'Guitars', NULL),
(2, 'Accessories', NULL),
(3, 'Amps', NULL),
(101, 'Acoustic guitars', 1),
(102, 'Electric guitars', 1),
(103, 'Bass guitars', 1);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) COLLATE utf8_bin NOT NULL,
  `category_id` int(4) DEFAULT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3003 ;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `category_id`, `description`) VALUES
(401, 'Cort GB-PB50', 103, 'Cort GB-PB50'),
(402, 'J&D 4B', 103, 'J&D 4B'),
(501, 'Aria XL-DLX', 102, 'Aria XL-DLX, floyd rose, tone, volume'),
(502, 'Cort G240', 102, 'Cort G240'),
(503, 'Cort Viva Gold II', 102, 'Cort Viva Gold II'),
(504, 'Washburn XMSTD2PWH', 102, 'Washburn XMSTD2PWH'),
(601, 'Rogue Hawaiian Soprano Ukulele', 1, 'Rogue Hawaiian Soprano Ukulele'),
(1001, 'Rogue RA-090 Dreadnought', 101, 'Rogue RA-090 Dreadnought Acoustic Guitar'),
(1002, 'Hohner HW03 3/4 Sized Steel String', 101, 'Hohner HW03 3/4 Sized Steel String Acoustic Guitar'),
(2001, 'D''Addario EXL110 Nickel Light Electric Guitar Strings 10-Pack', 2, 'D''Addario EXL110 Nickel Light Electric Guitar Strings 10-Pack'),
(2002, 'EMG -ZW Zakk Wylde 81/85', 2, 'EMG -ZW Zakk Wylde 81/85 Humbucker Set'),
(2003, 'Mogami Gold Series Instrument Cable', 2, 'Mogami Gold Series Instrument Cable'),
(3001, 'Fender Vintage Reissue', 3, 'Fender Vintage Reissue ''65 Twin Reverb Guitar Combo Amp'),
(3002, 'Line 6 Spider Jam 75W', 3, 'Line 6 Spider Jam 75W 1x12 Guitar Combo Amp');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
