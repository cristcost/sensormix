package com.google.developers.gdgfirenze.memservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ObjectsSetter class.
 */
public class Util {

  private static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
  
  public static Date newDateTime(String dateTimeString) throws ParseException {
    if (dateTimeString == null) {
      return null;
    }
    return dateTimeFormat.parse(dateTimeString);
  }
  
  public static Date newDate(String dateString) throws ParseException {
    if (dateString == null) {
      return null;
    }
    return dateFormat.parse(dateString);
  }

  public static Long newTime(String timeString) throws ParseException {
    if (timeString == null) {
      return null;
    }
    Date parsedDate = timeFormat.parse(timeString);
    return parsedDate.getTime();
  }
  
  public static List<String> newStringArray(String... stringList) {
    ArrayList<String> stringArray = new ArrayList<String>();
    for (String currentString : stringList) {
      stringArray.add(currentString);
    }
    return stringArray;
  }

//  public static Acknowledgement newAck(String operatorId, Date ackTime, String message) {
//    final Acknowledgement ack = new Acknowledgement();
//    if (operatorId != null) {
//      ack.setOperatorId(operatorId);
//    }
//    if (ackTime != null) {
//      ack.setAckTime(ackTime);
//    }
//    if (message != null) {
//      ack.setMessage(message);
//    }
//    return ack;
//  }
//
//  public static List<Acknowledgement> newAcks(Acknowledgement... acks) {
//    return Arrays.asList(acks);
//  }

//  public static Alarm newAlarm(String id, String typeId, List<String> resourceIds, String message,
//      Date openTime, Date closeTime, Position position, List<Acknowledgement> acknowledgements,
//      AlarmSeverity severity, AlarmStatus status) {
//
//    final Alarm ret = new Alarm();
//
//    if (id != null) {
//      ret.setId(id);
//    }
//    if (typeId != null) {
//      ret.setTypeId(typeId);
//    }
//    if (resourceIds != null && resourceIds.size() > 0) {
//      ret.setResourceIds(resourceIds);
//    }
//    if (message != null) {
//      ret.setMessage(message);
//    }
//    if (openTime != null) {
//      ret.setOpenTime(openTime);
//    }
//    if (closeTime != null) {
//      ret.setCloseTime(closeTime);
//    }
//    if (position != null) {
//      ret.setPosition(position);
//    }
//    if (acknowledgements != null && acknowledgements.size() > 0) {
//      ret.setAcknowledgements(acknowledgements);
//    }
//    if (severity != null) {
//      ret.setSeverity(severity);
//    }
//    if (status != null) {
//      ret.setStatus(status);
//    }
//    return ret;
//  }






  

  

 




}