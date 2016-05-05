
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:02:19 GMT)
 */

        
            package com.datacode.avon_ots_ws.model.xsd;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://model.avon_ots_ws.datacode.com/xsd".equals(namespaceURI) &&
                  "DocumentoOrdenDejadaRecolectadaPUPDTO".equals(typeName)){
                   
                            return  com.datacode.avon_ots_ws.model.xsd.DocumentoOrdenDejadaRecolectadaPUPDTO.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.avon_ots_ws.datacode.com/xsd".equals(namespaceURI) &&
                  "PUPDTO".equals(typeName)){
                   
                            return  com.datacode.avon_ots_ws.model.xsd.PUPDTO.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.avon_ots_ws.datacode.com/xsd".equals(namespaceURI) &&
                  "LiquidacionRepartoDTO".equals(typeName)){
                   
                            return  com.datacode.avon_ots_ws.model.xsd.LiquidacionRepartoDTO.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.avon_ots_ws.datacode.com/xsd".equals(namespaceURI) &&
                  "PremioUnitarioOrdenDejadaRecolectadaPUPDTO".equals(typeName)){
                   
                            return  com.datacode.avon_ots_ws.model.xsd.PremioUnitarioOrdenDejadaRecolectadaPUPDTO.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.avon_ots_ws.datacode.com/xsd".equals(namespaceURI) &&
                  "CajaOrdenDejadaRecolectadaPUPDTO".equals(typeName)){
                   
                            return  com.datacode.avon_ots_ws.model.xsd.CajaOrdenDejadaRecolectadaPUPDTO.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    