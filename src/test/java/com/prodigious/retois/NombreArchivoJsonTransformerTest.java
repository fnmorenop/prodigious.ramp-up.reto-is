package com.prodigious.retois;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;

import static org.mockito.Mockito.*;

public class NombreArchivoJsonTransformerTest {
	
	private NombreArchivoJsonTransformer najt;// = new NombreArchivoJsonTransformer();

	@Before
	public void setUp() throws Exception {
		najt = new NombreArchivoJsonTransformer();
	}

	@SuppressWarnings("unused")
	@Test
	public void mensajeNuloTest() {
		MuleMessage mensaje = null;
		try{
			Object transformacion = najt.transformMessage(mensaje, "UTF-8");
			assertTrue(false);
		}catch(TransformerException e){
			assertTrue(true);
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void payloadNuloTest() {
		
		MuleMessage mensaje = mock(org.mule.api.MuleMessage.class);
		
		when(mensaje.getPayload()).thenReturn(null);
		
		try{
			Object transformacion = najt.transformMessage(mensaje, "UTF-8");
			assertTrue(false);
		}catch(TransformerException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void newJsonObjectContaintNombreArchivoTest() {
		
		MuleMessage mensaje = mock(org.mule.api.MuleMessage.class);
		
		when(mensaje.getPayload()).thenReturn("{}");
		when(mensaje.getOutboundProperty("nombreArchivo")).thenReturn("books.xml");
		
		try{
			Object transformacion = najt.transformMessage(mensaje, "UTF-8");
			assertTrue(transformacion instanceof String);
			assertTrue(new JSONObject(transformacion) != null);
			JSONObject payLoad = new JSONObject(transformacion.toString());
			assertTrue(payLoad.has("nombreArchivo"));
			
		}catch(TransformerException e){
			assertTrue(false);
		}
	}

}
