SensorMix
=========
SensorMix is an example application for demonstrating use of Apache Camel and GWT on Apache Karaf on ServiceMix.

This application has been developed for supporting a tech talk organized by [GDG-Firenze](http://www.gdg-firenze.info/lista-eventi-italiani/osgi-camel-e-gwt). See [the slides of the event](http://cristcost.github.io/sensormix-slides/).

Version 1.0.0 will be released after the release of [GWT 2.6.0](http://www.gwtproject.org/versions.html), and the project will be updated also after release of ~~Servicemix 4.6.0~~ [Servicemix 5.0.0](http://servicemix.apache.org/).

~~Running the application is tricky due to two main issues:~~
* ~~gwt-servlet.jar lacks support for OSGi import and export packages.~~ Fixed with Servicemix GWT-Servelt OSGi bundle
* ~~servicemix 4.5.3 due to old version of mina and jaxb-impl bundles.~~ Ok in Servicemix 5.0.0 


Istructions for running the application ~~and working around the above issues~~ will be published ASAP. In the meanwhile we encurage you to open issues to improve the application.


What it does
------------
![What SensorMix does](http://cristcost.github.io/sensormix/images/sensormix_arc_2.png "What SensorMix does")


How it is done
--------------
![How SensorMix is done](http://cristcost.github.io/sensormix/images/sensormix_arc_1.png "How SensorMix is done")


How GWT is used 
---------------
![How SensorMix uses GWT](http://cristcost.github.io/sensormix/images/gwt_image_3.png "How SensorMix uses GW")
![SensorMix's GWT RPC Plumbing Diagram](http://cristcost.github.io/sensormix/images/sensormix_gwt_2.png "SensorMix's GWT RPC Plumbing Diagram")


How it integrates data
----------------------
![How SensorMix integrates data](http://cristcost.github.io/sensormix/images/sensormix_camel_1.png "How SensorMix integrates data")





Detailed list of integrated technologies
---------------------------------------
<table>
<tr><th>Technology</th><th>Status</th><th>Version</th><th>Notes</th></tr>
<tr><td>Android SDK</td><td>OK</td><td>4.3 (API 18)</td><td></td></tr>
<tr><td>Apache Camel: Cxf</td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: Jaxb</td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: Jetty</td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: Mina2 </td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: Netty </td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: Protocol Buffer </td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: Velocity</td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: Xmljson</td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel: XSLT</td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache Camel</td><td>OK</td><td>2.12.3</td><td></td></tr>
<tr><td>Apache CXF</td><td>OK</td><td>2.7.10</td><td></td></tr>
<tr><td>Apache Felix</td><td>OK</td><td>4.0.3</td><td></td></tr>
<tr><td>Apache Karaf</td><td>OK</td><td>2.3.4</td><td></td></tr>
<tr><td>Apache Servicemix</td><td>OK</td><td>5.0.0</td><td></td></tr>
<tr><td>Arduino IDE</td><td>OK</td><td>1.0.5</td><td></td></tr>
<tr><td>EclipseLink JPA</td><td>OK</td><td>2.5.0</td><td></td></tr>
<tr><td>Google Guice</td><td>TBD</td><td>3.0</td><td></td></tr>
<tr><td>Google Protocol Bugger</td><td>OK</td><td>2.5.0</td><td></td></tr>
<tr><td>Google Web Toolkit</td><td>OK</td><td>2.6.0</td><td></td></tr>
<tr><td>GWT Maven Plugin</td><td>OK</td><td>2.6.0</td><td></td></tr>
<tr><td>HSQLDB JDBC</td><td>OK</td><td>2.3.2</td><td></td></tr>
<tr><td>Java Persistence API</td><td>OK</td><td>2.1</td><td></td></tr>
<tr><td>Jetty</td><td>OK</td><td>7.6</td><td></td></tr>
<tr><td>Kryo</td><td>OK</td><td>2.23.0</td><td></td></tr>
<tr><td>MySQL JDBC</td><td>OK</td><td>5.1.29</td><td></td></tr>
<tr><td>OSGi</td><td>OK</td><td>4.3.1</td><td></td></tr>
<tr><td>Spring DM Web</td><td></td><td>1.2.1</td><td></td></tr>
<tr><td>Spring DM</td><td>OK</td><td>1.2.1</td><td></td></tr>
<tr><td>Spring JDBC</td><td>OK</td><td>3.2.4.RELEASE</td><td></td></tr>
<tr><td>Spring MVC</td><td></td><td>3.2.4.RELEASE</td><td></td></tr>
<tr><td>Spring ORM</td><td>OK</td><td>3.2.4.RELEASE</td><td></td></tr>
<tr><td>Spring Security</td><td></td><td>3.2.0.RELEASE</td><td></td></tr>
</table>



