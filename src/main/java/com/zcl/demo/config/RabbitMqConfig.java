package com.zcl.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**

 * @author  zcl

 * @create  2021/7/5 16:49

 * @desc rabbitmq配置类（主题模式）

 **/
@Configuration
public class RabbitMqConfig {


    //绑定键
    public final static String K1 = "email.k1";
    public final static String K2 = "email.k2";

    @Bean
    public Queue firstQueue() {
        return new Queue(RabbitMqConfig.K1);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(RabbitMqConfig.K2);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("emailExchange");
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(K1);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("email.#");
    }




}
