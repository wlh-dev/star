package com.star.wlh.mysql.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wlh
 */
@ConfigurationProperties(prefix = "spring.datasource.druid") @Configuration public class DruidConfig {
	private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

	private String url;
	private String username;
	private String password;

	/**
	 * 配置文件中是driver-class-name, 转驼峰命名便可以绑定成
	 */
	private String driverClassName;
	private String type;
	private Integer initialSize;
	private Integer minIdle;
	private Integer maxActive;
	private Long maxWait;
	private Long timeBetweenEvictionRunsMillis;
	private Long minEvictableIdleTimeMillis;
	private String validationQuery;
	private Boolean testWhileIdle;
	private Boolean testOnBorrow;
	private Boolean testOnReturn;
	private Boolean poolPreparedStatements;
	private String filters;
	private Integer maxPoolPreparedStatementPerConnectionSize;
	private Boolean useGlobalDataSourceStat;
	private Properties connectionProperties;

	@Bean(name = "druidDataSource") public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		// mysql驱动配置
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setDbType(type);
		// druid连接池配置
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setMaxWait(maxWait);
		druidDataSource.setTimeBetweenConnectErrorMillis(timeBetweenEvictionRunsMillis);
		druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		druidDataSource.setValidationQuery(validationQuery);
		druidDataSource.setTestWhileIdle(testWhileIdle);
		druidDataSource.setTestOnBorrow(testOnBorrow);
		druidDataSource.setTestOnReturn(testOnReturn);
		druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
		try {
			druidDataSource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("setFilters Error ", e);

		}
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		druidDataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
		druidDataSource.setConnectProperties(connectionProperties);
		return druidDataSource;
	}

	//因为Springboot内置了servlet容器，所以没有web.xml，替代方法就是将ServletRegistrationBean注册进去
	//加入后台监控
	@Bean public ServletRegistrationBean<StatViewServlet> statViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

		//后台需要有人登录，进行配置
		//bean.addUrlMappings(); 这个可以添加映射，我们在构造里已经写了
		//设置一些初始化参数
		Map<String, String> initParas = new HashMap<>(10);
		//它这个账户密码是固定的
		initParas.put("loginUsername", "admin");
		initParas.put("loginPassword", "123456");
		//允许谁能防伪
		//只允许本机访问，多个ip用逗号,隔开
		initParas.put("allow", "localhost");
		//ip黑名单，拒绝谁访问 deny和allow同时存在优先deny
		initParas.put("deny", "192.168.2.122");
		//禁用HTML页面的Reset按钮
		initParas.put("resetEnable", "false");
		bean.setInitParameters(initParas);
		return bean;
	}

	/**
	 * 再配置一个过滤器，Servlet按上面的方式注册Filter也只能这样
	 */
	@Bean public FilterRegistrationBean<WebStatFilter> webStatFilter() {
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
		//可以设置也可以获取,设置一个阿里巴巴的过滤器
		bean.setFilter(new WebStatFilter());
		bean.addUrlPatterns("/*");
		//可以过滤和排除哪些东西
		Map<String, String> initParams = new HashMap<>(10);
		//把不需要监控的过滤掉,这些不进行统计
		initParams.put("exclusions", "*.js,*.css,/druid/*");
		bean.setInitParameters(initParams);
		return bean;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Long maxWait) {
		this.maxWait = maxWait;
	}

	public Long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public Long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public Boolean getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public Integer getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	public Boolean getUseGlobalDataSourceStat() {
		return useGlobalDataSourceStat;
	}

	public void setUseGlobalDataSourceStat(Boolean useGlobalDataSourceStat) {
		this.useGlobalDataSourceStat = useGlobalDataSourceStat;
	}

	public Properties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(Properties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}
}
