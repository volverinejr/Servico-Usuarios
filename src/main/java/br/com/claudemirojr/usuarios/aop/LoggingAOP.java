package br.com.claudemirojr.usuarios.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.claudemirojr.usuarios.clients.ILogPesquisaFeign;
import br.com.claudemirojr.usuarios.config.Security;
import br.com.claudemirojr.usuarios.dto.LogPesquisaDto;

@Aspect
@Component
public class LoggingAOP {
	final static Logger log = LoggerFactory.getLogger(LoggingAOP.class);
	
	@Value("${spring.application.name}")
	private String servico;
	
	@Autowired
	private Security security;
	
	@Autowired
	private ILogPesquisaFeign logPesquisaFeign;
	

	@Pointcut(value = "execution(* br.com.claudemirojr.usuarios.controller.*.findBy*(..) )")
	public void myPointcut() {

	}

	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();

		String className = pjp.getTarget().getClass().toString();
		String methodName = pjp.getSignature().getName();
		Object[] array = pjp.getArgs();
		String argumento = mapper.writeValueAsString(array);

		Object object = pjp.proceed();

		String retorno = mapper.writeValueAsString(object);
		
		String[] result = className.split("\\.");
		

		LogPesquisaDto logPesquisaDto = new LogPesquisaDto(this.servico, this.getUsuarioLogado(), result[result.length - 1], methodName, argumento, retorno);
		logPesquisaFeign.create(logPesquisaDto);
		
		//LogPesquisa logPesquisa = new LogPesquisa(this.getUsuarioLogado(), className, methodName, argumento, retorno);
		//log.info( logPesquisa.toString() );

		return object;
	}
	
	private String getUsuarioLogado() {
		return security.getUsuarioLogado();
	}

}
