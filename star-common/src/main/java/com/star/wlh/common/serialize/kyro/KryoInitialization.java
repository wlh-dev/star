package com.star.wlh.common.serialize.kyro;

import com.star.wlh.common.initialization.InitializationRegister;
import org.springframework.stereotype.Component;

/**
 * 初始化kryo序列化的初始化工作
 *
 * @author : wlh
 * @date Date : 2022年09月21日 18:06
 */

@Component public class KryoInitialization implements InitializationRegister {
	@Override public void register() {
		KryoFactory.init(100);
	}
}
