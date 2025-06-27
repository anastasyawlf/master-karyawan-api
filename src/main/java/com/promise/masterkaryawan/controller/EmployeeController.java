package com.promise.masterkaryawan.controller;

import com.promise.masterkaryawan.dto.EmployeeDTO;
import com.promise.masterkaryawan.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Endpoint GET /api/employees (dengan pagination)
    // Contoh URL: /api/employees?page=0&size=10&sort=name,asc
    @GetMapping
    public Page<EmployeeDTO> getAllEmployees(Pageable pageable) {
        return employeeService.findAll(pageable);
    }

    // Endpoint GET /api/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        EmployeeDTO employeeDTO = employeeService.findById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    // Endpoint POST /api/employees
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Mengembalikan status 201 Created
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        // @RequestBody akan otomatis mengubah JSON dari request menjadi objek EmployeeDTO
        return employeeService.create(employeeDTO);
    }

    // Endpoint PUT /api/employees/{id}
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.update(id, employeeDTO);
    }

    // Endpoint DELETE /api/employees/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
        // Mengembalikan status 204 No Content, standar untuk delete yang berhasil
        return ResponseEntity.noContent().build();
    }
}