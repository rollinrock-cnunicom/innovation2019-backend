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
 * @since 2019-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AllyStation对象", description="")
public class AllyStation implements Serializable {

private static final long serialVersionUID=1L;

    private String stationName;

    private Double rpcRank;

    private Double rank;

    private Double communityLatitude;

    private Double communityLongitude;

    private Double gdLatitude;

    private Double gdLongitude;


}
