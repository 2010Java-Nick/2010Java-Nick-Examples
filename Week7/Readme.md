# Week 7 SOA/MSA Topics

- [SOAP](https://www.w3.org/TR/2000/NOTE-SOAP-20000508/)
  - WSDL
    - definition, types, message, porttype, operation, binding, service, port
    - [Binding styles](https://www.ibm.com/support/knowledgecenter/SSFTBX_8.5.7/com.ibm.wbpm.wid.integ.doc/access/topics/rwsdlstyle.html)
  - Contract first/contract last
  - XML Namespaces
  - [JAX-WS](https://docs.oracle.com/javaee/6/tutorial/doc/bnayl.html)
  - [JAXB](https://www.oracle.com/technical-resources/articles/javase/jaxb.html)
  - SOAP Engine
    - [Apache CXF](https://cxf.apache.org/)
  - SOAP envelope
  - SOAP vs. REST

- SOA - Service Oriented Architecture

- MSA - Microservice Architecture
  - Advantages/disadvantages
  - [Microservices](https://martinfowler.com/articles/microservices.html)
  - [Architecture](https://dzone.com/articles/design-patterns-for-microservices)
  - [ACID vs BASE](https://www.dataversity.net/acid-vs-base-the-shifting-ph-of-database-transaction-processing/#)
  - Infrastructure Services
    - Gateway
      - [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
        - Filters
        - Ribbon
    - [Consul](https://www.consul.io/)
      - Discovery & Registry
      - Configuration
    - [Spring Cloud Consul](https://spring.io/projects/spring-cloud-consul)
    - Circuit Breaker
      - [Hytrix](https://github.com/Netflix/Hystrix/wiki/How-it-Works)
    - [Monitoring/Tracing](https://dzone.com/articles/microservices-part-6-distributed-tracing-with-spri)
    - [Message Broker/Queue](https://spring.io/guides/gs/messaging-rabbitmq/)

- [Containerization (Docker)](https://www.docker.com/)
- [Orchestration (Kubernetes)](https://kubernetes.io/)
    - Kubectl
    - Kubernetes Services
    - Kubernetes Declarative Deployments
- OWASP Topics
    - [XML External Entities](https://owasp.org/www-project-top-ten/2017/A4_2017-XML_External_Entities_(XXE))
    - [Broken Access Control](https://owasp.org/www-project-top-ten/2017/A5_2017-Broken_Access_Control)
    - [Security Misconfiguration](https://owasp.org/www-project-top-ten/2017/A6_2017-Security_Misconfiguration)
    - [Insecure Deserialization](https://owasp.org/www-project-top-ten/2017/A8_2017-Insecure_Deserialization)
    - [Sensitive Data Exposure](https://owasp.org/www-project-top-ten/2017/A3_2017-Sensitive_Data_Exposure)
