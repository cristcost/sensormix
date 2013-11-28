package com.google.developers.gdgfirenze.memservice;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SensormixMemIntegrationTest {
	
	private static SensormixServiceMemoryImpl serviceMemoryImpl;

	@BeforeClass
  public static void setUpBeforeClass() throws Exception {
		serviceMemoryImpl = new SensormixServiceMemoryImpl();

//    alarms = new ArrayList<Alarm>();
//    existingAlarmIds = new ArrayList<String>();
//    existingAlarmIds.add(ALM_FIRE_1);
//    existingAlarmIds.add(ALM_FIRE_2);
//    existingAlarmTypeIds = new ArrayList<String>();
//    existingAlarmTypeIds.add(TYPE_FIRE_ALARM);
//    existingResourceIds = new ArrayList<String>();
//    existingResourceIds.add(RES_SMOKE_DETECTOR_1);
//    existingResourceIds.add(RES_SMOKE_DETECTOR_2);

//    alarmUpdates = new ArrayList<AlarmUpdate>();
//
//    initTime = Util.newDateTime("2013-01-01 08:00:00.000");
//    endTime = Util.newDateTime("2013-01-01 18:00:00.000");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void test1() {
    // Add an alarm, check again the size
//    alarms.add(Util.newAlarm(ALM_FIRE_1, TYPE_FIRE_ALARM,
//        Util.newStringArray(RES_SMOKE_DETECTOR_1, RES_SMOKE_DETECTOR_2),
//        "Pre-allarme fuoco delle ore 08:00", Util.newDateTime("2013-01-01 08:00:00.000"), null,
//        Util.newPosition(referenceId, Unit.LAT_LON, null, null, Util.newPoint(1.0, 1.0, null)),
//        null, AlarmSeverity.CRITICAL, AlarmStatus.PRE_ALARM));
//    memoryHistoryService.writeAlarms(alarms, Util.newDateTime("2013-01-01 09:00:00.000"));
//
//    assertEquals(0, memoryHistoryService.readAlarms(nonExistingAlarmIds).size());
//    assertEquals(1, memoryHistoryService.readAlarms(existingAlarmIds).size());
//
//    assertEquals(1, memoryHistoryService.getAlarmEvents(existingAlarmIds, null).size());
//    assertEquals(
//        1,
//        memoryHistoryService.getAlarmEvents(existingAlarmIds, Util.newTimeFilter(initTime, null)).size());
//    assertEquals(
//        1,
//        memoryHistoryService.getAlarmEvents(existingAlarmIds, Util.newTimeFilter(null, endTime)).size());
//    assertEquals(
//        1,
//        memoryHistoryService.getAlarmEvents(existingAlarmIds,
//            Util.newTimeFilter(initTime, endTime)).size());
//
//    assertEquals(0, memoryHistoryService.getAlarmEventLogs(null, null, null, null).size());
//    assertEquals(1,
//        memoryHistoryService.getAlarmEventLogs(existingAlarmIds, null, null, null).size());
//    assertEquals(
//        1,
//        memoryHistoryService.getAlarmEventLogs(existingAlarmIds,
//            Util.newTimeFilter(initTime, endTime), null, null).size());
//    assertEquals(
//        1,
//        memoryHistoryService.getAlarmEventLogs(
//            existingAlarmIds,
//            Util.newTimeFilter(initTime, endTime),
//            Util.newAlarmLocationFilter(Util.newLocations(Util.newCircle(referenceId,
//                Unit.LAT_LON, Util.newPoint(0.0, 0.0, null), 2.0))), null).size());
//    assertEquals(
//        1,
//        memoryHistoryService.getAlarmEventLogs(
//            existingAlarmIds,
//            Util.newTimeFilter(initTime, endTime),
//            Util.newAlarmLocationFilter(Util.newLocations(Util.newCircle(referenceId,
//                Unit.LAT_LON, Util.newPoint(0.0, 0.0, null), 2.0))),
//            Util.newAlarmFilter(existingAlarmTypeIds, null, null)).size());
  }
		
}