//package com.emt.model.mapper;
//
//import com.emt.model.Country;
//import com.emt.model.Host;
//import com.emt.model.dto.CountryDto;
//import com.emt.model.dto.HostDto;
//
//public class CountryMapper {
//    public static Country countryMapper(CountryDto countryDto){
//        return Country.builder()
//                .id(countryDto.getId())
//                .name(countryDto.getName())
//                .build();
//    }
//
//    public static CountryDto countryDtoMapper(Country country){
//        return CountryDto.builder()
//                .id(country.getId())
//                .name(country.getName())
//                .build();
//    }
//}
