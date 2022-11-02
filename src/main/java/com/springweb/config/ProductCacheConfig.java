package com.springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class ProductCacheConfig {
	
	@Bean
	public Config cacheConfig() {
		return new Config()
				.setInstanceName("hazel-instance")
				.addMapConfig(new MapConfig()
						.setName("produt-cache")
						.setTimeToLiveSeconds(3000) //Amount of time the info will live
//						.setMaxSizeConfig(new MaxSizeConfig())
//						.setEvictionPolicy
						);
	}
}
