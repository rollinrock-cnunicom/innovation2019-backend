package io.renren.modules.station.entity;

import java.time.LocalDate;
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
@ApiModel(value="Sitetraffic对象", description="")
public class Sitetraffic implements Serializable {

private static final long serialVersionUID=1L;

    private String siteName;

    private LocalDate month;

    private Double trafficErl;

    private Double trafficGb;

    private Double communityLatitude;

    private Double communityLongitude;

    private Double netType;

    private Double gdLatitude;

    private Double gdLongitude;


}
