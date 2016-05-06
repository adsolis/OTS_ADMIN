
/**
 * EnviarCorreo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:02:19 GMT)
 */

            
                package com.datacode.avon_ots_ws;
            

            /**
            *  EnviarCorreo bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class EnviarCorreo
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "http://avon_ots_ws.datacode.com",
                "EnviarCorreo",
                "ns2");

            

                        /**
                        * field for De
                        */

                        
                                    protected java.lang.String localDe ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDeTracker = false ;

                           public boolean isDeSpecified(){
                               return localDeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDe(){
                               return localDe;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param De
                               */
                               public void setDe(java.lang.String param){
                            localDeTracker = true;
                                   
                                            this.localDe=param;
                                       

                               }
                            

                        /**
                        * field for Para
                        */

                        
                                    protected java.lang.String localPara ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localParaTracker = false ;

                           public boolean isParaSpecified(){
                               return localParaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPara(){
                               return localPara;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Para
                               */
                               public void setPara(java.lang.String param){
                            localParaTracker = true;
                                   
                                            this.localPara=param;
                                       

                               }
                            

                        /**
                        * field for Asunto
                        */

                        
                                    protected java.lang.String localAsunto ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAsuntoTracker = false ;

                           public boolean isAsuntoSpecified(){
                               return localAsuntoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAsunto(){
                               return localAsunto;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Asunto
                               */
                               public void setAsunto(java.lang.String param){
                            localAsuntoTracker = true;
                                   
                                            this.localAsunto=param;
                                       

                               }
                            

                        /**
                        * field for TextoCorreo
                        */

                        
                                    protected java.lang.String localTextoCorreo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTextoCorreoTracker = false ;

                           public boolean isTextoCorreoSpecified(){
                               return localTextoCorreoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTextoCorreo(){
                               return localTextoCorreo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TextoCorreo
                               */
                               public void setTextoCorreo(java.lang.String param){
                            localTextoCorreoTracker = true;
                                   
                                            this.localTextoCorreo=param;
                                       

                               }
                            

                        /**
                        * field for EsHtml
                        */

                        
                                    protected boolean localEsHtml ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEsHtmlTracker = false ;

                           public boolean isEsHtmlSpecified(){
                               return localEsHtmlTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getEsHtml(){
                               return localEsHtml;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EsHtml
                               */
                               public void setEsHtml(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       localEsHtmlTracker =
                                       true;
                                   
                                            this.localEsHtml=param;
                                       

                               }
                            

                        /**
                        * field for ServidorSmtp
                        */

                        
                                    protected java.lang.String localServidorSmtp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localServidorSmtpTracker = false ;

                           public boolean isServidorSmtpSpecified(){
                               return localServidorSmtpTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getServidorSmtp(){
                               return localServidorSmtp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ServidorSmtp
                               */
                               public void setServidorSmtp(java.lang.String param){
                            localServidorSmtpTracker = true;
                                   
                                            this.localServidorSmtp=param;
                                       

                               }
                            

                        /**
                        * field for Usuario
                        */

                        
                                    protected java.lang.String localUsuario ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUsuarioTracker = false ;

                           public boolean isUsuarioSpecified(){
                               return localUsuarioTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getUsuario(){
                               return localUsuario;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Usuario
                               */
                               public void setUsuario(java.lang.String param){
                            localUsuarioTracker = true;
                                   
                                            this.localUsuario=param;
                                       

                               }
                            

                        /**
                        * field for Contrasenia
                        */

                        
                                    protected java.lang.String localContrasenia ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localContraseniaTracker = false ;

                           public boolean isContraseniaSpecified(){
                               return localContraseniaTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getContrasenia(){
                               return localContrasenia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Contrasenia
                               */
                               public void setContrasenia(java.lang.String param){
                            localContraseniaTracker = true;
                                   
                                            this.localContrasenia=param;
                                       

                               }
                            

                        /**
                        * field for HabilitaSsl
                        */

                        
                                    protected boolean localHabilitaSsl ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHabilitaSslTracker = false ;

                           public boolean isHabilitaSslSpecified(){
                               return localHabilitaSslTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getHabilitaSsl(){
                               return localHabilitaSsl;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HabilitaSsl
                               */
                               public void setHabilitaSsl(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       localHabilitaSslTracker =
                                       true;
                                   
                                            this.localHabilitaSsl=param;
                                       

                               }
                            

                        /**
                        * field for Puerto
                        */

                        
                                    protected int localPuerto ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPuertoTracker = false ;

                           public boolean isPuertoSpecified(){
                               return localPuertoTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getPuerto(){
                               return localPuerto;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Puerto
                               */
                               public void setPuerto(int param){
                            
                                       // setting primitive attribute tracker to true
                                       localPuertoTracker =
                                       param != java.lang.Integer.MIN_VALUE;
                                   
                                            this.localPuerto=param;
                                       

                               }
                            

                        /**
                        * field for Adjuntos
                        * This was an Array!
                        */

                        
                                    protected com.datacode.avon_ots_ws.model.xsd.Archivo[] localAdjuntos ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAdjuntosTracker = false ;

                           public boolean isAdjuntosSpecified(){
                               return localAdjuntosTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.datacode.avon_ots_ws.model.xsd.Archivo[]
                           */
                           public  com.datacode.avon_ots_ws.model.xsd.Archivo[] getAdjuntos(){
                               return localAdjuntos;
                           }

                           
                        


                               
                              /**
                               * validate the array for Adjuntos
                               */
                              protected void validateAdjuntos(com.datacode.avon_ots_ws.model.xsd.Archivo[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Adjuntos
                              */
                              public void setAdjuntos(com.datacode.avon_ots_ws.model.xsd.Archivo[] param){
                              
                                   validateAdjuntos(param);

                               localAdjuntosTracker = true;
                                      
                                      this.localAdjuntos=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param com.datacode.avon_ots_ws.model.xsd.Archivo
                             */
                             public void addAdjuntos(com.datacode.avon_ots_ws.model.xsd.Archivo param){
                                   if (localAdjuntos == null){
                                   localAdjuntos = new com.datacode.avon_ots_ws.model.xsd.Archivo[]{};
                                   }

                            
                                 //update the setting tracker
                                localAdjuntosTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localAdjuntos);
                               list.add(param);
                               this.localAdjuntos =
                             (com.datacode.avon_ots_ws.model.xsd.Archivo[])list.toArray(
                            new com.datacode.avon_ots_ws.model.xsd.Archivo[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME));
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://avon_ots_ws.datacode.com");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":EnviarCorreo",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "EnviarCorreo",
                           xmlWriter);
                   }

               
                   }
                if (localDeTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "de", xmlWriter);
                             

                                          if (localDe==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDe);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localParaTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "para", xmlWriter);
                             

                                          if (localPara==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPara);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAsuntoTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "asunto", xmlWriter);
                             

                                          if (localAsunto==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAsunto);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTextoCorreoTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "textoCorreo", xmlWriter);
                             

                                          if (localTextoCorreo==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTextoCorreo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEsHtmlTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "esHtml", xmlWriter);
                             
                                               if (false) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("esHtml cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEsHtml));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localServidorSmtpTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "servidorSmtp", xmlWriter);
                             

                                          if (localServidorSmtp==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localServidorSmtp);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localUsuarioTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "usuario", xmlWriter);
                             

                                          if (localUsuario==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localUsuario);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localContraseniaTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "contrasenia", xmlWriter);
                             

                                          if (localContrasenia==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localContrasenia);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHabilitaSslTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "habilitaSsl", xmlWriter);
                             
                                               if (false) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("habilitaSsl cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHabilitaSsl));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPuertoTracker){
                                    namespace = "http://avon_ots_ws.datacode.com";
                                    writeStartElement(null, namespace, "puerto", xmlWriter);
                             
                                               if (localPuerto==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("puerto cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPuerto));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAdjuntosTracker){
                                       if (localAdjuntos!=null){
                                            for (int i = 0;i < localAdjuntos.length;i++){
                                                if (localAdjuntos[i] != null){
                                                 localAdjuntos[i].serialize(new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","adjuntos"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                            writeStartElement(null, "http://avon_ots_ws.datacode.com", "adjuntos", xmlWriter);

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                writeStartElement(null, "http://avon_ots_ws.datacode.com", "adjuntos", xmlWriter);

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://avon_ots_ws.datacode.com")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeAttribute(writerPrefix, namespace,attName,attValue);
            } else {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
                xmlWriter.writeAttribute(prefix, namespace,attName,attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace), namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{
        private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static EnviarCorreo parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            EnviarCorreo object =
                new EnviarCorreo();

            int event;
            javax.xml.namespace.QName currentQName = null;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                currentQName = reader.getName();
                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"EnviarCorreo".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (EnviarCorreo)com.datacode.avon_ots_ws.model.xsd.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list11 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","de").equals(reader.getName()) || new javax.xml.namespace.QName("","de").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDe(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","para").equals(reader.getName()) || new javax.xml.namespace.QName("","para").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPara(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","asunto").equals(reader.getName()) || new javax.xml.namespace.QName("","asunto").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAsunto(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","textoCorreo").equals(reader.getName()) || new javax.xml.namespace.QName("","textoCorreo").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTextoCorreo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","esHtml").equals(reader.getName()) || new javax.xml.namespace.QName("","esHtml").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"esHtml" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEsHtml(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","servidorSmtp").equals(reader.getName()) || new javax.xml.namespace.QName("","servidorSmtp").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setServidorSmtp(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","usuario").equals(reader.getName()) || new javax.xml.namespace.QName("","usuario").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUsuario(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","contrasenia").equals(reader.getName()) || new javax.xml.namespace.QName("","contrasenia").equals(reader.getName()) ){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setContrasenia(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","habilitaSsl").equals(reader.getName()) || new javax.xml.namespace.QName("","habilitaSsl").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"habilitaSsl" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHabilitaSsl(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","puerto").equals(reader.getName()) || new javax.xml.namespace.QName("","puerto").equals(reader.getName()) ){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"puerto" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPuerto(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setPuerto(java.lang.Integer.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","adjuntos").equals(reader.getName()) || new javax.xml.namespace.QName("","adjuntos").equals(reader.getName()) ){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list11.add(null);
                                                              reader.next();
                                                          } else {
                                                        list11.add(com.datacode.avon_ots_ws.model.xsd.Archivo.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone11 = false;
                                                        while(!loopDone11){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone11 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://avon_ots_ws.datacode.com","adjuntos").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list11.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list11.add(com.datacode.avon_ots_ws.model.xsd.Archivo.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone11 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setAdjuntos((com.datacode.avon_ots_ws.model.xsd.Archivo[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                com.datacode.avon_ots_ws.model.xsd.Archivo.class,
                                                                list11));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // 2 - A start element we are not expecting indicates a trailing invalid property
                                
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    