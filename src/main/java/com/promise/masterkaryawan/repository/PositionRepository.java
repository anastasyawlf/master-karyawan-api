package com.promise.masterkaryawan.repository;

import com.promise.masterkaryawan.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Menandakan bahwa ini adalah sebuah Spring Bean untuk akses data
public interface PositionRepository extends JpaRepository<Position, Integer> {

    /**
     * HQL Query untuk mengambil semua jabatan yang belum dihapus (is_delete = 0).
     * Hasilnya diurutkan berdasarkan nama secara menaik (ASC).
     *
     * HQL menggunakan nama Entity (Position) dan field (isDelete, name),
     * bukan nama tabel (t1_position) atau kolom.
     */
    @Query("SELECT p FROM Position p WHERE p.isDelete = 0 ORDER BY p.name ASC")
    List<Position> findAllNotDeleted();
}