package io.renren.modules.station.dto;


import io.renren.modules.station.entity.TBaseStation;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 */
@Data
public class StationDTO implements Serializable {
    private static final long serialVersionUID = 5352793532722238011L;

    private Double longitude;
    private Double latitude;
    private Double deviationDegree;
    private Double degree;
    private Double radius;

    public static StationDTO fromEntity(TBaseStation stationEntity) {
        Objects.requireNonNull(stationEntity);
        StationDTO station = new StationDTO();
        station.setLongitude(stationEntity.getGdLongitude());
        station.setLatitude(stationEntity.getGdLatitude());
        station.setDeviationDegree(stationEntity.getDeviationDegree());
        boolean indoor = stationEntity.getDeviationDegree() == 360d;
        station.setDegree(indoor ? 360d : 30d);
        station.setRadius(indoor ? 15d : 30d);

        return station;
    }
}
