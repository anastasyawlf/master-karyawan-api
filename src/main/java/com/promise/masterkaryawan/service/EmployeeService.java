package com.promise.masterkaryawan.service;

import com.promise.masterkaryawan.dto.EmployeeDTO;
import com.promise.masterkaryawan.model.Employee;
import com.promise.masterkaryawan.model.Position;
import com.promise.masterkaryawan.repository.EmployeeRepository;
import com.promise.masterkaryawan.repository.PositionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Custom Exception (akan kita buat setelah ini)
import com.promise.masterkaryawan.exception.ResourceNotFoundException;
import com.promise.masterkaryawan.exception.ValidationException;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    // Method untuk mengubah Entity ke DTO
    private EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setBirthDate(employee.getBirthDate());
        dto.setIdNumber(employee.getIdNumber());
        dto.setGender(employee.getGender());
        if (employee.getPosition() != null) {
            dto.setPositionId(employee.getPosition().getId());
            dto.setPositionName(employee.getPosition().getName());
        }
        return dto;
    }

    // READ (dengan pagination)
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAllNotDeleted(pageable);
        return employeePage.map(this::toDTO);
    }

    // READ by ID
    @Transactional(readOnly = true)
    public EmployeeDTO findById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .filter(emp -> emp.getIsDelete() == 0)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return toDTO(employee);
    }

    // CREATE
    @Transactional
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        // Validasi 1: NIP tidak boleh duplikat
        if (employeeRepository.existsByIdNumber(employeeDTO.getIdNumber())) {
            throw new ValidationException("Validation failed: NIP already exists!");
        }
        // Validasi 2: NIP harus angka (contoh sederhana)
        if (!employeeDTO.getIdNumber().matches("\\d+")) {
            throw new ValidationException("Validation failed: NIP must be numeric!");
        }

        Position position = positionRepository.findById(employeeDTO.getPositionId())
            .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + employeeDTO.getPositionId()));

        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getName());
        newEmployee.setBirthDate(employeeDTO.getBirthDate());
        newEmployee.setIdNumber(employeeDTO.getIdNumber());
        newEmployee.setGender(employeeDTO.getGender());
        newEmployee.setPosition(position);
        // isDelete default 0

        Employee savedEmployee = employeeRepository.save(newEmployee);
        return toDTO(savedEmployee);
    }

    // UPDATE
    @Transactional
    public EmployeeDTO update(Integer id, EmployeeDTO employeeDTO) {
        Employee employeeToUpdate = employeeRepository.findById(id)
             .filter(emp -> emp.getIsDelete() == 0)
             .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        // Validasi 1: NIP unik (tapi abaikan untuk dirinya sendiri)
        if (employeeRepository.existsByIdNumberAndIdNot(employeeDTO.getIdNumber(), id)) {
            throw new ValidationException("Validation failed: NIP already exists for another employee!");
        }
        // Validasi 2: NIP harus angka
        if (!employeeDTO.getIdNumber().matches("\\d+")) {
             throw new ValidationException("Validation failed: NIP must be numeric!");
        }

        Position position = positionRepository.findById(employeeDTO.getPositionId())
            .orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + employeeDTO.getPositionId()));

        employeeToUpdate.setName(employeeDTO.getName());
        employeeToUpdate.setBirthDate(employeeDTO.getBirthDate());
        employeeToUpdate.setIdNumber(employeeDTO.getIdNumber());
        employeeToUpdate.setGender(employeeDTO.getGender());
        employeeToUpdate.setPosition(position);

        Employee updatedEmployee = employeeRepository.save(employeeToUpdate);
        return toDTO(updatedEmployee);
    }

    // DELETE (Soft Delete)
    @Transactional
    public void delete(Integer id) {
         Employee employeeToDelete = employeeRepository.findById(id)
             .filter(emp -> emp.getIsDelete() == 0)
             .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

         employeeToDelete.setIsDelete(1);
         employeeRepository.save(employeeToDelete);
    }
}