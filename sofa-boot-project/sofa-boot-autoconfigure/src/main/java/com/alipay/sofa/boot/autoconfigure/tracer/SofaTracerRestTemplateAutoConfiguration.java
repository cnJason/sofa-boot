/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.boot.autoconfigure.tracer;

import com.alipay.sofa.tracer.boot.resttemplate.SofaTracerRestTemplateEnhance;
import com.alipay.sofa.tracer.boot.resttemplate.processor.SofaTracerRestTemplateBeanPostProcessor;
import com.sofa.alipay.tracer.plugins.rest.RestTemplateTracer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guolei.sgl (guolei.sgl@antfin.com) 2019/9/11 9:43 PM
 **/
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "com.alipay.sofa.tracer.resttemplate", value = "enable", matchIfMissing = true)
@ConditionalOnClass({ RestTemplateTracer.class, SofaTracerRestTemplateEnhance.class,
                     SofaTracerRestTemplateBeanPostProcessor.class })
public class SofaTracerRestTemplateAutoConfiguration {

    @Bean
    public SofaTracerRestTemplateEnhance sofaTracerRestTemplateEnhance() {
        return new SofaTracerRestTemplateEnhance();
    }

    @Bean
    public SofaTracerRestTemplateBeanPostProcessor sofaTracerRestTemplateBeanPostProcessor(SofaTracerRestTemplateEnhance sofaTracerRestTemplateEnhance) {
        return new SofaTracerRestTemplateBeanPostProcessor(sofaTracerRestTemplateEnhance);
    }
}
