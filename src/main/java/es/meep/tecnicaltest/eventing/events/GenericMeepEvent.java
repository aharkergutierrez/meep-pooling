package es.meep.tecnicaltest.eventing.events;

import org.springframework.context.ApplicationEvent;

import lombok.Data;

@Data
public class GenericMeepEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7386794856672003002L;
	
	private Object modelObject;
	
	public static final String METHOD_TO_SET_MODEL = "setModelObject";
	public static final String METHOD_TO_GET_MODEL = "getModelObject";

	public GenericMeepEvent(Object source) {
		super(source);
	}

}
