-- =========================================================
-- Database: db_warnet_management
-- Project : ProjectPemrog2_MErsyadB (Aplikasi Manajemen Warnet)
-- =========================================================
-- Cara pakai:
-- 1. Buka phpMyAdmin / HeidiSQL / MySQL Workbench (pastikan MySQL server sudah jalan, mis. via XAMPP/Laragon)
-- 2. Jalankan seluruh isi file ini (Import / Execute SQL)
-- 3. Database "db_warnet_management" beserta seluruh tabel akan otomatis dibuat
-- =========================================================

CREATE DATABASE IF NOT EXISTS db_warnet_management;
USE db_warnet_management;

-- ---------------------------------------------------------
-- Tabel User (Admin / Operator)
-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_user (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    nama_user VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'Operator'
);

-- ---------------------------------------------------------
-- Tabel Komputer
-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_komputer (
    id_komputer INT AUTO_INCREMENT PRIMARY KEY,
    nomor_komputer VARCHAR(20) NOT NULL,
    status_komputer VARCHAR(20) NOT NULL DEFAULT 'Aktif',
    keterangan VARCHAR(255)
);

-- ---------------------------------------------------------
-- Tabel Pelanggan
-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_pelanggan (
    id_pelanggan INT AUTO_INCREMENT PRIMARY KEY,
    nama_pelanggan VARCHAR(100) NOT NULL,
    no_hp VARCHAR(20),
    alamat VARCHAR(255)
);

-- ---------------------------------------------------------
-- Tabel Tarif
-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_tarif (
    id_tarif INT AUTO_INCREMENT PRIMARY KEY,
    nama_tarif VARCHAR(100) NOT NULL,
    harga_per_jam DECIMAL(12,2) NOT NULL,
    keterangan VARCHAR(255)
);

-- ---------------------------------------------------------
-- Tabel Transaksi (Billing)
-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS tb_transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    id_komputer INT NOT NULL,
    id_pelanggan INT NULL,
    id_tarif INT NOT NULL,
    waktu_mulai DATETIME NOT NULL,
    waktu_selesai DATETIME NULL,
    durasi_menit INT NULL,
    total_bayar DECIMAL(12,2) NULL,
    status_transaksi VARCHAR(20) NOT NULL DEFAULT 'Berlangsung',
    status_bayar VARCHAR(20) NOT NULL DEFAULT 'Belum Bayar',
    uang_bayar DECIMAL(12,2) NULL,
    kembalian DECIMAL(12,2) NULL,
    CONSTRAINT fk_transaksi_komputer FOREIGN KEY (id_komputer) REFERENCES tb_komputer(id_komputer),
    CONSTRAINT fk_transaksi_pelanggan FOREIGN KEY (id_pelanggan) REFERENCES tb_pelanggan(id_pelanggan) ON DELETE SET NULL,
    CONSTRAINT fk_transaksi_tarif FOREIGN KEY (id_tarif) REFERENCES tb_tarif(id_tarif)
);

-- =========================================================
-- Data awal (contoh) supaya aplikasi bisa langsung dicoba
-- =========================================================

-- User default untuk login (username: admin / password: admin)
INSERT INTO tb_user (nama_user, username, password, role) VALUES
('Administrator', 'admin', 'admin', 'Admin');

-- Beberapa komputer contoh
INSERT INTO tb_komputer (nomor_komputer, status_komputer, keterangan) VALUES
('PC-01', 'Aktif', 'Lantai 1'),
('PC-02', 'Aktif', 'Lantai 1'),
('PC-03', 'Aktif', 'Lantai 1'),
('PC-04', 'Maintenance', 'Monitor rusak');

-- Beberapa pelanggan contoh
INSERT INTO tb_pelanggan (nama_pelanggan, no_hp, alamat) VALUES
('Budi Santoso', '081234567890', 'Jl. Merdeka No.1'),
('Siti Aminah', '081298765432', 'Jl. Sudirman No.10');

-- Tarif contoh
INSERT INTO tb_tarif (nama_tarif, harga_per_jam, keterangan) VALUES
('Reguler', 4000, 'Tarif normal per jam'),
('VIP', 7000, 'Komputer spesifikasi tinggi'),
('Member', 3500, 'Khusus pelanggan member');
