package com.prodigious.retois;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class NombreArchivoJsonTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		java.util.logging.Logger log = java.util.logging.Logger.getLogger("Mensajes");
		
		if(message == null){
			log.severe("Mensaje nulo");
			throw new TransformerException(this, new NullPointerException("Mensaje Nulo"));
		}
		
		if(message.getPayload() == null){
			log.severe("Payload nulo");
			throw new TransformerException(this, new NullPointerException("Payload Nulo"));
		}
		
		JSONObject payLoad = new JSONObject( message.getPayload().toString());
		
		log.info(String.format("payLoad: %s", payLoad.toString()));
		
		String nombreArchivo = message.getOutboundProperty("nombreArchivo");
		
		log.info(String.format("nombreArchivo: %s", nombreArchivo));

		payLoad.put("nombreArchivo", nombreArchivo);
		log.info(String.format("payLoad: %s", payLoad.toString()));
		payLoad.put("fechaCreacion", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		log.info(String.format("payLoad: %s", payLoad.toString()));
		log.info(String.format("payload.has(nombreArchivo): %b", payLoad.has("nombreArchivo")));
		return payLoad.toString();
	}

}
