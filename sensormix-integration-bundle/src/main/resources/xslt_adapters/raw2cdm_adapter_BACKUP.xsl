<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:s="http://developers.google.com/gdgfirenze/ns/service" xmlns:m="http://developers.google.com/gdgfirenze/ns/model"
	version="1.0">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/">
	
		<s:samples xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">	 
		<xsl:variable name="deviceId" select="normalize-space(.//device_id)" />	
		<xsl:variable name="sampleTime" select="normalize-space(.//time)" />
				
			<!-- TODO: here are 3 fixed examples that need to be adapted from data as visible on src/test/resources/xml_raw_sample.xml -->
			<!-- 
			<numericValueSample value="123.4" sensorId="#sensorId"
				time="2013-10-30T19:31:08.445+01:00" type="#sampleType" />
			<wifiSignalSample bssid="00:00:00:00:00:00"
				capabilities="[C1][C2][C3]" frequency="2400.0" level="-40.0" ssid="WLAN Name"
				sensorId="#sensorId" time="2013-10-30T19:31:08.445+01:00" type="#sampleType" />
			<positionSample accuracy="10.0" alt="100.0" bearing="180.0"
				lat="43.0" lng="11.0" speed="0.0" sensorId="#sensorId"
				time="2013-10-30T19:31:08.445+01:00" type="#sampleType" />
			-->
		 	
		 <xsl:if test=".//battery_level">
			<numericValueSample>
				<xsl:attribute name="sensorId"><xsl:value-of select="$deviceId" /></xsl:attribute>
				<xsl:attribute name="time">
					<xsl:value-of select="substring-before($sampleTime, ' ')" />T<xsl:value-of select="substring-after($sampleTime, ' ')" />
				</xsl:attribute>
				<xsl:attribute name="type">urn:rixf:net.sensormix/sample_types/battery_level</xsl:attribute>
				<xsl:attribute name="value"><xsl:value-of select=".//battery_level" /></xsl:attribute>
			</numericValueSample>
		</xsl:if>
		
		<xsl:if test=".//position">
			<xsl:variable name="sampleTimePosition" select="normalize-space(.//position/time)" />
			<positionSample>
				<xsl:attribute name="sensorId"><xsl:value-of select="$deviceId" /></xsl:attribute>
				<xsl:attribute name="time">
					<xsl:value-of select="substring-before($sampleTimePosition, ' ')" />T<xsl:value-of select="substring-after($sampleTimePosition, ' ')" />
				</xsl:attribute>
				<xsl:attribute name="type">urn:rixf:net.sensormix/sample_types/phone_gps</xsl:attribute>
				<xsl:attribute name="accuracy"><xsl:value-of select=".//position/accuracy" /></xsl:attribute>
				<xsl:attribute name="alt"><xsl:value-of select=".//position/alt" /></xsl:attribute>
				<xsl:attribute name="bearing"><xsl:value-of select=".//position/bearing" /></xsl:attribute>
				<xsl:attribute name="lat"><xsl:value-of select=".//position/lat" /></xsl:attribute>
				<xsl:attribute name="lng"><xsl:value-of select=".//position/lng" /></xsl:attribute>
				<xsl:attribute name="speed"><xsl:value-of select=".//position/speed" /></xsl:attribute>
			</positionSample>
		</xsl:if>
		
		<xsl:if test=".//wifi_scans">
			<xsl:for-each select=".//wifi_scans/item">
				<wifiSignalSample>
					<xsl:attribute name="sensorId"><xsl:value-of select="$deviceId" /></xsl:attribute>
					<xsl:attribute name="time">
						<xsl:value-of select="substring-before($sampleTime, ' ')" />T<xsl:value-of select="substring-after($sampleTime, ' ')" />
					</xsl:attribute>
					<xsl:attribute name="type">urn:rixf:net.sensormix/sample_types/wifi_signal</xsl:attribute>
					<xsl:attribute name="bssid"><xsl:value-of select=".//bssid" /></xsl:attribute>
					<xsl:attribute name="capabilities"><xsl:value-of select=".//capabilities" /></xsl:attribute>
					<xsl:attribute name="frequency"><xsl:value-of select=".//frequency" /></xsl:attribute>
					<xsl:attribute name="level"><xsl:value-of select=".//level" /></xsl:attribute>
					<xsl:attribute name="ssid"><xsl:value-of select=".//ssid" /></xsl:attribute>
				</wifiSignalSample>
			</xsl:for-each>
		</xsl:if>
		
		</s:samples>
	</xsl:template>
</xsl:stylesheet>