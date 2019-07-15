package io.renren.modules.station.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class StationData implements Serializable {
    private static final long serialVersionUID = -2640664966133529703L;

    private String name;
    private Double longitude;
    private Double latitude;
    private Double distance;
    private Double businessVolume;
    private Double monthRevenue;

}
