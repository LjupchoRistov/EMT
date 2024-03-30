package com.emt.model.mapper;

import com.emt.model.Host;
import com.emt.model.dto.HostDto;


public class HostMapper {
    public static HostDto hostDtoMapper(Host host){
        return HostDto.builder()
                .id(host.getId())
                .name(host.getName())
                .surname(host.getSurname())
                .countryId(host.getId())
                .build();
    }
}
