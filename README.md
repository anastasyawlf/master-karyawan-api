# Aplikasi Master Karyawan (Employee Master Full-Stack)

Ini adalah proyek aplikasi web full-stack yang dirancang untuk mengelola data master karyawan. Aplikasi ini dibangun dengan arsitektur berlapis yang modern, memisahkan antara logika backend dan frontend secara jelas.


---

## ✨ Fitur Utama

* **CRUD Penuh:** Membuat, Membaca, Memperbarui, dan Menghapus data karyawan.
* **Soft Delete:** Data tidak dihapus secara fisik dari database, melainkan ditandai sebagai tidak aktif (`is_delete = 1`), menjaga integritas data historis.
* **Arsitektur Berlapis:** Implementasi yang bersih dengan pemisahan antara Controller, Service, Repository, dan Model (Entity).
* **Validasi Sisi Server:** Pengecekan NIP (Nomor Induk Pegawai) yang unik dan validasi lainnya sebelum menyimpan data.
* **Pagination & Sorting:** Menampilkan daftar karyawan dalam bentuk halaman untuk performa yang efisien.
* **API Dokumentasi:** Rangkuman endpoint REST API yang jelas dan siap digunakan.

---

## 🛠️ Tech Stack

### Backend
* **Framework:** Spring Boot 3.x
* **Bahasa:** Java 21
* **Akses Data:** Spring Data JPA
* **Provider JPA:** Hibernate
* **Database (Development):** H2 In-Memory Database
* **Database (Production):** PostgreSQL
* **Build Tool:** Maven
* **Query Language:** HQL (Hibernate Query Language)

### Frontend 
* **Framework:** React / Vue / Svelte / Angular
* **Komunikasi:** REST API (JSON)

---

## 📖 Dokumentasi API Endpoint

Berikut adalah daftar endpoint REST API yang tersedia.

| Metode HTTP | Endpoint                  | Deskripsi                                        |
| :----------- | :------------------------ | :----------------------------------------------- |
| `GET`        | `/api/employees`          | Mengambil semua data karyawan (dengan pagination). Contoh: `/api/employees?page=0&size=10&sort=name,asc` |
| `GET`        | `/api/employees/{id}`     | Mengambil detail satu karyawan berdasarkan ID.     |
| `POST`       | `/api/employees`          | Membuat data karyawan baru.                      |
| `PUT`        | `/api/employees/{id}`     | Memperbarui data karyawan berdasarkan ID.          |
| `DELETE`     | `/api/employees/{id}`     | Menghapus (soft delete) data karyawan berdasarkan ID. |
| `GET`        | `/api/positions`          | Mengambil semua data jabatan (untuk dropdown form).|

---

## 🚀 Cara Menjalankan Proyek (Backend)

### Prasyarat
* Java Development Kit (JDK) 21 atau lebih baru.
* Apache Maven.
* Git.

### Langkah-langkah Instalasi
1.  **Clone repositori ini:**
    ```bash
    git clone https://github.com/anastasyawlf/master-karyawan-api
    ```
2.  **Masuk ke direktori proyek:**
    ```bash
    cd masterkaryawan
    ```
3.  **Jalankan aplikasi menggunakan Maven Wrapper:**
    * Untuk Windows (Command Prompt / PowerShell):
        ```bash
        ./mvnw.cmd spring-boot:run
        ```
    * Untuk Linux / macOS:
        ```bash
        ./mvnw spring-boot:run
        ```
4.  Aplikasi akan berjalan di `http://localhost:8080`.

### Database Development
* Aplikasi menggunakan database **H2 In-Memory** secara default saat development.
* Anda dapat mengakses **H2 Console** untuk melihat tabel dan data melalui browser di: `http://localhost:8080/h2-console`
* Gunakan JDBC URL: `jdbc:h2:mem:masterkaryawan` untuk login.

---
*Dibuat dengan ❤️ oleh anastasyawlf.*
