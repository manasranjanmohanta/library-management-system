CREATE TABLE `books` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `isbn` varchar(20) UNIQUE NOT NULL,
  `author_id` bigint NOT NULL,
  `genre` varchar(50),
  `available` bool DEFAULT true,
  `borrowed_by` bigint,
  `borrowed_date` date,
  `due_date` date
);

CREATE TABLE `authors` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) UNIQUE NOT NULL
);

CREATE TABLE `borrowers` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) UNIQUE NOT NULL,
  `phone` varchar(15) NOT NULL
);

ALTER TABLE `books` ADD FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`);

ALTER TABLE `books` ADD FOREIGN KEY (`borrowed_by`) REFERENCES `borrowers` (`id`);
