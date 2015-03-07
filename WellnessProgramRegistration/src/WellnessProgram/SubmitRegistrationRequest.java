/**
 * SubmitRegistrationRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package WellnessProgram;

@SuppressWarnings({"rawtypes", "unused", "serial"})
public class SubmitRegistrationRequest  implements java.io.Serializable {
    private java.lang.String email_type;

    private java.lang.String first_name;

    private java.lang.String username;

    private float weight;

    private java.lang.String birth_date;

    private java.lang.String email;

    private java.math.BigInteger age;

    private java.lang.String last_name;

    private java.lang.String badge_number;

    private java.lang.String gender;

    private java.lang.String login;

    private java.lang.String vitex_id;

    private java.lang.String password;

    private java.math.BigInteger email_count;

    public SubmitRegistrationRequest() {
    }

    public SubmitRegistrationRequest(
           java.lang.String email_type,
           java.lang.String first_name,
           java.lang.String username,
           float weight,
           java.lang.String birth_date,
           java.lang.String email,
           java.math.BigInteger age,
           java.lang.String last_name,
           java.lang.String badge_number,
           java.lang.String gender,
           java.lang.String login,
           java.lang.String vitex_id,
           java.lang.String password,
           java.math.BigInteger email_count) {
           this.email_type = email_type;
           this.first_name = first_name;
           this.username = username;
           this.weight = weight;
           this.birth_date = birth_date;
           this.email = email;
           this.age = age;
           this.last_name = last_name;
           this.badge_number = badge_number;
           this.gender = gender;
           this.login = login;
           this.vitex_id = vitex_id;
           this.password = password;
           this.email_count = email_count;
    }


    /**
     * Gets the email_type value for this SubmitRegistrationRequest.
     * 
     * @return email_type
     */
    public java.lang.String getEmail_type() {
        return email_type;
    }


    /**
     * Sets the email_type value for this SubmitRegistrationRequest.
     * 
     * @param email_type
     */
    public void setEmail_type(java.lang.String email_type) {
        this.email_type = email_type;
    }


    /**
     * Gets the first_name value for this SubmitRegistrationRequest.
     * 
     * @return first_name
     */
    public java.lang.String getFirst_name() {
        return first_name;
    }


    /**
     * Sets the first_name value for this SubmitRegistrationRequest.
     * 
     * @param first_name
     */
    public void setFirst_name(java.lang.String first_name) {
        this.first_name = first_name;
    }


    /**
     * Gets the username value for this SubmitRegistrationRequest.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this SubmitRegistrationRequest.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the weight value for this SubmitRegistrationRequest.
     * 
     * @return weight
     */
    public float getWeight() {
        return weight;
    }


    /**
     * Sets the weight value for this SubmitRegistrationRequest.
     * 
     * @param weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }


    /**
     * Gets the birth_date value for this SubmitRegistrationRequest.
     * 
     * @return birth_date
     */
    public java.lang.String getBirth_date() {
        return birth_date;
    }


    /**
     * Sets the birth_date value for this SubmitRegistrationRequest.
     * 
     * @param birth_date
     */
    public void setBirth_date(java.lang.String birth_date) {
        this.birth_date = birth_date;
    }


    /**
     * Gets the email value for this SubmitRegistrationRequest.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this SubmitRegistrationRequest.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the age value for this SubmitRegistrationRequest.
     * 
     * @return age
     */
    public java.math.BigInteger getAge() {
        return age;
    }


    /**
     * Sets the age value for this SubmitRegistrationRequest.
     * 
     * @param age
     */
    public void setAge(java.math.BigInteger age) {
        this.age = age;
    }


    /**
     * Gets the last_name value for this SubmitRegistrationRequest.
     * 
     * @return last_name
     */
    public java.lang.String getLast_name() {
        return last_name;
    }


    /**
     * Sets the last_name value for this SubmitRegistrationRequest.
     * 
     * @param last_name
     */
    public void setLast_name(java.lang.String last_name) {
        this.last_name = last_name;
    }


    /**
     * Gets the badge_number value for this SubmitRegistrationRequest.
     * 
     * @return badge_number
     */
    public java.lang.String getBadge_number() {
        return badge_number;
    }


    /**
     * Sets the badge_number value for this SubmitRegistrationRequest.
     * 
     * @param badge_number
     */
    public void setBadge_number(java.lang.String badge_number) {
        this.badge_number = badge_number;
    }


    /**
     * Gets the gender value for this SubmitRegistrationRequest.
     * 
     * @return gender
     */
    public java.lang.String getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this SubmitRegistrationRequest.
     * 
     * @param gender
     */
    public void setGender(java.lang.String gender) {
        this.gender = gender;
    }


    /**
     * Gets the login value for this SubmitRegistrationRequest.
     * 
     * @return login
     */
    public java.lang.String getLogin() {
        return login;
    }


    /**
     * Sets the login value for this SubmitRegistrationRequest.
     * 
     * @param login
     */
    public void setLogin(java.lang.String login) {
        this.login = login;
    }


    /**
     * Gets the vitex_id value for this SubmitRegistrationRequest.
     * 
     * @return vitex_id
     */
    public java.lang.String getVitex_id() {
        return vitex_id;
    }


    /**
     * Sets the vitex_id value for this SubmitRegistrationRequest.
     * 
     * @param vitex_id
     */
    public void setVitex_id(java.lang.String vitex_id) {
        this.vitex_id = vitex_id;
    }


    /**
     * Gets the password value for this SubmitRegistrationRequest.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SubmitRegistrationRequest.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the email_count value for this SubmitRegistrationRequest.
     * 
     * @return email_count
     */
    public java.math.BigInteger getEmail_count() {
        return email_count;
    }


    /**
     * Sets the email_count value for this SubmitRegistrationRequest.
     * 
     * @param email_count
     */
    public void setEmail_count(java.math.BigInteger email_count) {
        this.email_count = email_count;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubmitRegistrationRequest)) return false;
        SubmitRegistrationRequest other = (SubmitRegistrationRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.email_type==null && other.getEmail_type()==null) || 
             (this.email_type!=null &&
              this.email_type.equals(other.getEmail_type()))) &&
            ((this.first_name==null && other.getFirst_name()==null) || 
             (this.first_name!=null &&
              this.first_name.equals(other.getFirst_name()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            this.weight == other.getWeight() &&
            ((this.birth_date==null && other.getBirth_date()==null) || 
             (this.birth_date!=null &&
              this.birth_date.equals(other.getBirth_date()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.age==null && other.getAge()==null) || 
             (this.age!=null &&
              this.age.equals(other.getAge()))) &&
            ((this.last_name==null && other.getLast_name()==null) || 
             (this.last_name!=null &&
              this.last_name.equals(other.getLast_name()))) &&
            ((this.badge_number==null && other.getBadge_number()==null) || 
             (this.badge_number!=null &&
              this.badge_number.equals(other.getBadge_number()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.login==null && other.getLogin()==null) || 
             (this.login!=null &&
              this.login.equals(other.getLogin()))) &&
            ((this.vitex_id==null && other.getVitex_id()==null) || 
             (this.vitex_id!=null &&
              this.vitex_id.equals(other.getVitex_id()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.email_count==null && other.getEmail_count()==null) || 
             (this.email_count!=null &&
              this.email_count.equals(other.getEmail_count())));
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
        if (getEmail_type() != null) {
            _hashCode += getEmail_type().hashCode();
        }
        if (getFirst_name() != null) {
            _hashCode += getFirst_name().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        _hashCode += new Float(getWeight()).hashCode();
        if (getBirth_date() != null) {
            _hashCode += getBirth_date().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getAge() != null) {
            _hashCode += getAge().hashCode();
        }
        if (getLast_name() != null) {
            _hashCode += getLast_name().hashCode();
        }
        if (getBadge_number() != null) {
            _hashCode += getBadge_number().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getLogin() != null) {
            _hashCode += getLogin().hashCode();
        }
        if (getVitex_id() != null) {
            _hashCode += getVitex_id().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getEmail_count() != null) {
            _hashCode += getEmail_count().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubmitRegistrationRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("WellnessProgram", ">SubmitRegistrationRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("first_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "first_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weight");
        elemField.setXmlName(new javax.xml.namespace.QName("", "weight"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birth_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birth_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("age");
        elemField.setXmlName(new javax.xml.namespace.QName("", "age"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("last_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "last_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("badge_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "badge_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("login");
        elemField.setXmlName(new javax.xml.namespace.QName("", "login"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vitex_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vitex_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email_count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email_count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
