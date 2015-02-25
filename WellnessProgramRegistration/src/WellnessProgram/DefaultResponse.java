/**
 * DefaultResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WellnessProgram;

@SuppressWarnings({"serial", "rawtypes"})
public class DefaultResponse  implements java.io.Serializable {
    private java.lang.String workflowId;

    private java.lang.String workitemId;

    public DefaultResponse() {
    }

    public DefaultResponse(
           java.lang.String workflowId,
           java.lang.String workitemId) {
           this.workflowId = workflowId;
           this.workitemId = workitemId;
    }


    /**
     * Gets the workflowId value for this DefaultResponse.
     * 
     * @return workflowId
     */
    public java.lang.String getWorkflowId() {
        return workflowId;
    }


    /**
     * Sets the workflowId value for this DefaultResponse.
     * 
     * @param workflowId
     */
    public void setWorkflowId(java.lang.String workflowId) {
        this.workflowId = workflowId;
    }


    /**
     * Gets the workitemId value for this DefaultResponse.
     * 
     * @return workitemId
     */
    public java.lang.String getWorkitemId() {
        return workitemId;
    }


    /**
     * Sets the workitemId value for this DefaultResponse.
     * 
     * @param workitemId
     */
    public void setWorkitemId(java.lang.String workitemId) {
        this.workitemId = workitemId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefaultResponse)) return false;
        DefaultResponse other = (DefaultResponse) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.workflowId==null && other.getWorkflowId()==null) || 
             (this.workflowId!=null &&
              this.workflowId.equals(other.getWorkflowId()))) &&
            ((this.workitemId==null && other.getWorkitemId()==null) || 
             (this.workitemId!=null &&
              this.workitemId.equals(other.getWorkitemId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getWorkflowId() != null) {
            _hashCode += getWorkflowId().hashCode();
        }
        if (getWorkitemId() != null) {
            _hashCode += getWorkitemId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefaultResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("WellnessProgram", ">defaultResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "workflowId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workitemId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "workitemId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
