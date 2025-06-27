package com.promise.masterkaryawan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Anotasi Lombok: Membuat getter, setter, toString(), equals(), hashCode()
@NoArgsConstructor // Anotasi Lombok: Membuat constructor tanpa argumen
@AllArgsConstructor // Anotasi Lombok: Membuat constructor dengan semua argumen

@Entity // Menandakan bahwa kelas ini adalah sebuah JPA Entity
@Table(name = "t1_position") // Memetakan kelas ini ke tabel database bernama "t1_position"
public class Position {

    @Id // Menandakan bahwa field ini adalah Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Mengatur agar ID di-generate otomatis oleh database (auto-increment)
    private Integer id;

    @Column(name = "code", length = 50, nullable = false, unique = true) // Memetakan ke kolom 'code'
    private String code;

    @Column(name = "name", length = 100, nullable = false) // Memetakan ke kolom 'name'
    private String name;

    @Column(name = "is_delete", nullable = false) // Memetakan ke kolom 'is_delete'
    private Integer isDelete = 0; // Default value saat objek baru dibuat
}