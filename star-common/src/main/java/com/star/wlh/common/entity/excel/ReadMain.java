package com.star.wlh.common.entity.excel;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author : wlh
 * @date Date : 2022年09月07日 09:36
 */

public class ReadMain {
	private static final Logger logger = LoggerFactory.getLogger(ExcelListener.class);

	private static final Map<String, String> classCodeMap = new HashMap<>();

	static {
		classCodeMap.put("VpnGateway", "VpnGateway");
		classCodeMap.put("VideoConferenceControlUnit", "VideoConferenceControlUnit");
		classCodeMap.put("ContactCenterAccessGateway", "ContactCenterAccessGateway");
		classCodeMap.put("InternetBehaviorGateway", "InternetBehaviorGateway");
		classCodeMap.put("VideoConferenceTerminal", "VideoConferenceTerminal");
		classCodeMap.put("NetworkController", "NetworkController");
		classCodeMap.put("MediaGateway", "MediaGateway");
		classCodeMap.put("ProxyServer", "ProxyServer");
		classCodeMap.put("NetworkTrafficCollectionAndDiversionTool", "NetworkTrafficCollectionAndDiversionTool");
		classCodeMap.put("WirelessAccessController", "WirelessAccessController");
		classCodeMap.put("LoadBalancer", "LB");
		classCodeMap.put("IpsecVpnGateway", "IpsecVpnGateway");
		classCodeMap.put("NetworkPerformanceTestAndDiagnosticTool", "NetworkPerformanceTestAndDiagnosticTool");
		classCodeMap.put("WirelessAccessPoint", "WirelessAccessPoint");
		classCodeMap.put("IpPhone", "IpPhone");
		classCodeMap.put("Router", "10202");
		classCodeMap.put("SwitchAndRouter", "SwitchAndRouter");
		classCodeMap.put("DWDM", "DWDM");
		classCodeMap.put("Switch", "10201");
		classCodeMap.put("Firewall", "10203");
	}

	public static void main(String[] args) {
		StringJoiner stringJoiner = new StringJoiner(",");
		for (String value : classCodeMap.keySet()) {
			stringJoiner.add(value);
		}
		//logger.info("结果是:{}", stringJoiner);
		String outputFile = "/Users/wlh/IdeaProjects/star/star-common/src/main/resources/视图领域和配置项展示顺序.xlsx";
		// 加上继承了AnalysisEventListener类的监听器
		ExcelListener excelListener = new ExcelListener();
		EasyExcel.read(outputFile,ResClassEntity.class, excelListener).sheet().doRead();
	}
}
