package com.projectservice.serviceapp.service;

import com.projectservice.serviceapp.GenericSpecification;
import com.projectservice.serviceapp.Mapper;
import com.projectservice.serviceapp.dto.SparePartDto;
import com.projectservice.serviceapp.model.SparePart;
import com.projectservice.serviceapp.repository.SparePartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SparePartService {

    @Autowired
    private SparePartRepository sparePartRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private GenericSpecification genericSpecification;

    public Page<SparePartDto> getAll(Pageable pageable, Map<String, String> params) {
        Page<SparePart> all = sparePartRepository.findAll(genericSpecification.hasParameter(params), pageable);
        List<SparePartDto> dtoList = mapper.mapToDtoList(all.getContent(), SparePartDto.class);

        return new PageImpl<>(dtoList, all.getPageable(), all.getTotalElements());
    }

    public SparePartDto getById(Integer id) {
        Optional<SparePart> byId = sparePartRepository.findById(id);
        if (byId.isPresent()){
            return (SparePartDto) mapper.mapToDto(byId.get(), SparePartDto.class);
        }else {
            throw new NoSuchElementException();
        }
    }

    public SparePartDto save (SparePartDto sparePartDto){
        SparePart sparePartForSave = (SparePart) mapper.mapToEntity(sparePartDto, SparePart.class);
        SparePart savedSparePart = sparePartRepository.save(sparePartForSave);

        return (SparePartDto) mapper.mapToDto(savedSparePart, SparePartDto.class);
    }

}
