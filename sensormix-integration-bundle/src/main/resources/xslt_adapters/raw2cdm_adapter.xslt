<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:cap="urn:oasis:names:tc:emergency:cap:1.2" 
	xmlns:wsn="http://docs.oasis-open.org/wsn/b-2"
	version="1.0">
	<xsl:output method="xml" indent="yes" />

	<xsl:template match="/">
		<AoccEventPayload xmlns:core="http://protectrail.eu/ns/service/core"
			xmlns:loc="http://protectrail.eu/ns/model/loc" 
			xmlns:alms="http://protectrail.eu/ns/service/alarm"
			xmlns:alm="http://protectrail.eu/ns/model/alarm" 
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

			<!-- <xsl:for-each select="/wsn:Notify/wsn:NotificationMessage"> -->
			<!-- <xsl:if test=".//wsn:Topic"> -->
			<!-- <xsl:variable name="topic" select="normalize-space(.//wsn:Topic)" 
				/> -->

			<!-- <xsl:for-each select=".//cap:alert"> -->
			<!-- <event xsi:type="alms:AlarmEvent"> -->
			<!-- <xsl:choose> -->
			<!-- <xsl:when test=".//cap:sent"> -->
			<!-- <xsl:attribute name="time"><xsl:value-of -->
			<!-- select="normalize-space(.//cap:sent)" /></xsl:attribute> -->
			<!-- </xsl:when> -->
			<!-- <xsl:otherwise> -->
			<!-- <xsl:attribute name="time"><xsl:value-of -->
			<!-- select="ex:date-time()" xmlns:ex="http://exslt.org/dates-and-times" 
				/></xsl:attribute> -->
			<!-- </xsl:otherwise> -->
			<!-- </xsl:choose> -->

			<!-- <alms:alarmDefinitions> -->
			<!-- <alms:alarm> -->
			<!-- <xsl:attribute name="id"> -->
			<!-- <xsl:value-of select="normalize-space(.//cap:identifier)" /> -->
			<!-- </xsl:attribute> -->
			<!-- <xsl:attribute name="typeId"><xsl:value-of -->
			<!-- select="$topic" /></xsl:attribute> -->

			<!-- <xsl:if test=".//cap:sent"> -->
			<!-- <xsl:attribute name="openTime"> -->
			<!-- <xsl:value-of select="normalize-space(.//cap:sent)" /> -->
			<!-- </xsl:attribute> -->
			<!-- </xsl:if> -->

			<!-- <xsl:attribute name="status">ALARM_OPEN</xsl:attribute> -->

			<!-- <xsl:if test=".//cap:severity"> -->
			<!-- <xsl:attribute name="severity"> -->
			<!-- <xsl:if test="contains(.//cap:severity, 'Extreme')">CRITICAL</xsl:if> -->
			<!-- <xsl:if test="contains(.//cap:severity, 'Severe')">MAJOR</xsl:if> -->
			<!-- <xsl:if test="contains(.//cap:severity, 'Moderate')">MINOR</xsl:if> -->
			<!-- <xsl:if test="contains(.//cap:severity, 'Minor')">MINOR</xsl:if> -->
			<!-- <xsl:if test="contains(.//cap:severity, 'Unknown')">MINOR</xsl:if> -->
			<!-- </xsl:attribute> -->
			<!-- </xsl:if> -->

			<!-- <xsl:if test=".//cap:note"> -->
			<!-- <xsl:element name="alm:message"> -->
			<!-- <xsl:value-of select="normalize-space(.//cap:note)" /> -->
			<!-- </xsl:element> -->
			<!-- </xsl:if> -->

			<!-- <xsl:if test=".//cap:circle"> -->
			<!-- <xsl:element name="alm:position"> -->
			<!-- <xsl:attribute name="referenceId">urn:rixf:com.poland.zmigrod/city</xsl:attribute> -->
			<!-- <xsl:attribute name="unit">LAT_LON</xsl:attribute> -->
			<!-- <xsl:element name="loc:point"> -->
			<!-- <xsl:attribute name="y"> -->
			<!-- <xsl:value-of select="substring-before(.//cap:circle, ',')" /> -->
			<!-- </xsl:attribute> -->
			<!-- <xsl:attribute name="x"> -->
			<!-- <xsl:value-of -->
			<!-- select="substring-after(substring-before(.//cap:circle, ' '), ',')" 
				/> -->
			<!-- </xsl:attribute> -->
			<!-- </xsl:element> -->
			<!-- </xsl:element> -->
			<!-- </xsl:if> -->

			<!-- <xsl:if test=".//cap:references"> -->
			<!-- <xsl:variable name="references" -->
			<!-- select="str:tokenize(.//cap:references, ',')" xmlns:str="http://exslt.org/strings" 
				/> -->
			<!-- <xsl:element name="alm:resourceIds"> -->
			<!-- <xsl:for-each select="$references"> -->
			<!-- <xsl:element name="alm:resourceId"> -->
			<!-- <xsl:value-of select="." /> -->
			<!-- </xsl:element> -->
			<!-- </xsl:for-each> -->
			<!-- </xsl:element> -->
			<!-- </xsl:if> -->

			<!-- </alms:alarm> -->
			<!-- </alms:alarmDefinitions> -->
			<!-- </event> -->
			<!-- </xsl:for-each> -->
			<!-- </xsl:if> -->
			<!-- </xsl:for-each> -->
		</AoccEventPayload>
	</xsl:template>
</xsl:stylesheet>