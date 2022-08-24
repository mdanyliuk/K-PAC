CREATE DATABASE IF NOT EXISTS kpacapp;

USE kpacapp;

ALTER DATABASE kpacapp
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS kpac (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(250),
  description VARCHAR(2000),
  creation_date DATE,
  INDEX(title)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS kset (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(250),
  INDEX(title)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pac_set (
  kset_id INT(4) UNSIGNED NOT NULL,
  kpac_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (kset_id) REFERENCES kset(id) ON DELETE CASCADE,
  FOREIGN KEY (kpac_id) REFERENCES kpac(id) ON DELETE CASCADE,
  UNIQUE (kset_id,kpac_id)
) engine=InnoDB;