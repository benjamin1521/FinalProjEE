CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_type` varchar(255) DEFAULT NULL,
  `full_name_en` varchar(255) DEFAULT NULL,
  `full_name_ua` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `reports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL,
  `bank_bic` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `inn` varchar(255) DEFAULT NULL,
  `kpp` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_short` varchar(255) DEFAULT NULL,
  `oktmo` varchar(255) DEFAULT NULL,
  `parent_address` varchar(255) DEFAULT NULL,
  `parent_code` varchar(255) DEFAULT NULL,
  `parent_name` varchar(255) DEFAULT NULL,
  `parent_phone` varchar(255) DEFAULT NULL,
  `payment_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `inspector_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiccom7gk3c0d6kla0cbbw3kc9` (`client_id`),
  KEY `FKee1dvqioc4u5y9h76xwv7dybk` (`inspector_id`),
  CONSTRAINT `FKee1dvqioc4u5y9h76xwv7dybk` FOREIGN KEY (`inspector_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKiccom7gk3c0d6kla0cbbw3kc9` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `report_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `reports_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkreb95f5excpkk5b8qmvubgf5` (`reports_id`),
  KEY `FKbygy385322crtlkn6m9qrgbhy` (`user_id`),
  CONSTRAINT `FKbygy385322crtlkn6m9qrgbhy` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkreb95f5excpkk5b8qmvubgf5` FOREIGN KEY (`reports_id`) REFERENCES `reports` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `users`
(`id`,`client_type`,`full_name_en`,`full_name_ua`,`password`,`role`,`username`)
VALUES
('1', 'Physical', 'Petro Petrovich', 'Петро Петрович', 'q', 'Client', 'c'),
('2', 'Physical', 'Ivan Ivanovich', 'Іван Іванович', 'q', 'Inspector', 's'),
('3', 'Physical', 'Sidr Sidrovich', 'Сидр Сидрович', 'q', 'Inspector', 's2'),
('5', 'Physical', 'Semen Semenovich', 'Семен Семенович', 'q', 'Client', 'c2');

INSERT INTO `reports`
(`id`,`address`,`bank_account`,`bank_bic`,`bank_name`,`code`,`inn`,`kpp`,`name`,`name_short`,`oktmo`,`parent_address`,`parent_code`,`parent_name`,`parent_phone`,`payment_name`,`phone`,`status`,`client_id`,`inspector_id`)
VALUES
('43', 'Address 123', 'bank acc', '9212', 'Public Bank', '1213', '13134', '4141', 'myReport1', 'mr1', 'Some Oktmo', 'Address 415', '9419', 'Some name', '24242', 'Cash', '61363', 'Active', '1', '3'),
('44', 'Address 123', 'Any acc', '314', 'Protected Bank', '5135', '13134', '1351', 'importantReport2', 'ir2', 'Any OKTMO', 'Address 415', '9419', 'Some name', '24242', 'Card', '15315', 'Active', '1', '3'),
('45', '', '', '', '', '', '', '', 'sampleReport', '', '', '', '', '', '', '', '', 'Active', '1', '2');
