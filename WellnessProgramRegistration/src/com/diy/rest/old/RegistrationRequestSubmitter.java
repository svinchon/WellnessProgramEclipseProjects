package com.diy.rest.old;

import java.io.StringWriter;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class RegistrationRequestSubmitter {
	
	private String age;
	private String weight;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;

    public String run() {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            String url = "http://nkxcp21:7000/bps/webservice/4b0d8b6680006456/HealthProgram";
            SOAPMessage soapRequest = createSOAPRequest();
            SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
            soapConnection.close();
            return getSOAPResponseAsString(soapResponse);
        } catch (Exception e) {
            System.err.println("Error occurred while sending SOAP Request to Server");
            e.printStackTrace();
            return "Connection Error";
        }
    }

    private SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String serverURI = "HealthProgram";
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("heal", serverURI);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("SubmitRegistrationRequest", "heal");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("first_name");
        soapBodyElem1.addTextNode(firstName);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("weight");
        soapBodyElem2.addTextNode(weight);
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("email");
        soapBodyElem4.addTextNode(email);
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("age");
        soapBodyElem5.addTextNode("42");
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("last_name");
        soapBodyElem6.addTextNode(lastName);
        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("gender");
        soapBodyElem7.addTextNode(gender);
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI +"/"+serverURI+"/"+"SubmitRegistration");
        soapMessage.saveChanges();
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();
        return soapMessage;
    }

    private String getSOAPResponseAsString(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        //System.out.print("\nResponse SOAP Message = ");
        //StreamResult result = new StreamResult(System.out);
        //transformer.transform(sourceContent, result);
        StringWriter sw = new StringWriter();
        StreamResult xmlOutput=new StreamResult(sw);
        transformer.transform(sourceContent, xmlOutput);
        return xmlOutput.getWriter().toString();
    }

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}