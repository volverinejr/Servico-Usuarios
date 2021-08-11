package br.com.claudemirojr.usuarios.rabbitmq.sender;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.claudemirojr.usuarios.dto.LogPesquisaDto;

@Component
public class LogPesquisaSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	public void send(LogPesquisaDto logPesquisaDto) throws JsonProcessingException, AmqpException {
		ObjectMapper obj = new ObjectMapper();

		rabbitTemplate.convertAndSend(this.queue.getName(), obj.writeValueAsString(logPesquisaDto));
	}
}
