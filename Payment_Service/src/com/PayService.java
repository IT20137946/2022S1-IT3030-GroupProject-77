package com;

import model.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PayService {
	Payment PayObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPay() {
		return PayObj.readPay();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPay(
			@FormParam("PAYCName") String PAYCName,
			@FormParam("PAYAccNO") String PAYAccNO,
			@FormParam("PAYDate") String PAYDate,
			@FormParam("PAYAmount") String PAYAmount) {
		String output = PayObj.insertPay(PAYCName, PAYAccNO, PAYDate, PAYAmount);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePay(String paymentData) {
		
		JsonObject PaymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		
		String PAYID = PaymentObject.get("PAYID").getAsString();
		String PAYCName = PaymentObject.get("PAYCName").getAsString();
		String PAYAccNO = PaymentObject.get("PAYAccNO").getAsString();
		String PAYDate = PaymentObject.get("PAYDate").getAsString();
		String PAYAmount = PaymentObject.get("PAYAmount").getAsString();
		
		String output = PayObj.updatePay(PAYID, PAYCName, PAYAccNO, PAYDate, PAYAmount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePay(String paymentData) {
		
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		String PAYID = doc.select("PAYID").text();
		String output = PayObj.deletePay(PAYID);
		return output;
		
	}
}
