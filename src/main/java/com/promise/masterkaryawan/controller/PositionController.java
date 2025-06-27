package com.promise.masterkaryawan.controller;

import com.promise.masterkaryawan.dto.PositionDTO;
import com.promise.masterkaryawan.service.PositionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Menandakan ini adalah REST API Controller
@RequestMapping("/api/positions") // Base URL untuk semua endpoint di kelas ini
@CrossOrigin("*") // Mengizinkan request dari frontend (domain/port yang berbeda)
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    // Endpoint untuk GET /api/positions
    @GetMapping
    public List<PositionDTO> getAllPositions() {
        return positionService.findAll();
    }
}