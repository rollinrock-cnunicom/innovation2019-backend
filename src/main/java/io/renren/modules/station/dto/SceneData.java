package io.renren.modules.station.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SceneData implements Serializable {
    private static final long serialVersionUID = -2969015063701820119L;
    private String name;
    private Double longitude;
    private Double latitude;
    private Double radius;
    private Double mrCoverage;
}
