package com;
import model.Financial;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;



//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Financial")
public class FinancialService
{
 Financial BillObj = new Financial();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readBills()
 {
	return BillObj.readBills();
 }




@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertBills(
		
 @FormParam("BillDate") String BillDate,
 @FormParam("ConsumedUnits") String ConsumedUnits,
 @FormParam("UnitPrice") String UnitPrice,
 @FormParam("Total") String Total)
{
 String output = BillObj.insertBill(BillDate, ConsumedUnits, UnitPrice, Total);
return output;
}


@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateBills(String BillData)
{
//Convert the input string to a JSON object
 JsonObject BillObject = new JsonParser().parse(BillData).getAsJsonObject();
//Read the values from the JSON object
 String BillID = BillObject.get("BillID").getAsString();
 String BillDate = BillObject.get("BillDate").getAsString();
 String ConsumedUnits = BillObject.get("ConsumedUnits").getAsString();
 String UnitPrice = BillObject.get("UnitPrice").getAsString();
 String Total = BillObject.get("Total").getAsString();
 String output = BillObj.updateBills(BillID, BillDate, ConsumedUnits, UnitPrice, Total);
return output;
}




@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteBills(String BillData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(BillData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String BillID = doc.select("BillID").text();
 String output = BillObj.deleteBills(BillID);
return output;
}



}


