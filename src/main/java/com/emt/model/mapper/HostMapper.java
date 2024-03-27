package com.emt.model.mapper;

import com.emt.model.Host;
import com.emt.model.dto.HostDto;


public class HostMapper {
    public static Host hostMapper(HostDto hostDto){
        return Host.builder()
                .id(hostDto.getId())
                .name(hostDto.getName())
                .surname(hostDto.getSurname())
                .country(hostDto.getCountry())
                .build();
    }

    public static HostDto hostDtoMapper(Host host){
        return HostDto.builder()
                .id(host.getId())
                .name(host.getName())
                .surname(host.getSurname())
                .country(host.getCountry())
                .build();
    }
}
