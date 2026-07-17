-- ==========================================
-- Create Dogs Database
-- ==========================================

CREATE DATABASE IF NOT EXISTS dogsdb;


-- ==========================================
-- Create Application User
-- ==========================================

CREATE USER IF NOT EXISTS 'dogs_user'@'localhost'
IDENTIFIED BY 'dogs_password';

-- ==========================================
-- Grant Privileges
-- ==========================================

GRANT ALL PRIVILEGES
ON dogsdb.*
TO 'dogs_user'@'localhost';

FLUSH PRIVILEGES;

-- ==========================================
-- Use Database
-- ==========================================

USE dogsdb;