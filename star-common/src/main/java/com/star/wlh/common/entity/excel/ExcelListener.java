package com.star.wlh.common.entity.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author wlh
 */
public class ExcelListener extends AnalysisEventListener<ResClassEntity> {
    private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

    /**
	 * 一行一行去读取excel中的内容(表头不会去读取)
	 */
	@Override public void invoke(ResClassEntity data, AnalysisContext context) {
      //logger.info("获取结果{}",data);
		System.out.println(("db.resClass.updateMany({\"code\":\""+data.getClassCode()+"\"},{$set: {\"sortIndex\":NumberInt("+data.getIndex()*10+")}});"));
	}
	/**
	 * 读取表头中的内容
	 */
	@Override public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
      logger.info("表头:{}" , headMap);
	}

	/**
	 * 读取完成之后做的内容
	 */
	@Override public void doAfterAllAnalysed(AnalysisContext context) {
      logger.info("读取完成");
	}

}
