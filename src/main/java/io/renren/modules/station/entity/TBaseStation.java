package io.renren.modules.station.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cj
 * @since 2019-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TBaseStation对象", description="")
public class TBaseStation implements Serializable {

private static final long serialVersionUID=1L;

    private String baseStationName;

    private Double ci;

    private Double communityLatitude;

    private Double communityLongitude;

    private String communityName;

    private Double deviationDegree;

    private String district;

    private Double enodeBid;

    private String physicalNodeId;

    private Double tac;

    private Double radius;

    private Double angle;

    private Double nettype;

    private Double gdLatitude;

    private Double gdLongitude;

    private String sitetype;


}
