package pro.sky.telegrambot.utility;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ProcessUtilityTests {

	private final ProcessUtility processUtility = new ProcessUtility();

	@Test
	public void should_process_Start_OK() {
		processUtility.process("/start");
		assertEquals(ProcessResult.TELEGRAM, processUtility.getResult());
		assertEquals("Hello!", processUtility.getTelegramMessage());
		assertNull(processUtility.getDateTime());
		assertNull(processUtility.getNotificationTask());
	}

	@Test
	public void should_process_none() {
		processUtility.process("11 qwerty");
		assertEquals(ProcessResult.NONE, processUtility.getResult());
		assertNull(processUtility.getTelegramMessage());
		assertNull(processUtility.getDateTime());
		assertNull(processUtility.getNotificationTask());
	}

	@Test
	public void should_process_notificationTask_OK() {
		processUtility.process("16.02.2029 13:30 Memories of A. Navalny");
		assertEquals(ProcessResult.BOTH, processUtility.getResult());
		assertEquals("Notification saved", processUtility.getTelegramMessage());
		assertEquals(LocalDateTime.of(2029, Month.FEBRUARY, 16, 13, 30),
				processUtility.getDateTime());
		assertEquals("Memories of A. Navalny", processUtility.getNotificationTask());
	}
}
