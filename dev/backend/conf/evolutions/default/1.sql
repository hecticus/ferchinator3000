# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

-- init script create procs
-- Inital script to create stored procedures etc for mysql platform
DROP PROCEDURE IF EXISTS usp_ebean_drop_foreign_keys;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_foreign_keys TABLE, COLUMN
-- deletes all constraints and foreign keys referring to TABLE.COLUMN
--
CREATE PROCEDURE usp_ebean_drop_foreign_keys(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE c_fk_name CHAR(255);
  DECLARE curs CURSOR FOR SELECT CONSTRAINT_NAME from information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE() and TABLE_NAME = p_table_name and COLUMN_NAME = p_column_name
      AND REFERENCED_TABLE_NAME IS NOT NULL;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

  OPEN curs;

  read_loop: LOOP
    FETCH curs INTO c_fk_name;
    IF done THEN
      LEAVE read_loop;
    END IF;
    SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP FOREIGN KEY ', c_fk_name);
    PREPARE stmt FROM @sql;
    EXECUTE stmt;
  END LOOP;

  CLOSE curs;
END
$$

DROP PROCEDURE IF EXISTS usp_ebean_drop_column;

delimiter $$
--
-- PROCEDURE: usp_ebean_drop_column TABLE, COLUMN
-- deletes the column and ensures that all indices and constraints are dropped first
--
CREATE PROCEDURE usp_ebean_drop_column(IN p_table_name VARCHAR(255), IN p_column_name VARCHAR(255))
BEGIN
  CALL usp_ebean_drop_foreign_keys(p_table_name, p_column_name);
  SET @sql = CONCAT('ALTER TABLE ', p_table_name, ' DROP COLUMN ', p_column_name);
  PREPARE stmt FROM @sql;
  EXECUTE stmt;
END
$$
create table client (
  id                            integer auto_increment not null,
  msisdn                        varchar(255),
  promo_id                      varchar(255),
  carrier                       varchar(255),
  country                       varchar(255),
  constraint pk_client primary key (id)
);

create table premio (
  id                            integer auto_increment not null,
  promo_id                      integer not null,
  nombre                        varchar(255),
  orden                         integer not null,
  suplente                      integer not null,
  constraint pk_premio primary key (id)
);

create table promocion (
  id                            integer auto_increment not null,
  nombre                        varchar(255),
  fecha_inicio                  varchar(255),
  fecha_fin                     varchar(255),
  activa                        integer not null,
  operadora_id                  varchar(255),
  business_id                   varchar(255),
  premiacion                    varchar(255),
  constraint pk_promocion primary key (id)
);


# --- !Downs

drop table if exists client;

drop table if exists premio;

drop table if exists promocion;

