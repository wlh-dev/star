package com.star.wlh.common.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author : wlh
 * @date Date : 2022年09月07日 09:30
 */

@Data
public class ResClassEntity {
	@ExcelProperty("序号")
	private Integer index;
	@ExcelProperty("配置领域")
	private String field;
	@ExcelProperty("领域编码")
	private String fieldCode;
	@ExcelProperty("配置分层")
	private String configurationLayer;
	@ExcelProperty("所属资产类别")
	private String assetClass;
	@ExcelProperty("配置项名称")
	private String className;
	@ExcelProperty("配置项Code")
	private String classCode;
	@ExcelProperty("唯一标识说明")
	private String uniqueIdDescription;
	@ExcelProperty("负责处室")
	private String responsibleOffice;
	@ExcelProperty("数据来源（总行）")
	private String dataSourceMaster;
	@ExcelProperty("数据来源（分行）")
	private String dataSourceSlave;

}

