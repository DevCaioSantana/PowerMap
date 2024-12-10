package com.example.powermap.mapper;

import com.example.powermap.model.ChargingStation;
import com.example.powermap.model.DTO.RouteDTO;
import com.example.powermap.model.DTO.UserDTO;
import com.example.powermap.model.Route;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteDTO toRouteDTO(Route route);
    List<RouteDTO> toRouteDTO(List<Route> route);
}
