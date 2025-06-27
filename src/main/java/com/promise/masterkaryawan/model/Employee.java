package com.promise.masterkaryawan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t2_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE) // Menentukan bahwa kita hanya menyimpan bagian tanggal (tanpa waktu)
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY) // Mendefinisikan relasi Many-to-One (Banyak karyawan bisa memiliki satu jabatan)
    @JoinColumn(name = "position_id", referencedColumnName = "id") // Mendefinisikan foreign key
    private Position position;

    @Column(name = "id_number", nullable = false, unique = true) // id_number adalah NIP
    private String idNumber; // Kita gunakan String untuk NIP agar lebih fleksibel, validasi angka dilakukan di service

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "is_delete", nullable = false)
    private Integer isDelete = 0;
}