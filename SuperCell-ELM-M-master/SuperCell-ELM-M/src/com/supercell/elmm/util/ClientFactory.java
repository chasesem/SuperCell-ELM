package com.supercell.elmm.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;


public class ClientFactory {

	private int maxTotal;
	private int defaultMaxPerRoute;

	public Client getClient() {
		final ClientConfig clientConfig = new ClientConfig();
		final SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", 8080, PlainSocketFactory
				.getSocketFactory()));
		final PoolingClientConnectionManager cm = new PoolingClientConnectionManager(
				registry);

		// 1.配置连接池管理实例
		cm.setMaxTotal(20000);
		cm.setDefaultMaxPerRoute(10000);
		// 2.将连接池管理实例配置为ClientConfig的属性值
		clientConfig.property(ApacheClientProperties.CONNECTION_MANAGER, cm);
		// 3.传递客户端配置给连接器
		final ApacheConnectorProvider provider = new ApacheConnectorProvider();
		clientConfig.connectorProvider(provider);
		//4.客户端配置实例携带了Apache 连接器信息
		Client client = ClientBuilder.newClient(clientConfig);
		return client;
	}

	public int getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

	public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
}
	
