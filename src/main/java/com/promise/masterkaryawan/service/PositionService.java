package com.promise.masterkaryawan.service;

import com.promise.masterkaryawan.dto.PositionDTO;
import com.promise.masterkaryawan.model.Position;
import com.promise.masterkaryawan.repository.PositionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    // Constructor Injection - best practice
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    // Method untuk mengambil semua jabatan yang aktif
    public List<PositionDTO> findAll() {
        List<Position> positions = positionRepository.findAllNotDeleted();
        // Ubah (map) setiap Entity Position menjadi PositionDTO
        return positions.stream()
                .map(pos -> new PositionDTO(pos.getId(), pos.getName()))
                .collect(Collectors.toList());
    }
}