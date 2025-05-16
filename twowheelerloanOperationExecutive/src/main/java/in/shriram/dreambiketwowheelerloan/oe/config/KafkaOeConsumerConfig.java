package in.shriram.dreambiketwowheelerloan.oe.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaOeConsumerConfig {

	@Bean
	public ConsumerFactory<String, String> consumerFactory()
	{
		Map<String, Object> map = new HashMap<String,Object>();	
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , JsonSerializer.class);
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "cibil_group");
		
		return new DefaultKafkaConsumerFactory<String,String>(map,new StringDeserializer(),new JsonDeserializer<String>());

	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,String> containerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String> factory=new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
