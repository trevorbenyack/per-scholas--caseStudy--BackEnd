package org.perscholas.caseStudy.service;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.perscholas.caseStudy.dao.IHouseRepository;
import org.perscholas.caseStudy.dao.IUserRepository;
import org.perscholas.caseStudy.dto.HouseDTO;
import org.perscholas.caseStudy.entity.Area;
import org.perscholas.caseStudy.entity.House;
import org.perscholas.caseStudy.util.AreaIdsToAreasListConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;

@Slf4j
@Service
public class HouseService {

    IUserRepository iUserRepository;
    IHouseRepository iHouseRepository;
    AreaService areaService;
    ModelMapper modelMapper;
    AreaIdsToAreasListConverter areaIdsToAreasListConverter;

    public HouseService(IUserRepository iUserRepository,
                        IHouseRepository iHouseRepository,
                        AreaService areaService,
                        ModelMapper modelMapper,
                        AreaIdsToAreasListConverter areaIdsToAreasListConverter) {
        this.iUserRepository = iUserRepository;
        this.iHouseRepository = iHouseRepository;
        this.areaService = areaService;
        this.modelMapper = modelMapper;
        this.areaIdsToAreasListConverter = areaIdsToAreasListConverter;

        TypeMap<HouseDTO, House> typeMap = this.modelMapper.createTypeMap(HouseDTO.class, House.class);
        typeMap.addMappings(mapper -> mapper.using(areaIdsToAreasListConverter)
                .map(HouseDTO::getAreas, House::persistAreas));
    }

    public House getHouseById(Long houseId) {
        return iHouseRepository.getById(houseId);
    }

    public House saveHouse(House house) {
        return iHouseRepository.saveAndFlush(house);
    }

    public House[] getAllHouses() {
        return iHouseRepository.findAll().toArray(new House[0]);
    }

    public House convertHouseDtoToEntity(HouseDTO houseDTO) {
        House house = modelMapper.map(houseDTO, House.class);
        log.debug("house found! house is " + house);
        return house;
    }

    public House getHouseByAreaId(String areaId) {
        Area area = areaService.getAreaById(Long.parseLong(areaId));
        return iHouseRepository.findByAreas(area);
    }

    public House updateHouse(House house) {
        log.debug("HouseService updateHouse() has been called");
        House updatedHouse = iHouseRepository.saveAndFlush(house);

        log.debug("updated House is " + updatedHouse);
        log.debug("updated Houses areas are: ");
        updatedHouse.getAreas().forEach(area -> log.debug(area.toString()));

        log.debug("Original house area's are: ");
        house.getAreas().forEach(area -> {
            log.debug(area.toString());
            Area attachedArea = areaService.getAreaById(area.getAreaId());
            if (!updatedHouse.getAreas().contains(attachedArea)) {
                log.debug("adding " + attachedArea.getAreaName() + " to house");
                updatedHouse.addArea(area);
            }
        });
        iHouseRepository.saveAndFlush(updatedHouse);

        return updatedHouse;
    }
}
