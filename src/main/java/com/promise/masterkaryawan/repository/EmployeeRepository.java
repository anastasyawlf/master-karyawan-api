package com.promise.masterkaryawan.repository;

import com.promise.masterkaryawan.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * HQL Query untuk mengambil semua karyawan yang belum dihapus,
     * dengan dukungan pagination (halaman).
     * Pageable adalah objek dari Spring yang membawa info halaman ke berapa,
     * berapa data per halaman, dan sorting.
     */
    @Query(value = "SELECT e FROM Employee e WHERE e.isDelete = 0",
           countQuery = "SELECT count(e) FROM Employee e WHERE e.isDelete = 0")
    Page<Employee> findAllNotDeleted(Pageable pageable);


    /**
     * HQL Query untuk mengecek apakah sebuah NIP (idNumber) sudah ada di database.
     * Query ini lebih efisien karena hanya me-return true/false, bukan seluruh data employee.
     * :idNumber adalah named parameter yang nilainya diambil dari argumen metode.
     */
    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.idNumber = :idNumber AND e.isDelete = 0")
    boolean existsByIdNumber(@Param("idNumber") String idNumber);


    /**
     * HQL Query untuk mengecek keunikan NIP saat proses UPDATE.
     * Query ini mengecek apakah ada NIP yang sama, TETAPI milik karyawan dengan ID yang BERBEDA.
     * Ini penting agar tidak terjadi error "NIP sudah ada" pada data karyawan itu sendiri.
     */
    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.idNumber = :idNumber AND e.id <> :id AND e.isDelete = 0")
    boolean existsByIdNumberAndIdNot(@Param("idNumber") String idNumber, @Param("id") Integer id);
}