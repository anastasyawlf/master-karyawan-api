package com.promise.masterkaryawan.dto;

import lombok.Data;
import java.util.Date;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private Date birthDate;
    private Integer positionId; // Untuk menerima ID jabatan dari form
    private String positionName; // Untuk menampilkan nama jabatan di tabel
    private String idNumber; // NIP
    private Integer gender;
}